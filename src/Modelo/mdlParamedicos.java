
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import Vista.frmParamedico;
/**
 *
 * @author Estudiante
 */
public class mdlParamedicos {
    private int UUID_Paramedico ;
    private String Nombre_Paramedico;
    private int Edad_Paramedico;
    private double Peso_Paramedico;
    private String Correo_Paramedico;
    
    public int getUUID_Paramedico(){
    return Edad_Paramedico;
}
    public void setUUID_Paramedico(){
        this.UUID_Paramedico = UUID_Paramedico;
    }
    
    public String getNombre_Paramedico() {
        return Nombre_Paramedico;
    }
    
    public void setNombreParamedico(){
        this.Edad_Paramedico = Edad_Paramedico;
    }
    
    public int getEdad_Paramedico(){
    return Edad_Paramedico;
}
    public void setEdad_Paramedico(){
        this.Edad_Paramedico = Edad_Paramedico;
    }
    
    public double getPeso_Paradamedico(){
        return Peso_Paramedico;
    }
    
    public void setPeso_Paradamedico(){
        this.Peso_Paramedico = Peso_Paramedico;
    }
  public String getCorreo_Paramedico() {
        return Correo_Paramedico;
    }
    
    public void setCorreo_Paramedico(){
        this.Correo_Paramedico = Correo_Paramedico;
    }  
    
}
