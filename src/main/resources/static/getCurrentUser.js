function getCurrentUser() {
    fetch("/apiUser/getCurrentUser")
        .then(res => res.json())
        .then(user => {
            $('#UsernameCurrentUser').append(`<span>${user.username}</span>`)
            $('#roleCurrentUser').append(`<span>${user.roles.map(role => role.name.replace('ROLE_', '')).join(', ')}</span>`);
            const u = `$(
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.yob}</td>
                        <td>${user.country}</td>
                        <td>${user.roles.map(role => role.name.replace('ROLE_', '')).join(', ')}</td>
                    </tr>)`;
            $('#oneUser').append(u)
        })
}

getCurrentUser()