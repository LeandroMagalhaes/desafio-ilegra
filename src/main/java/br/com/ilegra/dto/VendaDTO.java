package br.com.ilegra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO implements Serializable {

    private Long id;
    private Double valorVenda;
    private VendedorDTO vendedor;
    private List<ProdutoDTO> produtos;

}
