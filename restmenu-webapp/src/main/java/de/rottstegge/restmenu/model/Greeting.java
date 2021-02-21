package de.rottstegge.restmenu.model;

import de.rottstegge.restmenu.model.base.AbstractBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "greeting")
public class Greeting extends AbstractBaseEntity {

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public long getId() {
        return doGetId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
