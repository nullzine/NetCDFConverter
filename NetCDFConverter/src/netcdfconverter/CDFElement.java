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
    
    private NCDEArrayStracture ncdeas;
    private Variable headerData;
    
    public CDFElement(Variable headerData){
        this.headerData=headerData;
        ncdeas = null;
    }
    
    public void setArray(Array array){
        if (array.getShape().length == 2) {
            ncdeas = new NCDEArrayStracture(array.getShape()[0], array.getShape()[1]);
        }
        while (array.hasNext()) {
            float tmp = array.nextFloat();
            if (ncdeas != null) {
                ncdeas.add(tmp);
            }
        }
        
    }
    
    public BufferedImage getBufferedImage(){
        if(ncdeas!=null){
            return ncdeas.getBufferedImage(false);
        }else{
            return null;
        }
    }
    
    public BufferedImage getBufferedImage(boolean isColor){
        if(ncdeas!=null){
            return ncdeas.getBufferedImage(isColor);
        }else{
            return null;
        }
    }
    
    public String getHeaderData(){
        return headerData.toString();
    }
    
    public float[][] getDataSet(){
        if(ncdeas!=null){
            return ncdeas.getDataSet();
        }else{
            return null;
        }
    }
    
    public NCDEArrayStracture getArrayStracture(){
        return ncdeas;
    }
    
    public Variable getVariable(){
        return headerData;
    }
    
    public String getType(){
        return headerData.getNameAndDimensions();
    }
    
    public boolean hasArray(){
        return ncdeas!=null;
    }
    
}
