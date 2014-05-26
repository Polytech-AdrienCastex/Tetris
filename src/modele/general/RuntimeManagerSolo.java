/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.general;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1002239
 */
public class RuntimeManagerSolo<T extends TimedRuntime> extends RuntimeManagerMulti<T>
{
    public RuntimeManagerSolo(Class<T> c)
    {
        super(1, c);
    }
}
