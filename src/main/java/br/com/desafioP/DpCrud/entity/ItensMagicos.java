package br.com.desafioP.DpCrud.entity;

import br.com.desafioP.DpCrud.entity.enums.TipoItem;
import jakarta.persistence.*;

@Entity(name = "ITENSMAGICOS")
@Table
public class ItensMagicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer indentificador;

    private String nome;

    private TipoItem tipo;

    private Integer força;

    private Integer defesa;

    public ItensMagicos(Integer indentificador, String nome, TipoItem tipo, Integer força, Integer defesa) {
        this.indentificador = indentificador;
        this.nome = nome;
        this.tipo = tipo;
        this.força = força;
        this.defesa = defesa;
    }

    public Integer getIndentificador() {
        return indentificador;
    }

    public void setIndentificador(Integer indentificador) {
        this.indentificador = indentificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public Integer getForça() {
        return força;
    }

    public void setForça(Integer força) {
        this.força = força;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }
}
