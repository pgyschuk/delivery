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
    @JoinTable(name = "cateringProvider_addressLocation", joinColumns = {
        @JoinColumn(name = "cateringProviderId", unique = false)
    },
    inverseJoinColumns = {
        @JoinColumn(name = "addressId", unique = false)
    })
    private Set<Address> addressLocation;
    
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "cateringProvider_shippingAddress", joinColumns = {
        @JoinColumn(name = "cateringProviderId", unique = false)
    },
    inverseJoinColumns = {
        @JoinColumn(name = "addressId", unique = false)
    })
    private Set<Address> shippingAddress;
    
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "cateringProvider_menu", joinColumns = {
        @JoinColumn(name = "cateringProviderId", unique = false)
    },
    inverseJoinColumns = {
        @JoinColumn(name = "menuId", unique = false)
    })
    private Set<Menu> menus;

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

    public Set<Address> getAddressLocation() {
        return addressLocation;
    }

    public void setAddressLocation(Set<Address> addressLocation) {
        this.addressLocation = addressLocation;
    }

    public Set<Address> getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Set<Address> shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
    
}
