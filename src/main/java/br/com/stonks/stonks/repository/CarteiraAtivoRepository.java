package br.com.stonks.stonks.repository;

import br.com.stonks.stonks.helper.ConexaoFactory;
import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Operacao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarteiraAtivoRepository {

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

    public void update(CarteiraAtivo carteiraAtivo) {

        try {
            conectar();
            StringBuffer buffer = new StringBuffer();
            buffer.append("UPDATE carteira_ativo SET ");
            buffer.append(returnFieldValuesBD(carteiraAtivo));
            buffer.append(" WHERE id =");
            buffer.append(carteiraAtivo.getId());
            String sql = buffer.toString();

            System.out.println(sql);

            comando.executeUpdate(sql);
            fechar();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(CarteiraAtivo carteiraAtivo) {
        try {
            conectar();

            StringBuffer buffer = new StringBuffer();
            buffer.append("INSERT INTO EMPLOYEE (");
            buffer.append(this.retornarCamposBD());
            buffer.append(") VALUES (");
            buffer.append(retornarValoresBD(carteiraAtivo));
            buffer.append(")");
            String sql = buffer.toString();

            System.out.println(sql);

            comando.executeUpdate(sql);
            fechar();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CarteiraAtivo search(int id) {
        try {
            conectar();
            String sql = "SELECT * FROM carteira_ativo WHERE id = " + id;
            ResultSet rs = comando.executeQuery(sql);
            CarteiraAtivo carteiraAtivo = new CarteiraAtivo();
            if (rs.next()) {
                Ativo ativo = new Ativo();
                ativo.setId(rs.getInt("ativo_id"));

                Carteira carteira = new Carteira();
                carteira.setId(rs.getInt("carteira_id"));

                carteiraAtivo.setAtivo(ativo);
                carteiraAtivo.setStatus(rs.getBoolean("ativo"));
                carteiraAtivo.setDataTransacao(rs.getDate("data_transacao"));
                carteiraAtivo.setQuantidade(rs.getInt("quantidade"));
                carteiraAtivo.setValor(rs.getDouble("valor"));
                carteiraAtivo.setOperacao(Operacao.valueOf(rs.getString("operacao")));
            }
            fechar();
            return carteiraAtivo;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(CarteiraAtivo carteiraAtivo) {

        try {
            conectar();

            String sql = "DELETE FROM carteira_ativo WHERE id = " + carteiraAtivo.getId();
            System.out.println(sql);

            comando.executeUpdate(sql);

            fechar();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public List<CarteiraAtivo> findByAtivosCarteiraCompra(int id) {
        try {
            conectar();
            String sql = "SELECT * FROM carteira_ativo ca JOIN ativo a ON ca.ativo_id = a.id WHERE ca.operacao = 'COMPRA' AND ca.carteira_id = " + id ;
            ResultSet rs = comando.executeQuery(sql);
            List<CarteiraAtivo> emps = new ArrayList<CarteiraAtivo>();
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

                emps.add(carteiraAtivo);
            }
            fechar();
            return emps;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarteiraAtivo> findByAtivosCarteira(int id, Sort sort) {
        try {
            conectar();
            String sql = "SELECT * FROM carteira_ativo ca JOIN ativo a ON ca.ativo_id = a.id WHERE ca.carteira_id = " + id;
            ResultSet rs = comando.executeQuery(sql);
            List<CarteiraAtivo> emps = new ArrayList<CarteiraAtivo>();
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

                emps.add(carteiraAtivo);
            }
            fechar();
            return emps;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double totalCarteira(Integer idCarteira) {
        try {
            conectar();
            String sql = "SELECT SUM(ca.quantidade * ca.valor) FROM carteira_ativo ca WHERE ca.carteira_id = " + idCarteira;
            ResultSet rs = comando.executeQuery(sql);

            fechar();
            return rs.getDouble("SUM");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void conectar() throws ClassNotFoundException, SQLException {
        con = ConexaoFactory.conexao(URL, NOME, SENHA, BANCO);
        comando = con.createStatement();
        System.out.println("Conectado!");
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
        return "ativo, data_criacao, data_transacao, quantidade, valor, operacao, ativo_id, carteira_id";
    }

    protected String returnFieldValuesBD(CarteiraAtivo carteiraAtivo) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("ativo_id = ");
        buffer.append(carteiraAtivo.getAtivo().getId());
        buffer.append(", carteira_ativo = ");
        buffer.append(carteiraAtivo.getCarteira().getId());
        buffer.append(", valor = ");
        buffer.append(carteiraAtivo.getValor());
        buffer.append(", quantidade = ");
        buffer.append(carteiraAtivo.getQuantidade());
        buffer.append(", operacao = ");
        buffer.append(retornarValorStringBD(carteiraAtivo.getOperacao().getDenominacao()));
        buffer.append(", data_transacao = ");
        buffer.append(carteiraAtivo.getDataTransacao());
        buffer.append(", ativo = ");
        buffer.append(carteiraAtivo.getStatus());

        return buffer.toString();
    }

    protected String retornarValoresBD(CarteiraAtivo carteiraAtivo) {
        return
                true
                        + ", "
                        + new Date()
                        + ", "
                        + carteiraAtivo.getDataTransacao()
                        + ", "
                        + carteiraAtivo.getQuantidade()
                        + ", "
                        + carteiraAtivo.getQuantidade()
                        + ", "
                        + carteiraAtivo.getValor()
                        + ", "
                        + carteiraAtivo.getOperacao().getDenominacao()
                        + ", "
                        + carteiraAtivo.getAtivo().getId()
                        + ", "
                        + carteiraAtivo.getCarteira().getId();
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

