package br.edu.unidavi.backupdb.web;

import android.content.Context;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.backupdb.R;
import br.edu.unidavi.backupdb.model.Tarefa;

/**
 * Created by GT2A-002 on 18/04/2018.
 */

public class WebTaskTarefa extends WebTaskBase {
    private static final String SERVICE_NAME = "tarefa_android.php";
    private String usuario;

    public WebTaskTarefa(Context context, String usuario) {
        super(context, SERVICE_NAME);
        this.usuario = usuario;
    }

    @Override
    public RequestBody getRequestBody() {
        RequestBody body = new FormEncodingBuilder()
                .add("usuario",usuario)
                .build();
        return body;
    }

    @Override
    public void handleResponse(String response) {
        List<Tarefa> tarefaList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for(int index= 0; index < jsonArray.length(); index++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(index);
                Tarefa myTarefa = new Tarefa();
                myTarefa.setNome(jsonObject.getString("nome"));
                myTarefa.setExecucao(jsonObject.getString("execucao"));
                myTarefa.setTamanho(jsonObject.getString("tamanho"));
                myTarefa.setTempo_total(jsonObject.getString("tempo_total"));
                myTarefa.setExecuta(jsonObject.getString("executa"));
                myTarefa.setStatus(jsonObject.getString("status"));
                myTarefa.setTarefa(jsonObject.getString("tarefa"));
                tarefaList.add(myTarefa);
            }
            EventBus.getDefault().post(tarefaList);

        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.error_request)));
        }
    }

}
