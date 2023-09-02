package br.com.firstproject.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.firstproject.api.model.Client;
import br.com.firstproject.api.model.Person;
import br.com.firstproject.api.repositories.Repositories;
import br.com.firstproject.api.service.Employ;
import jakarta.validation.Valid;

@RestController
public class Controller {

  @Autowired
  private Repositories action;

  @Autowired
  private Employ employ;

  @PostMapping("/api")
  public ResponseEntity<?> register(@RequestBody Person obj) {
    return employ.register(obj);
  }

  @GetMapping("/api")
  public ResponseEntity<?> select() {
    return employ.select();
  }

  @GetMapping("/api/{codigo}")
  public ResponseEntity<?> selectByCode(@PathVariable int codigo) {
    return employ.selectByCode(codigo);
  }

  @PutMapping("/api")
  public ResponseEntity<?> edit(@RequestBody Person obj) {
    return employ.edit(obj);
  }

  @DeleteMapping("/api/{codigo}")
  public ResponseEntity<?> remove(@PathVariable int codigo) {
    return employ.remove(codigo);
  }

  @GetMapping("/api/contador")
  public long contador(){
    return action.count();
  }

  @GetMapping("/api/ordenarNomes")
  public List<Person> ordenarNomes(){
    return action.findByOrderByNome();
  }

  @GetMapping("/api/ordenarNomes2")
  public List<Person> ordenarNomes2(){
    return action.findByNomeOrderByIdadeDesc("Maria");
  }

  @GetMapping("/api/nomeContem")
  public List<Person> nomeContem(){
    return action.findByNomeContaining("ri");
  }

  @GetMapping("/api/iniciaCom")
  public List<Person> iniciaCom(){
    return action.findByNomeStartsWith("a");
  }

  @GetMapping("/api/terminaCom")
  public List<Person> terminaCom(){
    return action.findByNomeEndsWith("s");
  }

  @GetMapping("/api/somaIdades")
  public int somaIdades(){
    return action.somaIdades();
  }

  @GetMapping("/api/idadeMaiorIgual")
  public List<Person> idadeMaiorIgual(){
    return action.equalOlderAge(20);
  }

  @GetMapping("")
  public String message() {
    return "Vai se fuder!";
  }

  @GetMapping("/welcome")
  public String welcome() {
    return "Seja bem vindo(a)!";
  }

  @GetMapping("/welcome/{nome}")
  public String welcome(@PathVariable String nome) {
    return "Seja bem vindo(a)! " + nome;
  }

  @PostMapping("/pessoa")
  public Person person(@RequestBody Person p) {
    return p;
  }

  @GetMapping("/status")
  public ResponseEntity<?> status(){
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/cliente")
  public void client(@Valid @RequestBody Client obj){

  }
}
