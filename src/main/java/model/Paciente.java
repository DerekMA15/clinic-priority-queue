package model;

public class Paciente {
    public String nome; 
    private String cpf; 
    public int prioridade; // (na escala de 1 a 5)
    private int idade; // seria algo pego diretamente no cpf, mas para facilitar o nosso caso né
    private boolean idoso = false; // acho que ficou meio redundante por conta da idade, mas fica melhor trabalhar com o boolean
    
    public Paciente(String nome, String cpf, int prioridade, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.prioridade = prioridade;
        this.idade = idade;
        if (idade >= 60) {
            setIdoso(true);
    }

    }
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public int getPrioridade() {
        return prioridade;
    }
    public int getIdade() {
        return idade;
    }
    
    public boolean isIdoso() {
        return idoso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setIdoso(boolean idoso) {
        this.idoso = idoso;
    }
}
