package aulas.part1;

import dao.AlunoDAO;
import model.Aluno;

import java.util.List;

public class MysqlAlunoJdbcQueries {
    public static void main(String[] args) {
        // ---------------------  1. Consulta -------------------------------------

        AlunoDAO dao = new AlunoDAO();

        /*List<Aluno> alunos = dao.list();
        alunos.stream().forEach(System.out::println);
*/
        // ------------------------------------------------------------------------


        // ---------------------  1.1 Consulta por id -------------------------------------
  /*      Aluno pedro = dao.getById(1);

        System.out.println("getById:: pedro");
        System.out.println(pedro);
*/
        // ------------------------------------------------------------------------


        // ---------------------  2 Inserção -------------------------------------
      /*  Aluno novoAluno = new Aluno(27, "Tsutsumi", "PA");

        dao.create(novoAluno);*/
        // ------------------------------------------------------------------------

        // ---------------------  3 Atualização -------------------------------------
        Aluno alunoToUpdate = dao.getById(5);
        alunoToUpdate.setNome("Jurema");

        dao.update(alunoToUpdate);
        // ------------------------------------------------------------------------

        // ---------------------  4 delete -------------------------------------
        /*dao.delete(5);*/
        // ------------------------------------------------------------------------
    }
}
