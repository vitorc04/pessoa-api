package br.com.webapi.pessoaapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa getPessoaById(Long id) throws Exception {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new Exception("Pessoa não encontrada"));
    }

    public Pessoa savePessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa newPessoa) throws Exception {
        Pessoa pessoa = getPessoaById(id);
        pessoa.setNome(newPessoa.getNome());
        pessoa.setDataNascimento(newPessoa.getDataNascimento());
        return savePessoa(pessoa);
    }

    public void deletePessoaById(Long id) throws Exception {
        Pessoa pessoa = getPessoaById(id);
        pessoaRepository.delete(pessoa);
    }

}
