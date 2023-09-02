package br.com.firstproject.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.firstproject.api.model.Message;
import br.com.firstproject.api.model.Person;
import br.com.firstproject.api.repositories.Repositories;

@Service
public class Employ {
  
  @Autowired
  private Message message;

  @Autowired
  private Repositories action;

  //Method for register persons
  public ResponseEntity<?> register(Person obj){

      if(obj.getNome().equals("")){
        message.setMessage("O nome precisa ser preenchido");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
      } else if(Integer.parseInt(obj.getIdade()) < 0){
        message.setMessage("Informe uma idade válida");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
      } else {
        return new ResponseEntity<>(action.save(obj), HttpStatus.CREATED);
      }
  }

  //Method for select persons
  public ResponseEntity<?> select(){

      return new ResponseEntity<>(action.findAll(), HttpStatus.OK);

  }

  //Method for select persons by code
  public ResponseEntity<?> selectByCode(int codigo){

    if(action.countByCodigo(codigo) == 0){
      message.setMessage("Não foi encontrada nenhuma pessoa");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }else {
      return new ResponseEntity<>(action.findByCodigo(codigo), HttpStatus.OK);
    }

  }

  //Method for edit persons
  public ResponseEntity<?> edit(Person obj){

    if(action.countByCodigo(obj.getCodigo()) == 0){
      message.setMessage("O código informado não existe");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }else if(obj.getNome().equals("")){
      message.setMessage("É necessario informar um nome");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }else if(Integer.parseInt(obj.getIdade()) < 0){
        message.setMessage("Informe uma idade válida");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }else{
      return new ResponseEntity<>(action.save(obj), HttpStatus.OK);
    }

  }

  //Method for remove persons
  public ResponseEntity<?> remove(int codigo) {

    if(action.countByCodigo(codigo) == 0){
      message.setMessage("O código informado não existe");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }else{
      Person obj = action.findByCodigo(codigo); 
      action.delete(obj);
      message.setMessage("Pessoa removida com sucesso");
      return new ResponseEntity<>(message, HttpStatus.OK);
    }

  }
}
