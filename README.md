# 🏥 Clinic Priority Queue

> Sistema de gerenciamento de fila de atendimento hospitalar baseado em prioridades, desenvolvido em **Java 21** com interface gráfica em **JavaFX**.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Regras de Negócio](#-regras-de-negócio)
- [Arquitetura](#️-arquitetura-e-estrutura)
- [Tecnologias](#️-tecnologias-utilizadas)
- [Pré-requisitos](#-pré-requisitos)
- [Como Rodar](#-como-rodar)
- [Comandos Maven](#-comandos-principais-maven)
- [Melhorias Futuras](#-melhorias-futuras)
- [Contribuição](#-contribuição)

---

## 📖 Sobre o Projeto

Este sistema simula a gestão de filas de uma clínica/UPA, onde pacientes são atendidos **não por ordem de chegada**, mas por uma combinação de **gravidade clínica** e **direito preferencial** (idosos, PCDs e gestantes).

A estrutura de dados central é uma **Lista Duplamente Encadeada** implementada do zero, sem uso de `java.util.LinkedList`, garantindo controle total sobre a ordenação e remoção dos nós.

---

## ✅ Funcionalidades

- **Cadastro de pacientes** via formulário com validação de CPF (algoritmo oficial dos dígitos verificadores)
- **Inserção ordenada automática** na fila correta (preferencial ou comum) com base na gravidade
- **Chamada de pacientes** com painel visual exibindo nome, CPF e gravidade do próximo a ser atendido
- **Dois tipos de fila** gerenciadas em paralelo: Preferencial e Comum
- **Reinicialização do sistema** para novo turno de atendimento
- **Contador de atendimentos** do turno atual na barra de status
- **Painel administrativo** para monitoramento do estado das filas

---

## 📐 Regras de Negócio

### 1. Classificação dos Pacientes

| Tipo | Critério |
| :--- | :--- |
| **Preferencial** | Idade ≥ 60 anos (idoso), PCD ou Gestante |
| **Comum** | Nenhum dos critérios acima |

### 2. Ordenação dentro de cada fila

Os pacientes são inseridos de forma ordenada seguindo dois critérios em cascata:

1. **Gravidade** (escala de 1 a 5): pacientes com maior gravidade ficam à frente
2. **Desempate**: em caso de gravidade igual, o paciente preferencial passa na frente do comum *dentro da mesma fila*

### 3. Regra de chamada entre as filas (ciclo 3:2)

Quando ambas as filas têm pacientes com a **mesma gravidade na cabeça**, o sistema aplica uma intercalação proporcional:

```
Preferencial → Preferencial → Preferencial → Comum → Comum → [reinicia ciclo]
```

> **Gravidade absoluta sempre prevalece:** se a cabeça de uma fila tiver gravidade maior que a outra, ela é chamada imediatamente, quebrando o ciclo.

---

## 🏗️ Arquitetura e Estrutura

O projeto segue uma separação clara de responsabilidades em camadas:

```text
clinic-priority-queue/
├── src/main/java/
│   ├── controller/
│   │   ├── AdminController.java      # Controle e lógica da tela de administração
│   │   ├── FilaService.java          # Regras de negócio, inserção e chamadas na fila
│   │   └── MainController.java       # Controlador principal da aplicação
│   ├── main/
│   │   └── Main.java                 # Ponto de entrada (Inicialização do JavaFX)
│   ├── model/
│   │   ├── estrutura/                # Estruturas de dados customizadas (Lista encadeada, Nós)
│   │   │   ├── Lista.java            # Lista duplamente encadeada
│   │   │   └── No.java               # Nó da lista duplamente encadeada
│   │   └── Paciente.java             # Entidade representando o paciente
│   ├── util/
│   │   └── ValidaCPF.java            # Métodos utilitários de validação
│   └── view/
│       ├── ComponentFactory.java     # Fábrica para construção de componentes visuais
│       └── SistemaAtendimento.java   # Gerenciamento da interface e telas
├── src/main/resources/
│   └── Tela.fxml                     # Arquivo de estilização e layout da interface FXML
├── module-info.java                  # Configuração de módulos do Java
└── pom.xml                           # Dependências e ciclo de vida do Maven
```

### Responsabilidade de cada camada

| Camada | Responsabilidade |
| :--- | :--- |
| `controller` | Controladores de interface que gerenciam as interações do usuário e o fluxo das telas, além do processamento das regras da fila. |
| `main` | Ponto de entrada da aplicação, encarregado de inicializar o ciclo de vida do JavaFX. |
| `model` | Contém a entidade principal do domínio (Paciente). |
| `model.estrutura` | Camada dedicada à implementação da estrutura de dados (Lista encadeada/Nós). |
| `util` | Classes utilitárias e funções auxiliares desacopladas, como o validador ValidaCPF. |
| `view` | Componentes visuais da interface, fábricas de elementos de tela e execução do layout. |

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
| :--- | :--- | :--- |
| Java | 21 | Linguagem principal |
| JavaFX | 21 | Interface gráfica (controls, fxml, media) |
| ControlsFX | — | Componentes visuais e notificações |
| JUnit Jupiter | 5.x | Testes unitários |
| Maven | 3.x+ | Build e gerenciamento de dependências |

---

## 📦 Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- **JDK 21** — [Baixar Adoptium](https://adoptium.net/)
- **Maven 3.8+** — [Baixar Maven](https://maven.apache.org/download.cgi)

Para verificar suas versões:

```bash
java -version   # deve exibir: openjdk 21...
mvn -version    # deve exibir: Apache Maven 3.x...
```

---

## 🚀 Como Rodar

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/clinic-priority-queue.git
cd clinic-priority-queue
```

### 2. Compile o projeto

```bash
mvn clean compile
```

### 3. Execute a aplicação

```bash
mvn javafx:run
```

### 4. (Opcional) Gere o JAR distribuível

```bash
mvn package
java -jar target/clinic-priority-queue.jar
```

---

## 🧪 Comandos Principais (Maven)

| Comando | Descrição |
| :--- | :--- |
| `mvn javafx:run` | Inicia a aplicação em ambiente de desenvolvimento |
| `mvn test` | Executa a suíte de testes unitários (JUnit) |
| `mvn clean` | Limpa o diretório `target/` removendo arquivos compilados |
| `mvn package` | Executa os testes e gera o Fat JAR distribuível em `target/` |
| `mvn clean install` | Compilação completa + testes + instalação no repositório local |

---

## 🔮 Melhorias Futuras

- [ ] **Máscara de CPF** no campo de formulário (formatação `000.000.000-00` automática)
- [ ] **Validação de nome** — impedir cadastro com nome vazio ou muito curto
- [ ] **Log de atendimentos** com horário para relatório de turno completo
- [ ] **Persistência de dados** entre sessões com arquivo JSON ou banco H2 embutido
- [ ] **Testes unitários** para `ValidaCPF`, `Lista` e `FilaService`
- [ ] **Exportação de relatório** do turno em PDF ou CSV
- [ ] **Internacionalização (i18n)** para mensagens de erro e labels da interface

---

## 👥 Contribuição

1. Faça um fork do projeto
2. Crie uma branch com a sua feature ou correção:
   ```bash
   git checkout -b feature/minha-feature
   ```
3. Certifique-se de que os testes estão passando:
   ```bash
   mvn test
   ```
4. Faça o commit das suas alterações:
   ```bash
   git commit -m "feat: descrição da minha feature"
   ```
5. Abra um **Pull Request** detalhando suas alterações
