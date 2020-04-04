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
      expanded.add(current_node);
      if (current_node.getParent()!=null && !path.contains(current_node.getParent())){
        path.add(current_node.getParent());
      }

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
      current_node= fringe.get(0);
      fringe.remove(0);
    }

    // found the goal node
    expanded.add(current_node);
    path.add(current_node);
    printValues(path);
    printValues(expanded);
    return;
  }


  public static void main(String[] args){
    // testing printValues
    //ArrayList<Node> expanded = new ArrayList<Node>();
    ArrayList<Integer> val = new ArrayList<Integer>();
    ArrayList<Integer> val2 = new ArrayList<Integer>();
    val.add(1);
    val.add(8);
    val.add(0);
    val2.add(0);
    val2.add(9);
    val2.add(0);

    //expanded.add(new Node(val,null, null, -1));

    //expanded.add(new Node(val2,null, null, -1));
    //printValues(expanded);

    Node start = new Node(val,null, null, -1);
    Node goal = new Node(val2,null, null, -1);

    BFS(start, goal,null);


  }
}
