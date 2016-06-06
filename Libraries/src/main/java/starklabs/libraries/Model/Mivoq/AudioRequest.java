package starklabs.libraries.Model.Mivoq;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class AudioRequest extends Request<byte[]> {

    private Response.ErrorListener mErrorListener;
    private Response.Listener<byte[]> mListener;
    private boolean delivered=false;

    private Map<String, String> mParams;
    //create a static map for directly accessing headers
    public Map<String, String> responseHeaders ;

    public AudioRequest(int post, String mUrl, Map<String, String> params, Response.Listener<byte[]> listener,
                        Response.ErrorListener errorListener) {
        // TODO Auto-generated constructor stub

        super(post, mUrl, errorListener);
        mErrorListener=errorListener;
        // this request would never use cache.
        setShouldCache(false);
        mListener = listener;
        mParams=params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        return headers;
    }

    @Override
    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError
    {
        return mParams;
    };

    @Override
    protected void deliverResponse(byte[] response)
    {
        if(!delivered) {
            System.out.println("Risposta Consegnata");
            delivered=true;
            mListener.onResponse(response);
        }
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null
                && volleyError.networkResponse.data != null) {
            VolleyError error = new VolleyError(new String(
                    volleyError.networkResponse.data));
            volleyError = error;
        }

        return volleyError;
    }

    @Override
    public void deliverError(VolleyError error) {
        mErrorListener.onErrorResponse(error);
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response)
    {
            System.out.println("Risposta Arrivata");

            //Initialise local responseHeaders map with response headers received
            responseHeaders = response.headers;

            System.out.println(response.data.length);
            deliverResponse(response.data);
            //Pass the response data here
            return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));

    }

}
