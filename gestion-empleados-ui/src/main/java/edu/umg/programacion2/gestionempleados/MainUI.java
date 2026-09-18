package edu.umg.programacion2.gestionempleados;

import javax.swing.SwingUtilities;

import edu.umg.programacion2.gestionempleados.ui.VentanaPrincipal;

public class MainUI {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
