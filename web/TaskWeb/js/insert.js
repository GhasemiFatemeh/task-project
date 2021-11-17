function sendInformation() {
    let taskId = document.getElementById('taskId').value;
    let title = document.getElementById('taskTitle').value;
    let description = document.getElementById('taskDescription').value;
    let desc=new Description(description);
    let req = pageURL+'tasks/manageTasks/register?taskId=' + taskId + '&title=' + title + '&description=' + JSON.stringify(desc);
    let http;
    if (navigator.appName === "Microsoft Internet Explorer") {
        http = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        http = new XMLHttpRequest();
    }
    http.open("GET", req, true);
    http.send();
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

function getValidTextForSendRequest(text) {
   // let out = text.replace(/\n/g, "/n/r");
    return text;
}

function createEditor()
{
        ClassicEditor
        .create( document.querySelector( '#editor' ) )
        .catch( error => {
        console.error( error );
    } );

}