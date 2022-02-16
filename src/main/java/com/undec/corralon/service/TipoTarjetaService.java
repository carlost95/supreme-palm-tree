package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Tipotarjeta;
import com.undec.corralon.repository.TipoTarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoTarjetaService {
    @Autowired
    TipoTarjetaRepository tipoTarjetaRepository;

    public List<Tipotarjeta> findAllCards() {
        List<Tipotarjeta> cards = tipoTarjetaRepository.findAll();
        if (cards == null) {
            throw new NotFoundException("\nWARNING: Error no exiten Tipos de tarjetas");
        }
        return cards;
    }

    public Tipotarjeta findCardById(Integer id) {
        Tipotarjeta card = this.tipoTarjetaRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: Error no existe tipo tarjeta por id"));
        return card;
    }

    public Tipotarjeta saveCard(Tipotarjeta receivedCard) {
        Tipotarjeta cardToSave = new Tipotarjeta();

        cardToSave.setHabilitado(true);
        cardToSave.setNombreTipo(receivedCard.getNombreTipo());
        cardToSave = tipoTarjetaRepository.save(cardToSave);

        if (cardToSave == null) {
            throw new NotFoundException("\nWARNING: No se guardo el tipo de tarjeta");
        }
        return cardToSave;
    }

    public Tipotarjeta modifyTypeCard(Tipotarjeta typeCard) {
        if (typeCard.getNombreTipo() == null) {
            throw new BadRequestException("\nWARNING: No se permiten nombre nullos para los tipos de tarjetas");
        }
        Tipotarjeta typeCardModify = tipoTarjetaRepository.findById(typeCard.getIdTipoTarjeta()).
                orElseThrow(
                        () -> new NotFoundException("\nError:No existe el tipo de atarjeta a modificar"));
        typeCardModify.setNombreTipo(typeCard.getNombreTipo());
        typeCardModify=tipoTarjetaRepository.save(typeCardModify);
        if (typeCardModify == null){
            throw new NotFoundException("\nError al modificar el tipo de tarjeta");
        }
        return  typeCardModify;
    }

    public Tipotarjeta changeEnablementToTypeCard(Integer id) {
        if (id == null) {
            throw new BadRequestException("\nWARNING: No se envio ningun Id correspondiente al tipo de tarjeta");
        }
        Tipotarjeta typeCard = tipoTarjetaRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("WARNIGN: no existe el ajuste"));
        typeCard.setHabilitado(!typeCard.getHabilitado());
        typeCard = tipoTarjetaRepository.save(typeCard);
        if (typeCard == null) {
            throw new NotFoundException("\nWARNING: Error no se pudo hacer el cambio de estado del tipo de tarjeta");
        }
        return typeCard;
    }
}
