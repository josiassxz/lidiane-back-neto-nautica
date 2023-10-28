package com.clientes.rest.exception;

import com.clientes.model.entity.Cidade;
import com.clientes.model.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
@CrossOrigin("http://localhost:4200")
public class CidadeController {

    private final CidadeRepository cidadeRepository;

    @Autowired
    public CidadeController(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> listarCidades() {
        List<Cidade> cidades = cidadeRepository.findAll();
        return new ResponseEntity<>(cidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscarCidadePorId(@PathVariable Integer id) {
        Cidade cidade = cidadeRepository.findById(id).orElse(null);
        if (cidade != null) {
            return new ResponseEntity<>(cidade, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Cidade> criarCidade(@RequestBody Cidade cidade) {
        Cidade novaCidade = cidadeRepository.save(cidade);
        return new ResponseEntity<>(novaCidade, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> atualizarCidade(@PathVariable Integer id, @RequestBody Cidade cidade) {
        if (!cidadeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cidade.setId(id);
        Cidade cidadeAtualizada = cidadeRepository.save(cidade);
        return new ResponseEntity<>(cidadeAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCidade(@PathVariable Integer id) {
        if (!cidadeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cidadeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
