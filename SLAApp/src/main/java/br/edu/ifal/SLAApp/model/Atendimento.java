package br.edu.ifal.SLAApp.model;

import java.time.LocalDateTime;

public class Atendimento {
    private int id;
    private LocalDateTime dataSolicitacao;
    private String status;
    private SLA sla; 
    private Responsavel responsavel; 
    
}
