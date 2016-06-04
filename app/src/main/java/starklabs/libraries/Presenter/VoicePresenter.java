package starklabs.libraries.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import starklabs.libraries.Model.Voice.Voice;
import starklabs.libraries.View.EditVoiceActivity;
import starklabs.libraries.View.NewVoiceActivity;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public interface VoicePresenter {

    Voice getVoice();

    void loadVoiceGender(Context context);
    ArrayAdapter<String> getGenderAdapter(Context context);

    void loadVoiceLanguage(Context context);
    ArrayAdapter<String> getLanguageAdapter(Context context);

    void setActivity(NewVoiceActivity newVoiceActivity);
    void setActivity(EditVoiceActivity editVoiceActivity);
    void goToNewVoiceActivity(Context context);
    void goToEditVoiceActivity(Context context);
}