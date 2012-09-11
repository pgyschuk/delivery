package com.dkord.datamodel;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;

/**
 *
 * @author Peter Gyschuk
 */
@Entity
@Table(name = "CateringProviders")
@NamedQueries({
//    @NamedQuery(name = "findCateringProvidersByCity", query = "FROM CateringProvider cateringProvider WHERE cateringProvider.addressLocation.city = :city")
})
public class CateringProvider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cateringProviderId")
    private Long id;

    @Column
    private String name;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "cateringProvider_contactsLocation", joinColumns = {
        @JoinColumn(name = "cateringProviderId", unique = false)
    },
    inverseJoinColumns = {
        @JoinColumn(name = "contactsId", unique = false)
    })
    private Set<Contacts> contactsLocation;
    
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "cateringProvider_shippingContacts", joinColumns = {
        @JoinColumn(name = "cateringProviderId", unique = false)
    },
    inverseJoinColumns = {
        @JoinColumn(name = "contactsId", unique = false)
    })
    private Set<Contacts> shippingContacts;
    
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
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

    public Set<Contacts> getContactsLocation() {
        return contactsLocation;
    }

    public void setContactsLocation(Set<Contacts> contactsLocation) {
        this.contactsLocation = contactsLocation;
    }

    public Set<Contacts> getShippingContacts() {
        return shippingContacts;
    }

    public void setShippingContacts(Set<Contacts> shippingContacts) {
        this.shippingContacts = shippingContacts;
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
        
}
