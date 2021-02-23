package de.rottstegge.restmenu.model;

import de.rottstegge.restmenu.model.base.AbstractBaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {

    private List<Category> categories;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public long getId() {
        return doGetId();
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
