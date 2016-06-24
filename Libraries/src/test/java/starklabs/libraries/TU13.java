package starklabs.libraries;

import org.junit.Test;
import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.Language;
import starklabs.libraries.Model.Voice.MivoqVoice;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Enrico on 24/06/16.
 */
public class TU13 {
    @Test
    public void testSynthesizeToFile() {
        Language language = new Language("lingua");
        String name = "name";
        String myVoiceName = "myVoiceName";

        String path = "/Users/Enrico/Desktop/test/test.mp3";
        String text = "prova";
        MivoqVoice mivoqVoice = new MivoqVoice(name, myVoiceName, language);

        MivoqTTSSingleton mivoqTTSSingleton = Mockito.mock(MivoqTTSSingleton.class);

        try {
            mivoqTTSSingleton.synthesizeToFile(path,mivoqVoice,text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File file = new File(path);
        assertEquals(true,file.exists());
    }
}
