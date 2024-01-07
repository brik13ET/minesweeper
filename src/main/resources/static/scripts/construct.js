import { DB } from './pseudo.js'

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

var difficulty  = document.getElementById("difficulty");
var autoFill    = document.getElementById("autoFill"  );
var gameBoard   = document.getElementById("minefield" );
var save        = document.getElementById("save"      );
var back        = document.getElementById("back"      );

if (difficulty)
    difficulty  .addEventListener("change",updateDifficulty  );
if (autoFill)
    autoFill    .addEventListener("click", autofillMines     );
if (save)
    save        .addEventListener("click", sendBoard         );
if (back)
    back        .addEventListener("click", () => { history.back(); });
var width = 10, height = 10, mines = 20;
db.getDifficulty(difficulty.value)
    .then(
        resp =>
        {
            width = resp.width ;
            height= resp.height;
            mines = resp.mines ;
});

var logical_minefield = new Array(width*height).fill(false);
updateDifficulty();

function updateDifficulty() {
    db.getDifficulty(difficulty.value)
        .then(
            resp => {
                width = resp.width ;
                height= resp.height;
                mines = resp.mines ;
                logical_minefield = new Array(width*height).fill(false);
                draw_minefield();
    });
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
    var plantedCells = width*height*(mines/100);
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