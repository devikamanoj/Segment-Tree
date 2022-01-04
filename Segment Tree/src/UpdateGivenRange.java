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
    static void updateRangeUtil(int CurrentIndex, int StartIndex, int EndIndex, int StartIndex_query, int EndIndex_query, int Element)
    {
        if (UP[CurrentIndex] != 0)
        {
            ST[CurrentIndex] += (EndIndex - StartIndex + 1) * UP[CurrentIndex];
    
            if (StartIndex != EndIndex)
            {
                UP[CurrentIndex * 2 + 1] += UP[CurrentIndex];
                UP[CurrentIndex * 2 + 2] += UP[CurrentIndex];
            }
      
            UP[CurrentIndex] = 0;
        }
        // out of range
        if (StartIndex > EndIndex || StartIndex > EndIndex_query || EndIndex < StartIndex_query)
        {
            return;
        }
        // Current segment is fully in range
        if (StartIndex >= StartIndex_query && EndIndex <= EndIndex_query)
        {
            // Add the difference to current node
            ST[CurrentIndex] += (EndIndex - StartIndex + 1) * Element;
      
            // same logic for checking leaf node or not
            if (StartIndex != EndIndex)
            {
                UP[CurrentIndex * 2 + 1] += Element;
                UP[CurrentIndex * 2 + 2] += Element;
            }
            return;
        }
      
        // If not completely in rang, but overlaps, recur for children,
        int mid = (StartIndex + EndIndex) / 2;
        updateRangeUtil(CurrentIndex * 2 + 1, StartIndex, mid, StartIndex_query, EndIndex_query, Element);
        updateRangeUtil(CurrentIndex * 2 + 2, mid + 1, EndIndex, StartIndex_query, EndIndex_query, Element);
      
        // And use the result of children calls to update this node
        ST[CurrentIndex] = ST[CurrentIndex * 2 + 1] + ST[CurrentIndex * 2 + 2];
    }
      
    // Function to update a range of values in segmenttree
    static void updateRange(int n, int StartIndex_query, int EndIndex_query, int Element)  
    {
        updateRangeUtil(0, 0, n - 1, StartIndex_query, EndIndex_query, Element);
    } 
    static void constructSTUtil(ArrayList<Integer> LeafNodes, int StartIndex, int EndIndex, int CurrentIndex)
    {
        // out of range as StartIndex can never be greater than EndIndex
        if (StartIndex > EndIndex)
        {
            return;
        }
      
        /* If there is one element in array, store it in current node of segment tree and return */
        if (StartIndex == EndIndex)
        {
            ST[CurrentIndex] = LeafNodes.get(StartIndex);
            return;
        }
        int mid = (StartIndex + EndIndex) / 2;
        constructSTUtil(LeafNodes, StartIndex, mid, CurrentIndex * 2 + 1);
        constructSTUtil(LeafNodes, mid + 1, EndIndex, CurrentIndex * 2 + 2);
      
        ST[CurrentIndex] = ST[CurrentIndex * 2 + 1] + ST[CurrentIndex * 2 + 2];
    }
    static void constructST(ArrayList<Integer> LeafNodes, int size)
    {
        // Fill the allocated memory st
        constructSTUtil(LeafNodes, 0, size - 1, 0);
    } 
}
