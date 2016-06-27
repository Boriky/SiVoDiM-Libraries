package starklabs.libraries;

/**
 * Created by GINO on 27/06/2016.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

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
