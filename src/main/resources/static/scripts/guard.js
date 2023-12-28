let exit = document.querySelector('#exit');
if (exit)
    exit.addEventListener('click', () => {
        localStorage.clear();
        window.location.href = 'reg.html';
    });

var prv = document.querySelector("#back");
if (prv)
    prv.addEventListener(
        "click",
        e => {
        history.back();
    });