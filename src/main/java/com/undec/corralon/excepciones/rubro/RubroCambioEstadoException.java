package com.undec.corralon.excepciones.rubro;
public class RubroCambioEstadoException extends RubroException  {
    public RubroCambioEstadoException() {
        super("RubroCambioEstadoException: error al cambiar el estado del rubro");
    }
}
