//package app.entities.UniversitySystem;
//
//import app.entities.BaseEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import java.math.BigDecimal;
//import java.util.Set;
//
//@Entity
//@Table(name = "teachers")
//public class Teacher extends BaseEntity {
//    private String firstName;
//    private String lastName;
//    private int phoneNumber;
//    private String email;
//    private BigDecimal salaryPerHour;
//    private Set<Course> courses;
//
//    public Teacher() {
//    }
//
//    @Column(name = "first_name")
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    @Column(name = "last_name")
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Column(name = "phone_number")
//    public int getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(int phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    @Column(name = "email")
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @Column(name = "salary_per_hour")
//    public BigDecimal getSalaryPerHour() {
//        return salaryPerHour;
//    }
//
//    public void setSalaryPerHour(BigDecimal salaryPerHour) {
//        this.salaryPerHour = salaryPerHour;
//    }
//
//    @OneToMany(targetEntity = Course.class,mappedBy = "teacher")
//    public Set<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }
//}
