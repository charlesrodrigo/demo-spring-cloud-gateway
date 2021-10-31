# demo-spring-cloud-gateway

Demo de uso do spring cloud gateway

O Spring Cloud Gateway tem como objetivo fornecer uma maneira simples e eficaz de fazer um proxy para APIs e fornecer
questões transversais a elas, como: segurança, monitoramento / métricas e resiliência.

## Iniciando o serviço

mvn spring-boot:run

### Após iniciar o serviço, para acessar executar os endpoints

```
curl --location --request GET 'http://localhost:9999/mock' \--header 'type: 1' 
```

```
curl --location --request GET 'http://localhost:9999/mock' \--header 'type: 2' 
```

```
curl --location --request GET 'http://localhost:9999/mock' \--header 'type: 3'
```