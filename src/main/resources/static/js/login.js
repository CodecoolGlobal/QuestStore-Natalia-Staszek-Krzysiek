
const birds = new Audio("resources/birds.mp3");

function subPages(){
    const username = (document.getElementById("username").value);
    const password = (document.getElementById("password").value);
    if(username == "creep@gmail.com" && password == "11111")
    {
        location.href="creep.html";
    } else if ((username == "mentor@gmail.com" && password == "22222")) {
        location.href="creep.html";
    }else if ((username == "codecooler@gmail.com" && password == "33333")) {
        location.href="creep.html";
    }
}

//hamburger responsive
function myFunction() {
    const x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}

(() => {
    document.getElementById('confirm').addEventListener("click", () => subPages());

})();


function addClass() {
    const classesTable = document.getElementById("classes_table");

    const newClass = document.createElement("tr");
    const newMentor = document.createElement("td");
    const newClassName = document.createElement("td");

    newMentor.innerHTML = document.getElementById("choose_mentor").value;
    newClassName.innerHTML = document.getElementById("class_name").value;

    newClass.appendChild(newClassName);
    newClass.appendChild(newMentor);

    classesTable.appendChild(newClass);
}

function createNextLvlMin(thisLevel) {
    const nextLevel = 1 + thisLevel;
    const nextLevelMax = document.getElementById("level" + thisLevel + "_limit");
    const nextLevelMin = document.getElementById("level" + nextLevel + "_min");

    nextLevelMin.innerHTML = parseInt(nextLevelMax.value) + 1;
}

function createUpMinLevel(thisLevel) {
    const newLevelMin = parseInt(document.getElementById("level" + thisLevel + "_min").innerHTML);
    document.getElementById("level" + thisLevel + "_limit").min = newLevelMin + 1;
}


const form = document.querySelector("#login-form");

form.addEventListener('submit', function (e) {
    e.preventDefault();
    const data = `email=${this.email.value}&password=${this.password.value}`;
    getUser(data);
});
function clicking() {

}

function getUser(data) {
    fetch(`${apiUrl}/login`,
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