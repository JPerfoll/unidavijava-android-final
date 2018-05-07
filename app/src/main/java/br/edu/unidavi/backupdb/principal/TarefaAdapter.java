package br.edu.unidavi.backupdb.principal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import br.edu.unidavi.backupdb.R;
import br.edu.unidavi.backupdb.data.Session;
import br.edu.unidavi.backupdb.model.Tarefa;
import br.edu.unidavi.backupdb.principal.Historico.Historico;

/**
 * Created by GT2A-002 on 18/04/2018.
 */

public class TarefaAdapter extends RecyclerView.Adapter<TarefaViewHolder> {

    List<Tarefa> tarefaList;
    Context context;

    public TarefaAdapter(List<Tarefa> tarefaList, Context context){
        this.tarefaList = tarefaList;
        this.context = context;
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_tarefa,
                        parent,
                        false);

        TarefaViewHolder myViewHolder = new TarefaViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final TarefaViewHolder holder, int position) {
        try {
            final Tarefa myTarefa = tarefaList.get(position);

            holder.tarefa.setText(myTarefa.getTarefa());
            holder.nome.setText(myTarefa.getNome());
            holder.execucao.setText(myTarefa.getExecucao());

            if (Objects.equals(myTarefa.getStatus(), "C")) {
                holder.status.setImageResource(R.drawable.correto);
            } else if (Objects.equals(myTarefa.getStatus(), "E")) {
                holder.status.setImageResource(R.drawable.erro);
            } else if ((Objects.equals(myTarefa.getStatus(), "P")) || (Objects.equals(myTarefa.getStatus(), "EX"))){
                holder.status.setImageResource(R.drawable.pendente);
            }else{
                holder.status.setImageResource(R.drawable.alerta);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Session mySession = new Session(context);
                    mySession.saveTarefaInSession(myTarefa.getTarefa());

                    Intent intent = new Intent(context, Historico.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    context.startActivity(intent);
                }
            });
        }catch (Exception e){
            //Está caindo aqui algumas vezes, não consegui identificar o por que mesmo com o debug, simplesmente não recupera o moodel
            //EventBus.getDefault().post(new Error("Erro ao carregar a lista"));
        }
    }

    @Override
    public int getItemCount() {
        return tarefaList.size();
    }

    public List<Tarefa> getTarefaList() {
        return tarefaList;
    }

    public void setTarefaList(List<Tarefa> tarefaList) {
        this.tarefaList = tarefaList;
    }
}
