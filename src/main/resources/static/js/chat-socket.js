let socket = null;
let stomp = null;

window.addEventListener("load", (event) => {
    connectServer();
    setEventListener();
});

window.addEventListener("close")

function setEventListener() {
    let connectChatRoomBtn = document.querySelector("#connectChatRoomBtn");
    connectChatRoomBtn.addEventListener("click", connectChatRoom);
    let messageInputBtn = document.querySelector("#messageInputBtn");
    messageInputBtn.addEventListener("click", sendMessage);
}

function connectServer() {
    socket = new SockJS("http://localhost:8080/connect");
    stomp = Stomp.over(socket);

    stomp.connect({}, (frame) => {
        console.log("연결");
    }, (error) => {
        console.log(error);
    })
}

function connectChatRoom() {
    console.log("채팅방 입장");
    // "/topic/chat" 채널을 구독함
    stomp.subscribe("/topic/chat", receiveMessage);
}

function sendMessage() {
    let nickname = document.querySelector("#nicknameInputBox").value;
    let message = document.querySelector("#messageInputBox").value;
    // 브로커에게 "/chat" 경로로 메시지를 보냄 -> 서버는 @MessageMapping("/chat")으로 맵핑된 함수로 메시지가 전달됨
    stomp.send("/broker/chat", {}, JSON.stringify({message: message, nickname: nickname}));
}

function receiveMessage(response) {
    let nickname = JSON.parse(response.body).nickname;
    let message = JSON.parse(response.body).message;
    let divElement = document.createElement("div");
    divElement.innerHTML = nickname + ": " + message;
    document.querySelector("#messageOutputBox").append(divElement);
}