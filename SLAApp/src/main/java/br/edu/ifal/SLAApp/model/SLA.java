package br.edu.ifal.SLAApp.model;

import java.io.Serializable;

public class SLA implements Serializable {
	private int id;
	private int tempoResposta;
	private int tempoSolucao;
	private String criticidade;
	private String prioridade;

		public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTempoResposta() {
		return tempoResposta;
	}
	public void setTempoResposta(int tempoResposta) {
		this.tempoResposta = tempoResposta;
	}
	public int getTempoSolucao() {
		return tempoSolucao;
	}
	public void setTempoSolucao(int tempoSolucao) {
		this.tempoSolucao = tempoSolucao;
	}
	public String getCriticidade() {
		return criticidade;
	}
	public void setCriticidade(String criticidade) {
		this.criticidade = criticidade;
	}
	public String getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
		public SLA() {}
			public SLA(int tempoResposta, int tempoSolucao, String criticidade, String prioridade) {
				this.tempoResposta = tempoResposta;
				this.tempoSolucao = tempoSolucao;
				this.criticidade = criticidade;
				this.prioridade = prioridade;
	    }

}