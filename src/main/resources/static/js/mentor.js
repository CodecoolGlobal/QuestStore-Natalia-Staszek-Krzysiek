const container = document.querySelector(".mentors");

function getMentors() {
    fetch("http://localhost:9000/mentors")
        .then(function (response) {
            if (response.ok) {
                return response.json();
            }
        })
        .then(function (mentors) {
            innerMentors(mentors);
        })
}


function innerMentors(mentors) {
    const table = document.querySelector(".tbody");
    mentors.forEach(mentor => {
        let row = document.createElement("tr");

        row.innerHTML =
            `<td>${mentor.id} </td>` +
            `<td>${mentor.name} </td>` +
            `<td>${mentor.login} </td>` +
            `<td>${mentor.email} </td>`;

        table.appendChild(row);
    });
}

getMentors();

