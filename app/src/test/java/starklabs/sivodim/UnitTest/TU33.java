package starklabs.sivodim.UnitTest;

/**
 * Created by GINO on 23/06/2016.
 */

import org.junit.Test;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test TU33 that tests if it is possible to delete a chapter speech
 */
public class TU33 {
    @Test
    public void testDeleteSpeech() {
        Speech speech = new SpeechImpl.SpeechBuilder()
                .setText("ciao").build();
        Speech speech1 = new SpeechImpl.SpeechBuilder()
                .setText("ciao").build();
        Speech speech2 = new SpeechImpl.SpeechBuilder()
                .setText("ciao").build();
        Chapter chapter = new ChapterImpl.ChapterBuilder()
                .setTitle("capitolo1").build();
        chapter.addSpeech(speech);
        chapter.addSpeech(speech1);
        chapter.addSpeech(speech2);
        Iterator<Speech> it=chapter.getSpeechIterator();
        int c=0;
        while(it.hasNext()){if(!it.next().getText().isEmpty()){c++;}}
        chapter.deleteSpeech(speech);
        int d=0;
        Iterator<Speech> it1=chapter.getSpeechIterator();
        while(it1.hasNext()){if(!it1.next().getText().isEmpty()){d++;}}
        boolean check=false;
        if(c!=d){
            check=true;
        }
        assertEquals(true,check);
    }
}
