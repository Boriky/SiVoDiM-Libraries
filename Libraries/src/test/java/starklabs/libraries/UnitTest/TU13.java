package starklabs.libraries.UnitTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.Language;
import starklabs.libraries.Model.Voice.MivoqVoice;


/**
 * Created by Enrico on 24/06/16.
 */
public class TU13 extends InstrumentationTestCase {
    @Test
    public void testSynthesizeToFile() {
        String name = "name";
        String myVoiceName = "myVoiceName";
        Language language = new Language("lingua");

        MivoqVoice mivoqVoice = new MivoqVoice(name, myVoiceName, language);
        String path = "/Desktop/test";
        String text = "prova";

        MivoqTTSSingleton mivoqTTSSingleton = MivoqTTSSingleton.getInstance();

        Context context = getInstrumentation().getContext();

        mivoqTTSSingleton.setContext(context);

        try {
            mivoqTTSSingleton.synthesizeToFile(path,mivoqVoice,text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File file=new File(path);
        assertEquals(true,file.exists());

    }
}
