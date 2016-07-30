package com.coreyshort;

import java.util.List;

/**
 * A lightweight class to return a List<Integer> or boolean for our MergeSortAndCompare class.
 * Created by Corey Short on 8/3/15.
 */
public class ReturningValues {

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
