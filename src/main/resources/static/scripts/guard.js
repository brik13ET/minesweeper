let uid = localStorage.getItem('user_id');

let exit = document.querySelector('#exit');
if (exit)
    exit.addEventListener('click', () => {
        localStorage.clear();
        window.location.href = 'reg.html';
    });
if (uid == null)
{
    localStorage.clear();
    if (!(/\/reg\.html$/.test(window.location.href))){
        console.log(`${window.location.href}: ${/\/reg\.html$/.test(window.location.href)}`);
        window.location.href = 'reg.html';
    }
}
if (uid == 0 && /\/Menu\.html$/.test(window.location.href))
    window.location.href = 'admin_menu.html';
if (uid != 0 && /\/admin_menu\.html$/.test(window.location.href))
    window.location.href = 'Menu.html';