package src;

public enum AgeRating {
    FOURTEEN(14), SEVEN(7);

    private final int age;

    AgeRating(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
