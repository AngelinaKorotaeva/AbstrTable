package kolekce;
import abstrTable.AbstrTable;
import enumClass.ETypProhl;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class AbstrTableTest {
    private static class TestClass {

        int a;

        public TestClass(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "T" + a;
        }

        public int getA() {
            return a;
        }
        

    }
    /***
     * Sada instancí testovací třídy pro ověření implementace třídy SpojovySeznam
     */
    private final TestClass T1 = new TestClass(1);
    private final TestClass T2 = new TestClass(2);
    private final TestClass T3 = new TestClass(3);
    private final TestClass T4 = new TestClass(4);
    private final TestClass T5 = new TestClass(5);
    private final TestClass T6 = new TestClass(6);
    private final TestClass T7 = new TestClass(7);
    private final TestClass T8 = new TestClass(8);
    private final TestClass T9 = new TestClass(9);
    private final TestClass T31 = new TestClass(31);
    private final TestClass T92 = new TestClass(92);
    private final TestClass T50 = new TestClass(50);
    private final TestClass T77 = new TestClass(77);

    public AbstrTableTest() {
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
   
    @Test
    public void test_01_Najdi() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T1.getA(), T1);
            int expected = T1.getA();
            TestClass result = instance.najdi(T1.getA());            
            assertEquals(expected, result.getA());
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void test_02_Najdi() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T3.getA(), T3);
            instance.vloz(T1.getA(), T1);
            int expected = T1.getA();
            TestClass result = instance.najdi(T1.getA());            
            assertEquals(expected, result.getA());
        } catch (Exception ex) {
            fail();
        }
    }
    @Test
    public void test_01_Vloz() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T3.getA(), T3);
            instance.vloz(T1.getA(), T1);
            TestClass[] result = new TestClass[4];     
            Iterator it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T1, T3, T5, T8};           
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    @Test
    public void test_02_Vloz() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T3.getA(), T3);
            instance.vloz(T1.getA(), T1);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T1.getA(), T1);
            instance.vloz(T9.getA(), T9);
            instance.vloz(T2.getA(), T2);
            TestClass[] result = new TestClass[6];     
            Iterator it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T1, T2, T3, T5, T8, T9};           
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_03_Vloz() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T50.getA(), T50);
            instance.vloz(T77.getA(), T77);
            instance.vloz(T92.getA(), T92);
            instance.vloz(T31.getA(), T31);
            instance.vloz(T1.getA(), T1);
            TestClass[] result = new TestClass[6];     
            Iterator it = instance.vytvorIterator(ETypProhl.SIRKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T5, T1, T50, T31, T77, T92};           
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
 
    @Test(expected = NullPointerException.class)
    public void test_01_Exceptions() throws NullPointerException {
        AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
        instance.vloz(null, null);
        fail();
    }
    @Test(expected = NullPointerException.class)
    public void test_02_Exceptions() throws NullPointerException {
        AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
        instance.vloz(null, T3);
        fail();
    }
    @Test(expected = NullPointerException.class)
    public void test_03_Exceptions() throws NullPointerException {
        AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
        instance.odeber(T4.getA());
        fail();
    }

    @Test
    public void test_01_Select() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T3.getA(), T3);
            instance.vloz(T1.getA(), T1);
            
            TestClass expected = T8;
            TestClass result = instance.select(4);   
            assertEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_02_Select() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T3.getA(), T3);
            instance.vloz(T1.getA(), T1);
            
            TestClass expected = T5;
            TestClass result = instance.select(3);     
            assertEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_01_Odeber() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T3.getA(), T3);
            instance.vloz(T1.getA(), T1);
            instance.odeber(T1.getA());
            
            TestClass[] result = new TestClass[3];     
            Iterator it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T3, T5, T8};
                     
            assertArrayEquals(expected, result);
            
            instance.odeber(T8.getA());
            
            TestClass[] result2 = new TestClass[2];     
            it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            i = 0;
            while (it.hasNext()) {
                result2[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected2 = {T3, T5};
            assertArrayEquals(expected2, result2);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_02_Odeber() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T3.getA(), T3);
            instance.vloz(T1.getA(), T1);
            instance.odeber(T3.getA());
            
            TestClass[] result = new TestClass[3];     
            Iterator it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T1, T5, T8};
                     
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_03_Odeber() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T2.getA(), T2);
            instance.vloz(T5.getA(), T5);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T9.getA(), T9);
            instance.odeber(T8.getA());
            
            TestClass[] result = new TestClass[3];     
            Iterator it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T2, T5, T9};
                     
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_04_Odeber() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T2.getA(), T2);
            instance.vloz(T5.getA(), T5);
            instance.vloz(T8.getA(), T8);
            instance.odeber(T5.getA());
            
            TestClass[] result = new TestClass[2];     
            Iterator it = instance.vytvorIterator(ETypProhl.SIRKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T2, T8};
                     
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_05_Odeber() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T2.getA(), T2);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T9.getA(), T9);
            instance.vloz(T4.getA(), T4);
            
            instance.odeber(T5.getA());
            instance.odeber(T8.getA());
            
            TestClass[] result = new TestClass[3];     
            Iterator it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T2, T4, T9};
            assertArrayEquals(expected, result);
            
            instance.odeber(T4.getA());
            TestClass[] result2 = new TestClass[2];     
            it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            i = 0;
            while (it.hasNext()) {
                result2[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected2 = {T2, T9};
            assertArrayEquals(expected2, result2);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_06_Odeber() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T4.getA(), T4);
            instance.vloz(T8.getA(), T8);
            instance.vloz(T9.getA(), T9);
            instance.vloz(T2.getA(), T2);
            instance.vloz(T7.getA(), T7);
            
            instance.odeber(T4.getA());
            
            TestClass[] result = new TestClass[5];     
            Iterator it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T2, T5, T7, T8, T9};
            assertArrayEquals(expected, result);
            
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_07_Odeber() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T1.getA(), T1);
            instance.vloz(T3.getA(), T3);
            instance.vloz(T4.getA(), T4);
            instance.vloz(T2.getA(), T2);
            
            instance.odeber(T1.getA());
            
            TestClass[] result = new TestClass[4];     
            Iterator it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T2, T3, T4, T5};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_08_Odeber() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T2.getA(), T2);
            instance.vloz(T3.getA(), T3);
            instance.vloz(T4.getA(), T4);
            instance.vloz(T1.getA(), T1);
            
            instance.odeber(T2.getA());
            
            TestClass[] result = new TestClass[4];     
            Iterator it = instance.vytvorIterator(ETypProhl.HLOUBKA);
            int i = 0;
            while (it.hasNext()) {
                result[i] = (TestClass) it.next();
                i++;
            }
            
            TestClass[] expected = {T1, T3, T4, T5};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void test_01_rank() {
        try {
            AbstrTable<Integer, TestClass> instance = new AbstrTable<>();
            instance.vloz(T5.getA(), T5);
            instance.vloz(T2.getA(), T2);
            instance.vloz(T3.getA(), T3);
            instance.vloz(T4.getA(), T4);
            instance.vloz(T1.getA(), T1);
            
            int result = instance.rank(T3.getA());
            
            assertEquals(3, result);
            
            result = instance.rank(T5.getA());
            
            assertEquals(5, result);
            
            instance.odeber(T3.getA());
            
            result = instance.rank(T4.getA());
            
            assertEquals(3, result);
        } catch (Exception ex) {
            fail();
        }
    }  
}
