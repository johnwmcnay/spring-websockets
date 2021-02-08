let webSocket = null;

const PRIMARY_BG = "bg-light";
const SECONDARY_BG = "";

function connect() {

    webSocket = new WebSocket("wss://vuybyli569.execute-api.us-east-1.amazonaws.com/test");

    webSocket.onmessage = function (event) {

        let messageOutput = event.data;

        if (messageOutput !== "") {

            let $chatMessages = $("#chat-messages div");
            let bg = PRIMARY_BG;

            if ($chatMessages.last().hasClass('bg-light')) {
                bg = SECONDARY_BG;
            }

            //TODO: limit message history to an arbitrary amount
            $("#chat-messages").append("<div class='p-1 msg " + bg + "'>" + messageOutput + "</div>");

            let $chat = $("#chat-room");
            $chat[0].scrollTop = $chat[0].scrollHeight - $chat[0].clientHeight;
        }
    }
}

function joinRoom() {

    let username = $("#username-input").val().trim();

    if (username === "") {
        return;
    }

    connect();

    $("#username-id").val(username);
    $("#lobby").removeClass("d-flex").addClass("d-none");
    $("#room").addClass("d-flex");

}

// function createRoom() {
//
//     let username = $("#username-input").val().trim();
//
//     $.ajax({
//         url: "/room/create/?username=" + username,
//         type: "POST",
//         success: function (room) {
//
//             connect(room);
//             $("#room-id").val(room);
//             $("#username-id").val(username);
//             $("#room-title").html("Room: " + room);
//             $("#lobby").removeClass("d-flex").addClass("d-none");
//             $("#room").addClass("d-flex");
//         }
//     });
// }

$(function() {
    $("#message").keydown(function(e){
        if (e.keyCode === 13) {
            let $message = $("#message");

            if ($message.val().trim() !== "") {
                sendMessage();
            }
        }
    });
});

function sendMessage() {

    let $message = $("#message");

    webSocket.send(JSON.stringify({"action":"sendmessage", "data":createMessage()}));

    $message.val("");
}

function createMessage() {
    let $message = $("#message")

    return $("#username-id").val() + ": " + $message.val()
}