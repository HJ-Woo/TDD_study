package airplane.domain;

import java.util.stream.Stream;

public enum UserGrade {
//    GOLD, DIAMOND, DIAMOND_PLUS, PLATINUM;
    // GOLD - ~ 4만까지 0.01
    // DIAMOND = 4만 이상 0.02
    // DIAMOND_PLUS = 10만 이상 0.03
    // PLATINUM = 100만 이상 0.04

    GOLD(0, 39_999, 0.01),
    DIAMOND(40_000, 99_999, 0.02),
    DIAMOND_PLUS(100_000, 999_999, 0.03),
    PLATINUM(1_000_000, 1_000_000_000, 0.04);

    private int mileageLowerBound;
    private int mileageUpperBound;
    private double discount;

    UserGrade(int mileageLowerBound, int mileageUpperBound, double discount) {
        this.mileageLowerBound = mileageLowerBound;
        this.mileageUpperBound = mileageUpperBound;
        this.discount = discount;
    }

    public int getMileageLowerBound() {
        return mileageLowerBound;
    }

    public int getMileageUpperBound() {
        return mileageUpperBound;
    }

    public static UserGrade match(double mileage) {
        return Stream.of(UserGrade.values())
                .filter(grade -> mileage >= grade.getMileageLowerBound() && mileage <= grade.getMileageUpperBound() )
                .findFirst()
                .orElse(GOLD);
    }

    public double mileageCalc(int price) {
        return price * discount;
    }

}
