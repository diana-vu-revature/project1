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
  
  getAllReimToTable();

  document.getElementById("profileButton").onclick = () => {
    getCurrentUserToForm();
  }
}

// uses fetch to get manager's reimbursements and sends to makeTable
function getUserReimToTable() {
  return fetch(window.location.origin + "/reimbursement")
    .then(response => response.json())
    .then(data => makeTable(data))
    .catch(error => console.log(error));
}

// uses fetch to get all reimbursements and sends to makeTable
function getAllReimToTable() {
  fetch(window.location.origin + "/reimbursement/all")
    .then(response => response.json())
    .then(data => makeTable(data))
    .catch(error => console.log(error));
}

// adds array data to reimbursement table
function makeTable(reimArray) {
  console.log(reimArray);

  const reimTable = document.getElementById("reimTable");
  if(reimArray != null) {
    if(reimArray.length > 0) {
      reimArray.forEach(reim => {
        let tr = document.createElement("tr");
          
        for(const prop in reim) {
          let td = document.createElement("td");
          td.innerHTML = reim[prop];
          tr.appendChild(td);
        }    
        reimTable.appendChild(tr);
      });
    }
  }
}

// fetch current user data and sends to createUser Form
function getCurrentUserToForm() {
  return fetch(window.location.origin + "/user")
    .then(response => response.json())
    .then(data => createUserForm(data))
    .catch(error => console.log(error));
}

// creates form for viewing/updating user profile from user object
function createUserForm(user) {
  const profileDiv = document.getElementById("profileDiv");
  let title = document.createElement("h2");
  title.innerHTML = "Profile";
  profileDiv.appendChild(title);

  let form = document.createElement("form");
  form.method = "post";
  form.action = "user";
  form.id = "userForm";

  for(const prop in user) {
    let label = document.createElement("label");
    label.for = prop;
    label.innerHTML = prop;

    let input = document.createElement("input");
    switch(prop) {
      case "id": 
        input.type = "number"; 
        input.readOnly = true; 
        break;
      case "roleId": 
        input.type = "number";
        input.readOnly = true;
        break;
      case "email": input.type = "email"; break;
      case "pword": input.type = "password"; break;
      default: input.type = "text";
    }
    input.name = prop;
    input.id = prop;
    input.value =  user[prop];

    form.appendChild(label);
    form.appendChild(input);
  }

  let submit = document.createElement("input");
  submit.type = "submit";
  submit.value = "Update";
  form.appendChild(submit);

  profileDiv.appendChild(form);
  let profileButton = document.getElementById("profileButton");
  profileButton.style.display = "none";
}