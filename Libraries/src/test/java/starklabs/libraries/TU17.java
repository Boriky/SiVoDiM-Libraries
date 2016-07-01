package starklabs.libraries;

/**
 * Created by GINO on 27/06/2016.
 */

import org.junit.Test;

import starklabs.libraries.Model.Voice.Emotion;
import static org.junit.Assert.assertEquals;
/**
 * Test TU17 that tests if it is possible to assign an emotion to a speech
 */
public class TU17 {
    @Test
    public void testEmotion(){
        String e="fear";
        Emotion emotion=new Emotion(e);
        assertEquals(true,!emotion.equals(null));
        assertEquals(e,emotion.toString());
    }
}
