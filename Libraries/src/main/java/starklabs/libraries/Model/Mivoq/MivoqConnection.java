package starklabs.libraries.Model.Mivoq;

import com.android.volley.RequestQueue;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public interface MivoqConnection {
    void setVoiceGender(String s);
    void setVoiceName(String s);
    void setLocale(String s);
    void setEffects(String s);
    void setQueue(RequestQueue rq);

    void sendRequest(String text);

    byte[] getResponse();
}
