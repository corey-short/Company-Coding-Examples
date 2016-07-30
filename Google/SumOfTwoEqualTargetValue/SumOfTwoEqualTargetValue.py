__author__ = 'Corey Short'
__date__ = '7/28/15'


def merge_and_compare(array, target_value):
    """
    Given an array of integers, determine whether or not there exist two elements
    in the array (at different positions) whose sum is equal to some target value
    Ex:
    Input: [5,4,2,4], 8
    Output: True
    Input: [5,1,2,4], 8]
    Output: False
    Input: [5,6,3,5,2,1,0,9,7], 7
    Output: True
    Input: [5,6,3,5,2,1,0,9,7], 20
    Output: False
    """
    left, right = mergesort(array)
    is_sum_equal = compare(left, right, target_value)
    if is_sum_equal:
        return True
    merge(left, right)

    return False


def mergesort(array):
    # Base case: by def, a list of zero or one elements is already sorted
    if len(array) <= 1:
        return array
    left = []
    right = []
    mid = len(array) / 2
    # Recurse and divide our initial array into smaller sub-arrays
    for i in xrange(0, mid):
        left.append(array[i])
    for i in xrange(mid, len(array)):
        right.append(array[i])
    left = mergesort(left)
    right = mergesort(right)
    # Compare our values before we merge them
    return left, right


def compare(left, right, target_value):
    # If left+right sum equals our target_value then we are done
    if left[0] + right[0] == target_value:
        return True
    return False


def merge(left, right):
    print left
    print right

    # Our merge step for our mergesort algorithm
    sorted_array = []
    # Add comment about handling Null case
    for i in xrange(0, len(right)):
        if right[0] <= left[0]:
            sorted_array.append(right.pop(0))
        if left[0] > right[0]:
            sorted_array.append(left.pop(0))


if __name__ == '__main__':
    merge_and_compare([5, 4, 2, 4], 8)
