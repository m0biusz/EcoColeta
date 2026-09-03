# Documentação Técnica - EcoColeta

Este documento descreve a estrutura técnica, arquitetura e funcionalidades do aplicativo EcoColeta.

## 1. Objetivo do Aplicativo
O **EcoColeta** é uma solução móvel voltada para a gestão inteligente de resíduos urbanos. Seu propósito é conectar cidadãos aos serviços de limpeza da cidade, fornecendo informações sobre horários de coleta, localização de ecopontos, envio de ocorrências e lembretes proativos.

## 2. Arquitetura Utilizada
O projeto segue uma arquitetura modular baseada em **Android Fragments** para a interface de usuário e o padrão **Repository** para a camada de dados:
*   **View Layer**: Fragments independentes para cada seção, gerenciados por uma `MainActivity`.
*   **Data Layer**: Modelos POJO (Plain Old Java Objects) e Repositórios para abstração da fonte de dados (local/remota).
*   **Networking**: Camada de comunicação REST centralizada via Retrofit Singleton.

## 3. Linguagem de Programação
*   **Java**: Utilizado para toda a lógica de negócios, controle de ciclo de vida e interação com APIs do sistema.
*   **XML**: Utilizado para a definição de layouts, estilos, temas e vetores gráficos.

## 4. Configuração do Gradle
*   **Android Gradle Plugin (AGP)**: 9.3.2
*   **Compile SDK**: 37 (Android 15+)
*   **Min SDK**: 24 (Android 7.0 Nougat)
*   **Target SDK**: 37

## 5. Bibliotecas Utilizadas
*   **Material Components (M3)**: Design system moderno e componentes de interface.
*   **Retrofit 2.11.0**: Cliente HTTP para comunicação com API REST.
*   **Gson**: Conversão automática de JSON para objetos Java.
*   **Google Play Services Maps**: Renderização de mapas interativos.
*   **Google Play Services Location**: Geolocalização precisa e fused location.
*   **AppCompat & ConstraintLayout**: Suporte a versões anteriores e layouts flexíveis.

## 6. API Utilizada
Definida na interface `EcoColetaApi` com suporte aos seguintes endpoints (Base URL: `https://api.ecocoleta.com.br/`):
*   `GET /coletas`: Programação de resíduos.
*   `GET /pontos-descarte`: Localização de ecopontos.
*   `GET /avisos`: Alertas do sistema.
*   `GET /ocorrencias`: Lista de chamados do usuário.
*   `POST /ocorrencias`: Registro de novos problemas.

## 7. Estrutura das Telas
*   **MainActivity**: Contêiner principal com navegação inferior.
*   **HomeFragment**: Painel de controle com saudação, próxima coleta e avisos.
*   **MapFragment**: Visualização geográfica de pontos de descarte e posição do usuário.
*   **CollectionsFragment**: Lista cronológica da programação de coletas (RecyclerView).
*   **TicketsFragment**: Gestão de chamados registrados.
*   **TicketDetailActivity**: Detalhamento técnico de uma ocorrência específica e seu histórico.

## 8. Fluxo de Navegação
A navegação é do tipo **Bottom Navigation** (Barra Inferior) de 4 níveis. O fluxo de detalhes ocorre através de transições de Activity, permitindo ao usuário aprofundar-se em informações de chamados sem perder o contexto da aba principal.

## 9. Funcionalidades Implementadas
*   **Dashboard**: Resumo da próxima coleta com data, hora e tipo de resíduo.
*   **Mapa Interativo**: Marcadores coloridos para Ecopontos (Verde) e Reciclagem (Azul).
*   **Gestão de Chamados**: Acompanhamento de status (Enviado até Resolvido).
*   **Notificações**: Sistema de lembretes via canal de notificação oficial do Android.
*   **Configurações**: Switch para ativação/desativação de lembretes com persistência de estado via SharedPreferences.

## 10. Recursos de UX Aplicados
*   **Hierarquia Visual**: Uso de Cards e tipografia variada para destacar informações críticas.
*   **Feedback de Ações**: Uso de `Toast` e `ProgressBars` para indicar processamento e carregamento.
*   **Acessibilidade**: Atributos `contentDescription` em todos os elementos visuais e alto contraste de cores.
*   **Empty States**: Telas amigáveis com ilustrações vetoriais quando não há dados a exibir.

## 11. Permissões Utilizadas
*   `INTERNET`: Comunicação com a API.
*   `ACCESS_FINE_LOCATION`: Localização precisa no mapa.
*   `ACCESS_COARSE_LOCATION`: Localização aproximada.
*   `POST_NOTIFICATIONS`: Envio de alertas no Android 13+.

## 12. Limitações Atuais
*   A interface utiliza dados simulados (*Mock Data*) para demonstração das funcionalidades.
*   A autenticação de usuário ainda não foi implementada.
*   O envio real de fotos em chamados está em modo placeholder.

## 13. Possíveis Melhorias Futuras
*   **Sincronização Real**: Conectar os repositórios à instância real do `RetrofitClient`.
*   **Modo Offline**: Cache local com banco de dados Room para visualização da programação sem internet.
*   **Rotas de Coleta**: Traçar rotas no mapa entre a posição do usuário e o ponto de descarte mais próximo.
*   **Login**: Integração com OAuth ou Firebase para perfis de usuário personalizados.
