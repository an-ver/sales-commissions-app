package data;

public class Company {

    private String name;
    private Address address;

	public Company(String name, Address address) {
		this.name = name;
		this.address = address;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Address getAddress() {
        return address;
    }
}
