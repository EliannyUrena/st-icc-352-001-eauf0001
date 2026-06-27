package edu.pucmm.eict.a2.servicios;

import edu.pucmm.eict.a2.encapsulaciones.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteServices {

    private static EstudianteServices instancia;

    public static EstudianteServices getInstancia() {
        if (instancia == null) {
            instancia = new EstudianteServices();
        }
        return instancia;
    }

    // listar
    public List<Estudiante> findAll() {
        List<Estudiante> lista = new ArrayList<>();

        String sql = "SELECT * FROM ESTUDIANTE";

        try (Connection con = GestionDb.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                lista.add(new Estudiante(
                    rs.getInt("Matricula"),
                    rs.getString("Nombre"),
                    rs.getString("Carrera")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void insertar(Estudiante e) {
        String sql = "INSERT INTO ESTUDIANTE (MATRICULA, NOMBRE, CARRERA) VALUES (?, ?, ?)";

        try (Connection con = GestionDb.getConnection();
        PreparedStatement ps = con.prepareStatement(sql) ) {

            ps.setInt(1, e.getMatricula());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getCarrera());

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Estudiante e) {
        String sql = "UPDATE ESTUDIANTE SET NOMBRE=?, CARRERA=? WHERE MATRICULA=?";

        try (Connection con = GestionDb.getConnection();
             PreparedStatement ps = con.prepareStatement(sql) ) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCarrera());
            ps.setInt(3, e.getMatricula());

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void eliminar(int matricula) {
        String sql = "DELETE FROM ESTUDIANTE WHERE MATRICULA=?";

        try (Connection con = GestionDb.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, matricula);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Estudiante buscarEstPorMatricula(int matricula) {

        String sql = "SELECT * FROM ESTUDIANTE WHERE MATRICULA=?";

        try (Connection con = GestionDb.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Estudiante(
                        rs.getInt("MATRICULA"),
                        rs.getString("NOMBRE"),
                        rs.getString("CARRERA")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
