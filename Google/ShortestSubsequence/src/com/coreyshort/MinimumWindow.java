package com.coreyshort;

import java.util.*;

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
 * Created by Corey Short on 8/6/15.
 */
public class MinimumWindow {

    public static WindowObj getMinimumWindow(String[] s, String[] t) {

        WindowObj minWindow = null;

        /* LinkedHashSet for querying words in t in O(1) while preserving insertion order */
        LinkedHashSet<String> querySet = new LinkedHashSet<String>();
        Collections.addAll(querySet, t);

        /* TreeMap to keep track of where we have seen words & their index */
        TreeMap<String, Integer> where = new TreeMap<String, Integer>();

        /* TreeSet keeps the indexes sorted */
        TreeSet<Integer> indexInWindow = new TreeSet<Integer>();

        for (int i = 0; i < s.length; i++) {
            if (querySet.contains(s[i])) {
                /* If we have already seen this word */
                if (where.containsKey(s[i])) {
                    /* Remove the word's old index */
                    indexInWindow.remove(where.get(s[i]));
                }
                /* Update/Insert a word's index */
                if (querySet.toArray()[0].equals(s[i])) {
                    where.put(s[i], i);
                    indexInWindow.add(i);
                }
                if (indexInWindow.size() == 1 && querySet.toArray()[querySet.size()-1].equals(s[i])) {
                    final int index = where.get(s[indexInWindow.first()]);
                    for (int j = index; j < s.length; j++) {
                        if (s[j].equals("them")) {
                            where.put(s[i], i);
                            indexInWindow.add(i);
                            break;
                        }
                    }
                }
                /* We have seen the first and last words from our query */
                if (indexInWindow.size() == 2) {
                    /* Our window edges */
                    int firstIndex = indexInWindow.first();
                    int lastIndex = indexInWindow.last();
                    WindowObj currentWindow = new WindowObj(s, firstIndex, lastIndex);
                    /* The first window we have seen */
                    if (minWindow == null) {
                        minWindow = currentWindow;
                    } else {
                        if (minWindow.compareTo(currentWindow) > 0) {
                            /* The currentWindow is smaller than our minWindow */
                            minWindow = currentWindow;
                        }
                    }
                }
            }
        }
        return minWindow;
    }

    private static class WindowObj implements Comparable<WindowObj> {

        private String[] window;

        private WindowObj(String[] s, int firstIndex, int lastIndex) {
            this.window = Arrays.copyOfRange(s, firstIndex, lastIndex + 1);
        }

        public String[] getWindow() {
            return this.window;
        }

        @Override
        public int compareTo(WindowObj w) {
            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            if (this.window.length < w.getWindow().length) {
                return BEFORE;
            }
            if (this.window.length > w.getWindow().length) {
                return AFTER;
            }
            assert this.equals(w) : "compareTo inconsistent with equals";
            return EQUAL;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof WindowObj)) {
                return false;
            }
            WindowObj w = (WindowObj) obj;
            return this.window.length == w.getWindow().length;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(this.window);
        }

        @Override
        public String toString() {
            // TODO: Add delimiters
            StringBuilder sb = new StringBuilder(this.window.length);
            for (String aWindow : this.window) {
                sb.append(aWindow).append(" ");
            }
            return sb.toString().trim();
        }
    }

    public static void main(String[] args) {

        /* Input 1 */
        String S = "One Ring to rule them all, One Ring to find them, " +
                "One Ring to bring them all and in the darkness bind them";

        String[] s = S.split("\\W");
        String[] t = new String[]{"find", "them", "all"};

        WindowObj wo = MinimumWindow.getMinimumWindow(s, t);

        if (wo == null) {
            System.out.println("No minimum continuous subsequence found.");
        } else {
            System.out.println("Shortest continuous subsequence found: " + wo);
        }

        /* This should not work */
        String[] s2 = new String[]{"ADOBECODEBANC"};
        String[] t2 = new String[]{"ABC"};

        WindowObj wo2 = MinimumWindow.getMinimumWindow(s2, t2);

        if (wo2 == null) {
            System.out.println("No minimum continuous subsequence found.");
        } else {
            System.out.println("Shortest continuous subsequence found: " + wo2);
        }

        /* Input 3 */
        String S3 = "One Ring to rule them all, One Ring to find them, " +
                "One Ring to bring them all and in the darkness bind them";

        String[] s3 = S.split("\\W");
        String[] t3 = new String[]{"rule", "find", "all"};

        WindowObj wo3 = MinimumWindow.getMinimumWindow(s3, t3);

        if (wo == null) {
            System.out.println("No minimum continuous subsequence found.");
        } else {
            System.out.println("Shortest continuous subsequence found: " + wo3);
        }
    }

}
