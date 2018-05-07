package br.edu.unidavi.backupdb.principal.Historico;


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
import br.edu.unidavi.backupdb.model.Historico;
import br.edu.unidavi.backupdb.web.WebTaskHistorico;

public class HistoricoListFragment extends Fragment {
    ProgressDialog progressDialog;

    private RecyclerView recyclerView;
    private HistoricoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_historico_list,
                container, false);

        showDialog();
        Session mySession = new Session(getActivity());
        WebTaskHistorico taskHistoricos = new WebTaskHistorico(getActivity(),
                mySession.getTarefaInSession());

        taskHistoricos.execute();

        recyclerView = view.findViewById(R.id.recycler_historicos);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


        adapter = new HistoricoAdapter(new ArrayList<Historico>(),getActivity());
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
    public void onEvent(List<Historico> HistoricoList){

        if(HistoricoList.size() == 0){
            getView().findViewById(R.id.recycler_historicos).setVisibility(View.GONE);
        }else{
            getView().findViewById(R.id.recycler_historicos).setVisibility(View.VISIBLE);
            adapter.setHistoricoList(HistoricoList);
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