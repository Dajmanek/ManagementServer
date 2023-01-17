package pl.krakow.up.managementserver.persistence.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "password", nullable = false)
    private String password;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return Objects.equals(id, client.id) && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(address, client.address) && Objects.equals(password, client.password) && Objects.equals(full, client.full);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, password, full);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + this.id +
                ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", address='" + this.address + '\'' +
                ", password='" + this.password + '\'' +
                ", full=" + this.full +
                '}';
    }
}
