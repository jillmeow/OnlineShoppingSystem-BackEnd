/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author mirji507
 */
public class DAOException extends RuntimeException{
    public DAOException(String reason){
        super(reason);
    }
    public DAOException(String reason, Exception ex){
        super(reason, ex);
    }
}
