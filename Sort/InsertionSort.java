/**
 * This program implements and demonstrated the insertion sort algorithm
 * Theta(N^2) time
 */

public class InsertionSort {

    /**
     * Sorts the array A of size n
     * @param A the array A to be sorted
     * @param n the size of array A
     */
    public void insertionSort(int[] A, int n) {
        for (int i = 1; i < n; i++)
        {
            int key = A[i];
            // Insert A[i] into the sorted subarray A[0 : i-1]
            int j = i - 1;
            while (j >= 0 && A[j] > key)
            {
                A[j+1] = A[j];
                j--;
            }
            A[j+1] = key;
        }
    }

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        InsertionSort is = new InsertionSort();
        int[] n = {5, 2, 3, -1, 8, 0, 9, 10, 9};
        is.insertionSort(n, n.length);
        printArray(n);
    }
}
