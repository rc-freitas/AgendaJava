package testes;


import agenda.Agenda;
import agenda.Evento;
import db.SQLiteDataBase;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class testeDB {
    //Criando a agenda
    public static void main(String[] args){
        String nome = "Agenda de teste";
        String descricao = "Primeiro teste da classe de agenda.";

        Agenda agenda1 = new Agenda(nome, descricao);
        
        String path_db = "./agendas.db";
        SQLiteDataBase DB = new SQLiteDataBase();
        //DB.insertAgenda(path_db, agenda1);
        DB.printAllAgendas(path_db);
        
        nome = "Evento teste";
        descricao = "Teste de evento";
        boolean dia_inteiro = false;
        String data = "2018-11-05";
        String hora = "18:00";
        String hora_final = "19:00";
        
        Evento evento_teste1 =  new Evento(1, nome, descricao, dia_inteiro, data, hora, hora_final);
        //DB.insertEvento(path_db, evento_teste1, 1);
        
        
        //ArrayList<Evento> eventos = new ArrayList();
        //eventos = DB.allEventos(path_db, 1);
        //System.out.println();
        //System.out.println(java.util.Arrays.toString(eventos.toArray()));
        
    }
    
}
