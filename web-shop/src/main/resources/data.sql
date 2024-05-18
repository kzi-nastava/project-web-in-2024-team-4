INSERT INTO COMPANY (name, address) VALUES ('FTN', 'Trg Dositeja Obradovića 6');

INSERT INTO DEPARTMENT (name, company_id) VALUES ('Menadžment', 1);
INSERT INTO DEPARTMENT (name, company_id) VALUES ('Računarski centar', 1);

INSERT INTO EMPLOYEE (first_name, last_name, position, department_id) VALUES ('Aleksandar', 'Aleksić', 'radnik', 1);
INSERT INTO EMPLOYEE (first_name, last_name, position, department_id) VALUES ('Milica', 'Milić', 'menadžer', 1);
INSERT INTO EMPLOYEE (first_name, last_name, position, department_id) VALUES ('Slavica', 'Slavić', 'radnik', 2);

INSERT INTO PROJECT (name, deadline) VALUES ('Biću student FTN', '2019-02-10');
INSERT INTO PROJECT (name, deadline) VALUES ('Podizanje sistema', '2019-05-1');

INSERT INTO WORKING (project_id, employee_id) VALUES (1, 1);
INSERT INTO WORKING (project_id, employee_id) VALUES (1, 2);
INSERT INTO WORKING (project_id, employee_id) VALUES (2, 3);

INSERT INTO KATEGORIJA (naziv) VALUES ('Alat');
INSERT INTO KATEGORIJA (naziv) VALUES ('IT');
INSERT INTO KATEGORIJA (naziv) VALUES ('Namestaj');
INSERT INTO KATEGORIJA (naziv) VALUES ('Elektronika');

INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (false, current_date, 5, 1, '04321351', 'Nemanja', 'nekimail@gmail.com', 'top g', 'lozinkolozic', 'Radic', 'url', 'Nemanja12');
INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (true, current_date, 4.78, 1, '063283921', 'Ivan', 'nekimail1@gmail.com', 'lep decko1', 'lozinka341', 'Ristic', 'url', 'Ivan123');
INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (false, current_date, 3.65, 1, '061801294', 'Isidor', 'nekimail2@gmail.com', 'lep decko2', 'lozinka123', 'Ivanov', 'url', 'Isidor3');
INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (false, current_date, 0.0, 2, '0663476437', 'Admin', 'admin@gmail.com', 'Najbolji admin', 'admin123', 'Adminic', 'url', 'IsidorAdmin');



INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (34500, current_date, 2, 'AUKCIJA', 'Ugaona garnitura', 'Simpo ugaona garnitura', 'url', 2, true, true, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (5000, current_date, 1, 'FIKSNA_CENA', 'RAM', '16GB DDR5 6400MHz', 'url', 1, true, true, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (5000, current_date, 2,'FIKSNA_CENA', 'Procesor', 'Intel® Core™ i5-12500H Processor up to 4.50 GHz', 'url', 1, false, true, true);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 3, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'url', 2, false, false, true);

INSERT INTO RECENZIJA (datum_recenzije, ocena, podnosilac_id, komentar) VALUES (current_date, 5, 1, 'svaka cast');
INSERT INTO RECENZIJA (datum_recenzije, ocena, podnosilac_id, komentar) VALUES (current_date, 3, 1, 'dobro odradjen posao');
INSERT INTO RECENZIJA (datum_recenzije, ocena, podnosilac_id, komentar) VALUES (current_date, 4, 1, 'bravo');

/*
INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id, kupljeni_proizvodi_id) VALUES (2, 1);
INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id, kupljeni_proizvodi_id) VALUES (2, 2);
INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id, kupljeni_proizvodi_id) VALUES (2, 4);
*/
-- INSERT INTO KORISNIK_RECENZIJE ()
-- INSERT INTO KORISNIK_RECENZIJE ()
-- INSERT INTO KORISNIK_RECENZIJE ()

INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'PODNETA', 2, 1, 'tako mi doslo');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'ODBIJENA', 3, 2, 'neadekvatan recnik');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'PRIHVACENA', 2, 3, 'vredja');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'PRIHVACENA', 1, 2, 'vredja');

INSERT INTO PONUDA (cena, kupac_id) VALUES (5000, 1);
INSERT INTO PONUDA (cena, kupac_id) VALUES (12345, 1);
INSERT INTO PONUDA (cena, kupac_id) VALUES (8300, 2);
INSERT INTO PONUDA (cena, kupac_id) VALUES (2300, 2);

INSERT INTO PROIZVOD_PONUDA (ponuda_id, proizvod_id) VALUES (2, 4);
INSERT INTO PROIZVOD_PONUDA (ponuda_id, proizvod_id) VALUES (4, 3);
INSERT INTO PROIZVOD_PONUDA (ponuda_id, proizvod_id) VALUES (1, 1);
INSERT INTO PROIZVOD_PONUDA (ponuda_id, proizvod_id) VALUES (3, 2);