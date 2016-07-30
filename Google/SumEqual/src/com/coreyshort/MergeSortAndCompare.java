package com.coreyshort;

import java.util.ArrayList;
import java.util.List;

/**
 * A top-down merge sort class that compares its elements before merging them.
 * If the two elements sum is equal to the target value specified
 * then return true. Otherwise false.
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

}
