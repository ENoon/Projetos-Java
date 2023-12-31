package br.com.firstproject.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int codigo;
  private String nome;
  private String idade;

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getIdade() {
    return idade;
  }

  public void setIdade(String idade) {
    this.idade = idade;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
