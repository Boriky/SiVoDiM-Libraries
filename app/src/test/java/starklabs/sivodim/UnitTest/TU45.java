package starklabs.sivodim.UnitTest;

/**
 * Created by GINO on 24/06/2016.
 */

import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterContainer;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Utilities.MutableInteger;
import starklabs.sivodim.Drama.Presenter.ChapterPresenter;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.View.NewSpeechActivity;
import starklabs.sivodim.Drama.View.NewSpeechInterface;

import static org.mockito.Mockito.when;

/**
 * Test TU45 that tests a correct speech creation
 */
public class TU45 {
    @Test
    public void testCreation(){


        Context context=Mockito.mock(Context.class);
        context=context.getApplicationContext();
        when(context.getFilesDir()).thenReturn(new File("C:/Desktop"));


        String text="speech";
        Character character=new CharacterImpl.CharacterBuilder()
                .setName("character").build();

        String emotion="happyness";
        Chapter chapter=new ChapterImpl.ChapterBuilder()
                .setTitle("chapter").build();
        CharacterContainer characterContainer= Mockito.mock(CharacterContainer.class);
        ChapterPresenter chapterPresenter= new ChapterPresenterImpl(chapter,characterContainer,"chapterpresenter",new MutableInteger(0));
        NewSpeechInterface newSpeechInterface=new NewSpeechActivity();

       chapterPresenter.newSpeech(text,character,emotion,context);
        //System.out.println(chapterPresenter.getSpeeches(context.getApplicationContext()).getItem(0).getText());
    }
}
