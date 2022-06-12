import java.util.LinkedList;

@SuppressWarnings("UnusedReturnValue")
public class BinaryTree <T extends Comparable <? super T>> {
    private BinaryTreeNode <T> root = null;
    private int size = 0;

    public T getRootValue(){
        if (!is_empty()) {
            return root.value;
        }
        return null;
    }
    @SuppressWarnings("rawtypes")
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


    @SuppressWarnings("rawtypes")
    private static int get_balance(BinaryTreeNode node) {

        if (node == null) {
            return 0;
        } else {
            return height(node.leftChild) - height(node.rightChild);
        }
    }


    @SuppressWarnings("UnusedReturnValue")
    public BinaryTree <T> add(T item) {
       insert_balanced(item);
       return this;
    }


    public BinaryTree <T> insert_balanced(T value) {
        BinaryTreeNode <T> newNode = new BinaryTreeNode<>();
        newNode.value = value;
        root = insert_balanced(root, newNode);
        size += 1;
        return this;
    }


    private BinaryTreeNode <T> insert_balanced(BinaryTreeNode <T> currentNode,BinaryTreeNode <T> newNode) {
        if (currentNode == null) {
            return newNode;
        }
        if (newNode.value.compareTo(currentNode.value) < 0) {
            currentNode.leftChild = insert_balanced(currentNode.leftChild, newNode);
        } else { //check for duplicates
            currentNode.rightChild = insert_balanced(currentNode.rightChild, newNode);
        }
        currentNode.height = 1 + Math.max(height(currentNode.leftChild), height(currentNode.rightChild));
        int balance = get_balance(currentNode);
	    //if the tree is imbalanced, then there are four cases
	    //Case 1:Left left case
        if (balance > 1 && newNode.value.compareTo(currentNode.leftChild.value) < 0) {
            return right_rotate(currentNode);
        }

        //Case 2:Right right case
        if (balance < -1 && newNode.value.compareTo(currentNode.rightChild.value) > 0) {
            return left_rotate(currentNode);
        }

        //Case 3:Left right case
        if (balance > 1 && newNode.value.compareTo(currentNode.leftChild.value) > 0) {
            currentNode.leftChild = left_rotate(currentNode.leftChild);
            return right_rotate(currentNode);
        }

	    //Case Right left case
        if (balance < -1 && newNode.value.compareTo(currentNode.rightChild.value) < 0) {
            currentNode.rightChild = right_rotate(currentNode.rightChild);
            return left_rotate(currentNode);
        }
        return currentNode;
    }

    private BinaryTreeNode <T> right_rotate(BinaryTreeNode <T> imbalancedNode) {
        BinaryTreeNode <T> transformationNode = imbalancedNode.leftChild;
        BinaryTreeNode <T> nodeT2 = transformationNode.rightChild;
	    //perform rotation
        transformationNode.rightChild = imbalancedNode;
        imbalancedNode.leftChild = nodeT2;
	    //takes the 2 heights and finds the largest of the 2 plus itself
	    //that becomes the new height at that location
        imbalancedNode.height = Math.max(height(imbalancedNode.leftChild), height(imbalancedNode.rightChild)) + 1;
        transformationNode.height = Math.max(height(transformationNode.leftChild), height(transformationNode.rightChild)) + 1;
        return transformationNode;
    }


    private BinaryTreeNode <T> left_rotate(BinaryTreeNode <T> imbalancedNode) {
        BinaryTreeNode <T> transformationNode = imbalancedNode.rightChild;
        BinaryTreeNode <T> nodeT2 = transformationNode.leftChild;
	    //perform rotation
        transformationNode.leftChild = imbalancedNode;
        imbalancedNode.rightChild = nodeT2;
	    //takes the 2 heights and finds the largest of the 2 plus itself
	    //that becomes the new height at that location
        imbalancedNode.height = Math.max(height(imbalancedNode.leftChild), height(imbalancedNode.rightChild)) + 1;
        transformationNode.height = Math.max(height(transformationNode.leftChild), height(transformationNode.rightChild)) + 1;
        return transformationNode;
    }

    //Depth First Search
    @SuppressWarnings("unused")
    public int recursive_count() {
        return recursive_count(root);
    }


    private int recursive_count(BinaryTreeNode <T> node) {
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
        LinkedList <Pair<Integer,BinaryTreeNode<T>>> queue = new LinkedList <> ();
        queue.offer(new Pair<>(0, root));
        boolean isOnNewLevel = true;
        var currentLevel = 0;
        StringBuilder currentLevelStr = new StringBuilder(String.format("L%s: ",currentLevel));
        while (!queue.isEmpty()) {
            Pair<Integer,BinaryTreeNode<T>> next = queue.poll();
            BinaryTreeNode <T> currentNode = next.second;
            int nodeLevel = next.first;
            if (currentLevel != nodeLevel) {
                //You are on a new level
                isOnNewLevel = true;
                //print the currentLevelStr
                //noinspection UnnecessaryToStringCall
                System.out.println(currentLevelStr.toString());
                currentLevel = nodeLevel;
                //make new currentLevelStr "L1: "
                currentLevelStr = new StringBuilder(String.format("L%s: ",currentLevel));
            }

            //add node.value to currentLevelStr
            if (isOnNewLevel){ //to add a comma or not -- that is the question
                isOnNewLevel = false;
            } else {
                currentLevelStr.append(", ");
            }
            currentLevelStr.append(currentNode.value);


            if (currentNode.leftChild != null) {
                queue.offer(new Pair<>(currentLevel + 1, currentNode.leftChild));
            }
            if (currentNode.rightChild != null) {
                queue.offer(new Pair<>(currentLevel + 1, currentNode.rightChild));
            }
        }
        System.out.println(currentLevelStr);
    }


    @SuppressWarnings("unused")
    public void recursive_print() {
        recursive_print(root);
    }


    private void recursive_print(BinaryTreeNode <T> node) {
        if (node != null) {
            System.out.println(node.value);
            recursive_print(node.leftChild);
            recursive_print(node.rightChild);
        }
    }


   @SuppressWarnings("unused")
   public void depth_first_search_non_recursive() {
        BinaryTreeNode <T> stackItem = root;
        LinkedList <BinaryTreeNode <T>> stack = new LinkedList<>();
        stack.push(stackItem);
        while (!stack.isEmpty()){
            stackItem = stack.pop();
            if (stackItem != null) {
                System.out.println(stackItem.value);
                stack.push(stackItem.rightChild);
                stack.push(stackItem.leftChild);
            }
        }
    }


private static class BinaryTreeNode <T extends Comparable<? super T>>{
    BinaryTreeNode <T> leftChild;
    BinaryTreeNode <T> rightChild;
    T value;
    int height = 1;
}



}
