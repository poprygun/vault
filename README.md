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

_May need to delete ~/.vault-token if error thrown during initialization._

```bash
vault server -config vault.conf
export VAULT_ADDR="http://127.0.0.1:8200"
vault init -key-shares=5 -key-threshold=2
```

>Unseal Key 1: hEdQ7k5JoBfWr62cXesooP5RERhNWcutshzU85gZ0Wmb

>Unseal Key 2: 1WqEhDXVB5w4qZhfZzpc7QltsnBeB6ooNgD3rwYgfo9j

>Unseal Key 3: KRCzEi51zXY/btMiVyQ8U2WaC1JngDTjp0KsnBL+Um50

>Unseal Key 4: eameiU8sXwCEuxMgwTsLFfrZ9nMnhpaeh1QeIPGPHgu9

>Unseal Key 5: /4j2vbzlJEZC8wnHbz9M2Bs//X/YtGZwdR+XKgAFhS3o

>Initial Root Token: a44bf740-97a3-94bc-aba6-1ab85f8c0bb8


```bash

vault unseal hEdQ7k5JoBfWr62cXesooP5RERhNWcutshzU85gZ0Wmb

vault unseal KRCzEi51zXY/btMiVyQ8U2WaC1JngDTjp0KsnBL+Um50

export VAULT_TOKEN="a44bf740-97a3-94bc-aba6-1ab85f8c0bb8"

export VAULT_ADDR="http://127.0.0.1:8200"

vault write secret/vault-access username=app_user password=app_password
vault write secret/vault-access/stage username_stage=app_stage_user password_stage=app_stage_password

vault write secret/application username_common=common_user password_common=common_password
vault write secret/application/stage username_common_stage=common_stage_user password_common_stage=common_stage_password

vault read secret/vault-access

```

## Create generic token to test and add to `bootstrap.yml`

```bash
vault token-create -id="00000000-0000-0000-0000-000000000000" -policy="root"
```

```yaml
spring:
  application:
    name: vault-access
  cloud:
    vault:
      token: 00000000-0000-0000-0000-000000000000
      scheme: http

```



## If Cloud Connectors are not used, following properties need to be set

`spring.cloud.vault.uri=${vcap.services.hashicorp-vault.credentials.address}`

`spring.cloud.vault.token=${vcap.services.hashicorp-vault.auth.token}`

`spring.cloud.vault.generic.backend=${vcap.services.hashicorp-vault.credentials.backend.generic}`





