# Spring Boot Application to Read Values From Vault

Application startup stack trace shows values retrieved from vault.


## Install Vault

```bash
brew install vault
```


## Create Vault configuration file vault.conf

```
backend "inmem" {
}

listener "tcp" {
  address = "0.0.0.0:8200"
  tls_disable = 1
}

disable_mlock = true

```

## Configure Vault

```bash
vault server -config vault.conf
vault init -key-shares=5 -key-threshold=2
```

>Unseal Key 1: hEdQ7k5JoBfWr62cXesooP5RERhNWcutshzU85gZ0Wmb

>Unseal Key 2: 1WqEhDXVB5w4qZhfZzpc7QltsnBeB6ooNgD3rwYgfo9j

>Unseal Key 3: KRCzEi51zXY/btMiVyQ8U2WaC1JngDTjp0KsnBL+Um50

>Unseal Key 4: eameiU8sXwCEuxMgwTsLFfrZ9nMnhpaeh1QeIPGPHgu9

>Unseal Key 5: /4j2vbzlJEZC8wnHbz9M2Bs//X/YtGZwdR+XKgAFhS3o

>Initial Root Token: a44bf740-97a3-94bc-aba6-1ab85f8c0bb8

## Make sure to add Initial Root Taken from above to `bootstrap.yml`

```yaml
spring:
  application:
    name: vault-access
  cloud:
    vault:
      token: a44bf740-97a3-94bc-aba6-1ab85f8c0bb8
      scheme: http

```

```bash

vault unseal hEdQ7k5JoBfWr62cXesooP5RERhNWcutshzU85gZ0Wmb

vault unseal KRCzEi51zXY/btMiVyQ8U2WaC1JngDTjp0KsnBL+Um50

export VAULT_TOKEN="a44bf740-97a3-94bc-aba6-1ab85f8c0bb8"

export VAULT_ADDR="http://127.0.0.1:8200"

vault write secret/vault-access username=demouser password=demopassword

vault read secret/vault-access

```

## If Cloud Connectors are not used, following properties need to be set

`spring.cloud.vault.uri=${vcap.services.hashicorp-vault.credentials.address}`

`spring.cloud.vault.token=${vcap.services.hashicorp-vault.auth.token}`

`spring.cloud.vault.generic.backend=${vcap.services.hashicorp-vault.credentials.backend.generic}`





