/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import Acquaintance.IBackEnd;
import Acquaintance.IFrontEnd;

/**
 *
 * @author Krongrah
 */
public class FrontEndFacade implements IFrontEnd{
    IBackEnd backEnd;
    
    @Override
    public void injectBackEnd(IBackEnd backEnd) {
        this.backEnd=backEnd;
    }

    @Override
    public void begin(String[] args) {
        Initializer initializer=new Initializer();
        initializer.injectBackEnd(backEnd);
        initializer.begin(args);
    }

    
}
