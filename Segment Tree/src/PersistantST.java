import java.util.ArrayList;
import java.util.Scanner;

public class PersistantST
{
	static class node
	{
		int sum;
		node left, right;//children nodes
	    
		node(node l, node r, int v)
		{
			left = l;
			right = r;
			sum = v;
		}
	}
	
	static int[] arr;
	static node version[] ; //pointer
	
	static void Version0(node n, int low, int high)
	{
		if (low == high)//only one element in array
		{
			n.sum = arr[low];
			return;
		}
		
		int mid = (low + high) / 2;
		n.left = new node(null, null, 0);
		n.right = new node(null, null, 0);
		Version0(n.left, low, mid);
		Version0(n.right, mid + 1, high);
		n.sum = n.left.sum + n.right.sum;//sum of initial version
	}
	
	//Upgrading to newer versions
	//value - new number 
	static void upgrade(node prev, node curr, int low,int high, int index, int value)
	{
		if (index > high || index < low || low > high)
			return;
	
		if (low == high)
		{
			curr.sum = value;
			return;
		}
		
		int mid = (low + high) / 2;
		
		if (index <= mid)//left
		{
			// Link to right child of previous version
			curr.right = prev.right;
	
			// Create new node in current version
			curr.left = new node(null, null, 0);
	
			upgrade(prev.left, curr.left, low,mid, index, value);
		}
		else
		{
			// Link to left child of previous version
			curr.left = prev.left;
	
			// Create new node for current version
			curr.right = new node(null, null, 0);
	
			upgrade(prev.right, curr.right, mid + 1,high, index, value);
		}
		// calculating the sum of current version including the previous and current version
		curr.sum = curr.left.sum + curr.right.sum;
	}
	
	static int query(node n, int low, int high,int BI, int EI)
	{
		if (BI > high || EI < low || low > high)
			return 0;
		if (BI <= low && high <= EI)
			return n.sum;
			
		int mid = (low + high) / 2;
		int p1 = query(n.left, low, mid, BI, EI);
		int p2 = query(n.right, mid + 1, high, BI, EI);
		return p1 + p2;
	}
	
	static void mainPersistent(ArrayList <Integer> LeafNodes, int size)
	{
		Scanner in =new Scanner(System.in);
    	int n;
    	n=size;
    	arr=new int[n];;
    	for(int i=0;i<n;i++)
    	{
    		arr[i]=LeafNodes.get(i);
    	}
    	
    	version=new node[n];
 	
    	// Creating initial tree - version-0
 		node root = new node(null, null, 0);
 		Version0(root, 0, n - 1);
 	
 		// Storing root node for version-0
 		version[0] = root;
 	
 		int count=1,ct=0;
 		char ch='y';
 		int nodeIndex,value;
 		do
 		{
 			System.out.print("Enter node index : ");
 			nodeIndex=in.nextInt();

 			if(nodeIndex>n)
 			{
 				System.out.println("Node Index out of range!!");
 			}
 			else
 			{
 				System.out.print("Enter value : ");
 	 			value=in.nextInt();
 	 			version[count] = new node(null, null, 0);
 	 	 		upgrade(version[ct], version[count], 0, n - 1, nodeIndex, value);
 	 	 		
 	 	 		System.out.print("Enter starting index of query : ");
 	 	    	int start=in.nextInt();
 	 	    	System.out.print("Enter ending index of query : ");
 	 	    	int end=in.nextInt();
 	 	    	if(start>=0 && end<n)
 	 	    	{
 	 	    		System.out.print("In version "+count+" , query ("+start+" - "+end+") sum is : ");
 	 	 	 		System.out.print(query(version[count], 0, n - 1, start, end));
 	 	    	}
 	 	    	else
 	 	    	{
 	 	    		System.out.print("Sum of nodes of query range "+start+"-"+end+" is : -1");
 	 	    	}
 	 	 		count=count+1;
 	 	 		ct=ct+1;
 			}
 	 		System.out.print("\nDo you wish to continue in the persistent segment tree? (Y/N) : ");
            ch=in.next().charAt(0);
            System.out.print("\n");
 			
 		}
 		while(ch=='y'||ch=='Y');
	}
}