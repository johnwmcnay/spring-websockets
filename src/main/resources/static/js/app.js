let stompClient = null;

function connect(room) {
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/messages/' + room, function(messageOutput) {
            $("#chat").append("<p>" + messageOutput.body + "</p>")
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
            $("#lobby").hide();
            $("#room").html("Room: " + room);
            $("#chat-room").show();
        }
    });
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
}

function sendMessage() {
    stompClient.send("/app/chat", {},
        JSON.stringify({ "message" : $("#message").val()}));
}
