export class Cell {
    field_id = 0;
    value = false;

    constructor(field_id, value) {
        this.field_id = field_id;
        this.value = value;
    }
}

export class GameField {
    id = 0;
    mines = 0;

    constructor(db, id, width, height, mines) {
        mines = Math.round(mines);
        if (
            width > 20 ||
            width < 4 ||
            height > 20 ||
            height < 4 ||
            mines > (width * height * 0.2)
        ) {
            console.log(width > 20);
            console.log(width < 4);
            console.log(height > 20);
            console.log(height < 4);
            console.log(mines);
            console.log((width * height * 0.2));
            throw new Error("Один из параметров поля не находится в диапазоне");
        }
        this.id = id;
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.data = new Array(width * height);
        for (let i = 0; i < this.data.length; i++) {
            this.data[i] = new Cell(this.id, i < mines);
        }
        this.data.sort(() => 0.5 - Math.random()); //shuffle array
        db.map_cells[id] = this.data;
    }

    getId() {
        return this.id;
    }
    getElem(x, y) {
        if (0 > x || width < x || 0 > y || height < y) {
            throw new Error('Выбрана ячейка вне диапазона');
        }
        return data[y * this.width + x];
    }
}

export class UserField {
    id = 0;
    mines = 0;
    original = 0;
    player = 0;

    getId() {
        return this.id;
    }
    getElem(x, y) {
        if (0 > x || width < x || 0 > y || height < y) {
            throw new Error('Выбрана ячейка вне диапазона');
        }
        return data[y * this.width + x];
    }
}

export class User {
    id = 0;
    login = "";
    password = "";
    saves = [];

    constructor(id, login, password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

}

export class DB {
/*
Get /isUserExists
Get /isFieldExists
Get /isCorrectPass
Get /make_coffee
Get /getGameMapsIds
Get /getUserSaves
Get /getUserCell
Get /getUserLogin
Get /getUserId
Get /getGameFieldParams
*/
isFieldExists(field_id)
{
    fetch(`https://localhost:8000/isFieldExists?field_id=${field_id}`)
        .then(response => {return response;});
}

isUserExists(login)
{
    fetch(`https://localhost:8000/isUserExists?login=${login}}`)
        .then( response => {return response;} )
}

isCorrectPass(login, pass)
{
    fetch(`https://localhost:8000/isCorrectPass?login=${login}&pass=${pass}`)
        .then( response => {return response;} )
}

newGameField(width, height, mines)
{
    fetch(
        "https://localhost:8000/newGameField", {
        method: "POST",
        body: JSON.stringify({
            width: width,
            height: height,
            mines: mines

        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
}

getGameMapsIds()
{
    fetch(
        "https://localhost:8000/getGameMapsIds", {
        method: "POST",
        body: JSON.stringify({
            MapsCount: 10

        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
    return
}

getUserSaves(login)
{

fetch(
    "https://localhost:8000/getUserSaves", {
    method: "POST",
    body: JSON.stringify({
        login: login

    }),
    headers: {
        "Content-type": "application/json; charset=UTF-8"
    }
})
}

getUserCell(login, field_id, pos_x, pos_y)
{
    fetch(
        "https://localhost:8000/getUserCell", {
        method: "POST",
        body: JSON.stringify({
            login: login,
            field_id: field_id,
            pos_x: pos_x,
            pos_y: pos_y
    
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
    return null;
}

newUser(login, pass)
{
    fetch(
        "https://localhost:8000/newUser", {
        method: "POST",
        body: JSON.stringify({
            login: login,
            pass: pass
    
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
}

newUserField(field_id, user_id)
{
    fetch(
        "https://localhost:8000/newUserField", {
        method: "POST",
        body: JSON.stringify({
            field_id: field_id,
            user_id: user_id
    
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
}

getUserLogin(user_id)
{

    fetch(
        "https://localhost:8000/getUserLogin", {
        method: "POST",
        body: JSON.stringify({
            user_id: user_id

        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
    return
}

getUserId(login, pass)
{
    fetch(
        "https://localhost:8000/getUserId", {
        method: "POST",
        body: JSON.stringify({
            login: login,
            pass: pass

        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
    return
}

getGameFieldParams(field_id)
{
    fetch(
        "https://localhost:8000/getGameFieldParams", {
        method: "POST",
        body: JSON.stringify({
            field_id: field_id

        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
    return {
        id: orig.id,
        width: orig.width,
        height: orig.height,
        mines: orig.mines
    };
}
}