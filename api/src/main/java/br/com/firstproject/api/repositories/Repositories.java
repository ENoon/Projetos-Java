package br.com.firstproject.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.firstproject.api.model.Person;

public interface Repositories extends CrudRepository<Person, Integer> {

  List<Person> findAll();

  Person findByCodigo(int codigo);

  List<Person> findByOrderByNome();

  List<Person> findByNomeOrderByIdadeDesc(String nome);

  List<Person> findByNomeContaining(String termo);

  List<Person> findByNomeStartsWith(String termo);

  List<Person> findByNomeEndsWith(String termo);

  @Query(value = "SELECT SUM(idade) FROM persons", nativeQuery = true)
  int somaIdades();

  @Query(value = "SELECT * FROM persons WHERE idade >= :idade", nativeQuery = true)
  List<Person> equalOlderAge(int idade);

  int countByCodigo(int codigo);

}

