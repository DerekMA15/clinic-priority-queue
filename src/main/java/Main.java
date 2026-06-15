
// import java.util.Scanner;

import model.estrutura.Lista;
import model.Paciente;

public class Main
{
    public static void main(String args[])
    {
        Paciente a1 = new Paciente("Derek", "00000000", 3, 210);
        System.out.println("Exemplo de lista inicial:");
        Lista pacientes = new Lista("Pacientes");
        pacientes.mostra();   
        pacientes.insereNaFrente(a1);
        pacientes.mostra();  
        

    }
}