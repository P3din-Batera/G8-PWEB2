package br.edu.ifal.SLAApp.model;

import java.time.LocalDateTime;

public class HistoricoSLA {
    private int id;
    private LocalDateTime dataAplicacao;
    private String statusCumprimento;
    private String observacao;
    private SLA sla;
    
}
