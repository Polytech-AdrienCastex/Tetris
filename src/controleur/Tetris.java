/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import modele.Grid;
import vue.FenetrePrincipale;
import modele.Runtime;

/**
 *
 * @author p1002239
 */
public class Tetris
{

    public static void main(String[] args)
    {
        Runtime runtime = new Runtime();
        FenetrePrincipale fenetre = new FenetrePrincipale(runtime);
        
        fenetre.setVisible(true);
        runtime.run();
    }
    
}
