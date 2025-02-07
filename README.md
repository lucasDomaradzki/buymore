
# Buy more API

Projeto fictício de API para e-commerce com nome ficítio de Buy More para demonstração da implementação de uma API feita em modelo arquitetural MVC.

## Pré-requisitos

- Java 17
- Docker Compose version v2.32.4
- Apache Maven 3.8.7

## Installation

- Clonar repositório em local de sua preferência:
```bash
git clone
```
- Realizar instalação/build da aplicação onde foi clonado o código:
```bash
mvn clean install
```
- Rodar comando docker compose para iniciar a aplicação:
```bash
docker compose up -d
```

## Usage

### Accesando  Swagger
Para accesar o swagger abra em seu browser na página:
http://localhost:8080/swagger-ui.html

### Accesando  Banco de dados
O banco de dados MySQL está exposto com os seguintes detalhes:
Nome do banco: buy_more
Usuário: buy_more
Senha: buy_more
Porta: 3306
Host: buy_more-mysql

O modo mais fácil de acessar o banco de dados é através dos comandos abaixo:

Permite acessar o container mysql
```
docker exec -it $(docker ps | grep mysql | awk '{print $1}') sh
```

Acessa banco de dados uma vez logado dentro do container MySQL com o comando acima:
```
docker exec -it $(docker ps | grep mysql | awk '{print $1}') sh
```
Ou simplesment execute o seu comando, sua query com o comando abaixo:
```
docker exec $(docker ps | grep mysql | awk '{print $1}') mysql -vvv -u buy_more -p'buy_more' buy_more -e 'your query here'
```
Exemplo:
```
lucasdom@lucas-domaradzki:~/workspace/buymore$ docker exec $(docker ps | grep mysql | awk '{print $1}') mysql -vvv -u buy_more -p'buy_more' buy_more -e 'select * from product'
mysql: [Warning] Using a password on the command line interface can be insecure.
--------------
select * from product
--------------

+----+-----------------------+----------------+----------+---------------+---------------+----------+--------+----------+---------------------+---------------------+
| id | name                  | starting_price | discount | profit_margin | description   | category | active | comments | created_at          | updated_at          |
+----+-----------------------+----------------+----------+---------------+---------------+----------+--------+----------+---------------------+---------------------+
|  6 | LG Smartphone 50 Edge |         100.00 |     NULL |            10 | Description 1 | NULL     |      1 | NULL     | 2025-02-07 18:48:19 | 2025-02-07 18:48:19 |
+----+-----------------------+----------------+----------+---------------+---------------+----------+--------+----------+---------------------+---------------------+
1 row in set (0.00 sec)

Bye
lucasdom@lucas-domaradzki:~/workspace/buymore$
```
### Accesando  log da API
```
docker compose logs -f buy_more-api
```
### Parando os containers
```
docker stop $(docker ps | awk '{print $1}' | tail -n 2)
```
### Reiniciando os containers
```
docker start $(docker ps | awk '{print $1}' | tail -n 2)
```
**Informação importante: todos os comandos docker devem ser executados na pasta root do projeto ou do repositório que foi clonado, pois os comandos docker compose precisam estar no mesmo nível onde se encontra o aqui docker-compose.yml**

## License

[MIT](https://choosealicense.com/licenses/mit/)