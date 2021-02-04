let stompClient = null;

function connect(room) {
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/messages/' + room, function (messageOutput) {

            //TODO: limit message history to an arbitrary amount
            $("#chat-messages").append("<div>" + messageOutput.body + "</div>");

            let $chat = $("#chat-room");
            $chat[0].scrollTop = $chat[0].scrollHeight - $chat[0].clientHeight;
        });
    });
}

function joinRoom() {

    let room = $("#code").val()
    let username = $("#username-input").val().trim();

    if (username === "") {
        return;
    }

    $.ajax({
        url: "/room/join/" + room + "/?username=" + username,
        type: "POST",
        success: function (exists) {

            if (exists) {
                connect(room);
                $("#room-id").val(room);
                $("#username-id").val(username);
                $("#room-title").html("Room: " + room);
                $("#lobby").hide();
                $("#room").show();
            } else {
                alert("Invalid Room Code");
            }
        }
    })
}

function createRoom() {

    let username = $("#username-input").val().trim();

    $.ajax({
        url: "/room/create/?username=" + username,
        type: "POST",
        success: function (room) {

            connect(room);
            $("#room-id").val(room);
            $("#username-id").val(username);
            $("#room-title").html("Room: " + room);
            $("#lobby").hide();
            $("#room").show();
        }
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
}


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
    let room = $("#room-id").val();
    let $message = $("#message");

    stompClient.send("/app/chat/" + room, {},
        JSON.stringify({"message": createMessage()})
    );

    $message.val("");
}

function createMessage() {
    let $message = $("#message")

    return $("#username-id").val() + ": " + $message.val()
}