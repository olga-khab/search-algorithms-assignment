import java.util.*;
import java.io.*;
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
    //BFS
    while (!current_node.getValue().equals(goal_node.getValue())){
      if (!(forbidden!=null && forbidden.contains(current_node.getValue()))
        && !(expanded.size()!=0 && current_node.getParent()!=null && current_node.isInList(expanded))){
        expanded.add(current_node);
        if (expanded.size()>=1000){
            System.out.println("No solution found");
            printValues(expanded);
            return;
        }
        // generate children
        current_node.createChildren();
        for (Node child : current_node.getChirdren()){
          fringe.add(child);
        }
      }
      // reached the end but didn't find the node
      if (fringe.size() == 0){
        System.out.println("No solution found");
        printValues(expanded);
        return;
      }
      // keep going
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

  public static void DFS(Node start_node, Node goal_node,
  ArrayList<ArrayList<Integer>> forbidden){
    ArrayList<Node> expanded = new ArrayList<Node>();
    ArrayList<Node> path = new ArrayList<Node>();
    Stack<Node> fringe = new Stack<Node>();
    Node current_node = start_node;
    while (!current_node.getValue().equals(goal_node.getValue())){
      if (!(forbidden!=null && forbidden.contains(current_node.getValue()))
        && !(expanded.size()!=0 && current_node.getParent()!=null && current_node.isInList(expanded))){
        expanded.add(current_node);
        if (expanded.size()>=1000){
            System.out.println("No solution found");
            printValues(expanded);
            return;
        }
        // generate children
        current_node.createChildren();
        for (int i = current_node.getChirdren().size()-1; i>=0; i--){
          Node child = current_node.getChirdren().get(i);
          fringe.push(child);
        }
      }
      // reached the end but didn't find the node
      if (fringe.size() == 0){
        System.out.println("No solution found");
        printValues(expanded);
        return;
      }
      // keep going
      current_node= fringe.peek();
      fringe.pop();
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

// depth limited search - needed for IDS
// returns expanded
  public static ArrayList<Node> DLS(Node start_node, Node goal_node,
  ArrayList<ArrayList<Integer>> forbidden, int limit_depth, int total_expanded){

    ArrayList<Node> expanded = new ArrayList<Node>();
  //  ArrayList<Node> path = new ArrayList<Node>();
    Stack<Node> fringe = new Stack<Node>();
    Node current_node = start_node;
  //  int iterations = 0;

    while (current_node.getDepth() <= limit_depth){
      // expand node if allowed
    //  System.out.println(current_node.getValue());
      if (!(forbidden!=null && forbidden.contains(current_node.getValue()))
        && !(expanded.size()!=0 && current_node.getParent()!=null && current_node.isInList(expanded))){
          if (total_expanded+expanded.size() >= 1000){
            // limit reach, exit
              return expanded;
          }
          if (current_node.getValue().equals(goal_node.getValue())){
            expanded.add(current_node);
            return expanded;
          }

          expanded.add(current_node);

      if (current_node.getDepth() < limit_depth){// if reached max depth stop creating children
          current_node.createChildren();
          for (int i = current_node.getChirdren().size()-1; i>=0; i--){
            //expanded.add(current_node);
            Node child = current_node.getChirdren().get(i);
            fringe.push(child);
          }
        }
      }
      if (fringe.size() == 0 && current_node.getDepth() < limit_depth){
        // no more children - e.g. all forbidden
      //  printValues(expanded);
        return expanded;
      }else if (fringe.size()==0 && current_node.getDepth() == limit_depth){
        //printValues(expanded);
      //  System.out.println();
        return expanded;
      }
      // keep going
      current_node = fringe.peek();
      fringe.pop();
  }
  // reached max depth
  //System.out.println("jd");
  //printValues(expanded);
  return expanded;
}

public static void IDS(Node start_node, Node goal_node,
ArrayList<ArrayList<Integer>> forbidden){
  ArrayList<Node> expanded = new ArrayList<Node>();
  ArrayList<Node> path = new ArrayList<Node>();
  int depth = 1;
  expanded.add(start_node);
  while (true){

      ArrayList<Node> current_expanded =
      DLS(start_node, goal_node, forbidden, depth, expanded.size());

      // reached the end but didn't find the node
      if (current_expanded == null){
        System.out.println("No solution found");
        printValues(expanded);
        return;
      }

      expanded.addAll(current_expanded);

      if (expanded.get(expanded.size()-1).getValue().equals(goal_node.getValue())){
        // found the goal node
        Node current_node = expanded.get(expanded.size()-1);
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

      // reached the limit
      if (expanded.size()>=1000){
          System.out.println("No solution found");
          printValues(expanded);
          return;
      }

      // otherwise keep going
      depth++;
  }

}




  public static void main(String[] args) throws FileNotFoundException {
    File file = new File(args[1]);
    Scanner scanner = new Scanner(file);
    ArrayList<String> input = new ArrayList<String>();
    while (scanner.hasNextLine()){
      input.add(scanner.nextLine());
    }

    ArrayList<Integer> start = new ArrayList<Integer>();
    start.add(Integer.parseInt(input.get(0).split("")[0]));
    start.add(Integer.parseInt(input.get(0).split("")[1]));
    start.add(Integer.parseInt(input.get(0).split("")[2]));
    ArrayList<Integer> end = new ArrayList<Integer>();
    end.add(Integer.parseInt(input.get(1).split("")[0]));
    end.add(Integer.parseInt(input.get(1).split("")[1]));
    end.add(Integer.parseInt(input.get(1).split("")[2]));


    Node start_node = new Node(start,null,null,0, 0);
    Node end_node = new Node(end,null,null,-4, 0);

    ArrayList<ArrayList<Integer>> forbidden = new ArrayList<ArrayList<Integer>>();
    if (input.size()==3){

      for (String val:input.get(2).split(",")){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(Integer.parseInt(val.split("")[0]));
        temp.add(Integer.parseInt(val.split("")[1]));
        temp.add(Integer.parseInt(val.split("")[2]));
        forbidden.add(temp);
      }
    }else{
      forbidden = null;
    }
    //System.out.println(args[0].compareTo("BFS"));
    if (args[0].compareTo("B")==0){
      BFS(start_node,end_node,forbidden);
    }else if (args[0].compareTo("D")==0){
      DFS(start_node,end_node,forbidden);
    }else if (args[0].compareTo("I")==0){
      IDS(start_node,end_node,forbidden);
    }
  }
}
