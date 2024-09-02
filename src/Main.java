import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<Aluno> alunos = new ArrayList<>();

    public void adicionarDisciplina(String nome, String codigo) {
        disciplinas.add(new Disciplina(nome, codigo));
    }

    public Disciplina buscarDisciplinaPorCodigo(String codigo) {
        return disciplinas.stream()
                .filter(d -> d.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    public Disciplina buscarDisciplinaPorNome(String nome) {
        return disciplinas.stream()
                .filter(d -> d.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public Aluno buscarAlunoPorNome(String nome) {
        return alunos.stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public void matricularAluno(String nome, String matricula, String codigoDisciplina) {
        nome = nome.toUpperCase();
        Aluno aluno = new Aluno(nome, matricula);
        Disciplina disciplina = buscarDisciplinaPorCodigo(codigoDisciplina);
        if (disciplina != null) {
            disciplina.matricularAluno(aluno);
            alunos.add(aluno);
            System.out.println("Matrícula concluída.");
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    public void listarDisciplinas() {
        disciplinas.forEach(System.out::println);
    }

    public void listarAlunosPorDisciplina(String codigo) {
        Disciplina disciplina = buscarDisciplinaPorCodigo(codigo);
        if (disciplina != null) {
            disciplina.getAlunos().forEach(System.out::println);
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    public void pesquisarAluno(String nome) {
        Aluno aluno = buscarAlunoPorNome(nome);
        if (aluno != null) {
            System.out.println(aluno);
            disciplinas.stream()
                    .filter(d -> d.getAlunos().contains(aluno))
                    .forEach(d -> System.out.println("Disciplina: " + d.getNome() + " (Código: " + d.getCodigo() + ")"));
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void alterarInformacoesDoEstudante(String matricula, String novoNome) {
        novoNome = novoNome.toUpperCase();
        Aluno aluno = alunos.stream()
                .filter(a -> a.getMatricula().equalsIgnoreCase(matricula))
                .findFirst()
                .orElse(null);
        if (aluno != null) {
            aluno.setNome(novoNome);
            System.out.println("Informações do aluno atualizadas.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);

        main.adicionarDisciplina("Matemática", "MAT01");
        main.adicionarDisciplina("Português", "POR02");
        main.adicionarDisciplina("Inglês", "ING03");

        int opcao;
        do {
            System.out.println("\n1. Matricular Alunos");
            System.out.println("2. Listar Disciplinas");
            System.out.println("3. Listar Alunos (por disciplina)");
            System.out.println("4. Pesquisar Disciplina (por nome ou por código)");
            System.out.println("5. Pesquisar Aluno (por nome)");
            System.out.println("6. Alterar informações do estudante");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do aluno (somente maiúsculas): ");
                    String nomeAluno = obterEntradaMaiuscula(scanner);
                    System.out.print("Matrícula do aluno: ");
                    String matriculaAluno = scanner.nextLine();
                    System.out.print("Código da disciplina: ");
                    String codigoDisciplinaMatricula = scanner.nextLine();
                    main.matricularAluno(nomeAluno, matriculaAluno, codigoDisciplinaMatricula);
                    break;
                case 2:
                    main.listarDisciplinas();
                    break;
                case 3:
                    System.out.print("Código da disciplina: ");
                    String codigoDisciplinaListar = scanner.nextLine();
                    main.listarAlunosPorDisciplina(codigoDisciplinaListar);
                    break;
                case 4:
                    System.out.print("Pesquisar disciplina por (1) Nome ou (2) Código? ");
                    int tipoPesquisa = scanner.nextInt();
                    scanner.nextLine();
                    if (tipoPesquisa == 1) {
                        System.out.print("Nome da disciplina: ");
                        String nomeDisciplina = scanner.nextLine();
                        Disciplina disciplinaPorNome = main.buscarDisciplinaPorNome(nomeDisciplina);
                        System.out.println(disciplinaPorNome != null ? disciplinaPorNome : "Disciplina não encontrada.");
                    } else {
                        System.out.print("Código da disciplina: ");
                        String codigoDisciplinaPesquisa = scanner.nextLine();
                        Disciplina disciplinaPorCodigo = main.buscarDisciplinaPorCodigo(codigoDisciplinaPesquisa);
                        System.out.println(disciplinaPorCodigo != null ? disciplinaPorCodigo : "Disciplina não encontrada.");
                    }
                    break;
                case 5:
                    System.out.print("Nome do aluno (somente maiúsculas): ");
                    String nomeAlunoBusca = obterEntradaMaiuscula(scanner);
                    main.pesquisarAluno(nomeAlunoBusca);
                    break;
                case 6:
                    System.out.print("Matrícula do aluno: ");
                    String matriculaAlteracao = scanner.nextLine();
                    System.out.print("Novo nome do aluno (somente maiúsculas): ");
                    String novoNome = obterEntradaMaiuscula(scanner);
                    main.alterarInformacoesDoEstudante(matriculaAlteracao, novoNome);
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);
    }

    private static String obterEntradaMaiuscula(Scanner scanner) {
        String entrada;
        while (true) {
            entrada = scanner.nextLine();
            if (entrada.equals(entrada.toUpperCase())) {
                return entrada;
            } else {
                System.out.println("Use apenas letras maiúsculas.");
            }
        }
    }
}
