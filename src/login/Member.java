package src.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Member implements Serializable {
    private String name;
    private String email;
    private String pw;
    private Integer age;
    private String address;
    private int point;
    private List<Ticket> ticketList;


    public Member(String name, String email, String pw, Integer age, String address) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.age = age;
        this.address = address;
        this.ticketList = new ArrayList<>();
    }

    public Member(String name, String email, String pw, Integer age, String address, List<Ticket> ticketList) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.age = age;
        this.address = address;
        this.ticketList = ticketList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member m = (Member) o;
        return Objects.equals(name, m.name) && Objects.equals(email, m.email) && Objects.equals(pw, m.pw);
    }
    @Override
    public String toString() {
        return "=== Member ===" +
                "이름: " + name + '\n' +
                "주소: " + email + '\n' +
                "비밀번호: " + pw + '\n' +
                "나이: " + age + '\n' +
                "주소: " + address + '\n' +
                "포인트: " + point + '\n' +
                "예매내역" + ticketList + '\n';
    }


}//class end

