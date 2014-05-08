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
public class NCDFArrayStracture {
    
    private float[][] dataSet;
    private int r,c;
    private int row;
    private int column;
    
    public NCDFArrayStracture(int row,int column){
        this.row=row;
        this.column=column;
        dataSet = new float[row][column];
        r=0;
        c=0;
    }
    
    public void add(float num){
        dataSet[r][c] = num;
        c++;
        if(c==column){
            r++;
            c=0;
        }
    }
    
    public void print(){
        System.out.println(dataSet.length+":"+dataSet[0].length);
    }
    
    public float[][] getDataSet(){
        return dataSet;
    }
    
    public BufferedImage getBufferedImage(boolean isColor) {
        BufferedImage bi;
        if (isColor) {
            bi = new BufferedImage(row, column, BufferedImage.TYPE_INT_RGB);
            //カラー版の表示を書く
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    int tmp = 0;
                    if (0 < dataSet[i][j]) {
                        tmp = (int) (dataSet[i][j] * 255);
                    }
                    //System.out.println(dataSet[i][j]);
                    bi.setRGB(i, j, tmp*tmp*tmp);
                    //bi.setRGB(i, j, rgb(tmp, Math.abs(255/2-tmp), 255-tmp));
                }
            }
        } else {
            bi = new BufferedImage(row, column, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    int tmp = 0;
                    if (0 < dataSet[i][j]) {
                        tmp = (int) (dataSet[i][j] * 255);
                    }
                    //System.out.println(dataSet[i][j]);
                    bi.setRGB(i, j, rgb(tmp, tmp, tmp));
                }
            }
        }
        return bi;
    }
    
    private int rgb(int r,int g,int b){
        return 0xff000000 | r <<16 | g <<8 | b;
    }
    
}
