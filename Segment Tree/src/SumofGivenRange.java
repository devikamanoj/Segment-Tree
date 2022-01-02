import java.util.*;
public class SumofGivenRange 
{
    static int [] ST=UpdateGivenRange.ST;
    static int [] UP=UpdateGivenRange.UP;
    static Scanner in = new Scanner(System.in);
    
    static void Sum(ArrayList<Integer> LeafNodes,int size)
    {
        ArrayList <Integer> Range = new ArrayList<Integer>();

        UpdateGivenRange.constructST(LeafNodes, size);

        System.out.print("\n Enter the range to compute the sum: ");
        Range=SegmentTree.InputElements(Range, 2);
  
        System.out.println(" Sum of values in given range = " +getSum(size, Range.get(0), Range.get(1)));
    }
    // Return sum of elements in range from index qs (query start) to qe (query end).  It mainly uses getSumUtil()
    static int getSum(int n, int start, int end)
    {
        // Check for erroneous input values
        if (start < 0 || end > n - 1 || start > end)
        {
            System.out.println(" Invalid Input");
            return -1;
        }
      
        return getSumUtil(0, n - 1, start, end, 0);
    }
    /*  A recursive function to get the sum of values in given range of the array. The following are parameters for this function.
        si --> Index of current node in the segment tree.
            Initially 0 is passed as root is always at'
            index 0
        ss & se  --> Starting and ending indexes of the
            segment represented by current node,
            i.e., tree[si]
        qs & qe  --> Starting and ending indexes of query
            range */
    static int getSumUtil(int ss, int se, int qs, int qe, int si)
    {
        // If lazy flag is set for current node of segment tree, then there are some pending updates. So we need to
        // make sure that the pending updates are done before processing the sub sum query
        if (UP[si] != 0)
        {
            // Make pending updates to this node. Note that this node represents sum of elements in arr[ss..se] and
            // all these elements must be increased by lazy[si]
            ST[si] += (se - ss + 1) * UP[si];
              
            // checking if it is not leaf node because if it is leaf node then we cannot go further
            if (ss != se)
            {
                // Since we are not yet updating children os si, we need to set lazy values for the children
                UP[si * 2 + 1] += UP[si];
                UP[si * 2 + 2] += UP[si];
            }
            // unset the lazy value for current node as it has been updated
            UP[si] = 0;
        }
        // Out of range
        if (ss > se || ss > qe || se < qs)
        {
            System.out.println(" !!! Out Of Range !!!");
            return 0;
        }
        // At this point sure, pending lazy updates are done for current node. So we can return value
        // If this segment lies in range
        if (ss >= qs && se <= qe)
        {
            return ST[si];
        }
        // If a part of this segment overlaps with the given range
        int mid = (ss + se) / 2;
        
        return getSumUtil(ss, mid, qs, qe, 2 * si + 1) + getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }
}
