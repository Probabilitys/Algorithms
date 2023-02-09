/**
 * This program implements and demonstrated the selection sort algorithm
 * O(N^2) time
 */

public class SelectionSort {

    /**
     * Sorts the array A of size n
     * @param A the array A to be sorted
     * @param n the size of array A
     */
    public void selectionSort(int[] A, int n) {
        for (int i = 0; i < n; i++) {
            int min_index = i;
            // find the min value in the subarray A[i : n]
            for (int j = i; j < n; j++)
                if (A[j] < A[min_index])
                    min_index = j;

            // swap A[i] and the min value in the subarray A[i : n]
            int temp = A[i];
            A[i] = A[min_index];
            A[min_index] = temp;
        }
    }

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        SelectionSort ss = new SelectionSort();
        int[] n = {5, 100, 2, 3, -1, 8, 0, 9, 10, 9};
        ss.selectionSort(n, n.length);
        printArray(n);
    }

}
