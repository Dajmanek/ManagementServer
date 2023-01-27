package pl.krakow.up.managementserver.persistence.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building_number")
    private Integer buildingNumber;

    @Column(name = "flat_number")
    private Integer flatNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "last_update")
    private Long lastUpdate;

    @Column(name = "full", nullable = false, columnDefinition = "boolean default false")
    private Boolean full;

    public Client() {
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Boolean getFull() {
        return full;
    }

    public void setFull(Boolean full) {
        this.full = full;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return buildingNumber == client.buildingNumber && flatNumber == client.flatNumber && Objects.equals(id, client.id) && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(postCode, client.postCode) && Objects.equals(city, client.city) && Objects.equals(street, client.street) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(password, client.password) && Objects.equals(lastUpdate, client.lastUpdate) && Objects.equals(full, client.full);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, postCode, city, street, buildingNumber, flatNumber, phoneNumber, password, lastUpdate, full);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", flatNumber=" + flatNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", full=" + full +
                '}';
    }
}
