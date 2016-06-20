package starklabs.libraries.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.View.EditVoiceActivityInterface;
import starklabs.libraries.View.NewVoiceActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public interface VoicePresenter {

    /** Method to set Activity to the VoicePresenter
     *
     * @param newVoiceActivityInterface
     */
    void setActivity(NewVoiceActivityInterface newVoiceActivityInterface);

    /** Method to set Activity to the VoicePresenter
     *
     * @param editVoiceActivityInterface
     */
    void setActivity(EditVoiceActivityInterface editVoiceActivityInterface);

    /** Method to create Array with Gender list
     *
     * @param context
     */
    void loadVoiceGender(Context context);

    /** Method that returns an ArrayAdapter with Gender list
     *
     * @param context
     * @return
     */
    ArrayAdapter<String> getGenderAdapter(Context context);

    /** Method to create Array with Language list
     *
     * @param context
     */
    void loadVoiceLanguage(Context context);

    /** Method that returns an ArrayAdapter with Language list
     *
     * @param context
     * @return
     */
    ArrayAdapter<String> getLanguageAdapter(Context context);

    String getVoiceName();

    MivoqVoice getVoice();

    Engine getEngine();

    void setGender(int pos);

    void setLanguage(int pos);

    String getLanguage();
}