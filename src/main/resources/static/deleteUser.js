
function deleteModal(userId) {
    fetch(`/apiUser/getUserById/${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(user => {
            if (user && user.username) {
                const userName = user.username;
                $('#userNameToDelete').text(userName);
                $('#userIdToDelete').text(userId);
                $('#deleteModal').modal('show');
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
            $('#deleteModal').modal('hide');
            getTableUser();
        })
        .catch(error => {
            console.error('Error during DELETE request:', error.message);
        });
}

