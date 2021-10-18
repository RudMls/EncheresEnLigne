DROP TABLE IF EXISTS signaler_article;
DROP TABLE IF EXISTS signaler_membre;
DROP TABLE IF EXISTS service_juridique;
DROP TABLE IF EXISTS service_commercial;
DROP TABLE IF EXISTS enchere;
DROP TABLE IF EXISTS vendre;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS membre;
DROP TABLE IF EXISTS compte;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS categorie;
DROP TABLE IF EXISTS avis;
DROP TABLE IF EXISTS frais;
DROP TABLE IF EXISTS commission;
DROP TABLE IF EXISTS type_membre;
DROP TABLE IF EXISTS optionj;

-- -----------------------------------------------------------------------------
--       TABLE : COMPTE
-- -----------------------------------------------------------------------------


CREATE TABLE compte(

   compte_id INT AUTO_INCREMENT,
   compte_email VARCHAR(255) NOT NULL,
   compte_mdp VARCHAR(255) NOT NULL,
   compte_role VARCHAR(50) NOT NULL,
   
   CONSTRAINT pk_compte PRIMARY KEY(compte_id)

);


-- CREATE TABLE role(
--    role_id INT AUTO_INCREMENT,
--    role_libelle VARCHAR(50),
--    CONSTRAINT pk_role PRIMARY KEY(role_id)
-- );

-- CREATE TABLE compte(

--    compte_id INT AUTO_INCREMENT,
--    compte_email VARCHAR(255) NOT NULL,
--    compte_mdp VARCHAR(255) NOT NULL,
--    role_id INT NOT NULL,
   
--    CONSTRAINT pk_compte PRIMARY KEY(compte_id),
--    CONSTRAINT fk_compte_role_id FOREIGN KEY(role_id) REFERENCES role(role_id)

-- );




-- -----------------------------------------------------------------------------
--       TABLE : MEMBRE
-- -----------------------------------------------------------------------------

