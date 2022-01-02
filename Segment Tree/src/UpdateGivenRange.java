import java.util.*;
public class UpdateGivenRange
{       
    static int ST[] = new int[1000];  // To store segment tree
    static int UP[] = new int[1000];  // To store pending updates
    static Scanner in = new Scanner(System.in);
    static int size;
    
    static void update(ArrayList <Integer> LeafNodes, int size )
    {
        ArrayList <Integer> Range = new ArrayList<Integer>();
        ArrayList <Integer> UpRange = new ArrayList<Integer>();

        constructST(LeafNodes, size);
        
        System.out.print("\n Enter the range to update the tree: ");
        UpRange=SegmentTree.InputElements(UpRange, 2);
        System.out.print("\n Enter the number to be updated: ");
        int num=in.nextInt();
        updateRange(size, UpRange.get(0), UpRange.get(1), num);
        System.out.print("\n Enter the range to compute the sum: ");
        Range=SegmentTree.InputElements(Range, 2);
  
        System.out.println("Updated sum of values in given range = " +SumofGivenRange.getSum(size,Range.get(0), Range.get(1)));
    }
    /*  si -> index of current node in segment tree
        ss and se -> Starting and ending indexes of elements for which current nodes stores sum.
        us and eu -> starting and ending indexes of update query
        ue  -> ending index of update query
        diff -> which we need to add in the range us to ue */
    static void updateRangeUtil(int si, int ss, int se, int us, int ue, int diff)
    {
        // If lazy value is non-zero for current node of segment tree, then there are some pending updates. So we need
        // to make sure that the pending updates are done before making new updates. Because this value may be used by
        // parent after recursive calls (See last line of this function)
        if (UP[si] != 0)
        {
            // Make pending updates using value stored in lazy nodes
            ST[si] += (se - ss + 1) * UP[si];
      
            // checking if it is not leaf node because if it is leaf node then we cannot go further
            if (ss != se)
            {
                // We can postpone updating children we don't need their new values now.
                // Since we are not yet updating children of si, we need to set lazy flags for the children
                UP[si * 2 + 1] += UP[si];
                UP[si * 2 + 2] += UP[si];
            }
      
            // Set the lazy value for current node as 0 as it has been updated
                UP[si] = 0;
        }
        // out of range
        if (ss > se || ss > ue || se < us)
        {
            return;
        }
        // Current segment is fully in range
        if (ss >= us && se <= ue)
        {
            // Add the difference to current node
            ST[si] += (se - ss + 1) * diff;
      
            // same logic for checking leaf node or not
            if (ss != se)
            {
                // This is where we store values in lazy nodes,  rather than updating the segment tree itelf
                // Since we don't need these updated values now we postpone updates by storing values in lazy[]
                UP[si * 2 + 1] += diff;
                UP[si * 2 + 2] += diff;
            }
            return;
        }
      
        // If not completely in rang, but overlaps, recur for children,
        int mid = (ss + se) / 2;
        updateRangeUtil(si * 2 + 1, ss, mid, us, ue, diff);
        updateRangeUtil(si * 2 + 2, mid + 1, se, us, ue, diff);
      
        // And use the result of children calls to update this node
        ST[si] = ST[si * 2 + 1] + ST[si * 2 + 2];
    }
      
    // Function to update a range of values in segmenttree
    /*  us and eu -> starting and ending indexes of update query
        ue  -> ending index of update query
        diff -> which we need to add in the range us to ue */
    static void updateRange(int n, int us, int ue, int diff)  
    {
        updateRangeUtil(0, 0, n - 1, us, ue, diff);
    } 
    /* A recursive function that constructs Segment Tree for array[ss..se]. si is index of current node in segment tree st. */
    static void constructSTUtil(ArrayList<Integer> LeafNodes, int ss, int se, int si)
    {
        // out of range as ss can never be greater than se
        if (ss > se)
        {
            return;
        }
      
        /* If there is one element in array, store it in current node of segment tree and return */
        if (ss == se)
        {
            ST[si] = LeafNodes.get(ss);
            return;
        }
      
        /* If there are more than one elements, then recur for left and right subtrees and store the sum of values in this node */
        int mid = (ss + se) / 2;
        constructSTUtil(LeafNodes, ss, mid, si * 2 + 1);
        constructSTUtil(LeafNodes, mid + 1, se, si * 2 + 2);
      
        ST[si] = ST[si * 2 + 1] + ST[si * 2 + 2];
    }
      
    /* Function to construct segment tree from given array. This function allocates memory for segment tree and
    calls constructSTUtil() to fill the allocated memory */
    static void constructST(ArrayList<Integer> LeafNodes, int size)
    {
            // Fill the allocated memory st
        constructSTUtil(LeafNodes, 0, size - 1, 0);
    } 
}
