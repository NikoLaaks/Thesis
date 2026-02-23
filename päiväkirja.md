Kirjaa päivämäärä, mitä teit, mitä havaitsit. 
Mitä ongelmia tuli vastaan ja miten ne ratkaistiin 
Havaintoja ratkaisuista, mikä toimii ja mikä ei 
Reflektointia mikä meni hyvin ja mikä olisi voinut mennä paremmin 

MITÄ, MITEN, MIKSI, MITÄ OPIMME? 

## 5.2
ER kaavio tehty ja löytyy osoitteesta https://dbdiagram.io/d/Opinnaytetyo-6984ad5cbd82f5fce2c781e1
Projekti luotu ja github repo tehty.

## 6.2
Task ja Answer saatu alustavasti toimimaan. 
H2 tietokanta valittu sittenkin käyttöön SQL tietokannan tilalle. H2 konsolia ei saatu toimimaan, mutta tietokanta toimii muuten.
Harkittu myös demo-frontendin unohtamista, koska se veisi liikaa aikaa. 
Tällä hetkellä testaukseen käytetty VSCoden Thunder Clientiä, joka on ollut toimiva.
Elinalta tullut viesti teoriaosuuden tekstistä ja vaatii parantamista. 
Sanasto puuttuu ja tekstissä paljon selittämätöntä ammattisanastoa.
Tekstissä myös sekaisin englantia ja suomea.

## 9.2
Projektista kirjoitettu alustavat endpointit muistilista.md tiedostoon.
Endpointit voivat vielä muuttua, mutta näillä pääsee hyvin alkuun.
Frontendin tämänhetkinen suunnitelma on se, että osa näytetään postmanilla(tai vastaavalla) ja joillekkin osille mahdollisesti tehdään frontend.
Tämä mietitään tarpeen mukaan, että millä tavalla asiat on helpoin esittää ymmärrettävästi.

## 10.2
Projektissa nyt alustavat endpointit koodattu.
Swagger toiminnassa, mutta vaatii JsonIgnoret, jotta saa järkevästi toimimaan.
Ehkä sittenkin helpompi käyttää Thunder Clientia endpointtien testaamiseen.
Kun endpointit testataan, niin muista tarkistaa mikä kaikki näkyy frontendille(esim salasana).

## 11.2
DTO-malli otettu käyttöön kontrollereissa testaamisen helpottamiseksi.
DTO pitää myös tehdä erikseen responseihin, jotta ei palauteta kaikkea tietoa GET endpointeista.

## 12.2
Lisätty puuttuva service kerros ja samalla toimintalogiikka siirretty sinne controllereista. Nyt kerrostus on oikeaoppinen eli entiteetti -> repository -> service -> controller. Ohjelmaa ei ole vielä testattu kunnolla ja kaikki responseDTO:t puuttuu kokonaan.

## 13.2
Lisätty responseille DTO luokat + mapperit ja muutettu hiukan kansiorakennetta siistimmäksi.
Arkkitehtuurivalintoja tehty jo tietoturvaa ajatellen. Tästä on hyvä kirjoittaa jo ennen Spring Securityä ettei tietoturva ole pelkästään jonkun paketin asentamista, vaan kaikki valinnat vaikuttavat siihen.
Myös kerrostaminen itsessään lisää tietoturvaa, koska asiat on eristetty toisistaan.

## 17.2
1. pom.xml tiedostoon lisätty Spring Security riippuvuus. Sen mukana tulee default konfiguraatiot, jotka sulkevat kaikki endpointit ja luo default login sivun. Käyttäjänimenä on user ja kertakäyttöisen salasanan löytää IDE:n terminaalista käynnistyksen jälkeen.
2. Lisätty JJWT riippuvuudet api, impl ja jackson JWT toteutusta varten.
3. Luodaan SecurityConfig, joka tulee olemaan se päätiedosto koko Spring Securityn hallinnalle. Tästä pitää tarkistaa että löytyy varmasti tekstiä jo teoriaosuudesta.
4. Lisätty PasswordEncoder SecurityConfigiin ja lisäksi käyttäjienluonti endpointissa on nyt käytössä Hashaus. Tämä voidaan kuvata yksinkertaisesti että kyseessä ei ole mikään älyttömän vaikea prosessi
5. SecurityFilterChain pitää luoda SecurityConfigiin, jotta saadaan otettua formlogin ja muut defaultjutut pois päältä. Tätä filterchainia tullaan sitten jatkossa muuttamaan oman tarpeen mukaan, mutta tässä tilanteessa vain poistetaan default juttuja

## 18.2
1. Roolit on nyt luotu. Pitää varmistaa teksteistä missä kohtaa rooleista aletaan puhumaan.
2. SecurityFilterChain luotu SecurityConfig tiedostoon, missä avataan nyt kaikki endpointit aluksi
3. Kaikilla tehtävillä ja vastauksilla pitää olla nyt käyttäjä eli null ei kelpaa.

## 19.2
1. Loginille tehty dto:t, service ja controller. JwtService tehty tokenia varten ja secret key löytyy oikeaoppisesti application.properties tiedostosta ja se haetaan @value annotaatiolla sieltä. JWT servicessä metodit tokenin purkamiseen, validointiin ja eri asioiden tarkistamiseen tokenista.
2. Kirjautusessa authenticationManager käyttää authentication provideria ja loadByUsername funktiota käyttäjänimen tarkistamiseen. Sen jälkeen passwordEncoderia salasanan automaattiseen vertailuun. Siitä ei ole omaa koodia vaan se tapahtuu taustalla ja palautuu Authentication object jos ok?

## 20.2
1. JWT filtterissä otetaan tokenista käyttäjänimi. Sen avulla haetaan käyttäjätiedot CustomUserDetailsServicen metodilla. Tarkistetaan onko token valid JWTServicen metodilla ja tämän jälkeen luodaan Authentication olio, johon haetut käyttäjätiedot tallennetaan. Tämä authentication olio tallennetaan sitten SecurityContextHolderiin, jotta se on käytettävissä pääsynhallinnassa koska security tarkistaa sieltä oikeudet. Tallennus tehdään threadiin ja sen elinkaari kestää vain sen pyynnön ajan.

## 22.2
1. Luonti-endpointit vaihdettu toimimaan nyt oikein, eli käyttäjä otetaan Spring Securityn contextista johon jwtfiltteri on sen laittanut. Eli taskia tai answeria luodessa kirjautunut käyttäjä merkitään omistajaksi.

## 23.2
1. Securityconfigissa luotu pääsynhallinta endpointteihin rooleihin perustuen. Tämä hoitaa vain roolien kautta pääsynhallintaa eikä puutu omistajuuteen ollenkaan.
