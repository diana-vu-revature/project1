
// TODO: 
// - use fetch to get manager's reimbursements
// - create table with document.createElement(), .appendChild(), .getElementById(), etc
// - https://www.w3schools.com/howto/howto_js_toggle_hide_show.asp
document.body.onload = () => {
  const welcome = document.getElementById("welcome");
  let hello = document.getElementById("hello");
  if(hello != null) {
    hello.style.color = "green";
    welcome.appendChild(hello);
  }

  let alert = document.getElementById("alert");
  if(alert != null) {
    alert.style.color = "red";
    welcome.appendChild(alert);
  }
}

// TODO
function getUserReim() {
  fetch(window.location.origin + "/reimbursement");
}

// TODO
function getAllReim() {
  fetch(window.location.origin + "/reimbursement/all");
}

// TODO
function makeTable() {

}