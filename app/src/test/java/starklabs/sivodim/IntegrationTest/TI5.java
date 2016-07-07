package starklabs.sivodim.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

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
import starklabs.sivodim.Drama.Model.Utilities.MutableInteger;
import starklabs.sivodim.Drama.Presenter.ChapterPresenter;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.CharacterPresenter;
import starklabs.sivodim.Drama.Presenter.CharacterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.Drama.Presenter.SpeechPresenter;
import starklabs.sivodim.Drama.Presenter.SpeechPresenterImpl;

/**
 * Created by Francesco Bizzaro on 06/07/2016.
 */
public class TI5 extends InstrumentationTestCase {

    @Test
    public void testLibrariesPresenters(){
        Screenplay screenplay=new ScreenplayImpl("Screenplay",0);
        assertNotNull(screenplay);
        ScreenplayPresenter screenplayPresenter=new ScreenplayPresenterImpl(screenplay);
        assertNotNull(screenplayPresenter);

        Context context=getInstrumentation().getContext();
        Engine engine=new EngineImpl(context);
        MivoqVoice voice=engine.createVoice("voice","male","it");

        Character character=new CharacterImpl.CharacterBuilder()
                .setName("character")
                .setVoice(voice.getName())
                .build();
        assertNotNull(character);

        screenplayPresenter.addCharacter(character);
        assertEquals(character,screenplayPresenter.getScreenplay().getCharacterByName("character"));
        assertEquals("Screenplay",screenplayPresenter.getChapterSelectedName());

        screenplayPresenter.newChapter(context,"Capitolo 1",null,null);
        assertNotNull(screenplay.getChapter("Capitolo 1"));
        screenplayPresenter.setChapterSelected(0,"Capitolo 1");
        assertEquals(0,screenplayPresenter.getChapterSelected());

        Chapter chapter=new ChapterImpl.ChapterBuilder()
                .setTitle("Capitolo 1")
                .build();
        assertNotNull(chapter);

        ChapterPresenter chapterPresenter =new ChapterPresenterImpl(chapter,
                screenplay.getCharacters(),screenplay.getTitle(),new MutableInteger(1));
        assertNotNull(chapterPresenter);

        chapterPresenter.newSpeech("testo di prova",
                screenplay.getCharacterByName("character"),
                "HAPPINESS",context);
        chapterPresenter.setSpeechSelected(0);
        assertEquals(0,chapterPresenter.getSpeechSelected());


        Speech speech=new SpeechImpl.SpeechBuilder()
                .setText("Ciao")
                .setCharacter(character)
                .setEmotion("HAPPINESS")
                .build();
        assertNotNull(speech);

        SpeechPresenter speechPresenter=new SpeechPresenterImpl(speech,screenplay.getCharacters());
        assertNotNull(speechPresenter);
        assertEquals(speech.getEmotion(),speechPresenter.getSpeechEmotion());
        assertEquals(speech.getAudioPath(),speechPresenter.getAudioPath());
        assertEquals(speech.getText(),speechPresenter.getSpeechText());
        assertEquals(speech.getCharacter(),speechPresenter.getSpeechCharacter());

        CharacterPresenter characterPresenter=new CharacterPresenterImpl(screenplay.getCharacters(),
                screenplay.getTitle());
        assertNotNull(characterPresenter);
        assertEquals(screenplay.getTitle(),characterPresenter.getProjectName());
        characterPresenter.newCharacter("Character name",voice.getVoiceName(),null);
        assertEquals("Character name",characterPresenter.getCharacter().getName());
        assertEquals(voice.getName(),characterPresenter.getCharacter().getVoiceID());
    }
}
