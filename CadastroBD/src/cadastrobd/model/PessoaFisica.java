package cadastrobd.model;

public class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica() {
        super(); // Chamando o construtor sem parâmetros da classe Pessoa
    }

    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + cpf);
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
               "\nNome: " + getNome() +
               "\nLogradouro: " + getLogradouro() +
               "\nCidade: " + getCidade() +
               "\nEstado: " + getEstado() +
               "\nTelefone: " + getTelefone() +
               "\nEmail: " + getEmail() +
               "\nCPF: " + getCpf();
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
