package com.coreyshort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A brute-force substring search algorithm that checks all positions.
 * Created by Corey Short on 8/4/15.
 */
public class NaiveSearch {

    public static String searchSubSequence(String S, List<String> T) {
        List<String> list = new ArrayList<String>(Arrays.asList(S.split("\\W")));
        System.out.println(list);

        final int n = list.size();
        final int m = T.size();

        StringBuilder result = new StringBuilder(n);

        for (int i = 0; i < n-m; i++) {
            for (int j = 0; j < m; j++) {
                if (list.get(i+j).equals(T.get(j))) {
                    System.out.println("list :" + list.get(i+j));
                    System.out.println("pattern :" + T.get(j));
                    result.append(list.get(i+j)).append(" ");
                    break;
                }
            }
            //result.append(list.get(i));
            //System.out.println("result :" + result);
            //return result.toString();
        }
        return result.toString();
    }

}