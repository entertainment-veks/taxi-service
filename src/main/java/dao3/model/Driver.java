package dao3.model;

public class Driver {
    private long id;
    private String name;
    private String licenceNumber;

    public Driver(long id, String name, String licenceNumber) {
        this.id = id;
        this.name = name;
        this.licenceNumber = licenceNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
