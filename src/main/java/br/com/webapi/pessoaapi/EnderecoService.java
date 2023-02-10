package br.com.webapi.pessoaapi;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco getEnderecoById(Long id) throws Exception {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new Exception("Endereço não encontrado"));
    }

    private List<Endereco> findByIdAndPrincipal(Long id_pessoa, boolean principal) {
        return findByIdAndPrincipal(id_pessoa,  principal);
    }

    public Endereco saveEndereco(Endereco endereco) {

        List<Endereco> existeEndereco = findByIdAndPrincipal(endereco.getPessoa().getId_pessoa(), endereco.isPrincipal());

        if (!existeEndereco.isEmpty()) {
            throw new RuntimeException("Ja existe endereço principal cadastrado para essa pessoa.");
        }

        return enderecoRepository.save(endereco);
    }

    public Endereco updateEndereco(Long id, Endereco newEndereco) throws Exception {

        if (newEndereco.isPrincipal()) {
            List<Endereco> existeEndereco = findByIdAndPrincipal(newEndereco.getPessoa().getId_pessoa(), newEndereco.isPrincipal());

            if (!existeEndereco.isEmpty()){
                throw new RuntimeException("Ja existe endereço principal cadastrado para essa pessoa.");
            }
        }
        Endereco endereco = getEnderecoById(id);
        endereco.setPrincipal(newEndereco.isPrincipal());
        endereco.setLogradouro(newEndereco.getLogradouro());
        endereco.setCep(newEndereco.getCep());
        endereco.setNumero(newEndereco.getNumero());
        endereco.setCidade(newEndereco.getCidade());

        return saveEndereco(endereco);
    }

    public void deleteEnderecoById(Long id) throws Exception {
        Endereco endereco = getEnderecoById(id);
        enderecoRepository.delete(endereco);
    }
}
