/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Adrien
 */
public abstract class RRuntime extends Observable implements Runnable
{
    public RRuntime()
    {
        
    }
    
    private int start_time = 1000; // ms
    protected void setStartTime(int value)
    {
        start_time = value;
    }
    protected int getStartTime()
    {
        return start_time;
    }
    
    private int decrement_time = 100; // ms
    protected void setDecrementTime(int value)
    {
        decrement_time = value;
    }
    protected int getDecrementTime()
    {
        return decrement_time;
    }
    
    
    private boolean finished;
    protected void terminate()
    {
        finished = true;
        terminedGame();
    }
    public boolean isTerminated()
    {
        return finished;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Abstract methods">
    public abstract void setObservers(Observer... obs);
    
    protected abstract void initialize();
    protected abstract boolean timeIncremented(); // Returns true if acceleration is required
    protected abstract void terminedGame();
    protected abstract void uninitialize();
    protected abstract void error();
    //</editor-fold>
    
    
    @Override
    public void run()
    {
        try
        {
            finished = false;
            int time = getStartTime();
            
            initialize();
            
            while(!isTerminated())
            {
                if(timeIncremented())
                    time -= getDecrementTime();
                
                Thread.sleep(time);
                ManagePause();
            }
            
            uninitialize();
        }
        catch (InterruptedException ex)
        {
            error();
        }
    }
    
    public boolean isBlocked()
    {
        return finished || pause;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Pause / Resume">
    private boolean pause = false;
    private void ManagePause()
    {
        if(pause)
        {
            try
            {
                synchronized(this)
                {
                    this.wait();
                }
            }
            catch (InterruptedException ex)
            { }
            finally
            {
                setPause(false);
            }
        }
    }
    public void Pause()
    {
        setPause(true);
    }
    synchronized private void setPause(boolean value)
    {
        pause = value;
        
        this.setChanged();
        this.notifyObservers(new PauseResumeEventArg(this, pause));
    }
    synchronized public void Resume()
    {
        this.notifyAll();
    }
    public void PauseResume()
    {
        if(pause)
            Resume();
        else
            Pause();
    }
    //</editor-fold>
    
    
    
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Events">
    public class TerminatedEventArg extends Event
    {
        public TerminatedEventArg(Object sender, int score)
        {
            super(sender);
            value = score;
        }
        
        private final int value;
        public int getFinalScore()
        {
            return value;
        }
    }
    public class PauseResumeEventArg extends Event
    {
        public PauseResumeEventArg(Object sender, boolean pause)
        {
            super(sender);
            value = pause;
        }
        
        private final boolean value;
        public boolean getPause()
        {
            return value;
        }
    }
    //</editor-fold>
}
