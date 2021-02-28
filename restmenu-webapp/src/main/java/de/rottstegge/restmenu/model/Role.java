package de.rottstegge.restmenu.model;

import de.rottstegge.restmenu.model.base.AbstractBaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_role")
public class Role extends AbstractBaseEntity {

    private String name;
    private Set<Permission> permissions = new HashSet<>();

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

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
