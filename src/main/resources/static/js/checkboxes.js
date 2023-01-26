function checkCheckboxes() {
  let checkedBoxes = 0;
  let submitButton = document.getElementById("submit");
          // counting the number of checked checkboxes
          document.querySelectorAll('input[type="checkbox"]').forEach(function(checkbox) {
      if (checkbox.checked) {
          checkedBoxes++;
      }
  });

  if (checkedBoxes === 3) {
      submitButton.disabled = false;
      // disabling the rest of the checkboxes
      document.querySelectorAll('input[type="checkbox"]').forEach(function(checkbox) {
          if (!checkbox.checked) {
              checkbox.disabled = true;
          }
      });
  } else {
      submitButton.disabled = true;
      // enabling the rest of the checkboxes
      document.querySelectorAll('input[type="checkbox"]').forEach(function(checkbox) {
checkbox.disabled = false;
});
}
}