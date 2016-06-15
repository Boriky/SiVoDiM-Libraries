package starklabs.sivodim.Drama.Presenter;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.View.EditSpeechInterface;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public interface SpeechPresenter {

    // ----------------------------- ACTIVITY ----------------------------------------------

    /**
     * Set up a link to a related {@link EditSpeechInterface}
     * @param editSpeechInterface The {@link EditSpeechInterface} which uses this presenter
     */
    void setActivity(EditSpeechInterface editSpeechInterface);

    // ------------------------------ GETTER ----------------------------------------------

    /**
     * Gives the current text of the speech inside the presenter
     * @return
     */
    String getSpeechText();

    /**
     * Gives the current emotion of the speech inside the presenter
     * @return
     */
    String getSpeechEmotion();

    /**
     * Gives a reference to the Character who says the speech
     * @return
     */
    Character getSpeechCharacter();

    /**
     * Gives an Iterator of Character related to the current characters of the speech
     * @return
     */
    Iterator<Character> getScreenplayCharacters();


    // ------------------------------- SETTER ---------------------------------------------

    /**
     * Set the text of the speech
     * @param text The new text for the speech
     */
    void setSpeechText(String text);

    /**
     * Set the emotion of the speech
     * @param emotion
     */
    void setSpeechEmotion(String emotion);

    /**
     * Set the character of the speech
     * @param character
     */
    void setSpeechCharacter(Character character);
}
