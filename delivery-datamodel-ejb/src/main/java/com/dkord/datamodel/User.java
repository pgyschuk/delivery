/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.datamodel;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Peter Gyschuk
 */
@Entity
@Table(name = "Users")
@NamedQueries({
    @NamedQuery(name = "findByEmail", query = "FROM User user WHERE user.email = :email")
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

    @Column(unique = true)
    private String email;
    
    private String telephone;

    @Column
    private String password;

    @Column
    private float account;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public float getAccount() {
        return account;
    }

    public void addToAccount(float quantity) {
        this.account += quantity;
    }

    public void subtractFromAccount(float quantity) {
        this.account -= quantity;
    }
}
