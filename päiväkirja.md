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
