import java.util.Scanner;
public class MinQueryST 
{
	int ST[];
	int getMin(int x,int y)
	{
		int m;
		m=(x<y)?x:y;
		return m;
	}
	
	int getMid(int a, int b)
	{
		int mid;
		mid= a+(a+b) /2;
		return mid;
	}
	
	//To get minimum in the given range
	int RMQUtilFunc(int SI, int EI, int SQ, int EQ, int index)
	{
		//If segment part of given range, return minimum of the segment
		if(SQ<=SI && EQ>= EI)
			return ST[index];
		
		//If segment is out of given range
		if(EI<SQ || SI>EQ)
			return Integer.MAX_VALUE;
		return 1;
	}
	
	void ConstructST(int arr[],int size)
	{
		
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
    	
    	min.ConstructST(arr, n);
    	
    	int start,end;
    	System.out.print("Enter starting index of query");
    	start=in.nextInt();
    	System.out.print("Enter ending index of query");
    	end=in.nextInt();
    	 
    	int small=0;
    	System.out.println("\n Smallest Element in the range "+start+" "+end+"  is: "+small);
    	in.close();
    }
}



