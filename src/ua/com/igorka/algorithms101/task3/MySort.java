package ua.com.igorka.algorithms101.task3;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySort {


    public int counter = 0;
    public int recursiveCounter = 0;
    public int[] letters = new int[26];

    public MySort() {
        for (int i = 0; i < letters.length; i++) {
            letters[i] = 0;
        }
    }

    //p - first element of array, r - last element of array
    public int[] quickSort(int[] array, int p, int r) {
        if (p < r) {
            //друга частина другого завдання
//            int tmp = array[p];
//            array[p] = array[r];
//            array[r] = tmp;
            //***2***

            //Третя частина другого завдання
//            int t = (p + r) / 2;
//            int medianaIndex = p;
//            if (array[p] < array[t] && array[t] < array[r]) {
//                medianaIndex = t;
//            }
//            if (array[r] < array[t] && array[t] < array[p]) {
//                medianaIndex = t;
//            }
//            if (array[t] < array[p] && array[p] < array[r]) {
//                medianaIndex = p;
//            }
//            if (array[r] < array[p] && array[p] < array[t]) {
//                medianaIndex = p;
//            }
//            if (array[p] < array[r] && array[r] < array[t]) {
//                medianaIndex = r;
//            }
//            if (array[t] < array[r] && array[r] < array[p]) {
//                medianaIndex = r;
//            }
//            int tmp = array[medianaIndex];
//            array[medianaIndex] = array[r];
//            array[r] = tmp;
            //***3***
            recursiveCounter++;
            int q = partition(array, p, r);
            array = quickSort(array, p, q-1);
            array = quickSort(array, q+1, r);
        }
        System.out.println(recursiveCounter);
        return array;
    }

    private int partition(int[] array, int p, int r) {
        int x = array[r];
        int i = p -1;
        for (int j = p; j <= r - 1; j++) {
            if (array[j] <= x) {
                i++;
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
            counter++;
        }
        int tmp = array[r];
        i++;
        array[r] = array[i];
        array[i] = tmp;
        return i;
    }


    //Radix sort
    public void radixSort(String[] wBase, int d) {
        System.out.println("Start radix sort");
        for (int i = 2; i >=0; i--) {
            wBase = countingSort(wBase, i);
            System.out.println(Arrays.toString(wBase));
        }
        System.out.println(Arrays.toString(letters));
        System.out.println(intToChar(findMax(letters)).toString());
        System.out.println(wBase[0] + intToChar(findMax(letters)).toString() + wBase[wBase.length-1]);
    }

    private String[] countingSort(String[] wBase, int i) {
        byte t = 97;
        int[] c = new int[26];
        for (int j = 0; j < c.length; j++) {
            c[j] = 0;
        }

        for (int j = 0; j < wBase.length; j++) {
            int jj = wBase[j].charAt(i) - t;
            c[jj] += 1;
            letters[jj] += 1;
        }

        for (int j = 1; j < c.length; j++) {
            c[j] = c[j] + c[j - 1];
        }
        System.out.println(Arrays.toString(c));

        String[] result = new String[wBase.length];
        for (int j = wBase.length - 1; j >= 0; j-- ) {
            int jj = wBase[j].charAt(i) - t;
            result[c[jj]-1] = wBase[j];
            c[jj] -= 1;
        }
        return result;
    }

    private List<Integer> findMax(int[] array) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        int max = array[0];
        for (int i = 1; i < array.length - 1; i++) {
            if (max < array[i] ) {
                result.removeAll(result);
                max = array[i];
                result.add(i);
                continue;
            }
            if (max == array[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private List<Character> intToChar(List<Integer> integers) {
        List<Character> result = new ArrayList<Character>();
        for (Integer item : integers) {
            result.add(new Character((char) (item + 97)));
        }
        return result;
    }

}
