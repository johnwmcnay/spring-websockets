let stompClient = null;

function connect(room) {
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/messages/' + room, function(messageOutput) {
            $("#chat-room").append("<div>" + messageOutput.body + "</div>")
        });
    });
}

function joinRoom() {

    let room = $("#code").val()

    //TODO: check if room is valid inside controller
    $.ajax({
        url: "/room/join/" + room,
        type: "POST",
        success: function(data) {
            connect(room);
            $("#room-id").val(room);
            $("#room-title").html("Room: " + room);
            $("#lobby").hide();
            $("#room").show();
        }
    })
}

function createRoom() {

    $.ajax({
        url: "/room/create",
        type: "POST",
        success: function (room) {
            console.log("create room works");
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