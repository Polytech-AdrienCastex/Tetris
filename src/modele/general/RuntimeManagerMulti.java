/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.general;

import java.lang.reflect.ParameterizedType;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien
 */
public class RuntimeManagerMulti<T extends TimedRuntime> implements Runnable
{
    public RuntimeManagerMulti(int nb_players, Class<T> c)
    {
        try
        {
            runtimes = new TimedRuntime[nb_players];
            
            for(int i = 0; i < nb_players; i++)
                runtimes[i] = c.newInstance();
                //runtimes[i] = new TetrisRuntime();
        }
        catch (InstantiationException | IllegalAccessException ex)
        { }
    }
    
    private TimedRuntime[] runtimes;
    
    public int getNbPlayers()
    {
        return runtimes.length;
    }
    
    public void addObservers(Observer... o)
    {
        for(TimedRuntime r : runtimes)
            r.setObservers(o);
    }
    public void addObservers(int player, Observer... o)
    {
        runtimes[player].setObservers(o);
    }
    
    public T getRuntime(int player)
    {
        return (T)runtimes[player];
    }
    public T[] getRuntime()
    {
        return (T[])runtimes;
    }
    
    @Override
    public void run()
    {
        for(TimedRuntime r : runtimes)
            r.run();
    }
}
