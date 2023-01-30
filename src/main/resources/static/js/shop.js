// Dynamic price calculation based on select box value
$(document).ready(function() {
    $("#common-pack").change(function() {
      $("#common-price").text($(this).val() * 100);
    });
    $("#uncommon-pack").change(function() {
      $("#uncommon-price").text($(this).val() * 200);
    });
    $("#rare-pack").change(function() {
      $("#rare-price").text($(this).val() * 400);
    });
    $("#epic-pack").change(function() {
      $("#epic-price").text($(this).val() * 600);
    });
  });