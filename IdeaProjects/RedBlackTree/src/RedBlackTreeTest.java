import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {
    RedBlackTree RBT;
    @Before
    public void setUp() throws Exception {
        RBT = new RedBlackTree();
    }

    @Test
    public void Insert() {
        RBT.insert(2);
        System.out.println(RBT.print());
        RBT.insert(1);
        RBT.findNode(1).setColor(RedBlackTree.Color.BLACK);
        System.out.println(RBT.print());
        RBT.insert(3);
        RBT.findNode(3).setColor(RedBlackTree.Color.RED);
        System.out.println(RBT.print());
        RBT.insert(4);
        System.out.println(RBT.print());

        //assertEquals(2, RBT.findNode(1, RBT.root).parent.value);
        //System.out.println(RBT.getUncleColor(RBT.findNode(3, RBT.root)));
    }

}
