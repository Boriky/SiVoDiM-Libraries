package starklabs.sivodim.UnitTest;

/**
 * Created by GINO on 23/06/2016.
 */

import org.junit.Test;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

import static org.junit.Assert.assertEquals;

/**
 * Test TU30 that tests if it is possible to delete a chapter soundtrack
 */
public class TU30 {
    @Test
    public void testDeleteSoundtrack() {
        Soundtrack soundtrack = new Soundtrack("C:/Desktop/prova.wav");
        Chapter chapter = new ChapterImpl.ChapterBuilder()
                .setTitle("capitolo1").build();
        chapter.setSoundtrack(soundtrack);
        String path=chapter.getSoundtrack().getAudio().getAbsolutePath();
        chapter.deleteSoundtrack();
        boolean check=false;
        if(chapter.getSoundtrack()==null){
            check=true;
        }
        assertEquals(true,check);
    }
}
