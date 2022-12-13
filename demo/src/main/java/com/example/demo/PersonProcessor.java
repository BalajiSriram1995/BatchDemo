package com.example.demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class PersonProcessor implements ItemProcessor<Person,Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonProcessor.class);

    public Person process(final Person person)  {
        final String firstName = person.getName().toUpperCase();
        final String lastName = person.getAddress().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName,person.getExp1(),person.getExp2(),person.getAge1(), person.getAge2());

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}


