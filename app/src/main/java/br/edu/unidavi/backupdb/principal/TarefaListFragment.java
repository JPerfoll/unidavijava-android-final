package br.edu.unidavi.backupdb.principal;

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
import br.edu.unidavi.backupdb.model.Tarefa;
import br.edu.unidavi.backupdb.web.WebTaskTarefa;

/**
 * Created by GT2A-002 on 18/04/2018.
 */

public class TarefaListFragment extends Fragment {
    ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private TarefaAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tarefa_list,
                container, false);

        recyclerView = view.findViewById(R.id.recycler_tarefas);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new TarefaAdapter(
                new ArrayList<Tarefa>(),getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        showDialog();
        Session mySession = new Session(getActivity());
        WebTaskTarefa taskTarefas = new WebTaskTarefa(getActivity(),
                mySession.getUsuarioInSession());

        taskTarefas.execute();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(List<Tarefa> TarefaList){

        if(TarefaList.size() == 0){
            getView().findViewById(R.id.recycler_tarefas).setVisibility(View.GONE);
        }else{
            getView().findViewById(R.id.recycler_tarefas).setVisibility(View.VISIBLE);
            adapter.setTarefaList(TarefaList);
            adapter.notifyDataSetChanged();
        }
        hideDialog();
    }

    @Subscribe
    public void onEvent(Error error){
        Snackbar.make(getView(), error.getMessage(),
                Snackbar.LENGTH_LONG).show();
        hideDialog();
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