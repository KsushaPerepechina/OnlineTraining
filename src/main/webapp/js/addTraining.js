var addTraining = document.getElementById('addTraining');

window.onclick = function(event) {
    if (event.target === addTraining) {
        addTraining.style.display = "none";
    }
};

function addedTraining() {
    document.getElementById('addTraining').style.display='none';
    document.getElementById('addTrainingNotify').style.display='block';
}