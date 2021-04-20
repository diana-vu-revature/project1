public class Reimbursement {
  private int id;
  private String name;
  private double price;
  private int employeeId;
  private int managerId;
  
  public Reimbursement(int id, String name, double price, int employeeId, int managerId) {
      this.id = id;
      this.name = name;
      this.price = price;
      this.employeeId = employeeId;
      this.managerId = managerId;
  }

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public double getPrice() {
    return price;
}

public void setPrice(double price) {
    this.price = price;
}

public int getEmployeeId() {
    return employeeId;
}

public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
}

public int getManagerId() {
    return managerId;
}

public void setManagerId(int managerId) {
    this.managerId = managerId;
}

  

}
