import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class BinaryTree {
    private BinaryTreeNode root = null;
    private int size = 0;

    private static int height(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        else {
            return node.height;
        }
    }


    public int height (){
        return height(root);
    }


    public Boolean is_empty() {
        return root == null;
    }


    public int size() {
        return size;
    }


    private static int get_balance(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return height(node.leftChild) - height(node.rightChild);
        }
    }


    public BinaryTree add(int item) {
        BinaryTreeNode newItem = new BinaryTreeNode();
        newItem.value = item;
        if (is_empty()) {
            root = newItem;
            size += 1;
        } else {
            BinaryTreeNode current = root;
            BinaryTreeNode parent = null;
            while (true) {
                parent = current;
                if (newItem.value < parent.value) {
                    current = current.leftChild;
                }
                if (current == null) {
                    parent.leftChild = newItem;
                    size += 1;
                    break;
                }
                else { //greater than or equal go on the right side
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newItem;
                        size += 1;
                        break;
                    }
                }
            }
        }
        return this;
    }



    public BinaryTree insert (int item) {
        BinaryTreeNode newNode = new BinaryTreeNode();
        newNode.value = item;
        if (is_empty()) {
            root = newNode;
            size += 1;
        }
        else {
            insert(root, newNode);
        }
        return this;
    }

        private void insert (@NotNull BinaryTreeNode currentNode, @NotNull BinaryTreeNode newNode) {
            if (newNode.value < currentNode.value) {
            //left side
                if (currentNode.leftChild == null) {
                    currentNode.leftChild = newNode;
                    size += 1;
                } else {
                    insert(currentNode.leftChild, newNode);
                }
            }
	        else {
                //right side
                if (currentNode.rightChild == null) {
                    currentNode.rightChild = newNode;
                    size += 1;
                } else {
                    insert(currentNode.rightChild, newNode);
                }
            }
        }

    public BinaryTree insert_balanced(int value) {
        BinaryTreeNode newNode = new BinaryTreeNode();
        newNode.value = value;
        root = insert_balanced(root, newNode);
        size += 1;
        return this;
    }


    private BinaryTreeNode insert_balanced(BinaryTreeNode currentNode,BinaryTreeNode newNode) {
        if (currentNode == null) {
            return newNode;
        }
        if (newNode.value < currentNode.value) {
            currentNode.leftChild = insert_balanced(currentNode.leftChild, newNode);
        } else { //check for duplicates
            currentNode.rightChild = insert_balanced(currentNode.rightChild, newNode);
        }
        currentNode.height = 1 + Math.max(height(currentNode.leftChild), height(currentNode.rightChild));
        int balance = get_balance(currentNode);
	    //if the tree is imbalanced, then there are four cases
	    //Case 1:Left left case
        if (balance > 1 && newNode.value < currentNode.leftChild.value) {
            return right_rotate(currentNode);
        }

        //Case 2:Right right case
        if (balance < -1 && newNode.value > currentNode.rightChild.value) {
            return left_rotate(currentNode);
        }

        //Case 3:Left right case
        if (balance > 1 && newNode.value > currentNode.leftChild.value) {
            currentNode.leftChild = left_rotate(currentNode.leftChild);
            return right_rotate(currentNode);
        }

	    //Case Right left case
        if (balance < -1 && newNode.value < currentNode.rightChild.value) {
            currentNode.rightChild = right_rotate(currentNode.rightChild);
            return left_rotate(currentNode);
        }
        return currentNode;
    }

    private BinaryTreeNode right_rotate(BinaryTreeNode imbalancedNode) {
        BinaryTreeNode transformationNode = imbalancedNode.leftChild;
        BinaryTreeNode nodeT2 = transformationNode.rightChild;
	    //perform rotation
        transformationNode.rightChild = imbalancedNode;
        imbalancedNode.leftChild = nodeT2;
	    //takes the 2 heights and finds the largest of the 2 plus itself
	    //that becomes the new height at that location
        imbalancedNode.height = Math.max(height(imbalancedNode.leftChild), height(imbalancedNode.rightChild)) + 1;
        transformationNode.height = Math.max(height(transformationNode.leftChild), height(transformationNode.rightChild)) + 1;
        return transformationNode;
    }


    private BinaryTreeNode left_rotate(BinaryTreeNode imbalancedNode) {
        BinaryTreeNode transformationNode = imbalancedNode.rightChild;
        BinaryTreeNode nodeT2 = transformationNode.leftChild;
	    //perform rotation
        transformationNode.leftChild = imbalancedNode;
        imbalancedNode.rightChild = nodeT2;
	    //takes the 2 heights and finds the larger of the 2 plus itself
	    //that becomes the new height at that location
        imbalancedNode.height = Math.max(height(imbalancedNode.leftChild), height(imbalancedNode.rightChild)) + 1;
        transformationNode.height = Math.max(height(transformationNode.leftChild), height(transformationNode.rightChild)) + 1;
        return transformationNode;
    }

    //Depth First Search
    public int recursive_count() {
        return recursive_count(root);
    }


    private int recursive_count(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            var subTreeCount = 1;
            subTreeCount += recursive_count(node.leftChild);
            subTreeCount += recursive_count(node.rightChild);
            return subTreeCount;
        }
    }


    public void print_tree() {
        LinkedList <Pair> queue = new LinkedList <> ();
        queue.offer(new Pair(0, root));
        var currentLevel = 0;
        var currentLevelStr = "LO: ";
        while (!queue.isEmpty()) {
            Pair next = queue.poll();
            BinaryTreeNode currentNode = (BinaryTreeNode) next.second;
            int nodeLevel = (int) next.first;
            if (currentLevel != nodeLevel) {
                //You are on a new level
                //print the currentLevelStr
                System.out.println(currentLevelStr);
                currentLevel = nodeLevel;
                //make new currentLevelStr "L1: "
                currentLevelStr = String.format("L%s: ",currentLevel);
            }

            //add node.value to currentLevelStr
            currentLevelStr += String.format("%s, ",currentNode.value);
            //TODO String Builder
            if (currentNode.leftChild != null) {
                queue.offer(new Pair(currentLevel + 1, currentNode.leftChild));
            }
            if (currentNode.rightChild != null) {
                queue.offer(new Pair(currentLevel + 1, currentNode.rightChild));
            }
        }
        System.out.println(currentLevelStr);
    }


    public void recursive_print() {
        recursive_print(root);
    }


    private void recursive_print(BinaryTreeNode node) {
        if (node != null) {
            System.out.println(node.value);
            recursive_print(node.leftChild);
            recursive_print(node.rightChild);
        }
    }


private static class BinaryTreeNode{
    BinaryTreeNode leftChild;
    BinaryTreeNode rightChild;
    int value;
    int height = 1;
}



}
