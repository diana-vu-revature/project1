package project1;

public class Employee {
    private int id;
    private String fname;
    private String surname;
    private String email;
    private String pword;
    private double reimbursement;

    public Employee(int id, String fname, String surname, String email, String pword, double reimbursement) {
        this.id = id;
        this.fname = fname;
        this.surname = surname;
        this.email = email;
        this.pword = pword;
        this.reimbursement = reimbursement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(double reimbursement) {
        this.reimbursement = reimbursement;
    }
    
}
