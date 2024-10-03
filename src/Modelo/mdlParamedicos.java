
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
    private String Nombre_Paramedico;
    private int Edad_Paramedico;
    private double Peso_Paramedico;
    private String Correo_Paramedico;

    // Getters y Setters
    public String getNombre_Paramedico() { return Nombre_Paramedico; }
    public void setNombre_Paramedico(String Nombre_Paramedico) { this.Nombre_Paramedico = Nombre_Paramedico; }

    public int getEdad_Paramedico() { return Edad_Paramedico; }
    public void setEdad_Paramedico(int Edad_Paramedico) { this.Edad_Paramedico = Edad_Paramedico; }

    public double getPeso_Paramedico() { return Peso_Paramedico; }
    public void setPeso_Paramedico(double Peso_Paramedico) { this.Peso_Paramedico = Peso_Paramedico; }

    public String getCorreo_Paramedico() { return Correo_Paramedico; }
    public void setCorreo_Paramedico(String Correo_Paramedico) { this.Correo_Paramedico = Correo_Paramedico; }

    // Métodos para interactuar con la base de datos
    public void Guardar() {
        Connection conexion = ClaseConexion.getConexion();
        try {
            String sql = "INSERT INTO tbParamedico(UUID_Paramedico, Nombre_Paramedico, Edad_Paramedico, Peso_Paramedico, Correo_Paramedico) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, java.util.UUID.randomUUID().toString());
            pstmt.setString(2, getNombre_Paramedico());
            pstmt.setInt(3, getEdad_Paramedico());
            pstmt.setDouble(4, getPeso_Paramedico());
            pstmt.setString(5, getCorreo_Paramedico());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al guardar paramédico: " + ex);
        }
    }

    public void Mostrar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID", "Nombre", "Edad", "Peso", "Correo"});
        try {
            String query = "SELECT * FROM tbParamedico";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("UUID_Paramedico"), rs.getString("Nombre_Paramedico"), rs.getInt("Edad_Paramedico"), rs.getDouble("Peso_Paramedico"), rs.getString("Correo_Paramedico")});
            }
            tabla.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al mostrar paramédicos: " + e);
        }
    }

    public void Eliminar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        String UUID = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            String sql = "DELETE FROM tbParamedico WHERE UUID_Paramedico = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, UUID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar paramédico: " + e);
        }
    }

    public void Actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        String UUID = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            String sql = "UPDATE tbParamedico SET Nombre_Paramedico = ?, Edad_Paramedico = ?, Peso_Paramedico = ?, Correo_Paramedico = ? WHERE UUID_Paramedico = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, getNombre_Paramedico());
            pstmt.setInt(2, getEdad_Paramedico());
            pstmt.setDouble(3, getPeso_Paramedico());
            pstmt.setString(4, getCorreo_Paramedico());
            pstmt.setString(5, UUID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar paramédico: " + e);
        }
    }

    public void Buscar(JTable tabla, JTextField campoBusqueda) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID", "Nombre", "Edad", "Peso", "Correo"});
        try {
            String sql = "SELECT * FROM tbParamedico WHERE Nombre_Paramedico LIKE ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, campoBusqueda.getText() + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("UUID_Paramedico"), rs.getString("Nombre_Paramedico"), rs.getInt("Edad_Paramedico"), rs.getDouble("Peso_Paramedico"), rs.getString("Correo_Paramedico")});
            }
            tabla.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al buscar paramédico: " + e);
        }
    }

    public void limpiar(frmParamedico vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }

    public void cargarDatosTabla(frmParamedico vista) {
        int filaSeleccionada = vista.tbParamedicos.getSelectedRow();
        if (filaSeleccionada != -1) {
            vista.txtNombre.setText(vista.tbParamedicos.getValueAt(filaSeleccionada, 1).toString());
            vista.txtEdad.setText(vista.tbParamedicos.getValueAt(filaSeleccionada, 2).toString());
            vista.txtPeso.setText(vista.tbParamedicos.getValueAt(filaSeleccionada, 3).toString());
            vista.txtCorreo.setText(vista.tbParamedicos.getValueAt(filaSeleccionada, 4).toString());
        }
    }
}
