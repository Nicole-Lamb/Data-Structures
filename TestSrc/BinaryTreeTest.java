import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    BinaryTree<Integer> testTree;

    @BeforeEach
    void setUp() {
        testTree = new BinaryTree<>();
    }

    @Test
    void height() {
        //height of 0 when empty
        assertEquals(0,testTree.height(),"Tree should be empty which has no height");
        //height of 1 when it has a root
        testTree.add(10);
        assertEquals(1,testTree.height());
        //height of 2 when it has a root and child
        testTree.add(5);
        assertEquals(2,testTree.height(),"This tree has a root with 1 child. Should have a height of 2.");
        testTree.add(15);
        assertEquals(2,testTree.height(),"Added another child. Should still only have a height of 2.");
    }

    @Test
    void is_empty() {
        assertTrue(testTree.is_empty());
    }

    @Test
    void size() {
        assertEquals(0,testTree.size(),"Why would a empty tree have a non-zero size?");
    }

    @Test
    @DisplayName("Case 1: Add an item to an empty tree")
    void addItemToEmptyTree() {
        int item1 = 1;
        testTree.add(item1);
        assertEquals(1,testTree.size());
        assertEquals(1,testTree.getRootValue());
    }

    @Test
    void insert() {
        fail("Not implemented.");
    }

    @Test
    void insert_balanced() {
        fail("Not implemented.");
    }

    @Test
    void recursive_count() {
        fail("Not implemented.");
    }

    @Test
    void print_tree() {
        fail("Not implemented.");
    }

    @Test
    void recursive_print() {
        fail("Not implemented.");
    }

    @Test
    void depth_first_search_non_recursive() {
        fail("Not implemented.");
    }
}