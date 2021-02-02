var stompClient = null;

connect();

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            $("#chat").append("<p>" + messageOutput.body + "</p>")
        });
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
