import { DB, User, GameField, Cell } from './pseudo.js';

var db = new DB();
let uid = localStorage.getItem('user_id');
if (uid == null){
	window.location.href = "reg.html"
	throw "can`t get user id";
}

function addElem(dom_parent, dom_child) {
	dom_parent.appendChild(dom_child);
}
// console.log();
db.getUserSaves(uid)
	.then(
		responce => {
			var tab_save = document.getElementById("game_saves");
			for (let i = 0; i < responce.length; i++) {
				const element = responce[i];
				db.getGameFieldParams(element.orig)
					.then(
						responce =>
						{
							orig = JSON.parse(responce);
							var row     	= document.createElement('tr');
							var game_id 	= document.createElement('td');
							var wid     	= document.createElement('td');
							var hei     	= document.createElement('td');
							var orig_mines	= document.createElement('td');
							var user_mines	= document.createElement('td');

							game_id.value	= element.id;
							wid				= element.width;
							hei				= element.height;
							orig_mines		= orig.mines;
							user_mines		= element.mines;

							addElem(row, game_id);
							addElem(row, wid);
							addElem(row, hei);
							addElem(row, orig_mines);
							addElem(row, user_mines);
							addElem(tab_save, row);
						}
				);
			}
	}
)