<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  <h1>AppNetflix Android App</h1>
  
  <h2>Sobre o Projeto</h2>
  <p>Este projeto é um clone do aplicativo da Netflix, desenvolvido com o objetivo de aprender e aplicar conceitos essenciais no desenvolvimento Android. Durante o desenvolvimento, estou explorando boas práticas, organização de código e recursos modernos para criar uma interface rica e funcional.</p>

  <h2>Objetivos de Aprendizado</h2>
  <ul>
    <li><strong>Ciclo de Vida de uma Activity:</strong> Gerenciar o ciclo de vida para garantir uma experiência de usuário fluida e eficiente.</li>
    <li><strong>Modificação de Temas no Android:</strong> Customização de temas para uma interface visual consistente.</li>
    <li><strong>Prototipagem:</strong> Design inicial das telas e fluxo de navegação.</li>
    <li><strong>Estruturação do Projeto:</strong> Separação de classes e arquivos para organização clara e escalabilidade.</li>
    <li><strong>Arquitetura Inicial e Modelos de Dados:</strong> Definição de uma base sólida para manipulação de dados.</li>
    <li><strong>Uso de Imagens e Proporções de Telas:</strong> Adaptação da interface para diferentes tamanhos de telas.</li>
    <li><strong>Listas Horizontais:</strong> Exibição de conteúdo com RecyclerView.</li>
    <li><strong>Listas Aninhadas:</strong> Utilizando NestedScrollView para deixar UI mais dinâmica.</li>
    <li><strong>Carrossel de Filmes:</strong> Implementação de carrossel com layout dinâmico e lógica para interação do usuário.</li>
    <li><strong>Placeholders:</strong> Melhorar a experiência de carregamento com placeholders para elementos visuais.</li>
    <li><strong>Toolbar:</strong> Criando toolbar própria.</li>
    <li><strong>Shadows:</strong> Estilizando imagens.</li>
  </ul>

  <h2>Tecnologias Utilizadas</h2>
  <ul>
    <li><strong>Linguagem:</strong> Kotlin</li>
    <li><strong>IDE:</strong> Android Studio</li>
    <li><strong>Gerenciamento de Dependências:</strong> Gradle com Kotlin DSL</li>
    <li><strong>Arquitetura:</strong> MVC (Model-View-Controller)</li>
    <li><strong>Componentes:</strong> Jetpack Components (LiveData, ViewModel, Navigation)</li>
  </ul>

  <h2>Funcionalidades Planejadas</h2>
  <ul>
    <li>Tela inicial com listas de filmes organizadas por categorias.</li>
    <li>Carrossel interativo de destaques.</li>
    <li>Detalhamento de filmes com informações relevantes.</li>
    <li>Placeholder para carregamento suave.</li>
    <li>Suporte a diferentes resoluções de tela.</li>
  </ul>

  <h2>Como Executar</h2>
  <ol>
    <li>Clone este repositório:</li>
    <pre><code>git clone https://github.com/dutraotavio/AppNetflix.git</code></pre>
    <li>Abra o projeto no Android Studio.</li>
    <li>Sincronize as dependências do Gradle.</li>
    <li>Execute o aplicativo em um emulador ou dispositivo físico.</li>
  </ol>

  <h2>Estrutura do Projeto</h2>
  <pre>
app
├── model        # Lógica de negócios e modelos de dados
├── view         # Activities, Fragments e arquivos de layout
│   └── adapter  # Adapters para listas e carrosséis
├── controller   # Controladores para gestão da lógica entre model e view
└── resources    # Arquivos de recursos (temas, strings, imagens)
  </pre>

  <h2>Aprendizados</h2>
  <p>Este projeto me permitiu entender melhor conceitos fundamentais do desenvolvimento Android, desde a estruturação do projeto até a implementação de recursos visuais avançados.</p>

  <h2>Próximos Passos</h2>
  <ul>
    <li>Implementar navegação entre telas.</li>
    <li>Melhorar a responsividade e desempenho.</li>
    <li>Adicionar suporte a múltiplos idiomas.</li>
  </ul>

  <hr>
  <p>Sinta-se à vontade para acompanhar o progresso do projeto e contribuir com sugestões!</p>
</body>
</html>
