package br.com.desafioP.DpCrud.service;

import br.com.desafioP.DpCrud.entity.ItensMagicos;
import br.com.desafioP.DpCrud.entity.Personagem;
import br.com.desafioP.DpCrud.entity.enums.TipoItem;
import br.com.desafioP.DpCrud.repository.ItensMagicosRepository;
import br.com.desafioP.DpCrud.repository.PersonagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagensRepository personagensRepository;

    @Autowired
    private ItensMagicosRepository itensMagicosRepository;

    public Personagem criarPersonagem(Personagem personagem){
            return this.personagensRepository.save(personagem);
    }

    public ItensMagicos criarItems(ItensMagicos item){
            if(item.getForca() > 10){
               throw  new RuntimeException("A força não pode ser maior que 10");
            }
            if(item.getDefesa() > 10){
               throw  new RuntimeException("A defesa não pode ser maior que 10");
            }
            if(item.getForca() == 0 && item.getDefesa() == 0){
                throw new RuntimeException("A força e a defesa não podem ser 0");
            }
            verificaTipo(item.getTipo(), item.getDefesa(), item.getForca());

            return this.itensMagicosRepository.save(item);




    }

    public Personagem editarPersonagem(Personagem personagem){
        return this.personagensRepository.save(personagem);
    }

    public ItensMagicos editarItem(ItensMagicos item){
        return this.itensMagicosRepository.save(item);
    }
    public void deletarPersonagem(Integer id){
        this.personagensRepository.deleteById(id);
    }
    public void deletarItem(Integer id){
        this.itensMagicosRepository.deleteById(id);
    }
    public Optional<Personagem> buscarPersonagemPorId(Integer id){
        return this.personagensRepository.findById(id);
    }
    public Optional<ItensMagicos> buscarItemPorId(Integer id){
        return this.itensMagicosRepository.findById(id);
    }
    public List<Personagem> listarPersonagens(){
        return this.personagensRepository.findAll();
    }
    public List<ItensMagicos> listarItens(){
        return this.itensMagicosRepository.findAll();
    }


    void verificaTipo(TipoItem tipoItem, Integer defesa, Integer forca){
        switch (tipoItem){
            case ARMA:
                if(defesa != 0){
                    throw new RuntimeException("no tipo arma a defesa deve ser 0");
                }
                break;
            case ARMADURA:
                if(forca != 0){
                    throw new RuntimeException("no tipo armadura a força deve ser 0");
                }
                break;
        }
    }
}
