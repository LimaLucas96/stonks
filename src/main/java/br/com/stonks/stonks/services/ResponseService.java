package br.com.stonks.stonks.services;

import br.com.stonks.stonks.exception.ResponseException;
import br.com.stonks.stonks.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResponseService {

    @Autowired
    RestTemplate restTemplate;

    public Response getDadosAtivo(String symbol) throws ResponseException {
        Response response = restTemplate.getForObject("https://query1.finance.yahoo.com/v8/finance/chart/" +symbol+ ".SA" , Response.class);

        if (response == null) {
            throw new ResponseException("Não foi possivel receber os dados do ativo "+symbol+".");
        }

        if (response.getChart().getError() != null){
            throw new ResponseException("Não foi possivel pegar os dados. Erro: " + response.getChart().getError());
        }

        return response;
    }
}
