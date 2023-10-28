package com.clientes.rest.exception;

import com.clientes.model.entity.Origem;
import com.clientes.model.repository.OrigemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/origem")
@CrossOrigin("http://localhost:4200")
public class OrigemController {

    private final OrigemRepository origemRepository;

    @Autowired
    public OrigemController(OrigemRepository origemRepository) {
        this.origemRepository = origemRepository;
    }

    @GetMapping
    public ResponseEntity<List<Origem>> listarOrigens() {
        List<Origem> origens = origemRepository.findAll();
        return new ResponseEntity<>(origens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Origem> buscarOrigemPorId(@PathVariable Integer id) {
        Origem origem = origemRepository.findById(id).orElse(null);
        if (origem != null) {
            return new ResponseEntity<>(origem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Origem> criarOrigem(@RequestBody Origem origem) {
        Origem novaOrigem = origemRepository.save(origem);
        return new ResponseEntity<>(novaOrigem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Origem> atualizarOrigem(@PathVariable Integer id, @RequestBody Origem origem) {
        if (!origemRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        origem.setId(id);
        Origem origemAtualizada = origemRepository.save(origem);
        return new ResponseEntity<>(origemAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirOrigem(@PathVariable Integer id) {
        if (!origemRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        origemRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
