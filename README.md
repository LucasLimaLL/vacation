
# Calculadora de Períodos de Férias

Bem-vindo à Calculadora de Melhores Períodos de Férias! Esta aplicação foi criada com o objetivo de ajudar os usuários a identificar os melhores períodos para tirar férias, levando em consideração uma série de parâmetros fornecidos pelo próprio usuário. Sabemos como é importante aproveitar ao máximo os momentos de descanso, e essa ferramenta está aqui para tornar o processo de planejamento de férias mais simples e eficiente.




## Exemplo de utilização

#### Buscar os melhores períodos

```http
  GET /vacation
```

| Parameter | Type     | Description                | Default                |
| :-------- | :------- | :------------------------- |:------------------------- |
| `cidade`  | `string` | **Required**. Cidade onde a empresa se localiza | |
| `estado`  | `string` | **Required**. UF onde a empresa se localiza | |
| `inicio_periodo` | `string` | Início do período de pesquisa: Padrão: yyyy-MM-dd | D+1 |
| `fim_periodo` | `string` | Fim do período de pesquisa: Padrão: yyyy-MM-dd | D+365 |
| `qtd_dias` | `int` | Quantidade de dias de férias | 30 |
| `valor_divisao` | `int` | Quantidade de períodos | 1 |
| `dias_trabalho` | `int` | **Required**. Identificador dos dias de trabalho: 1 - Segunda, 2 - Terça, 3 - Quarta, 4 - Quinta, 5 - Sexta, 6 - Sábado, 7 - Domingo |


