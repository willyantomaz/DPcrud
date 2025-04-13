package br.com.desafioP.DpCrud.entity;

import br.com.desafioP.DpCrud.entity.enums.Classe;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "PERSONAGEM")
@Table
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificador;

    private String nome;

    private String nomeAventureiro;

    private Classe classe;

    private Integer level;

    @OneToMany
    private List<ItensMagicos> itensMagicos;

    private Integer forca;

    private Integer defesa;


    public Personagem(Integer identificador, String nome, String nomeAventureiro, Classe classe, Integer level, List<ItensMagicos> itensMagicos, Integer forca, Integer defesa) {
        this.identificador = identificador;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.itensMagicos = itensMagicos;
        this.forca = forca;
        this.defesa = defesa;
    }
    public Personagem() {
    }

    public Integer getForca() {
       return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<ItensMagicos> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItensMagicos> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }
}
