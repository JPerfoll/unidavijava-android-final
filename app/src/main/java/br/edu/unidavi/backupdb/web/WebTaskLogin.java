package br.edu.unidavi.backupdb.web;

import android.content.Context;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.unidavi.backupdb.R;
import br.edu.unidavi.backupdb.model.User;

/**
 * Created by GT2A-002 on 16/04/2018.
 */

public class WebTaskLogin extends WebTaskBase{
    private static final String SERVICE_NAME = "login_android.php";

    private String usuario;
    private String senha;

    public WebTaskLogin(Context context, String usuario, String senha) {
        super(context, SERVICE_NAME);
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public RequestBody getRequestBody() {
        RequestBody body = new FormEncodingBuilder()
                .add("usuario",usuario)
                .add("senha", senha)
                .build();
        return body;
    }

    @Override
    public void handleResponse(String response) {
        User user = new User();
        try {
            JSONObject jsonObject = new JSONObject(response);
            user.setUsuario(jsonObject.getString("usuario"));

            EventBus.getDefault().post(user);

        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.label_error_invalid_response)));
        }
    }
}
