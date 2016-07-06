package starklabs.libraries.UnitTest;

/**
 * Created by GINO on 27/06/2016.
 */

import org.junit.Test;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import static org.junit.Assert.assertEquals;
/**
 * Test TU51 that tests if it is possible to get the voices list
 */
public class TU51 {
    @Test
    public void testGetVoice(){
        MivoqTTSSingleton mivoqTTSSingleton=MivoqTTSSingleton.getInstance();
        assertEquals("starklabs.libraries.Model.Mivoq.MivoqTTSSingleton",mivoqTTSSingleton.getClass().getName());
        boolean check=true;
        if(mivoqTTSSingleton.getVoices().isEmpty()){
            check=false;
        }
        assertEquals(true,check);
    }
}
