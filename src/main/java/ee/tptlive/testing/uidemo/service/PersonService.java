package ee.tptlive.testing.uidemo.service;


import ee.tptlive.testing.uidemo.dao.PersonDao;
import ee.tptlive.testing.uidemo.model.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class PersonService {

    @Inject
    private PersonDao personDao;

    public List<Person> getAll() {
        return personDao.findAll();
    }

    public List<Person> getAllFilteredByNameAndSex(String name, Boolean sex) {
        Stream<Person> persons = personDao.findAll().stream();
        if (sex != null) {
            persons = persons.filter(person -> sex.equals(person.isSex()));
        }
        if (name != null) {
            persons = persons.filter(person ->
                person.getName().toLowerCase().contains(name.toLowerCase()));
        }
        return persons.collect(toList());
    }

}