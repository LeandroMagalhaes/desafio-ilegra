package br.com.ilegra.enums;

import lombok.Getter;

@Getter
public enum DesafioEnum {

    IDENTIFICADOR_VENDEDOR("001"),
    IDENTIFICADOR_CLIENTE("002"),
    IDENTIFICADOR_VENDA("003"),

    SEPARADOR_ARQUIVO("ç"),
    SEPARADOR_ITENS("-"),
    SEPARADOR_VENDAS(","),

    PATH_ARQUIVO_ENTRADA("/data/in"),
    PATH_ARQUIVO_SAIDA("/data/out");

    private final String valor;

    DesafioEnum(String valor) {
        this.valor = valor;
    }

}
