package beans;

import java.util.List;

public class Publisher {
    private String name;
    private List<String> addresses;
    private List<String> phones;

    // GETTERS
    public String getName() {
        return name;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public List<String> getPhones() {
        return phones;
    }


    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}
