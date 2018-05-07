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
import br.edu.unidavi.backupdb.model.Historico;

/**
 * Created by GT2A-002 on 23/04/2018.
 */

public class WebTaskHistorico extends WebTaskBase {
    private static final String SERVICE_NAME = "historico_android.php";
    private String tarefa;

    public WebTaskHistorico(Context context, String tarefa) {
        super(context, SERVICE_NAME);
        this.tarefa = tarefa;
    }

    @Override
    public RequestBody getRequestBody() {
        RequestBody body = new FormEncodingBuilder()
                .add("tarefa",tarefa)
                .build();
        return body;
    }

    @Override
    public void handleResponse(String response) {
        List<Historico> historicoList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for(int index= 0; index < jsonArray.length(); index++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(index);
                Historico myHistorico = new Historico();
                myHistorico.setId(jsonObject.getString("id"));
                myHistorico.setTarefa_id(jsonObject.getString("tarefa_id"));
                myHistorico.setNome(jsonObject.getString("nome"));
                myHistorico.setTamanho(jsonObject.getString("tamanho"));
                myHistorico.setData_execucao(jsonObject.getString("data_execucao"));
                myHistorico.setHora_execucao(jsonObject.getString("hora_execucao"));
                myHistorico.setTempo_total(jsonObject.getString("tempo_total"));
                myHistorico.setStatus(jsonObject.getString("status"));
                myHistorico.setTipo_backup(jsonObject.getString("tipo_backup"));
                myHistorico.setNome_arquivo(jsonObject.getString("nome_arquivo"));
                myHistorico.setMensagem(jsonObject.getString("mensagem"));
                myHistorico.setDia_por_extenso(jsonObject.getString("dia_por_extenso"));
                historicoList.add(myHistorico);
            }

            EventBus.getDefault().post(historicoList);

        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.error_request)));
        }
    }
}
