package br.com.stonks.stonks.dao;

import br.com.stonks.stonks.helper.ConexaoFactory;
import br.com.stonks.stonks.models.Usuario;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UsuarioDAO {

    private Connection con;
    private Statement comando;

    public void update(Usuario usuario) {
        try {
            conectar();
            StringBuffer buffer = new StringBuffer();
            buffer.append("UPDATE usuario SET ");
            buffer.append(returnFieldValuesBD(usuario));
            buffer.append(" WHERE id =");
            buffer.append(usuario.getId());
            String sql = buffer.toString();

            comando.executeUpdate(sql);
            fechar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save(Usuario usuario) {
        try {
            conectar();

            StringBuffer buffer = new StringBuffer();
            buffer.append("INSERT INTO usuario (");
            buffer.append(this.retornarCamposBD());
            buffer.append(") VALUES (");
            buffer.append(retornarValoresBD(usuario));
            buffer.append(")");
            String sql = buffer.toString();

            comando.executeUpdate(sql);
            fechar();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int id) {
        try {
            conectar();
            String sql = "DELETE FROM usuario WHERE id = " + id;
            comando.executeUpdate(sql);
            fechar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Usuario findByEmail(String email) {
        Usuario usuario = new Usuario();
        try {
            conectar();
            String sql = "SELECT * FROM usuario WHERE email = '" + email + "'";
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setDataNascimento(rs.getDate("data_nascimento"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNome(rs.getString("nome"));
                usuario.setPassword(rs.getString("password"));
                usuario.setStatus(rs.getBoolean("status"));

            }
            fechar();
            return usuario;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public Usuario findByCpf(String cpf) {
        Usuario usuario = new Usuario();
        try {
            conectar();
            String sql = "SELECT * FROM usuario WHERE cpf = '" + cpf + "'";
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setDataNascimento(rs.getDate("data_nascimento"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNome(rs.getString("nome"));
                usuario.setPassword(rs.getString("password"));
                usuario.setStatus(rs.getBoolean("status"));

            }
            fechar();
            return usuario;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public Usuario findById(int id) {
        Usuario usuario = new Usuario();
        try {
            conectar();
            String sql = "SELECT * FROM usuario WHERE id = " + id;
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setDataNascimento(rs.getDate("data_nascimento"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNome(rs.getString("nome"));
                usuario.setPassword(rs.getString("password"));
                usuario.setStatus(rs.getBoolean("status"));

            }
            fechar();
            return usuario;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    private void conectar() throws ClassNotFoundException, SQLException {
        con = ConexaoFactory.conexao();
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
        return "cpf, email, nome, password, status";
    }

    protected String returnFieldValuesBD(Usuario usuario) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("cpf = '");
        buffer.append(usuario.getCpf());
        buffer.append(", email = '");
        buffer.append(usuario.getEmail());
        buffer.append("', nome = '");
        buffer.append(usuario.getNome());
        buffer.append("', password = '");
        buffer.append(usuario.getPassword());
        buffer.append("', status = ");
        buffer.append(usuario.getStatus());

        return buffer.toString();
    }

    protected String retornarValoresBD(Usuario usuario) {

        return "'" + usuario.getCpf()
                + "', '"
                + usuario.getEmail()
                + "', '"
                + usuario.getNome()
                + "', '"
                + usuario.getPassword()
                + "', "
                + usuario.getStatus();
    }
}
