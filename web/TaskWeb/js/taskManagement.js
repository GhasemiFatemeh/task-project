//Open Modals
function openChooseModal() {
    document.getElementById('choose-modal').style.display = 'block';
}

function openDeleteModal() {
    closeChooseModal()
    document.getElementById('delete-modal').style.display = 'block';
}

function openUpdateModal(taskId, title, description) {
    closeChooseModal()
    document.getElementById('update-modal').style.display = 'block';
    document.getElementById('taskId').value = taskId;
    document.getElementById('taskTitle').value = title;
    document.getElementById('taskDescription').value = description;
}

//close modals
function closeChooseModal() {
    document.getElementById('choose-modal').style.display = 'none';
}

function closeDeleteModal() {
    document.getElementById('delete-modal').style.display = 'none';
}

function closeUpdateModal() {
    document.getElementById('update-modal').style.display = 'none';
}

//Close Modals with clicking anywhere on the page
window.onclick = function (event) {
    if ((event.target === document.getElementById('delete-modal')) || event.target === document.getElementById('update-modal') || event.target === document.getElementById('choose-modal')) {
        closeChooseModal()
        closeDeleteModal();
        closeUpdateModal();
    }
}

//Close Modals with esc
window.addEventListener('keydown', function (event) {
    if (event.key === 'Escape') {
        closeChooseModal()
        closeDeleteModal();
        closeUpdateModal();
    }
})


//Requests

let json;
let http;
if (navigator.appName === "Microsoft Internet Explorer") {
    http = new ActiveXObject("Microsoft.XMLHTTP");
} else {
    http = new XMLHttpRequest();
}
http.open("GET", "http://localhost:8081/tasks/manageTasks/findAll", true);
http.send();
http.onreadystatechange = function () {

    if (http.readyState === 4) {
        UIManager.fillSelectableTable(["taskId", "title", "description"], http.responseText, 'tableRow', 'onclick', 'onClickCallBack');
    }
}

function onClickCallBack(s) {
    openChooseModal();
}

