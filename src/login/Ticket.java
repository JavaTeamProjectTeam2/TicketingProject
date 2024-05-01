package src.login;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket {

    private String title;
    private String date;
    private String seat;
    private int price;
    private SHOWSTATE state;

    enum SHOWSTATE {
        SOON, PAST
    }

    public Ticket(String title, String date, String seat, int price) {
        this.title = title;
        this.date = date;
        this.seat = seat;
        this.price = price;
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

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public SHOWSTATE getState() {
        return this.state;
    }
    public void setState() {
        LocalDateTime targetDate = LocalDateTime.parse(this.getDate());
        LocalDateTime currDate = LocalDateTime.now();

        int diff = targetDate.compareTo(currDate);
        this.state = diff > 0 ? SHOWSTATE.SOON:SHOWSTATE.PAST;
    }

    @Override
    public String toString() {
        return "티켓 예약 내역";
    }
}
