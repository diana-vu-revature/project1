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

  getUserReimToTable();

  document.getElementById("profileButton").onclick = () => {
    getCurrentUserToForm();
  };

  document.getElementById("createReimButton").onclick = () => {
    createReimForm();
  };
}

// uses fetch to get employee's reimbursements and sends to makeTable
function getUserReimToTable() {
  fetch(window.location.origin + "/reimbursement")
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

        let reimRowCount = reimTable.rows.length;
		  	let tr = reimTable.insertRow(reimRowCount);
        
        let column = 0;
        for(const prop in reim) {
          let td = tr.insertCell(column);
          td.innerHTML = reim[prop];

          switch(prop) {
            case "resolved":
              if(reim[prop] == true) {
                td.innerHTML = "Resolved";
              } else {
                td.innerHTML = "Pending";
              }
              break;
            case "approved":
              if(reim.resolved == false) {
                td.innerHTML = "Pending";
              } else if(reim[prop] == true) {
                td.innerHTML = "Approved";
              } else {
                td.innerHTML = "Denied";
              }
              break;
          }
          column++;
        }    
      });

      changeManIdToName();
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

// creates form for add user profile from a reimbursement object
function createReimForm() {
  const reimbursementDiv = document.getElementById("reimbursementDiv");
  let title = document.createElement("h2");
  title.innerHTML = "Reimbursement Request";
  reimbursementDiv.appendChild(title);

  let reimForm = document.createElement("form");
  reimForm.method = "post";
  reimForm.action = "reimbursement";
  reimForm.id = "reimForm";
  reimbursementDiv.appendChild(reimForm);
  
  let idInput = document.createElement("input");
  idInput.type = "hidden";
  idInput.name = "id";
  idInput.value = "0";
  reimForm.appendChild(idInput);

  let nameLabel = document.createElement("label");
  nameLabel.innerHTML = "Name: ";
  nameLabel.for = "name";
  reimForm.appendChild(nameLabel);
  
  let nameInput = document.createElement("input");
  nameInput.type = "text";
  nameInput.name = "name";
  nameInput.id = "name";
  nameInput.required = true;
  reimForm.appendChild(nameInput);

  let priceLabel = document.createElement("label");
  priceLabel.innerHTML = "Amount: $";
  priceLabel.for = "price";
  reimForm.appendChild(priceLabel);

  let priceInput = document.createElement("input");
  priceInput.type = "number";
  priceInput.name = "price";
  priceInput.id = "price";
  priceInput.required = true;
  priceInput.step = "0.01";
  priceInput.value = "0.01";
  priceInput.min = "0.01";
  reimForm.appendChild(priceInput);
  
  let submit = document.createElement("input");
  submit.type = "submit";
  submit.value = "Submit Request";
  reimForm.appendChild(submit);

  let reimButton = document.getElementById("createReimButton");
  reimButton.style.display = "none";
}

function changeManIdToName() {
  fetch(window.location.origin + "/managers")
    .then(response => response.json())
    .then(data => updateManIdToName(data))
    .catch(error => console.log(error));
}

function updateManIdToName(managers) {
  const reimTable = document.getElementById("reimTable");
  if(reimTable.rows.length > 1) {
    managers.forEach(m => {
      for (var i = reimTable.rows.length - 1; i > 0; --i) {
        let cell = reimTable.rows[i].cells[4];
        let id = cell.innerHTML;
        if(id == m.id) {
          cell.innerHTML = String(
            m.fname + " " +
            m.surname + " " +
            "(" + m.id + ")"
          );
        } else if(id == 0 || id == null || id == "") {
          cell.innerHTML = "Pending";
        }
      }
    });
  }
}