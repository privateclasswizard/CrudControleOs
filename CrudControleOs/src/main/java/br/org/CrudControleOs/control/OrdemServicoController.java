package br.org.CrudControleOs.control;

import br.org.CrudControleOs.model.Cliente;
import br.org.CrudControleOs.model.OrdemServico;
import br.org.CrudControleOs.model.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository repository;

    @GetMapping({"", "/"})
    public List<OrdemServico> getOrdensServico() {
        return repository.findAll(Sort.by("data"));
    }

    @GetMapping("/{id}/cliente")
    public Cliente getClienteDaOrdemServico(@PathVariable("id") Long id) {
        OrdemServico ordemServico = repository.findById(id).orElse(null);
        if (ordemServico != null) {
            return ordemServico.getCliente();
        }
        return null;
    }

    @PostMapping({"", "/"})
    public OrdemServico insere(@RequestBody OrdemServico ordemServico) {
        return repository.save(ordemServico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualiza(@PathVariable("id") Long id, @RequestBody OrdemServico ordemServico) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        ordemServico.setId(id);
        return ResponseEntity.ok(repository.save(ordemServico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> exclui(@PathVariable("id") Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.ok("Excluído");
    }
}