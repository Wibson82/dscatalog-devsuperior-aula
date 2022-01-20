package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.Category;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

public class CategoryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    public CategoryDTO() {
    }
    public CategoryDTO(Category obj) {
        id = obj.getId();
        nome = obj.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
