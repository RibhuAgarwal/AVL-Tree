//Name: Ribhu Agarwal
//Student Id: T00659301

public class TestHarness{

    public static void main(String[] args) {
          AVLTree<Integer> tree = new AVLTree<Integer>();
          try{
          for (int i = 1; i <= 7; i++)
              tree.add(new Integer(i));
          tree.add(7);
         
          tree.PrintTree();
          tree.removeAll(7);
          tree.iterator();
          if(tree.contain(3)){
            System.out.println("It contains that");
          }
          else{
            System.out.println("It doesn't contain that");
           }
        }
        catch(Exception e){
            System.out.println("Something is wrong");
        }
        
        
      
      }

  
  }