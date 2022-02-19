ClassicEditor
    .create( document.querySelector( '#taskDescription' ) )
    .then( newEditor => {
        editor = newEditor;
    } )
    .catch( error => {
        console.error( error );
    } );

function sendInformation() {
    let taskId = document.getElementById('taskId').value;
    let title = document.getElementById('taskTitle').value;
    let description = editor.getData();
    let req = pageURL+'tasks/manageTasks/register?taskId=' + taskId
        + '&title='
        + title;
    let http;
    if (navigator.appName === "Microsoft Internet Explorer") {
        http = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        http = new XMLHttpRequest();
    }

    http.open("POST", req, true);
    http.setRequestHeader("Content-type","text/plain; charset=utf-8");
    http.send(description);
    http.onreadystatechange = function () {
        if (http.readyState === 4 ) {
            if (http.status === 200) {
                http.responseText;
                showSuccessSnackbar();
            }
            else {
                showFailedSnackbar();
            }
        }
    }
}

function redirectTasksPage(){
    location.replace(pageURL+"TaskWeb/view/taskManagement.html");
}
function showSuccessSnackbar() {
    let x = document.getElementById("successSnackbar");
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}
function showFailedSnackbar(){
    let x = document.getElementById("failedSnackbar");
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

function showUpdateSnackbar(){
    let x= document.getElementById("updateSnackbar");
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

function showSuccessfullyUpdatedSnackbar(){
    let x= document.getElementById("successfullyUpdatedSnackbar");
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

function updateInfo(){
    let id = EntityManager.findOne("id");
    let taskId = document.getElementById("taskId").value;
    let title = document.getElementById("taskTitle").value;
    let description = editor.getData();
    let http;
    if (navigator.appName === "Microsoft Internet Explorer") {
        http = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        http = new XMLHttpRequest();
    }
    let req = pageURL+"tasks/manageTasks/update?id=" + id + "&taskId=" + taskId + "&title=" + title;
    http.open("POST", req , true);
    http.send(description);
   return  http.onreadystatechange = function () {
       return http.status === 200&&http.readyState === 4;
    }

}

function showSuccessfullyUpdateSnackbarConditionally(){
    if (updateInfo()){
        showSuccessfullyUpdatedSnackbar()
    }
    else {
        showFailedSnackbar();
    }
}



function checkRepeatedTaskId(){
    let taskId = document.getElementById('taskId').value;
    fetch(pageURL+"tasks/manageTasks/checkRepeatedTaskId?taskId="+taskId, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            showUpdateSnackbar();
            document.getElementById("taskTitle").value= data[0];
            editor.setData(data[1])
            document.getElementById("save-button").addEventListener("click", showSuccessfullyUpdateSnackbarConditionally);
        })
        .catch(error => {

        });
}



