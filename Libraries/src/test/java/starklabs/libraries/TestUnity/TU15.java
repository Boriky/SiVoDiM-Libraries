package starklabs.libraries.TestUnity;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.MivoqVoice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Enrico on 24/06/16.
 */
public class TU15 {
    @Test
    public void testCreateVoice(){

        String name ="name";
        String gender = "male";
        String myLanguage = "de";

        String de = "dfki-stefan-hsmm";

        //Mock voice
        MivoqVoice mivoqVoiceFintoDe = Mockito.mock(MivoqVoice.class);
        when(mivoqVoiceFintoDe.getVoiceName()).thenReturn(de);
        when(mivoqVoiceFintoDe.getGender()).thenReturn(gender);
        when(mivoqVoiceFintoDe.getLanguage()).thenReturn(myLanguage);


        MivoqTTSSingleton mivoqTTSSingleton = MivoqTTSSingleton.getInstance();
        Context context = Mockito.mock(Context.class);
        mivoqTTSSingleton.setContext(context);

        //Create real voice
        MivoqVoice enri=mivoqTTSSingleton.createVoice(name,gender,myLanguage);

        assertEquals(true,enri.getGender()==mivoqVoiceFintoDe.getGender());
        assertEquals(true,enri.getLanguage()==mivoqVoiceFintoDe.getLanguage());
        assertEquals(true,enri.getVoiceName()==mivoqVoiceFintoDe.getVoiceName());

    }

}
