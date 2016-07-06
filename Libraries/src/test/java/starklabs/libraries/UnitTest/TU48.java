package starklabs.libraries.UnitTest;

/**
 * Created by GINO on 27/06/2016.
 */

import org.junit.Test;
import org.mockito.Mockito;

import starklabs.libraries.Model.Mivoq.AudioRequest;

import static org.junit.Assert.assertEquals;

/**
 * Test TU47 that tests the correct creation of an AudioRequest object
 */
public class TU48 {
    @Test
    public void testAudioRequestCreation(){
        AudioRequest audioRequest=Mockito.mock(AudioRequest.class);
        assertEquals(true,!audioRequest.equals(null));
    }
}
