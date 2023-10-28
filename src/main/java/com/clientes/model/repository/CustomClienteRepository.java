package com.clientes.model.repository;

import com.clientes.model.entity.Cliente;
import com.clientes.model.filtro.ClienteFiltro;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomClienteRepository {
    List<Cliente> findLikeFiltro(String nomeFiltro);

    List<Cliente> pesquisaAvancada(ClienteFiltro clienteFiltro);
}
