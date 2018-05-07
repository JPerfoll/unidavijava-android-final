package br.edu.unidavi.backupdb.principal.Arquivo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unidavi.backupdb.R;

/**
 * Created by GT2A-002 on 23/04/2018.
 */

public class ArquivoViewHolder extends RecyclerView.ViewHolder {
    TextView id_a;
    TextView data_execucao_a;
    TextView nome_arquivo_a;
    TextView tamanho_a;
    TextView dia_por_extenso_a;
    ImageView status_a;

    public ArquivoViewHolder(View itemView) {
        super(itemView);
        id_a = itemView.findViewById(R.id.id_a);
        data_execucao_a = itemView.findViewById(R.id.data_execucao_a);
        nome_arquivo_a = itemView.findViewById(R.id.nome_arquivo_a);
        tamanho_a = itemView.findViewById(R.id.tamanho_a);
        dia_por_extenso_a = itemView.findViewById(R.id.dia_por_extenso_a);
        status_a = itemView.findViewById(R.id.status_a);
    }
}