CREATE TABLE membre(

   membre_id INT AUTO_INCREMENT,
   membre_nom VARCHAR(50) NOT NULL,
   membre_prenom VARCHAR(50) NOT NULL,
   membre_date_naissance DATE,
   membre_email VARCHAR(50),
   membre_adresse_postale VARCHAR(50),
   membre_code_postal VARCHAR(50),
   membre_ville VARCHAR(50),
   membre_pays VARCHAR(50),
   membre_plus TINYINT(1) DEFAULT 0,
   compte_id INT NOT NULL,

   CONSTRAINT pk_membre PRIMARY KEY(membre_id),
   CONSTRAINT fk_membre_compte_id FOREIGN KEY (compte_id) REFERENCES compte (compte_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : SERVICE_COMMERCIAL
-- -----------------------------------------------------------------------------

CREATE TABLE service_commercial(

   service_commercial_id INT AUTO_INCREMENT,
   compte_id INT NOT NULL,

   CONSTRAINT pk_service_commercial PRIMARY KEY(service_commercial_id),
   CONSTRAINT fk_service_commercial_compte_id FOREIGN KEY(compte_id) REFERENCES compte(compte_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : SERVICE_JURIDIQUE
-- -----------------------------------------------------------------------------

CREATE TABLE service_juridique(

   service_juridique_id INT AUTO_INCREMENT,
   compte_id INT NOT NULL,

   CONSTRAINT pk_service_juridique PRIMARY KEY(service_juridique_id),
   CONSTRAINT fk_service_juridique_compte_id FOREIGN KEY(compte_id) REFERENCES compte(compte_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : CATEGORIE
-- -----------------------------------------------------------------------------

CREATE TABLE categorie(

   categorie_id INT AUTO_INCREMENT,
   categorie_libelle VARCHAR(50) NOT NULL,
   super_categorie_id INT,

   CONSTRAINT pk_categorie PRIMARY KEY(categorie_id),
   CONSTRAINT fk_categorie_super_categorie_id FOREIGN KEY (super_categorie_id) REFERENCES categorie (categorie_id)

)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- -----------------------------------------------------------------------------
--       TABLE : AVIS
-- -----------------------------------------------------------------------------

CREATE TABLE avis(
   avis_id INT AUTO_INCREMENT,
   avis_libelle VARCHAR(50) NOT NULL,
   PRIMARY KEY(avis_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : FRAIS
-- -----------------------------------------------------------------------------

CREATE TABLE frais(
   frais_id INT AUTO_INCREMENT,
   PRIMARY KEY(frais_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : COMMISSION
-- -----------------------------------------------------------------------------

CREATE TABLE commission(
   commission_id INT AUTO_INCREMENT,
   PRIMARY KEY(commission_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : TYPE_MEMBRE
-- -----------------------------------------------------------------------------

CREATE TABLE type_membre(
   type_membre_id INT AUTO_INCREMENT,
   type_membre_libelle VARCHAR(50),
   PRIMARY KEY(type_membre_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : OPTIONJ
-- -----------------------------------------------------------------------------

CREATE TABLE optionJ(

   option_id INT,
   option_libelle VARCHAR(255),
   option_prix_catalogue FLOAT,
   option_prix_gold FLOAT,

   PRIMARY KEY(option_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : ARTICLE
-- -----------------------------------------------------------------------------

CREATE TABLE article(

   article_id INT AUTO_INCREMENT,
   article_titre VARCHAR(50) NOT NULL,
   article_description VARCHAR(50),
   article_frais_port VARCHAR(50),
   article_region_livraison VARCHAR(50),
   article_date_cloture DATE,
   article_mode_cloture VARCHAR(50),
   article_montant_vente FLOAT,
   article_prix_depart FLOAT,
   article_prix_reserve FLOAT,
   article_prix_achat_immediat FLOAT,
   categorie_id INT,

   CONSTRAINT pk_article PRIMARY KEY(article_id),
   CONSTRAINT fk_article_categorie_id FOREIGN KEY (categorie_id) REFERENCES categorie (categorie_id)

);


-- -----------------------------------------------------------------------------
--       TABLE : MEMBRE
-- -----------------------------------------------------------------------------

CREATE TABLE vendre(

   membre_id INT NOT NULL,
   article_id INT NOT NULL,
   option_id INT,

   CONSTRAINT pk_vendre PRIMARY KEY(membre_id, article_id, option_id),
   CONSTRAINT fk_vendre_membre_id FOREIGN KEY (membre_id) REFERENCES membre (membre_id),
   CONSTRAINT fk_vendre_article_id FOREIGN KEY (article_id) REFERENCES article (article_id),
   CONSTRAINT fk_vendre_option_id FOREIGN KEY (option_id) REFERENCES optionJ (option_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : ENCHERE
-- -----------------------------------------------------------------------------

CREATE TABLE enchere(

   membre_id INT NOT NULL,
   article_id INT NOT NULL,
   enchere_montant FLOAT NOT NULL,
   enchere_date_heure TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

   PRIMARY KEY(membre_id, article_id),
   CONSTRAINT fk_enchere_membre_id FOREIGN KEY(membre_id) REFERENCES membre(membre_id),
   CONSTRAINT fk_enchere_article_id FOREIGN KEY(article_id) REFERENCES article(article_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : SIGNALER_ARTICLE
-- -----------------------------------------------------------------------------

CREATE TABLE signaler_article(

   article_id INT NOT NULL,
   signaler_article_commentaire VARCHAR(255) NOT NULL,

   CONSTRAINT fk_signaler_article_article_id FOREIGN KEY(article_id) REFERENCES article(article_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : SIGNALER_MEMBRE
-- -----------------------------------------------------------------------------

CREATE TABLE signaler_membre(

   membre_id INT NOT NULL,
   signaler_membre_commentaire VARCHAR(255) NOT NULL,
   CONSTRAINT fk_signaler_membre_membre_id FOREIGN KEY(membre_id) REFERENCES membre(membre_id)
);


-- -----------------------------------------------------------------------------
--       INSERT
-- -----------------------------------------------------------------------------

INSERT INTO avis (avis_libelle) VALUES
("Très Bon"),
("Gros Problème");

-- INSERT INTO role (role_libelle) VALUES
-- ("membre"),
-- ("service_juridique"),
-- ("service_commercial"),
-- ("gestionnaire");

INSERT INTO categorie (categorie_id, categorie_libelle, super_categorie_id) VALUES
(1, "Multimédia", null),
(2, "Télévision", 1),
(3, "Vidéo", 1),
(4, "Audio-HIFI", 1),
(5, "Photo", 1),
(6, "Informatique", 1),
(7, "Téléphone", 1),
(8, "Equipement de bureau", 1),
(9, "Téléviseurs 4/3", 2),
(10, "Téléviseurs 16/9", 2),
(11, "Téléviseurs LCD", 2),
(12, "Téléviseurs Plasma", 2),

(13, "Electroménager", null),
(14, "Lavage", 13),
(15, "Froid", 13),
(16, "Lave-linge", 14),
(17, "Sèche-linge", 14);




-- -----------------------------------------------------------------------------
--       TABLE : MEMBRE
-- -----------------------------------------------------------------------------


-- ALTER TABLE categorie ADD (
--       CONSTRAINT fk_categorie_super_categorie_id
--             FOREIGN KEY (super_categorie_id)
--                 REFERENCES categorie (categorie_id));

-- ALTER TABLE article ADD (
--       CONSTRAINT fk_article_categorie_id
--             FOREIGN KEY (categorie_id)
--                 REFERENCES categorie (categorie_id));

-- ALTER TABLE vendre ADD (
--       CONSTRAINT fk_vendre_membre_id
--             FOREIGN KEY (membre_id)
--                 REFERENCES membre (membre_id));

-- ALTER TABLE vendre ADD (
--       CONSTRAINT fk_vendre_article_id
--             FOREIGN KEY (article_id)
--                 REFERENCES article (article_id));

-- ALTER TABLE vendre ADD (
--       CONSTRAINT fk_vendre_option_id
--             FOREIGN KEY (option_id)
--                 REFERENCES option (option_id));

-- ALTER TABLE enchere ADD (
--       CONSTRAINT fk_enchere_membre_id
--             FOREIGN KEY (membre_id)
--                 REFERENCES membre (membre_id));

-- ALTER TABLE enchere ADD (
--       CONSTRAINT fk_enchere_article_id
--             FOREIGN KEY (article_id)
--                 REFERENCES article (article_id));