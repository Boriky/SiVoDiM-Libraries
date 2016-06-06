package starklabs.libraries.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.View.VoiceListActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public interface VoiceListPresenter {

    //ArrayAdapter<String> getVoices(Context context);

    void createVoiceList();

    void loadVoiceNames(Context context);

    ArrayAdapter<String> getVoicesAdapter(Context context);

    void setActivity(VoiceListActivityInterface voiceListActivityInterface);

    void goToEditVoiceActivity(Context context, MivoqVoice mivoqVoice);

    MivoqVoice createMivoqVoice(String s);
}
