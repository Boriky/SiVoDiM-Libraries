package starklabs.sivodim.UnitTest;

/**
 * Created by GINO on 23/06/2016.
 */

import org.junit.Test;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test TU34 that tests a correct chapter creation from a string
 */
public class TU34 {
    @Test
    public void testChapterCreation(){
        String s="titolo";
        Chapter chapter=new ChapterImpl.ChapterBuilder()
                .setTitle("").build();
        chapter.setTitle("titolo");
        assertEquals(s,chapter.getTitle());
    }
}
