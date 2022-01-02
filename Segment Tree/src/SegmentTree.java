import java.util.*;
public class SegmentTree
 {
    public static void main(String[] args) throws Exception 
    {
        Scanner in = new Scanner(System.in);
        System.out.println("\n             ============");
        System.out.println("             SEGMENT TREE");
        System.out.println("             ============\n");
        System.out.println("  RANGE QUERY");
        System.out.println("  -----------");
        System.out.println("\n 1.Minimum Range Query");
        System.out.println(" 2.Maximum Range Query");
        System.out.println(" 3.Persistant Segment Tree");
        System.out.println(" 4.Lazy Propogation\n");
        
        int choice;
        char bool='y';
        do
        {
            System.out.print("Enter choice : ");
            choice=in.nextInt();
            
            switch(choice)
            {
                case 1:
                    System.out.print("\n -------------------");
                    System.out.print("\n Minimum Range Query");
                    System.out.print("\n -------------------\n");
                    MinQueryST.mainMin();
                    break;
                
                case 2:
                    System.out.print("\n -------------------");
                    System.out.print("\n Maximum Range Query");
                    System.out.print("\n -------------------\n");
                    MaxQueryST.mainMax();
                    break;
                
                case 3:
                    System.out.print("\n -------------------");
                    System.out.print("\n Persistent Segment Tree");
                    System.out.print("\n -------------------\n");
                    break;
                
                case 4:
                    System.out.print("\n -------------------");
                    System.out.print("\n Lazy Propagation");
                    System.out.print("\n -------------------\n");
                    break;
            }
        
            System.out.print("Do you wish to continue? (Y/N) : ");
            bool=in.next().charAt(0);
            System.out.print("\n");
        }
            while(bool=='Y'||bool=='y');
       
        in.close();    
    }
}
