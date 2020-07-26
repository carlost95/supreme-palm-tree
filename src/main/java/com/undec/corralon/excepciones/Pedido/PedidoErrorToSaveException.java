package com.undec.corralon.excepciones.Pedido;

public class PedidoErrorToSaveException extends PedidoException {
    public PedidoErrorToSaveException() {
        super("PedidoErrorToSaveException: Se genero error al guardar un pedido");
    }
}
