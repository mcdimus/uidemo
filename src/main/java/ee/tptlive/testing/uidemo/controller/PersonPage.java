package ee.tptlive.testing.uidemo.controller;

import ee.tptlive.testing.uidemo.model.Person;
import ee.tptlive.testing.uidemo.service.PersonService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class PersonPage {

  @Inject
  private PersonService personRepository;

  private int selectedId;
  private Person selectedPerson;

  public List<Person> getPersons() {
    return personRepository.getAll();
  }

  public void loadPerson() {
    selectedPerson = personRepository.getAll().stream()
        .filter(person -> person.getId() == selectedId)
        .findFirst()
        .orElse(null);
  }

  public Person getSelectedPerson() {
    return selectedPerson;
  }

  public int getSelectedId() {
    return selectedId;
  }

  public void setSelectedId(int selectedId) {
    this.selectedId = selectedId;
  }

}