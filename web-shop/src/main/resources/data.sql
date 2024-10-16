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

INSERT INTO KORISNIK (ime,prezime,korisnicko_ime,email_adresa,broj_telefona,lozinka,datum_rodjenja,profilna_slika,opis,uloga,blokiran,prosecna_ocena) VALUES ('Kristijan','Mrkalj','kristijan','email@gmail.com','0670000000','12345678','01-01-1999','url_slike','opis_slike','Kupac',false,4.66);
INSERT INTO KORISNIK (ime,prezime,korisnicko_ime,email_adresa,broj_telefona,lozinka,datum_rodjenja,profilna_slika,opis,uloga,blokiran,prosecna_ocena) VALUES ('Isidor','Ivanov','isidor','email1@gmail.com','0640000000','87654321','01-03-2004','url_slike1','opis_slike1','Prodavac',false,3.66);
INSERT INTO KORISNIK (ime,prezime,korisnicko_ime,email_adresa,broj_telefona,lozinka,datum_rodjenja,profilna_slika,opis,uloga,blokiran,prosecna_ocena) VALUES ('Stefan','Stefanovic','stefan','email2@gmail.com','0650000000','31351251','21-06-2003','url_slike2','opis_slike2','Administrator',false,2.6);
INSERT INTO KORISNIK (ime,prezime,korisnicko_ime,email_adresa,broj_telefona,lozinka,datum_rodjenja,profilna_slika,opis,uloga,blokiran,prosecna_ocena) VALUES ('Nemanja','Radic','neca','email3@gmail.com','0650000000','313512521','21-06-2002','url_slike3','opis_slike3','Kupac',false,4.32);



INSERT INTO KATEGORIJA (naziv)values('Televizori');
INSERT INTO KATEGORIJA (naziv)values('Telefoni');
INSERT INTO KATEGORIJA (naziv)values('Tableti');
INSERT INTO KATEGORIJA (naziv)values('Mali kucni aparatici');

INSERT INTO PROIZVOD  (naziv,opis,slika,cena,tip_prodaje,ostavljena_recenzija_kupac,ostavljena_recenzija_prodavac,prodat,kategorija_id,prodavac_id,datum_objavljivanja,kupac_id) values ('LG G500','televizor u boji','slika1',5000,'Fiksna_Cena',false,false,true,1,1,current_date,1);
INSERT INTO PROIZVOD  (naziv,opis,slika,cena,tip_prodaje,ostavljena_recenzija_kupac,ostavljena_recenzija_prodavac,prodat,kategorija_id,prodavac_id,datum_objavljivanja,kupac_id) values ('Iphone 11','telefon','slika2',2000,'Aukcija',true,false,false,1,1,current_date,1);
INSERT INTO PROIZVOD  (naziv,opis,slika,cena,tip_prodaje,ostavljena_recenzija_kupac,ostavljena_recenzija_prodavac,prodat,kategorija_id,prodavac_id,datum_objavljivanja,kupac_id) values ('Samsung S23','telefon','slika3',3000,'Fiksna_Cena',false,false,true,1,1,current_date,4);
INSERT INTO PROIZVOD  (naziv,opis,slika,cena,tip_prodaje,ostavljena_recenzija_kupac,ostavljena_recenzija_prodavac,prodat,kategorija_id,prodavac_id,datum_objavljivanja,kupac_id) values ('Samsung S23','telefon','slika3',3000,'Fiksna_Cena',false,false,true,1,1,current_date,2);

/*
INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id,kupljeni_proizvodi_id) values (1,1);
INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id,kupljeni_proizvodi_id) values (2,2);
INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id,kupljeni_proizvodi_id) values (3,3);
*/
/*
INSERT INTO KORISNIK_PROIZVODi_NA_PRODAJU (prodavac_id,proizvodi_na_prodaju_id) values (1,1);
INSERT INTO KORISNIK_PROIZVODi_NA_PRODAJU (prodavac_id,proizvodi_na_prodaju_id) values (2,2);
INSERT INTO KORISNIK_PROIZVODi_NA_PRODAJU (prodavac_id,proizvodi_na_prodaju_id) values (3,3);
*/
INSERT INTO PONUDA (cena,kupac_id,proizvod_id) values (1000,1,2);
INSERT INTO PONUDA (cena,kupac_id,proizvod_id) values (2000,1,1);
INSERT INTO PONUDA (cena,kupac_id,proizvod_id) values (3000,3,3);
INSERT INTO PONUDA (cena,kupac_id,proizvod_id) values (3000,2,1);

INSERT INTO RECENZIJA (datum_recenzije,ocena,komentar,korisnik_id) values (current_date,4.68,'Neki komentar',1);
INSERT INTO RECENZIJA (datum_recenzije,ocena,komentar,korisnik_id) values (current_date,4.54,'Neki komentar1',2);
INSERT INTO RECENZIJA (datum_recenzije,ocena,komentar,korisnik_id) values (current_date,3.68,'Neki komentar2',3);
INSERT INTO RECENZIJA (datum_recenzije,ocena,komentar,korisnik_id) values (current_date,3.68,'Neki komentar2',1);


INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave,odnosi_se_prijava_id,podneo_prijavu_id,razlog_prijave,status_prijave) values (current_date,1,3,'nisu placeni racuni','Podneta');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave,odnosi_se_prijava_id,podneo_prijavu_id,razlog_prijave,status_prijave) values (current_date,2,2,'prekoracenje interneta','Odbijena');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave,odnosi_se_prijava_id,podneo_prijavu_id,razlog_prijave,status_prijave) values (current_date,3,1,'roming','Prihvaćena');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave,odnosi_se_prijava_id,podneo_prijavu_id,razlog_prijave,status_prijave) values (current_date,4,1,'nesto','Prihvaćena');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave,odnosi_se_prijava_id,podneo_prijavu_id,razlog_prijave,status_prijave) values (current_date,3,2,'nesto','Prihvaćena');
/*
INSERT INTO PROIZVOD_PONUDE (proizvod_id,PONUDE_ID) values (1,1);
INSERT INTO PROIZVOD_PONUDE (proizvod_id,PONUDE_ID) values (2,2);
INSERT INTO PROIZVOD_PONUDE (proizvod_id,PONUDE_ID) values (3,3);
*/
/*
INSERT INTO KATEGORIJA_PROIZVODI(kategorija_id,proizvodi_id) values (1,1);
INSERT INTO KATEGORIJA_PROIZVODI(kategorija_id,proizvodi_id) values (2,2);
INSERT INTO KATEGORIJA_PROIZVODI(kategorija_id,proizvodi_id) values (3,3);
*/