package edu.umg.programacion2.gestionempleados.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import edu.umg.programacion2.gestionempleados.dao.EmpleadoDAO;
import edu.umg.programacion2.gestionempleados.modelo.Empleado;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNombre;
    private JTextField txtDepartamento;
    private JTextField txtSalario;
    private JTextField txtFechaContratacion;
    private JTextField txtAniosExperiencia;

    private JCheckBox chkActivo;

    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;

    private EmpleadoDAO empleadoDAO;
    private Integer idSeleccionado;

    public VentanaPrincipal() {

        setTitle("Gestión de Empleados");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));

        JPanel panelPrincipal = new JPanel(new BorderLayout(0, 15));
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        panelPrincipal.add(crearEncabezado(), BorderLayout.NORTH);
        panelPrincipal.add(crearContenido(), BorderLayout.CENTER);

        setContentPane(panelPrincipal);

        empleadoDAO = new EmpleadoDAO();

        configurarEventos();
        cargarEmpleados();
    }

    private JPanel crearEncabezado() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 247, 250));

        JLabel lblTitulo = new JLabel("GESTIÓN DE EMPLEADOS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(30, 64, 175));
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel lblSubtitulo =
                new JLabel("Sistema de administración de personal");

        lblSubtitulo.setFont(
                new Font("SansSerif", Font.PLAIN, 14)
        );

        lblSubtitulo.setForeground(new Color(90, 90, 90));

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblSubtitulo, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearContenido() {

        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setOpaque(false);

        panel.add(crearFormulario(), BorderLayout.NORTH);
        panel.add(crearPanelTabla(), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearFormulario() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                                " Datos del empleado "
                        ),
                        BorderFactory.createEmptyBorder(
                                10, 15, 15, 15
                        )
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(7, 8, 7, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        panel.add(new JLabel("Nombre completo:"), gbc);

        txtNombre = new JTextField(25);

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(txtNombre, gbc);

        // Fecha
        gbc.gridx = 2;
        gbc.weightx = 0;

        panel.add(
                new JLabel("Fecha de contratación:"),
                gbc
        );

        txtFechaContratacion = new JTextField(15);
        txtFechaContratacion.setToolTipText(
                "Ingrese la fecha en formato AAAA-MM-DD"
        );

        gbc.gridx = 3;
        gbc.weightx = 1;

        panel.add(txtFechaContratacion, gbc);

        // Departamento
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        panel.add(new JLabel("Departamento:"), gbc);

        txtDepartamento = new JTextField(25);

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(txtDepartamento, gbc);

        // Estado
        gbc.gridx = 2;
        gbc.weightx = 0;

        panel.add(new JLabel("Estado:"), gbc);

        chkActivo = new JCheckBox("Activo");
        chkActivo.setSelected(true);
        chkActivo.setBackground(Color.WHITE);

        gbc.gridx = 3;
        gbc.weightx = 1;

        panel.add(chkActivo, gbc);

        // Salario
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;

        panel.add(new JLabel("Salario mensual (Q):"), gbc);

        txtSalario = new JTextField(25);

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(txtSalario, gbc);
        
     // Años de experiencia
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 0;

        panel.add(new JLabel("Años de experiencia:"), gbc);

        txtAniosExperiencia = new JTextField(15);

        gbc.gridx = 3;
        gbc.weightx = 1;

        panel.add(txtAniosExperiencia, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.weightx = 1;

        panel.add(crearPanelBotones(), gbc);

        return panel;
    }

    private JPanel crearPanelBotones() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        btnGuardar.setFocusPainted(false);
        btnActualizar.setFocusPainted(false);
        btnEliminar.setFocusPainted(false);
        btnLimpiar.setFocusPainted(false);

        panel.add(btnGuardar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);

        return panel;
    }

    private JPanel crearPanelTabla() {

        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                                " Empleados registrados "
                        ),
                        BorderFactory.createEmptyBorder(
                                10, 15, 15, 15
                        )
                )
        );

        String[] columnas = {
                "ID",
                "Nombre completo",
                "Departamento",
                "Salario",
                "Fecha contratación",
                "Estado",
                "Años de experiencia"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEmpleados = new JTable(modeloTabla);

        tablaEmpleados.setRowHeight(26);

        tablaEmpleados.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION
        );

        tablaEmpleados.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 12)
        );

        JScrollPane scrollPane =
                new JScrollPane(tablaEmpleados);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void configurarEventos() {

        btnGuardar.addActionListener(
                e -> guardarEmpleado()
        );

        btnActualizar.addActionListener(
                e -> actualizarEmpleado()
        );

        btnEliminar.addActionListener(
                e -> eliminarEmpleado()
        );

        btnLimpiar.addActionListener(
                e -> limpiarFormulario()
        );

        tablaEmpleados.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {
                        cargarEmpleadoSeleccionado();
                    }
                });
    }

    private void cargarEmpleados() {

        try {

            List<Empleado> empleados =
                    empleadoDAO.listarTodos();

            modeloTabla.setRowCount(0);

            for (Empleado empleado : empleados) {

                modeloTabla.addRow(new Object[] {
                        empleado.getId(),
                        empleado.getNombreCompleto(),
                        empleado.getDepartamento(),
                        empleado.getSalario(),
                        empleado.getFechaContratacion(),
                        empleado.isActivo()
                                ? "Activo"
                                : "Inactivo" ,
                        empleado.getAniosExperiencia()         	
                });
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible cargar los empleados "
                    + "desde la base de datos.",
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void guardarEmpleado() {

        Empleado empleado = obtenerEmpleadoFormulario();

        if (empleado == null) {
            return;
        }

        try {

            empleadoDAO.crear(empleado);

            JOptionPane.showMessageDialog(
                    this,
                    "Empleado registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarFormulario();
            cargarEmpleados();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible registrar el empleado.",
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void actualizarEmpleado() {

        if (idSeleccionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un empleado de la tabla "
                    + "para actualizar.",
                    "Empleado no seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Empleado empleado = obtenerEmpleadoFormulario();

        if (empleado == null) {
            return;
        }

        empleado.setId(idSeleccionado);

        try {

            boolean actualizado =
                    empleadoDAO.actualizar(empleado);

            if (actualizado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Empleado actualizado correctamente.",
                        "Actualización exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );

                limpiarFormulario();
                cargarEmpleados();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró el empleado "
                        + "que desea actualizar.",
                        "Empleado no encontrado",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible actualizar el empleado.",
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void eliminarEmpleado() {

        if (idSeleccionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un empleado de la tabla "
                    + "para eliminar.",
                    "Empleado no seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar este empleado?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            boolean eliminado =
                    empleadoDAO.eliminar(idSeleccionado);

            if (eliminado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Empleado eliminado correctamente.",
                        "Eliminación exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );

                limpiarFormulario();
                cargarEmpleados();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró el empleado "
                        + "que desea eliminar.",
                        "Empleado no encontrado",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible eliminar el empleado.",
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private Empleado obtenerEmpleadoFormulario() {

        String nombre =
                txtNombre.getText().trim();

        String departamento =
                txtDepartamento.getText().trim();

        String textoSalario =
                txtSalario.getText().trim();

        String textoFecha =
                txtFechaContratacion.getText().trim();
        
        String textoAniosExperiencia =
                txtAniosExperiencia.getText().trim();

        // Validar nombre
        if (nombre.isEmpty()) {

            mostrarAdvertencia(
                    "El nombre completo es obligatorio."
            );

            return null;
        }

        // Validar departamento
        if (departamento.isEmpty()) {

            mostrarAdvertencia(
                    "El departamento es obligatorio."
            );

            return null;
        }

        // Validar salario
        BigDecimal salario;

        try {

            salario = new BigDecimal(textoSalario);

        } catch (NumberFormatException ex) {

            mostrarAdvertencia(
                    "Ingrese un salario válido."
            );

            return null;
        }

        if (salario.compareTo(BigDecimal.ZERO) <= 0) {

            mostrarAdvertencia(
                    "El salario debe ser mayor que cero."
            );

            return null;
        }

        // Validar fecha
        LocalDate fechaContratacion;

        try {

            fechaContratacion =
                    LocalDate.parse(textoFecha);

        } catch (DateTimeParseException ex) {

            mostrarAdvertencia(
                    "Ingrese la fecha en formato AAAA-MM-DD."
            );

            return null;
        }

        if (fechaContratacion.isAfter(LocalDate.now())) {

            mostrarAdvertencia(
                    "La fecha de contratación "
                    + "no puede ser futura."
            );

            return null;
        }

        
     // Validar años de experiencia
        int aniosExperiencia;

        try {

            aniosExperiencia =
                    Integer.parseInt(textoAniosExperiencia);

        } catch (NumberFormatException ex) {

            mostrarAdvertencia(
                    "Ingrese una cantidad válida de años de experiencia."
            );

            return null;
        }

        if (aniosExperiencia < 0) {

            mostrarAdvertencia(
                    "Los años de experiencia no pueden ser negativos."
            );

            return null;
        }
        
        return new Empleado(
                nombre,
                departamento,
                salario,
                fechaContratacion,
                chkActivo.isSelected(),
                aniosExperiencia
        );
    }

    private void cargarEmpleadoSeleccionado() {

        int fila = tablaEmpleados.getSelectedRow();

        if (fila == -1) {
            return;
        }

        idSeleccionado = Integer.parseInt(
                modeloTabla.getValueAt(fila, 0).toString()
        );

        txtNombre.setText(
                modeloTabla.getValueAt(fila, 1).toString()
        );

        txtDepartamento.setText(
                modeloTabla.getValueAt(fila, 2).toString()
        );

        txtSalario.setText(
                modeloTabla.getValueAt(fila, 3).toString()
        );

        txtFechaContratacion.setText(
                modeloTabla.getValueAt(fila, 4).toString()
        );

        String estado =
                modeloTabla.getValueAt(fila, 5).toString();

        chkActivo.setSelected(
                estado.equals("Activo")
        );

        txtAniosExperiencia.setText(
                modeloTabla.getValueAt(fila, 6).toString()
      
        );
    }

    private void limpiarFormulario() {

        txtNombre.setText("");
        txtDepartamento.setText("");
        txtSalario.setText("");
        txtFechaContratacion.setText("");
        txtAniosExperiencia.setText("");

        chkActivo.setSelected(true);

        idSeleccionado = null;

        tablaEmpleados.clearSelection();

        txtNombre.requestFocus();
    }

    private void mostrarAdvertencia(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Datos inválidos",
                JOptionPane.WARNING_MESSAGE
        );
    }
}