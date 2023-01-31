const splash = document.querySelector('.splash');
var logs = document.getElementsByClassName("logEntry");
var index = 0;
var backToProfileBtn = document.getElementById("profileButton");
backToProfileBtn.setAttribute("disabled", true);
var allRevealed = false;
const attackerName = document.getElementById("attackerName").innerText;
const defenderName = document.getElementById("defenderName").innerText;


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
  color(logs);
  if (index < logs.length) {
    logs[index].style.display = "block";
    index++;
    if (!allRevealed) {
      scrollToBottom();
      setTimeout(reveal, 1000);
    }
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
  color(logs);
  backToProfileBtn.removeAttribute("disabled");
  allRevealed = true;
  scrollToBottom();
}

function scrollToBottom() {
    var logContainer = document.getElementsByClassName("log-container")[0];
    logContainer.scrollTop = logContainer.scrollHeight;
}

function color(elements) {
  console.log(elements.length)
  for (var i = 0; i < elements.length; i++) {
    console.log(elements[i].textContent)

    if (elements[i].textContent.includes("FAINTED")) {
      elements[i].style.color = "#009fff"
    } else if (elements[i].textContent.includes(attackerName)) {
      elements[i].style.color = "green";
    } else if (elements[i].textContent.includes(defenderName)) {
      elements[i].style.color = "#d70808"
    }

  }
}

