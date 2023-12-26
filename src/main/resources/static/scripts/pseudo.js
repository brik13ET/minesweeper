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
    async isFieldExists(field_id) {
        let responce = await fetch(`http://localhost:8080/isFieldExists?field_id=${field_id}`);
        return await responce.json();
    }

    async isUserExists(login) {
        let responce = await fetch(`http://localhost:8080/isUserExists?login=${login}`);
        return await responce.json();
    }

    async isCorrectPass(login, pass) {
        let responce = await fetch(`http://localhost:8080/isCorrectPass?login=${login}&pass=${pass}`);
        return await responce.json();
    }

    async newGameField(width, height, mines) {
        let responce = await fetch("http://localhost:8080/newGameField", {
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
        return await responce.json();
    }

    async getGameMapsIds() {
        let responce = await fetch("http://localhost:8080/getGameMapsIds?MapsCount=10");
        return await responce.json();
    }

    async getUserSaves(uid) {
        let responce = await fetch(`http://localhost:8080/getUserSaves?uid=${uid}`);
        return await responce.json();
    }

    async getUserCell(uid, field_id, pos_x, pos_y) {
        let responce = await fetch(`http://localhost:8080/getUserCell?uid=${uid}&&field_id=${field_id}&pos_x=${pos_x}&pos_y=${pos_y}`);
        return await responce.json();
    }

    async newUser(login, pass) {
        let responce = await fetch(
            "http://localhost:8080/newUser", {
            method: "POST",
            body: JSON.stringify({
                login: login,
                pass: pass

            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        });
        
        return await responce.json();
    }

    async newUserField(field_id, user_id) {
        let responce = await fetch(
            "http://localhost:8080/newUserField", {
            method: "POST",
            body: JSON.stringify({
                field_id: field_id,
                user_id: user_id

            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        });
        
        return await responce.json();
    }

    async getUserLogin(user_id) {

        let responce = await fetch(`http://localhost:8080/getUserLogin?uid=${user_id}`);
        return await responce.text();
    }

    async getUserId(login, pass) {
        let responce = await fetch(`http://localhost:8080/getUserId?login=${login}&pass=${pass}`);
        return await responce.json();
    }

    async getGameFieldParams(field_id) {
        let responce = await fetch(`http://localhost:8080/getGameFieldParams?field_id=%{field_id}`);
        let data = await responce.json();
        return {
            id:     data.id,
            width:  data.width,
            height: data.height,
            mines:  data.mines
        };
    }
}