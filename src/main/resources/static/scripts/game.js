const flagsLeft = document.querySelector('#flags-left')
const result = document.querySelector('#result')
var width = document.getElementById("map_width");
var height = document.getElementById("map_height");
var bombAmount = document.getElementById("map_mines");
var gameBoard = document.getElementById("minefield");
var saveBoard = document.getElementById("save");
let flags = 0
let squares = []
let isGameOver = false
let lifes = document.querySelector('#lifes')
let lifesAmount = 3
for (let i = 0; i < width * height; i++) {
    const square = document.createElement('div')
    square.setAttribute('id', i)
    square.classList.add(shuffledArray[i])
    grid.appendChild(square)
    squares.push(square)

    //normal click
    square.addEventListener('click', function (e) {
        click(square)
    })

    //cntrl and left click
    square.oncontextmenu = function (e) {
        e.preventDefault()
        addFlag(square)
    }
}
var logical_minefield = new Array(width * height).fill(false);
function draw_minefield() {
    gameBoard.innerHTML = "minefield";
    for (var row = 0; row < height; row++) {
        var newRow = document.createElement("tr");
        for (var col = 0; col < width; col++) {
            var newCell = document.createElement("td");
            const pos = row * width + col;
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
draw_minefield();
function gameOver(square) {
    result.innerHTML = 'BOOM! Game Over!'
    isGameOver = true

    //show ALL the bombs
    squares.forEach(square => {
        if (square.classList.contains('bomb')) {
            square.innerHTML = '💣'
            square.classList.remove('bomb')
            square.classList.add('checked')
        }
    })
}
function addFlag(square) {
    if (isGameOver) return
    if (!square.classList.contains('checked') && (flags < bombAmount)) {
        if (!square.classList.contains('flag')) {
            square.classList.add('flag')
            square.innerHTML = ' 🚩'
            flags++
            flagsLeft.innerHTML = bombAmount - flags
            checkForWin()
        } else {
            square.classList.remove('flag')
            square.innerHTML = ''
            flags--
            flagsLeft.innerHTML = bombAmount - flags
        }
    }
}

//click on square actions
function click(square) {
    let currentId = square.id
    if (isGameOver) return
    if (square.classList.contains('checked') || square.classList.contains('flag')) return
    if (square.classList.contains('bomb')) {
        lifes--
        if (lifes == 0) { gameOver(square) }
    } else {
        let total = square.getAttribute('data')
        if (total != 0) {
            square.classList.add('checked')
            if (total == 1) square.classList.add('one')
            if (total == 2) square.classList.add('two')
            if (total == 3) square.classList.add('three')
            if (total == 4) square.classList.add('four')
            square.innerHTML = total
            return
        }

        checkSquare(square, currentId)
    }
    square.classList.add('checked')
}


//check neighboring squares once square is clicked
function checkSquare(square, currentId) {
    const isLeftEdge = (currentId % width === 0)
    const isRightEdge = (currentId % width === width - 1)

    setTimeout(() => {
        if (currentId > 0 && !isLeftEdge) {
            const newId = squares[parseInt(currentId) - 1].id
            //const newId = parseInt(currentId) - 1   ....refactor
            const newSquare = document.getElementById(newId)
            click(newSquare)
        }
        if (currentId > 9 && !isRightEdge) {
            const newId = squares[parseInt(currentId) + 1 - width].id
            //const newId = parseInt(currentId) +1 -width   ....refactor
            const newSquare = document.getElementById(newId)
            click(newSquare)
        }
        if (currentId > 10) {
            const newId = squares[parseInt(currentId - width)].id
            //const newId = parseInt(currentId) -width   ....refactor
            const newSquare = document.getElementById(newId)
            click(newSquare)
        }
        if (currentId > 11 && !isLeftEdge) {
            const newId = squares[parseInt(currentId) - 1 - width].id
            //const newId = parseInt(currentId) -1 -width   ....refactor
            const newSquare = document.getElementById(newId)
            click(newSquare)
        }
        if (currentId < 98 && !isRightEdge) {
            const newId = squares[parseInt(currentId) + 1].id
            //const newId = parseInt(currentId) +1   ....refactor
            const newSquare = document.getElementById(newId)
            click(newSquare)
        }
        if (currentId < 90 && !isLeftEdge) {
            const newId = squares[parseInt(currentId) - 1 + width].id
            //const newId = parseInt(currentId) -1 +width   ....refactor
            const newSquare = document.getElementById(newId)
            click(newSquare)
        }
        if (currentId < 88 && !isRightEdge) {
            const newId = squares[parseInt(currentId) + 1 + width].id
            //const newId = parseInt(currentId) +1 +width   ....refactor
            const newSquare = document.getElementById(newId)
            click(newSquare)
        }
        if (currentId < 89) {
            const newId = squares[parseInt(currentId) + width].id
            //const newId = parseInt(currentId) +width   ....refactor
            const newSquare = document.getElementById(newId)
            click(newSquare)
        }
    }, 10)
}

//check for win
function checkForWin() {
    ///simplified win argument
    let matches = 0

    for (let i = 0; i < squares.length; i++) {
        if (squares[i].classList.contains('flag') && squares[i].classList.contains('bomb')) {
            matches++
        }
        if (matches === bombAmount) {
            result.innerHTML = 'YOU WIN!'
            isGameOver = true
        }
    }
}
function sendBoard() {
    db.newGameField(width, height, logical_minefield)
        .then(resp => {
            alert('Успешно опубликовано');
        });
}