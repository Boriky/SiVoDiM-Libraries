package starklabs.sivodim;

/**
 * Created by GINO on 23/06/2016.
 */

import org.junit.Test;

import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test TU36 that tests the correct chapter's getter method functionality
 */
public class TU36 {
    @Test
    public void testGetter(){
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

        boolean check=true;

        if(!speech.getText().equals(text)){
            check=false;
        }

        if(!speech.getAudioPath().equals(path)){
            check=false;
        }

        if(!speech.getEmotion().equals(emotion)){
            check=false;
        }

        if(!speech.getCharacter().getName().equals(character.getName())){
            check=false;
        }

        assertEquals(true,check);
    }

}
