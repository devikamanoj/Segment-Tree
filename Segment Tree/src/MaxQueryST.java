import java.util.Scanner;

public class MaxQueryST
{
	static int getMid(int s, int e) 
	{
		int mid;
		mid= s + (e - s) / 2;
		return mid;
	}
	
	//A recursive function that gives the sum of values in a given range
	static int RMaxUtilFunc(int[] st, int SI,int EI, int QSI,int QEI, int CurrIndex)
	{
	
	// When segment of current index is part of the given range, return the maximum from the segment
	if (QSI <= SI && QEI >= EI)
		return st[CurrIndex];
	
	// When segment of current index is not part of the given range, return -1
	if (EI < QSI || SI > QEI)
		return -1;
	
	
	int mid = getMid(SI, EI);
	
	return Math.max(RMaxUtilFunc(st, SI, mid, QSI, QEI,2 * CurrIndex + 1),
			RMaxUtilFunc(st, mid + 1, EI, QSI, QEI,2 * CurrIndex + 2));
	}
	
	//Return largest element in a given query range
	static int RangeMaxQuery(int[] st, int n, int QSI, int QEI)
	{
		if (QSI < 0 || QEI > n - 1 || QSI > QEI) 
		{
			System.out.printf("Invalid Input\n");
			return -1;
		}

		return RMaxUtilFunc(st, 0, n - 1, QSI, QEI, 0);
	}
	
	static int constructSTUtil(int arr[],int SI, int EI,int[] st, int CurrIndex)
	{
	
		// If there is one element in array, store it in current node of segment tree and return
		if (SI == EI)
		{
		st[CurrIndex] = arr[SI];
		return arr[SI];
		}
		
		// If there are more than one elements, then recur for left and right subtrees and store the max of values in this node
		int mid = getMid(SI, EI);
		
		st[CurrIndex] = Math.max(constructSTUtil(arr, SI, mid,st, CurrIndex * 2 + 1),
				constructSTUtil(arr, mid + 1,EI, st,CurrIndex * 2 + 2));
		
		return st[CurrIndex];
	}
	
	static int[] ConstructST(int arr[], int size)
	{

		// Height of segment tree
		int x = (int)Math.ceil(Math.log(size) / Math.log(2));

		// Maximum size of segment tree
		int max_size = 2 * (int)Math.pow(2, x) - 1;

		// Allocate memory
		int[] st = new int[max_size];

		// Fill the allocated memory st
		constructSTUtil(arr, 0, size - 1, st, 0);

		// Return the constructed segment tree
		return st;
	}
	public static void main(String[] args) throws Exception 
	{
		Scanner in =new Scanner(System.in);
    	int n, arr[];
    	System.out.print("Enter size of the array : ");
    	n=in.nextInt();
    	
    	arr=new int[n];
    	System.out.println("Enter elements for the array ");
    	for(int i=0;i<n;i++)
    	{
    		arr[i]=in.nextInt();
    	}
    	
    	int ST[]=ConstructST(arr, n);
    	
    	int start,end;
    	System.out.print("Enter starting index of query");
    	start=in.nextInt();
    	System.out.print("Enter ending index of query");
    	end=in.nextInt();
    	 
    	int large=RangeMaxQuery(ST,n, start, end);
    	System.out.println("\n Largest Element in the range "+start+" "+end+"  is: "+large);
    	in.close();
	}

}
