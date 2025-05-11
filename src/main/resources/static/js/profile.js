// Функция для отображения соответствующего раздела
function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => section.style.display = 'none');
    document.getElementById(sectionId).style.display = 'block';
}

function setActiveLink(link) {
    const links = document.querySelectorAll('.sidebar a');
    links.forEach(link => link.classList.remove('active')); // Убираем класс active со всех ссылок
    link.classList.add('active'); // Добавляем класс active на текущую ссылку
    localStorage.setItem('activeLink', link.textContent); // Сохраняем активную ссылку в localStorage
}

function restoreActiveLink() {
    const activeLinkText = localStorage.getItem('activeLink');
    if (activeLinkText) {
    const links = document.querySelectorAll('.sidebar a');
    links.forEach(link => {
    if (link.textContent === activeLinkText) {
    link.classList.add('active');
    showSection(link.getAttribute('onclick').split("'")[1]); // Открываем соответствующий раздел
}
});
}
}
function toggleRoleForms() {
    const role = document.getElementById('role').value;
    const zawodnikFields = document.getElementById('zawodnikFields');
    const trenerFields = document.getElementById('trenerFields');

    if (role === "ZAWODNIK") {
        zawodnikFields.style.display = 'block';
        trenerFields.style.display = 'none';
    } else if (role === "TRENER") {
        trenerFields.style.display = 'block';
        zawodnikFields.style.display = 'none';
    } else {
        zawodnikFields.style.display = 'none';
        trenerFields.style.display = 'none';
    }
}


function toggleAdresFields() {
    const adresFields = document.getElementById("adresFields");
    adresFields.style.display = adresFields.style.display === "none" ? "block" : "none";
}



(function () { 'use strict'
    var forms = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(forms)
    .forEach(function (form) {
    form.addEventListener('submit', function (event) {
    if (!form.checkValidity()) {
    event.preventDefault()
    event.stopPropagation()
}
    form.classList.add('was-validated')
}, false)
})
})()


// Show the error message if a pending request exists
function showErrorMessage() {
    const errorMessageContainer = document.getElementById("error-message-container");
    errorMessageContainer.style.display = "block";
}

document.addEventListener('DOMContentLoaded', restoreActiveLink);
