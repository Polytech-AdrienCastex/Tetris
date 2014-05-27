/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.general;

/**
 *
 * @author Adrien
 */
public abstract class TimedRuntime extends Runtime
{
    public TimedRuntime()
    { }
    
    //<editor-fold defaultstate="collapsed" desc="Time">
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
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Termination">
    private boolean finished;
    protected void terminate()
    {
        terminate(0);
    }
    protected void terminate(int score)
    {
        finished = true;
        terminedGame();
        
        this.setChanged();
        this.notifyObservers(new TerminatedEventArg(this, score));
    }
    public boolean isTerminated()
    {
        return finished;
    }
    public boolean isBlocked()
    {
        return finished || pause;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Abstract methods">
    protected abstract void initialize();
    protected abstract boolean timeIncremented(); // Returns true if acceleration is required and next step
    protected abstract boolean checkTerminated();
    protected abstract void nextStep();
    protected abstract void terminedGame(); 
    protected abstract void uninitialize();
    protected abstract void error();
    //</editor-fold>
    
    
    public void run()
    {
        new Thread(new Runnable()
        {
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
                        {
                            time -= getDecrementTime();
                            nextStep();
                        }

                        if(checkTerminated())
                            terminate();

                        Thread.sleep(time);
                        ManagePause();
                    }

                    uninitialize();
                }
                catch (InterruptedException ex)
                {
                    uninitialize();
                }
                catch (Exception ex)
                {
                    error();
                }
            }
        }).start();
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
