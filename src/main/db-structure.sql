create database grupi_8_knk;

-- 1. Perdoruesi
CREATE TABLE Perdoruesi (
                            id SERIAL PRIMARY KEY,
                            emri VARCHAR(50) NOT NULL,
                            mbiemri VARCHAR(50) NOT NULL,
                            email VARCHAR(100) UNIQUE NOT NULL,
                            telefoni VARCHAR(20),
                            roli VARCHAR(20) CHECK (roli IN ('Doktor', 'Moter', 'Admin', 'Pacient', 'Praktikant')),
                            hashedpassword TEXT NOT NULL,
                            salt TEXT NOT NULL,
                            krijuar TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE id_seq
    START WITH 230756101
    INCREMENT BY 1
    MINVALUE 230756101
    MAXVALUE 930756100;

ALTER TABLE Perdoruesi ALTER COLUMN id SET DEFAULT nextval('id_seq');

-- 2. Mjeket
CREATE TABLE Mjeket (
                        id SERIAL PRIMARY KEY,
                        emri VARCHAR(20),
                        mbiemri VARCHAR(20),
                        nr_licenses VARCHAR(20),
                        nr_telefoni VARCHAR(20),
                        email VARCHAR(50),
                        specializimi_kryesor VARCHAR(50)
);

-- 3. Specializimet
CREATE TABLE Specializimet (
                               id SERIAL PRIMARY KEY,
                               emri_specializimit VARCHAR(50)
);

-- 4. Sallat
CREATE TABLE Sallat (
                        id SERIAL PRIMARY KEY,
                        emri_salles VARCHAR(50),
                        tipi VARCHAR(50),
                        kapaciteti INT,
                        statusi VARCHAR(20)
);

INSERT INTO Sallat (id, emri_salles, tipi, kapaciteti, statusi)
VALUES (1, 'Salla 1', null, 40, 'E gatshme');

-- 5. MjekuSpecializimi
CREATE TABLE MjekuSpecializimi (
                                   id SERIAL PRIMARY KEY,
                                   mjeku_id INT REFERENCES Mjeket(id) ON DELETE CASCADE,
                                   specializimi_id INT REFERENCES Specializimet(id) ON DELETE CASCADE
);

-- 6. Pacientet
CREATE TABLE Pacientet (
                           id SERIAL PRIMARY KEY,
                           user_id INT UNIQUE REFERENCES Perdoruesi(id) ON DELETE CASCADE,
                           datelindja DATE,
                           gjinia VARCHAR(10) CHECK (gjinia IN ('Mashkull', 'Femer')),
                           adresa TEXT,
                           kontakti_emergjent VARCHAR(100),
                           mjeku_familjar_id INT REFERENCES Mjeket(id) ON DELETE SET NULL
);

-- 7. Sherbimet
CREATE TABLE Sherbimet (
                           id SERIAL PRIMARY KEY,
                           emri_sherbimit VARCHAR(100) NOT NULL,
                           pershkrimi TEXT,
                           cmimi DECIMAL(10,2) NOT NULL CHECK (cmimi >= 0)
);

-- 8. Inventari
CREATE TABLE Inventari (
                           id SERIAL PRIMARY KEY,
                           emri_produktit VARCHAR(100) NOT NULL,
                           sasia INT NOT NULL CHECK (sasia >= 0),
                           furnizuesi VARCHAR(100)
);

-- 9. PajisjetMjekesore
CREATE TABLE PajisjetMjekesore (
                                   id SERIAL PRIMARY KEY,
                                   emri_pajisjes VARCHAR(50),
                                   pershkrimi VARCHAR(100),
                                   sasia INT,
                                   gjendja VARCHAR(50),
                                   salla_id INT REFERENCES Sallat(id) ON DELETE CASCADE
);

-- 10. Faturimi
CREATE TABLE Faturimi (
                          id SERIAL PRIMARY KEY,
                          pacient_id INT REFERENCES Pacientet(id) ON DELETE CASCADE,
                          sherbimi_id INT REFERENCES Sherbimet(id) ON DELETE SET NULL,
                          shuma DECIMAL(10,2) NOT NULL CHECK (shuma >= 0),
                          statusi VARCHAR(20) CHECK (statusi IN ('Ne pritje', 'Paguar', 'Anuluar')),
                          krijuar TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 11. Rekordet
CREATE TABLE Rekordet (
                          id SERIAL PRIMARY KEY,
                          pacient_id INT REFERENCES Pacientet(id) ON DELETE CASCADE,
                          doktor_id INT REFERENCES Perdoruesi(id) ON DELETE SET NULL,
                          diagnoza TEXT,
                          udhezimi TEXT,
                          data_vizites TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 12. Seancat
CREATE TABLE Seancat (
                         id SERIAL PRIMARY KEY,
                         pacient_id INT REFERENCES Pacientet(id) ON DELETE CASCADE,
                         doktor_id INT REFERENCES Perdoruesi(id) ON DELETE SET NULL,
                         data_seances TIMESTAMP NOT NULL,
                         statusi VARCHAR(20) CHECK (statusi IN ('Skeduluar', 'Perfunduar', 'Anuluar')),
                         shenime TEXT
);

-- 13. Orari
CREATE TABLE Orari (
                       id SERIAL PRIMARY KEY,
                       perdoruesi_id INT REFERENCES Perdoruesi(id) ON DELETE CASCADE,
                       fillimi_ndrrimit TIMESTAMP NOT NULL,
                       perfundimi_ndrrimit TIMESTAMP NOT NULL,
                       roli VARCHAR(20) CHECK (roli IN ('Doktor', 'Moter', 'Admin', 'Praktikant'))
);

-- 14. Terminet
CREATE TABLE Terminet (
                          id SERIAL PRIMARY KEY,
                          id_pacient INT REFERENCES Pacientet(id) ON DELETE CASCADE,
                          id_mjek INT REFERENCES Mjeket(id) ON DELETE CASCADE,
                          id_salle INT REFERENCES Sallat(id) ON DELETE CASCADE,
                          data DATE,
                          statusi VARCHAR(20),
                          shenime VARCHAR(100)
);

-- 15. Pagesa
CREATE TABLE Pagesa (
                        id SERIAL PRIMARY KEY,
                        pacient_id INT REFERENCES Pacientet(id) ON DELETE CASCADE,
                        fatura_id INT REFERENCES Faturimi(id) ON DELETE CASCADE,
                        shuma INT,
                        menyra_pageses VARCHAR(20),
                        statusi VARCHAR(20),
                        krijuar DATE
);
