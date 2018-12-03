/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 *
 * @author root
 */
public class SortEventos implements Comparator<Evento>{
    
    @Override
    public int compare(Evento evento1, Evento evento2){
        // COMPARAR A SOBREPOSIÇÃO DOS EVENTOS
        LocalDateTime data_hora1 = LocalDateTime.of(evento1.getData(), evento1.getHoraInicial());
        LocalDateTime data_hora2 = LocalDateTime.of(evento2.getData(), evento2.getHoraInicial());
        
        return data_hora1.compareTo(data_hora2);
    }
}
