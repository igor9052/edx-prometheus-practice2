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
        heap[0] = heap[heapSize() - 1];
        heap = Arrays.copyOfRange(heap, 0, heapSize() - 1);
        maxHeapify(0);
        return max;
    }

    public int heapExtractMin() {
        int min = minElement();
        heap[0] = heap[heapSize() - 1];
        heap = Arrays.copyOfRange(heap, 0, heapSize() - 1);
        minHeapify(0);
        return min;
    }

    public void insert(int x) {
        if (isMaxHeap()) {
            maxHeapInsert(x);
        } else {
            minHeapInsert(x);
        }
    }

    private void maxHeapInsert(int x) {
        heap = Arrays.copyOf(heap, heap.length + 1);
        int i = heapSize() - 1;
        heap[i] = x;
        while (i > 0 && heap[parent(i)] < heap[i]){
            swap(parent(i), i);
            i = parent(i);
        }
    }

    private void minHeapInsert(int x) {
        heap = Arrays.copyOf(heap, heap.length + 1);
        int i = heapSize() - 1;
        heap[i] = x;
        while (i > 0 && heap[parent(i)] > heap[i]){
            swap(parent(i), i);
            i = parent(i);
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
        return (index - 1) / 2;
    }

    private int left(int index) {
        if (index == 0) {
            return 1;
        }
        return index * 2 + 1;
    }

    private int right(int index) {
        if (index == 0) {
            return 2;
        }
        return index * 2 + 2;
    }


}
