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
public class FrontEndFacade implements IFrontEnd {

    IBackEnd backEnd;

    /**
     * Injects the backEnd into this.
     *
     * @param backEnd The instance of backEnd created in the Driver.
     */
    @Override
    public void injectBackEnd(IBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    /**
     * creates an instance of the Initializer class, injects backEnd into it and
     * finally calls the begin method on it.
     *
     * @param args the command line argument
     */
    @Override
    public void begin(String[] args) {
        Initializer initializer = new Initializer();
        initializer.injectBackEnd(backEnd);
        initializer.begin(args);
    }

}
