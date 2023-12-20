import { DB } from './pseudo.js'

var db = new DB();
var dom_add_user    = document.getElementById("add_user");
var dom_add_map     = document.getElementById("add_map");
var dom_clear_users = document.getElementById("clear_users");
var dom_clear_maps  = document.getElementById("clear_maps");
var dom_clear_all   = document.getElementById("clear_all");

if (dom_add_user)
    dom_add_user.onclick = add_user;
if (dom_add_map)
    dom_add_map.onclick = add_map;
if (dom_clear_users)
    dom_clear_users.onclick = clear_users;
if (dom_clear_maps)
    dom_clear_maps.onclick = clear_maps;
if (dom_clear_all)
    dom_clear_all.onclick = clear_all;


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

function clear_users()
{
    db.users = {};
    db.save();
}

function clear_maps()
{
    db.map_fields = {};
    db.map_cells = {};
    db.save();
}

function clear_all()
{
    db.map_fields = {};
    db.map_cells = {};
    db.user_cells = {};
    db.user_fields = {};
    db.users = {};
    localStorage.clear();
    db.save();
}