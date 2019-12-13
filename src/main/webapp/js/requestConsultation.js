var requestConsultation = document.getElementById('requestConsultation');

window.onclick = function(event) {
    if (event.target === requestConsultation) {
        requestConsultation.style.display = "none";
    }
};

function requestConsultation() {
    document.getElementById('requestConsultation').style.display='none';
    document.getElementById('requestConsultationNotify').style.display='block';
}