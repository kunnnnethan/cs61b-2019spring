import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void testUnion() {
        UnionFind test = new UnionFind(10);
        test.union(2,3);
        test.union(1,2);
        test.union(5,7);
        test.union(8,4);
        test.union(2,7);
        test.union(0,6);
        test.union(6,4);
        test.union(6,3);
        test.find(0);
        test.union(8,7);
        assertEquals(3, test.parent(0));
        assertEquals(3, test.parent(1));
        assertEquals(3, test.parent(2));
        assertEquals(-9, test.parent(3));
        assertEquals(3, test.parent(4));
        assertEquals(7, test.parent(5));
        assertEquals(4, test.parent(6));
        assertEquals(3, test.parent(7));
        assertEquals(3, test.parent(8));
        assertEquals(-1, test.parent(9));

    }

    @Test // Test wrote from other people
    public void testBasic() {
        UnionFind uf = new UnionFind(6);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 2);
        assertEquals(1, uf.parent(0));
        assertTrue(uf.connected(0, 2));
        uf.union(0, 2);
        assertEquals(3, uf.parent(0));
        assertEquals(4, uf.sizeOf(0));
        assertEquals(4, uf.sizeOf(1));
        assertEquals(4, uf.sizeOf(2));
        assertEquals(4, uf.sizeOf(3));
        assertTrue(uf.connected(1, 3));
        assertEquals(3, uf.find(0));

    }
}
