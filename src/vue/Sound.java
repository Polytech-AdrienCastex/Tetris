/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.io.File;
import java.nio.file.Paths;
/*
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
*/

/**
 *
 * @author Adrien
 */
public class Sound
{
    public Sound(String url)
    {
        this.url = new File(url).getAbsolutePath();
    }
    
    private final String url;
    public String getFileUrl()
    {
        return url;
    }
    
    
    /*private static JFXPanel initComponent = null;
    private synchronized static void initialize()
    {
        if(initComponent == null)
            initComponent = new JFXPanel();
    }
    
    private MediaPlayer mediaPlayer = null;
    private void initializeMedia()
    {
        if(mediaPlayer == null)
        {
            Media hit = new Media(Paths.get(new File(this.url).getAbsolutePath()).toUri().toString());
            mediaPlayer = new MediaPlayer(hit);
        }
    }
    */
    private Thread currentThread = null;
    public synchronized void play() 
    {/*
        if(mediaPlayer != null)
        {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
            return;
        }
        
        currentThread = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Sound.initialize();
                    initializeMedia();
                    
                    mediaPlayer.seek(Duration.ZERO);
                    mediaPlayer.setVolume(volume);
                    mediaPlayer.setRate(rate);
                    
                    if(_isLoop)
                        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                    else
                        mediaPlayer.setCycleCount(0);
                    
                    mediaPlayer.play();
                    
                    waitForEnd();
                }
                catch(Exception ex)
                { }
            }
        });
        
        currentThread.start();*/
    }
    
    private boolean _isLoop = false;
    public void setLoop()
    {/*
        _isLoop = true;
        if(mediaPlayer != null)
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);*/
    }
    public void removeLoop()
    {/*
        _isLoop = false;
        if(mediaPlayer != null)
            mediaPlayer.setCycleCount(0);*/
    }
    public boolean isLoop()
    {
        return _isLoop;
    }
    
    public void stop()
    {/*
        if(mediaPlayer != null)
            mediaPlayer.stop();*/
    }
    
    public void pause()
    {/*
        if(mediaPlayer != null)
            mediaPlayer.pause();*/
    }
    public void resume()
    {/*
        if(mediaPlayer != null)
            mediaPlayer.play();*/
    }
    
    private double volume = 1f;
    public double getVolume()
    {
        return volume;
    }
    public void setVolume(double volume)
    {/*
        this.volume = volume;
        if(mediaPlayer != null)
            mediaPlayer.setVolume(volume);*/
    }
    
    private double rate = 1f;
    public double getRate()
    {
        return rate;
    }
    public void setRate(double rate)
    {/*
        this.rate = rate;
        if(mediaPlayer != null)
            mediaPlayer.setRate(rate);*/
    }
    
    public void waitForEnd() throws InterruptedException
    {/*
        if(mediaPlayer == null)
            return;
        
        try
        {
            Thread.sleep((long)mediaPlayer.getTotalDuration().subtract(mediaPlayer.getCurrentTime()).toMillis());
        }
        catch(InterruptedException ex)
        {
            mediaPlayer.stop();
        }*/
    }
}
