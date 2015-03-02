package ua.com.igorka.algorithms101.task2;

import java.util.Arrays;

/**
 * Created by igor9_000 on 23.02.2015.
 */
public class InversionAlgorithm {


    public int countInversion(int[] filmArray1, int[] filmArray2) {
        int [] sortedArray = sortArrays(filmArray1, filmArray2);
        System.out.println(Arrays.toString(sortedArray));
        return SortAndCountInversion(sortedArray);
    }

    private int[] sortArrays(int[] filmArray1, int[] filmArray2) {
        if (filmArray1.length != filmArray2.length) {
            throw new IllegalArgumentException("Arrays should be equal length");
        }
        int length = filmArray1.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (filmArray1[j] == i+1){
                    result[i] = filmArray2[j];
                    break;
                }
            }
        }
        return result;
    }


    private int SortAndCountInversion(int[] sortedArray) {
        int length = sortedArray.length;
        int invCounter = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (sortedArray[i] > sortedArray[j]) {
                    invCounter++;
                }
            }
        }
        return invCounter;
    }
}
