package starklabs.libraries.TestUnity;

import org.junit.Test;

import starklabs.libraries.Model.Voice.MivoqVoice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Enrico on 27/06/16.
 */
public class TU22 {
    @Test

    public void testGetSampleText(){
        String textIT = MivoqVoice.getSampleText("it");
        String textDE = MivoqVoice.getSampleText("de");
        String textEN = MivoqVoice.getSampleText("en");
        String textFR = MivoqVoice.getSampleText("fr");

        assertNotEquals(textIT,textDE);
        assertNotEquals(textDE,textEN);
        assertNotEquals(textEN,textFR);
        assertNotEquals(textFR,textIT);


    }
}
