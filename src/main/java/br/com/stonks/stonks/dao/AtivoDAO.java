package br.com.stonks.stonks.dao;

import br.com.stonks.stonks.helper.ConexaoFactory;
import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.Empresa;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AtivoDAO {

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

    public Ativo findByCodigo(String codigo) {
        Ativo ativo = new Ativo();
        try {
            conectar();
            String sql = "SELECT * FROM ativo WHERE codigo = " + codigo;
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                ativo.setId(rs.getInt("id"));
                ativo.setCodigo(rs.getString("codigo"));

                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("empresa_id"));

                ativo.setEmpresa(empresa);
            }
            fechar();
            return ativo;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ativo;
    }

    public List<Ativo> findAll() {
        List<Ativo> ativos = new ArrayList<>();
        try {
            conectar();
            String sql = "SELECT * FROM ativo ";
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                Ativo ativo = new Ativo();
                ativo.setId(rs.getInt("id"));
                ativo.setCodigo(rs.getString("codigo"));

                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("empresa_id"));

                ativo.setEmpresa(empresa);

                ativos.add(ativo);
            }
            fechar();
            return ativos;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ativos;
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
}
