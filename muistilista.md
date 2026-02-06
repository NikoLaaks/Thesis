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