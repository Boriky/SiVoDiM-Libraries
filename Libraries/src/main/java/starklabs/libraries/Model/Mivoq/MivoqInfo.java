package starklabs.libraries.Model.Mivoq;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

/**
 * Created by AlbertoAndriolo on 27/05/2016.
 */
public interface MivoqInfo {
    public void setQueue(RequestQueue RQ);
    public void sendRequest(String url);
    public JSONObject getResponse();
}
