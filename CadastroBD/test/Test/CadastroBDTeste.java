package Test;

import cadastroo.model.PessoaFisicaDAO;
import cadastroo.model.PessoaJuridicaDAO;
import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CadastroBDTeste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        try {
            ConectorBD.getConnection();
            System.out.println("Conexão estabelecida com sucesso!");

            while (true) {
                System.out.println("================================");
                System.out.println("1 - Incluir Pessoa");
                System.out.println("2 - Alterar Pessoa");
                System.out.println("3 - Excluir Pessoa");
                System.out.println("4 - Buscar pelo Id");
                System.out.println("5 - Exibir Todos");
                System.out.println("0 - Finalizar Programa");
                System.out.println("================================");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                if (opcao == 0) {
                    System.out.println("Finalizando programa...");
                    break;
                }

                System.out.print("F - Pessoa Física | J - Pessoa Jurídica: ");
                String tipo = scanner.nextLine().toUpperCase();

                switch (opcao) {
                    case 1:
                        // Incluir Pessoa
                        if (tipo.equals("F")) {
                            PessoaFisica pf = receberDadosPessoaFisica(scanner);
                            pessoaFisicaDAO.incluir(pf);
                        } else if (tipo.equals("J")) {
                            PessoaJuridica pj = receberDadosPessoaJuridica(scanner);
                            pessoaJuridicaDAO.incluir(pj);
                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;
                    case 2:
                        // Alterar Pessoa
                        System.out.print("Digite o ID da pessoa a ser alterada: ");
                        int idAlterar = scanner.nextInt();
                        scanner.nextLine(); // Consumir nova linha

                        if (tipo.equals("F")) {
                            PessoaFisica pf = pessoaFisicaDAO.getPessoa(idAlterar);
                            if (pf != null) {
                                System.out.println("Dados atuais: " + pf);
                                PessoaFisica novosDadosPF = receberDadosPessoaFisica(scanner);
                                novosDadosPF.setId(idAlterar); // Manter o mesmo ID
                                pessoaFisicaDAO.alterar(novosDadosPF);
                            } else {
                                System.out.println("Pessoa Física não encontrada!");
                            }
                        } else if (tipo.equals("J")) {
                            PessoaJuridica pj = pessoaJuridicaDAO.getPessoa(idAlterar);
                            if (pj != null) {
                                System.out.println("Dados atuais: " + pj);
                                PessoaJuridica novosDadosPJ = receberDadosPessoaJuridica(scanner);
                                novosDadosPJ.setId(idAlterar); // Manter o mesmo ID
                                pessoaJuridicaDAO.alterar(novosDadosPJ);
                            } else {
                                System.out.println("Pessoa Jurídica não encontrada!");
                            }
                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;
                    case 3:
                        // Excluir Pessoa
                        System.out.print("Digite o ID da pessoa a ser excluída: ");
                        int idExcluir = scanner.nextInt();
                        scanner.nextLine(); // Consumir nova linha

                        if (tipo.equals("F")) {
                            pessoaFisicaDAO.excluir(idExcluir);
                        } else if (tipo.equals("J")) {
                            pessoaJuridicaDAO.excluir(idExcluir);
                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;
                    case 4:
                        // Buscar pelo Id
                        System.out.print("Digite o ID da pessoa: ");
                        int idBuscar = scanner.nextInt();
                        scanner.nextLine(); // Consumir nova linha

                        if (tipo.equals("F")) {
                            PessoaFisica pf = pessoaFisicaDAO.getPessoa(idBuscar);
                            if (pf != null) {
                                System.out.println("Exibindo dados de Pessoa Física...");
                                System.out.println(pf);
                            } else {
                                System.out.println("Pessoa Física não encontrada!");
                            }
                        } else if (tipo.equals("J")) {
                            PessoaJuridica pj = pessoaJuridicaDAO.getPessoa(idBuscar);
                            if (pj != null) {
                                System.out.println("Exibindo dados de Pessoa Jurídica...");
                                System.out.println(pj);
                            } else {
                                System.out.println("Pessoa Jurídica não encontrada!");
                            }
                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;
                    case 5:
                        // Exibir Todos
                        if (tipo.equals("F")) {
                            List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
                            System.out.println("Exibindo todas as Pessoas Físicas...");
                            for (PessoaFisica pf : pessoasFisicas) {
                                System.out.println(pf);
                            }
                        } else if (tipo.equals("J")) {
                            List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
                            System.out.println("Exibindo todas as Pessoas Jurídicas...");
                            for (PessoaJuridica pj : pessoasJuridicas) {
                                System.out.println(pj);
                            }
                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static PessoaFisica receberDadosPessoaFisica(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        return new PessoaFisica(0, nome, logradouro, cidade, estado, telefone, email, cpf);
    }

    private static PessoaJuridica receberDadosPessoaJuridica(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        return new PessoaJuridica(0, nome, logradouro, cidade, estado, telefone, email, cnpj);
    }
}
