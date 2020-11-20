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
public class ProdutoDTO implements Serializable {

    private Long id;
    private Long quantidade;
    private Double valor;

}
