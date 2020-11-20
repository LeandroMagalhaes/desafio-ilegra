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
public class ArquivoDTO implements Serializable {

    private String nomeArquivo;
    private Long quantidadeClientes;
    private Long quantidadeVendedores;
    private Long idMaiorVenda;
    private String nomePiorVendedor;

}
