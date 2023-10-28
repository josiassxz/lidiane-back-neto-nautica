package com.clientes.model.repository;

import com.clientes.model.entity.Cliente;
import com.clientes.model.filtro.ClienteFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ClienteRepositoryImpl implements CustomClienteRepository {

    @Autowired  private EntityManager entityManager;
    @Override
    public List<Cliente> findLikeFiltro(String nomeFiltro) //colocar mais parametros , stPagamento e na interface tambem custom cliente repository
    {     StringBuilder hql = new StringBuilder();
        hql.append(" SELECT c FROM Cliente  c ");
        hql.append(" WHERE 1 = 1 ");
        if(nomeFiltro != null){
            hql.append("AND c.nome LIKE CONCAT('%',:nomeFiltro,'%')");
        }
        TypedQuery<Cliente> query = entityManager.createQuery(hql.toString(),Cliente.class);
        if (nomeFiltro != null){query.setParameter("nomeFiltro", nomeFiltro);}
        return query.getResultList();}

    @Override
    public List<Cliente> pesquisaAvancada(ClienteFiltro filtro) {
        StringBuilder hql = new StringBuilder();
        hql.append(filds());
        hql.append(where(filtro));
        TypedQuery<Cliente> query = setParameters(hql,filtro);
        return query.getResultList();
    }


     public  String filds(){
       return   " SELECT c FROM Cliente  c ";
     };
    public  String where(ClienteFiltro filtro){
        StringBuilder where = new StringBuilder();
        where.append( "WHERE 1 = 1 ");
        if(Objects.nonNull(filtro.getNome())){
            where.append(" AND c.nome LIKE CONCAT('%',:filtroNome,'%')");
        };
        if(Objects.nonNull(filtro.getOrigem())){
            where.append(" AND origem = :filtroOrigem");
        };
        if(Objects.nonNull(filtro.getDtRecebimentoMim())&&Objects.nonNull(filtro.getDtRecebimentoMax())){
            where.append(" AND DATE_FORMAT(data_receb,'%Y-%m-%d')  BETWEEN  STR_TO_DATE(:filtroDataRecebimentoMim,'%Y-%m-%d') AND STR_TO_DATE(:filtroDataRecebimentoMax, '%Y-%m-%d')");
        }
        else if (Objects.nonNull(filtro.getDtRecebimento())){
            where.append(" AND DATE_FORMAT(data_receb,'%Y-%m-%d') =  STR_TO_DATE(:filtroDataRecebimento, '%Y-%m-%d')");
        };
        if (Objects.nonNull(filtro.getSituacao())) {
            where.append(" AND situacao_pagamento LIKE CONCAT('%',:filtroSituacao,'%')");
        };
        if(Objects.nonNull(filtro.getCaptania())){
            where.append(" AND captania = :filtroCaptania");

        };
        if(Objects.nonNull(filtro.getSemana())){
            where.append(" AND DATE_FORMAT(semana,'%Y-%m-%d') = STR_TO_DATE(:filtroSemana, '%Y-%m-%d')");
        };
        return  where.toString();
    };


    TypedQuery<Cliente> setParameters(StringBuilder hql ,ClienteFiltro filtro){
        TypedQuery<Cliente> query = entityManager.createQuery(hql.toString(),Cliente.class);
        if(Objects.nonNull(filtro.getNome())){
            query.setParameter("filtroNome", filtro.getNome().toUpperCase());
        };
        if(Objects.nonNull(filtro.getOrigem())){
            query.setParameter("filtroOrigem", filtro.getOrigem().toUpperCase());
        };
        if(Objects.nonNull(filtro.getDtRecebimentoMim())&& Objects.nonNull(filtro.getDtRecebimentoMax())){
            query.setParameter("filtroDataRecebimentoMim", filtro.getDtRecebimentoMim());
            query.setParameter("filtroDataRecebimentoMax", filtro.getDtRecebimentoMax());
        }
        else if(Objects.nonNull(filtro.getDtRecebimento())){
            query.setParameter("filtroDataRecebimento", filtro.getDtRecebimento());
        };
        if(Objects.nonNull(filtro.getSituacao())){
            query.setParameter("filtroSituacao", filtro.getSituacao().toUpperCase());
        };
        if(Objects.nonNull(filtro.getCaptania())){
            query.setParameter("filtroCaptania", filtro.getCaptania().toUpperCase());
        };
        if(Objects.nonNull(filtro.getSemana())){
            query.setParameter("filtroSemana", filtro.getSemana());
        };
        return  query;
    };


}

