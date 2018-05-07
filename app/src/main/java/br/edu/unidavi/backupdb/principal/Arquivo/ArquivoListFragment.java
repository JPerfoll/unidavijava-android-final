package br.edu.unidavi.backupdb.principal.Arquivo;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.backupdb.R;
import br.edu.unidavi.backupdb.data.Session;
import br.edu.unidavi.backupdb.model.Arquivo;
import br.edu.unidavi.backupdb.web.WebTaskArquivo;

public class ArquivoListFragment extends Fragment{
    ProgressDialog progressDialog;

    private RecyclerView recyclerView;
    private ArquivoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_arquivo_list,
                container, false);

        showDialog();
        Session mySession = new Session(getActivity());
        WebTaskArquivo taskArquivo = new WebTaskArquivo(getActivity(),
                mySession.getTarefaInSession());
        taskArquivo.execute();

        recyclerView = view.findViewById(R.id.recycler_historicos_arquivos);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new ArquivoAdapter(new ArrayList<Arquivo>(), getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(List<Arquivo> arquivoList){
        if(arquivoList.size() == 0){
            getView().findViewById(R.id.recycler_historicos_arquivos).setVisibility(View.GONE);
        }else{
            getView().findViewById(R.id.recycler_historicos_arquivos).setVisibility(View.VISIBLE);
            adapter.setArquivoList(arquivoList);
            adapter.notifyDataSetChanged();
        }
        hideDialog();
    }

    @Subscribe
    public void onEvent(Error error){
        Snackbar.make(getView(), error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }

    public void showDialog(){
        progressDialog = new ProgressDialog(getContext());
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
}