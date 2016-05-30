package starklabs.libraries.Model.Mivoq;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by AlbertoAndriolo on 27/05/2016.
 */
public class MivoqInfoImpl implements MivoqInfo{
    private JSONObject myResponse;
    private static RequestQueue myRequestQueue;

    public void setQueue(RequestQueue RQ) {myRequestQueue=RQ;}

    public void sendRequest(String url){

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                       // synchronized(this)

                            myResponse= response;
                            //this.notifyAll();

                    }}, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("Fede beve");
                            // TODO Auto-generated method stub
                        }
                    });

                    myRequestQueue.add(request);


                }

    public JSONObject getResponse(){
        return myResponse;
    }

}
