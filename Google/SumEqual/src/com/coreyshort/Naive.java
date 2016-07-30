int[] a;
int n;
// Naive
for (int i= 0; i < a.length; i++) {
    for (int j = i + 1; j < a.length; j++) {
        if (i != j && a[i] + a[j] == n) {
            return true;
        }
    }
}

// Not sure
a = sort(a);
int left = 0;
int right = a.length-1;
while (left != right) {
    if (a[left] + a[right} > n) {
        right--;
    }
    if else (a[left] + a[right]) {
            left++;
    } else {
        return true;
    }
}

// TreeSet
TreeSet<Integer> existVal; //nlogn
int[] a;
int expectSum;

for (int i = 0; i< a[i];i ++) {
    if (existVal.contains(expectSum - a[i])) {
        return true;
    } else {
    existingValues.add(a[i]);
    }
    return false;

// linear if n not too big. This is memory constrained. How much?
boolean[] isExist = new boolean[n]; // initialize with false
for (int i=0; i < a.length; i++) {
    if (a[i] < n) {
        isExist[a[i]] = true;
    }
}
for (int i =0; i < a.length; i++) {
    if (isExist[n-a[i]])
        return true;
}
return false;
