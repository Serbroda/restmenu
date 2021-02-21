package de.rottstegge.restmenu.model;

import de.rottstegge.restmenu.model.base.AbstractBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractBaseEntity {

    private String name;
    private String description;
    private Address address;
    private Contact contact;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public long getId() {
        return doGetId();
    }

    @Column(nullable = false)
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

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Embedded
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
