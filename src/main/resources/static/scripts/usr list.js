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