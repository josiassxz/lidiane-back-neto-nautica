package com.clientes.rest;



import com.clientes.model.entity.Cliente;
import com.clientes.model.filtro.ClienteFiltro;
import com.clientes.model.repository.ClienteRepository;
import com.clientes.model.repository.ClienteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

    @Autowired
    private  ClienteRepository repository;


    @Autowired
    private ClienteRepositoryImpl  clienteRepositoryImpl;


//    //public ClienteController(ClienteRepository repository) {
//        this.repository = repository;
//    }

@GetMapping
public List<Cliente> obterTodosOrdenadosPorNome() {
    Sort sort = Sort.by(Sort.Direction.ASC, "nome");
    return repository.findAll(sort);
}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente acharPorId(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/pesquisaFiltro")
    public List<Cliente> findLikeFiltro(@RequestParam(required = false) String nomeFiltro,@RequestParam(required = false) String stPagamento){
        return clienteRepositoryImpl
                .findLikeFiltro(nomeFiltro); // colocar proximo tambem

    }
    @GetMapping("/pesquisar")
    public List<Cliente> pesquisaAvancada(@RequestParam(required = false) String nome,@RequestParam(required = false) String origem,@RequestParam(required = false) String dtRecebimentoMim,@RequestParam(required = false) String dtRecebimento,@RequestParam(required = false) String dtRecebimentoMax ,@RequestParam(required = false) String situacao, @RequestParam(required = false) String captania,@RequestParam(required = false) String semana ){
        return clienteRepositoryImpl.pesquisaAvancada(ClienteFiltro.builder().nome(nome)
                .origem(origem)
                        .semana(semana)
                .dtRecebimentoMim(dtRecebimentoMim)
                .dtRecebimentoMax(dtRecebimentoMax)
                .dtRecebimento(dtRecebimento)
                .situacao(situacao)
                .captania(captania).build());
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(cliente ->{
                            repository.delete(cliente);
                            return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado){
        repository
                .findById(id)
                .map(cliente ->{
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setSemana(clienteAtualizado.getSemana());
                    return repository.save(clienteAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));

    }

}
