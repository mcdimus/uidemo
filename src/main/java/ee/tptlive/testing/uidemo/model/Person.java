package ee.tptlive.testing.uidemo.model;

import java.util.Objects;

public class Person {

  private int id;
  private String name;
  private int age;
  private boolean sex;

  public Person(int id, String name, int age, boolean sex) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return id == person.id &&
        age == person.age &&
        sex == person.sex &&
        Objects.equals(name, person.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, sex);
  }

  @Override
  public String toString() {
    return "Person{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", sex=" + sex +
        '}';
  }

}