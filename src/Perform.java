package src;
import java.util.*;

// 제목, 장소, 관람시간, 공연시간, 가격, 연령(등급), 좌석등급
public class Perform {
    private String title;
    private String category;
    private String place;
    private int age;
    private Date date; // 관람 시간, 공연 시간
    private PriceData price; //price, section

    public Perform(String title, String category, String place, int age, Date date, PriceData price) {
        this.title = title;
        this.category = category;
        this.place = place;
        this.age = age;
        this.date = date;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public PriceData getPrice() {
        return price;
    }

    public void setPrice(PriceData price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Perform{" +
                "title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", place='" + place + '\'' +
                ", age=" + age +
                ", date=" + date +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perform perform = (Perform) o;
        return age == perform.age && Objects.equals(title, perform.title) && Objects.equals(category, perform.category) && Objects.equals(place, perform.place) && Objects.equals(date, perform.date) && Objects.equals(price, perform.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, category, place, age, date, price);
    }
}

