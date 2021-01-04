package dao;

import dao.factory.ConnectionFactory;
import model.Aluno;
import model.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    // 1. Consulta
    public List<Aluno> list() {
        List<Aluno> alunos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "SELECT aluno.* FROM aluno";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            Estado estado = new Estado();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Integer idade = rs.getInt("idade");

                estado = rs.getObject("estado", Estado.class);
                Integer id = rs.getInt("id");

                alunos.add(new Aluno(id, idade, nome, estado));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    // 1.1  Consulta com filtro
    public Aluno getById(Integer id) {
        Aluno aluno = new Aluno();

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "SELECT * FROM aluno where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                Integer idade = rs.getInt("idade");
                Estado estado = rs.getObject("estado", Estado.class);

                aluno.setId(id);
                aluno.setEstado(estado);
                aluno.setIdade(idade);
                aluno.setNome(nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aluno;
    }

    // 2. Inserção
    public void create(Aluno aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "INSERT INTO aluno(nome,idade,estado) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, aluno.getNome());
            statement.setInt(2, aluno.getIdade());
            statement.setInt(3, aluno.getEstado().getId());
            int sucesso = statement.executeUpdate();
            if (sucesso == 1) {
                System.out.println("Inserção bem sucedida");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 3. delete
    public void delete(Integer id) {
        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "Delete FROM aluno where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int sucesso = statement.executeUpdate();
            if (sucesso == 1) {
                System.out.println("Aluno deletado com sucesso");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 4. atualizar
    public void update(Aluno aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "UPDATE aluno set nome=?, idade=?, estado=? where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, aluno.getNome());
            statement.setInt(2, aluno.getIdade());
            statement.setInt(3, aluno.getEstado().getId());
            statement.setInt(4, aluno.getId());
            int sucesso = statement.executeUpdate();
            if (sucesso == 1) {
                System.out.println("Aluno atualizado bem sucedida");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
