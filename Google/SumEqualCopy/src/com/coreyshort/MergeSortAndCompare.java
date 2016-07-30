package com.coreyshort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A top-down merge sort class that compares its elements before merging them.
 * If the two elements sum is equal to the target value specified
 * then return true. Otherwise false.
 *
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
public class MergeSortAndCompare {

    /* Sort our list into smaller sub-lists */
    public static ReturningValues sort(List<Integer> list, Integer targetVal) {
        /* Base case: by def, a list of zero or one elements is already sorted */
        if (list.size() <= 1) {
            return new ReturningValues(list);
        }

        /* Our left and right lists with a boolean flag for our compare method */
        ReturningValues left = new ReturningValues(new ArrayList<Integer>());
        ReturningValues right = new ReturningValues(new ArrayList<Integer>());
        final Integer mid = list.size() / 2;

        /* Recurse and divide our initial list into smaller sub-lists */
        for (int i = 0; i < mid; i++) {
            left.getList().add(list.get(i));
        }
        for (int i = mid; i <= list.size() - 1; i++) {
            right.getList().add(list.get(i));
        }
        left = sort(left.getList(), targetVal);
        right = sort(right.getList(), targetVal);

        /* Merge the sorted sub-lists */
        return merge(left, right, targetVal);
    }

    /**
     * Our merge method for MergeSort.
     * We compare the left and right sub-lists to our target value before the merge step
     */
    private static ReturningValues merge(ReturningValues left, ReturningValues right, Integer targetVal) {
        /* The sorted list we will return and compare values  */
        ReturningValues sortedList = new ReturningValues(new ArrayList<Integer>());
        /* left and right both non-empty */
        while (!left.getList().isEmpty() && !right.getList().isEmpty()) {
            final Boolean isSumEqual = compare(left.getList(), right.getList(), targetVal);
            if (isSumEqual) {
                sortedList.setIsSumEqlToTargetVal(true);
                //return sortedList;
            }
            if (left.getList().get(0) <= right.getList().get(0)) {
                sortedList.getList().add(left.getList().remove(0));
            } else {
                sortedList.getList().add(right.getList().remove(0));
            }
        }
        /* Either left or right may have elements left */
        while (!left.getList().isEmpty()) {
            sortedList.getList().add(left.getList().remove(0));
        }
        while (!right.getList().isEmpty()) {
            sortedList.getList().add(right.getList().remove(0));
        }
        return sortedList;
    }

    /**
     * Compare our left and right values to the given target value.
     * Set our boolean flag to true if their sum equal
     */
    private static boolean compare(List<Integer> left, List<Integer> right, Integer targetVal) {
        return left.get(0) + right.get(0) == targetVal;
    }
    /**
     * A lightweight class to return a List<Integer> or boolean for our MergeSortAndCompare class.
     * Created by Corey Short on 8/3/15.
     */
    private static class ReturningValues {

        private List<Integer> list;
        private Boolean isSumEqlToTargetVal;

        public ReturningValues(List<Integer> list) {
            this.list = list;
            this.isSumEqlToTargetVal = false;
        }

        public List<Integer> getList() {
            return list;
        }

        public Boolean getIsSumEqlToTargetVal() {
            return isSumEqlToTargetVal;
        }

        public void setList(List<Integer> l) {
            this.list = l;
        }

        public void setIsSumEqlToTargetVal(Boolean bool) {
            this.isSumEqlToTargetVal = bool;
        }
    }

    public static void main(String[] args) {
        // TODO: Test base case
    /* Polymorphism. Useful if we want to change out implementation of list in the future */
        final List<Integer> list = new ArrayList<Integer>(Arrays.asList(5, 4, 2, 4));
        final Integer targetVal = 8;

        ReturningValues rv = MergeSortAndCompare.sort(list, targetVal);
        if (rv.getIsSumEqlToTargetVal()) {
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }

        final List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(1,3,5,7,11,13));
        final Integer targetVal2 = 16;

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
