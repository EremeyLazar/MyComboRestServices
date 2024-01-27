function getCurrentUser() {
    fetch("/apiAuth/getCurrentUser")
        .then(res => res.json())
        .then(user => {
            const roles = user.roles.map(role => role.role).join(',')
            $('#UsernameCurrentUser').append(`<span>${user.username}</span>`)
            $('#roleCurrentUser').append(`<span>${user.roles.map(role => role.name.replace('ROLE_', '')).join(', ')}</span>`);
            console.log(user.username)
            console.log(user.roles)
            console.log(roles)

            const u = `$(
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.country}</td>
                        <td>${roles.replace('ROLE_', '') + ' '}</td>
                    </tr>)`;
            $('#oneUser').append(u)
        })
}

getCurrentUser()