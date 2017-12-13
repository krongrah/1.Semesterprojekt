/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;
/**
 *
 * @author Krongrah
 */
public interface IFrontEnd {
    void injectBackEnd(IBackEnd backEnd);
    public void begin(String[] args);
    
}
