package starklabs.sivodim.UnitTest;
import org.junit.Test;
import org.mockito.Mockito;
import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Enrico on 23/06/16.
 */

//Test TU29 that test if you can set a soundrack to a chapter and if the size and the format are correct
public class TU29 {
    @Test
    public void testSetSoundtrack() {
        String path = "/Users/AlbertoAndriolo/Desktop/test/prova.wav";
        Soundtrack soundtrack = new Soundtrack(path);

        Chapter chapter = Mockito.mock(Chapter.class);
        when(chapter.getSoundtrack()).thenReturn(soundtrack);
        chapter.setSoundtrack(soundtrack);

        assertEquals(soundtrack, soundtrack.getAudio());
        assertEquals(path,chapter.getSoundtrack().getAudio().getAbsolutePath());


        //Extension check
        String estensione = "";
        int i = soundtrack.getAudio().getName().lastIndexOf('.');
        if (i >= 0) {
            estensione = soundtrack.getAudio().getName().substring(i + 1);
        }

        String wav = "wav";
        String mp3 = "mp3";
        boolean formato_giusto = true;
        if (!estensione.equals(wav) && !estensione.equals(mp3))
            formato_giusto = false;
        assertEquals(true, formato_giusto);

        //Size check
        long size = soundtrack.getAudio().length();
        boolean size_giusta = false;
        if (size <= 15728640L) {
            size_giusta = true;
        }
        assertEquals(true, size_giusta);

    }
}
