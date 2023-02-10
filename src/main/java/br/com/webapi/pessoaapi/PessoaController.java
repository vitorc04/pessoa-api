package br.com.webapi.pessoaapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public List<Pessoa> listAll() {
        return pessoaService.getAllPessoas();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPessoaById (@PathVariable Long id){
        try {
            Pessoa pessoa = pessoaService.getPessoaById(id);
            return ResponseEntity.ok(pessoa);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Pessoa createPessoa(@RequestBody Pessoa pessoa) {
        return pessoaService.savePessoa(pessoa);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa){
        try {
            Pessoa updatedPessoa = pessoaService.updatePessoa(id, pessoa);
            return ResponseEntity.ok(pessoa);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletePessoa(@PathVariable Long id){
        try{
            pessoaService.deletePessoaById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
