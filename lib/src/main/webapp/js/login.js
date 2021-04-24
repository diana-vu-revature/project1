document.body.onload = () => {
  const welcome = document.getElementById('welcome');

  let alert = document.getElementById('alert');
  if(alert != null) {
    alert.style.color = "red";
    welcome.appendChild(alert);
  }

  let logoutAlert = document.getElementById('logoutAlert');
  if(logoutAlert != null) {
    logoutAlert.style.color = "green";
    welcome.appendChild(logoutAlert);
  }
}