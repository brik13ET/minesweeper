import { DB } from './pseudo.js'

// localStorage.clear();
var db = new DB();
let uid = localStorage.getItem('user_id');
if (uid != null){
    if (uid != 0){
        window.location.href = "Menu.html"
	    throw "had user id. redirect";
    }
}
else
    throw "bad user id. redirect";

var widthSlider = document.getElementById("map_width" );
var heightSlider= document.getElementById("map_height");
var minesSlider = document.getElementById("map_mines" );
var autoFill    = document.getElementById("autoFill"  );
var gameBoard   = document.getElementById("minefield" );
var saveBoard   = document.getElementById("save"      );

if (widthSlider)
    widthSlider .addEventListener("input" ,updateWidthValue  );
if (heightSlider)
    heightSlider.addEventListener("input" ,updateHeightValue );
if (minesSlider)
    minesSlider .addEventListener("input" ,updateMinesValue  );
if (autoFill)
    autoFill    .addEventListener("click", autofillMines     );
if (save)
    save        .addEventListener("click", sendBoard         );

var numOfMines= 10;
var width     = 10;
var height    = 10;
var logical_minefield = new Array(width*height).fill(false);

function updateWidthValue() {
    document.getElementById("widthValue").textContent = widthSlider.value;
    width = parseInt(widthSlider.value);
    logical_minefield = new Array(width*height).fill(false);
    draw_minefield();
}

function updateHeightValue() {
    document.getElementById("heightValue").textContent = heightSlider.value;
    height = parseInt(heightSlider.value);
    logical_minefield = new Array(width*height).fill(false);
    draw_minefield();
}

function updateMinesValue() {
    document.getElementById("minesValue").textContent = minesSlider.value;
    numOfMines = parseInt(minesSlider.value);
    logical_minefield = new Array(width*height).fill(false);
    draw_minefield();
}

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

function draw_minefield()
{
    gameBoard.innerHTML = "";
    for (var row = 0; row < height; row++) {
        var newRow = document.createElement("tr");
        for (var col = 0; col < width; col++) {
            var newCell = document.createElement("td");
            const pos = row*width+col;
            newCell.onclick = (e) => {
                logical_minefield[pos] = !logical_minefield[pos];
                if (logical_minefield[pos])
                    e.target.textContent = "💣";
                else
                    e.target.textContent = " ";
            };
            if (logical_minefield[pos])
                newCell.textContent = "💣";
            else
                newCell.textContent = " ";
            newRow.appendChild(newCell);
        }
        gameBoard.appendChild(newRow);
    }
}

export function autofillMines() {
    var plantedCells = width*height*(numOfMines/100);
    for (let i = 0; i < logical_minefield.length; i++) {
        logical_minefield[i] = i < plantedCells;
    }
    shuffleArray(logical_minefield);
    draw_minefield();
}

function sendBoard()
{
    db.newGameField(width, height, logical_minefield)
        .then( resp => {
            alert('Успешно опубликовано');
        });
}

draw_minefield();