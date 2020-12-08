package br.com.stonks.stonks.models;

public class Role {

    private int id;

    private String role;

    private String descricao;

    public Role(String role, String descricao) {
        this.role = role;
        this.descricao = descricao;
    }

    public Role () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
