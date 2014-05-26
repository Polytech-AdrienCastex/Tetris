/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.general;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author p1002239
 */
public abstract class Piece
{
    public Piece()
    {
        generate();
    }
    
    protected boolean[][] matrix;
    
    private static Point _defaultposition = new Point(0, 0);
    public static Point getDefaultPosition()
    {
        return _defaultposition;
    }
    public static void setDefaultPosition(Point Position)
    {
        _defaultposition = Position;
    }
    
    private Boolean _isFixed = false;
    public Boolean isFixed()
    {
        return _isFixed;
    }
    public void fixe()
    {
        _isFixed = true;
    }
    
    public Point getSize()
    {
        return new Point(matrix.length, matrix[0].length);
    }
    
    private Point _lastposition = null;
    private Point _position = null;
    public Point getPosition()
    {
        return _position;
    }
    protected void setPosition(Point Position)
    {
        _lastposition = _position;
        _position = Position;
    }
    private void cancelPosition()
    {
        _position = _lastposition;
        _lastposition = null;
    }
    
    private Color _color;
    public Color getColor()
    {
        return _color;
    }
    protected void setColor(Color color)
    {
        _color = color;
    }
    
    protected abstract void generate();
    protected void regenerateMatrix(int size_x, int size_y, boolean defaultvalue)
    {
        matrix = new boolean[size_x][size_y];
        
        for(int x = 0; x < size_x; x++)
            for(int y = 0; y < size_y; y++)
                matrix[x][y] = defaultvalue;
    }
    
    public boolean getCase(int x, int y)
    {
        return matrix[x][y];
    }
    
    //<editor-fold defaultstate="collapsed" desc="Move">
    public void moveLeft()
    {
        setPosition(new Point(
                getPosition().x - 1,
                getPosition().y
        ));
    }
    public void moveRight()
    {
        setPosition(new Point(
                getPosition().x + 1,
                getPosition().y
        ));
    }
    public void moveUp()
    {
        setPosition(new Point(
                getPosition().x,
                getPosition().y - 1
        ));
    }
    public void moveDown()
    {
        setPosition(new Point(
                getPosition().x,
                getPosition().y + 1
        ));
    }
    public void cancelLastMove()
    {
        cancelPosition();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Rotate">
    private boolean isLastRotateLeft = false;
    public void rotateLeft90()
    {
        isLastRotateLeft = true;
        
        int max_x = getSize().x;
        int max_y = getSize().y;
        
        boolean[][] temp = new boolean[max_y][max_x];
        
        for(int x = 0; x < max_y; x++)
            for(int y = 0; y < max_x; y++)
                temp[x][y] = matrix[max_x - 1 - y][x];
        
        matrix = temp;
    }
    public void rotateRight90()
    {
        isLastRotateLeft = false;
        
        int max_x = getSize().x;
        int max_y = getSize().y;
        
        boolean[][] temp = new boolean[max_y][max_x];
        
        for(int x = 0; x < max_y; x++)
            for(int y = 0; y < max_x; y++)
                temp[x][y] = matrix[y][max_y - 1 - x];
        
        matrix = temp;
    }
    public void cancelLastRotate()
    {
        if(isLastRotateLeft)
            rotateRight90();
        else
            rotateLeft90();
    }
    //</editor-fold>
}
