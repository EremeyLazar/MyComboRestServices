function getCurrentUser() {
    fetch("/apiUser/getCurrentUser")
        .then(res => {
            if (!res.ok) {
                throw new Error(`HTTP error! Status: ${res.status}`);
            }
            return res.json();
        })
        .then(user => {
            $('#UsernameCurrentUser').append(`<span>${user.username}</span>`);
            $('#roleCurrentUser').append(`<span>${user.roles.map(role => role.name.replace('ROLE_', '')).join(', ')}</span>`);

            if (user.game) {
                const u = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.yob}</td>
                            <td>${user.country}</td>
                            <td>${user.roles.map(role => role.name.replace('ROLE_', '')).join(', ')}</td>
                            <td>${user.game.totalRate}</td>
                            <td>${user.game.level}</td>
                            <td>${user.game.games}</td>
                        </tr>)`;
                $('#oneUser').append(u);
            } else {
                const u = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.yob}</td>
                            <td>${user.country}</td>
                            <td>${user.roles.map(role => role.name.replace('ROLE_', '')).join(', ')}</td>
                            <td>No Game</td>
                        </tr>)`;
                $('#oneUser').append(u);
            }
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
        });
}

getCurrentUser();
