import { DB, User, GameField, Cell } from './pseudo.js';

var db = new DB();
let uid = localStorage.getItem('user_id');
if (uid == null){
	window.location.href = "reg.html"

	throw "can`t get user id";
}

var nxt = document.querySelector("#mext");
var prv = document.querySelector("#back");

prv.addEventListener(
    "click",
    e => {
    history.back();
});

function addElem(dom_parent, dom_child)
{
    dom_parent.appendChild(dom_child);
}
db.getGameMapsIds()
.then( maps => {
    var tab_save = document.getElementById("game_tab");
    for (let i = 0; i < maps.length; i++) {
        db.getGameFieldParams(maps[i])
            .then(element =>
            {
                var dom_row         = document.createElement('tr');
                var dom_id          = document.createElement('td');
                var dom_width       = document.createElement('td');
                var dom_height      = document.createElement('td');
                var dom_mines       = document.createElement('td');

                dom_id      .value  = element.id;
                dom_width   .value  = element.width;
                dom_height  .value  = element.height;
                dom_mines   .value  = element.mines;
                
                dom_row.addEventListener(
                    "click",
                    e => {
                    nxt.style.visibility = "visible";
                    nxt.attributes.href=`gameplay.html?newGame=True&id=${element.id}`;
                });

                addElem(dom_row,dom_id);
                addElem(dom_row,dom_width);
                addElem(dom_row,dom_height);
                addElem(dom_row,dom_mines);
                addElem(tab_save,row);
            });
    }
});