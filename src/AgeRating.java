package src;

public enum AgeRating {
    FOURTEEN(14, "성인"), SEVEN(7, "미취학아동"), ALL(0,"전체");

    private final int age;
    private final String ageOption;

    AgeRating(int age, String ageOption) {
        this.age = age;
        this.ageOption = ageOption;
    }

    public int getAge() {
        return this.age;
    }

    public String getAgeOption() {
        return ageOption;
    }
}
