package starklabs.libraries.Model.Mivoq;

import com.android.volley.RequestQueue;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public interface MivoqConnection {
    public void setVoiceGender(String s);
    public void setVoiceName(String s);
    public void setLocale(String s);
    public void setEffects(String s);
    public void setQueue(RequestQueue RQ);

    public void sendRequest(String Text);

    public byte[] getResponse();
}
