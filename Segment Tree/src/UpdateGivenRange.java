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

        //construct ST
        constructST(LeafNodes, 0, size - 1, 0);
        
        System.out.print("\n Enter the range to update the tree: ");
        UpRange=SegmentTree.InputElements(UpRange, 2);

        System.out.print("\n Enter the number to be updated: ");
        int num=in.nextInt();
        
        System.out.print("\n Enter the range to compute the sum: ");
        Range=SegmentTree.InputElements(Range, 2);

        System.out.println("\n Sum in the given range before updaing: "+SumofGivenRange.getSum(size, Range.get(0), Range.get(1)));

        updateRange(0,0,size-1, UpRange.get(0), UpRange.get(1), num);

        System.out.println(" Updated sum of values in given range: " +getSum(size,Range.get(0), Range.get(1)));
    }
    static void updateRange(int CurrentIndex, int StartIndex, int EndIndex, int StartIndex_query, int EndIndex_query, int Element)
    {
        // out of range
        if (StartIndex > EndIndex || StartIndex > EndIndex_query || EndIndex < StartIndex_query)
        {
            return;
        }

        // range is in the ST
        if (StartIndex >= StartIndex_query && EndIndex <= EndIndex_query)
        {
            //add the number to the ST
            ST[CurrentIndex] += (EndIndex - StartIndex + 1) * Element;

            // check if the child nodes are present or not
            if (StartIndex != EndIndex)
            {
                UP[CurrentIndex * 2 + 1] += Element;
                UP[CurrentIndex * 2 + 2] += Element;
            }
            return;
        }
      
        // reccuring in the child nodes, if not completly in the range
        int mid = (StartIndex + EndIndex) / 2;
        updateRange(CurrentIndex * 2 + 1, StartIndex, mid, StartIndex_query, EndIndex_query, Element);
        updateRange(CurrentIndex * 2 + 2, mid + 1, EndIndex, StartIndex_query, EndIndex_query, Element);
      
        // And use the result of children calls to update this node
        ST[CurrentIndex] = ST[CurrentIndex * 2 + 1] + ST[CurrentIndex * 2 + 2];
    }
    // Return sum of elements in range from index StartInd_query (query start) to EndInd_query (query end).  It mainly uses getSumUtil()
    static int getSum(int n, int start, int end)
    {
        // such cases never exist
        if (start < 0 || end > n - 1 || start > end)
        {
            System.out.println(" Invalid Input");
            return -1;
        }
        return getSumUtil(0, n - 1, start, end, 0);     // to get the sum of values in given range of the array
    }
    static void CheckUpdates(int CurrentIndex, int StartIndex, int EndIndex, int StartIndex_query, int EndIndex_query)
    {
        //checks if the UP array have any updates in the current node
        if (UP[CurrentIndex] != 0)
        {
            // the update in the UP array is added to the current node in ST
            ST[CurrentIndex] += (EndIndex - StartIndex + 1) * UP[CurrentIndex];
    
            // The updates in the child nodes are added to UP array
            if (StartIndex != EndIndex)
            {
                UP[CurrentIndex * 2 + 1] += UP[CurrentIndex];
                UP[CurrentIndex * 2 + 2] += UP[CurrentIndex];
            }
            // the current Index in the UP array is set to 0
            UP[CurrentIndex] = 0;
        }
    }
    static int getSumUtil(int StartInd_seg, int EndInd_seg, int StartInd_query, int EndInd_query, int CurrentIndex) 
    {
        CheckUpdates(CurrentIndex, StartInd_seg, EndInd_seg, StartInd_query, EndInd_query);

        // Out of range
        if (StartInd_seg > EndInd_seg || StartInd_seg > EndInd_query || EndInd_seg < StartInd_query)
        {
            return 0;
        }

        // if the renge is within the ST
        if (StartInd_seg >= StartInd_query && EndInd_seg <= EndInd_query)
        {
            return ST[CurrentIndex];
        }
        // If a part of this segment overlaps with the given range
        int mid = (StartInd_seg + EndInd_seg) / 2;
            
        return getSumUtil(StartInd_seg, mid, StartInd_query, EndInd_query, 2 * CurrentIndex + 1) + getSumUtil(mid + 1, EndInd_seg, StartInd_query, EndInd_query, 2 * CurrentIndex + 2);
    }
    static void constructST(ArrayList<Integer> LeafNodes, int StartIndex, int EndIndex, int CurrentIndex)
    {
        // out of range 
        if (StartIndex > EndIndex)
        {
            return;
        }
        // store it in current node of segment tree and return 
        if (StartIndex == EndIndex)
        {
            ST[CurrentIndex] = LeafNodes.get(StartIndex);
            return;
        }
        //If there are more than one elements, then recur for left and right subtrees and store the sum of values in this node
        int mid = (StartIndex + EndIndex) / 2;
        constructST(LeafNodes, StartIndex, mid, CurrentIndex * 2 + 1);
        constructST(LeafNodes, mid + 1, EndIndex, CurrentIndex * 2 + 2);
      
        ST[CurrentIndex] = ST[CurrentIndex * 2 + 1] + ST[CurrentIndex * 2 + 2];
    }
}
