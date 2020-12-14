package br.com.stonks.stonks.dao;

import br.com.stonks.stonks.helper.ConexaoFactory;
import br.com.stonks.stonks.models.Role;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoleDAO {

    @Value("${spring.datasource.url}")
    public String URL;

    @Value("${spring.datasource.username}")
    private String NOME;

    @Value("${spring.datasource.password}")
    private String SENHA;

    @Value("${spring.datasource.banco}")
    private int BANCO;

    private Connection con;
    private Statement comando;

    public void update(Role role) {
        try {
            conectar();
            StringBuffer buffer = new StringBuffer();
            buffer.append("UPDATE role SET ");
            buffer.append(returnFieldValuesBD(role));
            buffer.append(" WHERE id =");
            buffer.append(role.getId());
            String sql = buffer.toString();

            comando.executeUpdate(sql);
            fechar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save(Role role) {
        try {
            conectar();

            StringBuffer buffer = new StringBuffer();
            buffer.append("INSERT INTO role (");
            buffer.append(this.retornarCamposBD());
            buffer.append(") VALUES (");
            buffer.append(retornarValoresBD(role));
            buffer.append(")");
            String sql = buffer.toString();

            comando.executeUpdate(sql);
            fechar();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Role findByRole(String role_name) {
        Role role = new Role();
        try {
            conectar();
            String sql = "SELECT * FROM role WHERE role_name = " + role_name;
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                role.setId(rs.getInt("id"));
                role.setDescricao(rs.getString("descricao"));
                role.setRole(rs.getString("role_name"));
            }
            fechar();
            return role;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return role;
    }

    private void conectar() throws ClassNotFoundException, SQLException {
        con = ConexaoFactory.conexao(URL, NOME, SENHA, BANCO);
        comando = con.createStatement();
    }

    private void fechar() {
        try {
            comando.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected String retornarCamposBD() {
        return "descricao, role_name";
    }

    protected String returnFieldValuesBD(Role role) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("descricao = ");
        buffer.append(role.getDescricao());
        buffer.append(", role_name = ");
        buffer.append(role.getRole());

        return buffer.toString();
    }

    protected String retornarValoresBD(Role role) {
        return role.getDescricao()
                + ", "
                + role.getRole();
    }
}
