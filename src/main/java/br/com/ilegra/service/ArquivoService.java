package br.com.ilegra.service;

import br.com.ilegra.dto.ArquivoDTO;
import br.com.ilegra.dto.ClienteDTO;
import br.com.ilegra.dto.VendaDTO;
import br.com.ilegra.dto.VendedorDTO;
import br.com.ilegra.enums.DesafioEnum;
import br.com.ilegra.enums.MensagemEnum;
import br.com.ilegra.exception.DesafioException;
import br.com.ilegra.util.DesafioUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class ArquivoService {

    private static final String LIMITADOR = DesafioEnum.SEPARADOR_ARQUIVO.getValor();

    public void listarArquivos() throws DesafioException {

        File arquivo = new File(DesafioEnum.PATH_ARQUIVO_ENTRADA.getValor());
        File[] arquivos = arquivo.listFiles();

        if (Objects.nonNull(arquivos)) {
            for (File tmp : arquivos) {
                ArquivoDTO arquivoDTO = processarArquivo(tmp);
                salvarArquivoTratado(arquivoDTO);
            }
        }
    }

    private void salvarArquivoTratado(ArquivoDTO arquivoDTO) throws DesafioException {

        File diretorioSaida = new File(DesafioEnum.PATH_ARQUIVO_SAIDA.getValor());
        if (diretorioSaida.exists() || diretorioSaida.mkdirs()) {
            File arquivo = new File(String.valueOf(diretorioSaida), arquivoDTO.getNomeArquivo());

            try (FileWriter fileWriter = new FileWriter(arquivo, false);) {
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.println(montarArquivo(arquivoDTO));

                printWriter.flush();
                printWriter.close();
            } catch (Exception e) {
                throw new DesafioException(MensagemEnum.FALHA_SALVAR_ARQUIVO.getValor() + e);
            }
        }
    }

    public String montarArquivo(ArquivoDTO arquivoDTO) {

        return MensagemEnum.MSG001.getValor() + arquivoDTO.getQuantidadeClientes() + "\n\r" +
                MensagemEnum.MSG002.getValor() + arquivoDTO.getQuantidadeVendedores() + "\n\r" +
                MensagemEnum.MSG003.getValor() + arquivoDTO.getIdMaiorVenda() + "\n\r" +
                MensagemEnum.MSG004.getValor() + arquivoDTO.getNomePiorVendedor() + "\n\r";
    }

    private ArquivoDTO processarArquivo(File arquivo) throws DesafioException {

        String caminhoArquivo = arquivo.getAbsolutePath();
        String nomeArquivo = arquivo.getName();

        try (Scanner scanner = new Scanner(new FileReader(caminhoArquivo))) {
            List<VendedorDTO> vendedores = new ArrayList<>();
            List<ClienteDTO> clientes = new ArrayList<>();
            List<VendaDTO> vendas = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();

                if (linha.contains(DesafioEnum.IDENTIFICADOR_VENDEDOR.getValor() + LIMITADOR)) {
                    vendedores.add(DesafioUtils.montarEstruturaVendedor(linha));

                } else if (linha.contains(DesafioEnum.IDENTIFICADOR_CLIENTE.getValor() + LIMITADOR)) {
                    clientes.add(DesafioUtils.montarEstruturaCliente(linha));

                } else if (linha.contains(DesafioEnum.IDENTIFICADOR_VENDA.getValor() + LIMITADOR)) {
                    VendaDTO vendaDTO = DesafioUtils.montarEstruturaVenda(linha);
                    calcularValorVendas(vendaDTO);
                    vendas.add(vendaDTO);
                }
            }

            Long quantidadeVendedores = (long) vendedores.size();
            Long qtdClientes = (long) clientes.size();
            Long idMaiorVenda = buscarMaiorVendaPorId(vendas);
            String vendedor = buscarMenorVendaPorVendedor(vendas);

            return new ArquivoDTO(nomeArquivo, qtdClientes, quantidadeVendedores, idMaiorVenda, vendedor);
        } catch (Exception e) {
            throw new DesafioException(MensagemEnum.FALHA_PROCESSAR_ARQUIVO + nomeArquivo, e);
        }
    }

    private static void calcularValorVendas(VendaDTO vendaDTO) {

        if (Objects.nonNull(vendaDTO.getProdutos()) && !vendaDTO.getProdutos().isEmpty()) {
            double soma = vendaDTO.getProdutos().stream()
                    .mapToDouble(produtoDTO -> (produtoDTO.getValor() * produtoDTO.getQuantidade()))
                    .sum();

            vendaDTO.setValorVenda(soma);
        }
    }

    private Long buscarMaiorVendaPorId(List<VendaDTO> vendas) {

        Optional<VendaDTO> maiorVenda = vendas.stream().max(
                Comparator.comparingDouble(VendaDTO::getValorVenda)
        );

        return maiorVenda.map(VendaDTO::getId)
                .orElse(null);
    }

    private String buscarMenorVendaPorVendedor(List<VendaDTO> vendas) {

        Optional<VendaDTO> menorVenda = vendas.stream().min(
                Comparator.comparingDouble(VendaDTO::getValorVenda)
        );

        return menorVenda.map(vendaDTO -> vendaDTO.getVendedor().getNome())
                .orElse(null);
    }

}