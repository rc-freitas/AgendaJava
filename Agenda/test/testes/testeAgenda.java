package testes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import agenda.Evento;
import agenda.Agenda;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Aluno
 */
public class testeAgenda {
    
    public static void main(String[] args){
        //Criando a agenda
        String nome = "Agenda de teste";
        String descricao = "Primeiro teste da classe de agenda.";
        
        Agenda agenda1 = new Agenda(nome, descricao);
        
        
        try {

            //Gera o arquivo para armazenar o objeto
            FileOutputStream arquivoGrav = new FileOutputStream("./saida.txt");

            //Classe responsavel por inserir os objetos
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

            //Grava o objeto cliente no arquivo
            objGravar.writeObject(agenda1);

            objGravar.flush();

            objGravar.close();

            arquivoGrav.flush();

            arquivoGrav.close();

            System.out.println("Objeto gravado com sucesso!");

        } catch (Exception e) {

            e.printStackTrace();

        }

        System.out.println("Recuperando objeto: ");

        try {

            //Carrega o arquivo
            FileInputStream arquivoLeitura = new FileInputStream("./saida.txt");
 
            //Classe responsavel por recuperar os objetos do arquivo
 
      
             ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);

            System.out.println(objLeitura.readObject());

            objLeitura.close();

            arquivoLeitura.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
        
    } 
}
