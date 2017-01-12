package north.sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by reva on 11/10/2016.
 */
@Entity
public class Antecedent {
//what's problem the patient have
    @Id
    private Long id;
    @ManyToOne
    Patient patient;
    @ManyToOne
    DiseaseType diseaseType;

    /* */
    @Column(name = "antecedent_date",nullable = false)
    private Date date;

    @Column(columnDefinition = "text",nullable = false, unique = true)
    private String detail;



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String  getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public DiseaseType getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(DiseaseType diseaseType) {
        this.diseaseType = diseaseType;
    }
}
