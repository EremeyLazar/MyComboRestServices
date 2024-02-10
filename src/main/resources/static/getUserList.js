// Объявление переменной tbody
const tbody = $('#AllUsers');

// Функция для получения и отображения пользователей
function getTableUser() {
    tbody.empty();  // Очистка содержимого tbody перед добавлением новых данных
    fetch("apiAuth/users")
        .then(res => res.json())
        .then(users => {
            console.log(users);
            users.forEach(user => {
                const roles = user.roles.map(role => role.name.replace('ROLE_', '')).join(', ');
                const gameInfo = user.game ? `${user.game.totalRate}` : 'No Game';

                const usersRow = $(`<tr>
                    <td class="pt-3" id="userID">${user.id}</td>
                    <td class="pt-3" >${user.username}</td>
                    <td class="pt-3" >${user.yob}</td>
                    <td class="pt-3" >${user.country}</td>
                    <td class="pt-3" >${roles}</td>
                    <td class="pt-3" >${gameInfo}</td>
                    <td>
                        <button type="button" class="btn btn-info" data-toggle="modal" 
                            data-target="#edit" onclick="editModal(${user.id})">
                            Edit
                        </button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" 
                            data-bs-target="#deleteModal" onclick="deleteModal(${user.id})">
                            Delete
                        </button>
                    </td>
                </tr>`);
                tbody.append(usersRow);
            });
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
        });
}

// Вызов функции после объявления
getTableUser();
