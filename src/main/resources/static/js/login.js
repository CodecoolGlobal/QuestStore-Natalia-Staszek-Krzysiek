
const birds = new Audio("resources/birds.mp3");


const form = document.querySelector("#confirm");
console.log(form)
form.addEventListener('click', function (e) {
console.log(1);
    e.preventDefault();
    let login = document.querySelector('#login');
    let password = document.querySelector('#password');
    console.log(login);
    console.log(login.value);
    console.log(password);
    console.log(password.value);
    const data = `login=${login.value}&password=${password.value}`;
    getUser(data);
});

function getUser(data) {
console.log(2);
    fetch(`http://localhost:8100/login`,
        {
            // mode: 'no-cors',
            method: "POST",
            body: data
        })
        .then(function (response) {
            let user = response.json();
            return user;
        })
        .then(function (user) {
            if (user.role == 'CREEP') {
                console.log('I am creep');
                sessionStorage.setItem('id', user.id);
                sessionStorage.setItem('role', user.role);
                window.location.replace("creep.html");
            } else if (user.role == 'MENTOR') {
                console.log('I am mentor');
                sessionStorage.setItem('id', user.id);
                sessionStorage.setItem('role', user.role);
                window.location.replace("creep.html");
            } else if (user.role == 'STUDENT') {
                console.log('I am student');
                sessionStorage.setItem('id', user.id);
                sessionStorage.setItem('role', user.role);
                window.location.replace("creep.html");
            } else {
                console.log('blabla');
            }
        });
}