package starklabs.sivodim;

/**
 * Created by GINO on 23/06/2016.
 */

import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.CharacterContainer;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Utilities.MutableInteger;
import starklabs.sivodim.Drama.Presenter.ChapterPresenter;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

/**
 * Test TU28 tha tests if it is possible to modify the order of a speech list in a chapter
 */
public class TU28 {
    @Test
    public void testMoveSpeech() {
        CharacterContainer characterContainer = Mockito.mock(CharacterContainer.class);
        Context context= Mockito.mock(Context.class);
        String project = "name";

        Chapter chapter = new ChapterImpl.ChapterBuilder()
                .setTitle("j,yfg,yg").build();
        chapter.setTitle("capitolo1");
        Chapter chapter2 = new ChapterImpl.ChapterBuilder()
                .setTitle("j,yfg,yg").build();
        chapter.setTitle("capitolo1");
        Speech speech1 = Mockito.mock(SpeechImpl.class);
        Speech speech2 = Mockito.mock(SpeechImpl.class);
        Speech speech3 = Mockito.mock(SpeechImpl.class);


        when(speech1.getText()).thenReturn("ciao");
        when(speech2.getText()).thenReturn("come");
        when(speech3.getText()).thenReturn("stai");

        chapter.addSpeech(speech1);
        chapter.addSpeech(speech2);
        chapter.addSpeech(speech3);

        chapter2.addSpeech(speech1);
        chapter2.addSpeech(speech2);
        chapter2.addSpeech(speech3);

        ChapterPresenter chapterPresenter = new ChapterPresenterImpl(chapter, characterContainer, project, new MutableInteger(0));
        ChapterPresenter chapterPresenter1 = new ChapterPresenterImpl(chapter2, characterContainer, project,new MutableInteger(0));


        chapterPresenter.moveDownSpeech(0);
        Iterator<Speech> it=chapter.getSpeechIterator();
        Iterator<Speech> it1=chapter2.getSpeechIterator();
        int c=0;
            boolean down=true;
            while(it.hasNext()){
                if(it.next().getText().equals(it1.next().getText())){
                    c++;
                }
    }
        if(c==3){down=false;}
        chapterPresenter.moveUpSpeech(1);
        Iterator<Speech> it2=chapter.getSpeechIterator();
        Iterator<Speech> it3=chapter2.getSpeechIterator();
        boolean up=true;
        int d=0;
        while(it2.hasNext() && up){
            if(it2.next().getText().equals(it3.next().getText())){
               c++;
            }
        }
        if(c<3){down=false;}
        assertEquals(true,down);
        assertEquals(true,up);
}


}