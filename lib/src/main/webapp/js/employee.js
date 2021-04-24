/**
 * TODO: 
 * - use fetch to get employee's reimbursements
 * - create table with document.createElement(), .appendChild(), .getElementById(), etc
 *
 **/ 

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