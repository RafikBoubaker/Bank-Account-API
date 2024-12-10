

# Bank Account API
```
## Prérequis

 Java 17+
 Maven

lancement de l'application :

mvn clean install
mvn spring-boot:run
```

### Configuration du Port
Par défaut, l'application tourne sur le port 8080.
Vous pouvez modifier le port dans `application.properties` :
```properties
server.port=8080
```

## Endpoints

### 1. Dépôt
- **URL :** `/api/account/deposit`
- **Méthode :** POST
- **Corps de la requête :**
```json
{
  "amount": 100.00
}
```

### 2. Retrait
- **URL :** `/api/account/withdraw`
- **Méthode :** POST
- **Corps de la requête :**
```json
{
  "amount": 50.00
}
```

### 3. Relevé de Compte
- **URL :** `/api/account/statement`
- **Méthode :** GET
- **Réponse :** Liste des opérations

## Tests avec Postman/cURL

### Dépôt
```bash
curl -X POST http://localhost:8080/api/account/deposit \
     -H "Content-Type: application/json" \
     -d '{"amount": 100.00}'
```

### Retrait
```bash
curl -X POST http://localhost:8080/api/account/withdraw \
     -H "Content-Type: application/json" \
     -d '{"amount": 50.00}'
```

### Relevé de Compte
```bash
curl -X GET http://localhost:8080/api/account/statement
```

## Gestion des Erreurs

- **400 Bad Request :**
    - Montant invalide (négatif)
    - Solde insuffisant pour un retrait

## Contraintes

- Montants uniquement positifs
- Retrait impossible si solde insuffisant


### Notes Importantes
- L'application utilise un compte en mémoire (pas de persistance)
- Les transactions sont stockées en mémoire

