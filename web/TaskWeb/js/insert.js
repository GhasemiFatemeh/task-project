function sendInformation() {
    let taskId = document.getElementById('taskId').value;
    let title = document.getElementById('taskTitle').value;
    let data = editor.getData();
    let result = data.replaceAll('&nbsp;', ' ');
    editor.setData(result);
    let description = editor.getData();
    let req = pageURL+'tasks/manageTasks/register?taskId=' + taskId
        + '&title='
        + title
        + '&description='
        + description;
    let http;
    if (navigator.appName === "Microsoft Internet Explorer") {
        http = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        http = new XMLHttpRequest();
    }
    http.open("POST", req, true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
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


editor.editing.view.document.on( 'enter', ( evt, data ) => {
    if ( data.isSoft ) {
        editor.execute( 'enter' );
    } else {
        editor.execute( 'shiftEnter' );
    }

    data.preventDefault();
    evt.stop();
    editor.editing.view.scrollToTheSelection();
}, { priority: 'high' } );
editor.scrollIntoView();
