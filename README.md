# Thesis
Tarkoituksena on tehdä
Ensimmäisessa vaiheessa luodaa REST palvelu ilman Spring Securityä.

1. Tietokannan suunnittelu
   - User-, Role-, Assignment- ja Submission -taulut
   - Roolien päättäminen = ROLE_TEACHER, ROLE_STUDENT, ROLE_ADMIN
2. REST palvelu ilman Spring Securityä
   - Tietokannan teko PostgreSQL tai MySQL
   - JPA-entiteetit
   - CRUD-operaatiot valmiiksi
   - Sovellukseen voi tallentaa ja hakea dataa, mutta tietoturvakerrosta ei ole vielä asennettu
3. Spring Securityn integrointi
   - Lisätään Spring Security työhön(tässä kohtaa kaikki endpointit menevät automaattisesti lukkoon)
   - Autentikointi: luodaan kirjautuminen joka palauttaa JWT tokenin
   - Autorisointi: Määritetään SecurityFilterChain, joka sanoo kuka pääsee mihin endpointtiin
   - Metoditason autorisointi(esim opiskelija saa nähdän vain omat palautuksensa)
4. Mahdollisesti jotain konfiguraatioiden säätöjä
