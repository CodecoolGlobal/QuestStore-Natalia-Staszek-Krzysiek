
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

            console.log(response);
            window.location.href = response.url;

        })
}