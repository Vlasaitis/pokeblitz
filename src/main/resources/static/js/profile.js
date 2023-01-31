const saleButtons = document.getElementsByClassName("sale-button");

for (let i = 0; i < saleButtons.length; i++) {
  saleButtons[i].addEventListener("click", function(event) {
    if (!confirm("Are you sure you want to sell this pokemon?")) {
      event.preventDefault();
    }
  });
}
