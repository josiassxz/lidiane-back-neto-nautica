package com.clientes.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    //@NotEmpty(message = "{campo.nome.obrigatorio}")
    //@JsonFormat(pattern = "dd/MM/yyyy")
    private Date semana;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.semana.obrigatorio}")
    private String nome;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.cidade.obrigatorio}")
    private String captania;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.cidade.obrigatorio}")
    private String cidade;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.tipoprocesso.obrigatorio}")
    private String tipoProcesso;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.numeroEmbarcacao.obrigatorio}")
    private String numEmbarc;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.origem.obrigatorio}")
    private String origem;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.formPgto.obrigatorio}")
    private String formPgto;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.banco.obrigatorio}")
    private String banco;

    @Column(nullable = false)
    // @NotEmpty(message = "{campo.dataRecebimento.obrigatorio}")
    //@JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataReceb;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.caixaRecebido.obrigatorio}")
    private Date caixaRecebido;

    @Column(nullable = false, length = 255)
    //@NotEmpty(message = "{campo.valorRecebido.obrigatorio}")
    private String valorServico;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.recebido.obrigatorio}")
    private String recebibo;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.areceber.obrigatorio}")
    private String areceber;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.situacaopagamento.obrigatorio}")
    private String situacaoPagamento;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.gru.obrigatorio}")
    private String gru;

    @Column(nullable = false, length = 255)
    // @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String valorLiquido;


    @Column(name = "data_cadastro", updatable = false)
   // @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCadastro;

    private String observacao;


}
