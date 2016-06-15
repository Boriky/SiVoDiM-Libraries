package starklabs.sivodim.Drama.Model.Chapter;

import android.content.Context;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.SoundFx;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public interface Speech extends Serializable {

    // ----------------------------- SETTER ----------------------------------------------

    /**
     * Sets the text for the speech
     * @param text Text to the speech
     */
    void setText(String text);

    /**
     * Sets the emotion for the speech
     * @param emotionID
     */
    void setEmotion(String emotionID);

    /**
     * Sets the {@link Character} for the speech
     * @param character
     */
    void setCharacter(Character character);

    /**
     * Sets the {@link SoundFx} for the speech
     * @param soundFx
     */
    void setSoundFx(SoundFx soundFx);

    /**
     * Sets the {@link String} for the speech
     * @param audioPath
     */
    void setAudioPath(String audioPath);

    /**
     * Sets the {@link boolean} for the speech
     * @param audioStatus
     */
    void setAudioStatus(boolean audioStatus);


    // ----------------------------- GETTER ----------------------------------------------

    /**
     * Gives the text of the speech
     * @return
     */
    String getText();

    /**
     * Gives the emotion of the speech
     * @return
     */
    String getEmotion();

    /**
     * Gives the character of the speech
     * @return
     */
    Character getCharacter();

    void synthesizeAudio(Context context,String path);

    String getAudioPath();

    boolean getAudioStatus();
}
