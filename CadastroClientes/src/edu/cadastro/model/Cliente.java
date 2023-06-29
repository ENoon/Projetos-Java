package edu.cadastro.model;
public class Cliente {
    
    private int id;
    private String Nome;
    private int idade;
    private String cidade;
    private String bairro;
    private String endereco;
    private String complemento;

    public Cliente(String Nome, int idade, String cidade, String bairro, String endereco, String complemento) {
        this.Nome = Nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.endereco = endereco;
        this.complemento = complemento;
    }

    public Cliente(String Nome, int idade, String cidade) {
        this.Nome = Nome;
        this.idade = idade;
        this.cidade = cidade;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return Nome;
    }
    
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    
    
}
