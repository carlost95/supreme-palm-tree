package com.undec.corralon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static Date stringToDate(String stringDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaFormateada = simpleDateFormat.parse(stringDate);
        return fechaFormateada;
    }
}
