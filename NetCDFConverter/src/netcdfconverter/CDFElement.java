/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package netcdfconverter;

import java.awt.image.BufferedImage;
import ucar.nc2.Variable;
import ucar.ma2.Array;

/**
 *
 * @author nullzine
 */
public class CDFElement {
    
    private NCDFArrayStracture ncdfas;
    private NCDF3DArrayStracture ncdf3das;
    private Variable headerData;
    
    public CDFElement(Variable headerData){
        this.headerData=headerData;
        ncdf3das = null;
        ncdfas = null;
    }
    
    public void setArray(Array array){
        if (array.getShape().length == 2) {
            ncdfas = new NCDFArrayStracture(array.getShape()[0], array.getShape()[1]);
        }
        if (array.getShape().length == 3){
            ncdf3das = new NCDF3DArrayStracture(array.getShape()[0], array.getShape()[1], array.getShape()[2]);
        }
        if (ncdfas != null) {
            while (array.hasNext()) {
                float tmp = array.nextFloat();
                ncdfas.add(tmp);
            }
        }else if(ncdf3das != null){
            while (array.hasNext()) {
                float tmp = array.nextFloat();
                ncdf3das.add(tmp);
            }
        }
        
    }
    
    public BufferedImage getBufferedImage(){
        if(ncdfas!=null){
            return ncdfas.getBufferedImage(false);
        }else{
            return null;
        }
    }
    
    public BufferedImage getBufferedImage(boolean isColor){
        if(ncdfas!=null){
            return ncdfas.getBufferedImage(isColor);
        }else{
            return null;
        }
    }
    
    public BufferedImage getBufferedImage(boolean isColor,int index){
        if(ncdf3das!=null){
            return ncdf3das.getBufferedImage(isColor, index);
        }else{
            return null;
        }
    }
    
    public String getHeaderData(){
        return headerData.toString();
    }
    
    public float[][] getDataSet(){
        if(ncdfas!=null){
            return ncdfas.getDataSet();
        }else{
            return null;
        }
    }
    
    public float[][] getDataSet(int timeIndex){
        if(ncdf3das!=null){
            return ncdf3das.getDataSet(timeIndex);
        }else{
            return null;
        }
    }
    
    public NCDFArrayStracture getArrayStracture(){
        return ncdfas;
    }
    
    public NCDF3DArrayStracture get3DArrayStracture(){
        return ncdf3das;
    }
    
    public Variable getVariable(){
        return headerData;
    }
    
    public String getType(){
        return headerData.getNameAndDimensions();
    }
    
    public boolean hasArray(){
        return ncdfas!=null;
    }
    
    public boolean has3DArray(){
        return ncdf3das!=null;
    }
    
    public int get3DArrayLength(){
        if(ncdf3das!=null){
            return ncdf3das.size();
        }else{
            return 0;
        }
    }
    
}
