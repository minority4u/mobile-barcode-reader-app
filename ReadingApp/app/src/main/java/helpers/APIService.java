package helpers;

        import android.preference.PreferenceActivity;

        import com.loopj.android.http.*;
        import com.loopj.android.http.JsonHttpResponseHandler;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import cz.msebera.android.httpclient.Header;
        import cz.msebera.android.httpclient.HttpStatus;

public class APIService {

    public void getBoxData() throws JSONException {
        BoxRestClient.get("boxes/", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                super.onSuccess(statusCode, headers, response);
                System.out.println("JSONObject response"+ response.toString());
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline

                // Do something with the response
                System.out.println("JSONArray"+timeline.toString());
            }
        });
    }
}

