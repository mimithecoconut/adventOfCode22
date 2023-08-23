import java.util.*;

// class to represent a directory tree for aocDay7 where TreeNode is a directory if has children and a file if none
public class TreeNode {

    String name; // name of node
    int size; // size of node. for directories it is the sum of its children size
    TreeNode parent; // parent node to current node
    List<TreeNode> children; // children to current node

    /*
    Constructor for creating a TreeNode
     */
    public TreeNode(String name, int size){
        this.name = name;
        this.size = size;
    }

    /*
    Set parent node of current node
     */
    public void setParent(TreeNode parent){
        this.parent = parent;
    }

    /*
    Add child to children of current node and update size
     */
    public void addChild(TreeNode child){
        child.setParent(this);
        if (this.children == null) this.children = new ArrayList<>();
        this.children.add(child);
        this.size += child.size;
    }

    /*
    Update size of node. Used when child directory size has been calculated and need to update current node
     */
    public void updateSize(int add){
        this.size += add;
    }

}
