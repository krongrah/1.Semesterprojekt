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

    void save();
    void drink();
    void talk(String string);
    void search(String string);
    void inspect(String string);
    void arrest(String string);
    void convict(String string);
    Set<String> talkMenu();
    Set<String> arrestMenu();
    Set<String> convictMenu();
    Set<String> searchMenu();
    Set<String> inspectMenu();
    
}
