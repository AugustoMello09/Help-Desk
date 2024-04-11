# Help Desk 🆘

### Projeto Full Stack

O Projeto HelpDesk é uma aplicação web desenvolvida utilizando as tecnologias Java, Spring Boot 3+ e Angular.O objetivo do projeto é fornecer um sistema no qual os usuários possam abrir chamados e serem atendidos por um técnico, as mudanças de status do chamado são enviadas por e-mail, informando o cliente sobre a aceitação e conclusão. O projeto conta com segurança do Spring Security, geração de tokens com JWT, testes unitários e integração com JUnit 5. Este ano, iniciei meus estudos em Cloud com foco na AWS e nada melhor do que colocar em prática alguns conceitos. Por isso, escolhi desenvolver esta aplicação simples para auxiliar nos estudos e compreender como funciona o processo de deploy na AWS, utilizando S3, RDS e Elastic Beanstalk.


https://github.com/AugustoMello09/Locadora/assets/101072311/044efd75-4dbc-425b-9b06-4e4dd98a7221

### UML 🧱

![Screenshot_2](https://github.com/AugustoMello09/Help-Desk/assets/101072311/3f9a3b3a-a648-4439-bc02-501336f888db)

### Funcionalidades Principais como Cliente 👷‍♀️

__Criando conta__: Os usuários podem se cadastrar no Help Desk.

__Criando chamado__: Os usuários podem criar chamados e descreverem seus problemas `🟢 ABERTO`, após aberto o chamado o usuário tem que aguardar até que seja aceito por um técnico.

### Funcionalidades Principais como Técnico 🛠️

 __Criando conta__: Os usuários podem se cadastrar no Help Desk.

__Listar Chamados__: O Técnico pode listar todos os chamados de acordo com seu StatusChamdo `🟢 ABERTO`, `🟡 ANDAMENTO` e `🔴 FECHADO`.

__Aceitando chamado__: O Técnico aceita o chamado, a aplicação envia uma email avisando que seu chamado foi aceito, trocando o status do chamado para `🟡 ANDAMENTO`.

__Finalizando chamado__: Após o técnico resolver o problema é enviado um email para o cliente informando que foi resolvido e seus status é alterado para `🔴 FECHADO`.

### AWS ☁️

O Projeto HelpDesk foi desenvolvido com o intuito de explorar e aplicar conceitos da AWS. Utilizando as tecnologias Java, Spring Boot 3+ e Angular, o projeto foi implantado na infraestrutura da AWS para fornecer uma solução escalável e segura para seus usuários.

##### Fluxo 🔄

![Screenshot_12](https://github.com/AugustoMello09/Locadora/assets/101072311/9911b1bd-c281-4fa2-bc56-013580d47ed7)

- S3 (Simple Storage Service): Armazena o frontend da aplicação, ou seja, todos os arquivos necessários para o usuário interagir com a interface.

- Beanstalk: É onde o backend da aplicação está hospedado. Aqui está o código do servidor que processa as requisições do usuário e fornece as respostas necessárias.

- RDS (Relational Database Service): É onde os dados da aplicação são armazenados de forma segura e organizada. Aqui está o banco de dados MySQL que o backend acessa para recuperar ou armazenar informações.

### Documentação com Swagger 📗

-  Documentação do nosso sistema de halp desk usando Swagger, proporcionando uma visão unificada e acessível.

![Screenshot_1](https://github.com/AugustoMello09/Help-Desk/assets/101072311/0f832148-9a20-4bf3-8b9b-db57ce8de508)

https://github.com/AugustoMello09/Help-Desk/assets/101072311/514100d3-13b1-447f-a15c-203c5441948f

__Acesse a documentação do projeto:__ (Local)

- __Acesse:__ http://localhost:8080/swagger-ui.html para visualizar os endpoints.


### Ferramentas e Tecnologias usadas no Backend 🧱

<div style="display: inline_block"><br>

<img align="center" alt="Augusto-Java" height="60" width="60" src=https://github.com/devicons/devicon/blob/master/icons/java/java-original.svg >
<img align="center" alt="Augusto-SpringBoot" height="70" width="70" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/spring/spring-original-wordmark.svg">
<img align="center" alt="Augusto-MYSQL" height="60" width="60"
src= https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/mysql/mysql-original.svg>
<img align="center" alt="Augusto-jwt" height="50" width="50"
src="https://img.icons8.com/?size=512&id=rHpveptSuwDz&format=png">
<img align="center" alt="Augusto-Swagger" height="40" width="40" src="https://github.com/AugustoMello09/Locadora/assets/101072311/a895137a-8126-4eed-8a5c-9934ed30401b">
<img align="center" alt="Augusto-Docker" height="70" width="70" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/docker/docker-original.svg">


</div>

### Ferramentas e Tecnologias usadas no Frontend 🎨

<div style="display: inline_block"><br>

<img align="center" alt="Augusto-HTML" height="50" width="50" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/html5/html5-plain.svg">
<img align="center" alt="Augusto-CSS" height="50" width="50" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/css3/css3-original.svg">
<img align="center" alt="Augusto-JAVASCRIP" height="50" width="50" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/javascript/javascript-plain.svg">
<img align="center" alt="Augusto-TYPESCRIPT" height="60" width="60" src="https://img.icons8.com/?size=512&id=nCj4PvnCO0tZ&format=png">
<img align="center" alt="Augusto-ANGULAR" height="50" width="50" src="https://raw.githubusercontent.com/get-icon/geticon/fc0f660daee147afb4a56c64e12bde6486b73e39/icons/angular-icon.svg">


</div>

## Execute o projeto 👁‍🗨

### Backend 🧱

__Pré-requisitos:__ Java 17, Docker(opcional)

__Clone o repositório do projeto__


` git clone https://github.com/AugustoMello09/Help-Desk.git `


### Configurando o projeto local 🏠

__Configurando o ambiente:__

- Navegue até o diretório do projeto.

```bash
cd HelpDesk-Backend
```

- Acesse o diretório do projeto, utilize o comando mvn install para instalar todas as dependências necessárias:

```bash
# exemplo
cd HelpDesk-Backend

./mvnw install -DskipTests=true
```

- Abra o arquivo de configuração application.yml.

- Modifique o perfil que deseja rodar a aplicação.

__Perfil de teste__

```bash
profiles:
  active:
  - test
```

__Perfil de Dev__

```bash
profiles:
  active:
  - dev
```

#### Usando a aplicação no perfil Test 🧪

- Não é necessário nenhuma modificação no projeto, certifique-se que esteja no perfil de test.

__Perfil de teste__: application-test.yml

```bash
profiles:
  active:
  - test
```

- Suba a aplicação e acesse o H2.

- __Acesse:__ http://localhost:8080/h2-console para visualizar o H2.

![Screenshot_9](https://github.com/AugustoMello09/Locadora/assets/101072311/4da42f1b-20ae-4236-9694-16d197cd9e9b)

- Tudo pronto e funcionando.

![Screenshot_10](https://github.com/AugustoMello09/Locadora/assets/101072311/3159039d-83fc-4326-b55f-c75d484bf73d)



#### Usando a aplicação no perfil Dev 🏛️

- __AVISO ⚠️__: para rodar no modo dev faça as modificações necessárias no application-dev.yml.

```bash
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/helpdesk
    username: username
    password: senha
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
```

- Antes de supir a aplicação prepare o banco e certifique-se que esteja no perfil de dev.

__Perfil de Dev__: application-dev.yml

```bash
profiles:
  active:
  - dev
```

![ban](https://github.com/AugustoMello09/Help-Desk/assets/101072311/75163da6-0cc9-45f6-8788-c0c2296bd6c5)

- Suba a aplicação e logo depois será criado automaticamente o banco.

![ol](https://github.com/AugustoMello09/Help-Desk/assets/101072311/b9d931c7-a347-41a2-a448-13fcd4571c88)

- faça um INSERT na tabela tb_cargo.

__COMANDO__:

```bash
INSERT INTO tb_cargo (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_cargo (authority) VALUES ('ROLE_ADMIN');
```

![prep](https://github.com/AugustoMello09/Help-Desk/assets/101072311/8a867e19-cffb-4630-8534-36d3195b5884)

- Feito o INSERT na tabela tb_cargo já pode começar a usar.

![result](https://github.com/AugustoMello09/Help-Desk/assets/101072311/46d7fe6a-db6b-46b2-bc46-66c2fa71455d)


### Configurando o projeto para usar Docker-compose 🐳

- __AVISO ⚠️__: para subir o docker-compose usando a imagem `augustomello09/help-desk-backend:latest` o username e password tem que ser root no mySQL. Caso não seja vou mostrar como subir uma nova img e alterar no docker-compose.

##### Alterando a img docker (Caso credencial não for compatível com password e username root).

- Navegue até o local do arquivo e faça modificações necessárias no application-dev.yml com sua credencial do mySQL.

![Screenshot_3](https://github.com/AugustoMello09/Locadora/assets/101072311/e4925368-1701-48ac-bca3-76fdc4e11f5d)

- Certifique-se que esteja no perfil Dev.

![Screenshot_4](https://github.com/AugustoMello09/Locadora/assets/101072311/2c56e4f4-072c-4dc7-9c69-2b6a155ef45c)

- Depois das alterações navegue até o diretório do projeto HelpDesk-Backend e empacote o Jar.

`./mvnw clean peckage -DskipTests=true`

- Gere o build da img do docker (Lembre de tirar os {}).

`docker build -t {NomeDaNovaImg}:{teg} .`

- Depois de todas as modificações e com a img nova, abra o docker-compose e faça as ultimas alterações com a nova img e sua credencial do mySQL.

![Screenshot_5](https://github.com/AugustoMello09/Locadora/assets/101072311/6c8bb6df-533e-47ec-93cf-29eaff26c95d)

- Agora com tudo pronto suba o docker-compose: `docker-compose up -d` ( Todas as modificações foram feitas no diretório HelpDesk-Backend).

![Screenshot_6](https://github.com/AugustoMello09/Locadora/assets/101072311/1b684dd5-9659-4178-8c47-77eb8c584e40)

#### Executando o Docker-compose

- Navegue até o local do arquivo docker-compose.yml e suba.

```bash
cd helpdesk

docker-compose up -d
```

- __Acesse:__ http://localhost:8000 para visualizar o phpMyAdmin.

![php](https://github.com/AugustoMello09/Help-Desk/assets/101072311/ff49613f-58f7-4993-b280-6609e239f88a)

- Abra o banco helpdesk.

![Screenshot_7](https://github.com/AugustoMello09/Locadora/assets/101072311/a76845f7-1553-41d2-b251-ca49665f344f)

- Execute a Query na tabela de tb_cargo.

__COMANDO__:

```bash
INSERT INTO tb_cargo (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_cargo (authority) VALUES ('ROLE_ADMIN');
```

![Screenshot_8](https://github.com/AugustoMello09/Locadora/assets/101072311/700c021b-7e5b-41b0-81c3-5785a00b3dcb)

- Tudo pronto, agora é só usar.

### Configurando o projeto na AWS ☁️ (Opcional)

- __AVISO ⚠️__: Esta seção de implantação na AWS é apenas informativa e não está refletida na última versão da aplicação HelpDesk, você poderá rodar a aplicação local.

##### Antes de começar

- Crie uma conta na AWS

#### Passos para Implantação

1. **Preparação do AWS S3**:

  - Acesse o console da AWS -> S3 ;

  - Crie um novo bucket

![nomebu](https://github.com/AugustoMello09/Locadora/assets/101072311/e92f9105-8af7-46d9-896c-582117f71a85)

  - Acesse as permissões do bucket:

  - Gerenciar políticas públicas do bucket;

  ![puli](https://github.com/AugustoMello09/Locadora/assets/101072311/14ac78a8-3048-440a-8dd5-dc2e42e8926a)

  - Acesse as propriedades do bucket:

  - Selecione a opção para utilizar o bucket como website host;

  ![site sta](https://github.com/AugustoMello09/Locadora/assets/101072311/828ebb5d-528b-48ee-9fa1-b772e1788e8c)

`S3 URL: [nome-do-bucket].s3-website.[zona-do-bucket].amazonaws.com`

![Screenshot_1](https://github.com/AugustoMello09/Locadora/assets/101072311/3e231c50-cea8-4775-a0d1-fb105c22befc)

2. **Configuração do AWS RDS**:

   - Crie um banco no RDS.

   ![Screenshot_16](https://github.com/AugustoMello09/Locadora/assets/101072311/064755bb-a8d5-449c-b6f2-b77c49086835)

   - Escolha o MySQL.

   ![Screenshot_17](https://github.com/AugustoMello09/Locadora/assets/101072311/2d381098-1e8b-4588-a5ac-45f21e2b92f3)

   - Modelos escolha free tier.

   ![Screenshot_18](https://github.com/AugustoMello09/Locadora/assets/101072311/3ae6935c-8209-4e2d-896f-2bc36c348971)

   - Configure o nome identificador, username e password do db.

   ![Screenshot_19](https://github.com/AugustoMello09/Locadora/assets/101072311/3b9309b8-99b6-4df0-b6d7-03b095b01edb)

   - Deixe somente habilitada a opção marcada e use `db.t3.micro`.

   ![Screenshot_20](https://github.com/AugustoMello09/Locadora/assets/101072311/98917e40-4c05-478d-91a7-dc006f91d433)

   - Desabilite a opção de escalabilidade.

   ![Screenshot_21](https://github.com/AugustoMello09/Locadora/assets/101072311/0232db84-333f-4eb4-832f-a2dbe4d25e4a)

   - Escolha o nome do banco de dados. (Recomendo usar o mesmo do id instância)

   ![Screenshot_22](https://github.com/AugustoMello09/Locadora/assets/101072311/fc8e4894-c671-4baf-b306-b24aa297e419)

   - Crie o banco.

3. **Configuração do Backend (Spring Boot)**:
   - Configure seu aplicativo Spring Boot para usar o banco de dados RDS (MySQL).

    1. Crie um application-aws.yml

    2. configure para acessar o RDS.
      ```bash
      spring:
        datasource:
          url: jdbc:mysql://{url}:3306/{nomedb}
          username: (username)
          password: (senha)
        jpa:
          hibernate:
            ddl-auto: update
          show-sql: false
      ```
   - Confira se está usando o perfil da aws no application.yml
   e também coloque a porta como 5000.
       ```bash
       server:
         port: 5000
       profiles:
         active:
         - aws
       ```
   - Abra o mySQL no meu caso estou usando o HeidiSQL como exemplo.

   ![Screenshot_24](https://github.com/AugustoMello09/Locadora/assets/101072311/79be4973-4da7-4336-bdd5-973fda015d75)

   - Acesse o banco.

   ![Screenshot_25](https://github.com/AugustoMello09/Locadora/assets/101072311/7a22dd86-5f9f-4445-94f7-6e51499992e7)

   - Suba a aplicação e certifique que está usando o perfil correto.

   ```bash
   profiles:
     active:
     - aws
   spring:
     datasource:
       url: jdbc:mysql://{url}:3306/{nomedb}
       username: (username)
       password: (senha)
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: false
   ```
   - Atualize a tabela.

   ![Screenshot_26](https://github.com/AugustoMello09/Locadora/assets/101072311/ba356730-dfdd-441a-91f0-82a254413d69)

   - faça um INSERT na tabela tb_cargo.

   __COMANDO__:

   ```bash
   INSERT INTO tb_cargo (authority) VALUES ('ROLE_OPERATOR');
   INSERT INTO tb_cargo (authority) VALUES ('ROLE_ADMIN');
   ```

   ![prep](https://github.com/AugustoMello09/Help-Desk/assets/101072311/8a867e19-cffb-4630-8534-36d3195b5884)

   - Empacote seu aplicativo em um arquivo JAR executável:
     ```bash
     ./mvnw clean package -DskipTests=true
     ```

    ![Screenshot_27](https://github.com/AugustoMello09/Locadora/assets/101072311/817a9fc1-fe73-416b-9981-0899769c7b5d)   

4. **Implantação no Elastic Beanstalk**:

  __ANTES DE CRIAR UM NOVO AMBIENTE NO ELASTIC BEANSTALK__:

  - Acesse IAM

    1. Crie um perfil.

    ![Screenshot_4](https://github.com/AugustoMello09/Locadora/assets/101072311/0ad31e17-2f77-4cd0-a160-d9fe0c7c44bb)

    2. Selecione as opções.

    ![Screenshot_5](https://github.com/AugustoMello09/Locadora/assets/101072311/ecb7b0d0-ddc8-4c14-b857-9684ab758d65)

    3. __ACESSE:__ https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/concepts-roles-instance.html

    ![Screenshot_6](https://github.com/AugustoMello09/Locadora/assets/101072311/6a2db4d7-b9fb-41db-b3ff-3ebbd382da0b)

    5. Adicione as 3 permissões.

    ![Screenshot_7](https://github.com/AugustoMello09/Locadora/assets/101072311/99aa5a41-87d7-4799-8080-45a7806e220e)

    4. Escolha um nome para usar.

    ![Screenshot_8](https://github.com/AugustoMello09/Locadora/assets/101072311/153dc80d-f150-483b-a401-9ba28ba89fc5)

    6. verifique se foram adicionados as permissões.

    ![Screenshot_9](https://github.com/AugustoMello09/Locadora/assets/101072311/fe96c705-5311-4bbc-baac-1cbc7c6f2be1)

   - Agora Crie um novo ambiente no Elastic Beanstalk para seu aplicativo.

   ![Screenshot_10](https://github.com/AugustoMello09/Locadora/assets/101072311/cc628b34-fe37-4cf5-a781-285c8ea06311)

   - Escolha o ambiente e o nome da aplicação.

   ![Screenshot_11](https://github.com/AugustoMello09/Locadora/assets/101072311/b15c57bc-0dc9-4b9d-a6d1-50ae85516c21)

   - Escolha a plataforma, e marque as primeiras opções de código da aplicação e predefinições.

   ![Screenshot_12](https://github.com/AugustoMello09/Locadora/assets/101072311/136c48e6-a702-4135-ac55-9d07fb2bd6c3)

   - Na configuração de acesso ao serviço use o perfil criado antes de começar a implantação do Beanstalk.

   ![Screenshot_13](https://github.com/AugustoMello09/Locadora/assets/101072311/237775b8-1e4f-4e5c-a46b-c8440cd55093)

   - Coloque as credenciais usadas na criação do RDS com username e password.

   ![Screenshot_14](https://github.com/AugustoMello09/Locadora/assets/101072311/169a6e35-4947-4472-8735-8419ea462141)

   - Crie a aplicação.

   - Faça o upload do jar empacotado

   ![Screenshot_24](https://github.com/AugustoMello09/Locadora/assets/101072311/66c55194-d3f0-4425-b613-972dd4c56c9b)

   5. **Preparação do frontend (Angular)**:
       - Acesse o diretório do HelpDesk-frontend

       ```bash
       cd helpdesk-frontend
       ```

       - Acesse `environment.prot.ts` e coloque a url gerada pelo Beanstalk.

       ```bash
        export const environment = {
          production: true,
          baseUrl: 'http://{url}.com'
        };
       ```

       - Compile seu projeto Angular para produção:

        ```bash
        ng build --configuration=production
        ```
       - Faça upload dos arquivos gerados para um bucket no Amazon S3.

      ![Screenshot_3](https://github.com/AugustoMello09/Locadora/assets/101072311/98882fb9-da14-4249-9521-6b224caad23b)

      - Agora é só acessar a url do S3 e testar.

### Frontend 🌐

__Pré-requisitos:__ Angular

__Executar__

- Certifique-se de ter o Node.js e o Angular CLI instalados em seu ambiente.
- Navegue até a pasta do projeto front-end:

```bash
cd helpdesk/helpdesk-frontend
```

__Instale as dependências do projeto:__

```bash
npm install
 ```

__Inicie a aplicação:__

```bash
ng serve
```

### Entre em contato  

Para mais informações sobre o projeto ou para entrar em contato, você pode me encontrar através dos canais abaixo:

<div style="display: inline_block">

  <a href="https://www.linkedin.com/in/jos%C3%A9-augusto-mello-794a94234" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
 <a href="mailto:joseaugusto.Mello01@gmail.com" target="_blank"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>   

</div>
