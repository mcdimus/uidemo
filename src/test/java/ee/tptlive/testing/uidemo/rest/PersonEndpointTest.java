package ee.tptlive.testing.uidemo.rest;

import ee.tptlive.testing.uidemo.model.Person;
import ee.tptlive.testing.uidemo.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.sun.deploy.config.JREInfo.getAll;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonEndpointTest {

  @Mock
  private PersonService personService;

  @InjectMocks
  private PersonEndpoint personEndpoint;

  @Test
  public void getAll_ReturnsEmptyList_IfServiceReturnsEmptyList() {
    // given
    String name = "John";
    Boolean sex = true;

    when(personService.getAllFilteredByNameAndSex(name, sex)).thenReturn(emptyList());

    // when
    List<Person> result = personEndpoint.getAll(sex, name);

    // then
    assertTrue(result.isEmpty());
  }

  @Test
  public void getAll_ReturnsPersons_IfServiceReturnsPersons() {
    // given
    String name = "John";
    Boolean sex = true;

    when(personService.getAllFilteredByNameAndSex(name, sex)).thenReturn(asList(
        new Person(10, "Hector", 50, false),
        new Person(11, "Pavel", 11, false)
    ));

    // when
    List<Person> result = personEndpoint.getAll(sex, name);

    // then
    assertEquals(2, result.size());
    assertEquals("Hector", result.get(0).getName());
    assertEquals("Pavel", result.get(1).getName());
  }

}