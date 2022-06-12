import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

@SuppressWarnings("ALL")
public class Tester implements Comparable {
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

    String aValue = "A Value";


    @Override
    public int compareTo(@NotNull Object o) {
        int returnValue = 0;
        if (o instanceof Tester){
            returnValue = aValue.compareTo(((Tester) o).aValue);
        }
        return returnValue;
    }

    @Override
    public boolean equals(Object o) {
        boolean returnValue = false;
        if (this == o){
            returnValue = true;
        } else if (o instanceof Tester) {
            returnValue = aValue == ((Tester) o).aValue;
        }
        return returnValue;
    }

}


