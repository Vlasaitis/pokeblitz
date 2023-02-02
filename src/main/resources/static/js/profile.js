const saleButtons = document.getElementsByClassName("sale-button");
// array of starterIds
const starterIds = Array.from(document.getElementsByClassName("starterPokemon"), element => element.innerText);

for (let i = 0; i < saleButtons.length; i++) {
  if (starterIds.includes(saleButtons[i].name)) {
    saleButtons[i].style.display = "none";
    saleButtons[i].style.cursor = "not-allowed";
  }

  saleButtons[i].addEventListener("click", function(event) {
    if (!confirm("Are you sure you want to sell this pokemon?")) {
      event.preventDefault();
    }
  });
}

// tank select listener
window.onload = function() {
    document.getElementById("tankSelect").addEventListener("change", function() {
      var xhr = new XMLHttpRequest();
      console.log(this.value)
      xhr.open("POST", "/changeTank", true);
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      xhr.send("tankName=" + encodeURIComponent(this.value));
      location.reload();
    });
  };
