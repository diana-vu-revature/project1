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

  document.getElementById("employeesButton").onclick = () => {
    getEmployeesToTable();
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

// uses fetch to get employees and sends to makeTable
function getEmployeesToTable() {
  return fetch(window.location.origin + "/employees")
    .then(response => response.json())
    .then(data => makeEmployeeTable(data))
    .catch(error => console.log(error));
}

// adds array data to employee table
function makeEmployeeTable(employeeArray) {
  let eDiv = document.getElementById("employeesDiv");

  if(employeeArray != null) {
    let employeeTable = document.createElement("table");
    employeeTable.id = "employeeTable";
    eDiv.appendChild(employeeTable);
    
    let headerRow = document.createElement("tr");
    employeeTable.appendChild(headerRow);

    let id = document.createElement("th");
    id.innerHTML = "ID"
    headerRow.appendChild(id);

    let fNameTh = document.createElement("th");
    fNameTh.innerHTML = "First Name"
    headerRow.appendChild(fNameTh);

    let sNameTh = document.createElement("th");
    sNameTh.innerHTML = "Last Name"
    headerRow.appendChild(sNameTh);

    let emailTh = document.createElement("th");
    emailTh.innerHTML = "Email";
    headerRow.appendChild(emailTh);

    if(employeeArray.length > 0) {
      employeeArray.forEach(employee => {
        let tr = document.createElement("tr");
        employeeTable.appendChild(tr);

        let idTd = document.createElement("td");
        idTd.innerHTML = employee.id;
        tr.appendChild(idTd);

        let fNameTd = document.createElement("td");
        fNameTd.innerHTML = employee.fname;
        tr.appendChild(fNameTd);

        let surnameTd = document.createElement("td");
        surnameTd.innerHTML = employee.surname;
        tr.appendChild(surnameTd);

        let emailTd = document.createElement("td");
        emailTd.innerHTML = employee.email;
        tr.appendChild(emailTd);

        employeeTable.appendChild(tr);
      });
    }

    let employeesButton = document.getElementById("employeesButton");
    employeesButton.style.display = "none";
  } else {
    if(document.getElementById("emptyEmployeesAlert") == null) {
      let noneMsg = document.createElement("p");
      noneMsg.id = "emptyEmployeesAlert";
      noneMsg.innerHTML = "No Employees";
      eDiv.appendChild(noneMsg);
    }
  }


}