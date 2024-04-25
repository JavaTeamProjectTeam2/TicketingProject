package src;

import java.util.Objects;

public class Perform {
    private String title;
    private String date;
    private int price;
    private String section;

    public Perform(String title, String date, int price, String section) {
        this.title = title;
        this.date = date;
        this.price = price;
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "PerformRepository{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", section='" + section + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perform that = (Perform) o;
        return price == that.price && Objects.equals(title, that.title) && Objects.equals(date, that.date) && Objects.equals(section, that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date, price, section);
    }
}
