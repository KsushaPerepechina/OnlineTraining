var addAssignment = document.getElementById('addAssignment');

window.onclick = function(event) {
    if (event.target === addAssignment) {
        addAssignment.style.display = "none";
    }
};

function addingAssignment() {
    document.getElementById('addAssignment').style.display='none';
    document.getElementById('addAssignmentNotify').style.display='block';
}