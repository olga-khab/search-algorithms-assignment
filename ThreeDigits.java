import java.util.*;

public class ThreeDigits {
  //printing values form ArrayList
  public static void printValues(ArrayList<Node> expanded){
    for (int i=0; i<expanded.size(); i++){
      for (int j=0; j< 3; j++){
        System.out.print(expanded.get(i).getValue().get(j));
      }
      if (i<expanded.size()-1){
        System.out.print(",");
      }
    }
    System.out.println();
  }

  // BFS - driver function will be in main
  public static void BFS(Node start_node, Node goal_node,
  ArrayList<ArrayList<Integer>> forbidden){
    // prep
    ArrayList<Node> expanded = new ArrayList<Node>();
    ArrayList<Node> path = new ArrayList<Node>();
    ArrayList<Node> fringe = new ArrayList<Node>();
    Node current_node = start_node;
  //  fringe.add(current_node)
    //BFS
    while (!current_node.getValue().equals(goal_node.getValue())){
      // check forbidden & cycles
      if (!(forbidden!=null && forbidden.contains(current_node.getValue()))
        && !(expanded.size()!=0 && current_node.getParent()!=null && current_node.isInList(expanded))){
        expanded.add(current_node);
        if (expanded.size()==1000){
            System.out.println("No solution found");
            printValues(expanded);
            return;
        }

        // check forbidden

        // generate children
        current_node.createChildren();
        for (Node child : current_node.getChirdren()){
          fringe.add(child);
        }

        /*if (fringe.size() == 0){ // reached the end but didn't find the node
          System.out.println("No solution found");
          printValues(expanded);
          return;
        }*/
      }
      current_node= fringe.get(0);
      fringe.remove(0);
    }

    // found the goal node
    expanded.add(current_node);

    path.add(current_node);
    while (current_node.getParent()!=null){
      path.add(current_node.getParent());
      current_node = current_node.getParent();

    }
    ArrayList<Node> path_reversed = new ArrayList<Node>();
    for (int i = path.size()-1; i>=0; i--){
      path_reversed.add(path.get(i));
    }
    printValues(path_reversed);
    printValues(expanded);
    return;
  }

  public static void main(String[] args){
    // testing printValues
    //ArrayList<Node> expanded = new ArrayList<Node>();
    ArrayList<Integer> val = new ArrayList<Integer>();
    ArrayList<Integer> val2 = new ArrayList<Integer>();
    ArrayList<Integer> val3 = new ArrayList<Integer>();
    ArrayList<Integer> val4 = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> forbidden = new ArrayList<ArrayList<Integer>>();
    val.add(3);
    val.add(2);
    val.add(0);
    val2.add(1);
    val2.add(1);
    val2.add(0);
    val3.add(3);
    val3.add(3);
    val3.add(0);
    val4.add(3);
    val4.add(1);
    val4.add(1);
    //expanded.add(new Node(val,null, null, -1));

    //expanded.add(new Node(val2,null, null, -1));
    //printValues(expanded);

    Node start = new Node(val,null, null, 0);
    Node goal = new Node(val2,null, null, 0);
  //  Node f1 = new Node(val3,null, null, 0);
    //forbidden.add(val3);
    forbidden.add(val4);
    BFS(start, goal,null);

  }
}
