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
  getAllPendingReimToTable();

  document.getElementById("profileButton").onclick = () => {
    getCurrentUserToForm();
  }

  document.getElementById("employeesButton").onclick = () => {
    getEmployeesToTable();
  }
}

// uses fetch to get all reimbursements and sends to makeTable
function getAllPendingReimToTable() {
  fetch(window.location.origin + "/reimbursement/all")
    .then(response => response.json())
    .then(data => {
      let pendingReims = data.filter(reim => reim.resolved == false);
      makePendingTable(pendingReims);
    })
    .catch(error => console.log(error));
}

// adds array data to reimbursement table
function makePendingTable(pendingArray) {
  const pendingTable = document.getElementById("pendingReimTable");
  const pendingDiv = document.getElementById("pendingDiv");
  pendingDiv.appendChild(pendingTable);

  if(pendingArray != null) {
    if(pendingArray.length > 0) {
      pendingArray.forEach(pendingReim => {

        let rowCount = pendingTable.rows.length;
		  	let pendingTr = pendingTable.insertRow(rowCount);
        pendingTr.id = "pReim" + String(pendingReim.id);

        let pIdTd = pendingTr.insertCell(0);
        pIdTd.innerHTML = pendingReim.id;

        let pNameTd = pendingTr.insertCell(1);
        pNameTd.innerHTML = pendingReim.name;

        let pPriceTd = pendingTr.insertCell(2);
        pPriceTd.innerHTML = pendingReim.price;

        let pEmpIdTd = pendingTr.insertCell(3);
        pEmpIdTd.innerHTML = pendingReim.employeeId;

        let approveTd = pendingTr.insertCell(4);
        let approveButton = document.createElement("button");
        approveButton.class = "btn btn-success";
        approveButton.innerHTML = "Approve";
        approveButton.onclick = () => {approveReim(String(pendingReim.id))};
        approveTd.appendChild(approveButton);

        let denyTd = pendingTr.insertCell(5);
        let denyButton = document.createElement("button");
        denyButton.class = "btn btn-danger";
        denyButton.innerHTML = "Deny";
        denyButton.onclick = () => {denyReim(String(pendingReim.id))};
        denyTd.appendChild(denyButton);

      });
      changeEmpIdToName();
      changeManIdToName();
    }
  }
}

function approveReim(id) {
  fetch(window.location.origin + "/reimbursement?approve=" + id);

  document.getElementById("pReim"+id).remove();

  refreshReimTable();
}

function denyReim(id) {
  fetch(window.location.origin + "/reimbursement?deny=" + id);
  
  document.getElementById("pReim"+id).remove();

  refreshReimTable();
}

function refreshReimTable() {
  let reimRows = document.getElementById("reimTable").getElementsByTagName('tr');
  for (var i = reimRows.length - 1; i > 0; --i) {
    reimRows[i].remove();
  }
  getAllReimToTable();
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
      changeEmpIdToName();
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

function changeEmpIdToName() {
  fetch(window.location.origin + "/employees")
    .then(response => response.json())
    .then(data => updateEmpIdToName(data))
    .catch(error => console.log(error));
}

function updateEmpIdToName(employees) {
  const reimTable = document.getElementById("reimTable");
  if(reimTable.rows.length > 1) {
    employees.forEach(e => {
      for (var i = reimTable.rows.length - 1; i > 0; --i) {
        let cell = reimTable.rows[i].cells[3];
        let id = cell.innerHTML;
        if(id == e.id) {
          cell.innerHTML = String(
            e.fname + " " +
            e.surname + " " +
            "(" + e.id + ")"
          );
        } else if(id == 0 || id == null || id == "") {
          cell.innerHTML = "Pending";
        }
      }
    });
  }

  const pendingTable = document.getElementById("pendingReimTable");
  if(pendingTable.rows.length > 1) {
    employees.forEach(e => {
      for (var i = pendingTable.rows.length - 1; i > 0; --i) {
        let cell = pendingTable.rows[i].cells[3];
        let id = cell.innerHTML;
        if(id == e.id) {
          cell.innerHTML = String(
            e.fname + " " +
            e.surname + " " +
            "(" + e.id + ")"
          );
        } else if(id == 0 || id == null || id == "") {
          cell.innerHTML = "Pending";
        }
      }
    });
  }
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

  const pendingTable = document.getElementById("pendingReimTable");
  if(pendingTable.rows.length > 1) {
    managers.forEach(m => {
      for (var i = pendingTable.rows.length - 1; i > 0; --i) {
        let cell = pendingTable.rows[i].cells[4];
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