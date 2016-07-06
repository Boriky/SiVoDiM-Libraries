package starklabs.libraries.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.Language;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Enrico on 01/07/16.
 */

public class TI15 extends InstrumentationTestCase {
    @Test
    public void testMivoqEngine() {
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
