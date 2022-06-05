public class Tester {
    public static void main(String[] args){
        test_add_items_already_added();
    }
    public static void test_add_items_already_added() { //Case 2: With items in it
        BinaryTree printedTree = new BinaryTree();
        int item1 = 1;
        printedTree.add(item1);
        int item0 = 0;
        printedTree.add(item0);
        int item2 = 2;
        printedTree.add(item2);
        printedTree.print_tree();
    }


}


