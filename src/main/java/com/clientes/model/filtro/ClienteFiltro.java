package com.clientes.model.filtro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteFiltro {

String nome;
String origem;
String dtRecebimento;
String dtRecebimentoMim;
String dtRecebimentoMax;
String situacao;
String captania;
String semana;

}
