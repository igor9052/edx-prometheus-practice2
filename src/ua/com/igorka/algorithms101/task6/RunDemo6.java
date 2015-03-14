package ua.com.igorka.algorithms101.task6;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by igor9_000 on 07.03.2015.
 */
public class RunDemo6 {

    private static  int counter = 0;
    private static Set<Long> result = new HashSet<Long>();

    public static void main(String[] args) {

        MyHash hash = new MyHash();
        Map<Long, Long> hashMap = hash.getData();
        System.out.println(hashMap.size());
        Long x;
        Long[] set = new Long[999752];
        int i = 0;
        for (Long item : hashMap.keySet()){
            set[i] = item;
            i++;
        }
        for (Long item : set) {
           hashMap = check(hashMap, item);
        }
        System.out.println(counter);
        System.out.println(result.size());

    }

    public static Map<Long, Long> check(Map<Long,Long> map, Long x) {
        map.remove(x);
        Long s;
        for (int j = -1000; j <= 1000; j++) {
            s = new Long(j);
            Long y = s - x;
            if (map.get(y) != null) {
                counter++;
                result.add(s);
            }
        }
        return map;
    }
}
