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

var map_diff    = document.querySelector('#difficulty'  );
var map_height  = document.querySelector('#map_height'  );
var map_width   = document.querySelector('#map_width'   );
var map_mines   = document.querySelector('#map_mines'   );
var map_lives   = document.querySelector('#lifes'       );
var Save_dif    = document.querySelector('#Save_dif'    );

db.getDifficulty(map_diff.value)
    .then(
        resp => {
            map_height.value = resp.height;
            map_width .value = resp.width;
            map_mines .value = resp.mines;
            map_lives .value = resp.lives;
            map_diff.options[map_diff.value].text;
});

map_diff.addEventListener(
    "change",
    (e) => {
        db.getDifficulty(e.target.value)
            .then(
                resp => {
                    map_height.value = resp.height;
                    map_width .value = resp.width;
                    map_mines .value = resp.mines;
                    map_lives .value = resp.lives;
                    map_diff.options[map_diff.value].text;
        });
});

Save_dif.addEventListener(
    "click",
    () => 
        db.setDifficulty(
            map_diff  .value,
            map_diff.options[map_diff.value].text,
            map_width .value,
            map_height.value,
            map_mines .value,
            map_lives .value
        )
        .then(
            resp => {
                if (resp)
                    alert('Данные успешно сохранены');
        })
);