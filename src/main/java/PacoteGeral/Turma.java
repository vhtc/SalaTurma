package PacoteGeral;
import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String codigo;
    private List<Aluno> alunos;
    private List<Professor> professores;

    public Turma(String codigo) {
        this.codigo = codigo;
        this.alunos = new ArrayList<>(); // Inicializando a lista de alunos
        this.professores = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        System.out.println(aluno.getNome() + " adicionado à turma " + codigo);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
        System.out.println(aluno.getNome() + " removido da turma " + codigo);
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
        System.out.println(professor.getNome() + " adicionado à turma " + codigo);
    }

    public void removerProfessor(Professor professor) {
        professores.remove(professor);
        System.out.println(professor.getNome() + " removido da turma " + codigo);
    }
}
