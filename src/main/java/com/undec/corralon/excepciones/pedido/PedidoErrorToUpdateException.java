package com.undec.corralon.excepciones.pedido;

public class PedidoErrorToUpdateException extends PedidoException {
    public PedidoErrorToUpdateException() {
        super("PedidoErrorToUpdateException: Se genero error al actualizar");
    }
}
