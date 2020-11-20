package br.com.ilegra;

import br.com.ilegra.enums.DesafioEnum;
import br.com.ilegra.enums.MensagemEnum;
import br.com.ilegra.exception.DesafioException;
import br.com.ilegra.listener.Ouvinte;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class Main {

    public static void main(String... args) throws DesafioException {

        FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(
                DesafioEnum.PATH_ARQUIVO_ENTRADA.getValor()
        );

        fileAlterationObserver.addListener(new Ouvinte());

        FileAlterationMonitor fileAlterationMonitor = new FileAlterationMonitor(10, fileAlterationObserver);

        try {
            fileAlterationMonitor.start();
        } catch (Exception e) {
            throw new DesafioException(MensagemEnum.FALHA_OUVINTE);
        }
    }
}
