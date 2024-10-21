package com.cursomateus.pizzariadankicode.demo.Pizza;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PizzaDto {
    private Long id;
    @NotBlank
    private String nome;
    @Positive
    private double preco;
    private boolean disponivel;
    private Tamanho tamanho;
    private Sabor Sabor;
    private Categoria categoria;
}
