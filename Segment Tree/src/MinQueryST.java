import java.util.Scanner;
public class MinQueryST
{
	int st[];
	int getMin(int x, int y) 
	{
		int m=0;
		m= (x < y) ? x : y;
		return m;

	}

	// A utility function to get the middle index from corner
	// indexes.
	int getMid(int s, int e) 
	{
		int mid;
		mid= s + (e - s) / 2;
		return mid;
	}
	
	//To get minimum in the given range
	int RMQUtilFunc(int SI, int EI, int SQ, int EQ, int index)
	{
		//If EIgment part of given range, return minimum of the EIgment
		if(SQ<=SI && EQ>= EI)
			return st[index];
		
		//If EIgment is out of given range
		if(EI<SQ || SI>EQ)
			return Integer.MAX_VALUE;
		
		int mid=getMid(SI,EI);
		return getMin(RMQUtilFunc(SI,mid,SQ,EQ, (2*index+1)),
				RMQUtilFunc(mid+1,EI,SQ,EQ,2*index+2));
	}
	
	//To return the minimum between a specified range, uEIs the RMQ Util function
	int RangeMinQuery(int n, int SQ, int EQ)
	{
		// Check for invalid input values
		if (SQ < 0 || EQ > n - 1 || SQ > EQ)
		{
			System.out.println("Invalid Input");
			return -1;
		}

		return RMQUtilFunc(0, n - 1, SQ, EQ, 0);
	}

	int constructstUtil(int arr[], int SI, int EI, int CurrIndex)
	{
		// If there is one element in array, store it in current
		// node of EIgment tree and return
		if (SI == EI)
		{
			st[CurrIndex] = arr[SI];
			return arr[SI];
		}

		// If there are more than one elements, then recur for left and
		// right subtrees and store the minimum of two values in this node
		int mid = getMid(SI, EI);
		st[CurrIndex] = getMin(constructstUtil(arr, SI, mid, CurrIndex * 2 + 1),
				constructstUtil(arr, mid + 1, EI, CurrIndex * 2 + 2));
		return st[CurrIndex];
	}
	void Constructst(int arr[],int size)
	{
		//Height of segment tree
		int x = (int) (Math.ceil(Math.log(size) / Math.log(2)));

		//Maximum size of segment tree
		int max_size = 2 * (int) Math.pow(2, x) - 1;
		st = new int[max_size]; // allocate memory

		// Fill the allocated memory st
		constructstUtil(arr, 0, size - 1, 0);
	}
	
    
	public static void main(String args[])
    {
    	MinQueryST min=new MinQueryST();
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
    	
    	min.Constructst(arr, n);
    	
    	int start,end;
    	System.out.print("Enter starting index of query");
    	start=in.nextInt();
    	System.out.print("Enter ending index of query");
    	end=in.nextInt();
    	 
    	int small=min.RangeMinQuery(n, start, end);
    	System.out.println("\n Smallest Element in the range "+start+" "+end+"  is: "+small);
    	in.close();
    }
}



