package starklabs.libraries.Presenter;

import android.content.Context;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public interface HomePresenter {
    /** Method to switch from HomeActivity to VoiceListActivity
     *
     * @param context
     */
    void goToVoiceListActivity(Context context);

    /** Method to switch from HomeActivity to NewVoiceActivity
     *
     * @param context
     */
    void goToNewVoiceActivity(Context context);

    //void setActivity(VoiceListActivityInterface voiceListActivityInterface);
}
