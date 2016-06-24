package starklabs.sivodim;
import org.junit.Test;
import org.mockito.Mockito;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;


/**
 * Created by Enrico on 21/06/16.
 */
public class TU7 {
    @Test
    public void testAddChapter(){
        ScreenplayImpl screenplay=new ScreenplayImpl("titolo");
        Chapter chapter = Mockito.mock(Chapter.class);
        String titolo="TitoloCapitolo";
        when(chapter.getTitle()).thenReturn(titolo);
        screenplay.addChapter(chapter);
        assertEquals(titolo,screenplay.getChapter(titolo).getTitle());
    }
}
