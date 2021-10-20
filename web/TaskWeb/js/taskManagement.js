//Open Modals
function openChooseModal() {
    document.getElementById('choose-modal').style.display = 'block';
}

function openDeleteModal() {
    closeChooseModal();
    document.getElementById('delete-modal').style.display = 'block';

}

function openUpdateModal(taskId, title, description) {
    closeChooseModal()
    document.getElementById('update-modal').style.display = 'block';
    document.getElementById('taskId').value = taskId;
    document.getElementById('taskTitle').value = title;
    document.getElementById('taskDescription').value = description;
}

//delete task when delete button is clicked
function deleteTask (){
    let id=Request.getCookie("id");
    Request.send("GET","http://localhost:8081/tasks/manageTasks/delete?id="+id);
    closeDeleteModal();
    fillTable();
}

//update task when update button is clicked
function updateTask(){
    let id=Request.getCookie("id");
    let taskId=Request.getCookie("taskId");
    let title=Request.getCookie("title");
    let description=Request.getCookie("description");
    Request.send("GET",'http://localhost:8081/tasks/manageTasks/update?id='+id+ 'taskId=' + taskId + '&title=' + title + '&description=' + description);
    closeUpdateModal();
    fillTable();
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
    fillTable();
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
       fillTable();
    }
}
function fillTable(){
    UIManager.fillSelectableTable(["taskId", "title", "description"], http.responseText, 'tableRow', 'onclick', 'onClickCallBack');
}
function onClickCallBack(s) {
    Response.addCookie("id",s['id']);
    openChooseModal();
}

