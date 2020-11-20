package br.com.ilegra.exception;

import br.com.ilegra.enums.MensagemEnum;
import lombok.Getter;

@Getter
public class DesafioException extends Exception {

    public DesafioException(MensagemEnum mensagem) {
        super(mensagem.name());
    }

    public DesafioException(String mensagem) {
        super(mensagem);
    }

    public DesafioException(String mensagem, Exception e) {
        super(mensagem, e);
    }
}
