package aulas.part1;

import dao.CursoDAO;
import model.Curso;

import java.util.List;

public class MysqlCursoJdbcQueries {
    public static void main(String[] args) {
        // ---------------------  1. Consulta -------------------------------------

        CursoDAO dao = new CursoDAO();

        List<Curso> cursos = dao.list();
        cursos.stream().forEach(System.out::println);
        // ------------------------------------------------------------------------


        // ---------------------  1.1 Consulta por id -------------------------------------
        Curso curso1 = dao.getById(1);

        System.out.println("getById:: curso1");
        System.out.println(curso1);
        // ------------------------------------------------------------------------


        // ---------------------  2 Inserção -------------------------------------
      /*  Curso novoCurso = new Curso("Java", 8);

        dao.create(novoCurso);*/
        // ------------------------------------------------------------------------

        // ---------------------  3 Atualização -------------------------------------
        /*Curso cursoToUpdate = dao.getById(1);
        cursoToUpdate.setNome("Angular");

        dao.update(cursoToUpdate);*/
        // ------------------------------------------------------------------------

        // ---------------------  4 delete -------------------------------------
        dao.delete(1);
        // ------------------------------------------------------------------------
    }
}
