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

function createRoom() {

    $.ajax({
        url: "/room/create",
        type: "GET",
        success: function (room) {
            console.log("create room works");
            connect(room);
            $("#room-id").val(room);
            $("#room-title").html("Room: " + room);
            $("#lobby").hide();
            $("#room").show()
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