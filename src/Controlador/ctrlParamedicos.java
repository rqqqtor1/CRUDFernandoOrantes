
package Controlador;

import Modelo.ClaseConexion;
import Modelo.mdlParamedicos;
import Vista.frmParamedico;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
/**
 *
 * @author Estudiante
 */
public class ctrlParamedicos implements MouseListener, KeyListener {
     private mdlParamedicos modelo;
    private frmParamedico vista;

    public ctrlParamedicos(mdlParamedicos modelo, frmParamedico vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Detectar eventos de los botones y la tabla
        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.tbParamedicos.addMouseListener(this);
        

        // Mostrar datos en la tabla al iniciar
        modelo.Mostrar(vista.tbParamedicos);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // Asignar valores de la vista al modelo
                    modelo.setNombre_Paramedico(vista.txtNombre.getText());
                    modelo.setEdad_Paramedico(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso_Paramedico(Double.parseDouble(vista.txtPeso.getText()));
                    modelo.setCorreo_Paramedico(vista.txtCorreo.getText());

                    // Guardar datos y actualizar la tabla
                    modelo.Guardar();
                    modelo.Mostrar(vista.tbParamedicos);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad y el peso deben ser números", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            if (vista.tbParamedicos.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.tbParamedicos);
                modelo.Mostrar(vista.tbParamedicos);
                modelo.limpiar(vista);
            }
        }

        if (e.getSource() == vista.btnActualizar) {
            if (vista.tbParamedicos.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // Asignar valores de la vista al modelo al actualizar
                    modelo.setNombre_Paramedico(vista.txtNombre.getText());
                    modelo.setEdad_Paramedico(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso_Paramedico(Double.parseDouble(vista.txtPeso.getText()));
                    modelo.setCorreo_Paramedico(vista.txtCorreo.getText());

                    // Actualizar registro
                    modelo.Actualizar(vista.tbParamedicos);
                    modelo.Mostrar(vista.tbParamedicos);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad y el peso deben ser números", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

      

        if (e.getSource() == vista.tbParamedicos) {
            modelo.cargarDatosTabla(vista);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }

    // Métodos no utilizados de MouseListener y KeyListener
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
}
