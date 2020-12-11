package br.com.stonks.stonks.services;

import br.com.stonks.stonks.exception.ResponseException;
import br.com.stonks.stonks.models.*;
import br.com.stonks.stonks.repository.CarteiraAtivoRepository;
import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivo;
import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivoValorAbstract;
import br.ufrn.imd.stonks.framework.framework.service.DespesaAtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarteiraAtivoService extends DespesaAtivoService {

    @Autowired
    CarteiraAtivoRepository carteiraAtivoRepository;

    @Autowired
    private ResponseService responseService;

    public List<CarteiraAtivo> findByAtivosCarteiraCompra(int id) {
        return carteiraAtivoRepository.findByAtivosCarteiraCompra(id);
    }

    public Ativo[] listarAtivos(Carteira carteira) {
        CarteiraAtivo[] carteiraAtivo = carteiraAtivoRepository.findAllByDespesa(carteira);

        Ativo[] ativos = new Ativo[carteiraAtivo.length];
        for (int i = 0; i < carteiraAtivo.length; i ++) {
            ativos[i] = (Ativo) carteiraAtivo[i].getAtivoAbstract();
        }
        return ativos;
    }

    public Optional<CarteiraAtivo> findById(int id) {
        return carteiraAtivoRepository.findById(id);
    }

    public Double totalCarteira(Integer idCarteira){
        return carteiraAtivoRepository.totalCarteira(idCarteira);
    }

    @Override
    public List<DespesaAtivoValorAbstract> gerarDadosRelatorio(List<DespesaAtivo> ativos) {
        List<DespesaAtivoValorAbstract> carteiraAtivoValorList = new ArrayList<>();

        for (DespesaAtivo ativo : ativos) {
            CarteiraAtivoValor carteiraAtivoValor = new CarteiraAtivoValor();
            carteiraAtivoValor.setDespesaAtivo(ativo);

            try {
                Response response = responseService.getDadosAtivo(ativo.getAtivoAbstract().getCodigo());
                carteiraAtivoValor.setValor(response.getValorAcao());
                carteiraAtivoValor.setLucro((float) (response.getValorAcao() - ativo.getValor()));
            } catch (ResponseException e) {
                e.printStackTrace();
            }

            carteiraAtivoValorList.add(carteiraAtivoValor);
        }

        return carteiraAtivoValorList;
    }
}
