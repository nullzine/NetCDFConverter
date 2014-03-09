/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package netcdfconverter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import ucar.ma2.Array;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

/**
 *
 * @author nullzine
 */
public class ManipulatingNetCDF {
    
    private String path;
    private NetcdfFile ncf = null;
    private CDFElement[] elementList;
    
    public ManipulatingNetCDF(String path) throws IOException{
        this.path=path;
        ncf = NetcdfFile.open(path);
        elementList = new CDFElement[ncf.getVariables().size()];
        initialize();
    }
    
    private void initialize() throws IOException{
        List<Variable> list = ncf.getVariables();
        List<Array> readArrays = ncf.readArrays(ncf.getVariables());
        for(int i=0;i<list.size();i++){
            elementList[i] = new CDFElement(list.get(i));
            elementList[i].setArray(readArrays.get(i));
        }
    }
    
    public String getPath(){
        return path;
    }
    
    public int size(){
        return elementList.length;
    }
    
    public BufferedImage getBufferedImage(int index,boolean isColor){
        if(elementList[index].getBufferedImage()!=null){
            return elementList[index].getBufferedImage(isColor);
        }else{
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        }
    }
    
    public String getHeaderData(int index){
        return elementList[index].getHeaderData();
    }
    
    public float[][] getDataSet(int index){
        return elementList[index].getDataSet();
    }
    
    public String getVariableType(int index){
        return elementList[index].getType();
    }
    
    public void savingImage(String path,int index,boolean isColor) throws IOException{
        File tmp = new File(path+".png");
        ImageIO.write(getBufferedImage(index,isColor), "png", tmp);
    }
    
    public void savingFlatBinary(String path,int index){
        
    }
    
    public boolean getHasArray(int index){
        return elementList[index].hasArray();
    }
    
}
