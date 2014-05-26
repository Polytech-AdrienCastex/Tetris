/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

/**
 *
 * @author Adrien
 */
public class Event
{
    public Event(Object sender)
    {
        this.sender = sender;
    }
    
    private final Object sender;
    public Object getSender()
    {
        return sender;
    }
}
