let formEdit = document.forms["editModal"];

function editModal(userId) {
    fetch(`/apiUser/getUserById/${userId}`)
        .then(response => response.json())
        .then(user => {
            document.getElementById('Edit_id').value = user.id;
            document.getElementById('Edit_username').value = user.username;
            document.getElementById('Edit_password').value = user.password;
            document.getElementById('Edit_yob').value = user.yob;
            document.getElementById('Edit_country').value = user.country;
            $('#edit').modal('show');
            $('#confirmEdit').on('click', function () {
                editUser();
            });
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
        });
}

function editUser() {
    let formEdit = document.forms["formEdit"];
    formEdit.addEventListener("submit", function (event) {
        event.preventDefault();
        let id = formEdit.id.value;
        let username = formEdit.username.value;
        let password = formEdit.password.value;
        let yob = formEdit.yob.value;
        let country = formEdit.country.value;
        fetch(`/apiAuth/users`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: id,
                username: username,
                password: password,
                yob: yob,
                country: country
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log("User updated! Status:", data);
                $('#edit').modal('hide');
                getTableUser();
                // window.location.href = 'http://localhost:8080/admin';
            })
            .catch(error => {
                console.error('Error during PUT request:', error);
            });
    });
}