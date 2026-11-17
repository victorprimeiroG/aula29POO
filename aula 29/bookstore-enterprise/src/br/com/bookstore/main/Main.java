package br.com.bookstore.main;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAOSqlite();

        System.out.println("=== [CREATE] Persistindo obras ===");
        Livro l1 = new Livro("Clean Code", "Robert C. Martin", 129.90);
        Livro l2 = new Livro("Design Patterns", "Erich Gamma", 189.00);
        Livro l3 = new Livro("Refactoring", "Martin Fowler", 159.50);

        dao.salvar(l1);
        dao.salvar(l2);
        dao.salvar(l3);

        System.out.println("IDs gerados -> " + l1.getId() + ", " + l2.getId() + ", " + l3.getId());

        System.out.println("\n=== [READ] Buscando o livro de ID 2 ===");
        dao.buscarPorId(2L).ifPresentOrElse(
                livro -> System.out.println("Encontrado -> " + livro),
                () -> System.out.println("Nenhum livro com ID 2."));

        System.out.println("\n=== [READ] Buscando ID inexistente (999) ===");
        dao.buscarPorId(999L).ifPresentOrElse(
                livro -> System.out.println("Encontrado -> " + livro),
                () -> System.out.println("Aviso: nenhum livro cadastrado com o ID 999."));

        System.out.println("\n=== [READ] Catálogo completo ===");
        imprimir(dao.listarTodos());
    }

    private static void imprimir(List<Livro> lista) {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-6s | %-18s | %-16s | %10s%n", "ID", "TÍTULO", "AUTOR", "PREÇO");
        System.out.println("---------------------------------------------------------------");
        if (lista.isEmpty()) {
            System.out.println("(catálogo vazio)");
        } else {
            lista.forEach(System.out::println);
        }
        System.out.println("---------------------------------------------------------------");
        System.out.println("Total: " + lista.size() + " obra(s)");
    }
}