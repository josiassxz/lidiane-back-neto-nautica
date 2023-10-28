package com.clientes.rest.exception;

import com.clientes.model.entity.TipoProcesso;
import com.clientes.model.repository.TipoProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-processo")
@CrossOrigin("http://localhost:4200")
public class TipoProcessoController {

    private final TipoProcessoRepository tipoProcessoRepository;

    @Autowired
    public TipoProcessoController(TipoProcessoRepository tipoProcessoRepository) {
        this.tipoProcessoRepository = tipoProcessoRepository;
    }

    @GetMapping
    public ResponseEntity<List<TipoProcesso>> listarTiposProcesso() {
        List<TipoProcesso> tiposProcesso = tipoProcessoRepository.findAll();
        return new ResponseEntity<>(tiposProcesso, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProcesso> buscarTipoProcessoPorId(@PathVariable Integer id) {
        TipoProcesso tipoProcesso = tipoProcessoRepository.findById(id).orElse(null);
        if (tipoProcesso != null) {
            return new ResponseEntity<>(tipoProcesso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TipoProcesso> criarTipoProcesso(@RequestBody TipoProcesso tipoProcesso) {
        TipoProcesso novoTipoProcesso = tipoProcessoRepository.save(tipoProcesso);
        return new ResponseEntity<>(novoTipoProcesso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoProcesso> atualizarTipoProcesso(@PathVariable Integer id, @RequestBody TipoProcesso tipoProcesso) {
        if (!tipoProcessoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tipoProcesso.setId(id);
        TipoProcesso tipoProcessoAtualizado = tipoProcessoRepository.save(tipoProcesso);
        return new ResponseEntity<>(tipoProcessoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTipoProcesso(@PathVariable Integer id) {
        if (!tipoProcessoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tipoProcessoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
