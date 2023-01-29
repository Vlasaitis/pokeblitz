const splash = document.querySelector('.splash');
var logs = document.getElementsByClassName("logEntry");
var index = 0;
var backToProfileBtn = document.getElementById("profileButton");
backToProfileBtn.setAttribute("disabled", true);

document.addEventListener('DOMContentLoaded', (e)=>{
    if(!splash){
        console.log("splash element not found")
    }
    setTimeout(()=> {
        splash.classList.add('display-none');
    }, 2000);
})


function reveal() {
  var startButton = document.getElementById("startButton");
  startButton.setAttribute("disabled", true);
  if (index < logs.length) {
    logs[index].style.display = "block";
    index++;
    scrollToBottom();
    setTimeout(reveal, 1000);
  }
  if (index == logs.length) {
    backToProfileBtn.removeAttribute("disabled");
  }
}

function skip() {
    var button = document.getElementById("skipButton");
  button.setAttribute("disabled", true);
  for (var i = 0; i < logs.length; i++) {
    logs[i].style.display = "block";
  }
  backToProfileBtn.removeAttribute("disabled");
}

function scrollToBottom() {
    var logContainer = document.getElementsByClassName("log-container")[0];
    logContainer.scrollTop = logContainer.scrollHeight;
}
