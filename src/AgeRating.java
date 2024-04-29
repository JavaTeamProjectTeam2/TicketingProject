package src;

public enum AgeRating {
    FOURTEEN(14), SEVEN(7), ALL(0);

    private final int age;

    AgeRating(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }
}
