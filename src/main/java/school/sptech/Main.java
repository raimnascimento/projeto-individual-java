package school.sptech;

import java.time.Year;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Museu museu = new Museu("Museu Tempus Ars");

        String opcao = "";

        while (!opcao.equals("0")) {
            System.out.println("\n----------------------------------------");
            System.out.println("       MUSEU TEMPUS ARS - MENU           ");
            System.out.println("----------------------------------------");
            System.out.println("1 - Cadastrar pintura");
            System.out.println("2 - Cadastrar escultura");
            System.out.println("3 - Listar acervo do Museu");
            System.out.println("4 - Remover obra por ID");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                cadastrarPintura(scanner, museu);
            } else if (opcao.equals("2")) {
                cadastrarEscultura(scanner, museu);
            } else if (opcao.equals("3")) {
                List<ObraDeArte> acervo = museu.listarObras();

                if (acervo.isEmpty()) {
                    System.out.println("\n[!] O acervo do museu está vazio no banco de dados.");
                } else {
                    System.out.println("\n------ ACERVO DO MUSEU ------");
                    for (ObraDeArte obra : acervo) {
                        System.out.println(obra.obterDetalhes());
                    }
                }
            } else if (opcao.equals("4")) {
                removerObra(scanner, museu);
            } else if (opcao.equals("0")) {
                System.out.println("\nEncerrando o sistema... Até a próxima!");
            } else {
                System.out.println("\nOpção inválida! Escolha um número do menu.");
            }
        }

        scanner.close();
    }

    private static void removerObra(Scanner scanner, Museu museu) {
        System.out.println("\n------ REMOVER OBRA ------");
        while (true) {
            System.out.print("Digite o ID da obra a ser removida: ");
            String idInput = scanner.nextLine();
            if (!idInput.isBlank() && idInput.matches("\\d+")) {
                Integer id = Integer.parseInt(idInput);
                museu.removerObraPorId(id);
                break;
            }
            System.out.println("[!] ID inválido! Digite apenas números inteiros.");
        }
    }

    private static void cadastrarPintura(Scanner scanner, Museu museu) {
        System.out.println("\n------ CADASTRO DE PINTURA ------");

        Integer id = null;
        while (true) {
            System.out.print("ID: ");
            String idInput = scanner.nextLine();
            if (!idInput.isBlank() && idInput.matches("\\d+")) {
                id = Integer.parseInt(idInput);
                break;
            }
            System.out.println("[!] ID inválido! Digite apenas números inteiros positivos.");
        }

        String titulo = "";
        while (true) {
            System.out.print("Título: ");
            titulo = scanner.nextLine();
            if (!titulo.isBlank()) {
                break;
            }
            System.out.println("[!] Título não pode ficar em branco!");
        }

        String autor = "";
        while (true) {
            System.out.print("Autor: ");
            autor = scanner.nextLine();
            if (!autor.isBlank()) {
                break;
            }
            System.out.println("[!] Autor não pode ficar em branco!");
        }

        Integer ano = null;
        int anoAtual = Year.now().getValue();
        while (true) {
            System.out.print("Ano de criação: ");
            String anoInput = scanner.nextLine();
            if (!anoInput.isBlank() && anoInput.matches("\\d+")) {
                int anoDigitado = Integer.parseInt(anoInput);
                if (anoDigitado <= anoAtual) {
                    ano = anoDigitado;
                    break;
                } else {
                    System.out.println("[!] Ano inválido! O ano de criação não pode ser maior que o ano atual (" + anoAtual + ").");
                }
            } else {
                System.out.println("[!] Ano inválido! Digite apenas números inteiros.");
            }
        }

        String tipoTinta = "";
        while (true) {
            System.out.print("Tipo de tinta: ");
            tipoTinta = scanner.nextLine();
            if (!tipoTinta.isBlank()) {
                break;
            }
            System.out.println("[!] Tipo de tinta não pode ficar em branco!");
        }

        String tecnica = "";
        while (true) {
            System.out.print("Técnica: ");
            tecnica = scanner.nextLine();
            if (!tecnica.isBlank()) {
                break;
            }
            System.out.println("[!] Técnica não pode ficar em branco!");
        }

        Pintura pintura = new Pintura(id, titulo, autor, ano, tipoTinta, tecnica);
        museu.cadastrarObra(pintura);
    }

    private static void cadastrarEscultura(Scanner scanner, Museu museu) {
        System.out.println("\n------ CADASTRO DE ESCULTURA ------");

        Integer id = null;
        while (true) {
            System.out.print("ID: ");
            String idInput = scanner.nextLine();
            if (!idInput.isBlank() && idInput.matches("\\d+")) {
                id = Integer.parseInt(idInput);
                break;
            }
            System.out.println("[!] ID inválido! Digite apenas números inteiros positivos.");
        }

        String titulo = "";
        while (true) {
            System.out.print("Título: ");
            titulo = scanner.nextLine();
            if (!titulo.isBlank()) {
                break;
            }
            System.out.println("[!] Título não pode ficar em branco!");
        }

        String autor = "";
        while (true) {
            System.out.print("Autor: ");
            autor = scanner.nextLine();
            if (!autor.isBlank()) {
                break;
            }
            System.out.println("[!] Autor não pode ficar em branco!");
        }

        Integer ano = null;
        int anoAtual = Year.now().getValue();
        while (true) {
            System.out.print("Ano de criação: ");
            String anoInput = scanner.nextLine();
            if (!anoInput.isBlank() && anoInput.matches("\\d+")) {
                int anoDigitado = Integer.parseInt(anoInput);
                if (anoDigitado <= anoAtual) {
                    ano = anoDigitado;
                    break;
                } else {
                    System.out.println("[!] Ano inválido! O ano de criação não pode ser maior que o ano atual (" + anoAtual + ").");
                }
            } else {
                System.out.println("[!] Ano inválido! Digite apenas números inteiros.");
            }
        }

        String material = "";
        while (true) {
            System.out.print("Material: ");
            material = scanner.nextLine();
            if (!material.isBlank()) {
                break;
            }
            System.out.println("[!] Material não pode ficar em branco!");
        }

        Double peso = null;
        while (true) {
            System.out.print("Peso (kg): ");
            String pesoInput = scanner.nextLine();
            if (!pesoInput.isBlank() && pesoInput.matches("\\d+(\\.\\d+)?")) {
                peso = Double.parseDouble(pesoInput);
                if (peso > 0) {
                    break;
                } else {
                    System.out.println("[!] Peso inválido! O peso deve ser maior que zero");
                }
            } else {
                System.out.println("[!] Peso inválido! Digite um número positivo");
            }
        }

        Escultura escultura = new Escultura(id, titulo, autor, ano, material, peso);
        museu.cadastrarObra(escultura);
    }
}