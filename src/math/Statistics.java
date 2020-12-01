package math;

import java.util.Arrays;

public class Statistics <T extends Number> {

    private final T[] numbers;

    public Statistics(T[] numbers,  boolean sorted) {
        if(numbers == null || numbers.length == 0){
            throw new IllegalArgumentException("This method cannot except an empty set.");
        }
        this.numbers = numbers;


        if(!sorted) Arrays.sort(numbers);
    }

    public T min() { return numbers[0];}

    public T max(){ return numbers[numbers.length-1]; }
}
