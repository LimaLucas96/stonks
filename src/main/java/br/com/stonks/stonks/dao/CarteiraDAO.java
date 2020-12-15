package br.com.stonks.stonks.dao;

import br.com.stonks.stonks.helper.ConexaoFactory;
import br.com.stonks.stonks.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarteiraDAO {

    private Connection con;
    private Statement comando;

    public void update(Carteira carteira) {
        try {
            conectar();
            StringBuffer buffer = new StringBuffer();
            buffer.append("UPDATE carteira SET ");
            buffer.append(returnFieldValuesBD(carteira));
            buffer.append(" WHERE id =");
            buffer.append(carteira.getId());
            String sql = buffer.toString();

            System.out.println(sql);

            comando.executeUpdate(sql);
            fechar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save(Carteira carteira) {
        try {
            conectar();

            StringBuffer buffer = new StringBuffer();
            buffer.append("INSERT INTO carteira (");
            buffer.append(this.retornarCamposBD());
            buffer.append(") VALUES (");
            buffer.append(retornarValoresBD(carteira));
            buffer.append(")");
            String sql = buffer.toString();

            System.out.println(sql);

            comando.executeUpdate(sql);
            fechar();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Carteira findById(int id) {
        Carteira carteira = new Carteira();
        try {
            conectar();
            String sql = "SELECT * FROM carteira WHERE id = " + id;
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                carteira.setStatus(rs.getBoolean("status"));
                carteira.setId(rs.getInt("id"));

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("usuario_id"));

                carteira.setUsuario(usuario);
            }
            fechar();
            return carteira;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return carteira;
    }

    public void remove(int id) {
        try {
            conectar();
            String sql = "DELETE FROM carteira WHERE id = " + id;
            comando.executeUpdate(sql);
            fechar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Carteira findByUsuario(Usuario usuario) {
        Carteira carteira = new Carteira();
        try {
            conectar();
            String sql = "SELECT ca.* FROM carteira ca JOIN usuario us ON ca.usuario_id = us.id WHERE ca.usuario_id = " + usuario.getId();
            ResultSet rs = comando.executeQuery(sql);
            while (rs.next()) {
                carteira.setStatus(rs.getBoolean("status"));
                carteira.setId(rs.getInt("id"));

                carteira.setUsuario(usuario);
            }
            fechar();
            return carteira;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return carteira;
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
        return "status, usuario_id";
    }

    protected String returnFieldValuesBD(Carteira carteira) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("data_modificacao = ");
        buffer.append(new Date());

        return buffer.toString();
    }

    protected String retornarValoresBD(Carteira carteira) {
        return (true + ", " + carteira.getUsuario().getId());
    }
}

