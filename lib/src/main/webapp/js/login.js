function moveAlert(){
  let alert = document.getElementById('alert');
  if(alert != null) {
    alert.style.color = "red";
    let loginForm = document.getElementById('loginForm');
    document.body.insertBefore(alert, loginForm);
  }

  let logoutAlert = document.getElementById('logoutAlert');
  if(logoutAlert != null) {
    logoutAlert.style.color = "green";
    let loginForm = document.getElementById('loginForm');
    document.body.insertBefore(logoutAlert, loginForm);
  }
}