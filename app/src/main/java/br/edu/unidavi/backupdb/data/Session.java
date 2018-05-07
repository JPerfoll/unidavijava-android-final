package br.edu.unidavi.backupdb.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GT2A-002 on 16/04/2018.
 */

public class Session {
    private final String FIELD_USUARIO = "usuario";
    private final String FIELD_TAREFA = "tarefa";
    private final String FIELD_HISTORICO = "historico";
    private final String CATEGORY_SESSION = "session";

    private SharedPreferences sharedPreferences;

    public Session(Context context){
        sharedPreferences =
                context.getSharedPreferences(CATEGORY_SESSION,
                        Context.MODE_PRIVATE);
    }

    public void saveUsuarioInSession(String usuarioValue){
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_USUARIO, usuarioValue);
        editor.commit();
    }

    public String getUsuarioInSession(){
        return sharedPreferences.getString(FIELD_USUARIO,"");
    }

    public void saveTarefaInSession(String tarefaValue){
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_TAREFA, tarefaValue);
        editor.commit();
    }

    public String getTarefaInSession(){
        return sharedPreferences.getString(FIELD_TAREFA,"");
    }

    public void saveHistoricoInSession(String historicoValue){
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_HISTORICO, historicoValue);
        editor.commit();
    }

    public String getHistoricoInSession(){
        return sharedPreferences.getString(FIELD_HISTORICO,"");
    }
}
