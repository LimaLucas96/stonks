package br.com.stonks.stonks.dao;

import br.com.stonks.stonks.helper.ConexaoFactory;
import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Operacao;
import br.com.stonks.stonks.services.AtivoService;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarteiraAtivoDAO {

    private Connection con;
    private Statement comando;

    private AtivoService ativoService;

    public void update(CarteiraAtivo carteiraAtivo) {

        try {
            conectar();
            StringBuffer buffer = new StringBuffer();
            buffer.append("UPDATE carteira_ativo SET ");
            buffer.append(returnFieldValuesBD(carteiraAtivo));
            buffer.append(" WHERE id =");
            buffer.append(carteiraAtivo.getId());
            String sql = buffer.toString();

            comando.executeUpdate(sql);
            fechar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save(CarteiraAtivo carteiraAtivo) {
        try {
            conectar();

            StringBuffer buffer = new StringBuffer();
            buffer.append("INSERT INTO carteira_ativo (");
            buffer.append(this.retornarCamposBD());
            buffer.append(") VALUES (");
            buffer.append(retornarValoresBD(carteiraAtivo));
            buffer.append(")");
            String sql = buffer.toString();

            System.out.println(sql);

            comando.executeUpdate(sql);
            fechar();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public CarteiraAtivo findById(int id) {
        CarteiraAtivo carteiraAtivo = new CarteiraAtivo();
        try {
            conectar();
            String sql = "SELECT ca.*, a.codigo FROM carteira_ativo ca JOIN ativo a ON ca.ativo_id = a.id WHERE ca.id =" + id;
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                Ativo ativo = new Ativo();
                ativo.setId(rs.getInt("ativo_id"));
                ativo.setCodigo(rs.getString("codigo"));

                Carteira carteira = new Carteira();
                carteira.setId(rs.getInt("carteira_id"));

                carteiraAtivo.setAtivo(ativo);
                carteiraAtivo.setId(rs.getInt("id"));
                carteiraAtivo.setStatus(rs.getBoolean("status"));
                carteiraAtivo.setDataTransacao(rs.getDate("data_transacao"));
                carteiraAtivo.setQuantidade(rs.getInt("quantidade"));
                carteiraAtivo.setValor(rs.getDouble("valor"));
                carteiraAtivo.setOperacao(Operacao.valueOf(rs.getString("operacao")));
            }
            fechar();
            return carteiraAtivo;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return carteiraAtivo;
    }

    public void remove(CarteiraAtivo carteiraAtivo) {
        try {
            conectar();
            String sql = "DELETE FROM carteira_ativo WHERE id = " + carteiraAtivo.getId();
            comando.executeUpdate(sql);
            fechar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<CarteiraAtivo> findByAtivosCarteiraCompra(int id) {
        List<CarteiraAtivo> carteiraAtivos = new ArrayList<CarteiraAtivo>();
        try {
            conectar();
            String sql = "SELECT ca.*, a.codigo FROM carteira_ativo ca JOIN ativo a ON ca.ativo_id = a.id WHERE ca.operacao = 'COMPRA' AND ca.carteira_id = " + id ;
            ResultSet rs = comando.executeQuery(sql);
            while (rs.next()) {
                Ativo ativo = new Ativo();
                ativo.setId(rs.getInt("ativo_id"));
                ativo.setCodigo(rs.getString("codigo"));

                Carteira carteira = new Carteira();
                carteira.setId(rs.getInt("id"));

                CarteiraAtivo carteiraAtivo = new CarteiraAtivo();
                carteiraAtivo.setId(rs.getInt("id"));
                carteiraAtivo.setAtivo(ativo);
                carteiraAtivo.setStatus(rs.getBoolean("status"));
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

    public List<CarteiraAtivo> findByAtivosCarteira(int id) {
        List<CarteiraAtivo> carteiraAtivos = new ArrayList<CarteiraAtivo>();
        try {
            conectar();
            String sql = "SELECT ca.*, a.codigo FROM carteira_ativo ca JOIN ativo a ON ca.ativo_id = a.id WHERE ca.carteira_id = " + id;
            ResultSet rs = comando.executeQuery(sql);
            while (rs.next()) {
                Ativo ativo = new Ativo();
                ativo.setId(rs.getInt("ativo_id"));
                ativo.setCodigo(rs.getString("codigo"));

                Carteira carteira = new Carteira();
                carteira.setId(rs.getInt("carteira_id"));

                CarteiraAtivo carteiraAtivo = new CarteiraAtivo();
                carteiraAtivo.setId(rs.getInt("id"));
                carteiraAtivo.setAtivo(ativo);
                carteiraAtivo.setStatus(rs.getBoolean("status"));
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
            String sql = "SELECT COALESCE(SUM(ca.quantidade * ca.valor), 0) AS total FROM carteira_ativo ca WHERE ca.carteira_id = " + idCarteira;
            ResultSet rs = comando.executeQuery(sql);
            rs.next();
            Double total = rs.getDouble("total");
            fechar();

            return total;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0.0;
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
        return "data_transacao, quantidade, valor, operacao, ativo_id, carteira_id, status";
    }

    protected String returnFieldValuesBD(CarteiraAtivo carteiraAtivo) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(carteiraAtivo.getDataTransacao());

        StringBuffer buffer = new StringBuffer();
        buffer.append("ativo_id = ");
        buffer.append(carteiraAtivo.getAtivo().getId());
        buffer.append(", carteira_id = ");
        buffer.append(carteiraAtivo.getCarteira().getId());
        buffer.append(", valor = ");
        buffer.append(carteiraAtivo.getValor());
        buffer.append(", quantidade = ");
        buffer.append(carteiraAtivo.getQuantidade());
        buffer.append(", operacao = ");
        buffer.append("\'" + carteiraAtivo.getOperacao() + "\'");
        buffer.append(", data_transacao = ");
        buffer.append("TO_DATE(\'" + strDate + "\', \'YYYY-MM-DD\')");
        buffer.append(", status = ");
        buffer.append(carteiraAtivo.getStatus());

        return buffer.toString();
    }

    protected String retornarValoresBD(CarteiraAtivo carteiraAtivo) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(carteiraAtivo.getDataTransacao());

        return "TO_DATE(\'" + strDate
                        + "\', \'YYYY-MM-DD\'), "
                        + carteiraAtivo.getQuantidade()
                        + ", "
                        + carteiraAtivo.getValor()
                        + ", \'"
                        + carteiraAtivo.getOperacao()
                        + "\', "
                        + carteiraAtivo.getAtivo().getId()
                        + ", "
                        + carteiraAtivo.getCarteira().getId()
                        + ", "
                        + carteiraAtivo.getStatus();
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
