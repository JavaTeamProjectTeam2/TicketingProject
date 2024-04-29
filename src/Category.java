package src;

public enum Category {
//    private final double VIPPRICE = 198000;
//    private final double RPRICE = 154000;
//    private final double SPRICE = 132000;
//    private final double APRICE = 99000;
    CONCERT(1), MUSICAL(2),EXHIBIT(3), FAMILY(4);

    private final int categoryOptionNumber;

    Category(int categoryOptionNumber) {
        this.categoryOptionNumber = categoryOptionNumber;
    }

    public int getCategoryOptionNumber() {
        return this.categoryOptionNumber;
    }

    public static Category getCategoryByOption(int option) {
        for (Category category : Category.values()) {
            if (category.getCategoryOptionNumber() == option) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid option: " + option);
    }
}
