package ee.tptlive.testing.uidemo.service;

import ee.tptlive.testing.uidemo.dao.PersonDao;
import ee.tptlive.testing.uidemo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

  @Mock
  private PersonDao personDao;

  @InjectMocks
  private PersonService personService;

  @Test
  public void getAll_ReturnsEmptyList_IfNothingFound() {
    // given
    when(personDao.findAll()).thenReturn(emptyList());

    // when
    List<Person> result = personService.getAll();

    // then
    assertTrue(result.isEmpty());
  }

  @Test
  public void getAll_ReturnsAllFromDao_IfDaoFindsSomething() {
    // given
    when(personDao.findAll()).thenReturn(asList(
          new Person(10, "Hector", 50, false),
          new Person(11, "Pavel", 11, false)
    ));

    // when
    List<Person> result = personService.getAll();

    // then
    assertEquals(2, result.size());
    assertEquals("Hector", result.get(0).getName());
    assertEquals("Pavel", result.get(1).getName());
  }

  @Test
  public void getAllFilteredByNameAndSex_ReturnsEmptyList_IfNothingFoundAndNoFilters() {
    // given
    when(personDao.findAll()).thenReturn(emptyList());

    String name = null;
    Boolean sex = null;

    // when
    List<Person> result = personService.getAllFilteredByNameAndSex(name, sex);

    // then
    assertTrue(result.isEmpty());
  }

  @Test
  public void getAllFilteredByNameAndSex_ReturnsEmptyList_IfNothingFoundAndHasFilters() {
    // given
    when(personDao.findAll()).thenReturn(emptyList());

    String name = "e";
    Boolean sex = true;

    // when
    List<Person> result = personService.getAllFilteredByNameAndSex(name, sex);

    // then
    assertTrue(result.isEmpty());
  }

  @Test
  public void getAllFilteredByNameAndSex_ReturnsEmptyList_IfFoundAndEverythingIsFilteredOut() {
    // given
    when(personDao.findAll()).thenReturn(asList(
        new Person(1, "John", 30, false),
        new Person(2, "Dacota", 19, true),
        new Person(3, "Stephen", 34, false),
        new Person(4, "Jelena", 70, true),
        new Person(5, "Elizabeth", 99, true),
        new Person(6, "Johnson", 56, false)
    ));

    String name = "this_will_not_match_anything";
    Boolean sex = true;

    // when
    List<Person> result = personService.getAllFilteredByNameAndSex(name, sex);

    // then
    assertTrue(result.isEmpty());
  }

  @Test
  public void getAllFilteredByNameAndSex_ReturnsOneEntry_IfFoundAndFilteringByNameWithExactMatch() {
    // given
    when(personDao.findAll()).thenReturn(asList(
        new Person(1, "John", 30, false),
        new Person(2, "Dacota", 19, true),
        new Person(3, "Stephen", 34, false),
        new Person(4, "Jelena", 70, true),
        new Person(5, "Elizabeth", 99, true),
        new Person(6, "Johnson", 56, false)
    ));

    String name = "Dacota";
    Boolean sex = null;

    // when
    List<Person> result = personService.getAllFilteredByNameAndSex(name, sex);

    // then
    assertEquals(1, result.size());
    assertEquals("Dacota", result.get(0).getName());
  }

  @Test
  public void getAllFilteredByNameAndSex_ReturnsOneEntry_IfFoundAndFilteringByNameWithCaseInsensitiveMatch() {
    // given
    when(personDao.findAll()).thenReturn(asList(
        new Person(1, "John", 30, false),
        new Person(2, "Dacota", 19, true),
        new Person(3, "Stephen", 34, false),
        new Person(4, "Jelena", 70, true),
        new Person(5, "Elizabeth", 99, true),
        new Person(6, "Johnson", 56, false)
    ));

    String name = "daCOTa";
    Boolean sex = null;

    // when
    List<Person> result = personService.getAllFilteredByNameAndSex(name, sex);

    // then
    assertEquals(1, result.size());
    assertEquals("Dacota", result.get(0).getName());
  }

  @Test
  public void getAllFilteredByNameAndSex_ReturnsTwoEntries_IfFoundAndFilteringByNameWithPartialMatch() {
    // given
    when(personDao.findAll()).thenReturn(asList(
        new Person(1, "John", 30, false),
        new Person(2, "Dacota", 19, true),
        new Person(3, "Stephen", 34, false),
        new Person(4, "Jelena", 70, true),
        new Person(5, "Elizabeth", 99, true),
        new Person(6, "Johnsonetta", 56, true)
    ));

    String name = "OHN";
    Boolean sex = null;

    // when
    List<Person> result = personService.getAllFilteredByNameAndSex(name, sex);

    // then
    assertEquals(2, result.size());
    assertEquals("John", result.get(0).getName());
    assertEquals("Johnsonetta", result.get(1).getName());
  }

  @Test
  public void getAllFilteredByNameAndSex_ReturnsAllWomen_IfFoundAndFilteringBySexWithTrue() {
    // given
    when(personDao.findAll()).thenReturn(asList(
        new Person(1, "John", 30, false),
        new Person(2, "Dacota", 19, true),
        new Person(3, "Stephen", 34, false),
        new Person(4, "Jelena", 70, true),
        new Person(5, "Elizabeth", 99, true),
        new Person(6, "Johnsonetta", 56, true)
    ));

    String name = null;
    Boolean sex = true;

    // when
    List<Person> result = personService.getAllFilteredByNameAndSex(name, sex);

    // then
    assertEquals(4, result.size());
    assertEquals("Dacota", result.get(0).getName());
    assertEquals("Jelena", result.get(1).getName());
    assertEquals("Elizabeth", result.get(2).getName());
    assertEquals("Johnsonetta", result.get(3).getName());
  }

  @Test
  public void getAllFilteredByNameAndSex_ReturnsAllMen_IfFoundAndFilteringBySexWithFalse() {
    // given
    when(personDao.findAll()).thenReturn(asList(
        new Person(1, "John", 30, false),
        new Person(2, "Dacota", 19, true),
        new Person(3, "Stephen", 34, false),
        new Person(4, "Jelena", 70, true),
        new Person(5, "Elizabeth", 99, true),
        new Person(6, "Johnsonetta", 56, true)
    ));

    String name = null;
    Boolean sex = false;

    // when
    List<Person> result = personService.getAllFilteredByNameAndSex(name, sex);

    // then
    assertEquals(2, result.size());
    assertEquals("John", result.get(0).getName());
    assertEquals("Stephen", result.get(1).getName());
  }

  @Test
  public void getAllFilteredByNameAndSex_ReturnsOneEntry_IfFoundAndFilteringByNameAndSexWithFalse() {
    // given
    when(personDao.findAll()).thenReturn(asList(
        new Person(1, "John", 30, false),
        new Person(2, "Dacota", 19, true),
        new Person(3, "Stephen", 34, false),
        new Person(4, "Jelena", 70, true),
        new Person(5, "Elizabeth", 99, true),
        new Person(6, "Johnsonetta", 56, true)
    ));

    String name = "OHN";
    Boolean sex = false;

    // when
    List<Person> result = personService.getAllFilteredByNameAndSex(name, sex);

    // then
    assertEquals(1, result.size());
    assertEquals("John", result.get(0).getName());
  }

}