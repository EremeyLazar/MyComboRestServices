let formEdit = document.forms["editModal"];
// editUser();

function editModal(userId) {
    // Отправляем запрос на получение данных пользователя по его идентификатору
    fetch(`/apiAuth/getUserById/${userId}`)
        .then(response => response.json())
        .then(user => {
            // Заполняем данные пользователя в модальном окне
            document.getElementById('Edit_id').value = user.id;
            document.getElementById('Edit_username').value = user.username;
            document.getElementById('Edit_password').value = user.password;
            document.getElementById('Edit_yob').value = user.yob;
            document.getElementById('Edit_country').value = user.country;

            // Открываем модальное окно
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

        // Собираем данные из формы
        let id = formEdit.id.value;
        let username = formEdit.username.value;
        let password = formEdit.password.value;
        let yob = formEdit.yob.value;
        let country = formEdit.country.value;

        // Отправляем PUT-запрос на сервер для обновления пользователя
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

                // Закрываем модальное окно
                $('#edit').modal('hide');

                // window.location.href = 'http://localhost:8080/admin';

                // Можете выполнить дополнительные действия после успешного обновления
            })
            .catch(error => {
                console.error('Error during PUT request:', error);
            });
    });

}