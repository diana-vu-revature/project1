package reimbursementmanager.model;

public class User {
  private int id;
  private int roleId;
  private String fname;
  private String surname;
  private String email;
  private String pword;

  public User(int id, int roleId, String fname, String surname, String email, String pword) {
    this.id = id;
    this.roleId = roleId;
    this.fname = fname;
    this.surname = surname;
    this.email = email;
    this.pword = pword;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPword() {
    return pword;
  }

  public void setPword(String pword) {
    this.pword = pword;
  }

}
