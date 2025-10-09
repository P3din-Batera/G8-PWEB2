package br.edu.ifal.SLAApp.model;

import java.time.LocalDateTime;

public class Violacao {
    private int id;
    private LocalDateTime dataOcorrencia;
    private String justificativa;
    private String acaoCorretiva;
    private SLA sla;
   
}