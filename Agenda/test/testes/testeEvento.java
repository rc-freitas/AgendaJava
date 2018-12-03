package testes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aluno
 */

import agenda.Evento;
public class testeEvento {
    
    public static void main(String[] args){
        String nome = "Evento teste";
        String descricao = "Teste de evento";
        boolean dia_inteiro = false;
        String data = "2018-11-05";
        String hora = "18:00";
        String hora_final = "19:00";
        
        Evento evento_teste1 =  new Evento(nome, descricao, dia_inteiro, data, hora, hora_final);
        
        System.out.println(evento_teste1.getId());
    }
    
}
