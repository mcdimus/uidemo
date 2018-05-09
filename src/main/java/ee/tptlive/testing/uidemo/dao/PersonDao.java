package ee.tptlive.testing.uidemo.dao;

import ee.tptlive.testing.uidemo.model.Person;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@ApplicationScoped
public class PersonDao {

    private List<Person> database = asList(
            new Person(1, "John", 30, false),
            new Person(2, "Dacota", 19, true),
            new Person(3, "Stephen", 34, false),
            new Person(4, "Jelena", 70, true),
            new Person(5, "Elizabeth", 99, true),
            new Person(6, "Boris", 39, false),
            new Person(7, "Lucy", 12, true),
            new Person(8, "Nick", 66, false),
            new Person(9, "Regina", 33, true),
            new Person(10, "Margarett", 42, true)
    );

    public List<Person> findAll() {
        return new ArrayList<>(database);
    }

}