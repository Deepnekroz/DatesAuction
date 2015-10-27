package com.sergeev.datesauction.model;

import javax.persistence.*;

/**
 * Created by dmitry-sergeev on 27.10.15.
 */
@Entity
public abstract class AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
