package dao;

import dao.factory.BankEnum;
import dao.factory.ConnectionFactory;
import model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.factory.BankEnum.MYSQL;

public class CursoDAO {
    private static final BankEnum BANK_DEFAULT = MYSQL;
    private static BankEnum USED_BANK;

    public CursoDAO() {
        USED_BANK = BANK_DEFAULT;
    }

    public CursoDAO(BankEnum bankEnum) {
        USED_BANK = bankEnum;
    }

    public List<Curso> list() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection(USED_BANK)) {
            String sql = "SELECT * FROM curso";
            PreparedStatement statement = conn.prepareStatement(sql);
            final var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                Integer duracaoHoras = resultSet.getInt("duracao_horas");
                cursos.add(new Curso(id, nome, duracaoHoras));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public Curso getById(int id) {
        Curso curso = new Curso();
        try (Connection conn = ConnectionFactory.getConnection(USED_BANK)) {
            String sql = "SELECT * FROM curso where id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            final var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                Integer duracaoHoras = resultSet.getInt("duracao_horas");
                curso = new Curso(id, nome, duracaoHoras);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

    public void create(Curso curso) {
        try (Connection conn = ConnectionFactory.getConnection(USED_BANK)) {
            String sql = "INSERT INTO curso(nome,duracao_horas) VALUES(?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, curso.getNome());
            statement.setInt(2, curso.getDuracaoHoras());
            final var result = statement.executeUpdate();

            if (result == 1) {
                System.out.println(" Curso criado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Curso curso) {
        try (Connection conn = ConnectionFactory.getConnection(USED_BANK)) {
            String sql = "UPDATE curso SET nome=?,duracao_horas=? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, curso.getNome());
            statement.setInt(2, curso.getDuracaoHoras());
            statement.setInt(3, curso.getId());
            final var result = statement.executeUpdate();

            if (result == 1) {
                System.out.println(" Curso atualizado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection(USED_BANK)) {
            String sql = "DELETE FROM curso WHERE id =?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            final var result = statement.executeUpdate();

            if (result == 1) {
                System.out.println(" Curso deletado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
