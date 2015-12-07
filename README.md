# fabio-moliveira-cep
CedroBKS

- Linguagem Java: pré-requisito
- Server: Tomcat v7.0

- Hibernate: mapeamento/interação com o banco de dados

- Jersey RESTful WebService (especificação JAX-RS 2.0):  desenvolvimento serviços server e client REST
  - CepServiceClient.java (package services): faz a chamada ao serviço de cadastro (POST), enviando um JSON com as informações do CEP
    - CepGETSer
  - antes do envio, o conteudo do JSON é impresso no console, para simples conferencia
  - Após envio, server retorna um JSON e seu conteudo é no console

Explicação de algumas packages:
- Package services
  - classes para o webservice 
  - CepGETService.java: http://localhost:8080/fabio-moliveira-cep/rest-api/busca/{cep}
  - CepPOSTService.java: recebe uma string formatada de JSON contendo informações do CEP e tenta inserir na base (http://localhost:8080/fabio-moliveira-cep/rest-api/cadastro)

- Package dao
  - CepDAO.java: classe para interação com o banco de dados (insert/findCep/cepExists) 

- Package util
  - CepUtil.java: funções simples para formatar, limpar (tirar "-") e validar o tamanho do CEP
  - HibernateUtil.java: session factory
  - JSONUtil.java: funções para transformar um objeto em JSON (e vice-versa) e aplica regras para inserir um JSON na base (insertJson)
  - hibernate.cfg.xml: configurações para acesso ao banco (username/password devem ser alterados aqui)

- MySQL: pré-requisito
  - Arquivo "cedrobks_t_cep.sql": script para criar o schema 'cedrobks' e a tabela 't_cep' da aplicação

