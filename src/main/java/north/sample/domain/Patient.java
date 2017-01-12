package north.sample.domain;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;


import javax.persistence.*;
import java.util.List;

/**
 * Created by yyou on 2016/11/14.
 */
@Entity
public class Patient {
    @Id
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private char sex;

    @Column(nullable = false,unique = true)
    private long telephone;

    @Column( nullable = false,unique = true)
    private String email;

    @Column( nullable = false)
    private String address;
//when you register you already login , and logout

//    @Column
//    private Point currentLocation;

    @OneToMany(mappedBy = "patient")
    private List<Antecedent> antecedents;

    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;

    public Patient() {
    }

    public Patient(Long id, String password, String lastName, String firstName, char sex, long telephone, String email, String address, List<Antecedent> antecedents, List<Consultation> consultations) {
        this.id = id;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.sex = sex;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
//        this.currentLocation = currentLocation;
        this.antecedents = antecedents;
        this.consultations = consultations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public Point getCurrentLocation() {
//        return currentLocation;
//    }
//
//    public void setCurrentLocation(Point currentLocation) {
//        this.currentLocation = currentLocation;
//    }

    public List<Antecedent> getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(List<Antecedent> antecedents) {
        this.antecedents = antecedents;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public static Patient findByEmail(String email) {
        return Ebean.find(Patient.class).where(Expr.eq("email", email)).findUnique();
    }

}
