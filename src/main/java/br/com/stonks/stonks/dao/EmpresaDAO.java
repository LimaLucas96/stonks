package br.com.stonks.stonks.dao;

import br.com.stonks.stonks.helper.ConexaoFactory;
import br.com.stonks.stonks.models.*;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpresaDAO {

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

    public void update(Empresa empresa) {

        try {
            conectar();
            StringBuffer buffer = new StringBuffer();
            buffer.append("UPDATE empresa SET ");
            buffer.append(returnFieldValuesBD(empresa));
            buffer.append(" WHERE id =");
            buffer.append(empresa.getId());
            String sql = buffer.toString();

            comando.executeUpdate(sql);
            fechar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save(Empresa empresa) {
        try {
            conectar();

            StringBuffer buffer = new StringBuffer();
            buffer.append("INSERT INTO empresa (");
            buffer.append(this.retornarCamposBD());
            buffer.append(") VALUES (");
            buffer.append(retornarValoresBD(empresa));
            buffer.append(")");
            String sql = buffer.toString();

            comando.executeUpdate(sql);
            fechar();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Empresa findById(Long id) {
        Empresa empresa = new Empresa();
        try {
            conectar();
            String sql = "SELECT * FROM empresa WHERE id = " + id;
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                empresa.setId(rs.getInt("id"));
                empresa.setCnpj(rs.getLong("cnpj"));
                empresa.setNome(rs.getString("nome"));
            }
            fechar();
            return empresa;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return empresa;
    }

    public void remove(Long id) {
        try {
            conectar();
            String sql = "DELETE FROM empresa WHERE id = " + id;
            comando.executeUpdate(sql);
            fechar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Empresa> findAll() {
        List<Empresa> empresas = new ArrayList<>();
        try {
            conectar();
            String sql = "SELECT * FROM empresa";
            ResultSet rs = comando.executeQuery(sql);
            while (rs.next()) {
                Empresa empresa = new Empresa();

                empresa.setId(rs.getInt("id"));
                empresa.setCnpj(rs.getLong("cnpj"));
                empresa.setNome(rs.getString("nome"));

                empresas.add(empresa);
            }
            fechar();
            return empresas;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return empresas;
    }

    public List<CarteiraAtivo> findByAtivosCarteira(int id) {
        List<CarteiraAtivo> carteiraAtivos = new ArrayList<CarteiraAtivo>();
        try {
            conectar();
            String sql = "SELECT ca.* FROM carteira_ativo ca JOIN ativo a ON ca.ativo_id = a.id WHERE ca.carteira_id = " + id;
            ResultSet rs = comando.executeQuery(sql);
            while (rs.next()) {
                Ativo ativo = new Ativo();
                ativo.setId(rs.getInt("ativo_id"));

                Carteira carteira = new Carteira();
                carteira.setId(rs.getInt("carteira_id"));

                CarteiraAtivo carteiraAtivo = new CarteiraAtivo();
                carteiraAtivo.setAtivo(ativo);
                carteiraAtivo.setStatus(rs.getBoolean("ativo"));
                carteiraAtivo.setDataTransacao(rs.getDate("data_transacao"));
                carteiraAtivo.setQuantidade(rs.getInt("quantidade"));
                carteiraAtivo.setValor(rs.getDouble("valor"));
                carteiraAtivo.setOperacao(Operacao.valueOf(rs.getString("operacao")));

                carteiraAtivos.add(carteiraAtivo);
            }
            fechar();
            return carteiraAtivos;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return carteiraAtivos;
    }

    public Double totalCarteira(Integer idCarteira) {
        try {
            conectar();
            String sql = "SELECT SUM(ca.quantidade * ca.valor) FROM carteira_ativo ca WHERE ca.carteira_id = " + idCarteira;
            ResultSet rs = comando.executeQuery(sql);

            fechar();
            return rs.getDouble("SUM");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0.0;
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
        return "cnpj, nome";
    }

    protected String returnFieldValuesBD(Empresa empresa) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("cnpj = ");
        buffer.append(empresa.getCnpj());
        buffer.append(", nome = ");
        buffer.append(empresa.getNome());

        return buffer.toString();
    }

    protected String retornarValoresBD(Empresa empresa) {
        return empresa.getCnpj()
                + ", "
                + empresa.getNome();
    }

    private String retornarValorStringBD(String valor) {
        if (valor != null && !"".equals(valor)) {
            valor = "'" + valor + "'";
        } else {
            valor = "'" + "'";
        }
        return valor;
    }
}