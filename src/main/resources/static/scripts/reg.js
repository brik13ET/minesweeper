import { DB } from './pseudo.js'

// localStorage.clear();
var db = new DB();
let uid = localStorage.getItem('user_id');
if (uid != null){
    if (uid != 0)
        window.location.href = "Menu.html"
    else
        window.location.href = 'admin_menu.html'
	throw "had user id. redirect";
}

let login = document.querySelector('#login');
let password = document.querySelector('#password');
let submit = document.querySelector('#submit');
let submit1 = document.querySelector('#submit1');
if (submit)
    submit.addEventListener('click', () => {

        const loginUser = login.value;
        const passwordUser = password.value;

        if (loginUser.length < 4 || loginUser.length > 10) {
            alert('Длина логина должна быть от 4 до 10 символов');
            throw new Error('Длина логина должна быть от 4 до 10 символов');
        }
        if (passwordUser.length < 4 || passwordUser.length > 12) {
            alert('Длина пароля должна быть от 4 до 12 символов');
            throw new Error('Длина пароля должна быть от 4 до 12 символов');
        }
        const validCharacters = /^[a-zA-Z0-9_а-яА-Я]+$/;
        if (!validCharacters.test(loginUser) || !validCharacters.test(passwordUser)) {
            alert('Логин и пароль могут содержать только латинские буквы, кириллицу, цифры и знаки подчеркивания');
            throw new Error('Логин и пароль могут содержать только латинские буквы, кириллицу, цифры и знаки подчеркивания');
        }
        if (loginUser === '' || passwordUser === '') {
            alert('Логин или пароль не могут быть пустыми');
            throw new Error('Логин или пароль не могут быть пустыми');
        }

        db.isUserExists(loginUser)
            .then(
                isExists => {
                    if (isExists) {
                        alert('Пользователь с таким логином уже зарегистрирован');
                        throw new Error('Пользователь с таким логином уже зарегистрирован');
                    }
                    return db.newUser(loginUser, passwordUser);
                })
            .then(
                db.isUserExists(loginUser)
            )
            .then(
                isExistsNew => {
                    if (isExistsNew)
                        alert('Вы успешно зарегистрированы');
                });
    });
if (submit1)
    submit1.addEventListener('click', () => {
        const loginUser = login.value;
        const passwordUser = password.value;
        db.isUserExists(loginUser)
            .then(responce => {
                if (!responce) {
                    alert('Пользователь с таким логином не найден');
                    throw new Error('Пользователь с таким логином не найден');
                }
                return db.isCorrectPass(loginUser, passwordUser);
            })
            .then(responce => {
                if (responce) {
                    db.getUserId(loginUser, passwordUser)
                        .then(responce => {
                            localStorage.setItem('user_id', responce);
                            alert('Вы успешно вошли');
                            if (responce != 0)
                                window.location.href = 'Menu.html';
                            else
                                window.location.href = 'admin_menu.html'
                        });
                } else {
                    alert('Неверное имя пользователя или пароль');
                    throw new Error('Неверное имя пользователя или пароль');
                }
            });
    });
