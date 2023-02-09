/**
 * This program implements and demonstrated the bubble sort algorithm
 * O(N^2) time
 */

public class BubbleSort {

    /**
     * Sorts the array A of size n
     * @param A the array A to be sorted
     * @param n the size of the array A
     */
    public void bubbleSort(int A[], int n)
    {
        for (int i = 0; i < n; i++)
            for (int j = n-1; j > i; j--)
                if (A[j] < A[j-1])
                {
                    int temp = A[j];
                    A[j] = A[j-1];
                    A[j-1] = temp;
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
        BubbleSort bs = new BubbleSort();
        int[] n = {5, 3, 8, 0, 9, -1, 10, 9, 8};
        bs.bubbleSort(n, n.length);
        printArray(n);
    }
}
