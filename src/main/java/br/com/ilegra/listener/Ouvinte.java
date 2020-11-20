package br.com.ilegra.listener;

import br.com.ilegra.service.ArquivoService;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class Ouvinte implements FileAlterationListener {

    private static final ArquivoService arquivoService = new ArquivoService();

    private static void init() {
        try {
            arquivoService.listarArquivos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFileCreate(File file) {
        init();
    }

    @Override
    public void onFileChange(File file) {
        init();
    }

    @Override
    public void onFileDelete(File file) {
        init();
    }

    @Override
    public void onStart(FileAlterationObserver fileAlterationObserver) {
    }

    @Override
    public void onDirectoryCreate(File file) {
    }

    @Override
    public void onDirectoryChange(File file) {
    }

    @Override
    public void onDirectoryDelete(File file) {
    }

    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {
    }
}
