package com.example.animematch.bdd;

import com.example.animematch.model.Anime;
import com.example.animematch.repository.AnimeRepository;
import com.example.animematch.service.AnimeService;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
public class BuscarAnimesComFiltrosSteps {

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private AnimeService animeService;

    private List<Anime> resultados;

    @Before
    public void limparBanco() {
        animeRepository.deleteAll();
    }

    @Dado("que existem animes cadastrados no sistema")
    public void queExistemAnimesCadastradosNoSistema(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> linhas = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> linha : linhas) {
            Anime anime = new Anime();
            anime.setTituloPrincipal(linha.get("titulo"));
            anime.setGeneros(List.of(linha.get("genero")));
            anime.setClassificacao(linha.get("classificacao"));
            anime.setStatus(linha.get("status"));
            animeRepository.save(anime);
        }
    }

    @Quando("eu buscar animes filtrando por genero {string} e sem demais filtros")
    public void euBuscarAnimesFiltrandoPorGeneroESemDemaisFiltros(String genero) {
        resultados = animeService.buscarAnimesComFiltros(genero, null, null, null);
    }

    @Quando("eu buscar animes filtrando por genero {string} e classificacao {string} e sem demais filtros")
    public void euBuscarAnimesFiltrandoPorGeneroEClassificacaoESemDemaisFiltros(String genero, String classificacao) {
        resultados = animeService.buscarAnimesComFiltros(genero, classificacao, null, null);
    }

    @Quando("eu buscar animes filtrando por status {string} e palavra-chave {string}")
    public void euBuscarAnimesFiltrandoPorStatusEPalavraChave(String status, String palavraChave) {
        resultados = animeService.buscarAnimesComFiltros(null, null, status, palavraChave);
    }

    @Quando("eu buscar animes filtrando por genero {string} e classificacao {string} e status {string}")
    public void euBuscarAnimesFiltrandoPorGeneroClassificacaoEStatus(String genero, String classificacao, String status) {
        resultados = animeService.buscarAnimesComFiltros(genero, classificacao, status, null);
    }

    @Então("a lista de resultados deve conter exatamente")
    public void aListaDeResultadosDeveConterExatamente(io.cucumber.datatable.DataTable dataTable) {
        List<String> titulosEsperados = dataTable.asMaps().stream()
                .map(linha -> (String) linha.get("titulo"))
                .collect(Collectors.toList());

        List<String> titulosObtidos = resultados.stream()
                .map(Anime::getTituloPrincipal)
                .collect(Collectors.toList());

        Assertions.assertEquals(titulosEsperados, titulosObtidos);
    }

    @Então("a lista de resultados deve estar vazia")
    public void aListaDeResultadosDeveEstarVazia() {
        Assertions.assertTrue(resultados == null || resultados.isEmpty());
    }
}


