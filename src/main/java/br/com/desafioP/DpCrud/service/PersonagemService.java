package br.com.desafioP.DpCrud.service;

import br.com.desafioP.DpCrud.entity.ItensMagicos;
import br.com.desafioP.DpCrud.entity.Personagem;
import br.com.desafioP.DpCrud.entity.enums.TipoItem;
import br.com.desafioP.DpCrud.repository.ItensMagicosRepository;
import br.com.desafioP.DpCrud.repository.PersonagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagensRepository personagensRepository;

    @Autowired
    private ItensMagicosRepository itensMagicosRepository;

    public Personagem criarPersonagem(Personagem personagem){
            if((personagem.getForca() + personagem.getDefesa()) > 10){
                throw  new RuntimeException("A soma de defesa e força superou o limite de 10");
            }
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
        if (personagem.getItensMagicos().stream().map(item -> item.getTipo() == TipoItem.AMULETO).isParallel()){
            throw new RuntimeException("Personagem já tem Amuleto");
        }
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
        Optional<Personagem> personagem = this.personagensRepository.findById(id);
        return Optional.of(somarStatus(personagem.get()));

    }
    public Optional<ItensMagicos> buscarItemPorId(Integer id){
        return this.itensMagicosRepository.findById(id);
    }
    public List<Personagem> listarPersonagens(){
        List<Personagem> personagens = this.personagensRepository.findAll();
        personagens.forEach(this::somarStatus);
        return personagens;
    }
    public List<ItensMagicos> listarItens(){
        return this.itensMagicosRepository.findAll();
    }

    public Personagem atualizarNome(Integer id,String nome){
        if(this.personagensRepository.findById(id).isEmpty()){
            throw new RuntimeException("Personagem não encontrado");
        }
        Personagem personagem = this.personagensRepository.findById(id).get();
        personagem.setNome(nome);
        return this.personagensRepository.save(personagem);
    }

    public Personagem adcionarItem(Integer id,ItensMagicos itensMagicos){
        if (this.personagensRepository.findById(id).isEmpty()){
            throw new RuntimeException("Personagem não encontrado");
        }
        Personagem personagem = this.personagensRepository.findById(id).get();
        if (personagem.getItensMagicos().stream().map(item -> item.getTipo() == TipoItem.AMULETO).isParallel()){
            throw new RuntimeException("Personagem já tem Amuleto");
        }
        verificaTipo(itensMagicos.getTipo(), itensMagicos.getDefesa(), itensMagicos.getForca());
        personagem.setItensMagicos(Collections.singletonList(itensMagicos));
        return this.personagensRepository.save(personagem);
    }

    public List<ItensMagicos> buscarIntesPersonagem(Integer id){
        if(this.personagensRepository.findById(id).isEmpty()){
            throw new RuntimeException("Personagem não encontrado");
        }
        Personagem personagem = this.personagensRepository.findById(id).get();
        List<ItensMagicos> itensMagicos = personagem.getItensMagicos();
        return itensMagicos;
    }

    public String deletarItemPersonagem(Integer idPersonagem,Integer idItem){
        if(this.personagensRepository.findById(idPersonagem).isEmpty()){
            throw new RuntimeException("Personagem não encontrado");
        }
        Personagem personagem = this.personagensRepository.findById(idPersonagem).get();
        List<ItensMagicos> itensMagicos = personagem.getItensMagicos();
        ItensMagicos item = this.itensMagicosRepository.findById(idItem).get();
        if(itensMagicos.contains(item)){
            itensMagicos.remove(item);
            personagem.setItensMagicos(itensMagicos);
            this.personagensRepository.save(personagem);
            return "Item removido com sucesso";
        }else{
            throw new RuntimeException("Item não encontrado");
        }
    }

    Personagem somarStatus(Personagem personagem){
        if(!personagem.getItensMagicos().isEmpty()){
            for (ItensMagicos item : personagem.getItensMagicos()) {
                personagem.setForca(personagem.getForca() + item.getForca());
                personagem.setDefesa(personagem.getDefesa() + item.getDefesa());
            }
        }
        return personagem;
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
