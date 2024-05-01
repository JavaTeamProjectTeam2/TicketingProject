package src.login;

public class Ticket {

    private String title;
    private String date;
    private String seat;
    private int price;

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

    @Override
    public String toString() {
        return "티켓 예약 내역";
    }
}
