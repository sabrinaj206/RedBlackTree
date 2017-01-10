import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class RedBlackTreeTest {
    RedBlackTree RBT;
    @Before
    public void setUp() throws Exception {
        RBT = new RedBlackTree();
    }


    public void Insert() {
        RBT.insert(2);
        System.out.println(RBT);
        RBT.insert(1);
        RBT.findNode(1).setColor(RedBlackTree.Color.BLACK);
        System.out.println(RBT);
        RBT.insert(3);
        RBT.findNode(3).setColor(RedBlackTree.Color.RED);
        System.out.println(RBT);
        RBT.insert(4);
        System.out.println(RBT);

        //assertEquals(2, RBT.findNode(1, RBT.root).parent.value);
        //System.out.println(RBT.getUncleColor(RBT.findNode(3, RBT.root)));
    }
    @Test
    public void Test() {
        for (int i = 0; i < 10; i++){
            Random r = new Random();
            int num = r.nextInt(1000);
            RBT.insert(num);
            System.out.print(num + " ");
        }
        System.out.println("\n"+RBT);
        RBT.clear();
    }
}
