export class DB {
    async isFieldExists(field_id) {
        let responce = await fetch(`isFieldExists?field_id=${field_id}`);
        return await responce.json();
    }

    async isUserExists(login) {
        let responce = await fetch(`isUserExists?login=${login}`);
        return await responce.json();
    }

    async isCorrectPass(login, pass) {
        let responce = await fetch(`isCorrectPass?login=${login}&pass=${pass}`);
        return await responce.json();
    }

    async newGameField(width, height, mines) {
        let responce = await fetch("newGameField", {
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
        let responce = await fetch("getGameMapsIds?MapsCount=10");
        return await responce.json();
    }

    async getUserSaves(uid) {
        let responce = await fetch(`getUserSaves?uid=${uid}`);
        return await responce.json();
    }

    async getUserCell(uid, field_id, pos_x, pos_y) {
        let responce = await fetch(`getUserCell?uid=${uid}&&field_id=${field_id}&pos_x=${pos_x}&pos_y=${pos_y}`);
        return await responce.json();
    }

    async newUser(login, pass) {
        let responce = await fetch(
            "newUser", {
            method: "POST",
            body: JSON.stringify({
                login: login,
                pass: pass

            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        });
        
        return await responce.ok;
    }

    async newUserField(field_id, user_id) {
        let responce = await fetch(
            "newUserField", {
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

        let responce = await fetch(`getUserLogin?uid=${user_id}`);
        return await responce.text();
    }

    async getUserId(login, pass) {
        let responce = await fetch(`getUserId?login=${login}&pass=${pass}`);
        return await responce.json();
    }

    async getGameField(field_id) {
        let responce = await fetch(`getGameFieldParams?field_id=${field_id}`);
        let data = await responce.json();
        return {
            id:     data.id,
            width:  data.width,
            height: data.height,
            mines:  data.mines
        };
    }
}