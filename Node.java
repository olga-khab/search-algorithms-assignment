import java.util.*;
import java.lang.Math.*;
public class Node{
  private ArrayList<Integer> value;
  private ArrayList<Node> children;
  private Node parent;
  private int changed_parent_digit;

  public Node(ArrayList<Integer> vals, ArrayList<Node> children, Node parent, int digit){
    this.value = vals;
    this.children=children;
    this.parent=parent;
    this.changed_parent_digit=digit;
  }

  public void createChildren(){
    this.children = new ArrayList<Node>();
    ArrayList<Integer> current_value = this.getValue();
    //**** generate first two ****//
    int first_digit = current_value.get(0);
    // - 1
    //don't change same digit twice
    if (Math.abs(this.changed_parent_digit)!=1){
      if (first_digit != 0){
        int first = first_digit-1;
        int second = current_value.get(1);
        int third = current_value.get(2);
        // create "value" of the child node
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(0,first);
        temp.add(1,second);
        temp.add(2,third);
        // create child Node
        Node n = new Node(temp, null, this, -1);
        this.children.add(n);
      }
    // + 1
      if (first_digit != 9){
        int first = first_digit+1;
        int second = current_value.get(1);
        int third = current_value.get(2);
        // create "value" of the child node
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(0,first);
        temp.add(1,second);
        temp.add(2,third);
        // create child Node
        Node n = new Node(temp, null, this, 1);
        this.children.add(n);
      }
    }
    //**** generate second two ****//
    int second_digit = current_value.get(1);
    // - 1
    if (Math.abs(this.changed_parent_digit)!=2){
      if (second_digit != 0){
        int first = current_value.get(0);
        int second = second_digit-1;
        int third = current_value.get(2);
        // create "value" of the child node
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(0,first);
        temp.add(1,second);
        temp.add(2,third);
        // create child Node
        Node n = new Node(temp, null, this, -2);
        this.children.add(n);
      }
      // + 1
      if (second_digit != 9){
        int first = current_value.get(0);
        int second = second_digit+1;
        int third = current_value.get(2);
        // create "value" of the child node
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(0,first);
        temp.add(1,second);
        temp.add(2,third);
        // create child Node
        Node n = new Node(temp, null, this, 2);
        this.children.add(n);
      }
    }
    //**** generate third two ****//
    int third_digit = current_value.get(2);
    // - 1
    if (Math.abs(this.changed_parent_digit)!=3){
      if (third_digit != 0){
        int first = current_value.get(0);
        int second = current_value.get(1);
        int third = third_digit-1;
        // create "value" of the child node
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(0,first);
        temp.add(1,second);
        temp.add(2,third);
        // create child Node
        Node n = new Node(temp, null, this, -3);
        this.children.add(n);
      }
      // + 1
      if (third_digit != 9){
        int first = current_value.get(0);
        int second = current_value.get(1);
        int third = third_digit+1;
        // create "value" of the child node
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(0,first);
        temp.add(1,second);
        temp.add(2,third);
        // create child Node
        Node n = new Node(temp, null, this, 3);
        this.children.add(n);
      }
    }
  }

// getters
  public ArrayList<Integer> getValue(){
    return(this.value);
  }
  public ArrayList<Node> getChirdren(){
    return(this.children);
  }
  public Node getParent(){
    return(this.parent);
  }
  public int getParentDigit(){
    return this.changed_parent_digit;
  }

  public static void main(String[] args){
    ArrayList<Integer> vals = new ArrayList<Integer>();
    vals.add(0,7);
    vals.add(1,5);
    vals.add(2,3);
    Node test = new Node(vals, null, null, 0);
    test.createChildren();
    for (int i=0; i<test.getChirdren().size(); i++){
      for (int j=0; j< 3; j++){
        System.out.print(test.getChirdren().get(i).getValue().get(j));
        System.out.print(",");
      }
      System.out.print(test.getChirdren().get(i).getParentDigit());
      System.out.println();
    }
  }
}
