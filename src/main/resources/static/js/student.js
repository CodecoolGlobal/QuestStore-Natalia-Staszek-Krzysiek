const container = document.querySelector(".students");

function getStudents() {
    fetch("http://localhost:9000/students")
        .then(function (response) {
            if (response.ok) {
                return response.json();
            }
        })
        .then(function (students) {
            innerStudents(students);
        })
}


function innerStudents(students) {
    const table = document.querySelector(".tbody")
    students.forEach(student => {
        let row = document.createElement("tr");

        row.innerHTML =
        `<td>${student.id} </td>` +
        `<td>${student.name} </td>` +
        `<td>${student.login} </td>` +
        `<td>${student.email} </td>`;

        table.appendChild(row);
    });
}

getStudents();

