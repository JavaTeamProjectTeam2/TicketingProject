package src;


public enum Section {
    VIP(198000, 1), R(154000, 2), S(132000, 3), A(99000, 4);
    private final int price;
    private final int secNum;
    Section(int price, int secNum) {
        this.price = price;
        this.secNum = secNum;
    }

    public int getPrice() {
        return price;
    }
    public int getSecNum() {
        return secNum;
    }
}
