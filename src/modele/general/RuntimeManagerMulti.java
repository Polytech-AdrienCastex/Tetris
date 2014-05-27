/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.general;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien
 */
public class RuntimeManagerMulti<T extends Runtime> implements RuntimeManager
{
    public RuntimeManagerMulti(int nb_players, Class<T> c)
    {
        try
        {
            runtimes = (T[])Array.newInstance(c, nb_players);
            
            for(int i = 0; i < nb_players; i++)
                runtimes[i] = c.newInstance();
        }
        catch (InstantiationException | IllegalAccessException ex)
        { }
    }
    
    private T[] runtimes;
    
    @Override
    public int getNbPlayers()
    {
        return runtimes.length;
    }
    
    @Override
    public void addObservers(Observer... o)
    {
        for(Runtime r : runtimes)
            r.setObservers(o);
    }
    @Override
    public void addObservers(int player, Observer... o)
    {
        runtimes[player].setObservers(o);
    }
    
    @Override
    public T getRuntime(int player)
    {
        if(player < runtimes.length)
            return (T)runtimes[player];
        else
            return null;
    }
    @Override
    public T[] getRuntime()
    {
        return (T[])runtimes;
    }
    
    @Override
    public void run()
    {
        for(Runtime r : runtimes)
            r.run();
    }
}
