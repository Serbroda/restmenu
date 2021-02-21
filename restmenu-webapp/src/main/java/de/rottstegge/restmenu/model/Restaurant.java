package de.rottstegge.restmenu.model;

import de.rottstegge.restmenu.model.base.AbstractBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractBaseEntity {

    private String name;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
