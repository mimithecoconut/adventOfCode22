import java.io.*;

// Utilizes TreeNode class which represents the directory tree
public class aocDay7 {

    // calculated the number of space needed to delete after part 1 at the bottom
    public static int needToDelete = 2036703;

    /*
    cd into a subdirectory from current directory
     */
    public static TreeNode cdDown(TreeNode curr, String dirName){
        for (TreeNode child :  curr.children){
            if (child.name.equals(dirName)) return child;
        }
        return null;
    }

    /*
    For part 1: check if node size is under the constraint
     */
    public static boolean underConstraint(TreeNode node){
        return node.size <= 100000;
    }

    /*
    Processes input data of directory and file information and commands in order to
    determine size of directories
     */
    public static void main(String[] args){
        TreeNode root = new TreeNode("/", 0);
        TreeNode curr = root;
        int totalSize = 0;
        int smallest = Integer.MAX_VALUE;
        try{
            File file = new File("inputData/aocDay7Input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            int count = 0;
            while((line = br.readLine()) != null){
                String[] list = line.split(" ");
                // parse case where the directory name is given or a file name and size is given
                if (line.charAt(0) != '$') {
                    // directory start out with size 0 but are then updated when traversing back up the tree
                    if (list[0].charAt(0) == 'd') curr.addChild(new TreeNode(list[1], 0));
                    // addchild also adds file size of children and updates the size of current node with added child
                    else curr.addChild(new TreeNode(list[1], Integer.parseInt(list[0])));
                }
                // case of going down the tree to subdirectory
                else if (list[1].equals("cd") && !list[2].equals("..") && line.charAt(5) != '/'){
                    curr = cdDown(curr, list[2]);
                }
                // case of going back up the tree. cd .. happens only when we have reached only files and no directories
                else if (list[1].equals("cd") && list[2].equals("..")){
                    int add = curr.size;
                    // for part 1 for getting total size of directories that are under the constraint
                    if (underConstraint(curr)) {
                        totalSize += add;
                    }
                    // for part 2 for updating the smallest directory that can free up the needToDelete space
                    if (add >= needToDelete && add < smallest) {
                        smallest = add;
                    }
                    curr = curr.parent; // traverse up
                    curr.updateSize(add); // take into account the newly updated child directory size
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        // since input does not end back to root, traverse remaining until root is reached
        // This is because our necessary operations happens on the traverse back
        while (!curr.name.equals("/")){
            int add = curr.size;
            if (underConstraint(curr)) {
                totalSize += add;
            }
            if (add >= needToDelete && add < smallest) smallest = add;
            curr = curr.parent;
            curr.updateSize(add);
        }

        System.out.println("The sum of total sizes of directories with size at most 100000 is: " + totalSize);

        // calculations for part 2 using part 1. needToDelete is set to the answer of spaceToBeDeleted for part 2
        int sizeOfRoot = curr.size;
        int spaceUnused = 70000000 - sizeOfRoot;
        int spaceToBeDeleted = 30000000 - spaceUnused;
        System.out.println("The space needed to be deleted in order to get at least 30000000 unused space is: " +
                spaceToBeDeleted);

        System.out.println("The smallest directory that would free up enough space if deleted is: " + smallest);
    }
}
