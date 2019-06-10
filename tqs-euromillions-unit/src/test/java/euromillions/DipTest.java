/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import jdk.nashorn.internal.ir.annotations.Ignore;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 *
 * @author ico0
 */
public class DipTest {
    
    private Dip instance;
	
	
    public DipTest() {
    }
    
    @BeforeEach
    public void setUp() {
    	instance = new Dip( new int[] {10,20,30,40,50}, new int[] {1,2});     
    }
    
    @AfterEach
    public void tearDown() {
    	instance= null;
    }

   
    @Test
    public void testConstructorFromBadArrays() 
    {
        fail();
        Assertions.assertThrows( IllegalArgumentException.class, () -> 
        {
           instance = new Dip( new int[] {10,20,30,40,50,60}, new int[] {1,2});    
        });        
    }
     
    @Test
    public void testFormat() {   
// note: correct the implementation, not the test ;)
        String result = instance.format();        
        assertEquals( "N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");        
    }

}
