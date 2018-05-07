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
import br.edu.unidavi.backupdb.model.Arquivo;

/**
 * Created by GT2A-002 on 27/04/2018.
 */

public class WebTaskArquivo extends WebTaskBase {
    private static final String SERVICE_NAME = "historico_arquivo_android.php";
    private String tarefa;

    public WebTaskArquivo(Context context, String tarefa) {
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
        List<Arquivo> arquivoList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for(int index= 0; index < jsonArray.length(); index++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(index);
                Arquivo myArquivo = new Arquivo();
                myArquivo.setId(jsonObject.getString("id"));
                myArquivo.setTarefa_id(jsonObject.getString("tarefa_id"));
                myArquivo.setTamanho(jsonObject.getString("tamanho"));
                myArquivo.setData_execucao(jsonObject.getString("data_execucao"));
                myArquivo.setTempo_total(jsonObject.getString("tempo_total"));
                myArquivo.setStatus(jsonObject.getString("status"));
                myArquivo.setNome_arquivo(jsonObject.getString("nome_arquivo"));
                myArquivo.setMensagem(jsonObject.getString("mensagem"));
                myArquivo.setDia_por_extenso(jsonObject.getString("dia_por_extenso"));
                arquivoList.add(myArquivo);
            }
            EventBus.getDefault().post(arquivoList);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.error_request)));
        }
    }
}
