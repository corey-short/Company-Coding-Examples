package com.coreyshort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    /**
     * Given an array of integers, determine whether or not there exist two elements
     * in the array (at different positions) whose sum is equal to some target value
     * Ex:
     * Input: [5,4,2,4], 8
     * Output: True
     * Input: [5,1,2,4], 8]
     * Output: False
     * Input: [5,6,3,5,2,1,0,9,7], 7
     * Output: True
     * Input: [5,6,3,5,2,1,0,9,7], 20
     * Output: False
     *
     * Created by Corey Short on 8/2/15.
     */
    public static void main(String[] args) {
        // TODO: Test base case
        /* Polymorphism. Useful if we want to change out implementation of list in the future */
        final List<Integer> list = new ArrayList<Integer>(Arrays.asList(5,4,2,4));
        final Integer targetVal = 8;

        ReturningValues rv = MergeSortAndCompare.sort(list, targetVal);
        if (rv.getIsSumEqlToTargetVal()) {
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }

        final List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(5,1,2,4));
        final Integer targetVal2 = 8;

        ReturningValues rv2 = MergeSortAndCompare.sort(list2, targetVal2);
        if (rv2.getIsSumEqlToTargetVal()) {
            System.out.println("True");
        }
        else {
            System.out.println("False: " + rv2.getList() + ", " + targetVal2);
        }

        final List<Integer> list3 = new ArrayList<Integer>(Arrays.asList(5,6,3,5,2,1,0,9,7));
        final Integer targetVal3 = 7;

        ReturningValues rv3 = MergeSortAndCompare.sort(list3, targetVal3);
        if (rv3.getIsSumEqlToTargetVal()) {
            System.out.println("True");
        }
        else {
            System.out.println("False: " + rv3.getList() + ", " + targetVal3);
        }

        final List<Integer> list4 = new ArrayList<Integer>(Arrays.asList(5,6,3,5,2,1,0,9,7));
        final Integer targetVal4 = 20;

        ReturningValues rv4 = MergeSortAndCompare.sort(list4, targetVal4);
        if (rv4.getIsSumEqlToTargetVal()) {
            System.out.println("True");
        }
        else {
            System.out.println("False: " + rv4.getList() + ", " + targetVal4);
        }

    }
}
