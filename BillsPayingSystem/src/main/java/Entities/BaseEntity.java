package Entities;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {
    private long id;


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BaseEntity() {
    }
}
