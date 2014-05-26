/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.general;

import java.util.Observer;

/**
 *
 * @author Adrien
 */
public interface RuntimeManager<T extends Runtime>
{
    int getNbPlayers();
    
    void addObservers(Observer... o);
    void addObservers(int player, Observer... o);
    
    T getRuntime(int player);
    T[] getRuntime();
    
    void run();
}
