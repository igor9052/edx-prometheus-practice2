package ua.com.igorka.algorithms101.Task5;

import java.util.Arrays;

/**
 * Created by igor9_000 on 02.03.2015.
 */
public class Heap {

    private int[] heap = new int[0];
    private boolean maxHeap = false;

    public Heap() {
        this(false);
    }


    public Heap(boolean maxHeap) {
        this.maxHeap = maxHeap;
    }

    public int heapSize() {
        return heap.length;
    }

    public boolean isMaxHeap() {
        return maxHeap;
    }

    @Override
    public String toString() {
        return "Heap{" +
                "heap=" + Arrays.toString(heap) +
                '}';
    }

    public int maxElement() {
        if (heapSize() == 0) {
            throw new UnsupportedOperationException("Heap is empty");
        }
        if (isMaxHeap()) {
            return heap[0];
        }
        throw new UnsupportedOperationException("Heap is min heap");
    }

    public int minElement() {
        if (heapSize() == 0) {
            throw new UnsupportedOperationException("Heap is empty");
        }
        if (!isMaxHeap()) {
            return heap[0];
        }
        throw new UnsupportedOperationException("Heap is man heap");
    }

    public int heapExtractMax() {
        int max = maxElement();
        heap = Arrays.copyOfRange(heap, 1, heapSize());
        buildMaxHeap();
        return max;
    }

    public int heapExtractMin() {
        int min = minElement();
        heap = Arrays.copyOfRange(heap, 1, heapSize());
        buildMinHeap();
        return min;
    }

    public void insert(int x) {
        heap = Arrays.copyOf(this.heap, heapSize() + 1);
        heap[heapSize() - 1] = x;
        if (isMaxHeap()) {
            buildMaxHeap();
        } else {
            buildMinHeap();
        }
    }

    public void buildMaxHeap() {
        if (heapSize() == 1) {
            return;
        }
        for (int i = heapSize() / 2; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    private void maxHeapify(int index) {
        if (heapSize() == 1) {
            return;
        }
        int l = left(index);
        int r = right(index);
        int largest;

        if (l < heapSize() && heap[l] > heap[index]) {
            largest = l;
        } else {
            largest = index;
        }
        if (r < heapSize() && heap[r] > heap[largest]) {
            largest = r;
        }
        if (heap[largest] != heap[index]) {
            swap(index, largest);
            maxHeapify(largest);
        }
    }

    public void buildMinHeap() {
        if (heapSize() == 1) {
            return;
        }
        for (int i = heapSize() / 2; i >= 0; i--) {
            minHeapify(i);
        }
    }


    private void minHeapify(int index) {
        if (heapSize() == 1) {
            return;
        }
        int l = left(index);
        int r = right(index);
        int smallest;

        if (l < heapSize() && heap[l] < heap[index]) {
            smallest = l;
        } else {
            smallest = index;
        }
        if (r < heapSize() && heap[r] < heap[smallest]) {
            smallest = r;
        }
        if (heap[smallest] != heap[index]) {
            swap(index, smallest);
            minHeapify(smallest);
        }
    }

    private void swap(int index1, int index2) {
        int temp = heap[index2];
        heap[index2] = heap[index1];
        heap[index1] = temp;
    }

    private int parent(int index) {
        return index / 2;
    }

    private int left(int index) {
        return index * 2;
    }

    private int right(int index) {
        return index * 2 + 1;
    }


}
