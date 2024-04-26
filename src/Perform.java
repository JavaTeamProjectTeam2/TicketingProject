package src;
import java.util.*;

// 제목, 장소, 관람시간, 공연시간, 가격, 연령(등급), 좌석등급
public class Perform {
    private String title;
    private String place;
    private String date;
    private int runningTime;
    private int price;
    private int age;
    private String section;

    public Perform(String title, String place, String date, int runningTime, int price, int age, String section) {
        this.title = title;
        this.place = place;
        this.date = date;
        this.runningTime = runningTime;
        this.price = price;
        this.age = age;
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public java.lang.String toString() {
        return "Perform{" +
                "title='" + title + '\'' +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", runningTime=" + runningTime +
                ", price=" + price +
                ", age=" + age +
                ", section='" + section + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perform perform = (Perform) o;
        return runningTime == perform.runningTime && price == perform.price && age == perform.age && Objects.equals(title, perform.title) && Objects.equals(place, perform.place) && Objects.equals(date, perform.date) && Objects.equals(section, perform.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, place, date, runningTime, price, age, section);
    }
}

