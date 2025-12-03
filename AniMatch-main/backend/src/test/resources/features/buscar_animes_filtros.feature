Funcionalidade: Busca de animes com múltiplos filtros
  Como fã de animes
  Quero filtrar animes por gênero, classificação, status e palavra-chave
  Para encontrar títulos relevantes de forma rápida

  Contexto:
    Dado que existem animes cadastrados no sistema
      | titulo              | genero      | classificacao | status   |
      | Fullmetal Alchemist | Action      | PG-13         | Finished |
      | Haikyuu!!           | Sports      | PG-13         | Finished |
      | Attack on Titan     | Action      | R-17          | Ongoing  |
      | K-On!               | Music       | PG-13         | Finished |

  Cenário: Buscar animes apenas por gênero
    Quando eu buscar animes filtrando por genero "Action" e sem demais filtros
    Então a lista de resultados deve conter exatamente
      | titulo              |
      | Fullmetal Alchemist |
      | Attack on Titan     |

  Cenário: Buscar animes por gênero e classificação
    Quando eu buscar animes filtrando por genero "Action" e classificacao "PG-13" e sem demais filtros
    Então a lista de resultados deve conter exatamente
      | titulo              |
      | Fullmetal Alchemist |

  Cenário: Buscar animes por status e palavra-chave
    Quando eu buscar animes filtrando por status "Finished" e palavra-chave "Haikyuu"
    Então a lista de resultados deve conter exatamente
      | titulo   |
      | Haikyuu!! |

  Cenário: Nenhum anime encontrado para os filtros
    Quando eu buscar animes filtrando por genero "Horror" e classificacao "G" e status "Finished"
    Então a lista de resultados deve estar vazia


