/**
 * A max heap data structure
 * Created by Corey Short on 8/1/15.
 */
public class MaxHeap {

    private int[] Heap;
    private int size;
    private int maxsize;
    private static int FRONT = 1;

    /* MaxHeap constructor */
    public MaxHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE;
    }

    private int parent(int pos) { return pos / 2; }

    private int leftChild(int pos) { return (2 * pos); }

    private int rightChild(int pos) { return (2 * pos) + 1; }

    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }

    private void swap(int fpos, int spos) {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private void maxHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (Heap[pos] < Heap[leftChild(pos)] || Heap[pos] < Heap[rightChild(pos)]) {
                if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                }
                else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }

    public void insert(int elem) {
        Heap[++size] = elem;
        int current = size;

        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void print() {
        for (int i = 1; i <= size; i++) {
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2*i]
                            + " RIGHT CHILD :" + Heap[2*1+1]);
            System.out.println();
        }
    }


}
