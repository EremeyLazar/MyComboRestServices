let formEdit = document.forms["formEdit"];
editUser();

async function editModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#edit'));
    await openAndFillInTheModal(formEdit, modal, id);
}

function editUser() {
    formEdit.addEventListener("submit", ev => {
        ev.preventDefault();
        let roles = [];
        for (let i = 0; i < formEdit.roles.options.length; i++) {
            if (formEdit.roles.options[i].selected) {
                roles.push(formEdit.roles.options[i].text.replace('ROLE_', ''));
            }
        }
        fetch("apiAuth/user/" + formEdit.id.value, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.id.value,
                username: formEdit.username.value,
                yob: formEdit.yob.value,
                password: formEdit.password.value,
                country: formEdit.country.value,
                roles: roles
            })
        }).then(() => {
            $('#closeEdit').click();
            getTableUser()
        });
    });
}
