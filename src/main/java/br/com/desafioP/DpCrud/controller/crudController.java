package br.com.desafioP.DpCrud.controller;

import br.com.desafioP.DpCrud.entity.ItensMagicos;
import br.com.desafioP.DpCrud.entity.Personagem;
import br.com.desafioP.DpCrud.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/crud")
public class crudController {

    @Autowired
    PersonagemService personagemService = new PersonagemService();

    @GetMapping()
    public ResponseEntity<List<Personagem>> getPersonagem() {
        List<Personagem> personagem = personagemService.listarPersonagens();
        return ResponseEntity.ok(personagem);
    }

    @PostMapping()
    public ResponseEntity<String> postPersonagem(@RequestBody Personagem personagem) {
        try {
            personagemService.criarPersonagem(personagem);
            return ResponseEntity.ok("Personagem criado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/personagem/{id}")
    public ResponseEntity<String> mudarNome(@PathVariable Integer id, @RequestBody String nome) {
        try {
            return ResponseEntity.ok("nome alterado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/personagem/item/{id}")
    public ResponseEntity<String> adicionarItem(@PathVariable Integer id, @RequestBody ItensMagicos item) {
        try {
            personagemService.adcionarItem(id, item);
            return ResponseEntity.ok("Item adicionado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping()
    public ResponseEntity<String> putPersonagem(@RequestBody Personagem personagem) {
        personagemService.editarPersonagem(personagem);
        return ResponseEntity.ok("Personagem editado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonagem(@PathVariable Integer id) {
        personagemService.deletarPersonagem(id);
        return ResponseEntity.ok("Personagem deletado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> getPersonagemPorId(@PathVariable Integer id) {
        Personagem personagem = personagemService.buscarPersonagemPorId(id).orElse(null);
        if (personagem != null) {
            return ResponseEntity.ok(personagem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/itens")
    public ResponseEntity<List<ItensMagicos>> getItens() {
        List<ItensMagicos> itens = personagemService.listarItens();
        return ResponseEntity.ok(itens);
    }

    @PostMapping("/itens")
    public ResponseEntity<String> postItens(@RequestBody ItensMagicos item) {
        try {
            personagemService.criarItems(item);
            return ResponseEntity.ok("Item criado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/itens")
    public ResponseEntity<String> putItens(@RequestBody ItensMagicos item) {
        personagemService.editarItem(item);
        return ResponseEntity.ok("Item editado com sucesso!");
    }

    @DeleteMapping("/itens/{id}")
    public ResponseEntity<String> deleteItens(@PathVariable Integer id) {
        personagemService.deletarItem(id);
        return ResponseEntity.ok("Item deletado com sucesso!");
    }
    @GetMapping("/itens/{id}")
    public ResponseEntity<ItensMagicos> getItensPorId(@PathVariable Integer id) {
        ItensMagicos item = personagemService.buscarItemPorId(id).orElse(null);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
