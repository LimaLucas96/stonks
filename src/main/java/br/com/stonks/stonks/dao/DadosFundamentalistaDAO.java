package br.com.stonks.stonks.dao;

import br.com.stonks.stonks.helper.ConexaoFactory;
import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.DadosFundamentalista;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DadosFundamentalistaDAO {

    private Connection con;
    private Statement comando;

    public DadosFundamentalista findByAtivo(Integer id) {
        DadosFundamentalista dadosFundamentalista = new DadosFundamentalista();
        try {
            conectar();
            String sql = "SELECT * FROM dados_fundamentalista WHERE ativo_id = " + id;
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                dadosFundamentalista.setEvEbit(rs.getDouble("ev_ebit"));
                dadosFundamentalista.setEvEbitda(rs.getDouble("ev_ebitda"));
                dadosFundamentalista.setLiquidezCorrente(rs.getDouble("liquidez_corrente"));
                dadosFundamentalista.setMargeEbit(rs.getDouble("marge_ebit"));
                dadosFundamentalista.setMargeLiq(rs.getDouble("marge_liq"));
                dadosFundamentalista.setpAcl(rs.getDouble("p_acl"));
                dadosFundamentalista.setpAtivo(rs.getDouble("p_ativo"));
                dadosFundamentalista.setpCapGiro(rs.getDouble("p_cap_giro"));
                dadosFundamentalista.setpEbit(rs.getDouble("p_ebit"));
                dadosFundamentalista.setpL(rs.getDouble("pl"));
                dadosFundamentalista.setpVp(rs.getDouble("p_vp"));
                dadosFundamentalista.setPsr(rs.getDouble("psr"));
                dadosFundamentalista.setRoe(rs.getDouble("roe"));
                dadosFundamentalista.setRoic(rs.getDouble("roic"));
                dadosFundamentalista.setStatus(rs.getBoolean("status"));
                dadosFundamentalista.setDataAtualizacao(rs.getDate("data_atualizacao"));

                Ativo ativo = new Ativo();
                ativo.setId(rs.getInt("ativo_id"));

                dadosFundamentalista.setAtivo(ativo);

            }
            fechar();
            return dadosFundamentalista;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dadosFundamentalista;
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
}
