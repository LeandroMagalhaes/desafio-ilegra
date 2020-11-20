package br.com.ilegra.util;

import br.com.ilegra.dto.ClienteDTO;
import br.com.ilegra.dto.ProdutoDTO;
import br.com.ilegra.dto.VendaDTO;
import br.com.ilegra.dto.VendedorDTO;
import br.com.ilegra.enums.DesafioEnum;
import br.com.ilegra.enums.MensagemEnum;
import br.com.ilegra.exception.DesafioException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DesafioUtils {

    private DesafioUtils() {
    }

    public static VendedorDTO montarEstruturaVendedor(String linha) throws DesafioException {
        List<String> vendedores = Stream.of(linha.split(DesafioEnum.SEPARADOR_ARQUIVO.getValor()))
                .collect(Collectors.toList());

        if (vendedores.isEmpty() || vendedores.size() > 4) {
            throw new DesafioException(MensagemEnum.FALHA_PROCESSAR_VENDEDORES.getValor());
        }

        return VendedorDTO.builder()
                .documento(vendedores.get(1))
                .nome(vendedores.get(2))
                .salario(vendedores.get(3))
                .build();
    }

    public static ClienteDTO montarEstruturaCliente(String linha) throws DesafioException {
        List<String> clientes = Stream.of(linha.split(DesafioEnum.SEPARADOR_ARQUIVO.getValor()))
                .collect(Collectors.toList());

        if (clientes.isEmpty() || clientes.size() > 4) {
            throw new DesafioException(MensagemEnum.FALHA_PROCESSAR_CLIENTES);
        }

        return ClienteDTO.builder()
                .documento(clientes.get(1))
                .nome(clientes.get(2))
                .trabalho(clientes.get(3))
                .build();
    }

    public static VendaDTO montarEstruturaVenda(String linha) throws DesafioException {
        List<String> vendas = Stream.of(linha.split(DesafioEnum.SEPARADOR_ARQUIVO.getValor()))
                .collect(Collectors.toList());

        if (vendas.isEmpty() || vendas.size() > 4) {
            throw new DesafioException(MensagemEnum.FALHA_PROCESSAR_VENDAS.getValor());
        }

        VendaDTO vendaDTO = VendaDTO.builder()
                .id(Long.valueOf(vendas.get(1)))
                .vendedor(VendedorDTO.builder()
                        .nome(vendas.get(3))
                        .build())
                .produtos(new ArrayList<>())
                .build();

        vendaDTO.setProdutos(montarEstruturaProduto(vendas));

        return vendaDTO;
    }

    public static List<ProdutoDTO> montarEstruturaProduto(List<String> linha) throws DesafioException {
        List<String> vendas = Stream.of(linha.get(2)
                .replace("[", "")
                .replace("]", "")
                .split(DesafioEnum.SEPARADOR_VENDAS.getValor()))
                .collect(Collectors.toList());

        if (vendas.isEmpty()) {
            throw new DesafioException(MensagemEnum.FALHA_PROCESSAR_PRODUTOS.getValor());
        }

        List<ProdutoDTO> produtos = new ArrayList<>();
        vendas.forEach(s -> {
            List<String> produto = Stream.of(s.split(DesafioEnum.SEPARADOR_ITENS.getValor()))
                    .collect(Collectors.toList());

            ProdutoDTO produtoDTO = ProdutoDTO.builder()
                    .id(Long.valueOf(produto.get(0)))
                    .quantidade(Long.valueOf(produto.get(1)))
                    .valor(Double.valueOf(produto.get(2)))
                    .build();

            produtos.add(produtoDTO);
        });

        return produtos;
    }
}
