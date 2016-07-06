package starklabs.sivodim;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
/**
 * Created by Enrico on 21/06/16.
 */
public class TU11 {
    @Test
    public void testAddSpeech(){
        Chapter chapter=
                new ChapterImpl.ChapterBuilder()
                        .setTitle("titolo")
                        .build();
        Speech speech=Mockito.mock(SpeechImpl.class);
        chapter.addSpeech(speech);

        Iterator<Speech> iterator =chapter.getSpeechIterator();
        boolean found=false;

        while(!found && iterator.hasNext()){
            Speech speech1 = iterator.next();
            if(speech1 == speech) found=true;
        }
        assertEquals(true,found);
    }
}
