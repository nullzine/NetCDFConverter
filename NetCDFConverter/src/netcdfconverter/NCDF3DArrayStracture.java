/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package netcdfconverter;

import java.awt.image.BufferedImage;

/**
 *
 * @author nullzine
 */
public class NCDF3DArrayStracture {
    
    private int time;
    private int row;
    private int column;
    private NCDFArrayStracture[] dataSet;
    private int t;
    
    public NCDF3DArrayStracture(int time,int row,int column){
        this.time = time;
        this.row = row;
        this.column = column;
        dataSet = new NCDFArrayStracture[time];
        t=0;
    }
    
    public void add(float num){
        if(dataSet[t]==null){
            dataSet[t] = new NCDFArrayStracture(row, column);
        }
        dataSet[t].add(num);
        if(dataSet[t].isArrayFull()){
            t++;
        }
    }
    
    public float[][] getDataSet(int timeIndex){
        return dataSet[timeIndex].getDataSet();
    }
    
    public BufferedImage getBufferedImage(boolean isColor,int timeIndex) {
        return dataSet[timeIndex].getBufferedImage(isColor);
    }
    
    private int rgb(int r,int g,int b){
        return 0xff000000 | r <<16 | g <<8 | b;
    }
    
    public int size(){
        return time;
    }
    
}
