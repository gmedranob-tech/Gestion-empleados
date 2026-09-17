package edu.umg.programacion2.gestionempleados.dao;

import edu.umg.programacion2.gestionempleados.modelo.Empleado;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/gestion_empleados_db";

    private static final String USER = "root";

    private static final String PASSWORD = "tu_password_aqui";


    public Empleado crear(Empleado empleado) throws SQLException {

        String sql = "INSERT INTO empleados "
                + "(nombre_completo, departamento, salario, fecha_contratacion, activo) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, empleado.getNombreCompleto());
            stmt.setString(2, empleado.getDepartamento());
            stmt.setBigDecimal(3, empleado.getSalario());
            stmt.setDate(4, Date.valueOf(empleado.getFechaContratacion()));
            stmt.setBoolean(5, empleado.isActivo());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    empleado.setId(rs.getInt(1));
                }
            }

            return empleado;
        }
    }
    
    
    public List<Empleado> listarTodos() throws SQLException {

        List<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT id, nombre_completo, departamento, salario, "
                + "fecha_contratacion, activo FROM empleados";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                empleados.add(mapearFila(rs));
            }
        }

        return empleados;
    }
    
    
    public Optional<Empleado> buscarPorId(int id) throws SQLException {

        String sql = "SELECT id, nombre_completo, departamento, salario, "
                + "fecha_contratacion, activo "
                + "FROM empleados WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(mapearFila(rs));
                }
            }
        }

        return Optional.empty();
    }
    
    
    private static Empleado mapearFila(ResultSet resultado) throws SQLException {

        int id = resultado.getInt("id");
        String nombreCompleto = resultado.getString("nombre_completo");
        String departamento = resultado.getString("departamento");
        BigDecimal salario = resultado.getBigDecimal("salario");
        LocalDate fechaContratacion =
                resultado.getDate("fecha_contratacion").toLocalDate();
        boolean activo = resultado.getBoolean("activo");

        return new Empleado(
                id,
                nombreCompleto,
                departamento,
                salario,
                fechaContratacion,
                activo
        );
    }
    
    
    public boolean actualizar(Empleado empleado) throws SQLException {

        String sql = "UPDATE empleados SET "
                + "nombre_completo = ?, "
                + "departamento = ?, "
                + "salario = ?, "
                + "fecha_contratacion = ?, "
                + "activo = ? "
                + "WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getNombreCompleto());
            stmt.setString(2, empleado.getDepartamento());
            stmt.setBigDecimal(3, empleado.getSalario());
            stmt.setDate(4, Date.valueOf(empleado.getFechaContratacion()));
            stmt.setBoolean(5, empleado.isActivo());
            stmt.setInt(6, empleado.getId());

            return stmt.executeUpdate() > 0;
        }
    }
    
    
    public boolean eliminar(int id) throws SQLException {

        String sql = "DELETE FROM empleados WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
