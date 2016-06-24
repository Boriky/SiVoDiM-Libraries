package starklabs.sivodim;

/**
 * Created by GINO on 23/06/2016.
 */

import org.junit.Test;
import org.mockito.Mockito;

import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterContainer;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Presenter.SpeechPresenter;
import starklabs.sivodim.Drama.Presenter.SpeechPresenterImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test TU37 that tests the correct speech getter method functionality
 */
public class TU37 {
    @Test
    public void testSpeechGetter(){
        String text="speech";
        String path="C:/Desktop/prova.wav";
        String emotion= "Happyness";
        Character character=new CharacterImpl.CharacterBuilder()
                .setName("character").build();
        Speech speech=new SpeechImpl.SpeechBuilder()
                .setText(text)
                .setAudioPath(path)
                .setEmotion(emotion)
                .setCharacter(character).build();

        CharacterContainer characterContainer= Mockito.mock(CharacterContainer.class);
        SpeechPresenter speechPresenter=new SpeechPresenterImpl(speech,characterContainer);



        boolean check=true;

        if(!speechPresenter.getSpeechText().equals(text)){
            check=false;
        }

        if(!speechPresenter.getAudioPath().equals(path)){
            check=false;
        }

        if(!speechPresenter.getSpeechEmotion().equals(emotion)){
            check=false;
        }

        if(!speechPresenter.getSpeechCharacter().getName().equals(character.getName())){
            check=false;
        }

        assertEquals(true,check);
    }
}
