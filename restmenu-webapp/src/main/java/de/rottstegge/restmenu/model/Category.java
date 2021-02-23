package de.rottstegge.restmenu.model;

import de.rottstegge.restmenu.model.base.AbstractBaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends AbstractBaseEntity {

    private Menu menu;
    private List<Meal> meals;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public long getId() {
        return doGetId();
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
