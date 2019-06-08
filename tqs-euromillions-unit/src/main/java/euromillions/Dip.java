package euromillions;

import java.util.Objects;
import sets.SetOfNaturals;
import java.util.Random;

/**
 * A set of 5 numbers and 2 starts according to the Euromillions ranges.
 *
 * @author ico0
 */
public class Dip {

    public final static int REQUIRED_NUMBERS_COUNT_FOR_BET = 5;
    public final static int REQUIRED_STARS_COUNT_FOR_BET = 2;

    private SetOfNaturals numbers;
    private SetOfNaturals starts; 
    private static Random generator;

    public Dip() {
        numbers = new SetOfNaturals();
        starts = new SetOfNaturals();
        generator = new Random();
    }

    public Dip(int[] arrayOfNumbers, int[] arrayOfStarts) {
        this();

        if (REQUIRED_NUMBERS_COUNT_FOR_BET == arrayOfNumbers.length && REQUIRED_STARS_COUNT_FOR_BET == arrayOfStarts.length) {
            numbers.add(arrayOfNumbers);
            starts.add(arrayOfStarts);
        } else {
            throw new IllegalArgumentException("wrong number of elements in numbers/stars");
        }

    }

    public SetOfNaturals getNumbersColl() {
        return numbers;
    }

    public SetOfNaturals getStarsColl() {
        return starts;
    }

    public static Dip generateRandomDip() {
        

        Dip randomDip = new Dip();
        for (int i = 0; i < REQUIRED_NUMBERS_COUNT_FOR_BET;) {
            int candidate = generator.nextInt(49) + 1;
            if (!randomDip.getNumbersColl().contains(candidate)) {
                randomDip.getNumbersColl().add(candidate);
                i++;
            }
        }
        for (int i = 0; i < REQUIRED_STARS_COUNT_FOR_BET;) {
            int candidate = generator.nextInt(11) + 1;
            if (!randomDip.getStarsColl().contains(candidate)) {
                randomDip.getStarsColl().add(candidate);
                i++;
            }
        }
        return randomDip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.numbers);
        hash = 29 * hash + Objects.hashCode(this.starts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dip other = (Dip) obj;
        if (!Objects.equals(this.numbers, other.numbers)) {
            return false;
        }
        if (!Objects.equals(this.starts, other.starts)) {
            return false;
        }
        return true;
    }


   
    /**
     * prepares a string representation of the data structure, formated for
     * printing
     *
     * @return formatted string with data
     */
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append("N[");
        for (int number : getNumbersColl()) {
            sb.append(String.format("%3d", number));
        }
        sb.append("] S[");
        for (int star : getStarsColl()) {
            sb.append(String.format("%3d", star));
        }
        sb.append("]");
        return sb.toString();
    }
}
