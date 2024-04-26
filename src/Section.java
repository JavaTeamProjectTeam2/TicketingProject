package src;

public enum Section {
    VIP(198000), R(154000), S(132000), A(99000), ADULT(30000), CHILD(15000);

    private final Integer price;
    Section(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
