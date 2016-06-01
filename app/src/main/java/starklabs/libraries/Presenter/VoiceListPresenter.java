package starklabs.libraries.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public interface VoiceListPresenter {

    //ArrayAdapter<String> getVoices(Context context);

    void createVoiceList();

    void loadVoiceNames(Context context);

    ArrayAdapter<String> getVoicesAdapter(Context context);

}
