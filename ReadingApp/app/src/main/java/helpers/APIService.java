package helpers;

        import android.preference.PreferenceActivity;

        import com.example.crist.readingapp.BoxdataReceivingActivity;
        import com.example.crist.readingapp.MainActivity;
        import com.loopj.android.http.*;
        import com.loopj.android.http.JsonHttpResponseHandler;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import cz.msebera.android.httpclient.Header;
        import cz.msebera.android.httpclient.HttpStatus;

public class APIService {
    private BoxdataReceivingActivity callingActivity;

    public void getBoxData(String box_id, final BoxdataReceivingActivity callingActivity) throws JSONException {
        this.callingActivity=callingActivity;
        BoxRestClient.get("boxes/"+box_id, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                super.onSuccess(statusCode, headers, response);
                System.out.println("JSONObject response in Callback"+ response.toString());
                if(callingActivity!=null) {
                    callingActivity.onBoxdataReceived(response);
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline

                // Do something with the response
                System.out.println("JSONArray"+timeline.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error ,JSONObject response){
                System.out.println(error.toString());
            }
        });
    }
    public void putAddressData(String address_id, RequestParams params, final BoxdataReceivingActivity callingActivity) throws JSONException{
        this.callingActivity=callingActivity;

        BoxRestClient.put("addresses/"+address_id,  params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                super.onSuccess(statusCode, headers, response);
                System.out.println("JSONObject response in Callback"+ response.toString());
                if(callingActivity!=null) {
                    callingActivity.onAddressDataChanged(response);
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline

                // Do something with the response
                System.out.println("JSONArray"+timeline.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error ,JSONObject response){
                System.out.println(error.toString());
            }
        });
    }
    public void putBoxData(String box_id, RequestParams params, final BoxdataReceivingActivity callingActivity) throws JSONException{
        this.callingActivity=callingActivity;

        BoxRestClient.put("boxes/"+box_id,  params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                super.onSuccess(statusCode, headers, response);
                System.out.println("JSONObject response in Callback"+ response.toString());
                if(callingActivity!=null) {
                    callingActivity.onBoxDataChanged(response);
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline

                // Do something with the response
                System.out.println("JSONArray"+timeline.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error ,JSONObject response){
                System.out.println(error.toString());
            }
        });
    }
}

