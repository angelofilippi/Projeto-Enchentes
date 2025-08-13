# 🌊 Projeto Enchentes — Back-End

## 📌 Sobre o Projeto
Este repositório contém o **back-end** do **Projeto Enchentes**, uma solução desenvolvida para **monitoramento do nível do rio** em uma cidade que enfrenta problemas recorrentes de alagamento durante fortes chuvas.  
O objetivo principal é **coletar dados em tempo real**, processar informações de risco e **emitir alertas automáticos para os moradores** via telefone, contribuindo para a **prevenção de desastres** e a **segurança da população**.

---

## 🔒 Foco em Segurança
A API foi projetada seguindo **boas práticas de segurança**, incluindo:
- **Autenticação e Autorização** com **JWT (JSON Web Token)**.
- **Hash seguro de senhas** utilizando algoritmos robustos.
- **Controle de permissões (Roles)** para diferentes tipos de usuários.
- **Validação de dados de entrada** para evitar injeções e vulnerabilidades.
- **Configurações sensíveis isoladas** em variáveis de ambiente.

---

## 🛠️ Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **Spring Security**
- **JPA / Hibernate**
- **Flyway** (controle de versão do banco de dados)
- **PostgreSQL**
- **Docker**

---

## 🚀 Funcionalidades Principais
- Registro e login de usuários.
- Gestão de permissões e papéis (roles).
- Coleta e armazenamento de dados do nível do rio.
- Emissão de alertas de risco.
- Histórico de medições e relatórios.

---

## 📂 Estrutura do Projeto
```
src/
 ├── main/
 │   ├── java/        # Código-fonte Java
 │   ├── resources/   # Configurações e migrations
 │
 └── test/            # Testes automatizados
```

---

## ⚙️ Como Executar Localmente

### 1️⃣ Clonar o Repositório
```bash
git clone https://github.com/usuario/projeto-enchentes-backend.git
cd projeto-enchentes-backend
```

### 2️⃣ Configurar Variáveis de Ambiente
Crie um arquivo `.env` ou defina as variáveis necessárias:
```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=enchentes
DB_USER=postgres
DB_PASSWORD=postgres
JWT_SECRET=sua_chave_segura
```

### 3️⃣ Subir o Banco de Dados com Docker
```bash
docker compose up -d
```

### 4️⃣ Rodar a Aplicação
```bash
./mvnw spring-boot:run
```

> 💡 Certifique-se de ter o **JDK 21** instalado e configurado no seu ambiente.

A API estará disponível em: **http://localhost:8080**

---

## 📜 Licença
Este projeto é de uso acadêmico e comunitário. Todos os direitos reservados.

---

💡 *Desenvolvido com foco em segurança e prevenção, para proteger vidas e minimizar danos causados por enchentes.*
