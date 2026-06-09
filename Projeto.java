
import java.util.ArrayList;
import java.util.Scanner;

public class Projeto {

    // =========================
    // CLASSE LIVRO
    // =========================
    static class Livro {

        private static int contador = 1;

        private int id;
        private String titulo;
        private String autor;
        private String genero;
        private int ano;
        private boolean disponivel;

        public Livro(String titulo, String autor, String genero, int ano) {

            this.id = contador++;
            this.titulo = titulo;
            this.autor = autor;
            this.genero = genero;
            this.ano = ano;
            this.disponivel = true;
        }

        public int getId() {
            return id;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public int getAno() {
            return ano;
        }

        public void setAno(int ano) {
            this.ano = ano;
        }

        public boolean isDisponivel() {
            return disponivel;
        }

        public void setDisponivel(boolean disponivel) {
            this.disponivel = disponivel;
        }

        @Override
        public String toString() {

            return "\nID: " + id +
                   "\nTítulo: " + titulo +
                   "\nAutor: " + autor +
                   "\nGênero: " + genero +
                   "\nAno: " + ano +
                   "\nDisponível: " + (disponivel ? "Sim" : "Não");
        }
    }

    // =========================
    // CLASSE SERVICE
    // =========================
    static class LivroService {

        private ArrayList<Livro> livros = new ArrayList<>();

        public void cadastrarLivro(Livro livro) {

            livros.add(livro);
        }

        public void listarLivros() {

            if (livros.isEmpty()) {

                System.out.println("\nNenhum livro cadastrado.");
                return;
            }

            for (Livro livro : livros) {

                System.out.println(livro);
                System.out.println("----------------------------");
            }
        }

        public Livro buscarPorId(int id) {

            for (Livro livro : livros) {

                if (livro.getId() == id) {

                    return livro;
                }
            }

            return null;
        }

        public boolean excluirLivro(int id) {

            Livro livro = buscarPorId(id);

            if (livro != null) {

                livros.remove(livro);
                return true;
            }

            return false;
        }

        public void buscarPorTitulo(String titulo) {

            boolean encontrado = false;

            for (Livro livro : livros) {

                if (livro.getTitulo().equalsIgnoreCase(titulo)) {

                    System.out.println(livro);
                    encontrado = true;
                }
            }

            if (!encontrado) {

                System.out.println("\nLivro não encontrado.");
            }
        }
    }

  
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LivroService service = new LivroService();

        int opcao;

        do {

            System.out.println("\n==============================");
            System.out.println("   CONTROLE DE LIVROS");
            System.out.println("==============================");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Editar livro");
            System.out.println("4 - Excluir livro");
            System.out.println("5 - Buscar livro");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    System.out.print("\nTítulo: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();

                    System.out.print("Gênero: ");
                    String genero = scanner.nextLine();

                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();

                    Livro livro = new Livro(titulo, autor, genero, ano);

                    service.cadastrarLivro(livro);

                    System.out.println("\nLivro cadastrado com sucesso!");
                    break;

                case 2:

                    service.listarLivros();
                    break;

                case 3:

                    System.out.print("\nDigite o ID do livro: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();

                    Livro livroEditar = service.buscarPorId(idEditar);

                    if (livroEditar != null) {

                        System.out.print("Novo título: ");
                        livroEditar.setTitulo(scanner.nextLine());

                        System.out.print("Novo autor: ");
                        livroEditar.setAutor(scanner.nextLine());

                        System.out.print("Novo gênero: ");
                        livroEditar.setGenero(scanner.nextLine());

                        System.out.print("Novo ano: ");
                        livroEditar.setAno(scanner.nextInt());
                        scanner.nextLine();

                        System.out.println("\nLivro atualizado com sucesso!");

                    } else {

                        System.out.println("\nLivro não encontrado!");
                    }

                    break;

                case 4:

                    System.out.print("\nDigite o ID do livro: ");
                    int idExcluir = scanner.nextInt();

                    boolean removido = service.excluirLivro(idExcluir);

                    if (removido) {

                        System.out.println("\nLivro removido com sucesso!");

                    } else {

                        System.out.println("\nLivro não encontrado!");
                    }

                    break;

                case 5:

                    System.out.print("\nDigite o título do livro: ");
                    String busca = scanner.nextLine();

                    service.buscarPorTitulo(busca);

                    break;

                case 0:

                    System.out.println("\nSistema encerrado!");
                    break;

                default:

                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}