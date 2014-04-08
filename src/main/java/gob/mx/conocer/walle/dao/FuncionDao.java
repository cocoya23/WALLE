/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.mx.conocer.walle.dao;

import gob.mx.conocer.walle.entity.Funcion;
import java.util.List;
public interface FuncionDao {

    Funcion getById(Integer id);
    List<Funcion> getChilds(Funcion funcion);
    void saveFuncion(Funcion funcion);
    void updateFuncion(Funcion funcion);
    void deleteFuncion(Funcion funcion);
    
}
