'use strict';


function getCurrentUser() {
    fetch("api/admin")
        .then(res => res.json())
        .then(user => {
            const roles = user.roles.map(role => role.role).join(',')
            $('#username').append(`<span>${user.username}</span>`)
            $('#roleCurrentUser').append(`<span>${roles.replace('ROLE_', '') + ' '}</span>`)
            const u = `$(
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.yob}</td>
                        <td>${user.country}</td>
                        <td>${roles.replace('ROLE_', '') + ' '}</td>
                    </tr>)`;
            $('#oneUser').append(u)
        })
}

getCurrentUser()