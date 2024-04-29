package src;

public enum Category {
//    private final double VIPPRICE = 198000;
//    private final double RPRICE = 154000;
//    private final double SPRICE = 132000;
//    private final double APRICE = 99000;
    CONCERT(1, "콘서트"), MUSICAL(2, "뮤지컬"),EXHIBIT(3, "전시회"), FAMILY(4, "아동/가족");
    private final String contentName;
    private final int categoryOptionNumber;

    Category(int categoryOptionNumber, String contentName) {
        this.contentName = contentName;
        this.categoryOptionNumber = categoryOptionNumber;
    }

    public int getCategoryOptionNumber() {
        return this.categoryOptionNumber;
    }

    public String getContentName() {
        return contentName;
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
