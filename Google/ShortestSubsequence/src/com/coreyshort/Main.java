package com.coreyshort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a sequence of words S and a sequence of words T.
 * Find the shortest continuous sub-sequence of words in S such that
 * the words in T appear in that order. Handling of capitalization,
 * punctuation, tie-breaking, etc are all optional.
 * Ex:
 * Input: "One Ring to rule them all, One Ring to find them," +
 *        "One Ring to bring them all and in the darkness bind them.", ["find","them","all"]
 *
 * Output: "find them, One Ring to bring them all"
 *
 * Created by Corey Short on 8/4/15.
 */
public class Main {

    public static void main(String[] args) {
        //TODO: Stringbuilder
        String S = "One Ring to rule them all, One Ring to find them, " +
                "One Ring to bring them all and in the darkness bind them.";

        List<String> T = new ArrayList<String>(Arrays.asList("find","them","all"));

        System.out.println(S);
        System.out.println(T);

        String result = NaiveSearch.searchSubSequence(S, T);
        System.out.println(result);
    }
}
