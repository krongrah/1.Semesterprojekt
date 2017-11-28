/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import java.util.Set;

/**
 *
 * @author Krongrah
 */
public interface IBackEnd {
    void injectFoundation(IFoundation foundation);
    void play();
    void talk(String string);
    void search();
    void inspect();
    void save();
    void drink();
    void arrest();
    void convict();
    Set<String> talkMenu();
    
    
}
