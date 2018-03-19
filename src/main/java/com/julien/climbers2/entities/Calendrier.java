package com.julien.climbers2.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Calendrier {

    private String year;

    String firstDay = "01/01/2018";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date givenDate;
/*
        try {
            givenDate = sdf.parse(firstDay);
            Calendar c = Calendar.getInstance();
            c.setTime(givenDate);
            System.out.println(c.get(Calendar.DAY_OF_WEEK));
        } catch (ParseException e1) {
            e1.getMessage();
            System.out.println("failed");
        }



    }
*/
}
