async function openAndFillInTheModal(form, modal, id) {
    modal.show();
    let user = await getOneUser(id);
    form.id.value = user.id;
    form.username.value = user.username;
    form.yob.value = user.yob;
    form.country.value = user.country;
    form.roles.value = user.roles.join(',');
}