/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msoede
 */
public class AlfaBetaSearchTest {

    public AlfaBetaSearchTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    

    /**
     * Test of max method, of class AlfaBetaSearch.
     */
    @Test
    public void testMax() {
        System.out.println("max");
        AlfaBetaSearch instance = new AlfaBetaSearch();
        assertEquals(instance.max(5, 3), 5);
        assertEquals(instance.max(5, 4), 5);
        assertEquals(instance.max(5, 5), 5);
        assertEquals(instance.max(5, 6), 6);
        assertEquals(instance.max(5, 7), 7);
        assertEquals(instance.max(5, 8), 8);
        System.out.println("max done");
    }

    /**
     * Test of min method, of class AlfaBetaSearch.
     */
    @Test
    public void testMin() {
        System.out.println("min");
        AlfaBetaSearch instance = new AlfaBetaSearch();
        assertEquals(instance.min(5, 3), 3);
        assertEquals(instance.min(5, 4), 4);
        assertEquals(instance.min(5, 5), 5);
        assertEquals(instance.min(5, 6), 5);
        assertEquals(instance.min(5, 7), 5);
        System.out.println("min done");
    }
}
