package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.Operacao;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.repository.CarteiraRepository;
import br.ufrn.imd.stonks.framework.framework.model.Despesa;
import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivo;
import br.ufrn.imd.stonks.framework.framework.service.DespesaServiceAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarteiraService extends DespesaServiceAbstract<Carteira>{

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    UsuarioService usuarioService;

    public void salvarCarteira(Carteira carteira) {
        carteiraRepository.save(carteira);
    }

    public boolean isAlreadyPresent(Carteira carteira) {
        return carteiraRepository.findById(carteira.getId()).isPresent();
    }

    public Optional<Carteira> findById(int id){
        return carteiraRepository.findById(id);
    }

    public Carteira carteiraByUsuario(Usuario usuario) {
        return carteiraRepository.findByUsuario(usuario);
    }

    public void deleteById(int id) {
        carteiraRepository.deleteById(id);
    }

    @Override
    public DespesaAtivo adicionarAtivo(Despesa despesa, DespesaAtivo despesaAtivo) {
        despesaAtivo.setDespesa(despesa);
        return despesaAtivo;
    }

    @Override
    public boolean removerAtivo(DespesaAtivo despesaAtivo) {
        return false;
    }

    @Override
    public Despesa despesaByUsuario() {
        Usuario usuarioLogado = usuarioService.usuarioLogado();
        Carteira carteira = null;
        carteira = carteiraByUsuario(usuarioLogado);

        return carteira;
    }

    @Override
    public void salvarDespesa(Despesa despesa) {
        Carteira carteira = new Carteira(despesa);
        carteiraRepository.save(carteira);
    }
}
