function editUser(button) {
    document.getElementById('editUser').style.display = 'block';

    document.getElementById('userId').value = button.id;
    document.getElementById('userName').value = button.dataset.username;
    document.getElementById('userRole').value = button.dataset.userrole;
    document.getElementById('userBlockingStatus').value = button.dataset.userblockingstatus;
}