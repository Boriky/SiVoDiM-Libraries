package starklabs.sivodim;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

import static org.junit.Assert.*;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import static org.mockito.Mockito.when;
/**
 * Created by Enrico on 21/06/16.
 */
/**
 * test TU5 that test the correct exportation of a ScreenPlay
 */
public class TU5 {
    @Test
            public void testExport(){
        Context context = Mockito.mock(Context.class);
        when(context.getFilesDir()).thenReturn(new File("C:/Desktop"));
        Screenplay screenplay=Mockito.mock(ScreenplayImpl.class);
        when(screenplay.getTitle()).thenReturn("titolo");
        //screenplay.export("Audio",context);

        File file=new File(context.getFilesDir(),"/titolo.mp3");

        assertEquals(true,file.exists());
    }



}
