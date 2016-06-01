package starklabs.libraries.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import starklabs.libraries.Model.Voice.Voice;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public interface VoicePresenter {

    Voice getVoice();

    void loadVoiceGender(Context context);
    ArrayAdapter<String> getGenderAdapter(Context context);

    void loadVoiceLanguage(Context context);
    ArrayAdapter<String> getLanguageAdapter(Context context);
}