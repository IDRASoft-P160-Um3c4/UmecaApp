package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Location;
import com.umeca.model.shared.EntityGrid;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 04:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "address")
public class Address implements EntityGrid {
    public Address() {

    }

    public Address(Address other) {
        this.street = other.getStreet();
        this.outNum = other.getOutNum();
        this.innNum = other.getInnNum();
        this.lat = other.getLat();
        this.lng = other.getLng();
        this.addressString = other.getAddressString();
        this.location = other.getLocation();
    }

    @Id
    @GeneratedValue
    @Column(name = "id_address")
    private Long id;

    @Column(name = "street", length = 100, nullable = true)
    private String street;

    @Column(name = "no_outside", length = 10, nullable = true)
    private String outNum;

    @Column(name = "no_inside", length = 10, nullable = true)
    private String innNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location", nullable = true)
    private Location location;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @Column(name = "address_string", nullable = true, length = 500)
    private String addressString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getInnNum() {
        return innNum;
    }

    public void setInnNum(String innNum) {
        this.innNum = innNum;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAddressString() {
        return addressString;
    }

    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        String result = "";
        if (street != null && !street.equals("")) {
            result = "Calle: " + street + " No Ext: " + outNum;
        }
        if (outNum != null && !outNum.equals("")) {

        }
        if (innNum != null && !innNum.equals("")) {
            result = result + " No Int:" + innNum;
        }
        if (location != null) {
            result = result + "," + location.getName() + ". CP: " + location.getZipCode() + ". " + location.getMunicipality().getName() + ", " + location.getMunicipality().getState().getName() + ".";
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Address)) return false;
        Address otherAddress = (Address) other;

        if (this.getStreet().equals(otherAddress.getStreet()) &&
                this.getOutNum().equals(otherAddress.getOutNum()) &&
                this.getInnNum().equals(otherAddress.getInnNum()) &&
                this.getLocation().getId().equals(otherAddress.getLocation().getId()))
            return true;

        return false;
    }
}
