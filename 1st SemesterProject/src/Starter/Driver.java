
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Starter;

import Acquaintance.IBackEnd;
import Acquaintance.IFoundation;
import Acquaintance.IFrontEnd;
import BackEnd.BackEndFacade;
import Foundation.FoundationFacade;
import FrontEnd.Initializer;

/**
 *
 * @author Krongrah
 */
public class Driver {

    /**
     * The main method, responsible for starting the program. It creates the
     * facades, and injects them where they need to go. Finally, it calls the
     * begin method on the frontEnd.
     *
     * @param args
     */

    public static void main(String[] args) {
        IFoundation foundation = new FoundationFacade();
        IBackEnd backEnd = new BackEndFacade();
        IFrontEnd frontEnd = new Initializer();

        backEnd.injectFoundation(foundation);
        frontEnd.injectBackEnd(backEnd);

        frontEnd.begin(args);
    }

}
