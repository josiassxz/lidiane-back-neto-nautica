package com.clientes.model.repository;

import com.clientes.model.entity.Origem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrigemRepository extends JpaRepository<Origem, Integer> {
    List<Origem> findAllByOrderByOrigem();
}
