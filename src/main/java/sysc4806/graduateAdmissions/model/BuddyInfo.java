package sysc4806.graduateAdmissions.model;

import javax.persistence.*;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private int age;

    public BuddyInfo() {
    }

    public BuddyInfo(String name, String address, String phone, int age) {
        super();

        this.name = name;
        this.address = address;
        this.phoneNumber = phone;
        this.age = age;

    }

    public BuddyInfo(BuddyInfo bInfo) {
        super();
        this.name = bInfo.getName();
        this.address = bInfo.getAddress();
        this.phoneNumber = bInfo.getPhoneNumber();
        this.age = bInfo.getAge();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public boolean isOver18() {
        if (this.age > 18) return true;
        return false;
    }

    public boolean equals(BuddyInfo info) {

        if (info.getName().equals(this.name) &&
                info.getAddress().equals(this.address) &&
                info.getPhoneNumber().equals(this.phoneNumber)) return true;
        return false;

    }

    public String getGreeting() {
        return "Hello";

    }

    public String toString() {
        return " Name: " + this.name +
                "\n Age: " + this.age +
                "\n Address: " + this.address +
                "\n Phone: " + this.phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
