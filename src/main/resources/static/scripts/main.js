import { DB } from './pseudo.js'

var db = new DB();
var dom_add_user    = document.getElementById("add_user");
var dom_add_map     = document.getElementById("add_map");

if (dom_add_user)
    dom_add_user.onclick = add_user;
if (dom_add_map)
    dom_add_map.onclick = add_map;


function add_user()
{
    var usrname = document.getElementById('username').value;
    var usrpass = document.getElementById('pass').value;
    db.newUser(usrname, usrpass);
}


function add_map()
{
    var map_width = document.getElementById('map_width').value;
    var map_height = document.getElementById('map_height').value;
    var map_mines = document.getElementById('map_mines').value / 100;
    db.newGameField(
        map_width,
        map_height,
        map_mines * map_width * map_height
    );
}