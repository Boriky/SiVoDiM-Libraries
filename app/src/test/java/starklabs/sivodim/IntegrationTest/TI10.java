package starklabs.sivodim.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.widget.TextView;

import org.junit.Test;

import java.io.File;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;

/**
 * Created by Francesco Bizzaro on 06/07/2016.
 */
public class TI10 extends InstrumentationTestCase{
    @Test
    public void testScreenplay(){

        Screenplay screenplay=new ScreenplayImpl("Test",0);
        assertNotNull(screenplay);
        Chapter chapter=new ChapterImpl.ChapterBuilder()
                .setTitle("Capitolo 1")
                .build();
        assertNotNull(chapter);
        screenplay.addChapter(chapter);
        assertEquals(chapter,screenplay.getChapter("Test"));

        Context context=getInstrumentation().getContext();
        assertNotNull(context);

        Engine engine=new EngineImpl(context);
        MivoqVoice voice=engine.createVoice("voice","male","it");

        Character character=new CharacterImpl.CharacterBuilder()
                .setName("character")
                .setVoice(voice.getName())
                .build();
        assertNotNull(character);
        screenplay.addCharacter(character);
        assertEquals(character,screenplay.getCharacterByName("character"));

    }
}
