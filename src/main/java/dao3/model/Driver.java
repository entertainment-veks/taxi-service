package dao3.model;

import java.util.Objects;

public class Driver {
    private Long id;
    private String name;
    private String licenceNumber;

    public Driver(String name, String licenceNumber) {
        this.name = name;
        this.licenceNumber = licenceNumber;
    }

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

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    @Override
    public String toString() {
        return "Driver{" + "id=" + id
                + ", name='" + name + '\''
                + ", licenceNumber='" + licenceNumber + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        Driver driver = (Driver) o;
        return Objects.equals(getId(), driver.getId()) &&
                Objects.equals(getName(), driver.getName()) &&
                Objects.equals(getLicenceNumber(), driver.getLicenceNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLicenceNumber());
    }
}
