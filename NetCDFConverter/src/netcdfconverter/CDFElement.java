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
    private Variable headerData;
    
    public CDFElement(Variable headerData){
        this.headerData=headerData;
        ncdfas = null;
    }
    
    public void setArray(Array array){
        if (array.getShape().length == 2) {
            ncdfas = new NCDFArrayStracture(array.getShape()[0], array.getShape()[1]);
        }
        while (array.hasNext()) {
            float tmp = array.nextFloat();
            if (ncdfas != null) {
                ncdfas.add(tmp);
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
    
    public NCDFArrayStracture getArrayStracture(){
        return ncdfas;
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
    
}
