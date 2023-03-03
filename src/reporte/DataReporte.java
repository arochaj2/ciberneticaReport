/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Dell
 */
public class DataReporte {
    
int id;
//String fieldNumPart;
//String fieldCodigoPart;
   
private List subreporte = new ArrayList();

public DataReporte(
int id
){

    this.id = id;

}



public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }






     // Agregamos los Materiales al Subreporte

 public List getSubreport()   
    {       
        return subreporte;   
    }    

    public void setSubreport(List subreporte)   
    {       
        this.subreporte = subreporte;   
    }    
    
    
       public void addDataSubrep(DataSubReporte subreporte)   
    {       
        this.subreporte.add(subreporte);   
    }    
  
     public JRDataSource getsubreporteDS()   
    {       
        return new JRBeanCollectionDataSource(subreporte);   
    } 
    


    
}
