package br.edu.unidavi.backupdb.principal.Arquivo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import br.edu.unidavi.backupdb.R;
import br.edu.unidavi.backupdb.model.Arquivo;

/**
 * Created by GT2A-002 on 23/04/2018.
 */

public class ArquivoAdapter extends RecyclerView.Adapter<ArquivoViewHolder> {
    List<Arquivo> arquivoList;
    Context context;

    public ArquivoAdapter(List<Arquivo> arquivoList,Context context){
        this.arquivoList = arquivoList;
        this.context = context;
    }

    @Override
    public ArquivoViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_arquivo,
                        parent,
                        false);

        ArquivoViewHolder myViewArquivoHolder = new ArquivoViewHolder(v);
        return myViewArquivoHolder;
    }

    @Override
    public void onBindViewHolder(final ArquivoViewHolder holder, int position) {
        try {
            final Arquivo myArquivo = arquivoList.get(position);

            holder.id_a.setText(myArquivo.getId());
            if (Objects.equals(myArquivo.getStatus(), "C")) {
                holder.status_a.setImageResource(R.drawable.correto);
            } else if (Objects.equals(myArquivo.getStatus(), "E")) {
                holder.status_a.setImageResource(R.drawable.erro);
            } else if ((Objects.equals(myArquivo.getStatus(), "P")) || (Objects.equals(myArquivo.getStatus(), "EX"))){
                holder.status_a.setImageResource(R.drawable.pendente);
            }else{
                holder.status_a.setImageResource(R.drawable.alerta);
            }
            holder.data_execucao_a.setText(myArquivo.getData_execucao());
            holder.nome_arquivo_a.setText(myArquivo.getNome_arquivo());
            holder.tamanho_a.setText(myArquivo.getTamanho());
            holder.dia_por_extenso_a.setText(myArquivo.getDia_por_extenso());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//          Session mySession = new Session(context);
//          mySession.saveHistoricoInSession(myArquivo.getId());
//
//          Intent intent = new Intent(context, Tarefa.class);
//          intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//          context.startActivity(intent);
                }
            });
        }catch (Exception e){
             Log.d("ERROARQUIVO",e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return arquivoList.size();
    }

    public List<Arquivo> getArquivoList() {
        return arquivoList;
    }

    public void setArquivoList(List<Arquivo> arquivoList) {
        this.arquivoList = arquivoList;
    }
}