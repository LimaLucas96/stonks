package br.com.stonks.stonks.models;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Nome é obrigatorio.")
    private String nome;

    @NotNull(message = "Email é obrigatorio.")
    @Email(message = "Email invalido")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Senha é obrigatorio.")
    @Column(unique = true)
    private String password;

    @NotNull(message = "CPF é obrigatorio.")
    private String cpf;

    @Column(name = "status")
    private Boolean status;

    @Column( name = "data_nascimento")
    private Date dataNascimento;

    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Usuario(@NotNull(message = "Nome é obrigatorio.") String nome,
                   @NotNull(message = "Email é obrigatorio.") @Email(message = "Email invalido") String email,
                   @NotNull(message = "Senha é obrigatorio.") String password,
                   @NotNull(message = "CPF é obrigatorio.") String cpf,
                   Boolean status, Date dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.status = status;
        this.dataNascimento = dataNascimento;
    }

    public Usuario() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
