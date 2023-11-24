package br.org.CrudControleOs.control;

import br.org.CrudControleOs.model.Cliente;
import br.org.CrudControleOs.model.ClienteRepository;
import br.org.CrudControleOs.model.OrdemServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repositorio;

    @GetMapping("/{id}/ordens-servico")
    public List<OrdemServico> getOrdensServicoDoCliente(@PathVariable("id") Long id) {
        Cliente cliente = repositorio.findById(id).orElse(null);
        if (cliente != null) {
            return cliente.getOrdensDeServico();
        }
        return null;
    }
    
    @GetMapping
    public List<Cliente> getClientes() {
        List<Cliente> clientes = repositorio.findAll(Sort.by("nome"));
        return clientes;
    }

    @PostMapping({ "", "/" })
    public Cliente inserir(@RequestBody Cliente cliente) {
        return repositorio.save(cliente);
    }

    @PutMapping({ "", "/" })
    public Cliente atualizar(@RequestBody Cliente cliente) {
        if (cliente.getIdCliente() != null) {
            return repositorio.save(cliente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable("id") Long id) {
        if (id != null) {
            repositorio.deleteById(id);
            return "Excluído";
        }
        return null;
    }
}