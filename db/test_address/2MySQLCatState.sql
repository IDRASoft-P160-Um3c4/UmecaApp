	CREATE TABLE IF NOT EXISTS CatState (
		StateId INTEGER AUTO_INCREMENT PRIMARY KEY,
		CountryId INTEGER NOT NULL REFERENCES CatCountry(CountryId), 
		Name varchar(50) NOT NULL,
		Abbreviation varchar(100) NULL,
		Description varchar(100) NULL
	) ENGINE=InnoDB;

	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (1, 1, "Aguascalientes", "Agu", "Aguascalientes");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (2, 1, "Baja California", "Baj", "Baja California");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (3, 1, "Baja California Sur", "Baj", "Baja California Sur");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (4, 1, "Campeche", "Cam", "Campeche");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (5, 1, "Coahuila de Zaragoza", "Coa", "Coahuila de Zaragoza");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (6, 1, "Colima", "Col", "Colima");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (7, 1, "Chiapas", "Chi", "Chiapas");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (8, 1, "Chihuahua", "Chi", "Chihuahua");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (9, 1, "Distrito Federal", "Dis", "Distrito Federal");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (10, 1, "Durando", "Dur", "Durando");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (11, 1, "Guanajuato", "Gua", "Guanajuato");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (12, 1, "Guerrero", "Gue", "Guerrero");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (13, 1, "Hidalgo", "Hid", "Hidalgo");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (14, 1, "Jalisco", "Jal", "Jalisco");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (15, 1, "México", "Méx", "México");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (16, 1, "Michoacán de Ocampo", "Mic", "Michoacán de Ocampo");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (17, 1, "Morelos", "Mor", "Morelos");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (18, 1, "Nayarit", "Nay", "Nayarit");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (19, 1, "Nuevo Leó", "Nue", "Nuevo Leó");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (20, 1, "Oaxaca", "Oax", "Oaxaca");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (21, 1, "Puebla", "Pue", "Puebla");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (22, 1, "Querétaro", "Que", "Querétaro");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (23, 1, "Quintana Roo", "Qui", "Quintana Roo");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (24, 1, "San Luis Potosí", "Sa", "San Luis Potosí");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (25, 1, "Sinaloa", "Si", "Sinaloa");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (26, 1, "Sonora", "So", "Sonora");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (27, 1, "Tabasco", "Tab", "Tabasco");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (28, 1, "Tamaulipas", "Tam", "Tamaulipas");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (29, 1, "Tlaxcala", "Tla", "Tlaxcala");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (30, 1, "Veracruz de Ignacio de la Llave", "Ver", "Veracruz de Ignacio de la Llave");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (31, 1, "Yucatá", "Yuc", "Yucatá");
	
	INSERT INTO CatState (StateId, CountryId, Name, Abbreviation, Description) VALUES (32, 1, "Zacatecas", "Zac", "Zacatecas");	

	SELECT * FROM CatState;