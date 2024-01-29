'use strict';

let deleteForm = document.forms["formDelete"]

function deleteModal(userId) {
    // Отправляем запрос на получение данных пользователя по его идентификатору
    fetch(`/apiAuth/getUserById/${userId}`)
        .then(response => response.json())
        .then(user => {
            if (user) {
                const userName = user.username;
                console.log("User ID: " + userId);
                console.log("User Name: " + userName);

                $('#deleteModal').modal('show');
            } else {
                console.log("User not found");
            }
        })
        .catch(error => console.error("Error fetching user:", error));
}

function deleteUser() {
    const userId = deleteForm.id.value;

    fetch("apiAuth/users/" + userId, {
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
            console.log("Response from server:", data);
            $('#closeDelete').click();
            getTableUser();
        })
        .catch(error => {
            console.error('Error during DELETE request:', error);
        });
}
