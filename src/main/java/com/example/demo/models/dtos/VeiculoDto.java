package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDto {
    private Long id;

    private String nome;
    private String marca;
    private Double valor;
    private Integer ano;

    private Long donoId;
    private String donoName;
}
