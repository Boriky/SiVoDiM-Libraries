package starklabs.libraries.Presenter;

import android.content.Context;

import starklabs.libraries.View.VoiceListActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public interface HomePresenter {
    void setActivity(VoiceListActivityInterface voiceListActivityInterface);
    void goToVoiceListActivity(Context context);
    void goToNewVoiceActivity(Context context);
}
