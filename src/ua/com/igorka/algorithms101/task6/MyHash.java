package ua.com.igorka.algorithms101.task6;

import java.io.*;
import java.util.*;

/**
 * Created by igor9_000 on 07.03.2015.
 */
public class MyHash {

    public Map<Long,Long> getData() {

        Map<Long, Long> longList = new LinkedHashMap<Long, Long>();
        String line;
        Long i =  new Long(1);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("H:\\users\\igor\\Temp\\input_06.txt")));
            while ((line = reader.readLine()) != null) {
                longList.put(Long.parseLong(line), i);
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return longList;
    }
}
