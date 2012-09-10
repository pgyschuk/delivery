package com.dkord.datamodel;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Peter Gyschuk
 */
@Entity
@Table(name = "Menus")
@NamedQueries({
    @NamedQuery(name = "findMenuByDate", query = "FROM Menu menu WHERE menu.validDate = :validDate")
})
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menuId")
    private Long id;

    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date validDate;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "menu_dish", joinColumns = {
        @JoinColumn(name = "menuId", unique = false)
    },
    inverseJoinColumns = {
        @JoinColumn(name = "dishId", unique = false)
    })
    private Set<Dish> dishes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }
}
