let stompClient = null;

function setConnected(connected) {
	document.querySelector("#connect").disabled = connected;
	document.querySelector("#disconnect").disabled = !connected;
	if (connected) {
		toggleVisibility(document.querySelectorAll("#conversation"), true);
	} else {
		toggleVisibility(document.querySelectorAll("#conversation"), false);
	}

	document.querySelector("#greetings").innerHTML = "";
}

function connect() {
	const socket = new SockJS("/gs-guide-websocket");
	stompClient = Stomp.over(socket);
	stompClient.connect(
		{},
		frame => {
			setConnected(true);
			console.log(`Connected: ${frame}`);
			stompClient.subscribe(
				"/topic/greetings",
					greeting => {
					showGreeting(JSON.parse(greeting.body).content);
				}
			);
		}
	);
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}

	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	stompClient.send("/app/hello", {}, JSON.stringify({ "name": document.querySelector("#name").value }));
}

function showGreeting(message) {
	const element = document.querySelector("#greetings");
	element.innerHTML += `<tr><td>${message}</td></tr>`;
}

/**
 * @param {NodeList} elements
 * @param {boolean} toShow
 */
function toggleVisibility(elements, toShow) {
	elements.forEach(element => {
		let display;
		if (toShow) {
			display = "table";
		} else {
			display = "none";
		}

		element.style.display = display;
	});
}

window.addEventListener(
	"DOMContentLoaded",
	() => {
		document.querySelector("#connect").onclick = () => connect();
		document.querySelector("#disconnect").onclick = () => disconnect();
		document.querySelector("#send").onclick = () => sendName();
	}
);
