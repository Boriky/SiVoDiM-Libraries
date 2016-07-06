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
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.Model.Character.Character;

import static org.junit.Assert.assertNotEquals;
/**
 * Created by Enrico on 01/07/16.
 */
public class TI1 extends InstrumentationTestCase {
    @Test
    public void testTotal(){

        Screenplay screenplay=new ScreenplayImpl("Test",0);
        Chapter chapter=new ChapterImpl.ChapterBuilder()
                .setTitle("Capitolo 1")
                .build();
        screenplay.addChapter(chapter);
        Context context=getInstrumentation().getContext();
        assertNotNull(context);
        Engine engine=new EngineImpl(context);

        MivoqVoice voice=engine.createVoice("voice","male","it");
        Character character=new CharacterImpl.CharacterBuilder()
                .setName("character")
                .setVoice(voice.getName())
                .build();
        Speech speech=new SpeechImpl.SpeechBuilder()
                .setText("Ciao")
                .setCharacter(character)
                .setEmotion("HAPPINESS")
                .build();
        chapter.addSpeech(speech);
        TextView textView=new TextView(context);
        screenplay.export("Audio",context,textView);
        File file=new File(context.getFilesDir(),"Test.mp3");
        System.out.println(file.exists());
        assertEquals(true,file.exists());

    }
}