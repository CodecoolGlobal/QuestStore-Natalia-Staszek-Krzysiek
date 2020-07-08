const form = document.querySelector("#register-form");

form.addEventListener('submit', function (e) {
    e.preventDefault();
    const data = `name=${this.name.value + " " + this.surname.value}&login=${this.login.value}&email=${this.email.value}&phone_number=${this.phone.value}&password=${this.password2.value}`;
    console.log(data);
    setStudent(data);
});

function setStudent(data) {
    fetch("http://localhost:9000/register",
        {
            mode: 'no-cors',
            method: "POST",
            body: data
        })
        .then(function (response) {
            console.log(response);
            console.log("Account created")
        });
}