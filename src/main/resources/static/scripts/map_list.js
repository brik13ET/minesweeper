import { DB, User, GameField, Cell } from './pseudo.js';

var db = new DB();
let uid = localStorage.getItem('user_id');
if (uid == null){
	window.location.href = "reg.html"

	throw "can`t get user id";
}

function addElem(dom_parent, dom_child)
{
    dom_parent.appendChild(dom_child);
}
var maps = db.getGameMapsIds();
var tab_save = document.getElementById("game_tab");
for (let i = 0; i < maps.length; i++) {
    const element       = db.getGameFieldParams(maps[i]);
    var dom_row         = document.createElement('tr');
    var dom_id          = document.createElement('td');
    var dom_width       = document.createElement('td');
    var dom_height      = document.createElement('td');
    var dom_mines       = document.createElement('td');

    dom_id.value      = element.id;
    dom_width.value   = element.width;
    dom_height.value  = element.height;
    dom_mines.value   = element.mines;

    addElem(row,dom_id);
    addElem(row,dom_width);
    addElem(row,dom_height);
    addElem(row,dom_mines);
    addElem(tab_save,row);
}