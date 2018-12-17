var ws;

init = function () {
    ws = new WebSocket("ws://localhost:8080/user");
    ws.onopen = function (event) {

    }
    ws.onmessage = function (event) {
        var textarea = document.getElementById("loadUserName");
        textarea.value = event.data;

    }
    ws.onclose = function (event) {

    }
};

function sendMessageSaveUser() {
    var userNameField = document.getElementById("userName");
    var message = {"command" : "saveUser", "userNAme" : userNameField.value};
    ws.send(JSON.stringify(message));

}
//
// function sendMessageLoadUser() {
//     var messageField = document.getElementById("message");
//     var userNameField = document.getElementById("username");
//     var message = userNameField.value + ":" + messageField.value;
//     ws.send(message);
//     messageField.value = '';
// }
