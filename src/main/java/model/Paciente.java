package model;


public class Paciente {
    public String nome; 
    private String cpf; 
    public int prioridade; // (na escala de 1 a 5)
    private int idade; // seria algo pego diretamente no cpf, mas para facilitar o nosso caso né

    // booleans
    private boolean idoso;
    private boolean pcd;
    private boolean gestante;

    public Paciente(String nome, String cpf, int prioridade, int idade, boolean pcd, boolean gestante) {
        this.nome = nome;
        // validar cpf 
        
        if(util.ValidaCPF.isCPF(cpf) == true){
            this.cpf = cpf;
        }else {
            System.out.println("CPF invalido");
            this.cpf = "erro";
        }
        this.prioridade = prioridade;
        this.idade = idade;
        this.pcd = pcd;
        this.gestante = gestante;

        this.idoso = (idade>=60); // definida automaticamente com base na idade passada
    }

    public boolean isPreferencial() {
        return this.idoso || this.pcd || this.gestante;
    }

    // getters
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
    public boolean isIdoso() { return idoso; }
    public boolean isPcd() { return pcd; }
    public boolean isGestante() { return gestante; }

    // setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    public void setPcd(boolean pcd) { this.pcd = pcd; }
    public void setGestante(boolean gestante) { this.gestante = gestante; }

    public void setIdade(int idade) {
        this.idade = idade;
        this.idoso = (idade >= 60);
    }
}
