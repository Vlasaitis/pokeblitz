var logs = document.getElementsByClassName("logEntry");
var index = 0;

function reveal() {
  var button = document.getElementById("startButton");
  button.setAttribute("disabled", true);
  if (index < logs.length) {
    logs[index].style.display = "block";
    index++;
    scrollToBottom();
    setTimeout(reveal, 1000);
  }
}

function skip() {
    var button = document.getElementById("skipButton");
  button.setAttribute("disabled", true);
  for (var i = 0; i < logs.length; i++) {
    logs[i].style.display = "block";
  }
}

function scrollToBottom() {
    var logContainer = document.getElementsByClassName("log-container")[0];
    logContainer.scrollTop = logContainer.scrollHeight;
}


//reveal();



