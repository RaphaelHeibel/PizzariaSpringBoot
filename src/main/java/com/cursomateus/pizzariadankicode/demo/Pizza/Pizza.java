package com.cursomateus.pizzariadankicode.demo.Pizza;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Pizza")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    private boolean disponivel;
    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;
    @Enumerated(EnumType.STRING)
    private Sabor Sabor;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
