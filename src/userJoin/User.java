package src.userJoin;

import java.util.Objects;

public class User {
    private String name;
    private String id;
    private String pw;
    private Integer age;
    private String address;


    public User(String name, String id, String pw, Integer age, String address) {
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = Integer.valueOf(age);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(id, user.id) && Objects.equals(pw, user.pw) && Objects.equals(age, user.age) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, pw, age, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}//class end

