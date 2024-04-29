package src;
import java.util.*;
import java.util.Date;

// 제목, 장소, 관람시간, 공연시간, 가격, 연령(등급), 좌석등급
public class Perform {
    private String title;
    private Category category;
    private String place;
    private int age;
    private Date date;

    public Perform(String title, Category category, String place, int age, Date date) {
        this.title = title;
        this.category = category;
        this.place = place;
        this.age = age;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Perform{" +
                "title='" + title + '\'' +
                ", category=" + category +
                ", place='" + place + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perform perform = (Perform) o;
        return age == perform.age && Objects.equals(title, perform.title) && category == perform.category && Objects.equals(place, perform.place) && Objects.equals(date, perform.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, category, place, age, date);
    }
}

