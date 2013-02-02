package com.dkord.datamodel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Peter Gyschuk
 */
@Entity
@Table(name = "CateringProviders")
@NamedQueries({
    @NamedQuery(name = "findAllCateringProviders", query = "FROM CateringProvider cateringProvider")
//    @NamedQuery(name = "findCateringProvidersByCity", query = "FROM CateringProvider cateringProvider WHERE cateringProvider.addressLocation.city = :city")
})
public class CateringProvider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cateringProviderId")
    private Long id;

    @Column
    private String name;

    @OneToMany(targetEntity = Contacts.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "cateringProvider_contactsLocation", joinColumns = {
        @JoinColumn(name = "cateringProviderId", unique = false)
    },
    inverseJoinColumns = {
        @JoinColumn(name = "contactsId", unique = false)
    })
    private List<Contacts> contactsLocation;

    @OneToMany(targetEntity = Menu.class, fetch = FetchType.EAGER)
    @JoinTable(name = "cateringProvider_menu", joinColumns = {
        @JoinColumn(name = "cateringProviderId", unique = false)
    },
    inverseJoinColumns = {
        @JoinColumn(name = "menuId", unique = false)
    })
    private Set<Menu> menus;

    @Lob
    private byte[] logo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contacts> getContactsLocation() {
        return contactsLocation;
    }

    public void setContactsLocation(List<Contacts> contactsLocation) {
        this.contactsLocation = contactsLocation;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 17 * hash + (this.contactsLocation != null ? this.contactsLocation.hashCode() : 0);
        hash = 17 * hash + (this.menus != null ? this.menus.hashCode() : 0);
        hash = 17 * hash + Arrays.hashCode(this.logo);
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
        final CateringProvider other = (CateringProvider) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.contactsLocation != other.contactsLocation && (this.contactsLocation == null || !this.contactsLocation.equals(other.contactsLocation))) {
            return false;
        }
        if (this.menus != other.menus && (this.menus == null || !this.menus.equals(other.menus))) {
            return false;
        }
        if (!Arrays.equals(this.logo, other.logo)) {
            return false;
        }
        return true;
    }
    
    
}
