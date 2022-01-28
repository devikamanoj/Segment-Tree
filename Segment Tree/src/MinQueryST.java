import java.util.ArrayList;
import java.util.Scanner;
public class MinQueryST
{
	static int st[];
	// To get the middle index
	static int getMid(int s, int e) 
	{
		int mid;
		mid= s + (e - s) / 2;
		return mid;
	}
	
	//To get minimum in the given range
	static int RMQUtilFunc(int SI, int EI, int SQ, int EQ, int index)
	{
		//If completely within range, return current value
		if(SQ<=SI && EQ>= EI)
			return st[index];
		
		if(EI<SQ || SI>EQ) // out of bound, returns infinity 
			return Integer.MAX_VALUE;
		
		int mid=getMid(SI,EI);
		return Math.min(RMQUtilFunc(SI,mid,SQ,EQ, (2*index+1)),
				RMQUtilFunc(mid+1,EI,SQ,EQ,2*index+2));
	}
	
	//To return the minimum between a specified range
	static int RangeMinQuery(int n, int SQ, int EQ)
	{
		if (SQ < 0 || EQ > n - 1 || SQ > EQ)
		{
			System.out.println("Invalid Input");
			return -1;
		}

		return RMQUtilFunc(0, n - 1, SQ, EQ, 0);
	}

	static int constructSTUtil(int arr[], int SI, int EI, int CurrIndex)
	{
		// If there is one element in array, store it in current node of segment tree and return
		if (SI == EI)
		{
			st[CurrIndex] = arr[SI];
			return arr[SI];
		}

		// If there are more than one elements, then recur for left and
		// right subtrees and store the minimum of two values in this node
		int mid = getMid(SI, EI);
		st[CurrIndex] = Math.min(constructSTUtil(arr, SI, mid, CurrIndex * 2 + 1),
				constructSTUtil(arr, mid + 1, EI, CurrIndex * 2 + 2));
		
		return st[CurrIndex];
	}
	static void ConstructST(int arr[],int size)
	{
		//Height of segment tree
		int x = (int) (Math.ceil(Math.log(size) / Math.log(2)));
	//	System.out.println("Ht"+x);
		//Maximum size of segment tree
		int max_size = 2 * (int) Math.pow(2, x) - 1;
		st = new int[max_size]; // allocate memory

		constructSTUtil(arr, 0, size - 1, 0);
	}
	
	static void mainMin(ArrayList <Integer> LeafNodes, int size)
	{
		Scanner in =new Scanner(System.in);
    	int n, arr[];
    	n=size;
    	arr=new int[n];
    	for(int i=0;i<n;i++)
    	{
    		arr[i]=LeafNodes.get(i);
    	}
    	ConstructST(arr, n);
    	
    	int start,end;
    	System.out.print("Enter starting index of query : ");
    	start=in.nextInt();
    	System.out.print("Enter ending index of query : ");
    	end=in.nextInt();
    	 
    	int small=0;
    	if(start>0 || end>n)
    	{
    		small=RangeMinQuery(n, start, end);
    		System.out.println("Smallest Element in the range ("
	    			+start+"-"+end+") is: "+small);
    	}
    	else
    	{
	    	small=RangeMinQuery(n, start, end);
	    	System.out.println("Smallest Element in the range "
	    			+start+"("+arr[start]+") - "+end+"("+arr[end]+")  is: "+small);
    	}
	}
    
}



