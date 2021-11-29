//Open Modals
function openDeleteModal() {
    closeUpdateModal();
    document.getElementById('delete-modal').style.display = 'block';
}

//update task when update button is clicked
function openUpdateModal() {
    document.getElementById('update-modal').style.display = 'block';
    let taskId = EntityManager.findOne("taskId");
    let title = EntityManager.findOne("title");
    let description = EntityManager.findOne("description");
    document.getElementById("taskId").value = taskId;
    document.getElementById("taskTitle").value = title;
    editor.setData(description);

}


//delete task when delete button is clicked
function deleteTask() {
    let id = EntityManager.findOne("id");
    Request.send("GET", pageURL+"tasks/manageTasks/delete?id=" + id);
    closeDeleteModal();
    location.reload();

}

function updateTask() {
    let id = EntityManager.findOne("id");
    let taskId = document.getElementById("taskId").value;
    let title = document.getElementById("taskTitle").value;
    let description = editor.getData();
   // Request.send("GET", pageURL+"tasks/manageTasks/update?id=" + id + "&taskId=" + taskId + "&title=" + title + "&description=" + description);
    let req = pageURL+"tasks/manageTasks/update?id=" + id + "&taskId=" + taskId + "&title=" + title;
    Request.sendPlain(req,description);
    closeUpdateModal();
    location.reload();
}

function selectTask(){
 Request.send("Get", pageURL+"tasks/manageTasks/")
}


//close modals
function closeDeleteModal() {
    document.getElementById('delete-modal').style.display = 'none';
}

function closeUpdateModal() {
    document.getElementById('update-modal').style.display = 'none';
    fillTable();
}

//Close Modals with esc
window.addEventListener('keydown', function (event) {
    if (event.key === 'Escape') {
        closeDeleteModal();
        closeUpdateModal();
    }
})


//Request
function fillTable() {
    let http;
    if (navigator.appName === "Microsoft Internet Explorer") {
        http = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        http = new XMLHttpRequest();
    }
    http.open("GET", pageURL+"tasks/manageTasks/findAll", true);
    http.send();
    http.onreadystatechange = function () {
        if (http.readyState === 4) {
            UIManager.fillSelectableTable(["taskId", "title", "description"], http.responseText, 'tableRow', 'onclick', 'onClickCallBack');
        }
    }
}

function onClickCallBack(s) {
    EntityManager.persist("id",EntityManager.findOne(s+"id"));
    EntityManager.persist("taskId",EntityManager.findOne(s+"taskId"));
    EntityManager.persist("title",EntityManager.findOne(s+"title"));
    EntityManager.persist("description",EntityManager.findOne(s+"description"));
    openUpdateModal();
}

