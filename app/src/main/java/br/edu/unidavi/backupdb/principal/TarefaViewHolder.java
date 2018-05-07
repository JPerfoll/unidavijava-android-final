package br.edu.unidavi.backupdb.principal;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unidavi.backupdb.R;

/**
 * Created by GT2A-002 on 18/04/2018.
 */

class TarefaViewHolder extends RecyclerView.ViewHolder{
    TextView nome;
    TextView execucao;
    TextView tarefa;
    ImageView status;

    public TarefaViewHolder(View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.nome);
        execucao = itemView.findViewById(R.id.execucao);
        tarefa = itemView.findViewById(R.id.tarefa);
        status = itemView.findViewById(R.id.status);
    }
}
