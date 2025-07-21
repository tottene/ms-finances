# Clean Architecture - ms-finances

## Camadas do Projeto

### `app`
- Controladores REST (`@RestController`)
- Configurações (`SecurityConfig`, `AwsS3Config`, etc.)
- Entry point da aplicação (`MsFinancesApplication`)

### `application`
- Casos de uso do sistema (`LoginUseCase`, `RegisterIncomeUseCase`, etc.)
- Implementações (`LoginUseCaseImpl`, etc.)
- Define orquestração de fluxos

### `domain`
- Modelos de negócio (`User`, `Income`, `Expense`)
- Enumerações (`Role`)
- Contratos que o domínio depende (ex: `UserRepository`, `StorageService`)

### `infrastructure`
- Implementações de serviços externos
    - `persistence/` → implementações de gateways e JPA
    - `gateway/mapper/` → entidades JPA
    - `security/`, `storage/` → JWT, S3, etc.

## Regras de Dependência
- `app` → depende de `application`, nunca de `infrastructure` diretamente
- `application` → depende de `domain`
- `domain` → independente de tudo
- `infrastructure` → depende de `domain` e `application`