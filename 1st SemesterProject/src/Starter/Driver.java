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
import FrontEnd.FrontEndFacade;
import FrontEnd.Initializer;

/**
 *
 * @author Krongrah
 */
public class Driver {
 
    public static void main(String[] args) {
        IFoundation foundation = new FoundationFacade();
	IBackEnd backEnd = new BackEndFacade();
	IFrontEnd frontEnd = new FrontEndFacade();
        
        backEnd.injectFoundation(foundation);
	frontEnd.injectBackEnd(backEnd);
        
        frontEnd.begin(args);
    }
    
    
}
