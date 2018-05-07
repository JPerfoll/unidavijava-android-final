package br.edu.unidavi.backupdb.model;

/**
 * Created by GT2A-002 on 23/04/2018.
 */

public class Historico {
    private String id;
    private String tarefa_id;
    private String nome;
    private String tamanho;
    private String data_execucao;
    private String hora_execucao;
    private String tempo_total;
    private String status;
    private String tipo_backup;
    private String nome_arquivo;
    private String mensagem;
    private String dia_por_extenso;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTempo_total() {
        return tempo_total;
    }

    public void setTempo_total(String tempo_total) {
        this.tempo_total = tempo_total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData_execucao() {
        return data_execucao;
    }

    public void setData_execucao(String data_execucao) {
        this.data_execucao = data_execucao;
    }

    public String getHora_execucao() {
        return hora_execucao;
    }

    public void setHora_execucao(String hora_execucao) {
        this.hora_execucao = hora_execucao;
    }

    public String getTipo_backup() {
        return tipo_backup;
    }

    public void setTipo_backup(String tipo_backup) {
        this.tipo_backup = tipo_backup;
    }

    public String getNome_arquivo() {
        return nome_arquivo;
    }

    public void setNome_arquivo(String nome_arquivo) {
        this.nome_arquivo = nome_arquivo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDia_por_extenso() {
        return dia_por_extenso;
    }

    public void setDia_por_extenso(String dia_por_extenso) {
        this.dia_por_extenso = dia_por_extenso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarefa_id() {
        return tarefa_id;
    }

    public void setTarefa_id(String tarefa_id) {
        this.tarefa_id = tarefa_id;
    }
}
