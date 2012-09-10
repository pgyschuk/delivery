/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Peter Gyschuk
 */
@Entity
@Table(name = "Roles")
@NamedQueries({
    @NamedQuery(name = "findByAuthority", query = "FROM Role role WHERE role.authority = :authority"),
    @NamedQuery(name = "findAllRoles", query = "FROM Role role")
})
public class Role implements GrantedAuthority {
    
    public static enum Authority{
        ROLE_USER, ROLE_ADMIN;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleId")
    private Long id;

    @Column
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 61 * hash + (this.authority != null ? this.authority.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role other = (Role) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.authority == null) ? (other.authority != null) : !this.authority.equals(other.authority)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return authority;
    }
    
}
