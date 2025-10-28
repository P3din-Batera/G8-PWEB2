package br.edu.ifal.SLAApp.model;

public class SLA {
    private int id;
    private int tempoResposta;
    private int tempoSolucao;
    private String criticidade;
    private String prioridade;
    private String nomeResponsavel; // transitório para exibir
    private String nomeTipo;        // transitório para exibir

    // getters / setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTempoResposta() { return tempoResposta; }
    public void setTempoResposta(int tempoResposta) { this.tempoResposta = tempoResposta; }

    public int getTempoSolucao() { return tempoSolucao; }
    public void setTempoSolucao(int tempoSolucao) { this.tempoSolucao = tempoSolucao; }

    public String getCriticidade() { return criticidade; }
    public void setCriticidade(String criticidade) { this.criticidade = criticidade; }

    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }

    public String getNomeTipo() { return nomeTipo; }
    public void setNomeTipo(String nomeTipo) { this.nomeTipo = nomeTipo; }
    
}
