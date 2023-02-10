package br.com.webapi.pessoaapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControler {

    private final EnderecoService enderecoService;

    public EnderecoControler(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public List<Endereco> listAll() {
        return enderecoService.getAllEnderecos();
    }

    @GetMapping("/{id}")
    public ResponseEntity getEnderecoById (@PathVariable Long id){
        try {
            Endereco endereco = enderecoService.getEnderecoById(id);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Endereco createEndereco(@RequestBody Endereco endereco) {
        return enderecoService.saveEndereco(endereco);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco){
        try {
            Endereco updatedEndereco = enderecoService.updateEndereco(id, endereco);
            return ResponseEntity.ok(updatedEndereco);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteEndereco(@PathVariable Long id){
        try{
            enderecoService.deleteEnderecoById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
