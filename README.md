BDD aplicado ao AniMatch

1 – História de usuário
No AniMatch, escolhemos como foco de BDD a funcionalidade de busca de animes com filtros:
> Como fã de animes,
> quero buscar animes filtrando por gênero, classificação, status e/ou palavra‑chave (título),
> para encontrar rapidamente títulos que combinem com o que eu quero assistir no momento.
Essa história está diretamente relacionada ao método buscarAnimesComFiltros(...) da camada de serviço do backend.

---

2 – Cenários em Gherkin
A partir dessa história, foram definidos cenários de uso em Gherkin no arquivo
backend/src/test/resources/features/buscar_animes_filtros.feature.

Os principais cenários são:
Cenário 1 – Buscar animes apenas por gênero
Quando o usuário filtra por um gênero (ex.: "Action") e não informa outros filtros,
então o sistema deve retornar apenas os animes cadastrados com esse gênero.

Cenário 2 – Buscar animes por gênero e classificação
Quando o usuário filtra por gênero (ex.: "Action") e classificação (ex.: "PG-13"),
então o sistema deve retornar apenas os animes que atendem a ambos os critérios.

Cenário 3 – Buscar animes por status e palavra‑chave
Quando o usuário filtra por status (ex.: "Finished") e palavra‑chave no título (ex.: "Haikyuu"),
então o sistema deve retornar somente os animes terminados cujo título contém a palavra‑chave.

Cenário 4 – Nenhum anime encontrado
Quando o usuário escolhe uma combinação de filtros que não corresponde a nenhum anime (por exemplo gênero "Horror", classificação "G", status "Finished"),
então a lista de resultados deve vir vazia.
Cada cenário descreve claramente o contexto (animes cadastrados), a ação do usuário (consulta com filtros) e o resultado esperado (lista de animes ou lista vazia).

---

3 – Implementação dos testes BDD
Para implementar os testes BDD, foram utilizados Cucumber, JUnit e Spring Boot:
- Feature file (.feature)
O comportamento esperado foi descrito em Gherkin no arquivo:
backend/src/test/resources/features/buscar_animes_filtros.feature.

- Step definitions (steps em Java)
Os passos (Dado, Quando, Então) foram implementados na classe:
backend/src/test/java/com/example/animematch/bdd/BuscarAnimesComFiltrosSteps.java.
  Nessa classe, é feito:
    - O cadastro inicial dos animes de teste no banco em memória (@Dado que existem animes cadastrados no sistema).
    - A chamada ao método animeService.buscarAnimesComFiltros(...) conforme cada cenário (@Quando ...).
    - Asserções para verificar se a lista retornada contém exatamente os títulos esperados ou se está vazia (@Então ...).
      
- Runner do Cucumber
A integração com o JUnit é feita por meio da classe runner:
backend/src/test/java/com/example/animematch/bdd/CucumberTest.java, anotada com @Cucumber, que descobre e executa automaticamente todas as features e steps.

- Dependências
As dependências do Cucumber (cucumber-java, cucumber-junit-platform-engine, cucumber-spring) foram adicionadas ao backend/pom.xml com escopo de teste.
Com isso, ao executar os testes (por exemplo, com mvn test no módulo backend), os cenários descritos em Gherkin são executados automaticamente contra o código real do AniMatch, garantindo que a funcionalidade de busca com filtros se comporte conforme o que foi especificado na história de usuário.


##COMO RODAR:##

#### Passo 1: Rodando o Backend
No diretório `backend`, siga os passos abaixo:

```bash
# Execute o backend com o Spring Boot
mvn spring-boot:run
```

#### Passo 2: Rodando o Frontend
No diretório `frontend`, siga os passos abaixo:

```bash
# Instale as dependências do projeto
npm install

# Instale o axios (necessário para chamadas HTTP)
npm install axios

# Inicie o servidor de desenvolvimento
npm run dev
```

#### Testes: Como rodar
No diretório `backend`, siga os passos abaixo:

```bash
# Execute os testes com o Maven
mvn test
```


