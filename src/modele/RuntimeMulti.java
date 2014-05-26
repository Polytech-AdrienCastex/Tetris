/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.util.Observer;

/**
 *
 * @author Adrien
 */
public class RuntimeMulti
{
    public RuntimeMulti(int nb_players)
    {
        runtimes = new Runtime[nb_players];
        
        for(int i = 0; i < nb_players; i++)
            runtimes[i] = new Runtime();
    }
    
    private final Runtime[] runtimes;
    
    public int getNbPlayers()
    {
        return runtimes.length;
    }
    
    public void addObserver(Observer o)
    {
        for(Runtime r : runtimes)
            r.addObserver(o);
    }
    public void addObserver(int player, Observer o)
    {
        runtimes[player].addObserver(o);
    }
    
    public void run()
    {
        for(Runtime r : runtimes)
            r.run();
    }
}
