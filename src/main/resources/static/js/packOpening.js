
var submitButtons = document.getElementsByClassName("open-button");


for (var i = 0; i < submitButtons.length; i++) {
submitButtons[i].addEventListener("click", function(event) {
  event.preventDefault();
  this.setAttribute("disabled", "disabled");
  this.form.submit();
});
}

