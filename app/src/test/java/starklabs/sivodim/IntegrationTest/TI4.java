package starklabs.sivodim.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

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
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

/**
 * Created by io on 06/07/2016.
 */
public class TI4 extends InstrumentationTestCase {
    @Test
    public void testDramaModel(){

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

        File backgroundFile=new File(context.getFilesDir(), "background.png");
        Background background=new Background(backgroundFile.getAbsolutePath());
        assertNotNull(background);

        File soundtrackFile=new File(context.getFilesDir(), "soundtrack.mp3");
        Soundtrack soundtrack=new Soundtrack(soundtrackFile.getAbsolutePath());
        assertNotNull(soundtrack);

        File avatarFile = new File(context.getFilesDir(), "avatar.png");
        Avatar avatar=new Avatar(avatarFile.getAbsolutePath());
        assertNotNull(avatar);

        chapter.setBackground(background);
        assertEquals(background,screenplay.getChapter("Capitolo 1").getBackground());

        chapter.setSoundtrack(soundtrack);
        assertEquals(soundtrack,screenplay.getChapter("Capitolo 1").getSoundtrack());

        character.setAvatar(avatar);
        assertEquals(avatar,screenplay.getCharacterByName("character").getAvatar());
    }
}
