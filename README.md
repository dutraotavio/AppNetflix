# AppNetflix Android App

## Sobre o Projeto
Este projeto é um clone do aplicativo da Netflix, desenvolvido com o objetivo de aprender e aplicar conceitos essenciais no desenvolvimento Android. Durante o desenvolvimento, estou explorando boas práticas, organização de código e recursos modernos para criar uma interface rica e funcional.

## Objetivos de Aprendizado
- **Ciclo de Vida de uma Activity:** Gerenciar o ciclo de vida para garantir uma experiência de usuário fluida e eficiente.
- **Modificação de Temas no Android:** Customização de temas para uma interface visual consistente.
- **Prototipagem:** Design inicial das telas e fluxo de navegação.
- **Estruturação do Projeto:** Separação de classes e arquivos para organização clara e escalabilidade.
- **Arquitetura Inicial e Modelos de Dados:** Definição de uma base sólida para manipulação de dados.
- **Uso de Imagens e Proporções de Telas:** Adaptação da interface para diferentes tamanhos de telas.
- **Listas Horizontais:** Exibição de conteúdo com RecyclerView.
- **Listas Aninhadas:** Utilizando NestedScrollView para deixar UI mais dinâmica.
- **Carrossel de Filmes:** Implementação de carrossel com layout dinâmico e lógica para interação do usuário.
- **Placeholders:** Melhorar a experiência de carregamento com placeholders para elementos visuais.
- **Toolbar:** Criando toolbar própria.
- **Shadows:** Estilizando imagens.

## Tecnologias Utilizadas
- **Linguagem:** Kotlin
- **IDE:** Android Studio
- **Gerenciamento de Dependências:** Gradle com Kotlin DSL
- **Arquitetura:** MVC (Model-View-Controller)
- **Componentes:** Jetpack Components (LiveData, ViewModel, Navigation)

## Funcionalidades Planejadas
- Tela inicial com listas de filmes organizadas por categorias.
- Carrossel interativo de destaques.
- Detalhamento de filmes com informações relevantes.
- Placeholder para carregamento suave.
- Suporte a diferentes resoluções de tela.

## Como Executar
1. Clone este repositório:
```
git clone https://github.com/dutraotavio/AppNetflix.git
```
2. Abra o projeto no Android Studio.
3. Sincronize as dependências do Gradle.
4. Execute o aplicativo em um emulador ou dispositivo físico.

## Estrutura do Projeto
```
app
├── model        # Lógica de negócios e modelos de dados
├── view         # Activities, Fragments e arquivos de layout
│   └── adapter  # Adapters para listas e carrosséis
├── controller   # Controladores para gestão da lógica entre model e view
└── resources    # Arquivos de recursos (temas, strings, imagens)
```

## Aprendizados
Este projeto me permitiu entender melhor conceitos fundamentais do desenvolvimento Android, desde a estruturação do projeto até a implementação de recursos visuais avançados.

## Próximos Passos
- Implementar navegação entre telas.
- Melhorar a responsividade e desempenho.
- Adicionar suporte a múltiplos idiomas.

---
Sinta-se à vontade para acompanhar o progresso do projeto e contribuir com sugestões!
