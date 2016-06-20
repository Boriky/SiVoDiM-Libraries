package starklabs.libraries.Model.Integration;

import android.app.ListActivity;
import android.os.Bundle;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;

/**
 * Created by AlbertoAndriolo on 20/06/2016.
 */
public class DownloadVoiceData extends ListActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MivoqTTSSingleton engine= MivoqTTSSingleton.getInstance();

        if(!engine.hasContext())
            engine.setContext(this);

        finish();
    }


}