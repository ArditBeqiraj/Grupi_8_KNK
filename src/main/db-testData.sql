-- Insert Perdoruesi
INSERT INTO Perdoruesi (emri, mbiemri, email, telefoni, roli, hashedpassword, salt)
VALUES
    ('Arben', 'Krasniqi', 'arben@example.com', '044123456', 'Doktor', '7a50414f3473724d39394a574941466972706c6e6a79765162494e3956427a3177787046796b57555043673df84b0c0a0c04bd49a3758bf551b9ae47be110e52baafed9d5947a1576d80032f', 'zPAO4srM99JWIAFirplnjyvQbIN9VBz1wxpFykWUPCg='), --arben123
    ('Linda', 'Hoxha', 'linda@example.com', '044654321', 'Moter', '4d66364f70734a70413176726977354d71706f4d6f55434f517a584b5845707834475543532f76397135343d201158d411c356c35e405a6f269b16e295ea4d879a417d7f9893e12a6f9d719c', 'Mf6OpsJpA1vriw5MqpoMoUCOQzXKXEpx4GUCS/v9q54='), --linda123
    ('Elda', 'Berisha', 'elda@example.com', '044987654', 'Pacient', '784248747a4b474c6b676a646c5458513541706b746671374d4a767a2b4e364366526d346a7454315866553dfc9db1f32d6e3a590687af69d2e8d0db84cea60a9d682492d651a757050d861d', 'xBHtzKGLkgjdlTXQ5Apktfq7MJvz+N6CfRm4jtT1XfU='), --elda123
    ('Genti', 'Leka', 'genti@example.com', '044123987', 'Praktikant', '386e4b413462476c554373676d4d2b42497863723665796e62344e36624e7549466b6f37334756717738453de8089096f8e2506c733b260b926622e6bb9e8c8ba2fc776109cdfcff61aaedec', '8nKA4bGlUCsgmM+BIxcr6eynb4N6bNuIFko73GVqw8E='); --genti123

-- Insert Mjeket
INSERT INTO Mjeket (emri, mbiemri, nr_licenses, nr_telefoni, email, specializimi_kryesor)
VALUES
    ('Arben', 'Krasniqi', 'LIC123', '044123456', 'arben@example.com', 'Kardiologji'),
    ('Fatmira', 'Shala', 'LIC456', '044654987', 'fatmira@example.com', 'Dermatologji');

-- Insert Specializimet
INSERT INTO Specializimet (emri_specializimit)
VALUES
    ('Kardiologji'),
    ('Dermatologji'),
    ('Neurologji');

-- Insert Sallat
INSERT INTO Sallat (emri_salles, tipi, kapaciteti, statusi)
VALUES
    ('Salla 1', 'Operacioni', 40, 'E gatshme'),
    ('Salla 2', 'Konsultimi', 20, 'E gatshme');

-- Insert MjekuSpecializimi
INSERT INTO MjekuSpecializimi (mjeku_id, specializimi_id)
VALUES
    (1, 1), -- Arben - Kardiologji
    (2, 2); -- Fatmira - Dermatologji

-- Insert Pacientet
INSERT INTO Pacientet (user_id, datelindja, gjinia, adresa, kontakti_emergjent, mjeku_familjar_id)
VALUES
    (3, '1990-05-15', 'Femer', 'Rruga 1, Prishtine', '049123456', 1);

-- Insert Sherbimet
INSERT INTO Sherbimet (emri_sherbimit, pershkrimi, cmimi)
VALUES
    ('Kontroll Mjekesor', 'Kontroll i përgjithshëm', 30.00),
    ('EKG', 'Elektrokardiogramë', 50.00);

-- Insert Inventari
INSERT INTO Inventari (emri_produktit, sasia, furnizuesi)
VALUES
    ('Pajisje EKG', 5, 'Firma X'),
    ('Sterilizues', 2, 'Firma Y');

-- Insert PajisjetMjekesore
INSERT INTO PajisjetMjekesore (emri_pajisjes, pershkrimi, sasia, gjendja, salla_id)
VALUES
    ('Pajisje EKG Model A', 'Pajisje per matje zemre', 3, 'Ne gjendje te mire', 1),
    ('Sterilizues Model B', 'Sterilizues per instrumenta', 2, 'Ne riparim', 2);

-- Insert Faturimi
INSERT INTO Faturimi (pacient_id, sherbimi_id, shuma, statusi)
VALUES
    (1, 1, 30.00, 'Paguar'),
    (1, 2, 50.00, 'Ne pritje');

-- Insert Rekordet
INSERT INTO Rekordet (pacient_id, doktor_id, diagnoza, udhezimi)
VALUES
    (1, 1, 'Hipertension', 'Mjekim te rregullt dhe kontroll 1 here ne muaj');

-- Insert Seancat
INSERT INTO Seancat (pacient_id, doktor_id, data_seances, statusi, shenime)
VALUES
    (1, 1, '2025-05-21 10:00:00', 'Skeduluar', 'Seanca e pare');

-- Insert Orari
INSERT INTO Orari (perdoruesi_id, fillimi_ndrrimit, perfundimi_ndrrimit, roli)
VALUES
    (1, '2025-05-21 08:00:00', '2025-05-21 16:00:00', 'Doktor'),
    (2, '2025-05-21 08:00:00', '2025-05-21 16:00:00', 'Moter');

-- Insert Terminet
INSERT INTO Terminet (id_pacient, id_mjek, id_salle, data, statusi, shenime)
VALUES
    (1, 1, 1, '2025-05-21', 'Konfirmuar', 'Kontroll periodik');

-- Insert Pagesa
INSERT INTO Pagesa (pacient_id, fatura_id, shuma, menyra_pageses, statusi, krijuar)
VALUES
    (1, 1, 30, 'Cash', 'Paguar', '2025-05-20');
