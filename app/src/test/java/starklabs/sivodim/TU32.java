package starklabs.sivodim;

/**
 * Created by GINO on 23/06/2016.
 */

import org.junit.Test;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

import static org.junit.Assert.assertEquals;

/**
 * Test TU32 that tests if it is possible to delite a chapter background
 */
public class TU32 {
    @Test
    public void testDeleteBackground() {
        Background background = new Background("C:/Desktop/prova.png");
        Chapter chapter = new ChapterImpl.ChapterBuilder()
                .setTitle("capitolo1").build();
        chapter.setBackground(background);
        String path=chapter.getBackground().getPath();
        chapter.deleteBackground();
        boolean check=false;
        if(chapter.getBackground()==null){
            check=true;
        }
        assertEquals(true,check);
    }

}
