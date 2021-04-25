package reimbursementmanager.model;

public class Reimbursement {
  private int id;
  private String name;
  private double price;
  private int employeeId;
  private Integer managerId;
  private Boolean approved;
  private Boolean resolved;

  public Reimbursement(int id, String name, double price, int employeeId, Integer managerId, Boolean approved, Boolean resolved) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.employeeId = employeeId;
    this.managerId = managerId;
    this.approved = approved;
    this.resolved = resolved;
  }

  public Boolean isApproved() {
    return approved;
  }

  public void setApprove(Boolean approved) {
    this.approved = approved;
  }

  public Boolean isResolved() {
    return resolved;
  }

  public void setResolved(Boolean resolved) {
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

  public Integer getManagerId() {
    return managerId;
  }

  public void setManagerId(Integer managerId) {
    this.managerId = managerId;
  }

}
