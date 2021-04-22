package reimbursementmanager.model;

public class Reimbursement {
  private int id;
  private String name;
  private double price;
  private int employeeId;
  private int managerId;
  private boolean approved;
  private boolean resolved;

  public Reimbursement(int id, String name, double price, int employeeId, int managerId, boolean approved, boolean resolved) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.employeeId = employeeId;
    this.managerId = managerId;
    this.approved = approved;
    this.resolved = resolved;
  }

  public boolean isApproved() {
    return approved;
  }

  public void setApprove(boolean approved) {
    this.approved = approved;
  }

  public boolean isResolved() {
    return resolved;
  }

  public void setResolved(boolean resolved) {
    this.resolved = resolved;
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
