package north.sample.domain;

//import org.postgis.Point;

import javax.persistence.*;
import java.util.List;

/**
 * Created by yyou on 2016/11/10.
 */

@Entity
public class Doctor {
    @Id
    private Long id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String address;

    //private Point currentLocation;
    @Column( nullable = false)
    private char sex;
    @Column(nullable = false)
    private String fac;
    @Column(nullable = false,unique = true)
    private Long telephone;
    @Column( nullable = false,unique = true)
    private String email;
    @Column( nullable = false)
    private String photo;
    @Column(name="pic")
    @Lob
    private byte[] picture;
    @OneToMany(mappedBy = "doctor")
    private List<Consultation> consultations;

    public Doctor() {
    }

    public Doctor(Long id, String password, String lastName, String firstName, String address, /*Point currentLocation,*/ char sex, String fac, Long telephone, String email, String photo, byte[] picture, List<Consultation> consultations) {
        this.id = id;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
//        this.currentLocation = currentLocation;
        this.sex = sex;
        this.fac = fac;
        this.telephone = telephone;
        this.email = email;
        this.photo = photo;
        this.picture = picture;
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

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getFac() {
        return fac;
    }

    public void setFac(String fac) {
        this.fac = fac;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

   /* public Point getCurrentLocation() { return currentLocation;}

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }*/
}