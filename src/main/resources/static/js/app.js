let stompClient = null;

function connect(room) {
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function() {
        stompClient.subscribe('/topic/messages/' + room, function(messageOutput) {
            $("#chat-room").append("<div>" + messageOutput.body + "</div>")
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
        success: function(exists) {

            if (exists) {
                connect(room);
                $("#room-id").val(room);
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
            $("#room-title").html("Room: " + room);
            $("#lobby").hide();
            $("#room").show();
        }
    });
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
}

function sendMessage() {
    let room = $("#room-id").val()
    let $message = $("#message");
    stompClient.send("/app/chat/" + room, {},
        JSON.stringify({ "message" : $message.val()}));
    $message.val("");
}