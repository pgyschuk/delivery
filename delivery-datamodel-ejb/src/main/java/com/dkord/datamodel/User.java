/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.datamodel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Peter Gyschuk
 */
@Entity
@Table(name = "Users")
@NamedQueries({
    @NamedQuery(name = "findByEmail", query = "FROM User user WHERE user.contacts.email = :email"),
    @NamedQuery(name = "findAllUsers", query = "FROM User user ORDER BY user.name")
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Long id;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
        @JoinColumn(name = "userId", unique = false)
    },
    inverseJoinColumns = {
        @JoinColumn(name = "roleId", unique = false)
    })
    private Set<Role> roles;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private float account;
    
    @ManyToOne(cascade= CascadeType.ALL)
    private Contacts contacts;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
        this.name = "";
        this.password = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles == null ? new HashSet<Role>() : roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public float getAccount() {
        return account;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public void addToAccount(float quantity) {
        this.account += quantity;
    }

    public void subtractFromAccount(float quantity) {
        this.account -= quantity;
    }

    @Override
    public String toString() {
        return name;
    }
}
