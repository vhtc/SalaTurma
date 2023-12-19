
package PacoteGeral;
public class Professor {
    private String nome;
    private String materia;

    public Professor(String nome, String materia) {
        this.nome = nome;
        this.materia = materia;
    }

    public String getNome() {
        return nome;
    }

    public String getMateria() {
        return materia;
    }

    public void darAula() {
        System.out.println(nome + " está dando aula de " + materia);
    }

    public void cancelarAula() {
        System.out.println(nome + " cancelou a aula de " + materia);
    }
}
