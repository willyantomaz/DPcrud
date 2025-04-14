package br.com.desafioP.DpCrud.entity;

import br.com.desafioP.DpCrud.entity.enums.TipoItem;
import jakarta.persistence.*;

@Entity(name = "ITENSMAGICOS")
@Table
public class ItensMagicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificador;

    private String nome;

    private TipoItem tipo;

    private Integer forca;

    private Integer defesa;

    public ItensMagicos(Integer indentificador, String nome, TipoItem tipo, Integer forca, Integer defesa) {
        this.identificador = indentificador;
        this.nome = nome;
        this.tipo = tipo;
        this.forca = forca;
        this.defesa = defesa;
    }

    public ItensMagicos() {
    }

    public Integer getIndentificador() {
        return identificador;
    }

    public void setIndentificador(Integer indentificador) {
        this.identificador = indentificador;
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
}
