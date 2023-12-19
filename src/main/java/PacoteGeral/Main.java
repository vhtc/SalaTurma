package PacoteGeral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        createTables();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------");
            System.out.println("------------------------------------------------");
            System.out.println("------------Escolha uma opção:------------------");
            System.out.println("- 1 - Cadastrar Alunos                         -");
            System.out.println("- 2 - Visualizar Alunos Cadastrados            -");
            System.out.println("- 3 - Cadastrar Professores                    -");
            System.out.println("- 4 - Visualizar Professores Cadastrados       -");
            System.out.println("- 5 - Cadastrar Turmas                         -");
            System.out.println("- 6 - Visualizar Turmas Cadastradas            -");
            System.out.println("- 7 - Vincular Aluno na Turma                  -");
            System.out.println("- 8 - Visualizar Alunos Vinculados na Turma    -");
            System.out.println("- 0 - Sair                                     -");
            System.out.println("------------------------------------------------");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o número

            switch (escolha) {
                case 1:
                    cadastrarAlunos();
                    break;
                case 2:
                    visualizarAlunos();
                    break;
                case 3:
                    cadastrarProfessores();
                    break;
                case 4:
                    visualizarProfessores();
                    break;
                case 5:
                    cadastrarTurmas();
                    break;
                case 6:
                    visualizarTurmas();
                    break;
                case 7:
                    vincularAlunoTurma();
                    break;
                case 8:
                    visualizarAlunosTurmas();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine(); // Consumir a quebra de linha após o número

            }
        }
    }

    public static void createTables() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/GestaoAulas.db")) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Aluno (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "nome TEXT," +
                            "idade INTEGER," +
                            "curso TEXT)")) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cadastrarProfessores() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do professor:");
        String nome = scanner.nextLine();

        System.out.println("Digite a disciplina do professor:");
        String disciplina = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/GestaoAulas.db")) {
            String insertProfessorQuery = "INSERT INTO Professor (nome, disciplina) VALUES (?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertProfessorQuery)) {
                insertStatement.setString(1, nome);
                insertStatement.setString(2, disciplina);
                insertStatement.executeUpdate();

                System.out.println("Professor cadastrado com sucesso!");
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine(); // Consumir a quebra de linha após o número
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cadastrarTurmas() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o código da turma:");
        String codigo = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/GestaoAulas.db")) {
            String insertTurmaQuery = "INSERT INTO Turma (codigo) VALUES (?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertTurmaQuery)) {
                insertStatement.setString(1, codigo);
                insertStatement.executeUpdate();

                System.out.println("Turma cadastrada com sucesso!");
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine(); // Consumir a quebra de linha após o número
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void visualizarTurmas() {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/GestaoAulas.db")) {
            // Exemplo de consulta à tabela Aluno
            String selectTurmaQuery = "SELECT * FROM Turma";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectTurmaQuery);
                    ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String codigo = resultSet.getString("codigo");
                    System.out.println("ID: " + id + ", Codigo: " + codigo);
                }
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine(); // Consumir a quebra de linha após o número
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void vincularAlunoTurma() {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/GestaoAulas.db")) {

            // Solicitar o ID do aluno
            System.out.println("Digite o ID do aluno:");
            int alunoId = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o número

            // Solicitar o ID da turma
            System.out.println("Digite o ID da turma:");
            int turmaId = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o número

            // Inserir dados na tabela Turma_Aluno
            String insertTurmaAlunoQuery = "INSERT INTO Turma_Aluno (turma_id, aluno_id) VALUES (?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertTurmaAlunoQuery)) {
                insertStatement.setInt(1, turmaId);
                insertStatement.setInt(2, alunoId);
                insertStatement.executeUpdate();

                System.out.println("Aluno vinculado à turma com sucesso!");
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine(); // Consumir a quebra de linha após o número
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void visualizarAlunosTurmas() {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/GestaoAulas.db")) {
            // Exemplo de consulta à tabela Turma_Aluno com JOIN nas tabelas Turma e Aluno
            String selectTurmaAlunoQuery = "SELECT Turma.codigo AS Turma, Aluno.nome AS Aluno " +
                    "FROM Turma_Aluno " +
                    "JOIN Turma ON Turma_Aluno.turma_id = Turma.id " +
                    "JOIN Aluno ON Turma_Aluno.aluno_id = Aluno.id";

            try (PreparedStatement selectStatement = connection.prepareStatement(selectTurmaAlunoQuery);
                    ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nomeTurma = resultSet.getString("Turma");
                    String nomeAluno = resultSet.getString("Aluno");

                    System.out.println("Turma: " + nomeTurma + ", Aluno: " + nomeAluno);
                    
                }

                System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine(); // Consumir a quebra de linha após o número
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void visualizarProfessores() {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/GestaoAulas.db")) {
            String selectProfessorQuery = "SELECT * FROM Professor";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectProfessorQuery);
                    ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String disciplina = resultSet.getString("disciplina");

                    System.out.println("ID: " + id + ", Nome: " + nome + ", Disciplina: " + disciplina);
                    
                }
                System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine(); // Consumir a quebra de linha após o número
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cadastrarAlunos() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do aluno:");
        String nome = scanner.nextLine();

        System.out.println("Digite a idade do aluno:");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o número

        System.out.println("Digite o curso do aluno:");
        String curso = scanner.nextLine();

        // Inserir dados na tabela Aluno
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/GestaoAulas.db")) {
            String insertAlunoQuery = "INSERT INTO Aluno (nome, idade, curso) VALUES (?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertAlunoQuery)) {
                insertStatement.setString(1, nome);
                insertStatement.setInt(2, idade);
                insertStatement.setString(3, curso);
                insertStatement.executeUpdate();

                System.out.println("Aluno cadastrado com sucesso!");
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine(); // Consumir a quebra de linha após o número
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void visualizarAlunos() {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/GestaoAulas.db")) {
            // Exemplo de consulta à tabela Aluno
            String selectAlunoQuery = "SELECT * FROM Aluno";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectAlunoQuery);
                    ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    int idade = resultSet.getInt("idade");
                    String curso = resultSet.getString("curso");

                    System.out.println("ID: " + id + ", Nome: " + nome + ", Idade: " + idade + ", Curso: " + curso);
                    
                }
                System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine(); // Consumir a quebra de linha após o número
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
