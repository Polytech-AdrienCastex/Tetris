/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.general;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Adrien
 */
public abstract class Runtime extends Observable
{
    public abstract void setObservers(Observer[] obs);
    
    public abstract void run();
    
}
