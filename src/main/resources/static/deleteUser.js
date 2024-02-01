// 'use strict';

let deleteForm = document.forms["formDelete"];

function deleteModal(userId) {
    // Отправляем запрос на получение данных пользователя по его идентификатору
    fetch(`/apiAuth/getUserById/${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json(); // Преобразовать ответ в JSON
        })
        .then(user => {
            // Обрабатывать данные пользователя
            if (user && user.username) {
                const userName = user.username;

                // Вставляем данные пользователя в модальное окно
                $('#userNameToDelete').text(userName);
                $('#userIdToDelete').text(userId);

                $('#deleteModal').modal('show');

                // Добавляем обработчик события для кнопки удаления в модальном окне
                $('#confirmDelete').on('click', function () {
                    deleteUser(userId);
                });
            } else {
                console.log("User not found");
            }
        })
        .catch(error => {
            console.error('Error during GET request:', error);
        });
}
function deleteUser(userId) {
    fetch(`/apiAuth/users/${userId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log("User has been deleted! Status:", data);
            $('#closeDelete').click();
            getTableUser();
            // window.location.href = 'http://localhost:8080/admin'; // Перенаправление на страницу admin после удаления
        })
        .catch(error => {
            console.error('Error during DELETE request:', error.message);
        });
}

