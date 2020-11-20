package br.com.ilegra.enums;

import lombok.Getter;

@Getter
public enum MensagemEnum {

    MSG001("Quantidade de clientes no arquivo de entrada: "),
    MSG002("Quantidade de vendedores no arquivo de entrada: "),
    MSG003("ID da venda mais cara: "),
    MSG004("O pior vendedor: "),

    FALHA_PROCESSAR_ARQUIVO("Falha ao processar Arquivo! "),
    FALHA_PROCESSAR_CLIENTES("Erro ao processar os Clientes, Tamanho Inválido! "),
    FALHA_PROCESSAR_VENDEDORES("Erro ao processar os Vendedores, Tamanho Inválido! "),
    FALHA_PROCESSAR_VENDAS("Erro ao processar as Vendas, Tamanho Inválido! "),
    FALHA_PROCESSAR_PRODUTOS("Erro ao processar os Produtos! "),
    FALHA_SALVAR_ARQUIVO("Falha ao Salvar o Arquivo! "),
    ;

    private final String valor;

    MensagemEnum(String valor) {
        this.valor = valor;
    }
}
