package pl.krakow.up.managementserver.api.dto;

public class ClientDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String password;

    private Boolean full;

    public ClientDto() {
    }

    public ClientDto(final Long id,
                     final String firstName,
                     final String lastName,
                     final String address,
                     final String password,
                     final Boolean full) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.full = full;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Boolean getFull() {
        return this.full;
    }

    public void setFull(final Boolean full) {
        this.full = full;
    }
}
