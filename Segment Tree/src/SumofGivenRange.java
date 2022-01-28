import java.util.*;
public class SumofGivenRange 
{
    static int [] ST=UpdateGivenRange.ST;
    static int [] UP=UpdateGivenRange.UP;
    static Scanner in = new Scanner(System.in);
    
    static void Sum(ArrayList<Integer> LeafNodes,int size)
    {
        ArrayList <Integer> Range = new ArrayList<Integer>();

        UpdateGivenRange.constructSTUtil(LeafNodes, 0, size - 1, 0);

        System.out.print("\n Enter the range to compute the sum: ");
        Range=SegmentTree.InputElements(Range, 2);
  
        System.out.println(" Sum of values in given range = " +getSum(size, Range.get(0), Range.get(1)));
    }
    static int getSum(int size, int start, int end)
    {
        // Check for erroneous input values
        if (start < 0 || end > size - 1 || start > end)
        {
            System.out.println(" Invalid Input");
            return -1;
        }
      
        return getSumUtil(0, size - 1, start, end, 0);
    }
    // to get the sum of values in given range of the array
    static int getSumUtil(int StartInd_seg, int EndInd_seg, int StartInd_query, int EndInd_query, int CurrentIndex) 
    {
        // If segment of this node is a part of given range, then return  the sum of the segment
        if (StartInd_query <= StartInd_seg && EndInd_query >= EndInd_seg)
        {
            return ST[CurrentIndex];
        }
        // If segment of this node is outside the given range
        if (EndInd_seg < StartInd_query || StartInd_seg > EndInd_query)
        {
            return 0;
        }

        // If a part of this segment overlaps with the given range
        int mid = StartInd_seg+ (EndInd_seg-StartInd_seg)/2;
        return getSumUtil(StartInd_seg, mid, StartInd_query, EndInd_query, 2 * CurrentIndex + 1) + getSumUtil(mid + 1, EndInd_seg, StartInd_query, EndInd_query, 2 * CurrentIndex + 2);
    }
}
