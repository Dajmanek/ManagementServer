package pl.krakow.up.managementserver.api.dto;

public class ClientDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String postCode;

    private String city;

    private String street;

    private Integer buildingNumber;

    private Integer flatNumber;

    private String phoneNumber;

    private String password;

    private Long lastUpdate;

    private Boolean full;

    public ClientDto() {
    }

    public ClientDto(Long id, String firstName, String lastName, String postCode, String city, String street, Integer buildingNumber, Integer flatNumber, String phoneNumber, String password, Long lastUpdate, Boolean full) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.flatNumber = flatNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.lastUpdate = lastUpdate;
        this.full = full;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
