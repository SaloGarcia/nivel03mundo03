package cadastroo.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import cadastrobd.model.PessoaFisica; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    // Método para obter uma pessoa física pelo ID
    public PessoaFisica getPessoa(int id) throws SQLException {
        Connection conn = ConectorBD.getConnection();
        String sql = "SELECT * FROM Pessoa WHERE idPessoa = ? AND cpf IS NOT NULL";
        PreparedStatement stmt = ConectorBD.getPrepared(conn, sql);
        stmt.setInt(1, id);
        ResultSet rs = ConectorBD.getSelect(stmt);

        PessoaFisica pessoa = null;
        if (rs.next()) {
            pessoa = new PessoaFisica();
            pessoa.setId(rs.getInt("idPessoa"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setLogradouro(rs.getString("logradouro"));
            pessoa.setCidade(rs.getString("cidade"));
            pessoa.setEstado(rs.getString("estado"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setCpf(rs.getString("cpf"));
        }

        ConectorBD.close(rs);
        ConectorBD.close(stmt);
        ConectorBD.close(conn);

        return pessoa;
    }

    // Método para obter todas as pessoas físicas
    public List<PessoaFisica> getPessoas() throws SQLException {
        Connection conn = ConectorBD.getConnection();
        String sql = "SELECT * FROM Pessoa WHERE cpf IS NOT NULL";
        PreparedStatement stmt = ConectorBD.getPrepared(conn, sql);
        ResultSet rs = ConectorBD.getSelect(stmt);

        List<PessoaFisica> pessoas = new ArrayList<>();
        while (rs.next()) {
            PessoaFisica pessoa = new PessoaFisica();
            pessoa.setId(rs.getInt("idPessoa"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setLogradouro(rs.getString("logradouro"));
            pessoa.setCidade(rs.getString("cidade"));
            pessoa.setEstado(rs.getString("estado"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setCpf(rs.getString("cpf"));
            pessoas.add(pessoa);
        }

        ConectorBD.close(rs);
        ConectorBD.close(stmt);
        ConectorBD.close(conn);

        return pessoas;
    }

    // Método para incluir uma pessoa física
    public void incluir(PessoaFisica pessoa) throws SQLException {
        Connection conn = ConectorBD.getConnection();
        int novoId = SequenceManager.getValue(conn, "seq_idPessoa");

        String sql = "INSERT INTO Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email, tipo, cpf) VALUES (?, ?, ?, ?, ?, ?, ?, 'F', ?)";
        PreparedStatement stmt = ConectorBD.getPrepared(conn, sql);
        stmt.setInt(1, novoId);
        stmt.setString(2, pessoa.getNome());
        stmt.setString(3, pessoa.getLogradouro());
        stmt.setString(4, pessoa.getCidade());
        stmt.setString(5, pessoa.getEstado());
        stmt.setString(6, pessoa.getTelefone());
        stmt.setString(7, pessoa.getEmail());
        String cpfTruncado = pessoa.getCpf() != null && pessoa.getCpf().length() <= 11 ? pessoa.getCpf() : pessoa.getCpf().substring(0, 11);
        stmt.setString(8, cpfTruncado);

        stmt.executeUpdate();

        ConectorBD.close(stmt);
        ConectorBD.close(conn);
    }

    // Método para alterar uma pessoa física
    public void alterar(PessoaFisica pessoa) throws SQLException {
        Connection conn = ConectorBD.getConnection();
        String sql = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ?, cpf = ? WHERE idPessoa = ?";
        PreparedStatement stmt = ConectorBD.getPrepared(conn, sql);
        stmt.setString(1, pessoa.getNome());
        stmt.setString(2, pessoa.getLogradouro());
        stmt.setString(3, pessoa.getCidade());
        stmt.setString(4, pessoa.getEstado());
        stmt.setString(5, pessoa.getTelefone());
        stmt.setString(6, pessoa.getEmail());
        stmt.setString(7, pessoa.getCpf());
        stmt.setInt(8, pessoa.getId()); 

        stmt.executeUpdate();

        ConectorBD.close(stmt);
        ConectorBD.close(conn);
    }

    // Método para excluir uma pessoa física pelo ID
    public void excluir(int id) throws SQLException {
        Connection conn = ConectorBD.getConnection();
        String sql = "DELETE FROM Pessoa WHERE idPessoa = ?";
        PreparedStatement stmt = ConectorBD.getPrepared(conn, sql);
        stmt.setInt(1, id);

        stmt.executeUpdate();

        ConectorBD.close(stmt);
        ConectorBD.close(conn);
    }
}
