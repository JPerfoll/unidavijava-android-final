package br.edu.unidavi.backupdb.model;

/**
 * Created by GT2A-002 on 18/04/2018.
 */

public class Tarefa {

    private String nome;
    private String tamanho;
    private String execucao;
    private String tempo_total;
    private String status;
    private String tipo_backup;
    private String agendamento_hora;
    private String executa;
    private String em_execucao;
    private String desliga_computador;
    private String tarefa;

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

    public String getExecucao() {
        return execucao;
    }

    public void setExecucao(String execucao) {
        this.execucao = execucao;
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

    public String getTipo_backup() {
        return tipo_backup;
    }

    public void setTipo_backup(String tipo_backup) {
        this.tipo_backup = tipo_backup;
    }

    public String getAgendamento_hora() {
        return agendamento_hora;
    }

    public void setAgendamento_hora(String agendamento_hora) {
        this.agendamento_hora = agendamento_hora;
    }

    public String getExecuta() {
        return executa;
    }

    public void setExecuta(String executa) {
        this.executa = executa;
    }

    public String getEm_execucao() {
        return em_execucao;
    }

    public void setEm_execucao(String em_execucao) {
        this.em_execucao = em_execucao;
    }

    public String getDesliga_computador() {
        return desliga_computador;
    }

    public void setDesliga_computador(String desliga_computador) {
        this.desliga_computador = desliga_computador;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
}
