package starklabs.libraries.UnitTest;

/**
 * Created by GINO on 27/06/2016.
 */

import org.junit.Test;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;

import static org.junit.Assert.assertNotEquals;

/**
 * Test TU50 that tests if is it possible to remove a voice created by user
 */
public class TU50 {
    @Test
    public void testRemoveVoice(){
        MivoqTTSSingleton mivoqTTSSingleton=MivoqTTSSingleton.getInstance();
        mivoqTTSSingleton.createVoice("name","gender","language");
        int check=mivoqTTSSingleton.getVoices().size();
        mivoqTTSSingleton.removeVoice(0);
        assertNotEquals(check,mivoqTTSSingleton.getVoices().size());

    }

}
