package br.edu.unidavi.backupdb.features.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.edu.unidavi.backupdb.R;
import br.edu.unidavi.backupdb.data.Session;
import br.edu.unidavi.backupdb.model.User;
import br.edu.unidavi.backupdb.principal.Tarefa;
import br.edu.unidavi.backupdb.web.WebTaskLogin;

public class Login extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonEnter = findViewById(R.id.botao_entrar);
        buttonEnter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Logar();
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(User user){
        hideDialog();
        Session session = new Session(this);
        session.saveUsuarioInSession(user.getUsuario());
        carregaPrincipal();
    }

    @Subscribe
    public void onEvent(Error error){
        hideDialog();
        Snackbar.make(findViewById(R.id.container),
                error.getMessage(), Snackbar.LENGTH_LONG)
                .show();
    }

    private void carregaPrincipal(){
        Intent intent = new Intent(this,
                Tarefa.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    public void showDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(
                R.string.message_wait));
        progressDialog.setProgressStyle(
                ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    public void hideDialog(){
        if(progressDialog != null &&
                progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }

    public void Logar(){
        EditText inputUsuario = findViewById(R.id.usuario);
        String usuarioValue = inputUsuario.getText().toString();

        EditText inputSenha = findViewById(R.id.senha);
        String senhaValue = inputSenha.
                getText().toString();

        showDialog();

        WebTaskLogin task = new WebTaskLogin(this,usuarioValue,
                senhaValue);
        task.execute();
    }
}
