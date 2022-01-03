import java.util.*;
public class SegmentTree
 {
    static int size;
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception 
    {
        ArrayList <Integer> LeafNodes = new ArrayList<Integer>();
        System.out.println("\n             ============");
        System.out.println("             SEGMENT TREE");
        System.out.println("             ============\n");

        System.out.print("Enter the number of Leaf Nodes: ");
        size=in.nextInt();
        
        // taking the input of the leaf nodes
        System.out.println("Enter the Leaf Nodes: ");
        LeafNodes=InputElements(LeafNodes, size);
        
        System.out.println();
        System.out.println(" -----------");
        System.out.println(" RANGE QUERY");
        System.out.println(" -----------");
        System.out.println("\n 1.Minimum Range Query");
        System.out.println(" 2.Maximum Range Query");
        System.out.println(" 3.Persistant Segment Tree");
        System.out.println(" 4.Get sum of the given range");
        System.out.println(" 5.Update the tree and get sum\n");
        
        int choice;
        char bool='y';
        do
        {
            System.out.print("Enter choice : ");
            choice=in.nextInt();
            
            switch(choice)
            {
                case 1:
                {
                    System.out.print("\n -------------------");
                    System.out.print("\n Minimum Range Query");
                    System.out.print("\n -------------------\n");
                    MinQueryST.mainMin(LeafNodes,size);
                    break;
                }
                case 2:
                {
                    System.out.print("\n -------------------");
                    System.out.print("\n Maximum Range Query");
                    System.out.print("\n -------------------\n");
                    MaxQueryST.mainMax(LeafNodes,size);
                    break;
                }
                case 3:
                {
                    System.out.print("\n -----------------------");
                    System.out.print("\n Persistent Segment Tree");
                    System.out.print("\n -----------------------\n");
                    PersistantST.mainPersistent(LeafNodes,size);
                    break;
                }
                case 4:
                {
                    System.out.print("\n --------------------------");
                    System.out.print("\n Get sum of the given range");
                    System.out.print("\n --------------------------\n");
                    SumofGivenRange.Sum(LeafNodes,size);
                    break;
                }
                case 5:
                {
                    System.out.print("\n ---------------------------");
                    System.out.print("\n Update the tree and get sum");
                    System.out.print("\n ---------------------------\n");
                    UpdateGivenRange.update(LeafNodes,size);
                    break;  
                }
            }
        
            System.out.print("\nDo you wish to continue? (Y/N) : ");
            bool=in.next().charAt(0);
            System.out.print("\n");
        }
            while(bool=='Y'||bool=='y');
       
        in.close();    
    }
    
    static ArrayList<Integer> InputElements(ArrayList <Integer> array, int size)
    {
        for(int i=0; i<size;i++)
        {
            array.add(in.nextInt());
        }
        return array;
    }
}