import { DB } from './pseudo.js';

var db = new DB();
let uid = localStorage.getItem('user_id');
if (uid == null){
	window.location.href = "reg.html"

	throw "can`t get user id";
}

var nxt = document.querySelector("#next");
var tab_save = document.querySelector("#maps");

function addElem(dom_parent, dom_child)
{
    dom_parent.appendChild(dom_child);
}
db.getGameMapsIds()
    .then(
        maps_ids =>
        {
            for (let i = 0; i < maps_ids.length; i++) {
                db.getGameField(maps_ids[i])
                    .then(
                        responce => {
                            var row         = document.createElement('tr');
                            var dom_id      = document.createElement('td');
                            var dom_width   = document.createElement('td');
                            var dom_height  = document.createElement('td');
                            var dom_mines   = document.createElement('td');

                            dom_id    .textContent = responce.id;
                            dom_width .textContent = responce.width;
                            dom_height.textContent = responce.height;
                            dom_mines .textContent = responce.mines;
                            
                            row.addEventListener(
                                "click",
                                (e) => 
                                {
                                    nxt.setAttribute(
                                        "href",
                                        `gameplay.html?id=${responce.id}`
                                    );
                                    nxt.style.visibility = "visible";
                                    }
                            );

                            addElem(row, dom_id);
                            addElem(row, dom_width);
                            addElem(row, dom_height);
                            addElem(row, dom_mines);
                            addElem(tab_save, row);
                        }
                );
            }
        
    });