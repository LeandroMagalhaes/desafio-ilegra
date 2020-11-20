package br.com.ilegra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendedorDTO implements Serializable {

    private String documento;
    private String nome;
    private String salario;

}
