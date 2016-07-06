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
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;

/**
 * Created by AlbertoAndriolo on 06/07/2016.
 */
public class TI12 extends InstrumentationTestCase {
    @Test
    public void testUtilites() {
        Context context=getInstrumentation().getContext();
        File backgroundFile=new File(context.getFilesDir(), "background.png");
        Background background=new Background(backgroundFile.getAbsolutePath());
        assertNotNull(background);

        File soundtrackFile=new File(context.getFilesDir(), "soundtrack.mp3");
        Soundtrack soundtrack=new Soundtrack(soundtrackFile.getAbsolutePath());
        assertNotNull(soundtrack);

        File avatarFile = new File(context.getFilesDir(), "avatar.png");
        Avatar avatar=new Avatar(avatarFile.getAbsolutePath());
        assertNotNull(avatar);

        Engine engine=new EngineImpl(context);
        MivoqVoice voice=engine.createVoice("Voice1","female","de");
        assertNotNull(engine.getVoiceByName("Voice1"));

        Character character=new CharacterImpl.CharacterBuilder()
                .setName("character1")
                .setVoice("Voice1")
                .setAvatar(avatar)
                .build();

        assertNotNull(character);
        assertEquals(avatar,character.getAvatar());

        Chapter chapter=new ChapterImpl.ChapterBuilder()
                .setTitle("chapter1")
                .setBackground(background)
                .setSoundtrack(soundtrack)
                .build();

        assertNotNull(chapter);
        assertEquals(background,chapter.getBackground());
        assertEquals(soundtrack,chapter.getSoundtrack());

        Speech speech=new SpeechImpl.SpeechBuilder()
                .setEmotion("NONE")
                .setText("this is a speech")
                .setCharacter(character)
                .build();

        assertNotNull(speech);

        chapter.addSpeech(speech);
        File synthDest=new File(context.getFilesDir(),"speech_1.wav");
        speech.synthesizeAudio(context,synthDest.getAbsolutePath());

        assertEquals(synthDest,speech.getAudioPath());
        SpeechSound speechSound=new SpeechSound(speech.getAudioPath());
        assertNotNull(speechSound);

    }
}
