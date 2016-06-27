package starklabs.libraries.Presenter;

import android.content.Context;

import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.View.VoiceListActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public interface VoiceListPresenter {

    /** Method to switch from VoiceListActivity to EditVoiceActivity
     *
     * @param context
     * @param mivoqVoice
     */
    void goToEditVoiceActivity(Context context, MivoqVoice mivoqVoice);

    /** Method to set Activity to the VoiceListPresenter
     *
     * @param voiceListActivityInterface
     */
    void setActivity(VoiceListActivityInterface voiceListActivityInterface);

    /** Method to create MivoqVoice from the engine
     *
     * @param s
     * @return
     */
    MivoqVoice createMivoqVoice(String s);

    /** Method to load create ArrayAdapter with VoiceNames
     *  and add VoiceName
     * @param context
     */
    void loadVoiceNames(Context context);

    /** Method that return an ArrayAdapter with the VoiceName
     *
     * @param context
     * @return
     */
    StringArrayAdapter getVoicesAdapter(Context context);

    void setVoiceSelected(int index, String name);

    void deleteVoiceSelected(Context context);
}
