import java.util.ArrayList;
import java.util.Random;

/**
 * This program implements and demonstrates the heap sort algorithm
 * O(N logN) time
 */

public class HeapSort {

    public static void heapSort(int[] A, int n)
    {
        Heap heap = new Heap(A, n);
        for (int i = 0; i < n; i++)
            A[i] = heap.removeMin();
    }

    public static void printArray(int arr[])
    {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; ++i)
        {
            System.out.print(arr[i] + " ");
            count++;
            if (count % 20 == 0)
                System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        int[] A = new int[500];
        Random rand = new Random();
        for (int i = 0; i < A.length; i++)
            A[i] = rand.nextInt(1000);
        heapSort(A, A.length);
        printArray(A);
    }

}

/**
 * A simple heap class implemented with an array
 */
class Heap
{

    private int[] heap;
    private int size;

    public Heap(int[] A, int n)
    {
        heap = new int[n];
        // initialize the heap
        for (int i = 0; i < n; i++)
            heap[i] = A[i];
        size = n;
        buildMinHeap(size);
    }

    // Utility functions
    private int parent(int i) { return (i-1)/2; }
    private int left(int i) { return 2*i+1; }
    private int right(int i) { return 2*(i+1); }

    /**
     * Min-heapify the element on index i
     */
    private void minHeapify(int i)
    {
        int l = left(i);
        int r = right(i);
        int min = i;
        if (l < size && heap[l] < heap[min])
            min = l;
        if (r < size && heap[r] < heap[min])
            min = r;
        if (min != i)
        {
            int temp = heap[i];
            heap[i] = heap[min];
            heap[min] = temp;
            minHeapify(min);
        }
    }

    /**
     * Build a min heap based on the array A of size n
     * @param n
     */
    private void buildMinHeap(int n)
    {
        for (int j = size/2; j >= 0; j--)
            minHeapify(j);
    }

    /**
     * Return the size of the heap
     * @return the size of the heap
     */
    public int size()
    {
        return size;
    }

    /**
     * Indicate whether the heap is empty
     * @return a boolean value indicating whether the heap is empty
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Return the min value without removing it
     */
    public int min()
    {
        if (size < 1)
            System.err.println("heap overflow");
        return heap[0];
    }

    /**
     * Remove and return the min value
     * @return the min value
     */
    public int removeMin()
    {
        int min = heap[0];
        heap[0] = heap[size-1];
        size--;
        minHeapify(0);
        return min;
    }
}