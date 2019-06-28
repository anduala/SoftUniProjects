//package app.entities.Gringotts;
//
//import app.entities.BaseEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.math.BigDecimal;
//import java.util.Date;
//
//@Entity
//@Table(name = "gringots")
//public class Gringotts extends BaseEntity {
//
//    private String firstName;
//    private String lastName;
//    private String notes;
//    private int age;
//    private String magic_wand_creator;
//    private int magic_wand_size;
//    private String deposit_group;
//    private Date deposit_start_date;
//    private BigDecimal deposit_amount;
//    private BigDecimal deposit_interest;
//    private BigDecimal deposit_charge;
//    private Date deposit_expiration_date;
//    private boolean is_deposit_expired;
//
//    public Gringotts() {
//    }
//
//
//    @Column(name = "first_name",length = 50)
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    @Column(name = "last_name",length = 60)
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Column(name = "notes",length = 1000)
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    @Column(name = "age",nullable = false)
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @Column(name = "magic_wand_creator",length = 100)
//    public String getMagic_wand_creator() {
//        return magic_wand_creator;
//    }
//
//    public void setMagic_wand_creator(String magic_wand_creator) {
//        this.magic_wand_creator = magic_wand_creator;
//    }
//
//    @Column(name = "magic_wand_size")
//    public int getMagic_wand_size() {
//        return magic_wand_size;
//    }
//
//    public void setMagic_wand_size(int magic_wand_size) {
//        this.magic_wand_size = magic_wand_size;
//    }
//
//    @Column(name = "deposit_group",length = 20)
//    public String getDeposit_group() {
//        return deposit_group;
//    }
//
//    public void setDeposit_group(String deposit_group) {
//        this.deposit_group = deposit_group;
//    }
//
//    @Column(name = "deposit_start_date")
//    public Date getDeposit_start_date() {
//        return deposit_start_date;
//    }
//
//    public void setDeposit_start_date(Date deposit_start_date) {
//        this.deposit_start_date = deposit_start_date;
//    }
//
//    @Column(name = "deposit_amount")
//    public BigDecimal getDeposit_amount() {
//        return deposit_amount;
//    }
//
//    public void setDeposit_amount(BigDecimal deposit_amount) {
//        this.deposit_amount = deposit_amount;
//    }
//
//    @Column(name = "deposit_interest")
//    public BigDecimal getDeposit_interest() {
//        return deposit_interest;
//    }
//
//    public void setDeposit_interest(BigDecimal deposit_interest) {
//        this.deposit_interest = deposit_interest;
//    }
//
//    @Column(name = "deposit_charge")
//    public BigDecimal getDeposit_charge() {
//        return deposit_charge;
//    }
//
//    public void setDeposit_charge(BigDecimal deposit_charge) {
//        this.deposit_charge = deposit_charge;
//    }
//
//    @Column(name = "deposit_expiration_date")
//    public Date getDeposit_expiration_date() {
//        return deposit_expiration_date;
//    }
//
//    public void setDeposit_expiration_date(Date deposit_expiration_date) {
//        this.deposit_expiration_date = deposit_expiration_date;
//    }
//
//    @Column(name = "deposit_expired")
//    public boolean isIs_deposit_expired() {
//        return is_deposit_expired;
//    }
//
//    public void setIs_deposit_expired(boolean is_deposit_expired) {
//        this.is_deposit_expired = is_deposit_expired;
//    }
//}
