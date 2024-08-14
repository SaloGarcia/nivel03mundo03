package Test;

import cadastroo.model.PessoaFisicaDAO;
import cadastroo.model.PessoaJuridicaDAO;
import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CadastroBDTeste {
    public static void main(String[] args) {
        // Teste para Pessoa Física
        try {
            ConectorBD.getConnection();
            System.out.println("Conexão estabelecida com sucesso!");

            // Criar uma instância de Pessoa Física com dados válidos
            PessoaFisica pessoaFisica = new PessoaFisica(1, "Ana Silva", "Av. das Flores, 123", "Fortaleza", "CE", "99999999999", "ana.silva@exemplo.com", "98765432100");
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            pessoaFisicaDAO.incluir(pessoaFisica);

            // Criar uma instância de Pessoa Jurídica com dados válidos
            PessoaJuridica pessoaJuridica = new PessoaJuridica(5, "Empresa Exemplo", "Av. Principal, 500", "São Paulo", "SP", "11223344556", "contato@empresa.com", "12345678000195");
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            pessoaJuridicaDAO.incluir(pessoaJuridica);

            // Exibir dados
            List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
            for (PessoaFisica pf : pessoasFisicas) {
                System.out.println("ID: " + pf.getId() + "\nNome: " + pf.getNome() + "\nLogradouro: " + pf.getLogradouro() + "\nCidade: " + pf.getCidade() + "\nEstado: " + pf.getEstado() + "\nTelefone: " + pf.getTelefone() + "\nEmail: " + pf.getEmail() + "\nCPF: " + pf.getCpf());
            }

            List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
            for (PessoaJuridica pj : pessoasJuridicas) {
                System.out.println("ID: " + pj.getId() + "\nNome: " + pj.getNome() + "\nLogradouro: " + pj.getLogradouro() + "\nCidade: " + pj.getCidade() + "\nEstado: " + pj.getEstado() + "\nTelefone: " + pj.getTelefone() + "\nEmail: " + pj.getEmail() + "\nCNPJ: " + pj.getCnpj());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
