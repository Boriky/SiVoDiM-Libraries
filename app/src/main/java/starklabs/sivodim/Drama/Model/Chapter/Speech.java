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
     * Sets the text of the speech
     * @param text Text to the speech
     */
    void setText(String text);

    /**
     * Sets the emotion of the speech
     * @param emotionID
     */
    void setEmotion(String emotionID);

    /**
     * Sets the {@link Character} of the speech
     * @param character
     */
    void setCharacter(Character character);

    /**
     * Sets the {@link SoundFx} of the speech
     * @param soundFx
     */
    void setSoundFx(SoundFx soundFx);

    /**
     * Sets the {@link String} of the speech
     * @param audioPath
     */
    void setAudioPath(String audioPath);

    /**
     * Sets the {@link boolean} of the speech
     * @param audioStatus
     */
    void setAudioStatus(boolean audioStatus);


    // ----------------------------- GETTER ----------------------------------------------

    /**
     * Returns the text of the speech
     * @return
     */
    String getText();

    /**
     * Returns the emotion of the speech
     * @return
     */
    String getEmotion();

    /**
     * Returns the character of the speech
     * @return
     */
    Character getCharacter();

    /**
     * Return the duration in milliseconds of the speech
     * @return
     */
    long getDuration();

    /**
     * Creates the wav file with the synthesis of the speech and save it to path
     * @param context the application context
     * @param path the path where save
     */
    void synthesizeAudio(Context context,String path);

    /**
     * Returns the path where the synthesis is saved
     * @return
     */
    String getAudioPath();

    /**
     * True only if the speech has been synthesized
     * @return
     */
    boolean getAudioStatus();
}
