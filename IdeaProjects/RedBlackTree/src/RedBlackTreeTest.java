import org.junit.*;

import java.util.*;

public class RedBlackTreeTest {
    RedBlackTree RBT;

    @Before
    public void setUp() throws Exception {
        RBT = new RedBlackTree();
    }

    @Test
    public void Insert() {
        RBT.insert(878);
        RBT.insert(538);
        RBT.insert(405);
        RBT.insert(368);
        RBT.insert(493);
        RBT.insert(24);
        RBT.insert(316);
        RBT.insert(339);

        //RBT.findNode(2).setColor(RedBlackTree.Color.RED);
        //RBT.findNode(3).setColor(RedBlackTree.Color.RED);

        System.out.println(RBT);
        Assert.assertTrue(RBT.hasNoDoubleReds());
    }

    @Test
    public void Test() {
        for (int runs = 0; runs < 10000; runs++) {
            ArrayList<Integer> nums = new ArrayList<Integer>();
            for (int i = 0; i < 10; i++) {
                Random r = new Random();
                int num = r.nextInt(100000);
                RBT.insert(num);
                nums.add(num);
                if (!RBT.hasBlackRoot() || !RBT.hasNoDoubleReds()) {
                    System.out.println(nums.toString() + "\n" + RBT);
                    System.out.println("Has black root: " + RBT.hasBlackRoot());
                    System.out.println("Has no double reds: " + RBT.hasNoDoubleReds() + "\n");
                    return;
                }
            }
            RBT.clear();
        }
    }
}
