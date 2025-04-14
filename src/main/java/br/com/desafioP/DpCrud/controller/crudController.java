package br.com.desafioP.DpCrud.controller;

import br.com.desafioP.DpCrud.entity.ItensMagicos;
import br.com.desafioP.DpCrud.entity.Personagem;
import br.com.desafioP.DpCrud.service.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/crud")
public class crudController {

    @Autowired
    PersonagemService personagemService = new PersonagemService();

    @Operation(summary = "Listar todos os personagens", description = "Retorna uma lista de todos os personagens cadastrados")
    @GetMapping()
    public ResponseEntity<List<Personagem>> getPersonagem() {
        List<Personagem> personagem = personagemService.listarPersonagens();
        return ResponseEntity.ok(personagem);
    }

    @Operation(summary = "Criar um novo personagem", description = "Cria um novo personagem com base nos dados fornecidos")
    @PostMapping()
    public ResponseEntity<String> postPersonagem(@RequestBody Personagem personagem) {
        try {
            personagemService.criarPersonagem(personagem);
            return ResponseEntity.ok("Personagem criado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Alterar o nome de um personagem", description = "Altera o nome de um personagem existente pelo ID")
    @GetMapping("/personagem/{id}")
    public ResponseEntity<String> mudarNome(@PathVariable Integer id, @RequestBody String nome) {
        try {
            personagemService.atualizarNome(id, nome);
            return ResponseEntity.ok("nome alterado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Adicionar um item mágico a um personagem", description = "Adiciona um item mágico a um personagem existente pelo ID")
    @PostMapping("/personagem/item/{id}")
    public ResponseEntity<String> adicionarItem(@PathVariable Integer id, @RequestBody ItensMagicos item) {
        try {
            personagemService.adcionarItem(id, item);
            return ResponseEntity.ok("Item adicionado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Listar itens mágicos de um personagem", description = "Retorna uma lista de todos os itens mágicos associados a um personagem específico pelo ID")
    @GetMapping("/personagem/{id}/itens")
    public ResponseEntity<List<ItensMagicos>> getItensPorPersonagem(@PathVariable Integer id) {
        try {
            List<ItensMagicos> itens = personagemService.buscarIntesPersonagem(id);
            return ResponseEntity.ok(itens);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Remover um item mágico de um personagem", description = "Remove um item mágico específico de um personagem pelo ID do personagem e do item")
    @DeleteMapping("/personagem/{id}/itens/{itemId}")
    public ResponseEntity<String> deleteItemPorPersonagem(@PathVariable Integer id, @PathVariable Integer itemId) {
        try {
            String response = personagemService.deletarItemPersonagem(id, itemId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Editar um personagem", description = "Edita os dados de um personagem existente")
    @PutMapping()
    public ResponseEntity<String> putPersonagem(@RequestBody Personagem personagem) {
        personagemService.editarPersonagem(personagem);
        return ResponseEntity.ok("Personagem editado com sucesso!");
    }

    @Operation(summary = "Deletar um personagem", description = "Remove um personagem existente pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonagem(@PathVariable Integer id) {
        personagemService.deletarPersonagem(id);
        return ResponseEntity.ok("Personagem deletado com sucesso!");
    }

    @Operation(summary = "Buscar um personagem por ID", description = "Retorna os dados de um personagem específico pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Personagem> getPersonagemPorId(@PathVariable Integer id) {
        Personagem personagem = personagemService.buscarPersonagemPorId(id).orElse(null);
        if (personagem != null) {
            return ResponseEntity.ok(personagem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todos os itens mágicos", description = "Retorna uma lista de todos os itens mágicos cadastrados")
    @GetMapping("/itens")
    public ResponseEntity<List<ItensMagicos>> getItens() {
        List<ItensMagicos> itens = personagemService.listarItens();
        return ResponseEntity.ok(itens);
    }

    @Operation(summary = "Criar um novo item mágico", description = "Cria um novo item mágico com base nos dados fornecidos")
    @PostMapping("/itens")
    public ResponseEntity<String> postItens(@RequestBody ItensMagicos item) {
        try {
            personagemService.criarItems(item);
            return ResponseEntity.ok("Item criado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Editar um item mágico", description = "Edita os dados de um item mágico existente")
    @PutMapping("/itens")
    public ResponseEntity<String> putItens(@RequestBody ItensMagicos item) {
        personagemService.editarItem(item);
        return ResponseEntity.ok("Item editado com sucesso!");
    }

    @Operation(summary = "Deletar um item mágico", description = "Remove um item mágico existente pelo ID")
    @DeleteMapping("/itens/{id}")
    public ResponseEntity<String> deleteItens(@PathVariable Integer id) {
        personagemService.deletarItem(id);
        return ResponseEntity.ok("Item deletado com sucesso!");
    }

    @Operation(summary = "Buscar um item mágico por ID", description = "Retorna os dados de um item mágico específico pelo ID")
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
