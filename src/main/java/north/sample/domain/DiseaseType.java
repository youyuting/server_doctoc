package north.sample.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by reva on 11/10/2016.
 */

@Entity
public class DiseaseType {
    @Id
    private long id;
    @Column(nullable = false)
    private String name;
    @Column (length = 256)
    private String description;

    @OneToMany(mappedBy = "diseaseType")
    private List<Antecedent> antecedents;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String   getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Antecedent> getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(List<Antecedent> antecedents) {
        this.antecedents = antecedents;
    }
}
