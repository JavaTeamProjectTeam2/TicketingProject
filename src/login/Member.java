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
    private String phone;
    private int point;
    private List<Ticket> ticketList;
    private boolean loginEnabled;
    private long lastDisabledTime;

    private static final long serialVersionUID = 1L;


    public Member(String name, String email, String pw, Integer age, String address, String phone) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.ticketList = new ArrayList<>();
        this.loginEnabled = true;
    }

    public Member(String name, String email, String pw, Integer age, String address, List<Ticket> ticketList) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.age = age;
        this.address = address;
        this.ticketList = ticketList;
        this.loginEnabled = true;
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

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public int getPoint() {
        return point;
    }

    public void setPoint(int price) {
        this.point = (int) Math.floor(price * 0.01);
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public boolean isLoginEnabled() {
        return loginEnabled;
    }

    public void setLoginEnabled(boolean loginEnabled) {
        this.loginEnabled = loginEnabled;
    }

    public long getLastDisabledTime() {
        return lastDisabledTime;
    }

    public void setLastDisabledTime(long lastDisabledTime) {
        this.lastDisabledTime = lastDisabledTime;
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
        return "=== Member ===\n" +
                "이름: " + name + '\n' +
                "주소: " + email + '\n' +
                "비밀번호: " + pw + '\n' +
                "나이: " + age + '\n' +
                "연락처: " + phone + '\n' +
                "주소: " + address + '\n' +
                "포인트: " + point + '\n' +
                "예매내역: " + ticketList.size() + '\n';
    }


}//class end

