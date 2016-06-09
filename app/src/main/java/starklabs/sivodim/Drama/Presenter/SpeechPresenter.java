package starklabs.sivodim.Drama.Presenter;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.View.EditSpeechInterface;

/**
 * Created by io on 25/05/2016.
 */
public interface SpeechPresenter {
    void newSpeech();
    String getSpeechText();
    String getSpeechEmotion();
    void setSpeechText(String text);
    Character getSpeechCharacter();
    Iterator<Character> getScreenplayCharacters();
    void setActivity(EditSpeechInterface editSpeechInterface);
    void setSpeechEmotion(String emotion);
    void setSpeechCharacter(Character character);
}
