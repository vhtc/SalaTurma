package PacoteGeral;

public class Aluno {
    private String nome;
    private int idade;
    private String curso;

    public Aluno(String nome, int idade, String curso) {
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCurso() {
        return curso;
    }

    public void matricular() {
        System.out.println(nome + " matriculado no curso de " + curso);
    }

    public void cancelarMatricula() {
        System.out.println(nome + " cancelou a matrícula no curso de " + curso);
    }
}
