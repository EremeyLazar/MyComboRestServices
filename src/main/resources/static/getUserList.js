const tbody = $('#AllUsers');
getTableUser();

function getTableUser() {
    tbody.empty();
    fetch("apiAuth/users")
        .then(res => res.json())
        .then(js => {
            console.log(js);
            js.forEach(user => {
                const roles = user.roles.map(role => role.name.replace('ROLE_', '')).join(', ');
                const users = $(`<tr>
                        <td class="pt-3" id="userID">${user.id}</td>
                        <td class="pt-3" >${user.username}</td>
                        <td class="pt-3" >${user.yob}</td>
                        <td class="pt-3" >${user.country}</td>
                        <td class="pt-3" >${roles}</td>
                        <td>
                                        <form th:action="@{/admin/update}" method="get" target="_blank">
                                                <input type="hidden" name="id" th:value="${user.id}"/>
                                                <button type="submit" class="btn btn-primary">Edit
                                                </button>
                                            </form>
                                        </td>
                                        <td>
                                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="deleteModal(${user.id})">
        Delete
    </button>
                        </td>
                    </tr>`);
                tbody.append(users);
            });
        })
}

//data-target="#delete"