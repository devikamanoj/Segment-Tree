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
    // Return sum of elements in range from index StartInd_query (query start) to EndInd_query (query end).  It mainly uses getSumUtil()
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
    // to get the sum of values in given range of the array
    static int getSumUtil(int StartInd_seg, int EndInd_seg, int StartInd_query, int EndInd_query, int CurrentIndex) 
    {
        // If ST flag is set for current node of segment tree, then there are some pending updates. So we need to
        // make sure that the pending updates are done before processing the sub sum query
        if (UP[CurrentIndex] != 0)
        {
            // Make pending updates to this node. Note that this node represents sum of elements in arr[ss..se] and
            // all these elements must be increased by ST[CurrentIndex]
            ST[CurrentIndex] += (EndInd_seg - StartInd_seg + 1) * UP[CurrentIndex];
              
            // checking if it is not leaf node because if it is leaf node then we cannot go further
            if (StartInd_seg != EndInd_seg)
            {
                // Since we are not yet updating children of CurrentIndex, we need to set ST values for the children
                UP[CurrentIndex * 2 + 1] += UP[CurrentIndex];
                UP[CurrentIndex * 2 + 2] += UP[CurrentIndex];
            }
            // unset the ST value for current node as it has been updated
            UP[CurrentIndex] = 0;
        }
        // Out of range
        if (StartInd_seg > EndInd_seg || StartInd_seg > EndInd_query || EndInd_seg < StartInd_query)
        {
            System.out.println(" !!! Out Of Range !!!");
            return 0;
        }
        // Pending ST updates are done for current node. So we can return value,if this segment lies in range
        if (StartInd_seg >= StartInd_query && EndInd_seg <= EndInd_query)
        {
            return ST[CurrentIndex];
        }
        // If a part of this segment overlaps with the given range
        int mid = (StartInd_seg + EndInd_seg) / 2;
        
        return getSumUtil(StartInd_seg, mid, StartInd_query, EndInd_query, 2 * CurrentIndex + 1) + getSumUtil(mid + 1, EndInd_seg, StartInd_query, EndInd_query, 2 * CurrentIndex + 2);
    }
}
