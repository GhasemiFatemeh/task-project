ClassicEditor
    .create( document.querySelector( '#taskDescription' ) )
    .then( newEditor => {
        editor = newEditor;
    } )
    .catch( error => {
        console.error( error );
    } );

function checkRepeatedTaskId(){
    let taskId = document.getElementById('taskId').value;
    fetch(pageURL+"tasks/manageTasks/checkRepeatedTaskId?taskId="+taskId, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            showUpdateSnackbar();
            if(data!==null){
                document.getElementById("taskTitle").value= data[0];
                editor.setData(data[1]);
                document.getElementById("send-info-button").innerText="update";
                status=true;
            }

        })
        .catch(error => {
            console.error( error );
        });
}


var status= false;
function sendInformation() {
    let id = EntityManager.findOne("id");
    let taskId = document.getElementById('taskId').value;
    let title = document.getElementById('taskTitle').value;
    let description = editor.getData();
    let insertReq = pageURL+'tasks/manageTasks/register?taskId=' + taskId
        + '&title='
        + title;
    let updateReq= pageURL+"tasks/manageTasks/update?id=" + id + "&taskId=" + taskId + "&title=" + title;
    let http;
    if (navigator.appName === "Microsoft Internet Explorer") {
        http = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        http = new XMLHttpRequest();
    }
    if (status){
        http.open("POST", updateReq, true);
        http.setRequestHeader("Content-type","text/plain; charset=utf-8");
        http.send(description);
        http.onreadystatechange = function () {
            if (http.readyState === 4 ) {
                if (http.status === 200) {
                    http.responseText;
                    showSuccessfullyUpdatedSnackbar();
                }
                else {
                    showFailedSnackbar();
                }
            }
        }
    }
    else {
        http.open("POST", insertReq, true);
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






