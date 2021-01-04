package model;

import javax.persistence.*;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    Integer idade;
    @Column
    String nome;

    @ManyToOne
    Estado estado;

    public Aluno() {
    }

    public Aluno(Integer idade, String nome, Estado estado) {
        this.idade = idade;
        this.nome = nome;
        this.estado = estado;
    }

    public Aluno(Integer id, Integer idade, String nome, Estado estado) {
        this.id = id;
        this.idade = idade;
        this.nome = nome;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", idade=" + idade +
                ", nome='" + nome + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
