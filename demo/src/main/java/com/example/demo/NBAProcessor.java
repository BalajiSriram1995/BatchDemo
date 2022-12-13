package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class NBAProcessor implements ItemProcessor<NBA, NBADTO> {
    private static final Logger log = LoggerFactory.getLogger(NBAProcessor.class);

    public NBADTO process(final NBA NBA)  {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        NBAStats stat=new NBAStats(Integer.parseInt(NBA.getHeight()),Integer.parseInt(NBA.getWeight()), Float.parseFloat(NBA.getAge()), dateFormat.format(new Date()));

        final NBADTO transformedPerson = new NBADTO(NBA.getName(), NBA.getTeam(),NBA.getPosition(),stat);

        log.info("Data: "+transformedPerson);

        return transformedPerson;
    }
}
