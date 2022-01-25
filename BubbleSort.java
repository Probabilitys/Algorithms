public class BubbleSort {

    /** implement bubble sort using sequence */
    public static void bubbleSort(NodeSequence<Integer> s) 
        throws BoundaryViolationException, InvalidPositionException, 
        EmptyListException {

        int size = s.size();
        boolean sorted = false;  // indicates whether the sequence is sorted
        while (!sorted) {
            sorted = true;  
            Position<Integer> left = s.first();
            Position<Integer> right = s.after(left);

            for (int i = 0; i < size - 1; i++) {
                int a = left.getElement();
                int b = right.getElement();
                if (a > b) {
                    // when a swap happens, the sequence is marked unsorted
                    sorted = false;
                    // swap two elements
                    int temp = a;
                    s.set(left, b);
                    s.set(right, temp);
                }
                left = s.after(left);
                right = s.after(right);            
            }  // end for
        }  // end while
    }
    /** end sequence bubble sort */
}
