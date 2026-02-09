1. Entity (Tietomalli)
    - Edustaa sovelluksen dataa ja tietokannan rakennetta
    - Jokainen entity luokka vastaa yhtä tietokantataulua. Luokan kentät vastaavat taulun sarakkeita.
    - Luokan päällä on annotaatio @Entity
2. Repository (Tietokantayhteys)
    - Hoitaa yhteyden tietokantaan (tallennus, haku, päivitys, poisto)
    - Interface, joka toimii siltana Javan ja tietokannan välillä. Spring Data JPA osaa automaattisesti luoda kyselyt, joten niitä ei tarvitse kirjoittaa.
    - Perii (extends) JpaRepository-luokan.
3. Controller (Rajapinta ulospäin)
    - Ottaa vastaan HTTP-pyynnöt ja palauttaa vastaukset.
    - Määrittää endpointit
    - Välittää pyyntöjä eteenpäin ja palauttaa vastauksia kyselyihin
    - Luokan päällä on annotaatio @RestController

## Auth-endpointit
| Endpoint | Metodi | Rooli | Selitys
|----------|--------|------|
| /api/auth/login | POST | - | Kirjautuminen, palauttaa JWT tokenin

## User-endpointit
| Endpoint | Metodi | Rooli | Selitys
|----------|--------|------|
| /api/admin/users | GET | ADMIN | kaikkien käyttäjien haku
| /api/admin/users | POST | ADMIN | uuden käyttäjän lisäys
| /api/admin/users | DELETE | ADMIN | käyttäjän poistaminen

## Task-endpointit
| Endpoint | Metodi | Rooli | Selitys
|----------|--------|------|
| /api/tasks | GET | TEACHER | kaikkien taskien haku
| /api/tasks | POST | TEACHER | uuden taskin lisäys
| /api/tasks/{id} | GET | TEACHER | taskin haku id:n perusteella
| /api/tasks/{id} | DELETE | TEACHER | taskin poisto id:n perusteella

## Answer-endpointit
| Endpoint | Metodi | Rooli | Lisäehto | Selitys
|----------|--------|------|------------|
| /api/tasks/{taskId}/answers | POST | STUDENT | - | vastauksen lisääminen taskiin id:n perusteella
| /api/answers/{id} | GET | STUDENT | vain oma vastaus | hae yksittäinen vastaus
| /api/answers/{id} | PUT | STUDENT | vain oma vastaus | muokkaa omaa vastausta
| /api/answers/my | GET | STUDENT | - | hae kaikki omat vastaukset
| /api/tasks/{taskId}/answers | GET | TEACHER | vain omat taskit | hae kaikki vastaukset tehtävään