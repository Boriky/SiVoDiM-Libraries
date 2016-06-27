package starklabs.sivodim;

import android.content.Context;

import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import starklabs.sivodim.Drama.Model.Screenplay.Mp3Converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Enrico on 23/06/16.
 */
//TU39 that test if you can convert a file into a mp3 format
public class TU39 {
    @Test
    public void testMp3Converter() {
        Context context= Mockito.mock(Context.class);
        File dir=new File("/Users/Enrico/Desktop/test");
        when(context.getFilesDir()).thenReturn(dir);
        File fileToConvert = new File("/Users/Enrico/Desktop/test/prova.wav");
        File destination = new File("/Users/Enrico/Desktop/test/prova.mp3");

        Mp3Converter mp3Converter = new Mp3Converter(context,fileToConvert,destination);
        mp3Converter.setFile(fileToConvert);
        mp3Converter.setDestination(destination);

        try {
            mp3Converter.exec();
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
/**
        //Extension check
        File file=new File(context.getFilesDir(),"/prova.mp3");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String estensione = "";
        int i = file.getPath().lastIndexOf('.');
        if (i >= 0) {
            estensione = file.getPath().substring(i + 1);
        }


        String mp3 = "mp3";
        boolean formato_giusto = true;
        if (!estensione.equals(mp3))
            formato_giusto = false;
        assertEquals(true, formato_giusto);
*/

    }
}
