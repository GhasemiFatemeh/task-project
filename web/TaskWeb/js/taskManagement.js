//kind of import CKeditor
let editor;
ClassicEditor
    .create(document.querySelector('#taskDescription'))
    .then(newEditor => {
        editor = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

//Open Modals
function openDeleteModal() {
    closeUpdateModal();
    document.getElementById('delete-modal').style.display = 'block';
}

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

//update task when update button is clicked
function updateTask() {
    let id = EntityManager.findOne("id");
    let taskId = document.getElementById("taskId").value;
    let title = document.getElementById("taskTitle").value;
    let description = editor.getData();
    let req = pageURL+"tasks/manageTasks/update?id=" + id + "&taskId=" + taskId + "&title=" + title;
    Request.sendPlain(req,description);
    closeUpdateModal();
    location.reload();
}

//search tasks
function findTasks(){
    let input= document.getElementById("searchbar").value;
    let req= pageURL+"tasks/manageTasks/findTasks?input=" + input;
    let http;
    if (navigator.appName === "Microsoft Internet Explorer") {
        http = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        http = new XMLHttpRequest();
    }
    http.open("GET", req, true);
    http.send();
    http.onreadystatechange = function () {
        if (http.readyState === 4) {
            UIManager.fillSelectableTable(["taskId", "title", "description"], http.responseText, 'tableRow', 'onclick', 'onClickCallBack');
        }
    }
}

//click search icon by enter
document.getElementById("searchbar").addEventListener("keyup", function(event) {
    if (event.key=== 'Enter') {
        event.preventDefault();
        document.getElementById("searchBtn").click();
    }
});

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


//fill table
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

