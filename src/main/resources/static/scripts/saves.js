import { DB, User, GameField, Cell } from './pseudo.js';

var db = new DB();
var uid = localStorage.getItem('user_id');
function addElem(dom_parent, dom_child)
{
    dom_parent.appendChild(dom_child);
}
// console.log(uid);
var saves = db.getUserSaves(db.getUserLogin(uid));
var tab_save = document.getElementById("game_saves");
for (let i = 0; i < saves.length; i++) {
    const element  = array[i];
    const orig     = db.getGameFieldParams(element.orig);
    var row        = document.createElement('tr');
    var game_id    = document.createElement('td');
    var wid        = document.createElement('td');
    var hei        = document.createElement('td');
    var orig_mines = document.createElement('td');
    var user_mines = document.createElement('td');
    
    game_id.value  = element.id;
    wid            = element.width;
    hei            = element.height;
    orig_mines     = orig.mines;
    user_mines     = element.mines;

    addElem(row,game_id);
    addElem(row,wid);
    addElem(row,hei);
    addElem(row,orig_mines);
    addElem(row,user_mines);
    addElem(tab_save,row);
}