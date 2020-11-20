package br.com.ilegra;

import br.com.ilegra.service.ArquivoService;

public class Main {

    private static final ArquivoService arquivoService = new ArquivoService();

    public static void main(String... args) throws Exception {
        arquivoService.listarArquivos();
    }
}
