package ee.tptlive.testing.uidemo.rest;

import ee.tptlive.testing.uidemo.model.Person;
import ee.tptlive.testing.uidemo.service.PersonService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Path("persons")
public class PersonEndpoint {

  @Inject
  private PersonService personService;

  @GET
  @Produces(APPLICATION_JSON)
  public List<Person> getAll(
      @QueryParam("sex") Boolean sex,
      @QueryParam("name") String name
  ) {
    return personService.getAllFilteredByNameAndSex(name, sex);
  }

}