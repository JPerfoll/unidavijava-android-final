package br.edu.unidavi.backupdb.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import br.edu.unidavi.backupdb.R;


/**
 * Created by GT2A-002 on 16/04/2018.
 */

public abstract class WebTaskBase extends AsyncTask<Void, Void, Void> {
    public static final int RESPONSE_OK = 200;
    public static final int RESPONSE_INVALID_REQUEST = 403;
    private static int TIMEOUT = 15;
    private static String BASE_URL = "https://upcloud.net.br/painel/php/";

    private String serviceURL;
    private Context context;
    private Error error;
    private String responseString;
    private int responseCode;
    private boolean silent;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public WebTaskBase(Context context, String serviceURL) {
        this.serviceURL = serviceURL;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (!isOnline(context)) {
            error = new Error(context.getString(R.string.error_connection));
            responseString = null;
            return null;
        }

        doRegularCall();

        return null;
    }

    private void doRegularCall() {
        OkHttpClient client = new OkHttpClient();
        //RequestBody body = RequestBody.create(JSON, getRequestBody());

        client.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
        client.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);

        Request request = new Request.Builder()
                .url(BASE_URL + serviceURL)
                .post(getRequestBody())
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseCode = response.code();
            responseString = response.body().string();

            Log.d("RESPONSE",responseString);
        } catch (IOException e) {
            error = new Error(context.getString(R.string.error_connection));
        }
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (error != null && !silent) {
            EventBus.getDefault().post(error);
        } else {
            switch (getResponseCode()) {
                case RESPONSE_OK:
                    try {
                        JSONObject responseJSON = new JSONObject(responseString);
                        String errorMessage = responseJSON.getString("error");
                        if (!silent) {
                            EventBus.getDefault().post(new Error(errorMessage));
                        }
                    } catch (JSONException e) {
                        handleResponse(responseString);
                    } catch (NullPointerException e) {
                        handleResponse("");
                    }
                    break;

                case RESPONSE_INVALID_REQUEST:
                    EventBus.getDefault().post(new Error(context.getString(R.string.error_request)));
                    break;
            }
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public abstract void handleResponse(String response);

    public abstract RequestBody getRequestBody();

    public Context getContext() {
        return context;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

}
