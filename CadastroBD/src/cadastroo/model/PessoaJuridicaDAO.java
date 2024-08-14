package cadastroo.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import cadastrobd.model.PessoaJuridica; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {

    // Método para obter uma pessoa jurídica pelo ID
    public PessoaJuridica getPessoa(int id) throws SQLException {
        String sql = "SELECT * FROM Pessoa WHERE idPessoa = ? AND tipo = 'J'";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(conn, sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PessoaJuridica(
                            rs.getInt("idPessoa"),
                            rs.getString("nome"),
                            rs.getString("logradouro"),
                            rs.getString("cidade"),
                            rs.getString("estado"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("cnpj")
                    );
                }
            }
        }
        return null;
    }

    // Método para obter todas as pessoas jurídicas
    public List<PessoaJuridica> getPessoas() throws SQLException {
        String sql = "SELECT * FROM Pessoa WHERE tipo = 'J'";
        List<PessoaJuridica> pessoas = new ArrayList<>();
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(conn, sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pessoas.add(new PessoaJuridica(
                        rs.getInt("idPessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj")
                ));
            }
        }
        return pessoas;
    }

    // Método para incluir uma pessoa jurídica
    public void incluir(PessoaJuridica pessoa) throws SQLException {
        String sql = "INSERT INTO Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email, tipo, cnpj) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, 'J', ?)";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(conn, sql)) {
            int idPessoa = SequenceManager.getValue(conn, "seq_idPessoa");
            stmt.setInt(1, idPessoa);
            stmt.setString(2, pessoa.getNome()); 
            stmt.setString(3, pessoa.getLogradouro());
            stmt.setString(4, pessoa.getCidade());
            stmt.setString(5, pessoa.getEstado());
            stmt.setString(6, pessoa.getTelefone());
            stmt.setString(7, pessoa.getEmail());
            stmt.setString(8, pessoa.getCnpj()); 
            stmt.executeUpdate();
        }
    }

    // Método para alterar uma pessoa jurídica
    public void alterar(PessoaJuridica pessoa) throws SQLException {
        String sql = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ?, cnpj = ? " +
                     "WHERE idPessoa = ? AND tipo = 'J'";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(conn, sql)) {
            stmt.setString(1, pessoa.getNome()); 
            stmt.setString(2, pessoa.getLogradouro());
            stmt.setString(3, pessoa.getCidade());
            stmt.setString(4, pessoa.getEstado());
            stmt.setString(5, pessoa.getTelefone());
            stmt.setString(6, pessoa.getEmail());
            stmt.setString(7, pessoa.getCnpj()); 
            stmt.setInt(8, pessoa.getId()); 
            stmt.executeUpdate();
        }
    }

    // Método para excluir uma pessoa jurídica pelo ID
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Pessoa WHERE idPessoa = ? AND tipo = 'J'";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(conn, sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
