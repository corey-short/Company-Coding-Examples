__author__ = 'short'


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

if __name__ == '__main__':
    test_list = [5, 4, 2, 4]
    print test_list
    merge_sort_test = mergesort(test_list)
    print merge_sort_test

    test_list = [4, 2, 4]
    print test_list
    merge_sort_test = mergesort(test_list)
    print merge_sort_test

