# Clinic Priority Queue

Sistema de gerenciamento de fila de atendimento hospitalar baseado em prioridades, desenvolvido em Java 21 com interface gráfica em JavaFX.

---

## 🏗️ Arquitetura e Estrutura

O projeto utiliza uma model.estrutura modular dividida por responsabilidades, implementando uma **Lista Duplamente Encadeada** própria para a gestão das regras de negócio.

![Arquitetura do Sistema](image.png)

```text
hospital-fila/
├── src/main/java/
│   ├── model/
│   │   ├── Paciente.java              # Entidade do domínio
│   │   ├── No.java                    # Nó da lista encadeada
│   │   └── TipoPrioridade.java        # Enum (PRIORIDADE, PREFERENCIAL, COMUM)
│   ├── model.estrutura/
│   │   └── ListaDuplamenteEncadeada.java # Estrutura de dados customizada
│   ├── service/
│   │   └── FilaService.java           # Regras de negócio da fila
│   └── view/
│       ├── TelaRecepcao.java          # Entrada de pacientes
│       ├── PainelChamada.java         # Painel visual/sonoro de chamadas
│       └── TelaAdmin.java             # Monitoramento e gestão
└── pom.xml                            # Configuração Maven e dependências
```
--- 

## 🛠️ Tecnologias Utilizadas

- Java 21

- JavaFX 21 (Módulos: controls, fxml, base, graphics, media)

- ControlsFX (Componentes visuais e notificações)

- JUnit Jupiter (v5) (Testes unitários)

- Maven (Gerenciamento de dependências e build)

---
## 🚀 Comandos Principais (Maven)

Use os comandos abaixo no terminal para interagir com o ciclo de vida do projeto:

| Comando | Descrição |
| :--- | :--- |
| `mvn javafx:run` | Inicia a aplicação em ambiente de desenvolvimento. |
| `mvn test` | Executa a suíte de testes unitários (`JUnit`). |
| `mvn clean` | Limpa o diretório `target/` removendo arquivos compilados. |
| `mvn package` | Executa os testes e gera o **Fat JAR** distribuível em `target/`. |

---

👥 Contribuição

1. Crie uma branch com a sua feature/correção: git checkout -b feature/minha-feature

2. Certifique-se de que os testes estão passando: mvn test

3. Abra um Pull Request detalhando suas alterações.