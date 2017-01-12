package north.sample.domain;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by yyou on 2016/11/20.
 */
public class Card {
    @Id
    private long id;
    @Column(nullable = false)
    private long number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
