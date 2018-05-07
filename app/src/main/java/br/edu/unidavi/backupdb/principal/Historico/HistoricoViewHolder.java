package br.edu.unidavi.backupdb.principal.Historico;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unidavi.backupdb.R;

/**
 * Created by GT2A-002 on 23/04/2018.
 */

public class HistoricoViewHolder extends RecyclerView.ViewHolder {
    TextView id;
    TextView data_execucao;
    TextView hora_execucao;
    TextView tamanho;
    TextView dia_por_extenso;
    ImageView status;

    public HistoricoViewHolder(View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.id);
        data_execucao = itemView.findViewById(R.id.data_execucao);
        dia_por_extenso = itemView.findViewById(R.id.dia_por_extenso);
        status = itemView.findViewById(R.id.status);
    }
}
