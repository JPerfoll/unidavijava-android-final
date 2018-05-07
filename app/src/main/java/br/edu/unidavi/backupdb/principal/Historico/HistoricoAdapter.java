package br.edu.unidavi.backupdb.principal.Historico;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import br.edu.unidavi.backupdb.R;
import br.edu.unidavi.backupdb.model.Historico;

/**
 * Created by GT2A-002 on 23/04/2018.
 */

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoViewHolder> {
    List<Historico> historicoList;
    Context context;

    public HistoricoAdapter(List<Historico> historicoList, Context context){
        this.historicoList = historicoList;
        this.context = context;
    }

    @Override
    public HistoricoViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_historico,
                        parent,
                        false);

        HistoricoViewHolder myViewHolder = new HistoricoViewHolder(v);
        return myViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(final HistoricoViewHolder holder, int position) {
        try {
            final Historico myHistorico = historicoList.get(position);

            holder.id.setText(myHistorico.getId());
            if (Objects.equals(myHistorico.getStatus(), "C")) {
                holder.status.setImageResource(R.drawable.correto);
            } else if (Objects.equals(myHistorico.getStatus(), "E")) {
                holder.status.setImageResource(R.drawable.erro);
            } else if ((Objects.equals(myHistorico.getStatus(), "P")) || (Objects.equals(myHistorico.getStatus(), "EX"))){
                holder.status.setImageResource(R.drawable.pendente);
            }else{
                holder.status.setImageResource(R.drawable.alerta);
            }
            holder.data_execucao.setText(myHistorico.getData_execucao());
            holder.tamanho.setText(myHistorico.getTamanho());
            holder.dia_por_extenso.setText(myHistorico.getDia_por_extenso());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//          Session mySession = new Session(context);
//          mySession.saveHistoricoInSession(myHistorico.getId());
//
//          Intent intent = new Intent(context, Tarefa.class);
//          intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//          context.startActivity(intent);
                }
            });
        }catch (Exception e){
            // EventBus.getDefault().post(new Error("Erro ao carregar a lista"));
        }
    }

    @Override
    public int getItemCount() {
        return historicoList.size();
    }

    public List<Historico> getHistoricoList() {
        return historicoList;
    }

    public void setHistoricoList(List<Historico> historicoList) {
        this.historicoList = historicoList;
    }
}
