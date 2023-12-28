import { DB } from './pseudo.js';

var db = new DB();
let uid = localStorage.getItem('user_id');
if (uid == null){
	window.location.href = "reg.html"
	throw "can`t get user id";
}

function addElem(dom_parent, dom_child) {
	dom_parent.appendChild(dom_child);
}
var nxt = document.querySelector("#next");
if (!nxt){
	alert("Doc load error: check extensions and you connection");
	throw new ReferenceError('#next not found, reload doc! check extensions and you connection');
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
						params =>
						{
							orig = JSON.parse(params);
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

							row.addEventListener(
								"click",
								(e) => {
									nxt.href = `game${element.id}`
							});

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