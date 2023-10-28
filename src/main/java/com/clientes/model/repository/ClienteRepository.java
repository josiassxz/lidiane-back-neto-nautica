    package com.clientes.model.repository;

    import com.clientes.model.entity.Cliente;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import javax.persistence.TypedQuery;
    import java.util.List;

    public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


//        @Query("SELECT c FROM Cliente  c WHERE c.nome LIKE CONCAT('%',:nomeFiltro,'%')")
//        List<Cliente> findLikeFiltro(@Param("nomeFiltro") String nomeFiltro);




    }
