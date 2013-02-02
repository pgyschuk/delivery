package com.dkord.datamodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Peter Gyschuk
 */
@Entity
@Table(name = "Contacts")
@NamedQueries({
    @NamedQuery(name = "findAddressesByCity", query = "FROM Contacts contacts WHERE contacts.city = :city")
})
public class Contacts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contactsId")
    private Long id;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String number;
    
    @Column
    private String telephone;

    @Column(unique = true)
    private String email;
    
    @Column
    private String latitude;

    @Column
    private String longitude;

    public Contacts() {
        this.email = "";
        this.city = "";
        this.street = "";
        this.number = "";
        this.telephone = "";
        this.latitude = "";
        this.longitude = "";
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 97 * hash + (this.street != null ? this.street.hashCode() : 0);
        hash = 97 * hash + (this.number != null ? this.number.hashCode() : 0);
        hash = 97 * hash + (this.telephone != null ? this.telephone.hashCode() : 0);
        hash = 97 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 97 * hash + (this.latitude != null ? this.latitude.hashCode() : 0);
        hash = 97 * hash + (this.longitude != null ? this.longitude.hashCode() : 0);
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
        final Contacts other = (Contacts) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city)) {
            return false;
        }
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street)) {
            return false;
        }
        if ((this.number == null) ? (other.number != null) : !this.number.equals(other.number)) {
            return false;
        }
        if ((this.telephone == null) ? (other.telephone != null) : !this.telephone.equals(other.telephone)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.latitude == null) ? (other.latitude != null) : !this.latitude.equals(other.latitude)) {
            return false;
        }
        if ((this.longitude == null) ? (other.longitude != null) : !this.longitude.equals(other.longitude)) {
            return false;
        }
        return true;
    }
    
    
}
