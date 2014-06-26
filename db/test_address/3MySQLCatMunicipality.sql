
	CREATE TABLE IF NOT EXISTS cat_municipality (
		id_municipality INTEGER AUTO_INCREMENT PRIMARY KEY,
		id_state INTEGER NOT NULL REFERENCES CatState(id_state), 
		name varchar(50) NOT NULL,
		abbreviation varchar(100) NULL,
		description varchar(100) NULL
	) ENGINE=InnoDB;


	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1, 1, "Aguascalientes", "Aguascalientes", "Aguascalientes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2, 1, "El Llano", "El Llano", "El Llano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (3, 1, "San Francisco de los Romo", "San Francisco d", "San Francisco de los Romo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (4, 1, "Asientos", "Asientos", "Asientos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (5, 1, "Calvillo", "Calvillo", "Calvillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (6, 1, "Cosío", "Cosío", "Cosío");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (7, 1, "Jesús María", "Jesús María", "Jesús María");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (8, 1, "Pabellón de Arteaga", "Pabellón de Art", "Pabellón de Arteaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (9, 1, "Rincón de Romos", "Rincón de Romos", "Rincón de Romos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (10, 1, "San José de Gracia", "San José de Gra", "San José de Gracia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (11, 1, "Tepezalá", "Tepezalá", "Tepezalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (12, 2, "Ensenada", "Ensenada", "Ensenada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (13, 2, "Mexicali", "Mexicali", "Mexicali");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (14, 2, "Tecate", "Tecate", "Tecate");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (15, 2, "Tijuana", "Tijuana", "Tijuana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (16, 2, "Playas de Rosarito", "Playas de Rosar", "Playas de Rosarito");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (17, 3, "Comondú", "Comondú", "Comondú");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (18, 3, "Mulegé", "Mulegé", "Mulegé");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (19, 3, "Poblado comunal", "Poblado comunal", "Poblado comunal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (20, 3, "La Paz", "La Paz", "La Paz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (21, 3, "Los Cabos", "Los Cabos", "Los Cabos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (22, 3, "Colonia", "Colonia", "Colonia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (23, 3, "Loreto", "Loreto", "Loreto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (24, 4, "Calkiní", "Calkiní", "Calkiní");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (25, 4, "Calakmul", "Calakmul", "Calakmul");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (26, 4, "Candelaria", "Candelaria", "Candelaria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (27, 4, "Campeche", "Campeche", "Campeche");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (28, 4, "Carmen", "Carmen", "Carmen");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (29, 4, "Champotón", "Champotón", "Champotón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (30, 4, "Hecelchakán", "Hecelchakán", "Hecelchakán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (31, 4, "Hopelchén", "Hopelchén", "Hopelchén");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (32, 4, "Palizada", "Palizada", "Palizada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (33, 4, "Tenabo", "Tenabo", "Tenabo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (34, 4, "Escárcega", "Escárcega", "Escárcega");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (35, 5, "Abasolo", "Abasolo", "Abasolo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (36, 5, "Aeropuerto", "Aeropuerto", "Aeropuerto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (37, 5, "Frontera", "Frontera", "Frontera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (38, 5, "General Cepeda", "General Cepeda", "General Cepeda");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (39, 5, "Guerrero", "Guerrero", "Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (40, 5, "Hidalgo", "Hidalgo", "Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (41, 5, "Jiménez", "Jiménez", "Jiménez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (42, 5, "Juárez", "Juárez", "Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (43, 5, "Lamadrid", "Lamadrid", "Lamadrid");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (44, 5, "Matamoros", "Matamoros", "Matamoros");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (45, 5, "Monclova", "Monclova", "Monclova");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (46, 5, "Morelos", "Morelos", "Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (47, 5, "Acuña", "Acuña", "Acuña");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (48, 5, "Múzquiz", "Múzquiz", "Múzquiz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (49, 5, "Fraccionamiento", "Fraccionamiento", "Fraccionamiento");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (50, 5, "Nadadores", "Nadadores", "Nadadores");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (51, 5, "Nava", "Nava", "Nava");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (52, 5, "Ocampo", "Ocampo", "Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (53, 5, "Parras", "Parras", "Parras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (54, 5, "Piedras Negras", "Piedras Negras", "Piedras Negras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (55, 5, "Progreso", "Progreso", "Progreso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (56, 5, "Ramos Arizpe", "Ramos Arizpe", "Ramos Arizpe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (57, 5, "Sabinas", "Sabinas", "Sabinas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (58, 5, "Sacramento", "Sacramento", "Sacramento");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (59, 5, "Allende", "Allende", "Allende");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (60, 5, "Saltillo", "Saltillo", "Saltillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (61, 5, "San Buenaventura", "San Buenaventur", "San Buenaventura");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (62, 5, "San Juan de Sabinas", "San Juan de Sab", "San Juan de Sabinas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (63, 5, "San Pedro", "San Pedro", "San Pedro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (64, 5, "Sierra Mojada", "Sierra Mojada", "Sierra Mojada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (65, 5, "Torreón", "Torreón", "Torreón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (66, 5, "Viesca", "Viesca", "Viesca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (67, 5, "Villa Unión", "Villa Unión", "Villa Unión");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (68, 5, "Zaragoza", "Zaragoza", "Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (69, 5, "Arteaga", "Arteaga", "Arteaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (70, 5, "Candela", "Candela", "Candela");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (71, 5, "Castaños", "Castaños", "Castaños");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (72, 5, "Cuatro Ciénegas", "Cuatro Ciénegas", "Cuatro Ciénegas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (73, 5, "Escobedo", "Escobedo", "Escobedo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (74, 5, "Francisco I. Madero", "Francisco I. Ma", "Francisco I. Madero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (75, 6, "Armería", "Armería", "Armería");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (76, 6, "Villa de Álvarez", "Villa de Álvare", "Villa de Álvarez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (77, 6, "Colima", "Colima", "Colima");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (78, 6, "Comala", "Comala", "Comala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (79, 6, "Coquimatlán", "Coquimatlán", "Coquimatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (80, 6, "Cuauhtémoc", "Cuauhtémoc", "Cuauhtémoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (81, 6, "Ixtlahuacán", "Ixtlahuacán", "Ixtlahuacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (82, 6, "Manzanillo", "Manzanillo", "Manzanillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (83, 6, "Minatitlán", "Minatitlán", "Minatitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (84, 6, "Tecomán", "Tecomán", "Tecomán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (85, 7, "Acacoyagua", "Acacoyagua", "Acacoyagua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (86, 7, "Bejucal de Ocampo", "Bejucal de Ocam", "Bejucal de Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (87, 7, "Tumbalá", "Tumbalá", "Tumbalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (88, 7, "Tuxtla Gutiérrez", "Tuxtla Gutiérre", "Tuxtla Gutiérrez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (89, 7, "Tuxtla Chico", "Tuxtla Chico", "Tuxtla Chico");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (90, 7, "Tuzantán", "Tuzantán", "Tuzantán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (91, 7, "Tzimol", "Tzimol", "Tzimol");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (92, 7, "Unión Juárez", "Unión Juárez", "Unión Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (93, 7, "Venustiano Carranza", "Venustiano Carr", "Venustiano Carranza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (94, 7, "Villa Corzo", "Villa Corzo", "Villa Corzo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (95, 7, "Villaflores", "Villaflores", "Villaflores");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (96, 7, "Yajalón", "Yajalón", "Yajalón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (97, 7, "Bella Vista", "Bella Vista", "Bella Vista");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (98, 7, "San Lucas", "San Lucas", "San Lucas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (99, 7, "Zinacantán", "Zinacantán", "Zinacantán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (100, 7, "San Juan Cancuc", "San Juan Cancuc", "San Juan Cancuc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (101, 7, "Aldama", "Aldama", "Aldama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (102, 7, "Benemérito de las Américas", "Benemérito de l", "Benemérito de las Américas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (103, 7, "Maravilla Tenejapa", "Maravilla Tenej", "Maravilla Tenejapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (104, 7, "Marqués de Comillas", "Marqués de Comi", "Marqués de Comillas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (105, 7, "Montecristo de Guerrero", "Montecristo de ", "Montecristo de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (106, 7, "San Andrés Duraznal", "San Andrés Dura", "San Andrés Duraznal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (107, 7, "Santiago el Pinar", "Santiago el Pin", "Santiago el Pinar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (108, 7, "Berriozábal", "Berriozábal", "Berriozábal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (109, 7, "Bochil", "Bochil", "Bochil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (110, 7, "El Bosque", "El Bosque", "El Bosque");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (111, 7, "Cacahoatán", "Cacahoatán", "Cacahoatán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (112, 7, "Catazajá", "Catazajá", "Catazajá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (113, 7, "Cintalapa", "Cintalapa", "Cintalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (114, 7, "Coapilla", "Coapilla", "Coapilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (115, 7, "Comitán de Domínguez", "Comitán de Domí", "Comitán de Domínguez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (116, 7, "Acala", "Acala", "Acala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (117, 7, "La Concordia", "La Concordia", "La Concordia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (118, 7, "Copainalá", "Copainalá", "Copainalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (119, 7, "Chalchihuitán", "Chalchihuitán", "Chalchihuitán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (120, 7, "Chamula", "Chamula", "Chamula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (121, 7, "Chanal", "Chanal", "Chanal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (122, 7, "Chapultenango", "Chapultenango", "Chapultenango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (123, 7, "Chenalhó", "Chenalhó", "Chenalhó");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (124, 7, "Chiapa de Corzo", "Chiapa de Corzo", "Chiapa de Corzo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (125, 7, "Chiapilla", "Chiapilla", "Chiapilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (126, 7, "Chicoasén", "Chicoasén", "Chicoasén");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (127, 7, "Acapetahua", "Acapetahua", "Acapetahua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (128, 7, "Chicomuselo", "Chicomuselo", "Chicomuselo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (129, 7, "Chilón", "Chilón", "Chilón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (130, 7, "Unidad habitacional", "Unidad habitaci", "Unidad habitacional");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (131, 7, "Escuintla", "Escuintla", "Escuintla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (132, 7, "Francisco León", "Francisco León", "Francisco León");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (133, 7, "Frontera Comalapa", "Frontera Comala", "Frontera Comalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (134, 7, "Frontera Hidalgo", "Frontera Hidalg", "Frontera Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (135, 7, "La Grandeza", "La Grandeza", "La Grandeza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (136, 7, "Huehuetán", "Huehuetán", "Huehuetán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (137, 7, "Huixtán", "Huixtán", "Huixtán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (138, 7, "Huitiupán", "Huitiupán", "Huitiupán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (139, 7, "Altamirano", "Altamirano", "Altamirano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (140, 7, "Huixtla", "Huixtla", "Huixtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (141, 7, "La Independencia", "La Independenci", "La Independencia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (142, 7, "Ixhuatán", "Ixhuatán", "Ixhuatán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (143, 7, "Ixtacomitán", "Ixtacomitán", "Ixtacomitán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (144, 7, "Ixtapa", "Ixtapa", "Ixtapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (145, 7, "Ixtapangajoya", "Ixtapangajoya", "Ixtapangajoya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (146, 7, "Jiquipilas", "Jiquipilas", "Jiquipilas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (147, 7, "Jitotol", "Jitotol", "Jitotol");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (148, 7, "Juárez", "Juárez", "Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (149, 7, "Larráinzar", "Larráinzar", "Larráinzar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (150, 7, "Amatán", "Amatán", "Amatán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (151, 7, "La Libertad", "La Libertad", "La Libertad");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (152, 7, "Mapastepec", "Mapastepec", "Mapastepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (153, 7, "Las Margaritas", "Las Margaritas", "Las Margaritas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (154, 7, "Mazapa de Madero", "Mazapa de Mader", "Mazapa de Madero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (155, 7, "Mazatán", "Mazatán", "Mazatán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (156, 7, "Metapa", "Metapa", "Metapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (157, 7, "Mitontic", "Mitontic", "Mitontic");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (158, 7, "Motozintla", "Motozintla", "Motozintla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (159, 7, "Nicolás Ruíz", "Nicolás Ruíz", "Nicolás Ruíz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (160, 7, "Ocosingo", "Ocosingo", "Ocosingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (161, 7, "Amatenango de la Frontera", "Amatenango de l", "Amatenango de la Frontera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (162, 7, "Ocotepec", "Ocotepec", "Ocotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (163, 7, "Ocozocoautla de Espinosa", "Ocozocoautla de", "Ocozocoautla de Espinosa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (164, 7, "Ostuacán", "Ostuacán", "Ostuacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (165, 7, "Osumacinta", "Osumacinta", "Osumacinta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (166, 7, "Oxchuc", "Oxchuc", "Oxchuc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (167, 7, "Palenque", "Palenque", "Palenque");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (168, 7, "Pantelhó", "Pantelhó", "Pantelhó");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (169, 7, "Pantepec", "Pantepec", "Pantepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (170, 7, "Pichucalco", "Pichucalco", "Pichucalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (171, 7, "Pijijiapan", "Pijijiapan", "Pijijiapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (172, 7, "Amatenango del Valle", "Amatenango del ", "Amatenango del Valle");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (173, 7, "El Porvenir", "El Porvenir", "El Porvenir");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (174, 7, "Villa Comaltitlán", "Villa Comaltitl", "Villa Comaltitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (175, 7, "Pueblo Nuevo Solistahuacán", "Pueblo Nuevo So", "Pueblo Nuevo Solistahuacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (176, 7, "Rayón", "Rayón", "Rayón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (177, 7, "Reforma", "Reforma", "Reforma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (178, 7, "Las Rosas", "Las Rosas", "Las Rosas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (179, 7, "Sabanilla", "Sabanilla", "Sabanilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (180, 7, "Salto de Agua", "Salto de Agua", "Salto de Agua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (181, 7, "San Cristóbal de las Casas", "San Cristóbal d", "San Cristóbal de las Casas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (182, 7, "San Fernando", "San Fernando", "San Fernando");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (183, 7, "Angel Albino Corzo", "Angel Albino Co", "Angel Albino Corzo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (184, 7, "Siltepec", "Siltepec", "Siltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (185, 7, "Simojovel", "Simojovel", "Simojovel");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (186, 7, "Sitalá", "Sitalá", "Sitalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (187, 7, "Socoltenango", "Socoltenango", "Socoltenango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (188, 7, "Solosuchiapa", "Solosuchiapa", "Solosuchiapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (189, 7, "Soyaló", "Soyaló", "Soyaló");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (190, 7, "Suchiapa", "Suchiapa", "Suchiapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (191, 7, "Suchiate", "Suchiate", "Suchiate");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (192, 7, "Sunuapa", "Sunuapa", "Sunuapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (193, 7, "Tapachula", "Tapachula", "Tapachula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (194, 7, "Arriaga", "Arriaga", "Arriaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (195, 7, "Tapalapa", "Tapalapa", "Tapalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (196, 7, "Tapilula", "Tapilula", "Tapilula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (197, 7, "Tecpatán", "Tecpatán", "Tecpatán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (198, 7, "Tenejapa", "Tenejapa", "Tenejapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (199, 7, "Teopisca", "Teopisca", "Teopisca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (200, 7, "Tila", "Tila", "Tila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (201, 7, "Tonalá", "Tonalá", "Tonalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (202, 7, "Totolapa", "Totolapa", "Totolapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (203, 7, "La Trinitaria", "La Trinitaria", "La Trinitaria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (204, 8, "Ahumada", "Ahumada", "Ahumada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (205, 8, "Buenaventura", "Buenaventura", "Buenaventura");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (206, 8, "Camargo", "Camargo", "Camargo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (207, 8, "Carichí", "Carichí", "Carichí");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (208, 8, "Casas Grandes", "Casas Grandes", "Casas Grandes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (209, 8, "Coronado", "Coronado", "Coronado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (210, 8, "Coyame del Sotol", "Coyame del Soto", "Coyame del Sotol");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (211, 8, "La Cruz", "La Cruz", "La Cruz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (212, 8, "Cuauhtémoc", "Cuauhtémoc", "Cuauhtémoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (213, 8, "Cusihuiriachi", "Cusihuiriachi", "Cusihuiriachi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (214, 8, "Chihuahua", "Chihuahua", "Chihuahua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (215, 8, "Aldama", "Aldama", "Aldama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (216, 8, "Chínipas", "Chínipas", "Chínipas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (217, 8, "Delicias", "Delicias", "Delicias");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (218, 8, "Fraccionamiento", "Fraccionamiento", "Fraccionamiento");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (219, 8, "Dr. Belisario Domínguez", "Dr. Belisario D", "Dr. Belisario Domínguez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (220, 8, "Galeana", "Galeana", "Galeana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (221, 8, "Santa Isabel", "Santa Isabel", "Santa Isabel");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (222, 8, "Gómez Farías", "Gómez Farías", "Gómez Farías");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (223, 8, "Gran Morelos", "Gran Morelos", "Gran Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (224, 8, "Guachochi", "Guachochi", "Guachochi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (225, 8, "Guadalupe", "Guadalupe", "Guadalupe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (226, 8, "Guadalupe y Calvo", "Guadalupe y Cal", "Guadalupe y Calvo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (227, 8, "Ranchería", "Ranchería", "Ranchería");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (228, 8, "Allende", "Allende", "Allende");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (229, 8, "Guazapares", "Guazapares", "Guazapares");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (230, 8, "Guerrero", "Guerrero", "Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (231, 8, " III", " III", " III");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (232, 8, " III", " III", " III");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (233, 8, " III", " III", " III");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (234, 8, "Hidalgo del Parral", "Hidalgo del Par", "Hidalgo del Parral");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (235, 8, "Huejotitán", "Huejotitán", "Huejotitán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (236, 8, "Ignacio Zaragoza", "Ignacio Zaragoz", "Ignacio Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (237, 8, "Janos", "Janos", "Janos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (238, 8, "Jiménez", "Jiménez", "Jiménez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (239, 8, "Juárez", "Juárez", "Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (240, 8, "Julimes", "Julimes", "Julimes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (241, 8, "López", "López", "López");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (242, 8, "Aquiles Serdán", "Aquiles Serdán", "Aquiles Serdán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (243, 8, "Madera", "Madera", "Madera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (244, 8, "Maguarichi", "Maguarichi", "Maguarichi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (245, 8, "Manuel Benavides", "Manuel Benavide", "Manuel Benavides");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (246, 8, "Matachí", "Matachí", "Matachí");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (247, 8, "Matamoros", "Matamoros", "Matamoros");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (248, 8, "Meoqui", "Meoqui", "Meoqui");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (249, 8, "Morelos", "Morelos", "Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (250, 8, "Moris", "Moris", "Moris");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (251, 8, "Namiquipa", "Namiquipa", "Namiquipa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (252, 8, "Nonoava", "Nonoava", "Nonoava");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (253, 8, "Ascensión", "Ascensión", "Ascensión");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (254, 8, "Nuevo Casas Grandes", "Nuevo Casas Gra", "Nuevo Casas Grandes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (255, 8, "Ocampo", "Ocampo", "Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (256, 8, "Ojinaga", "Ojinaga", "Ojinaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (257, 8, "Praxedis G. Guerrero", "Praxedis G. Gue", "Praxedis G. Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (258, 8, "Riva Palacio", "Riva Palacio", "Riva Palacio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (259, 8, "Rosales", "Rosales", "Rosales");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (260, 8, "Rosario", "Rosario", "Rosario");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (261, 8, "San Francisco de Borja", "San Francisco d", "San Francisco de Borja");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (262, 8, "San Francisco de Conchos", "San Francisco d", "San Francisco de Conchos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (263, 8, "San Francisco del Oro", "San Francisco d", "San Francisco del Oro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (264, 8, "Bachíniva", "Bachíniva", "Bachíniva");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (265, 8, "Santa Bárbara", "Santa Bárbara", "Santa Bárbara");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (266, 8, "Satevó", "Satevó", "Satevó");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (267, 8, "Saucillo", "Saucillo", "Saucillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (268, 8, "Temósachic", "Temósachic", "Temósachic");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (269, 8, "El Tule", "El Tule", "El Tule");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (270, 8, "Urique", "Urique", "Urique");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (271, 8, "Uruachi", "Uruachi", "Uruachi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (272, 8, "Valle de Zaragoza", "Valle de Zarago", "Valle de Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (273, 8, "Balleza", "Balleza", "Balleza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (274, 8, " III", " III", " III");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (275, 8, "Batopilas", "Batopilas", "Batopilas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (276, 8, "Bocoyna", "Bocoyna", "Bocoyna");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (277, 8, "Colonia", "Colonia", "Colonia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (278, 9, "Álvaro Obregón", "Álvaro Obregón", "Álvaro Obregón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (279, 9, "Tláhuac", "Tláhuac", "Tláhuac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (280, 9, "Tlalpan", "Tlalpan", "Tlalpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (281, 9, "Xochimilco", "Xochimilco", "Xochimilco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (282, 9, "Benito Juárez", "Benito Juárez", "Benito Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (283, 9, "Cuauhtémoc", "Cuauhtémoc", "Cuauhtémoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (284, 9, "Miguel Hidalgo", "Miguel Hidalgo", "Miguel Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (285, 9, "Venustiano Carranza", "Venustiano Carr", "Venustiano Carranza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (286, 9, "Azcapotzalco", "Azcapotzalco", "Azcapotzalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (287, 9, "Coyoacán", "Coyoacán", "Coyoacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (288, 9, "Cuajimalpa de Morelos", "Cuajimalpa de M", "Cuajimalpa de Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (289, 9, "Gustavo A. Madero", "Gustavo A. Made", "Gustavo A. Madero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (290, 9, "Iztacalco", "Iztacalco", "Iztacalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (291, 9, "Iztapalapa", "Iztapalapa", "Iztapalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (292, 9, "La Magdalena Contreras", "La Magdalena Co", "La Magdalena Contreras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (293, 9, "Milpa Alta", "Milpa Alta", "Milpa Alta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (294, 10, "Canatlán", "Canatlán", "Canatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (295, 10, "Hidalgo", "Hidalgo", "Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (296, 10, "Indé", "Indé", "Indé");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (297, 10, "Lerdo", "Lerdo", "Lerdo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (298, 10, "Mapimí", "Mapimí", "Mapimí");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (299, 10, "Mezquital", "Mezquital", "Mezquital");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (300, 10, "Nazas", "Nazas", "Nazas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (301, 10, "Nombre de Dios", "Nombre de Dios", "Nombre de Dios");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (302, 10, "Ocampo", "Ocampo", "Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (303, 10, "El Oro", "El Oro", "El Oro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (304, 10, "Otáez", "Otáez", "Otáez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (305, 10, "Canelas", "Canelas", "Canelas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (306, 10, "Pánuco de Coronado", "Pánuco de Coron", "Pánuco de Coronado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (307, 10, "Peñón Blanco", "Peñón Blanco", "Peñón Blanco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (308, 10, "Poanas", "Poanas", "Poanas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (309, 10, "Pueblo Nuevo", "Pueblo Nuevo", "Pueblo Nuevo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (310, 10, "Rodeo", "Rodeo", "Rodeo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (311, 10, "San Bernardo", "San Bernardo", "San Bernardo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (312, 10, "San Dimas", "San Dimas", "San Dimas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (313, 10, "San Juan de Guadalupe", "San Juan de Gua", "San Juan de Guadalupe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (314, 10, "San Juan del Río", "San Juan del Rí", "San Juan del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (315, 10, "San Luis del Cordero", "San Luis del Co", "San Luis del Cordero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (316, 10, "Coneto de Comonfort", "Coneto de Comon", "Coneto de Comonfort");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (317, 10, "San Pedro del Gallo", "San Pedro del G", "San Pedro del Gallo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (318, 10, "Santa Clara", "Santa Clara", "Santa Clara");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (319, 10, "Santiago Papasquiaro", "Santiago Papasq", "Santiago Papasquiaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (320, 10, "Súchil", "Súchil", "Súchil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (321, 10, "Tamazula", "Tamazula", "Tamazula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (322, 10, "Tepehuanes", "Tepehuanes", "Tepehuanes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (323, 10, "Tlahualilo", "Tlahualilo", "Tlahualilo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (324, 10, "Topia", "Topia", "Topia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (325, 10, "Vicente Guerrero", "Vicente Guerrer", "Vicente Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (326, 10, "Nuevo Ideal", "Nuevo Ideal", "Nuevo Ideal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (327, 10, "Cuencamé", "Cuencamé", "Cuencamé");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (328, 10, "Durango", "Durango", "Durango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (329, 10, "General Simón Bolívar", "General Simón B", "General Simón Bolívar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (330, 10, "Gómez Palacio", "Gómez Palacio", "Gómez Palacio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (331, 10, "Guadalupe Victoria", "Guadalupe Victo", "Guadalupe Victoria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (332, 10, "Colonia", "Colonia", "Colonia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (333, 10, "Guanaceví", "Guanaceví", "Guanaceví");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (334, 11, "Abasolo", "Abasolo", "Abasolo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (335, 11, "Coroneo", "Coroneo", "Coroneo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (336, 11, "Cortazar", "Cortazar", "Cortazar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (337, 11, "Cuerámaro", "Cuerámaro", "Cuerámaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (338, 11, "Doctor Mora", "Doctor Mora", "Doctor Mora");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (339, 11, "Dolores Hidalgo Cuna de la Independencia Nacional", "Dolores Hidalgo", "Dolores Hidalgo Cuna de la Independencia Nacional");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (340, 11, "Guanajuato", "Guanajuato", "Guanajuato");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (341, 11, "Huanímaro", "Huanímaro", "Huanímaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (342, 11, "Irapuato", "Irapuato", "Irapuato");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (343, 11, "Jaral del Progreso", "Jaral del Progr", "Jaral del Progreso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (344, 11, "Jerécuaro", "Jerécuaro", "Jerécuaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (345, 11, "Acámbaro", "Acámbaro", "Acámbaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (346, 11, "León", "León", "León");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (347, 11, "Moroleón", "Moroleón", "Moroleón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (348, 11, "Ocampo", "Ocampo", "Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (349, 11, "Pénjamo", "Pénjamo", "Pénjamo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (350, 11, "Pueblo Nuevo", "Pueblo Nuevo", "Pueblo Nuevo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (351, 11, "Purísima del Rincón", "Purísima del Ri", "Purísima del Rincón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (352, 11, "Romita", "Romita", "Romita");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (353, 11, "Salamanca", "Salamanca", "Salamanca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (354, 11, "Salvatierra", "Salvatierra", "Salvatierra");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (355, 11, "San Diego de la Unión", "San Diego de la", "San Diego de la Unión");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (356, 11, "San Miguel de Allende", "San Miguel de A", "San Miguel de Allende");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (357, 11, "San Felipe", "San Felipe", "San Felipe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (358, 11, "San Francisco del Rincón", "San Francisco d", "San Francisco del Rincón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (359, 11, "San José Iturbide", "San José Iturbi", "San José Iturbide");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (360, 11, "San Luis de la Paz", "San Luis de la ", "San Luis de la Paz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (361, 11, "Santa Catarina", "Santa Catarina", "Santa Catarina");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (362, 11, "Santa Cruz de Juventino Rosas", "Santa Cruz de J", "Santa Cruz de Juventino Rosas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (363, 11, "Santiago Maravatío", "Santiago Marava", "Santiago Maravatío");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (364, 11, "Silao de la Victoria", "Silao de la Vic", "Silao de la Victoria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (365, 11, "Tarandacuao", "Tarandacuao", "Tarandacuao");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (366, 11, "Tarimoro", "Tarimoro", "Tarimoro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (367, 11, "Apaseo el Alto", "Apaseo el Alto", "Apaseo el Alto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (368, 11, "Tierra Blanca", "Tierra Blanca", "Tierra Blanca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (369, 11, "Uriangato", "Uriangato", "Uriangato");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (370, 11, "Valle de Santiago", "Valle de Santia", "Valle de Santiago");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (371, 11, "Victoria", "Victoria", "Victoria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (372, 11, "Villagrán", "Villagrán", "Villagrán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (373, 11, "Xichú", "Xichú", "Xichú");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (374, 11, "Yuriria", "Yuriria", "Yuriria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (375, 11, "Apaseo el Grande", "Apaseo el Grand", "Apaseo el Grande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (376, 11, "Atarjea", "Atarjea", "Atarjea");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (377, 11, "Celaya", "Celaya", "Celaya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (378, 11, "Manuel Doblado", "Manuel Doblado", "Manuel Doblado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (379, 11, "Comonfort", "Comonfort", "Comonfort");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (380, 12, "Acapulco de Juárez", "Acapulco de Juá", "Acapulco de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (381, 12, "Atlixtac", "Atlixtac", "Atlixtac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (382, 12, "Atoyac de Álvarez", "Atoyac de Álvar", "Atoyac de Álvarez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (383, 12, "Ayutla de los Libres", "Ayutla de los L", "Ayutla de los Libres");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (384, 12, "Azoyú", "Azoyú", "Azoyú");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (385, 12, "Benito Juárez", "Benito Juárez", "Benito Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (386, 12, "Buenavista de Cuéllar", "Buenavista de C", "Buenavista de Cuéllar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (387, 12, "Coahuayutla de José María Izazaga", "Coahuayutla de ", "Coahuayutla de José María Izazaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (388, 12, "Cocula", "Cocula", "Cocula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (389, 12, "Copala", "Copala", "Copala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (390, 12, "Copalillo", "Copalillo", "Copalillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (391, 12, "Ahuacuotzingo", "Ahuacuotzingo", "Ahuacuotzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (392, 12, "Copanatoyac", "Copanatoyac", "Copanatoyac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (393, 12, "Coyuca de Benítez", "Coyuca de Benít", "Coyuca de Benítez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (394, 12, "Coyuca de Catalán", "Coyuca de Catal", "Coyuca de Catalán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (395, 12, "Cuajinicuilapa", "Cuajinicuilapa", "Cuajinicuilapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (396, 12, "Cualác", "Cualác", "Cualác");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (397, 12, "Cuautepec", "Cuautepec", "Cuautepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (398, 12, "Cuetzala del Progreso", "Cuetzala del Pr", "Cuetzala del Progreso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (399, 12, "Cutzamala de Pinzón", "Cutzamala de Pi", "Cutzamala de Pinzón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (400, 12, "Chilapa de Álvarez", "Chilapa de Álva", "Chilapa de Álvarez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (401, 12, "Chilpancingo de los Bravo", "Chilpancingo de", "Chilpancingo de los Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (402, 12, "Ajuchitlán del Progreso", "Ajuchitlán del ", "Ajuchitlán del Progreso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (403, 12, "Florencio Villarreal", "Florencio Villa", "Florencio Villarreal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (404, 12, "General Canuto A. Neri", "General Canuto ", "General Canuto A. Neri");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (405, 12, "General Heliodoro Castillo", "General Heliodo", "General Heliodoro Castillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (406, 12, "Huamuxtitlán", "Huamuxtitlán", "Huamuxtitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (407, 12, "Huitzuco de los Figueroa", "Huitzuco de los", "Huitzuco de los Figueroa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (408, 12, "Iguala de la Independencia", "Iguala de la In", "Iguala de la Independencia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (409, 12, "Igualapa", "Igualapa", "Igualapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (410, 12, "Ixcateopan de Cuauhtémoc", "Ixcateopan de C", "Ixcateopan de Cuauhtémoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (411, 12, "Zihuatanejo de Azueta", "Zihuatanejo de ", "Zihuatanejo de Azueta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (412, 12, "Juan R. Escudero", "Juan R. Escuder", "Juan R. Escudero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (413, 12, "Alcozauca de Guerrero", "Alcozauca de Gu", "Alcozauca de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (414, 12, "Leonardo Bravo", "Leonardo Bravo", "Leonardo Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (415, 12, "Malinaltepec", "Malinaltepec", "Malinaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (416, 12, "Mártir de Cuilapan", "Mártir de Cuila", "Mártir de Cuilapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (417, 12, "Metlatónoc", "Metlatónoc", "Metlatónoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (418, 12, "Mochitlán", "Mochitlán", "Mochitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (419, 12, "Olinalá", "Olinalá", "Olinalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (420, 12, "Ometepec", "Ometepec", "Ometepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (421, 12, "Pedro Ascencio Alquisiras", "Pedro Ascencio ", "Pedro Ascencio Alquisiras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (422, 12, "Petatlán", "Petatlán", "Petatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (423, 12, "Pilcaya", "Pilcaya", "Pilcaya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (424, 12, "Alpoyeca", "Alpoyeca", "Alpoyeca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (425, 12, "Pungarabato", "Pungarabato", "Pungarabato");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (426, 12, "Quechultenango", "Quechultenango", "Quechultenango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (427, 12, "San Luis Acatlán", "San Luis Acatlá", "San Luis Acatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (428, 12, "San Marcos", "San Marcos", "San Marcos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (429, 12, "San Miguel Totolapan", "San Miguel Toto", "San Miguel Totolapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (430, 12, "Taxco de Alarcón", "Taxco de Alarcó", "Taxco de Alarcón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (431, 12, "Tecoanapa", "Tecoanapa", "Tecoanapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (432, 12, "Técpan de Galeana", "Técpan de Galea", "Técpan de Galeana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (433, 12, "Teloloapan", "Teloloapan", "Teloloapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (434, 12, "Tepecoacuilco de Trujano", "Tepecoacuilco d", "Tepecoacuilco de Trujano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (435, 12, "Apaxtla", "Apaxtla", "Apaxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (436, 12, "Tetipac", "Tetipac", "Tetipac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (437, 12, "Tixtla de Guerrero", "Tixtla de Guerr", "Tixtla de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (438, 12, "Tlacoachistlahuaca", "Tlacoachistlahu", "Tlacoachistlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (439, 12, "Tlacoapa", "Tlacoapa", "Tlacoapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (440, 12, "Tlalchapa", "Tlalchapa", "Tlalchapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (441, 12, "Tlalixtaquilla de Maldonado", "Tlalixtaquilla ", "Tlalixtaquilla de Maldonado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (442, 12, "Tlapa de Comonfort", "Tlapa de Comonf", "Tlapa de Comonfort");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (443, 12, "Tlapehuala", "Tlapehuala", "Tlapehuala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (444, 12, "La Unión de Isidoro Montes de Oca", "La Unión de Isi", "La Unión de Isidoro Montes de Oca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (445, 12, "Xalpatláhuac", "Xalpatláhuac", "Xalpatláhuac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (446, 12, "Arcelia", "Arcelia", "Arcelia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (447, 12, "Xochihuehuetlán", "Xochihuehuetlán", "Xochihuehuetlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (448, 12, "Xochistlahuaca", "Xochistlahuaca", "Xochistlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (449, 12, "Zapotitlán Tablas", "Zapotitlán Tabl", "Zapotitlán Tablas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (450, 12, "Zirándaro", "Zirándaro", "Zirándaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (451, 12, "Zitlala", "Zitlala", "Zitlala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (452, 12, "Eduardo Neri", "Eduardo Neri", "Eduardo Neri");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (453, 12, "Acatepec", "Acatepec", "Acatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (454, 12, "Marquelia", "Marquelia", "Marquelia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (455, 12, "Cochoapa el Grande", "Cochoapa el Gra", "Cochoapa el Grande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (456, 12, "José Joaquín de Herrera", "José Joaquín de", "José Joaquín de Herrera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (457, 12, "Atenango del Río", "Atenango del Rí", "Atenango del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (458, 12, "Juchitán", "Juchitán", "Juchitán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (459, 12, "Iliatenco", "Iliatenco", "Iliatenco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (460, 12, "Atlamajalcingo del Monte", "Atlamajalcingo ", "Atlamajalcingo del Monte");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (461, 13, "Acatlán", "Acatlán", "Acatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (462, 13, "Atitalaquia", "Atitalaquia", "Atitalaquia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (463, 13, "Atlapexco", "Atlapexco", "Atlapexco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (464, 13, "Atotonilco el Grande", "Atotonilco el G", "Atotonilco el Grande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (465, 13, "Atotonilco de Tula", "Atotonilco de T", "Atotonilco de Tula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (466, 13, "Calnali", "Calnali", "Calnali");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (467, 13, "Cardonal", "Cardonal", "Cardonal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (468, 13, "Cuautepec de Hinojosa", "Cuautepec de Hi", "Cuautepec de Hinojosa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (469, 13, "Chapantongo", "Chapantongo", "Chapantongo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (470, 13, "Chapulhuacán", "Chapulhuacán", "Chapulhuacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (471, 13, "Chilcuautla", "Chilcuautla", "Chilcuautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (472, 13, "Acaxochitlán", "Acaxochitlán", "Acaxochitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (473, 13, "Eloxochitlán", "Eloxochitlán", "Eloxochitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (474, 13, "Emiliano Zapata", "Emiliano Zapata", "Emiliano Zapata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (475, 13, "Epazoyucan", "Epazoyucan", "Epazoyucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (476, 13, "Francisco I. Madero", "Francisco I. Ma", "Francisco I. Madero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (477, 13, "Huasca de Ocampo", "Huasca de Ocamp", "Huasca de Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (478, 13, "Huautla", "Huautla", "Huautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (479, 13, "Huazalingo", "Huazalingo", "Huazalingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (480, 13, "Huehuetla", "Huehuetla", "Huehuetla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (481, 13, "Huejutla de Reyes", "Huejutla de Rey", "Huejutla de Reyes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (482, 13, "Huichapan", "Huichapan", "Huichapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (483, 13, "Actopan", "Actopan", "Actopan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (484, 13, "Ixmiquilpan", "Ixmiquilpan", "Ixmiquilpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (485, 13, "Jacala de Ledezma", "Jacala de Ledez", "Jacala de Ledezma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (486, 13, "Jaltocán", "Jaltocán", "Jaltocán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (487, 13, "Juárez Hidalgo", "Juárez Hidalgo", "Juárez Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (488, 13, "Lolotla", "Lolotla", "Lolotla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (489, 13, "Metepec", "Metepec", "Metepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (490, 13, "San Agustín Metzquititlán", "San Agustín Met", "San Agustín Metzquititlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (491, 13, "Metztitlán", "Metztitlán", "Metztitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (492, 13, "Mineral del Chico", "Mineral del Chi", "Mineral del Chico");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (493, 13, "Mineral del Monte", "Mineral del Mon", "Mineral del Monte");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (494, 13, "Agua Blanca de Iturbide", "Agua Blanca de ", "Agua Blanca de Iturbide");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (495, 13, "La Misión", "La Misión", "La Misión");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (496, 13, "Mixquiahuala de Juárez", "Mixquiahuala de", "Mixquiahuala de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (497, 13, "Molango de Escamilla", "Molango de Esca", "Molango de Escamilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (498, 13, "Nicolás Flores", "Nicolás Flores", "Nicolás Flores");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (499, 13, "Nopala de Villagrán", "Nopala de Villa", "Nopala de Villagrán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (500, 13, "Omitlán de Juárez", "Omitlán de Juár", "Omitlán de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (501, 13, "San Felipe Orizatlán", "San Felipe Oriz", "San Felipe Orizatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (502, 13, "Pacula", "Pacula", "Pacula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (503, 13, "Pachuca de Soto", "Pachuca de Soto", "Pachuca de Soto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (504, 13, "Pisaflores", "Pisaflores", "Pisaflores");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (505, 13, "Ajacuba", "Ajacuba", "Ajacuba");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (506, 13, "Progreso de Obregón", "Progreso de Obr", "Progreso de Obregón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (507, 13, "Mineral de la Reforma", "Mineral de la R", "Mineral de la Reforma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (508, 13, "San Agustín Tlaxiaca", "San Agustín Tla", "San Agustín Tlaxiaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (509, 13, "San Bartolo Tutotepec", "San Bartolo Tut", "San Bartolo Tutotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (510, 13, "San Salvador", "San Salvador", "San Salvador");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (511, 13, "Santiago de Anaya", "Santiago de Ana", "Santiago de Anaya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (512, 13, "Santiago Tulantepec de Lugo Guerrero", "Santiago Tulant", "Santiago Tulantepec de Lugo Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (513, 13, "Singuilucan", "Singuilucan", "Singuilucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (514, 13, "Tasquillo", "Tasquillo", "Tasquillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (515, 13, "Tecozautla", "Tecozautla", "Tecozautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (516, 13, "Alfajayucan", "Alfajayucan", "Alfajayucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (517, 13, "Tenango de Doria", "Tenango de Dori", "Tenango de Doria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (518, 13, "Tepeapulco", "Tepeapulco", "Tepeapulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (519, 13, "Tepehuacán de Guerrero", "Tepehuacán de G", "Tepehuacán de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (520, 13, "Tepeji del Río de Ocampo", "Tepeji del Río ", "Tepeji del Río de Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (521, 13, "Tepetitlán", "Tepetitlán", "Tepetitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (522, 13, "Tetepango", "Tetepango", "Tetepango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (523, 13, "Villa de Tezontepec", "Villa de Tezont", "Villa de Tezontepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (524, 13, "Tezontepec de Aldama", "Tezontepec de A", "Tezontepec de Aldama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (525, 13, "Tianguistengo", "Tianguistengo", "Tianguistengo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (526, 13, "Tizayuca", "Tizayuca", "Tizayuca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (527, 13, "Almoloya", "Almoloya", "Almoloya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (528, 13, "Tlahuelilpan", "Tlahuelilpan", "Tlahuelilpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (529, 13, "Tlahuiltepa", "Tlahuiltepa", "Tlahuiltepa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (530, 13, "Tlanalapa", "Tlanalapa", "Tlanalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (531, 13, "Tlanchinol", "Tlanchinol", "Tlanchinol");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (532, 13, "Tlaxcoapan", "Tlaxcoapan", "Tlaxcoapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (533, 13, "Tolcayuca", "Tolcayuca", "Tolcayuca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (534, 13, "Tula de Allende", "Tula de Allende", "Tula de Allende");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (535, 13, "Tulancingo de Bravo", "Tulancingo de B", "Tulancingo de Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (536, 13, "Xochiatipan", "Xochiatipan", "Xochiatipan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (537, 13, "Xochicoatlán", "Xochicoatlán", "Xochicoatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (538, 13, "Apan", "Apan", "Apan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (539, 13, "Yahualica", "Yahualica", "Yahualica");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (540, 13, "Zacualtipán de Ángeles", "Zacualtipán de ", "Zacualtipán de Ángeles");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (541, 13, "Zapotlán de Juárez", "Zapotlán de Juá", "Zapotlán de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (542, 13, "Zempoala", "Zempoala", "Zempoala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (543, 13, "Zimapán", "Zimapán", "Zimapán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (544, 13, "El Arenal", "El Arenal", "El Arenal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (545, 14, "Acatic", "Acatic", "Acatic");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (546, 14, "Atemajac de Brizuela", "Atemajac de Bri", "Atemajac de Brizuela");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (547, 14, "Tomatlán", "Tomatlán", "Tomatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (548, 14, "Tonalá", "Tonalá", "Tonalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (549, 14, "Tonaya", "Tonaya", "Tonaya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (550, 14, "Tonila", "Tonila", "Tonila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (551, 14, "Totatiche", "Totatiche", "Totatiche");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (552, 14, "Tototlán", "Tototlán", "Tototlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (553, 14, "Tuxcacuesco", "Tuxcacuesco", "Tuxcacuesco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (554, 14, "Tuxcueca", "Tuxcueca", "Tuxcueca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (555, 14, "Tuxpan", "Tuxpan", "Tuxpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (556, 14, "Unión de San Antonio", "Unión de San An", "Unión de San Antonio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (557, 14, "Atengo", "Atengo", "Atengo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (558, 14, "Unión de Tula", "Unión de Tula", "Unión de Tula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (559, 14, "Valle de Guadalupe", "Valle de Guadal", "Valle de Guadalupe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (560, 14, "Valle de Juárez", "Valle de Juárez", "Valle de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (561, 14, "San Gabriel", "San Gabriel", "San Gabriel");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (562, 14, "Villa Corona", "Villa Corona", "Villa Corona");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (563, 14, "Villa Guerrero", "Villa Guerrero", "Villa Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (564, 14, "Villa Hidalgo", "Villa Hidalgo", "Villa Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (565, 14, "Cañadas de Obregón", "Cañadas de Obre", "Cañadas de Obregón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (566, 14, "Yahualica de González Gallo", "Yahualica de ", "Yahualica de González Gallo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (567, 14, "Zacoalco de Torres", "Zacoalco de Tor", "Zacoalco de Torres");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (568, 14, "Atenguillo", "Atenguillo", "Atenguillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (569, 14, "Zapopan", "Zapopan", "Zapopan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (570, 14, "Zapotiltic", "Zapotiltic", "Zapotiltic");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (571, 14, "Zapotitlán de Vadillo", "Zapotitlán de V", "Zapotitlán de Vadillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (572, 14, "Zapotlán del Rey", "Zapotlán del Re", "Zapotlán del Rey");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (573, 14, "Zapotlanejo", "Zapotlanejo", "Zapotlanejo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (574, 14, "San Ignacio Cerro Gordo", "San Ignacio Cer", "San Ignacio Cerro Gordo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (575, 14, "Atotonilco el Alto", "Atotonilco el A", "Atotonilco el Alto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (576, 14, "Atoyac", "Atoyac", "Atoyac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (577, 14, "Autlán de Navarro", "Autlán de Navar", "Autlán de Navarro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (578, 14, "Ayotlán", "Ayotlán", "Ayotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (579, 14, "Ayutla", "Ayutla", "Ayutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (580, 14, "La Barca", "La Barca", "La Barca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (581, 14, "Bolaños", "Bolaños", "Bolaños");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (582, 14, "Acatlán de Juárez", "Acatlán de Juár", "Acatlán de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (583, 14, "Cabo Corrientes", "Cabo Corrientes", "Cabo Corrientes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (584, 14, "Casimiro Castillo", "Casimiro Castil", "Casimiro Castillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (585, 14, "Fraccionamiento", "Fraccionamiento", "Fraccionamiento");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (586, 14, "Cihuatlán", "Cihuatlán", "Cihuatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (587, 14, "Zapotlán el Grande", "Zapotlán el Gra", "Zapotlán el Grande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (588, 14, "Cocula", "Cocula", "Cocula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (589, 14, "Colotlán", "Colotlán", "Colotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (590, 14, "Concepción de Buenos Aires", "Concepción de B", "Concepción de Buenos Aires");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (591, 14, "Cuautitlán de García Barragán", "Cuautitlán de G", "Cuautitlán de García Barragán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (592, 14, "Cuautla", "Cuautla", "Cuautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (593, 14, "Cuquío", "Cuquío", "Cuquío");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (594, 14, "Ahualulco de Mercado", "Ahualulco de Me", "Ahualulco de Mercado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (595, 14, "Chapala", "Chapala", "Chapala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (596, 14, "Chimaltitán", "Chimaltitán", "Chimaltitán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (597, 14, "Unidad habitacional", "Unidad habitaci", "Unidad habitacional");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (598, 14, "Chiquilistlán", "Chiquilistlán", "Chiquilistlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (599, 14, "Degollado", "Degollado", "Degollado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (600, 14, "Ejutla", "Ejutla", "Ejutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (601, 14, "Encarnación de Díaz", "Encarnación de ", "Encarnación de Díaz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (602, 14, "Etzatlán", "Etzatlán", "Etzatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (603, 14, "El Grullo", "El Grullo", "El Grullo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (604, 14, "Guachinango", "Guachinango", "Guachinango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (605, 14, "Guadalajara", "Guadalajara", "Guadalajara");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (606, 14, "Amacueca", "Amacueca", "Amacueca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (607, 14, "Hostotipaquillo", "Hostotipaquillo", "Hostotipaquillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (608, 14, "Huejúcar", "Huejúcar", "Huejúcar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (609, 14, "Huejuquilla el Alto", "Huejuquilla el ", "Huejuquilla el Alto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (610, 14, "La Huerta", "La Huerta", "La Huerta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (611, 14, "Ixtlahuacán de los Membrillos", "Ixtlahuacán de ", "Ixtlahuacán de los Membrillos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (612, 14, "Ixtlahuacán del Río", "Ixtlahuacán del", "Ixtlahuacán del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (613, 14, "Jalostotitlán", "Jalostotitlán", "Jalostotitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (614, 14, "Jamay", "Jamay", "Jamay");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (615, 14, "Jesús María", "Jesús María", "Jesús María");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (616, 14, "Jilotlán de los Dolores", "Jilotlán de los", "Jilotlán de los Dolores");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (617, 14, "Amatitán", "Amatitán", "Amatitán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (618, 14, "Jocotepec", "Jocotepec", "Jocotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (619, 14, "Juanacatlán", "Juanacatlán", "Juanacatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (620, 14, "Juchitlán", "Juchitlán", "Juchitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (621, 14, "Lagos de Moreno", "Lagos de Moreno", "Lagos de Moreno");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (622, 14, "El Limón", "El Limón", "El Limón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (623, 14, "Magdalena", "Magdalena", "Magdalena");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (624, 14, "Santa María del Oro", "Santa María del", "Santa María del Oro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (625, 14, "La Manzanilla de la Paz", "La Manzanilla d", "La Manzanilla de la Paz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (626, 14, "Mascota", "Mascota", "Mascota");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (627, 14, "Mazamitla", "Mazamitla", "Mazamitla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (628, 14, "Ameca", "Ameca", "Ameca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (629, 14, "Mexticacán", "Mexticacán", "Mexticacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (630, 14, "Mezquitic", "Mezquitic", "Mezquitic");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (631, 14, "Mixtlán", "Mixtlán", "Mixtlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (632, 14, "Ocotlán", "Ocotlán", "Ocotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (633, 14, "Ojuelos de Jalisco", "Ojuelos de Jali", "Ojuelos de Jalisco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (634, 14, "Pihuamo", "Pihuamo", "Pihuamo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (635, 14, "Poncitlán", "Poncitlán", "Poncitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (636, 14, "Puerto Vallarta", "Puerto Vallarta", "Puerto Vallarta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (637, 14, "Villa Purificación", "Villa Purificac", "Villa Purificación");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (638, 14, "Quitupan", "Quitupan", "Quitupan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (639, 14, "San Juanito de Escobedo", "San Juanito de ", "San Juanito de Escobedo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (640, 14, "El Salto", "El Salto", "El Salto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (641, 14, "San Cristóbal de la Barranca", "San Cristóbal d", "San Cristóbal de la Barranca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (642, 14, "San Diego de Alejandría", "San Diego de Al", "San Diego de Alejandría");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (643, 14, "San Juan de los Lagos", "San Juan de los", "San Juan de los Lagos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (644, 14, "San Julián", "San Julián", "San Julián");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (645, 14, "San Marcos", "San Marcos", "San Marcos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (646, 14, "San Martín de Bolaños", "San Martín de B", "San Martín de Bolaños");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (647, 14, "San Martín Hidalgo", "San Martín Hida", "San Martín Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (648, 14, "San Miguel el Alto", "San Miguel el A", "San Miguel el Alto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (649, 14, "Gómez Farías", "Gómez Farías", "Gómez Farías");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (650, 14, "Arandas", "Arandas", "Arandas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (651, 14, "San Sebastián del Oeste", "San Sebastián d", "San Sebastián del Oeste");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (652, 14, "Santa María de los Ángeles", "Santa María de ", "Santa María de los Ángeles");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (653, 14, "Sayula", "Sayula", "Sayula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (654, 14, "Tala", "Tala", "Tala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (655, 14, "Talpa de Allende", "Talpa de Allend", "Talpa de Allende");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (656, 14, "Tamazula de Gordiano", "Tamazula de Gor", "Tamazula de Gordiano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (657, 14, "Tapalpa", "Tapalpa", "Tapalpa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (658, 14, "Tecalitlán", "Tecalitlán", "Tecalitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (659, 14, "Tecolotlán", "Tecolotlán", "Tecolotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (660, 14, "Techaluta de Montenegro", "Techaluta de Mo", "Techaluta de Montenegro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (661, 14, "El Arenal", "El Arenal", "El Arenal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (662, 14, "Tenamaxtlán", "Tenamaxtlán", "Tenamaxtlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (663, 14, "Teocaltiche", "Teocaltiche", "Teocaltiche");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (664, 14, "Teocuitatlán de Corona", "Teocuitatlán de", "Teocuitatlán de Corona");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (665, 14, "Tepatitlán de Morelos", "Tepatitlán de M", "Tepatitlán de Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (666, 14, "Tequila", "Tequila", "Tequila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (667, 14, "Teuchitlán", "Teuchitlán", "Teuchitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (668, 14, "Tizapán el Alto", "Tizapán el Alto", "Tizapán el Alto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (669, 14, "Tlajomulco de Zúñiga", "Tlajomulco de Z", "Tlajomulco de Zúñiga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (670, 14, "San Pedro Tlaquepaque", "San Pedro Tlaqu", "San Pedro Tlaquepaque");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (671, 14, "Tolimán", "Tolimán", "Tolimán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (672, 15, "Acambay de Ruíz Castañeda", "Acambay de Ruíz", "Acambay de Ruíz Castañeda");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (673, 15, "Apaxco", "Apaxco", "Apaxco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (674, 15, "Tezoyuca", "Tezoyuca", "Tezoyuca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (675, 15, "Tianguistenco", "Tianguistenco", "Tianguistenco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (676, 15, "Timilpan", "Timilpan", "Timilpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (677, 15, "Tlalmanalco", "Tlalmanalco", "Tlalmanalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (678, 15, "Tlalnepantla de Baz", "Tlalnepantla de", "Tlalnepantla de Baz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (679, 15, "Tlatlaya", "Tlatlaya", "Tlatlaya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (680, 15, "Toluca", "Toluca", "Toluca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (681, 15, "Tonatico", "Tonatico", "Tonatico");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (682, 15, "Tultepec", "Tultepec", "Tultepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (683, 15, "Tultitlán", "Tultitlán", "Tultitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (684, 15, "Atenco", "Atenco", "Atenco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (685, 15, "Valle de Bravo", "Valle de Bravo", "Valle de Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (686, 15, "Villa de Allende", "Villa de Allend", "Villa de Allende");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (687, 15, "Villa del Carbón", "Villa del Carbó", "Villa del Carbón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (688, 15, "Villa Guerrero", "Villa Guerrero", "Villa Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (689, 15, "Villa Victoria", "Villa Victoria", "Villa Victoria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (690, 15, "Xonacatlán", "Xonacatlán", "Xonacatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (691, 15, "Zacazonapan", "Zacazonapan", "Zacazonapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (692, 15, "Zacualpan", "Zacualpan", "Zacualpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (693, 15, "Zinacantepec", "Zinacantepec", "Zinacantepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (694, 15, "Zumpahuacán", "Zumpahuacán", "Zumpahuacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (695, 15, "Atizapán", "Atizapán", "Atizapán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (696, 15, "Zumpango", "Zumpango", "Zumpango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (697, 15, "Cuautitlán Izcalli", "Cuautitlán Izca", "Cuautitlán Izcalli");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (698, 15, "Valle de Chalco Solidaridad", "Valle de Chalco", "Valle de Chalco Solidaridad");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (699, 15, "Luvianos", "Luvianos", "Luvianos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (700, 15, "San José del Rincón", "San José del Ri", "San José del Rincón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (701, 15, "Tonanitla", "Tonanitla", "Tonanitla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (702, 15, "Atizapán de Zaragoza", "Atizapán de Zar", "Atizapán de Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (703, 15, "Atlacomulco", "Atlacomulco", "Atlacomulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (704, 15, "Atlautla", "Atlautla", "Atlautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (705, 15, "Ejido", "Ejido", "Ejido");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (706, 15, "Axapusco", "Axapusco", "Axapusco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (707, 15, "Ayapango", "Ayapango", "Ayapango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (708, 15, "Calimaya", "Calimaya", "Calimaya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (709, 15, "Capulhuac", "Capulhuac", "Capulhuac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (710, 15, "Acolman", "Acolman", "Acolman");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (711, 15, "Coacalco de Berriozábal", "Coacalco de Ber", "Coacalco de Berriozábal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (712, 15, "Coatepec Harinas", "Coatepec Harina", "Coatepec Harinas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (713, 15, "Cocotitlán", "Cocotitlán", "Cocotitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (714, 15, "Coyotepec", "Coyotepec", "Coyotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (715, 15, "Cuautitlán", "Cuautitlán", "Cuautitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (716, 15, "Chalco", "Chalco", "Chalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (717, 15, "Chapa de Mota", "Chapa de Mota", "Chapa de Mota");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (718, 15, "Chapultepec", "Chapultepec", "Chapultepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (719, 15, "Chiautla", "Chiautla", "Chiautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (720, 15, "Chicoloapan", "Chicoloapan", "Chicoloapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (721, 15, "Aculco", "Aculco", "Aculco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (722, 15, "Chiconcuac", "Chiconcuac", "Chiconcuac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (723, 15, "Chimalhuacán", "Chimalhuacán", "Chimalhuacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (724, 15, "Donato Guerra", "Donato Guerra", "Donato Guerra");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (725, 15, "Ecatepec de Morelos", "Ecatepec de Mor", "Ecatepec de Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (726, 15, "Ecatzingo", "Ecatzingo", "Ecatzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (727, 15, "Huehuetoca", "Huehuetoca", "Huehuetoca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (728, 15, "Hueypoxtla", "Hueypoxtla", "Hueypoxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (729, 15, "Huixquilucan", "Huixquilucan", "Huixquilucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (730, 15, "Isidro Fabela", "Isidro Fabela", "Isidro Fabela");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (731, 15, "Ixtapaluca", "Ixtapaluca", "Ixtapaluca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (732, 15, "Almoloya de Alquisiras", "Almoloya de Alq", "Almoloya de Alquisiras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (733, 15, "Ixtapan de la Sal", "Ixtapan de la S", "Ixtapan de la Sal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (734, 15, "Ixtapan del Oro", "Ixtapan del Oro", "Ixtapan del Oro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (735, 15, "Ixtlahuaca", "Ixtlahuaca", "Ixtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (736, 15, "Xalatlaco", "Xalatlaco", "Xalatlaco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (737, 15, "Jaltenco", "Jaltenco", "Jaltenco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (738, 15, "Jilotepec", "Jilotepec", "Jilotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (739, 15, "Jilotzingo", "Jilotzingo", "Jilotzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (740, 15, "Jiquipilco", "Jiquipilco", "Jiquipilco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (741, 15, "Jocotitlán", "Jocotitlán", "Jocotitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (742, 15, "Joquicingo", "Joquicingo", "Joquicingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (743, 15, "Almoloya de Juárez", "Almoloya de Juá", "Almoloya de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (744, 15, "Juchitepec", "Juchitepec", "Juchitepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (745, 15, "Lerma", "Lerma", "Lerma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (746, 15, "Malinalco", "Malinalco", "Malinalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (747, 15, "Melchor Ocampo", "Melchor Ocampo", "Melchor Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (748, 15, "Metepec", "Metepec", "Metepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (749, 15, "Mexicaltzingo", "Mexicaltzingo", "Mexicaltzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (750, 15, "Morelos", "Morelos", "Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (751, 15, " III", " III", " III");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (752, 15, "Naucalpan de Juárez", "Naucalpan de Ju", "Naucalpan de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (753, 15, "Nezahualcóyotl", "Nezahualcóyotl", "Nezahualcóyotl");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (754, 15, "Nextlalpan", "Nextlalpan", "Nextlalpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (755, 15, "Almoloya del Río", "Almoloya del Rí", "Almoloya del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (756, 15, "Nicolás Romero", "Nicolás Romero", "Nicolás Romero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (757, 15, "Nopaltepec", "Nopaltepec", "Nopaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (758, 15, "Ocoyoacac", "Ocoyoacac", "Ocoyoacac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (759, 15, "Ocuilan", "Ocuilan", "Ocuilan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (760, 15, "El Oro", "El Oro", "El Oro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (761, 15, "Otumba", "Otumba", "Otumba");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (762, 15, "Otzoloapan", "Otzoloapan", "Otzoloapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (763, 15, "Otzolotepec", "Otzolotepec", "Otzolotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (764, 15, "Ozumba", "Ozumba", "Ozumba");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (765, 15, "Papalotla", "Papalotla", "Papalotla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (766, 15, "Amanalco", "Amanalco", "Amanalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (767, 15, "La Paz", "La Paz", "La Paz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (768, 15, "Polotitlán", "Polotitlán", "Polotitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (769, 15, "Rayón", "Rayón", "Rayón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (770, 15, "San Antonio la Isla", "San Antonio la ", "San Antonio la Isla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (771, 15, "San Felipe del Progreso", "San Felipe del ", "San Felipe del Progreso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (772, 15, "San Martín de las Pirámides", "San Martín de l", "San Martín de las Pirámides");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (773, 15, "San Mateo Atenco", "San Mateo Atenc", "San Mateo Atenco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (774, 15, "San Simón de Guerrero", "San Simón de Gu", "San Simón de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (775, 15, "Santo Tomás", "Santo Tomás", "Santo Tomás");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (776, 15, "Soyaniquilpan de Juárez", "Soyaniquilpan d", "Soyaniquilpan de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (777, 15, "Amatepec", "Amatepec", "Amatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (778, 15, "Sultepec", "Sultepec", "Sultepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (779, 15, "Tecámac", "Tecámac", "Tecámac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (780, 15, "Tejupilco", "Tejupilco", "Tejupilco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (781, 15, "Temamatla", "Temamatla", "Temamatla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (782, 15, "Temascalapa", "Temascalapa", "Temascalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (783, 15, "Temascalcingo", "Temascalcingo", "Temascalcingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (784, 15, "Temascaltepec", "Temascaltepec", "Temascaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (785, 15, "Temoaya", "Temoaya", "Temoaya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (786, 15, "Tenancingo", "Tenancingo", "Tenancingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (787, 15, "Tenango del Aire", "Tenango del Air", "Tenango del Aire");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (788, 15, "Amecameca", "Amecameca", "Amecameca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (789, 15, "Colonia", "Colonia", "Colonia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (790, 15, "Tenango del Valle", "Tenango del Val", "Tenango del Valle");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (791, 15, "Teoloyucan", "Teoloyucan", "Teoloyucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (792, 15, "Teotihuacán", "Teotihuacán", "Teotihuacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (793, 15, "Tepetlaoxtoc", "Tepetlaoxtoc", "Tepetlaoxtoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (794, 15, "Tepetlixpa", "Tepetlixpa", "Tepetlixpa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (795, 15, "Tepotzotlán", "Tepotzotlán", "Tepotzotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (796, 15, "Tequixquiac", "Tequixquiac", "Tequixquiac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (797, 15, "Texcaltitlán", "Texcaltitlán", "Texcaltitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (798, 15, "Texcalyacac", "Texcalyacac", "Texcalyacac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (799, 15, "Texcoco", "Texcoco", "Texcoco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (800, 16, "Acuitzio", "Acuitzio", "Acuitzio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (801, 16, "Arteaga", "Arteaga", "Arteaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (802, 16, "Tzintzuntzan", "Tzintzuntzan", "Tzintzuntzan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (803, 16, "Tzitzio", "Tzitzio", "Tzitzio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (804, 16, "Uruapan", "Uruapan", "Uruapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (805, 16, "Venustiano Carranza", "Venustiano Carr", "Venustiano Carranza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (806, 16, "Villamar", "Villamar", "Villamar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (807, 16, "Vista Hermosa", "Vista Hermosa", "Vista Hermosa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (808, 16, "Yurécuaro", "Yurécuaro", "Yurécuaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (809, 16, "Zacapu", "Zacapu", "Zacapu");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (810, 16, "Zamora", "Zamora", "Zamora");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (811, 16, "Zináparo", "Zináparo", "Zináparo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (812, 16, "Briseñas", "Briseñas", "Briseñas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (813, 16, "Zinapécuaro", "Zinapécuaro", "Zinapécuaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (814, 16, "Ziracuaretiro", "Ziracuaretiro", "Ziracuaretiro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (815, 16, "Zitácuaro", "Zitácuaro", "Zitácuaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (816, 16, "José Sixto Verduzco", "José Sixto Verd", "José Sixto Verduzco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (817, 16, "Buenavista", "Buenavista", "Buenavista");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (818, 16, "Carácuaro", "Carácuaro", "Carácuaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (819, 16, "Coahuayana", "Coahuayana", "Coahuayana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (820, 16, "Coalcomán de Vázquez Pallares", "Coalcomán de Vá", "Coalcomán de Vázquez Pallares");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (821, 16, "Coeneo", "Coeneo", "Coeneo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (822, 16, "Contepec", "Contepec", "Contepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (823, 16, "Copándaro", "Copándaro", "Copándaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (824, 16, "Cotija", "Cotija", "Cotija");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (825, 16, "Aguililla", "Aguililla", "Aguililla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (826, 16, "Cuitzeo", "Cuitzeo", "Cuitzeo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (827, 16, "Charapan", "Charapan", "Charapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (828, 16, "Fraccionamiento", "Fraccionamiento", "Fraccionamiento");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (829, 16, "Charo", "Charo", "Charo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (830, 16, "Chavinda", "Chavinda", "Chavinda");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (831, 16, "Cherán", "Cherán", "Cherán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (832, 16, "Chilchota", "Chilchota", "Chilchota");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (833, 16, "Chinicuila", "Chinicuila", "Chinicuila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (834, 16, "Chucándiro", "Chucándiro", "Chucándiro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (835, 16, "Churintzio", "Churintzio", "Churintzio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (836, 16, "Churumuco", "Churumuco", "Churumuco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (837, 16, "Álvaro Obregón", "Álvaro Obregón", "Álvaro Obregón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (838, 16, "Ecuandureo", "Ecuandureo", "Ecuandureo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (839, 16, "Epitacio Huerta", "Epitacio Huerta", "Epitacio Huerta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (840, 16, "Erongarícuaro", "Erongarícuaro", "Erongarícuaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (841, 16, "Gabriel Zamora", "Gabriel Zamora", "Gabriel Zamora");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (842, 16, "Hidalgo", "Hidalgo", "Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (843, 16, "La Huacana", "La Huacana", "La Huacana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (844, 16, "Huandacareo", "Huandacareo", "Huandacareo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (845, 16, "Huaniqueo", "Huaniqueo", "Huaniqueo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (846, 16, "Huetamo", "Huetamo", "Huetamo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (847, 16, "Huiramba", "Huiramba", "Huiramba");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (848, 16, "Angamacutiro", "Angamacutiro", "Angamacutiro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (849, 16, "Indaparapeo", "Indaparapeo", "Indaparapeo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (850, 16, "Irimbo", "Irimbo", "Irimbo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (851, 16, "Ixtlán", "Ixtlán", "Ixtlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (852, 16, "Jacona", "Jacona", "Jacona");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (853, 16, "Jiménez", "Jiménez", "Jiménez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (854, 16, "Jiquilpan", "Jiquilpan", "Jiquilpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (855, 16, "Juárez", "Juárez", "Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (856, 16, "Jungapeo", "Jungapeo", "Jungapeo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (857, 16, "Lagunillas", "Lagunillas", "Lagunillas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (858, 16, "Madero", "Madero", "Madero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (859, 16, "Angangueo", "Angangueo", "Angangueo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (860, 16, "Maravatío", "Maravatío", "Maravatío");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (861, 16, "Marcos Castellanos", "Marcos Castella", "Marcos Castellanos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (862, 16, "Lázaro Cárdenas", "Lázaro Cárdenas", "Lázaro Cárdenas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (863, 16, "Morelia", "Morelia", "Morelia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (864, 16, "Morelos", "Morelos", "Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (865, 16, "Múgica", "Múgica", "Múgica");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (866, 16, "Nahuatzen", "Nahuatzen", "Nahuatzen");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (867, 16, "Nocupétaro", "Nocupétaro", "Nocupétaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (868, 16, "Nuevo Parangaricutiro", "Nuevo Parangari", "Nuevo Parangaricutiro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (869, 16, "Nuevo Urecho", "Nuevo Urecho", "Nuevo Urecho");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (870, 16, "Apatzingán", "Apatzingán", "Apatzingán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (871, 16, "Numarán", "Numarán", "Numarán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (872, 16, "Ocampo", "Ocampo", "Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (873, 16, "Pajacuarán", "Pajacuarán", "Pajacuarán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (874, 16, "Panindícuaro", "Panindícuaro", "Panindícuaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (875, 16, "Parácuaro", "Parácuaro", "Parácuaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (876, 16, "Paracho", "Paracho", "Paracho");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (877, 16, "Pátzcuaro", "Pátzcuaro", "Pátzcuaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (878, 16, "Penjamillo", "Penjamillo", "Penjamillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (879, 16, "Peribán", "Peribán", "Peribán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (880, 16, "La Piedad", "La Piedad", "La Piedad");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (881, 16, "Aporo", "Aporo", "Aporo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (882, 16, "Purépero", "Purépero", "Purépero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (883, 16, "Puruándiro", "Puruándiro", "Puruándiro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (884, 16, "Queréndaro", "Queréndaro", "Queréndaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (885, 16, "Quiroga", "Quiroga", "Quiroga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (886, 16, "Cojumatlán de Régules", "Cojumatlán de R", "Cojumatlán de Régules");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (887, 16, "Los Reyes", "Los Reyes", "Los Reyes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (888, 16, "Sahuayo", "Sahuayo", "Sahuayo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (889, 16, "San Lucas", "San Lucas", "San Lucas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (890, 16, "Santa Ana Maya", "Santa Ana Maya", "Santa Ana Maya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (891, 16, "Salvador Escalante", "Salvador Escala", "Salvador Escalante");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (892, 16, "Aquila", "Aquila", "Aquila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (893, 16, "Senguio", "Senguio", "Senguio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (894, 16, "Susupuato", "Susupuato", "Susupuato");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (895, 16, "Tacámbaro", "Tacámbaro", "Tacámbaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (896, 16, "Tancítaro", "Tancítaro", "Tancítaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (897, 16, "Tangamandapio", "Tangamandapio", "Tangamandapio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (898, 16, "Tangancícuaro", "Tangancícuaro", "Tangancícuaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (899, 16, "Tanhuato", "Tanhuato", "Tanhuato");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (900, 16, "Taretan", "Taretan", "Taretan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (901, 16, "Tarímbaro", "Tarímbaro", "Tarímbaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (902, 16, "Tepalcatepec", "Tepalcatepec", "Tepalcatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (903, 16, "Ario", "Ario", "Ario");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (904, 16, "Tingambato", "Tingambato", "Tingambato");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (905, 16, "Tingüindín", "Tingüindín", "Tingüindín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (906, 16, "Tiquicheo de Nicolás Romero", "Tiquicheo de Ni", "Tiquicheo de Nicolás Romero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (907, 16, "Tlalpujahua", "Tlalpujahua", "Tlalpujahua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (908, 16, "Tlazazalca", "Tlazazalca", "Tlazazalca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (909, 16, "Tocumbo", "Tocumbo", "Tocumbo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (910, 16, "Tumbiscatío", "Tumbiscatío", "Tumbiscatío");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (911, 16, "Turicato", "Turicato", "Turicato");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (912, 16, "Tuxpan", "Tuxpan", "Tuxpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (913, 16, "Tuzantla", "Tuzantla", "Tuzantla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (914, 17, "Amacuzac", "Amacuzac", "Amacuzac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (915, 17, "Jantetelco", "Jantetelco", "Jantetelco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (916, 17, "Jiutepec", "Jiutepec", "Jiutepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (917, 17, "Jojutla", "Jojutla", "Jojutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (918, 17, "Jonacatepec", "Jonacatepec", "Jonacatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (919, 17, "Mazatepec", "Mazatepec", "Mazatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (920, 17, "Miacatlán", "Miacatlán", "Miacatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (921, 17, "Ocuituco", "Ocuituco", "Ocuituco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (922, 17, "Puente de Ixtla", "Puente de Ixtla", "Puente de Ixtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (923, 17, "Temixco", "Temixco", "Temixco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (924, 17, "Tepalcingo", "Tepalcingo", "Tepalcingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (925, 17, "Atlatlahucan", "Atlatlahucan", "Atlatlahucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (926, 17, "Tepoztlán", "Tepoztlán", "Tepoztlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (927, 17, "Tetecala", "Tetecala", "Tetecala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (928, 17, "Tetela del Volcán", "Tetela del Volc", "Tetela del Volcán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (929, 17, "Tlalnepantla", "Tlalnepantla", "Tlalnepantla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (930, 17, "Tlaltizapán de Zapata", "Tlaltizapán de ", "Tlaltizapán de Zapata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (931, 17, "Tlaquiltenango", "Tlaquiltenango", "Tlaquiltenango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (932, 17, "Tlayacapan", "Tlayacapan", "Tlayacapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (933, 17, "Totolapan", "Totolapan", "Totolapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (934, 17, "Xochitepec", "Xochitepec", "Xochitepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (935, 17, "Yautepec", "Yautepec", "Yautepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (936, 17, "Axochiapan", "Axochiapan", "Axochiapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (937, 17, "Yecapixtla", "Yecapixtla", "Yecapixtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (938, 17, "Zacatepec", "Zacatepec", "Zacatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (939, 17, "Zacualpan", "Zacualpan", "Zacualpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (940, 17, "Temoac", "Temoac", "Temoac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (941, 17, "Ayala", "Ayala", "Ayala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (942, 17, "Coatlán del Río", "Coatlán del Río", "Coatlán del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (943, 17, "Cuautla", "Cuautla", "Cuautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (944, 17, " III", " III", " III");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (945, 17, "Cuernavaca", "Cuernavaca", "Cuernavaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (946, 17, "Emiliano Zapata", "Emiliano Zapata", "Emiliano Zapata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (947, 17, "Huitzilac", "Huitzilac", "Huitzilac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (948, 18, "Acaponeta", "Acaponeta", "Acaponeta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (949, 18, "Rosamorada", "Rosamorada", "Rosamorada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (950, 18, "Ruíz", "Ruíz", "Ruíz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (951, 18, "San Blas", "San Blas", "San Blas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (952, 18, "San Pedro Lagunillas", "San Pedro Lagun", "San Pedro Lagunillas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (953, 18, "Santa María del Oro", "Santa María del", "Santa María del Oro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (954, 18, "Santiago Ixcuintla", "Santiago Ixcuin", "Santiago Ixcuintla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (955, 18, "Tecuala", "Tecuala", "Tecuala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (956, 18, "Tepic", "Tepic", "Tepic");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (957, 18, "Tuxpan", "Tuxpan", "Tuxpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (958, 18, "La Yesca", "La Yesca", "La Yesca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (959, 18, "Ahuacatlán", "Ahuacatlán", "Ahuacatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (960, 18, "Bahía de Banderas", "Bahía de Bander", "Bahía de Banderas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (961, 18, "Amatlán de Cañas", "Amatlán de Caña", "Amatlán de Cañas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (962, 18, "Compostela", "Compostela", "Compostela");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (963, 18, "Huajicori", "Huajicori", "Huajicori");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (964, 18, "Ixtlán del Río", "Ixtlán del Río", "Ixtlán del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (965, 18, "Jala", "Jala", "Jala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (966, 18, "Xalisco", "Xalisco", "Xalisco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (967, 18, "Del Nayar", "Del Nayar", "Del Nayar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (968, 19, "Abasolo", "Abasolo", "Abasolo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (969, 19, "El Carmen", "El Carmen", "El Carmen");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (970, 19, "Cerralvo", "Cerralvo", "Cerralvo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (971, 19, "Ciénega de Flores", "Ciénega de Flor", "Ciénega de Flores");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (972, 19, "China", "China", "China");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (973, 19, "Doctor Arroyo", "Doctor Arroyo", "Doctor Arroyo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (974, 19, "Doctor Coss", "Doctor Coss", "Doctor Coss");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (975, 19, "Doctor González", "Doctor González", "Doctor González");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (976, 19, "Galeana", "Galeana", "Galeana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (977, 19, "García", "García", "García");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (978, 19, "San Pedro Garza García", "San Pedro Garza", "San Pedro Garza García");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (979, 19, "Agualeguas", "Agualeguas", "Agualeguas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (980, 19, "General Bravo", "General Bravo", "General Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (981, 19, "Fraccionamiento", "Fraccionamiento", "Fraccionamiento");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (982, 19, "General Escobedo", "General Escobed", "General Escobedo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (983, 19, "General Terán", "General Terán", "General Terán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (984, 19, "General Treviño", "General Treviño", "General Treviño");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (985, 19, "General Zaragoza", "General Zaragoz", "General Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (986, 19, "General Zuazua", "General Zuazua", "General Zuazua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (987, 19, "Guadalupe", "Guadalupe", "Guadalupe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (988, 19, "Los Herreras", "Los Herreras", "Los Herreras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (989, 19, "Higueras", "Higueras", "Higueras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (990, 19, "Hualahuises", "Hualahuises", "Hualahuises");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (991, 19, "Los Aldamas", "Los Aldamas", "Los Aldamas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (992, 19, "Iturbide", "Iturbide", "Iturbide");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (993, 19, "Residencial", "Residencial", "Residencial");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (994, 19, "Juárez", "Juárez", "Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (995, 19, "Lampazos de Naranjo", "Lampazos de Nar", "Lampazos de Naranjo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (996, 19, "Linares", "Linares", "Linares");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (997, 19, "Marín", "Marín", "Marín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (998, 19, "Melchor Ocampo", "Melchor Ocampo", "Melchor Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (999, 19, "Mier y Noriega", "Mier y Noriega", "Mier y Noriega");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1000, 19, "Mina", "Mina", "Mina");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1001, 19, "Montemorelos", "Montemorelos", "Montemorelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1002, 19, "Monterrey", "Monterrey", "Monterrey");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1003, 19, "Allende", "Allende", "Allende");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1004, 19, "Parás", "Parás", "Parás");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1005, 19, "Pesquería", "Pesquería", "Pesquería");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1006, 19, "Los Ramones", "Los Ramones", "Los Ramones");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1007, 19, "Rayones", "Rayones", "Rayones");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1008, 19, "Sabinas Hidalgo", "Sabinas Hidalgo", "Sabinas Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1009, 19, "Salinas Victoria", "Salinas Victori", "Salinas Victoria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1010, 19, "San Nicolás de los Garza", "San Nicolás de ", "San Nicolás de los Garza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1011, 19, "Hidalgo", "Hidalgo", "Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1012, 19, "Santa Catarina", "Santa Catarina", "Santa Catarina");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1013, 19, "Santiago", "Santiago", "Santiago");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1014, 19, "Anáhuac", "Anáhuac", "Anáhuac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1015, 19, "Vallecillo", "Vallecillo", "Vallecillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1016, 19, "Villaldama", "Villaldama", "Villaldama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1017, 19, "Apodaca", "Apodaca", "Apodaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1018, 19, "22", "22", "22");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1019, 19, "3", "3", "3");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1020, 19, "Aramberri", "Aramberri", "Aramberri");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1021, 19, "Bustamante", "Bustamante", "Bustamante");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1022, 19, "Cadereyta Jiménez", "Cadereyta Jimén", "Cadereyta Jiménez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1023, 19, "Colonia", "Colonia", "Colonia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1024, 20, "Abejones", "Abejones", "Abejones");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1025, 20, "El Barrio de la Soledad", "El Barrio de la", "El Barrio de la Soledad");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1026, 20, "San Andrés Yaá", "San Andrés Yaá", "San Andrés Yaá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1027, 20, "San Andrés Zabache", "San Andrés Zaba", "San Andrés Zabache");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1028, 20, "San Andrés Zautla", "San Andrés Zaut", "San Andrés Zautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1029, 20, "San Antonino Castillo Velasco", "San Antonino Ca", "San Antonino Castillo Velasco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1030, 20, "San Antonino el Alto", "San Antonino el", "San Antonino el Alto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1031, 20, "San Antonino Monte Verde", "San Antonino Mo", "San Antonino Monte Verde");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1032, 20, "San Antonio Acutla", "San Antonio Acu", "San Antonio Acutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1033, 20, "San Antonio de la Cal", "San Antonio de ", "San Antonio de la Cal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1034, 20, "San Antonio Huitepec", "San Antonio Hui", "San Antonio Huitepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1035, 20, "San Antonio Nanahuatípam", "San Antonio Nan", "San Antonio Nanahuatípam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1036, 20, "Calihualá", "Calihualá", "Calihualá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1037, 20, "San Antonio Sinicahua", "San Antonio Sin", "San Antonio Sinicahua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1038, 20, "San Antonio Tepetlapa", "San Antonio Tep", "San Antonio Tepetlapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1039, 20, "San Baltazar Chichicápam", "San Baltazar Ch", "San Baltazar Chichicápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1040, 20, "San Baltazar Loxicha", "San Baltazar Lo", "San Baltazar Loxicha");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1041, 20, "San Baltazar Yatzachi el Bajo", "San Baltazar Ya", "San Baltazar Yatzachi el Bajo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1042, 20, "San Bartolo Coyotepec", "San Bartolo Coy", "San Bartolo Coyotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1043, 20, "San Bartolomé Ayautla", "San Bartolomé A", "San Bartolomé Ayautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1044, 20, "San Bartolomé Loxicha", "San Bartolomé L", "San Bartolomé Loxicha");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1045, 20, "San Bartolomé Quialana", "San Bartolomé Q", "San Bartolomé Quialana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1046, 20, "San Bartolomé Yucuañe", "San Bartolomé Y", "San Bartolomé Yucuañe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1047, 20, "Candelaria Loxicha", "Candelaria Loxi", "Candelaria Loxicha");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1048, 20, "San Bartolomé Zoogocho", "San Bartolomé Z", "San Bartolomé Zoogocho");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1049, 20, "San Bartolo Soyaltepec", "San Bartolo Soy", "San Bartolo Soyaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1050, 20, "San Bartolo Yautepec", "San Bartolo Yau", "San Bartolo Yautepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1051, 20, "San Bernardo Mixtepec", "San Bernardo Mi", "San Bernardo Mixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1052, 20, "San Blas Atempa", "San Blas Atempa", "San Blas Atempa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1053, 20, "San Carlos Yautepec", "San Carlos Yaut", "San Carlos Yautepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1054, 20, "San Cristóbal Amatlán", "San Cristóbal A", "San Cristóbal Amatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1055, 20, "San Cristóbal Amoltepec", "San Cristóbal A", "San Cristóbal Amoltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1056, 20, "San Cristóbal Lachirioag", "San Cristóbal L", "San Cristóbal Lachirioag");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1057, 20, "San Cristóbal Suchixtlahuaca", "San Cristóbal S", "San Cristóbal Suchixtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1058, 20, "Ciénega de Zimatlán", "Ciénega de Zima", "Ciénega de Zimatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1059, 20, "San Dionisio del Mar", "San Dionisio de", "San Dionisio del Mar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1060, 20, "San Dionisio Ocotepec", "San Dionisio Oc", "San Dionisio Ocotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1061, 20, "San Dionisio Ocotlán", "San Dionisio Oc", "San Dionisio Ocotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1062, 20, "San Esteban Atatlahuca", "San Esteban Ata", "San Esteban Atatlahuca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1063, 20, "San Felipe Jalapa de Díaz", "San Felipe Jala", "San Felipe Jalapa de Díaz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1064, 20, "San Felipe Tejalápam", "San Felipe Teja", "San Felipe Tejalápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1065, 20, "San Felipe Usila", "San Felipe Usil", "San Felipe Usila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1066, 20, "San Francisco Cahuacuá", "San Francisco C", "San Francisco Cahuacuá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1067, 20, "San Francisco Cajonos", "San Francisco C", "San Francisco Cajonos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1068, 20, "San Francisco Chapulapa", "San Francisco C", "San Francisco Chapulapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1069, 20, "Ciudad Ixtepec", "Ciudad Ixtepec", "Ciudad Ixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1070, 20, "San Francisco Chindúa", "San Francisco C", "San Francisco Chindúa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1071, 20, "San Francisco del Mar", "San Francisco d", "San Francisco del Mar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1072, 20, "San Francisco Huehuetlán", "San Francisco H", "San Francisco Huehuetlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1073, 20, "San Francisco Ixhuatán", "San Francisco I", "San Francisco Ixhuatán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1074, 20, "San Francisco Jaltepetongo", "San Francisco J", "San Francisco Jaltepetongo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1075, 20, "San Francisco Lachigoló", "San Francisco L", "San Francisco Lachigoló");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1076, 20, "San Francisco Logueche", "San Francisco L", "San Francisco Logueche");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1077, 20, "San Francisco Nuxaño", "San Francisco ", "San Francisco Nuxaño");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1078, 20, "San Francisco Ozolotepec", "San Francisco O", "San Francisco Ozolotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1079, 20, "San Francisco Sola", "San Francisco S", "San Francisco Sola");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1080, 20, "Coatecas Altas", "Coatecas Altas", "Coatecas Altas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1081, 20, "San Francisco Telixtlahuaca", "San Francisco T", "San Francisco Telixtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1082, 20, "San Francisco Teopan", "San Francisco T", "San Francisco Teopan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1083, 20, "San Francisco Tlapancingo", "San Francisco T", "San Francisco Tlapancingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1084, 20, "San Gabriel Mixtepec", "San Gabriel Mix", "San Gabriel Mixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1085, 20, "San Ildefonso Amatlán", "San Ildefonso A", "San Ildefonso Amatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1086, 20, "San Ildefonso Sola", "San Ildefonso S", "San Ildefonso Sola");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1087, 20, "San Ildefonso Villa Alta", "San Ildefonso V", "San Ildefonso Villa Alta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1088, 20, "San Jacinto Amilpas", "San Jacinto Ami", "San Jacinto Amilpas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1089, 20, "San Jacinto Tlacotepec", "San Jacinto Tla", "San Jacinto Tlacotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1090, 20, "San Jerónimo Coatlán", "San Jerónimo Co", "San Jerónimo Coatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1091, 20, "Coicoyán de las Flores", "Coicoyán de las", "Coicoyán de las Flores");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1092, 20, "San Jerónimo Silacayoapilla", "San Jerónimo Si", "San Jerónimo Silacayoapilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1093, 20, "San Jerónimo Sosola", "San Jerónimo So", "San Jerónimo Sosola");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1094, 20, "San Jerónimo Taviche", "San Jerónimo Ta", "San Jerónimo Taviche");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1095, 20, "San Jerónimo Tecóatl", "San Jerónimo Te", "San Jerónimo Tecóatl");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1096, 20, "San Jorge Nuchita", "San Jorge Nuchi", "San Jorge Nuchita");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1097, 20, "San José Ayuquila", "San José Ayuqui", "San José Ayuquila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1098, 20, "San José Chiltepec", "San José Chilte", "San José Chiltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1099, 20, "San José del Peñasco", "San José del Pe", "San José del Peñasco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1100, 20, "San José Estancia Grande", "San José Estanc", "San José Estancia Grande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1101, 20, "San José Independencia", "San José Indepe", "San José Independencia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1102, 20, "La Compañía", "La Compañía", "La Compañía");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1103, 20, "San José Lachiguiri", "San José Lachig", "San José Lachiguiri");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1104, 20, "San José Tenango", "San José Tenang", "San José Tenango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1105, 20, "San Juan Achiutla", "San Juan Achiut", "San Juan Achiutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1106, 20, "San Juan Atepec", "San Juan Atepec", "San Juan Atepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1107, 20, "Ánimas Trujano", "Ánimas Trujano", "Ánimas Trujano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1108, 20, "San Juan Bautista Atatlahuca", "San Juan Bautis", "San Juan Bautista Atatlahuca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1109, 20, "San Juan Bautista Coixtlahuaca", "San Juan Bautis", "San Juan Bautista Coixtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1110, 20, "San Juan Bautista Cuicatlán", "San Juan Bautis", "San Juan Bautista Cuicatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1111, 20, "San Juan Bautista Guelache", "San Juan Bautis", "San Juan Bautista Guelache");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1112, 20, "San Juan Bautista Jayacatlán", "San Juan Bautis", "San Juan Bautista Jayacatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1113, 20, "Concepción Buenavista", "Concepción Buen", "Concepción Buenavista");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1114, 20, "San Juan Bautista Lo de Soto", "San Juan Bautis", "San Juan Bautista Lo de Soto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1115, 20, "San Juan Bautista Suchitepec", "San Juan Bautis", "San Juan Bautista Suchitepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1116, 20, "San Juan Bautista Tlacoatzintepec", "San Juan Bautis", "San Juan Bautista Tlacoatzintepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1117, 20, "San Juan Bautista Tlachichilco", "San Juan Bautis", "San Juan Bautista Tlachichilco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1118, 20, "San Juan Bautista Tuxtepec", "San Juan Bautis", "San Juan Bautista Tuxtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1119, 20, "San Juan Cacahuatepec", "San Juan Cacahu", "San Juan Cacahuatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1120, 20, "San Juan Cieneguilla", "San Juan Cieneg", "San Juan Cieneguilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1121, 20, "San Juan Coatzóspam", "San Juan Coatzó", "San Juan Coatzóspam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1122, 20, "San Juan Colorado", "San Juan Colora", "San Juan Colorado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1123, 20, "San Juan Comaltepec", "San Juan Comalt", "San Juan Comaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1124, 20, "Concepción Pápalo", "Concepción Pápa", "Concepción Pápalo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1125, 20, "San Juan Cotzocón", "San Juan Cotzoc", "San Juan Cotzocón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1126, 20, "San Juan Chicomezúchil", "San Juan Chicom", "San Juan Chicomezúchil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1127, 20, "San Juan Chilateca", "San Juan Chilat", "San Juan Chilateca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1128, 20, "San Juan del Estado", "San Juan del Es", "San Juan del Estado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1129, 20, "San Juan del Río", "San Juan del Rí", "San Juan del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1130, 20, "San Juan Diuxi", "San Juan Diuxi", "San Juan Diuxi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1131, 20, "San Juan Evangelista Analco", "San Juan Evange", "San Juan Evangelista Analco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1132, 20, "San Juan Guelavía", "San Juan Guelav", "San Juan Guelavía");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1133, 20, "San Juan Guichicovi", "San Juan Guichi", "San Juan Guichicovi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1134, 20, "San Juan Ihualtepec", "San Juan Ihualt", "San Juan Ihualtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1135, 20, "Acatlán de Pérez Figueroa", "Acatlán de Pére", "Acatlán de Pérez Figueroa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1136, 20, "Constancia del Rosario", "Constancia del ", "Constancia del Rosario");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1137, 20, "San Juan Juquila Mixes", "San Juan Juquil", "San Juan Juquila Mixes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1138, 20, "San Juan Juquila Vijanos", "San Juan Juquil", "San Juan Juquila Vijanos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1139, 20, "San Juan Lachao", "San Juan Lachao", "San Juan Lachao");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1140, 20, "San Juan Lachigalla", "San Juan Lachig", "San Juan Lachigalla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1141, 20, "San Juan Lajarcia", "San Juan Lajarc", "San Juan Lajarcia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1142, 20, "San Juan Lalana", "San Juan Lalana", "San Juan Lalana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1143, 20, "San Juan de los Cués", "San Juan de los", "San Juan de los Cués");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1144, 20, "San Juan Mazatlán", "San Juan Mazatl", "San Juan Mazatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1145, 20, "San Juan Mixtepec -Dto. 08 -", "San Juan Mixtep", "San Juan Mixtepec -Dto. 08 -");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1146, 20, "San Juan Mixtepec -Dto. 26 -", "San Juan Mixtep", "San Juan Mixtepec -Dto. 26 -");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1147, 20, "Cosolapa", "Cosolapa", "Cosolapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1148, 20, "Fraccionamiento", "Fraccionamiento", "Fraccionamiento");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1149, 20, "San Juan Ñumí", "San Juan Ñumí", "San Juan Ñumí");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1150, 20, "San Juan Ozolotepec", "San Juan Ozolot", "San Juan Ozolotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1151, 20, "San Juan Petlapa", "San Juan Petlap", "San Juan Petlapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1152, 20, "San Juan Quiahije", "San Juan Quiahi", "San Juan Quiahije");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1153, 20, "San Juan Quiotepec", "San Juan Quiote", "San Juan Quiotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1154, 20, "San Juan Sayultepec", "San Juan Sayult", "San Juan Sayultepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1155, 20, "San Juan Tabaá", "San Juan Tabaá", "San Juan Tabaá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1156, 20, "San Juan Tamazola", "San Juan Tamazo", "San Juan Tamazola");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1157, 20, "San Juan Teita", "San Juan Teita", "San Juan Teita");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1158, 20, "San Juan Teitipac", "San Juan Teitip", "San Juan Teitipac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1159, 20, "Cosoltepec", "Cosoltepec", "Cosoltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1160, 20, "San Juan Tepeuxila", "San Juan Tepeux", "San Juan Tepeuxila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1161, 20, "San Juan Teposcolula", "San Juan Teposc", "San Juan Teposcolula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1162, 20, "San Juan Yaeé", "San Juan Yaeé", "San Juan Yaeé");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1163, 20, "San Juan Yatzona", "San Juan Yatzon", "San Juan Yatzona");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1164, 20, "San Juan Yucuita", "San Juan Yucuit", "San Juan Yucuita");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1165, 20, "San Lorenzo", "San Lorenzo", "San Lorenzo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1166, 20, "San Lorenzo Albarradas", "San Lorenzo Alb", "San Lorenzo Albarradas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1167, 20, "San Lorenzo Cacaotepec", "San Lorenzo Cac", "San Lorenzo Cacaotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1168, 20, "San Lorenzo Cuaunecuiltitla", "San Lorenzo Cua", "San Lorenzo Cuaunecuiltitla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1169, 20, "San Lorenzo Texmelúcan", "San Lorenzo Tex", "San Lorenzo Texmelúcan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1170, 20, "Cuilápam de Guerrero", "Cuilápam de Gue", "Cuilápam de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1171, 20, "San Lorenzo Victoria", "San Lorenzo Vic", "San Lorenzo Victoria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1172, 20, "San Lucas Camotlán", "San Lucas Camot", "San Lucas Camotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1173, 20, "San Lucas Ojitlán", "San Lucas Ojitl", "San Lucas Ojitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1174, 20, "San Lucas Quiaviní", "San Lucas Quiav", "San Lucas Quiaviní");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1175, 20, "San Lucas Zoquiápam", "San Lucas Zoqui", "San Lucas Zoquiápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1176, 20, "San Luis Amatlán", "San Luis Amatlá", "San Luis Amatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1177, 20, "San Marcial Ozolotepec", "San Marcial Ozo", "San Marcial Ozolotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1178, 20, "San Marcos Arteaga", "San Marcos Arte", "San Marcos Arteaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1179, 20, "San Martín de los Cansecos", "San Martín de l", "San Martín de los Cansecos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1180, 20, "San Martín Huamelúlpam", "San Martín Huam", "San Martín Huamelúlpam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1181, 20, "Cuyamecalco Villa de Zaragoza", "Cuyamecalco Vil", "Cuyamecalco Villa de Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1182, 20, "San Martín Itunyoso", "San Martín Itun", "San Martín Itunyoso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1183, 20, "San Martín Lachilá", "San Martín Lach", "San Martín Lachilá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1184, 20, "San Martín Peras", "San Martín Pera", "San Martín Peras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1185, 20, "San Martín Tilcajete", "San Martín Tilc", "San Martín Tilcajete");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1186, 20, "San Martín Toxpalan", "San Martín Toxp", "San Martín Toxpalan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1187, 20, "San Martín Zacatepec", "San Martín Zaca", "San Martín Zacatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1188, 20, "San Mateo Cajonos", "San Mateo Cajon", "San Mateo Cajonos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1189, 20, "Capulálpam de Méndez", "Capulálpam de M", "Capulálpam de Méndez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1190, 20, "San Mateo del Mar", "San Mateo del M", "San Mateo del Mar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1191, 20, "San Mateo Yoloxochitlán", "San Mateo Yolox", "San Mateo Yoloxochitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1192, 20, "Chahuites", "Chahuites", "Chahuites");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1193, 20, "San Mateo Etlatongo", "San Mateo Etlat", "San Mateo Etlatongo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1194, 20, "San Mateo Nejápam", "San Mateo Nejáp", "San Mateo Nejápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1195, 20, "San Mateo Peñasco", "San Mateo Peñas", "San Mateo Peñasco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1196, 20, "San Mateo Piñas", "San Mateo Piñas", "San Mateo Piñas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1197, 20, "San Mateo Río Hondo", "San Mateo Río H", "San Mateo Río Hondo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1198, 20, "San Mateo Sindihui", "San Mateo Sindi", "San Mateo Sindihui");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1199, 20, "San Mateo Tlapiltepec", "San Mateo Tlapi", "San Mateo Tlapiltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1200, 20, "San Melchor Betaza", "San Melchor Bet", "San Melchor Betaza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1201, 20, "San Miguel Achiutla", "San Miguel Achi", "San Miguel Achiutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1202, 20, "San Miguel Ahuehuetitlán", "San Miguel Ahue", "San Miguel Ahuehuetitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1203, 20, "Chalcatongo de Hidalgo", "Chalcatongo de ", "Chalcatongo de Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1204, 20, "San Miguel Aloápam", "San Miguel Aloá", "San Miguel Aloápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1205, 20, "San Miguel Amatitlán", "San Miguel Amat", "San Miguel Amatitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1206, 20, "San Miguel Amatlán", "San Miguel Amat", "San Miguel Amatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1207, 20, "San Miguel Coatlán", "San Miguel Coat", "San Miguel Coatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1208, 20, "San Miguel Chicahua", "San Miguel Chic", "San Miguel Chicahua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1209, 20, "San Miguel Chimalapa", "San Miguel Chim", "San Miguel Chimalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1210, 20, "San Miguel del Puerto", "San Miguel del ", "San Miguel del Puerto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1211, 20, "San Miguel del Río", "San Miguel del ", "San Miguel del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1212, 20, "San Miguel Ejutla", "San Miguel Ejut", "San Miguel Ejutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1213, 20, "San Miguel el Grande", "San Miguel el G", "San Miguel el Grande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1214, 20, "Chiquihuitlán de Benito Juárez", "Chiquihuitlán d", "Chiquihuitlán de Benito Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1215, 20, "San Miguel Huautla", "San Miguel Huau", "San Miguel Huautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1216, 20, "San Miguel Mixtepec", "San Miguel Mixt", "San Miguel Mixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1217, 20, "San Miguel Panixtlahuaca", "San Miguel Pani", "San Miguel Panixtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1218, 20, "San Miguel Peras", "San Miguel Pera", "San Miguel Peras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1219, 20, "San Miguel Piedras", "San Miguel Pied", "San Miguel Piedras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1220, 20, "San Miguel Quetzaltepec", "San Miguel Quet", "San Miguel Quetzaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1221, 20, "San Miguel Santa Flor", "San Miguel Sant", "San Miguel Santa Flor");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1222, 20, "Villa Sola de Vega", "Villa Sola de V", "Villa Sola de Vega");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1223, 20, "San Miguel Soyaltepec", "San Miguel Soya", "San Miguel Soyaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1224, 20, "San Miguel Suchixtepec", "San Miguel Such", "San Miguel Suchixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1225, 20, "Heroica Ciudad de Ejutla de Crespo", "Heroica Ciudad ", "Heroica Ciudad de Ejutla de Crespo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1226, 20, "Pueblo", "Pueblo", "Pueblo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1227, 20, "Villa Talea de Castro", "Villa Talea de ", "Villa Talea de Castro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1228, 20, "San Miguel Tecomatlán", "San Miguel Teco", "San Miguel Tecomatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1229, 20, "San Miguel Tenango", "San Miguel Tena", "San Miguel Tenango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1230, 20, "San Miguel Tequixtepec", "San Miguel Tequ", "San Miguel Tequixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1231, 20, "San Miguel Tilquiápam", "San Miguel Tilq", "San Miguel Tilquiápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1232, 20, "San Miguel Tlacamama", "San Miguel Tlac", "San Miguel Tlacamama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1233, 20, "San Miguel Tlacotepec", "San Miguel Tlac", "San Miguel Tlacotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1234, 20, "San Miguel Tulancingo", "San Miguel Tula", "San Miguel Tulancingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1235, 20, "San Miguel Yotao", "San Miguel Yota", "San Miguel Yotao");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1236, 20, "San Nicolás", "San Nicolás", "San Nicolás");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1237, 20, "Eloxochitlán de Flores Magón", "Eloxochitlán de", "Eloxochitlán de Flores Magón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1238, 20, "San Nicolás Hidalgo", "San Nicolás Hid", "San Nicolás Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1239, 20, "San Pablo Coatlán", "San Pablo Coatl", "San Pablo Coatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1240, 20, "San Pablo Cuatro Venados", "San Pablo Cuatr", "San Pablo Cuatro Venados");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1241, 20, "San Pablo Etla", "San Pablo Etla", "San Pablo Etla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1242, 20, "San Pablo Huitzo", "San Pablo Huitz", "San Pablo Huitzo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1243, 20, "San Pablo Huixtepec", "San Pablo Huixt", "San Pablo Huixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1244, 20, "San Pablo Macuiltianguis", "San Pablo Macui", "San Pablo Macuiltianguis");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1245, 20, "San Pablo Tijaltepec", "San Pablo Tijal", "San Pablo Tijaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1246, 20, "San Pablo Villa de Mitla", "San Pablo Villa", "San Pablo Villa de Mitla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1247, 20, "San Pablo Yaganiza", "San Pablo Yagan", "San Pablo Yaganiza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1248, 20, "Asunción Cacalotepec", "Asunción Cacalo", "Asunción Cacalotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1249, 20, "El Espinal", "El Espinal", "El Espinal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1250, 20, "San Pedro Amuzgos", "San Pedro Amuzg", "San Pedro Amuzgos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1251, 20, "San Pedro Apóstol", "San Pedro Apóst", "San Pedro Apóstol");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1252, 20, "San Pedro Atoyac", "San Pedro Atoya", "San Pedro Atoyac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1253, 20, "San Pedro Cajonos", "San Pedro Cajon", "San Pedro Cajonos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1254, 20, "San Pedro Coxcaltepec Cántaros", "San Pedro Coxca", "San Pedro Coxcaltepec Cántaros");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1255, 20, "San Pedro Comitancillo", "San Pedro Comit", "San Pedro Comitancillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1256, 20, "San Pedro el Alto", "San Pedro el Al", "San Pedro el Alto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1257, 20, "San Pedro Huamelula", "San Pedro Huame", "San Pedro Huamelula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1258, 20, "San Pedro Huilotepec", "San Pedro Huilo", "San Pedro Huilotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1259, 20, "San Pedro Ixcatlán", "San Pedro Ixcat", "San Pedro Ixcatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1260, 20, "Tamazulápam del Espíritu Santo", "Tamazulápam del", "Tamazulápam del Espíritu Santo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1261, 20, "San Pedro Ixtlahuaca", "San Pedro Ixtla", "San Pedro Ixtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1262, 20, "San Pedro Jaltepetongo", "San Pedro Jalte", "San Pedro Jaltepetongo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1263, 20, "San Pedro Jicayán", "San Pedro Jicay", "San Pedro Jicayán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1264, 20, "San Pedro Jocotipac", "San Pedro Jocot", "San Pedro Jocotipac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1265, 20, "San Pedro Juchatengo", "San Pedro Jucha", "San Pedro Juchatengo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1266, 20, "San Pedro Mártir", "San Pedro Márti", "San Pedro Mártir");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1267, 20, "San Pedro Mártir Quiechapa", "San Pedro Márti", "San Pedro Mártir Quiechapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1268, 20, "San Pedro Mártir Yucuxaco", "San Pedro Márti", "San Pedro Mártir Yucuxaco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1269, 20, "San Pedro Mixtepec -Dto. 22 -", "San Pedro Mixte", "San Pedro Mixtepec -Dto. 22 -");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1270, 20, "San Pedro Mixtepec -Dto. 26 -", "San Pedro Mixte", "San Pedro Mixtepec -Dto. 26 -");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1271, 20, "Fresnillo de Trujano", "Fresnillo de Tr", "Fresnillo de Trujano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1272, 20, "San Pedro Molinos", "San Pedro Molin", "San Pedro Molinos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1273, 20, "San Pedro Nopala", "San Pedro Nopal", "San Pedro Nopala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1274, 20, "San Pedro Ocopetatillo", "San Pedro Ocope", "San Pedro Ocopetatillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1275, 20, "San Pedro Ocotepec", "San Pedro Ocote", "San Pedro Ocotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1276, 20, "San Pedro Pochutla", "San Pedro Pochu", "San Pedro Pochutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1277, 20, "San Pedro Quiatoni", "San Pedro Quiat", "San Pedro Quiatoni");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1278, 20, "San Pedro Sochiápam", "San Pedro Sochi", "San Pedro Sochiápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1279, 20, "San Pedro Tapanatepec", "San Pedro Tapan", "San Pedro Tapanatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1280, 20, "San Pedro Taviche", "San Pedro Tavic", "San Pedro Taviche");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1281, 20, "San Pedro Teozacoalco", "San Pedro Teoza", "San Pedro Teozacoalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1282, 20, "Guadalupe Etla", "Guadalupe Etla", "Guadalupe Etla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1283, 20, "San Pedro Teutila", "San Pedro Teuti", "San Pedro Teutila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1284, 20, "San Pedro Tidaá", "San Pedro Tidaá", "San Pedro Tidaá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1285, 20, "San Pedro Topiltepec", "San Pedro Topil", "San Pedro Topiltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1286, 20, "San Pedro Totolápam", "San Pedro Totol", "San Pedro Totolápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1287, 20, "Villa de Tututepec de Melchor Ocampo", "Villa de Tutute", "Villa de Tututepec de Melchor Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1288, 20, "San Pedro Yaneri", "San Pedro Yaner", "San Pedro Yaneri");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1289, 20, "San Pedro Yólox", "San Pedro Yólox", "San Pedro Yólox");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1290, 20, "San Pedro y San Pablo Ayutla", "San Pedro y San", "San Pedro y San Pablo Ayutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1291, 20, "Villa de Etla", "Villa de Etla", "Villa de Etla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1292, 20, "San Pedro y San Pablo Teposcolula", "San Pedro y San", "San Pedro y San Pablo Teposcolula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1293, 20, "Guadalupe de Ramírez", "Guadalupe de Ra", "Guadalupe de Ramírez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1294, 20, "San Pedro y San Pablo Tequixtepec", "San Pedro y San", "San Pedro y San Pablo Tequixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1295, 20, "San Pedro Yucunama", "San Pedro Yucun", "San Pedro Yucunama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1296, 20, "San Raymundo Jalpan", "San Raymundo Ja", "San Raymundo Jalpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1297, 20, "San Sebastián Abasolo", "San Sebastián A", "San Sebastián Abasolo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1298, 20, "San Sebastián Coatlán", "San Sebastián C", "San Sebastián Coatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1299, 20, "San Sebastián Ixcapa", "San Sebastián I", "San Sebastián Ixcapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1300, 20, "San Sebastián Nicananduta", "San Sebastián ", "San Sebastián Nicananduta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1301, 20, "San Sebastián Río Hondo", "San Sebastián R", "San Sebastián Río Hondo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1302, 20, "San Sebastián Tecomaxtlahuaca", "San Sebastián T", "San Sebastián Tecomaxtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1303, 20, "San Sebastián Teitipac", "San Sebastián T", "San Sebastián Teitipac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1304, 20, "Guelatao de Juárez", "Guelatao de Juá", "Guelatao de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1305, 20, "San Sebastián Tutla", "San Sebastián T", "San Sebastián Tutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1306, 20, "San Simón Almolongas", "San Simón Almol", "San Simón Almolongas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1307, 20, "San Simón Zahuatlán", "San Simón Zahua", "San Simón Zahuatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1308, 20, "Santa Ana", "Santa Ana", "Santa Ana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1309, 20, "Santa Ana Ateixtlahuaca", "Santa Ana Ateix", "Santa Ana Ateixtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1310, 20, "Santa Ana Cuauhtémoc", "Santa Ana Cuauh", "Santa Ana Cuauhtémoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1311, 20, "Santa Ana del Valle", "Santa Ana del V", "Santa Ana del Valle");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1312, 20, "Santa Ana Tavela", "Santa Ana Tavel", "Santa Ana Tavela");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1313, 20, "Santa Ana Tlapacoyan", "Santa Ana Tlapa", "Santa Ana Tlapacoyan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1314, 20, "Santa Ana Yareni", "Santa Ana Yaren", "Santa Ana Yareni");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1315, 20, "Guevea de Humboldt", "Guevea de Humbo", "Guevea de Humboldt");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1316, 20, "Santa Ana Zegache", "Santa Ana Zegac", "Santa Ana Zegache");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1317, 20, "Santa Catalina Quierí", "Santa Catalina ", "Santa Catalina Quierí");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1318, 20, "Santa Catarina Cuixtla", "Santa Catarina ", "Santa Catarina Cuixtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1319, 20, "Santa Catarina Ixtepeji", "Santa Catarina ", "Santa Catarina Ixtepeji");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1320, 20, "Santa Catarina Juquila", "Santa Catarina ", "Santa Catarina Juquila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1321, 20, "Santa Catarina Lachatao", "Santa Catarina ", "Santa Catarina Lachatao");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1322, 20, "Santa Catarina Loxicha", "Santa Catarina ", "Santa Catarina Loxicha");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1323, 20, "Santa Catarina Mechoacán", "Santa Catarina ", "Santa Catarina Mechoacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1324, 20, "Santa Catarina Minas", "Santa Catarina ", "Santa Catarina Minas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1325, 20, "Santa Catarina Quiané", "Santa Catarina ", "Santa Catarina Quiané");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1326, 20, "Mesones Hidalgo", "Mesones Hidalgo", "Mesones Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1327, 20, "Santa Catarina Tayata", "Santa Catarina ", "Santa Catarina Tayata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1328, 20, "Santa Catarina Ticuá", "Santa Catarina ", "Santa Catarina Ticuá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1329, 20, "Santa Catarina Yosonotú", "Santa Catarina ", "Santa Catarina Yosonotú");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1330, 20, "Santa Catarina Zapoquila", "Santa Catarina ", "Santa Catarina Zapoquila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1331, 20, "Santa Cruz Acatepec", "Santa Cruz Acat", "Santa Cruz Acatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1332, 20, "Santa Cruz Amilpas", "Santa Cruz Amil", "Santa Cruz Amilpas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1333, 20, "Santa Cruz de Bravo", "Santa Cruz de B", "Santa Cruz de Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1334, 20, "Santa Cruz Itundujia", "Santa Cruz Itun", "Santa Cruz Itundujia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1335, 20, "Santa Cruz Mixtepec", "Santa Cruz Mixt", "Santa Cruz Mixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1336, 20, "Santa Cruz Nundaco", "Santa Cruz Nund", "Santa Cruz Nundaco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1337, 20, "Villa Hidalgo", "Villa Hidalgo", "Villa Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1338, 20, "Santa Cruz Papalutla", "Santa Cruz Papa", "Santa Cruz Papalutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1339, 20, "Santa Cruz Tacache de Mina", "Santa Cruz Taca", "Santa Cruz Tacache de Mina");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1340, 20, "Santa Cruz Tacahua", "Santa Cruz Taca", "Santa Cruz Tacahua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1341, 20, "Santa Cruz Tayata", "Santa Cruz Taya", "Santa Cruz Tayata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1342, 20, "Santa Cruz Xitla", "Santa Cruz Xitl", "Santa Cruz Xitla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1343, 20, "Santa Cruz Xoxocotlán", "Santa Cruz Xoxo", "Santa Cruz Xoxocotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1344, 20, "Santa Cruz Zenzontepec", "Santa Cruz Zenz", "Santa Cruz Zenzontepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1345, 20, "Santa Gertrudis", "Santa Gertrudis", "Santa Gertrudis");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1346, 20, "Santa Inés del Monte", "Santa Inés del ", "Santa Inés del Monte");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1347, 20, "Santa Inés Yatzeche", "Santa Inés Yatz", "Santa Inés Yatzeche");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1348, 20, "Heroica Ciudad de Huajuapan de León", "Heroica Ciudad ", "Heroica Ciudad de Huajuapan de León");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1349, 20, "Santa Lucía del Camino", "Santa Lucía del", "Santa Lucía del Camino");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1350, 20, "Santa Lucía Miahuatlán", "Santa Lucía Mia", "Santa Lucía Miahuatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1351, 20, "Santa Lucía Monteverde", "Santa Lucía Mon", "Santa Lucía Monteverde");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1352, 20, "Santa Lucía Ocotlán", "Santa Lucía Oco", "Santa Lucía Ocotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1353, 20, "Santa María Alotepec", "Santa María Alo", "Santa María Alotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1354, 20, "Santa María Apazco", "Santa María Apa", "Santa María Apazco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1355, 20, "Santa María la Asunción", "Santa María la ", "Santa María la Asunción");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1356, 20, "Heroica Ciudad de Tlaxiaco", "Heroica Ciudad ", "Heroica Ciudad de Tlaxiaco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1357, 20, "Ayoquezco de Aldama", "Ayoquezco de Al", "Ayoquezco de Aldama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1358, 20, "Santa María Atzompa", "Santa María Atz", "Santa María Atzompa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1359, 20, "Asunción Cuyotepeji", "Asunción Cuyote", "Asunción Cuyotepeji");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1360, 20, "Huautepec", "Huautepec", "Huautepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1361, 20, "Santa María Camotlán", "Santa María Cam", "Santa María Camotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1362, 20, "Santa María Colotepec", "Santa María Col", "Santa María Colotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1363, 20, "Santa María Cortijo", "Santa María Cor", "Santa María Cortijo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1364, 20, "Santa María Coyotepec", "Santa María Coy", "Santa María Coyotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1365, 20, "Santa María Chachoápam", "Santa María Cha", "Santa María Chachoápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1366, 20, "Villa de Chilapa de Díaz", "Villa de Chilap", "Villa de Chilapa de Díaz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1367, 20, "Santa María Chilchotla", "Santa María Chi", "Santa María Chilchotla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1368, 20, "Santa María Chimalapa", "Santa María Chi", "Santa María Chimalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1369, 20, "Santa María del Rosario", "Santa María del", "Santa María del Rosario");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1370, 20, "Santa María del Tule", "Santa María del", "Santa María del Tule");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1371, 20, "Huautla de Jiménez", "Huautla de Jimé", "Huautla de Jiménez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1372, 20, "Santa María Ecatepec", "Santa María Eca", "Santa María Ecatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1373, 20, "Santa María Guelacé", "Santa María Gue", "Santa María Guelacé");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1374, 20, "Santa María Guienagati", "Santa María Gui", "Santa María Guienagati");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1375, 20, "Santa María Huatulco", "Santa María Hua", "Santa María Huatulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1376, 20, "Santa María Huazolotitlán", "Santa María Hua", "Santa María Huazolotitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1377, 20, "Santa María Ipalapa", "Santa María Ipa", "Santa María Ipalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1378, 20, "Santa María Ixcatlán", "Santa María Ixc", "Santa María Ixcatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1379, 20, "Santa María Jacatepec", "Santa María Jac", "Santa María Jacatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1380, 20, "Santa María Jalapa del Marqués", "Santa María Jal", "Santa María Jalapa del Marqués");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1381, 20, "Santa María Jaltianguis", "Santa María Jal", "Santa María Jaltianguis");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1382, 20, "Ixtlán de Juárez", "Ixtlán de Juáre", "Ixtlán de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1383, 20, "Santa María Lachixío", "Santa María Lac", "Santa María Lachixío");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1384, 20, "Santa María Mixtequilla", "Santa María Mix", "Santa María Mixtequilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1385, 20, "Santa María Nativitas", "Santa María Nat", "Santa María Nativitas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1386, 20, "Santa María Nduayaco", "Santa María Ndu", "Santa María Nduayaco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1387, 20, "Santa María Ozolotepec", "Santa María Ozo", "Santa María Ozolotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1388, 20, "Santa María Pápalo", "Santa María Páp", "Santa María Pápalo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1389, 20, "Santa María Peñoles", "Santa María Peñ", "Santa María Peñoles");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1390, 20, "Santa María Petapa", "Santa María Pet", "Santa María Petapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1391, 20, "Santa María Quiegolani", "Santa María Qui", "Santa María Quiegolani");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1392, 20, "Santa María Sola", "Santa María Sol", "Santa María Sola");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1393, 20, "Heroica Ciudad de Juchitán de Zaragoza", "Heroica Ciudad ", "Heroica Ciudad de Juchitán de Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1394, 20, "Santa María Tataltepec", "Santa María Tat", "Santa María Tataltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1395, 20, "Santa María Tecomavaca", "Santa María Tec", "Santa María Tecomavaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1396, 20, "Santa María Temaxcalapa", "Santa María Tem", "Santa María Temaxcalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1397, 20, "Santa María Temaxcaltepec", "Santa María Tem", "Santa María Temaxcaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1398, 20, "Santa María Teopoxco", "Santa María Teo", "Santa María Teopoxco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1399, 20, "Santa María Tepantlali", "Santa María Tep", "Santa María Tepantlali");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1400, 20, "Santa María Texcatitlán", "Santa María Tex", "Santa María Texcatitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1401, 20, "Santa María Tlahuitoltepec", "Santa María Tla", "Santa María Tlahuitoltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1402, 20, "Santa María Tlalixtac", "Santa María Tla", "Santa María Tlalixtac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1403, 20, "Santa María Tonameca", "Santa María Ton", "Santa María Tonameca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1404, 20, "Loma Bonita", "Loma Bonita", "Loma Bonita");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1405, 20, "Santa María Totolapilla", "Santa María Tot", "Santa María Totolapilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1406, 20, "Santa María Xadani", "Santa María Xad", "Santa María Xadani");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1407, 20, "Santa María Yalina", "Santa María Yal", "Santa María Yalina");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1408, 20, "Santa María Yavesía", "Santa María Yav", "Santa María Yavesía");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1409, 20, "Santa María Yolotepec", "Santa María Yol", "Santa María Yolotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1410, 20, "Santa María Yosoyúa", "Santa María Yos", "Santa María Yosoyúa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1411, 20, "Santa María Yucuhiti", "Santa María Yuc", "Santa María Yucuhiti");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1412, 20, "Santa María Zacatepec", "Santa María Zac", "Santa María Zacatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1413, 20, "Santa María Zaniza", "Santa María Zan", "Santa María Zaniza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1414, 20, "Santa María Zoquitlán", "Santa María Zoq", "Santa María Zoquitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1415, 20, "Magdalena Apasco", "Magdalena Apasc", "Magdalena Apasco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1416, 20, "Santiago Amoltepec", "Santiago Amolte", "Santiago Amoltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1417, 20, "Santiago Apoala", "Santiago Apoala", "Santiago Apoala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1418, 20, "Santiago Apóstol", "Santiago Apósto", "Santiago Apóstol");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1419, 20, "Santiago Astata", "Santiago Astata", "Santiago Astata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1420, 20, "Santiago Atitlán", "Santiago Atitlá", "Santiago Atitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1421, 20, "Santiago Ayuquililla", "Santiago Ayuqui", "Santiago Ayuquililla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1422, 20, "Santiago Cacaloxtepec", "Santiago Cacalo", "Santiago Cacaloxtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1423, 20, "Santiago Camotlán", "Santiago Camotl", "Santiago Camotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1424, 20, "Santiago Comaltepec", "Santiago Comalt", "Santiago Comaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1425, 20, "Santiago Chazumba", "Santiago Chazum", "Santiago Chazumba");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1426, 20, "Magdalena Jaltepec", "Magdalena Jalte", "Magdalena Jaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1427, 20, "Santiago Choápam", "Santiago Choápa", "Santiago Choápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1428, 20, "Santiago del Río", "Santiago del Rí", "Santiago del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1429, 20, "Santiago Huajolotitlán", "Santiago Huajol", "Santiago Huajolotitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1430, 20, "Santiago Huauclilla", "Santiago Huaucl", "Santiago Huauclilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1431, 20, "Santiago Ihuitlán Plumas", "Santiago Ihuitl", "Santiago Ihuitlán Plumas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1432, 20, "Santiago Ixcuintepec", "Santiago Ixcuin", "Santiago Ixcuintepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1433, 20, "Santiago Ixtayutla", "Santiago Ixtayu", "Santiago Ixtayutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1434, 20, "Santiago Jamiltepec", "Santiago Jamilt", "Santiago Jamiltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1435, 20, "Santiago Jocotepec", "Santiago Jocote", "Santiago Jocotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1436, 20, "Santiago Juxtlahuaca", "Santiago Juxtla", "Santiago Juxtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1437, 20, "Santa Magdalena Jicotlán", "Santa Magdalena", "Santa Magdalena Jicotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1438, 20, "Santiago Lachiguiri", "Santiago Lachig", "Santiago Lachiguiri");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1439, 20, "Santiago Lalopa", "Santiago Lalopa", "Santiago Lalopa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1440, 20, "Santiago Laollaga", "Santiago Laolla", "Santiago Laollaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1441, 20, "Santiago Laxopa", "Santiago Laxopa", "Santiago Laxopa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1442, 20, "Santiago Llano Grande", "Santiago Llano ", "Santiago Llano Grande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1443, 20, "Santiago Matatlán", "Santiago Matatl", "Santiago Matatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1444, 20, "Santiago Miltepec", "Santiago Miltep", "Santiago Miltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1445, 20, "Santiago Minas", "Santiago Minas", "Santiago Minas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1446, 20, "Santiago Nacaltepec", "Santiago Nacalt", "Santiago Nacaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1447, 20, "Santiago Nejapilla", "Santiago Nejapi", "Santiago Nejapilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1448, 20, "Magdalena Mixtepec", "Magdalena Mixte", "Magdalena Mixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1449, 20, "Santiago Nundiche", "Santiago Nundic", "Santiago Nundiche");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1450, 20, "Santiago Nuyoó", "Santiago Nuyoó", "Santiago Nuyoó");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1451, 20, "Santiago Pinotepa Nacional", "Santiago Pinote", "Santiago Pinotepa Nacional");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1452, 20, "Santiago Suchilquitongo", "Santiago Suchil", "Santiago Suchilquitongo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1453, 20, "Santiago Tamazola", "Santiago Tamazo", "Santiago Tamazola");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1454, 20, "Santiago Tapextla", "Santiago Tapext", "Santiago Tapextla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1455, 20, "Villa Tejúpam de la Unión", "Villa Tejúpam d", "Villa Tejúpam de la Unión");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1456, 20, "Santiago Tenango", "Santiago Tenang", "Santiago Tenango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1457, 20, "Santiago Tepetlapa", "Santiago Tepetl", "Santiago Tepetlapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1458, 20, "Santiago Tetepec", "Santiago Tetepe", "Santiago Tetepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1459, 20, "Magdalena Ocotlán", "Magdalena Ocotl", "Magdalena Ocotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1460, 20, "Santiago Texcalcingo", "Santiago Texcal", "Santiago Texcalcingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1461, 20, "Santiago Textitlán", "Santiago Textit", "Santiago Textitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1462, 20, "Santiago Tilantongo", "Santiago Tilant", "Santiago Tilantongo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1463, 20, "Santiago Tillo", "Santiago Tillo", "Santiago Tillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1464, 20, "Santiago Tlazoyaltepec", "Santiago Tlazoy", "Santiago Tlazoyaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1465, 20, "Santiago Xanica", "Santiago Xanica", "Santiago Xanica");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1466, 20, "Santiago Xiacuí", "Santiago Xiacuí", "Santiago Xiacuí");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1467, 20, "Santiago Yaitepec", "Santiago Yaitep", "Santiago Yaitepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1468, 20, "Santiago Yaveo", "Santiago Yaveo", "Santiago Yaveo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1469, 20, "Santiago Yolomécatl", "Santiago Yolomé", "Santiago Yolomécatl");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1470, 20, "Asunción Ixtaltepec", "Asunción Ixtalt", "Asunción Ixtaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1471, 20, "Magdalena Peñasco", "Magdalena Peñas", "Magdalena Peñasco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1472, 20, "Santiago Yosondúa", "Santiago Yosond", "Santiago Yosondúa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1473, 20, "Santiago Yucuyachi", "Santiago Yucuya", "Santiago Yucuyachi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1474, 20, "Santiago Zacatepec", "Santiago Zacate", "Santiago Zacatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1475, 20, "Santiago Zoochila", "Santiago Zoochi", "Santiago Zoochila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1476, 20, "Nuevo Zoquiápam", "Nuevo Zoquiápam", "Nuevo Zoquiápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1477, 20, "Santo Domingo Ingenio", "Santo Domingo I", "Santo Domingo Ingenio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1478, 20, "Santo Domingo Albarradas", "Santo Domingo A", "Santo Domingo Albarradas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1479, 20, "Santo Domingo Armenta", "Santo Domingo A", "Santo Domingo Armenta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1480, 20, "Santo Domingo Chihuitán", "Santo Domingo C", "Santo Domingo Chihuitán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1481, 20, "Santo Domingo de Morelos", "Santo Domingo d", "Santo Domingo de Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1482, 20, "Magdalena Teitipac", "Magdalena Teiti", "Magdalena Teitipac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1483, 20, "Santo Domingo Ixcatlán", "Santo Domingo I", "Santo Domingo Ixcatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1484, 20, "Santo Domingo Nuxaá", "Santo Domingo ", "Santo Domingo Nuxaá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1485, 20, "Santo Domingo Ozolotepec", "Santo Domingo O", "Santo Domingo Ozolotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1486, 20, "Santo Domingo Petapa", "Santo Domingo P", "Santo Domingo Petapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1487, 20, "Santo Domingo Roayaga", "Santo Domingo R", "Santo Domingo Roayaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1488, 20, "Santo Domingo Tehuantepec", "Santo Domingo T", "Santo Domingo Tehuantepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1489, 20, "Santo Domingo Teojomulco", "Santo Domingo T", "Santo Domingo Teojomulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1490, 20, "Santo Domingo Tepuxtepec", "Santo Domingo T", "Santo Domingo Tepuxtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1491, 20, "Santo Domingo Tlatayápam", "Santo Domingo T", "Santo Domingo Tlatayápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1492, 20, "Santo Domingo Tomaltepec", "Santo Domingo T", "Santo Domingo Tomaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1493, 20, "Magdalena Tequisistlán", "Magdalena Tequi", "Magdalena Tequisistlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1494, 20, "Santo Domingo Tonalá", "Santo Domingo T", "Santo Domingo Tonalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1495, 20, "Santo Domingo Tonaltepec", "Santo Domingo T", "Santo Domingo Tonaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1496, 20, "Santo Domingo Xagacía", "Santo Domingo X", "Santo Domingo Xagacía");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1497, 20, "Santo Domingo Yanhuitlán", "Santo Domingo Y", "Santo Domingo Yanhuitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1498, 20, "Santo Domingo Yodohino", "Santo Domingo Y", "Santo Domingo Yodohino");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1499, 20, "Santo Domingo Zanatepec", "Santo Domingo Z", "Santo Domingo Zanatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1500, 20, "Santos Reyes Nopala", "Santos Reyes No", "Santos Reyes Nopala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1501, 20, "Santos Reyes Pápalo", "Santos Reyes Pá", "Santos Reyes Pápalo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1502, 20, "Santos Reyes Tepejillo", "Santos Reyes Te", "Santos Reyes Tepejillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1503, 20, "Santos Reyes Yucuná", "Santos Reyes Yu", "Santos Reyes Yucuná");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1504, 20, "Magdalena Tlacotepec", "Magdalena Tlaco", "Magdalena Tlacotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1505, 20, "Santo Tomás Jalieza", "Santo Tomás Jal", "Santo Tomás Jalieza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1506, 20, "Santo Tomás Mazaltepec", "Santo Tomás Maz", "Santo Tomás Mazaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1507, 20, "Santo Tomás Ocotepec", "Santo Tomás Oco", "Santo Tomás Ocotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1508, 20, "Santo Tomás Tamazulapan", "Santo Tomás Tam", "Santo Tomás Tamazulapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1509, 20, "San Vicente Coatlán", "San Vicente Coa", "San Vicente Coatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1510, 20, "San Vicente Lachixío", "San Vicente Lac", "San Vicente Lachixío");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1511, 20, "San Vicente Nuñú", "San Vicente Nuñ", "San Vicente Nuñú");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1512, 20, "Silacayoápam", "Silacayoápam", "Silacayoápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1513, 20, "Sitio de Xitlapehua", "Sitio de Xitlap", "Sitio de Xitlapehua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1514, 20, "Soledad Etla", "Soledad Etla", "Soledad Etla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1515, 20, "Magdalena Zahuatlán", "Magdalena Zahua", "Magdalena Zahuatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1516, 20, "Villa de Tamazulápam del Progreso", "Villa de Tamazu", "Villa de Tamazulápam del Progreso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1517, 20, "Tanetze de Zaragoza", "Tanetze de Zara", "Tanetze de Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1518, 20, "Taniche", "Taniche", "Taniche");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1519, 20, "Tataltepec de Valdés", "Tataltepec de V", "Tataltepec de Valdés");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1520, 20, "Teococuilco de Marcos Pérez", "Teococuilco de ", "Teococuilco de Marcos Pérez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1521, 20, "Teotitlán de Flores Magón", "Teotitlán de Fl", "Teotitlán de Flores Magón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1522, 20, "Teotitlán del Valle", "Teotitlán del V", "Teotitlán del Valle");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1523, 20, "Teotongo", "Teotongo", "Teotongo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1524, 20, "Tepelmeme Villa de Morelos", "Tepelmeme Villa", "Tepelmeme Villa de Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1525, 20, "Tezoatlán de Segura y Luna", "Tezoatlán de Se", "Tezoatlán de Segura y Luna");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1526, 20, "Mariscala de Juárez", "Mariscala de Ju", "Mariscala de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1527, 20, "San Jerónimo Tlacochahuaya", "San Jerónimo Tl", "San Jerónimo Tlacochahuaya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1528, 20, "Tlacolula de Matamoros", "Tlacolula de Ma", "Tlacolula de Matamoros");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1529, 20, "Tlacotepec Plumas", "Tlacotepec Plum", "Tlacotepec Plumas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1530, 20, "Tlalixtac de Cabrera", "Tlalixtac de Ca", "Tlalixtac de Cabrera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1531, 20, "Totontepec Villa de Morelos", "Totontepec Vill", "Totontepec Villa de Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1532, 20, "Trinidad Zaachila", "Trinidad Zaachi", "Trinidad Zaachila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1533, 20, "La Trinidad Vista Hermosa", "La Trinidad Vis", "La Trinidad Vista Hermosa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1534, 20, "Unión Hidalgo", "Unión Hidalgo", "Unión Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1535, 20, "Valerio Trujano", "Valerio Trujano", "Valerio Trujano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1536, 20, "San Juan Bautista Valle Nacional", "San Juan Bautis", "San Juan Bautista Valle Nacional");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1537, 20, "Mártires de Tacubaya", "Mártires de Tac", "Mártires de Tacubaya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1538, 20, "Villa Díaz Ordaz", "Villa Díaz Orda", "Villa Díaz Ordaz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1539, 20, "Yaxe", "Yaxe", "Yaxe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1540, 20, "Magdalena Yodocono de Porfirio Díaz", "Magdalena Yodoc", "Magdalena Yodocono de Porfirio Díaz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1541, 20, "Yogana", "Yogana", "Yogana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1542, 20, "Yutanduchi de Guerrero", "Yutanduchi de G", "Yutanduchi de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1543, 20, "Villa de Zaachila", "Villa de Zaachi", "Villa de Zaachila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1544, 20, "San Mateo Yucutindoo", "San Mateo Yucut", "San Mateo Yucutindoo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1545, 20, "Zapotitlán Lagunas", "Zapotitlán Lagu", "Zapotitlán Lagunas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1546, 20, "Zapotitlán Palmas", "Zapotitlán Palm", "Zapotitlán Palmas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1547, 20, "Santa Inés de Zaragoza", "Santa Inés de Z", "Santa Inés de Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1548, 20, "Matías Romero Avendaño", "Matías Romero A", "Matías Romero Avendaño");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1549, 20, "Zimatlán de Álvarez", "Zimatlán de Álv", "Zimatlán de Álvarez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1550, 20, "Mazatlán Villa de Flores", "Mazatlán Villa ", "Mazatlán Villa de Flores");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1551, 20, "Miahuatlán de Porfirio Díaz", "Miahuatlán de P", "Miahuatlán de Porfirio Díaz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1552, 20, "Asunción Nochixtlán", "Asunción Nochix", "Asunción Nochixtlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1553, 20, "Mixistlán de la Reforma", "Mixistlán de la", "Mixistlán de la Reforma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1554, 20, "Monjas", "Monjas", "Monjas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1555, 20, "Natividad", "Natividad", "Natividad");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1556, 20, "Nazareno Etla", "Nazareno Etla", "Nazareno Etla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1557, 20, "Nejapa de Madero", "Nejapa de Mader", "Nejapa de Madero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1558, 20, "Ixpantepec Nieves", "Ixpantepec Niev", "Ixpantepec Nieves");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1559, 20, "Santiago Niltepec", "Santiago Niltep", "Santiago Niltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1560, 20, "Oaxaca de Juárez", "Oaxaca de Juáre", "Oaxaca de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1561, 20, "Ocotlán de Morelos", "Ocotlán de More", "Ocotlán de Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1562, 20, "La Pe", "La Pe", "La Pe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1563, 20, "Asunción Ocotlán", "Asunción Ocotlá", "Asunción Ocotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1564, 20, "Pinotepa de Don Luis", "Pinotepa de Don", "Pinotepa de Don Luis");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1565, 20, "Pluma Hidalgo", "Pluma Hidalgo", "Pluma Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1566, 20, "San José del Progreso", "San José del Pr", "San José del Progreso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1567, 20, "Putla Villa de Guerrero", "Putla Villa de ", "Putla Villa de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1568, 20, "Santa Catarina Quioquitani", "Santa Catarina ", "Santa Catarina Quioquitani");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1569, 20, "Reforma de Pineda", "Reforma de Pine", "Reforma de Pineda");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1570, 20, "La Reforma", "La Reforma", "La Reforma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1571, 20, "Reyes Etla", "Reyes Etla", "Reyes Etla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1572, 20, "Rojas de Cuauhtémoc", "Rojas de Cuauht", "Rojas de Cuauhtémoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1573, 20, "Salina Cruz", "Salina Cruz", "Salina Cruz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1574, 20, "Asunción Tlacolulita", "Asunción Tlacol", "Asunción Tlacolulita");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1575, 20, "San Agustín Amatengo", "San Agustín Ama", "San Agustín Amatengo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1576, 20, "San Agustín Atenango", "San Agustín Ate", "San Agustín Atenango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1577, 20, "San Agustín Chayuco", "San Agustín Cha", "San Agustín Chayuco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1578, 20, "San Agustín de las Juntas", "San Agustín de ", "San Agustín de las Juntas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1579, 20, "San Agustín Etla", "San Agustín Etl", "San Agustín Etla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1580, 20, "San Agustín Loxicha", "San Agustín Lox", "San Agustín Loxicha");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1581, 20, "San Agustín Tlacotepec", "San Agustín Tla", "San Agustín Tlacotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1582, 20, "San Agustín Yatareni", "San Agustín Yat", "San Agustín Yatareni");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1583, 20, "San Andrés Cabecera Nueva", "San Andrés Cabe", "San Andrés Cabecera Nueva");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1584, 20, "San Andrés Dinicuiti", "San Andrés Dini", "San Andrés Dinicuiti");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1585, 20, "Ayotzintepec", "Ayotzintepec", "Ayotzintepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1586, 20, "San Andrés Huaxpaltepec", "San Andrés Huax", "San Andrés Huaxpaltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1587, 20, "San Andrés Huayápam", "San Andrés Huay", "San Andrés Huayápam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1588, 20, "San Andrés Ixtlahuaca", "San Andrés Ixtl", "San Andrés Ixtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1589, 20, "San Andrés Lagunas", "San Andrés Lagu", "San Andrés Lagunas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1590, 20, "San Andrés Nuxiño", "San Andrés Nuxi", "San Andrés Nuxiño");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1591, 20, "San Andrés Paxtlán", "San Andrés Paxt", "San Andrés Paxtlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1592, 20, "San Andrés Sinaxtla", "San Andrés Sina", "San Andrés Sinaxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1593, 20, "San Andrés Solaga", "San Andrés Sola", "San Andrés Solaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1594, 20, "San Andrés Teotilálpam", "San Andrés Teot", "San Andrés Teotilálpam");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1595, 20, "San Andrés Tepetlapa", "San Andrés Tepe", "San Andrés Tepetlapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1596, 21, "Acajete", "Acajete", "Acajete");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1597, 21, "Ajalpan", "Ajalpan", "Ajalpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1598, 21, "Naupan", "Naupan", "Naupan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1599, 21, "Nauzontla", "Nauzontla", "Nauzontla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1600, 21, "Nealtican", "Nealtican", "Nealtican");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1601, 21, "Nicolás Bravo", "Nicolás Bravo", "Nicolás Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1602, 21, "Nopalucan", "Nopalucan", "Nopalucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1603, 21, "Ocotepec", "Ocotepec", "Ocotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1604, 21, "Ocoyucan", "Ocoyucan", "Ocoyucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1605, 21, "Olintla", "Olintla", "Olintla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1606, 21, "Oriental", "Oriental", "Oriental");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1607, 21, "Pahuatlán", "Pahuatlán", "Pahuatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1608, 21, "Albino Zertuche", "Albino Zertuche", "Albino Zertuche");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1609, 21, "Palmar de Bravo", "Palmar de Bravo", "Palmar de Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1610, 21, "Pantepec", "Pantepec", "Pantepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1611, 21, "Petlalcingo", "Petlalcingo", "Petlalcingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1612, 21, "Piaxtla", "Piaxtla", "Piaxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1613, 21, "Puebla", "Puebla", "Puebla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1614, 21, "Quecholac", "Quecholac", "Quecholac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1615, 21, "Quimixtlán", "Quimixtlán", "Quimixtlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1616, 21, "Rafael Lara Grajales", "Rafael Lara Gra", "Rafael Lara Grajales");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1617, 21, "Los Reyes de Juárez", "Los Reyes de Ju", "Los Reyes de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1618, 21, "San Andrés Cholula", "San Andrés Chol", "San Andrés Cholula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1619, 21, "Aljojuca", "Aljojuca", "Aljojuca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1620, 21, "San Antonio Cañada", "San Antonio Cañ", "San Antonio Cañada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1621, 21, "San Diego la Mesa Tochimiltzingo", "San Diego la Me", "San Diego la Mesa Tochimiltzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1622, 21, "San Felipe Teotlalcingo", "San Felipe Teot", "San Felipe Teotlalcingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1623, 21, "San Felipe Tepatlán", "San Felipe Tepa", "San Felipe Tepatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1624, 21, "San Gabriel Chilac", "San Gabriel Chi", "San Gabriel Chilac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1625, 21, "San Gregorio Atzompa", "San Gregorio At", "San Gregorio Atzompa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1626, 21, "San Jerónimo Tecuanipan", "San Jerónimo Te", "San Jerónimo Tecuanipan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1627, 21, "San Jerónimo Xayacatlán", "San Jerónimo Xa", "San Jerónimo Xayacatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1628, 21, "San José Chiapa", "San José Chiapa", "San José Chiapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1629, 21, "San José Miahuatlán", "San José Miahua", "San José Miahuatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1630, 21, "Altepexi", "Altepexi", "Altepexi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1631, 21, "San Juan Atenco", "San Juan Atenco", "San Juan Atenco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1632, 21, "San Juan Atzompa", "San Juan Atzomp", "San Juan Atzompa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1633, 21, "San Martín Texmelucan", "San Martín Texm", "San Martín Texmelucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1634, 21, "San Martín Totoltepec", "San Martín Toto", "San Martín Totoltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1635, 21, "San Matías Tlalancaleca", "San Matías Tlal", "San Matías Tlalancaleca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1636, 21, "San Miguel Ixitlán", "San Miguel Ixit", "San Miguel Ixitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1637, 21, "San Miguel Xoxtla", "San Miguel Xoxt", "San Miguel Xoxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1638, 21, "San Nicolás Buenos Aires", "San Nicolás Bue", "San Nicolás Buenos Aires");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1639, 21, "San Nicolás de los Ranchos", "San Nicolás de ", "San Nicolás de los Ranchos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1640, 21, "San Pablo Anicano", "San Pablo Anica", "San Pablo Anicano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1641, 21, "Amixtlán", "Amixtlán", "Amixtlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1642, 21, "San Pedro Cholula", "San Pedro Cholu", "San Pedro Cholula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1643, 21, "San Pedro Yeloixtlahuaca", "San Pedro Yeloi", "San Pedro Yeloixtlahuaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1644, 21, "San Salvador el Seco", "San Salvador el", "San Salvador el Seco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1645, 21, "San Salvador el Verde", "San Salvador el", "San Salvador el Verde");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1646, 21, "San Salvador Huixcolotla", "San Salvador Hu", "San Salvador Huixcolotla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1647, 21, "San Sebastián Tlacotepec", "San Sebastián T", "San Sebastián Tlacotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1648, 21, "Santa Catarina Tlaltempan", "Santa Catarina ", "Santa Catarina Tlaltempan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1649, 21, "Santa Inés Ahuatempan", "Santa Inés Ahua", "Santa Inés Ahuatempan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1650, 21, "Santa Isabel Cholula", "Santa Isabel Ch", "Santa Isabel Cholula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1651, 21, "Santiago Miahuatlán", "Santiago Miahua", "Santiago Miahuatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1652, 21, "Amozoc", "Amozoc", "Amozoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1653, 21, "Huehuetlán el Grande", "Huehuetlán el G", "Huehuetlán el Grande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1654, 21, "Santo Tomás Hueyotlipan", "Santo Tomás Hue", "Santo Tomás Hueyotlipan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1655, 21, "Soltepec", "Soltepec", "Soltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1656, 21, "Tecali de Herrera", "Tecali de Herre", "Tecali de Herrera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1657, 21, "Tecamachalco", "Tecamachalco", "Tecamachalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1658, 21, "Tecomatlán", "Tecomatlán", "Tecomatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1659, 21, "Tehuacán", "Tehuacán", "Tehuacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1660, 21, "Tehuitzingo", "Tehuitzingo", "Tehuitzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1661, 21, "Tenampulco", "Tenampulco", "Tenampulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1662, 21, "Teopantlán", "Teopantlán", "Teopantlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1663, 21, "Aquixtla", "Aquixtla", "Aquixtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1664, 21, "Teotlalco", "Teotlalco", "Teotlalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1665, 21, "Tepanco de López", "Tepanco de Lópe", "Tepanco de López");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1666, 21, "Tepango de Rodríguez", "Tepango de Rodr", "Tepango de Rodríguez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1667, 21, "Tepatlaxco de Hidalgo", "Tepatlaxco de H", "Tepatlaxco de Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1668, 21, "Tepeaca", "Tepeaca", "Tepeaca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1669, 21, "Tepemaxalco", "Tepemaxalco", "Tepemaxalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1670, 21, "Tepeojuma", "Tepeojuma", "Tepeojuma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1671, 21, "Tepetzintla", "Tepetzintla", "Tepetzintla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1672, 21, "Tepexco", "Tepexco", "Tepexco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1673, 21, "Tepexi de Rodríguez", "Tepexi de Rodrí", "Tepexi de Rodríguez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1674, 21, "Atempan", "Atempan", "Atempan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1675, 21, "Tepeyahualco", "Tepeyahualco", "Tepeyahualco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1676, 21, "Tepeyahualco de Cuauhtémoc", "Tepeyahualco de", "Tepeyahualco de Cuauhtémoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1677, 21, "Tetela de Ocampo", "Tetela de Ocamp", "Tetela de Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1678, 21, "Teteles de Avila Castillo", "Teteles de Avil", "Teteles de Avila Castillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1679, 21, "Teziutlán", "Teziutlán", "Teziutlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1680, 21, "Tianguismanalco", "Tianguismanalco", "Tianguismanalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1681, 21, "Tilapa", "Tilapa", "Tilapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1682, 21, "Tlacotepec de Benito Juárez", "Tlacotepec de B", "Tlacotepec de Benito Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1683, 21, "Tlacuilotepec", "Tlacuilotepec", "Tlacuilotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1684, 21, "Tlachichuca", "Tlachichuca", "Tlachichuca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1685, 21, "Atexcal", "Atexcal", "Atexcal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1686, 21, "Tlahuapan", "Tlahuapan", "Tlahuapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1687, 21, "Tlaltenango", "Tlaltenango", "Tlaltenango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1688, 21, "Tlanepantla", "Tlanepantla", "Tlanepantla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1689, 21, "Tlaola", "Tlaola", "Tlaola");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1690, 21, "Tlapacoya", "Tlapacoya", "Tlapacoya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1691, 21, "Tlapanalá", "Tlapanalá", "Tlapanalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1692, 21, "Tlatlauquitepec", "Tlatlauquitepec", "Tlatlauquitepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1693, 21, "Tlaxco", "Tlaxco", "Tlaxco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1694, 21, "Tochimilco", "Tochimilco", "Tochimilco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1695, 21, "Tochtepec", "Tochtepec", "Tochtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1696, 21, "Atlixco", "Atlixco", "Atlixco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1697, 21, "Totoltepec de Guerrero", "Totoltepec de G", "Totoltepec de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1698, 21, "Tulcingo", "Tulcingo", "Tulcingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1699, 21, "Tuzamapan de Galeana", "Tuzamapan de Ga", "Tuzamapan de Galeana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1700, 21, "Tzicatlacoyan", "Tzicatlacoyan", "Tzicatlacoyan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1701, 21, "Venustiano Carranza", "Venustiano Carr", "Venustiano Carranza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1702, 21, "Vicente Guerrero", "Vicente Guerrer", "Vicente Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1703, 21, "Xayacatlán de Bravo", "Xayacatlán de B", "Xayacatlán de Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1704, 21, "Xicotepec", "Xicotepec", "Xicotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1705, 21, "Xicotlán", "Xicotlán", "Xicotlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1706, 21, "Xiutetelco", "Xiutetelco", "Xiutetelco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1707, 21, "Acateno", "Acateno", "Acateno");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1708, 21, "Atoyatempan", "Atoyatempan", "Atoyatempan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1709, 21, "Xochiapulco", "Xochiapulco", "Xochiapulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1710, 21, "Xochiltepec", "Xochiltepec", "Xochiltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1711, 21, "Xochitlán de Vicente Suárez", "Xochitlán de Vi", "Xochitlán de Vicente Suárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1712, 21, "Xochitlán Todos Santos", "Xochitlán Todos", "Xochitlán Todos Santos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1713, 21, "Yaonáhuac", "Yaonáhuac", "Yaonáhuac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1714, 21, "Yehualtepec", "Yehualtepec", "Yehualtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1715, 21, "Zacapala", "Zacapala", "Zacapala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1716, 21, "Zacapoaxtla", "Zacapoaxtla", "Zacapoaxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1717, 21, "Zacatlán", "Zacatlán", "Zacatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1718, 21, "Zapotitlán", "Zapotitlán", "Zapotitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1719, 21, "Atzala", "Atzala", "Atzala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1720, 21, "Zapotitlán de Méndez", "Zapotitlán de M", "Zapotitlán de Méndez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1721, 21, "Zaragoza", "Zaragoza", "Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1722, 21, "Zautla", "Zautla", "Zautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1723, 21, "Zihuateutla", "Zihuateutla", "Zihuateutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1724, 21, "Zinacatepec", "Zinacatepec", "Zinacatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1725, 21, "Zongozotla", "Zongozotla", "Zongozotla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1726, 21, "Zoquiapan", "Zoquiapan", "Zoquiapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1727, 21, "Zoquitlán", "Zoquitlán", "Zoquitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1728, 21, "Atzitzihuacán", "Atzitzihuacán", "Atzitzihuacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1729, 21, "Atzitzintla", "Atzitzintla", "Atzitzintla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1730, 21, "Axutla", "Axutla", "Axutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1731, 21, "Ayotoxco de Guerrero", "Ayotoxco de Gue", "Ayotoxco de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1732, 21, "Calpan", "Calpan", "Calpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1733, 21, "Caltepec", "Caltepec", "Caltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1734, 21, "Camocuautla", "Camocuautla", "Camocuautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1735, 21, "Caxhuacan", "Caxhuacan", "Caxhuacan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1736, 21, "Acatlán", "Acatlán", "Acatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1737, 21, "Coatepec", "Coatepec", "Coatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1738, 21, "Coatzingo", "Coatzingo", "Coatzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1739, 21, "Cohetzala", "Cohetzala", "Cohetzala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1740, 21, "Cohuecan", "Cohuecan", "Cohuecan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1741, 21, "Coronango", "Coronango", "Coronango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1742, 21, "Coxcatlán", "Coxcatlán", "Coxcatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1743, 21, "Coyomeapan", "Coyomeapan", "Coyomeapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1744, 21, "Coyotepec", "Coyotepec", "Coyotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1745, 21, "Cuapiaxtla de Madero", "Cuapiaxtla de M", "Cuapiaxtla de Madero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1746, 21, "Cuautempan", "Cuautempan", "Cuautempan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1747, 21, "Acatzingo", "Acatzingo", "Acatzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1748, 21, "Cuautinchán", "Cuautinchán", "Cuautinchán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1749, 21, "Cuautlancingo", "Cuautlancingo", "Cuautlancingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1750, 21, "Cuayuca de Andrade", "Cuayuca de Andr", "Cuayuca de Andrade");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1751, 21, "Cuetzalan del Progreso", "Cuetzalan del P", "Cuetzalan del Progreso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1752, 21, "Cuyoaco", "Cuyoaco", "Cuyoaco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1753, 21, "Chalchicomula de Sesma", "Chalchicomula d", "Chalchicomula de Sesma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1754, 21, "Chapulco", "Chapulco", "Chapulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1755, 21, "Chiautla", "Chiautla", "Chiautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1756, 21, "Chiautzingo", "Chiautzingo", "Chiautzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1757, 21, "Chiconcuautla", "Chiconcuautla", "Chiconcuautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1758, 21, "Acteopan", "Acteopan", "Acteopan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1759, 21, "Chichiquila", "Chichiquila", "Chichiquila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1760, 21, "Chietla", "Chietla", "Chietla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1761, 21, "Chigmecatitlán", "Chigmecatitlán", "Chigmecatitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1762, 21, "Chignahuapan", "Chignahuapan", "Chignahuapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1763, 21, "Chignautla", "Chignautla", "Chignautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1764, 21, "Chila", "Chila", "Chila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1765, 21, "Chila de la Sal", "Chila de la Sal", "Chila de la Sal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1766, 21, "Honey", "Honey", "Honey");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1767, 21, "Chilchotla", "Chilchotla", "Chilchotla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1768, 21, "Chinantla", "Chinantla", "Chinantla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1769, 21, "Ahuacatlán", "Ahuacatlán", "Ahuacatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1770, 21, "Domingo Arenas", "Domingo Arenas", "Domingo Arenas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1771, 21, "Eloxochitlán", "Eloxochitlán", "Eloxochitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1772, 21, "Epatlán", "Epatlán", "Epatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1773, 21, "Esperanza", "Esperanza", "Esperanza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1774, 21, "Francisco Z. Mena", "Francisco Z. Me", "Francisco Z. Mena");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1775, 21, "General Felipe Ángeles", "General Felipe ", "General Felipe Ángeles");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1776, 21, "Guadalupe", "Guadalupe", "Guadalupe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1777, 21, "Guadalupe Victoria", "Guadalupe Victo", "Guadalupe Victoria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1778, 21, "Hermenegildo Galeana", "Hermenegildo Ga", "Hermenegildo Galeana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1779, 21, "Huaquechula", "Huaquechula", "Huaquechula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1780, 21, "Ahuatlán", "Ahuatlán", "Ahuatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1781, 21, "Huatlatlauca", "Huatlatlauca", "Huatlatlauca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1782, 21, "Huauchinango", "Huauchinango", "Huauchinango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1783, 21, "Huehuetla", "Huehuetla", "Huehuetla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1784, 21, "Huehuetlán el Chico", "Huehuetlán el C", "Huehuetlán el Chico");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1785, 21, "Huejotzingo", "Huejotzingo", "Huejotzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1786, 21, "Hueyapan", "Hueyapan", "Hueyapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1787, 21, "Hueytamalco", "Hueytamalco", "Hueytamalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1788, 21, "Hueytlalpan", "Hueytlalpan", "Hueytlalpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1789, 21, "Huitzilan de Serdán", "Huitzilan de Se", "Huitzilan de Serdán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1790, 21, "Huitziltepec", "Huitziltepec", "Huitziltepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1791, 21, "Ahuazotepec", "Ahuazotepec", "Ahuazotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1792, 21, "Atlequizayan", "Atlequizayan", "Atlequizayan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1793, 21, "Ixcamilpa de Guerrero", "Ixcamilpa de Gu", "Ixcamilpa de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1794, 21, "Ixcaquixtla", "Ixcaquixtla", "Ixcaquixtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1795, 21, "Ixtacamaxtitlán", "Ixtacamaxtitlán", "Ixtacamaxtitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1796, 21, "Ixtepec", "Ixtepec", "Ixtepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1797, 21, "Izúcar de Matamoros", "Izúcar de Matam", "Izúcar de Matamoros");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1798, 21, "Jalpan", "Jalpan", "Jalpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1799, 21, "Jolalpan", "Jolalpan", "Jolalpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1800, 21, "Jonotla", "Jonotla", "Jonotla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1801, 21, "Jopala", "Jopala", "Jopala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1802, 21, "Ahuehuetitla", "Ahuehuetitla", "Ahuehuetitla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1803, 21, "Juan C. Bonilla", "Juan C. Bonilla", "Juan C. Bonilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1804, 21, "Juan Galindo", "Juan Galindo", "Juan Galindo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1805, 21, "Juan N. Méndez", "Juan N. Méndez", "Juan N. Méndez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1806, 21, "Lafragua", "Lafragua", "Lafragua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1807, 21, "Libres", "Libres", "Libres");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1808, 21, "La Magdalena Tlatlauquitepec", "La Magdalena Tl", "La Magdalena Tlatlauquitepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1809, 21, "Mazapiltepec de Juárez", "Mazapiltepec de", "Mazapiltepec de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1810, 21, "Mixtla", "Mixtla", "Mixtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1811, 21, "Molcaxac", "Molcaxac", "Molcaxac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1812, 21, "Cañada Morelos", "Cañada Morelos", "Cañada Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1813, 22, "Amealco de Bonfil", "Amealco de Bonf", "Amealco de Bonfil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1814, 22, "Landa de Matamoros", "Landa de Matamo", "Landa de Matamoros");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1815, 22, "El Marqués", "El Marqués", "El Marqués");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1816, 22, "Pedro Escobedo", "Pedro Escobedo", "Pedro Escobedo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1817, 22, "Peñamiller", "Peñamiller", "Peñamiller");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1818, 22, "Querétaro", "Querétaro", "Querétaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1819, 22, "San Joaquín", "San Joaquín", "San Joaquín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1820, 22, "San Juan del Río", "San Juan del Rí", "San Juan del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1821, 22, "Tequisquiapan", "Tequisquiapan", "Tequisquiapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1822, 22, "Tolimán", "Tolimán", "Tolimán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1823, 22, "Pinal de Amoles", "Pinal de Amoles", "Pinal de Amoles");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1824, 22, "Arroyo Seco", "Arroyo Seco", "Arroyo Seco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1825, 22, "Cadereyta de Montes", "Cadereyta de Mo", "Cadereyta de Montes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1826, 22, "Colón", "Colón", "Colón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1827, 22, "Corregidora", "Corregidora", "Corregidora");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1828, 22, "Ezequiel Montes", "Ezequiel Montes", "Ezequiel Montes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1829, 22, "Huimilpan", "Huimilpan", "Huimilpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1830, 22, "Jalpan de Serra", "Jalpan de Serra", "Jalpan de Serra");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1831, 23, "Cozumel", "Cozumel", "Cozumel");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1832, 23, "Bacalar", "Bacalar", "Bacalar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1833, 23, "Felipe Carrillo Puerto", "Felipe Carrillo", "Felipe Carrillo Puerto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1834, 23, "Ranchería", "Ranchería", "Ranchería");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1835, 23, "Isla Mujeres", "Isla Mujeres", "Isla Mujeres");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1836, 23, "Othón P. Blanco", "Othón P. Blanco", "Othón P. Blanco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1837, 23, "Benito Juárez", "Benito Juárez", "Benito Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1838, 23, "José María Morelos", "José María More", "José María Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1839, 23, "Lázaro Cárdenas", "Lázaro Cárdenas", "Lázaro Cárdenas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1840, 23, "Solidaridad", "Solidaridad", "Solidaridad");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1841, 23, "Tulum", "Tulum", "Tulum");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1842, 24, "Ahualulco", "Ahualulco", "Ahualulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1843, 24, "Ciudad del Maíz", "Ciudad del Maíz", "Ciudad del Maíz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1844, 24, "Ciudad Fernández", "Ciudad Fernánde", "Ciudad Fernández");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1845, 24, "Tancanhuitz", "Tancanhuitz", "Tancanhuitz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1846, 24, "Ciudad Valles", "Ciudad Valles", "Ciudad Valles");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1847, 24, "Coxcatlán", "Coxcatlán", "Coxcatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1848, 24, "Charcas", "Charcas", "Charcas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1849, 24, "Ebano", "Ebano", "Ebano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1850, 24, "Guadalcázar", "Guadalcázar", "Guadalcázar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1851, 24, "Huehuetlán", "Huehuetlán", "Huehuetlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1852, 24, "Lagunillas", "Lagunillas", "Lagunillas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1853, 24, "Alaquines", "Alaquines", "Alaquines");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1854, 24, "Matehuala", "Matehuala", "Matehuala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1855, 24, "Mexquitic de Carmona", "Mexquitic de Ca", "Mexquitic de Carmona");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1856, 24, "Moctezuma", "Moctezuma", "Moctezuma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1857, 24, "Rayón", "Rayón", "Rayón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1858, 24, "Rioverde", "Rioverde", "Rioverde");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1859, 24, "Salinas", "Salinas", "Salinas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1860, 24, "San Antonio", "San Antonio", "San Antonio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1861, 24, "San Ciro de Acosta", "San Ciro de Aco", "San Ciro de Acosta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1862, 24, "San Luis Potosí", "San Luis Potosí", "San Luis Potosí");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1863, 24, "San Martín Chalchicuautla", "San Martín Chal", "San Martín Chalchicuautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1864, 24, "Aquismón", "Aquismón", "Aquismón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1865, 24, "San Nicolás Tolentino", "San Nicolás Tol", "San Nicolás Tolentino");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1866, 24, "Santa Catarina", "Santa Catarina", "Santa Catarina");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1867, 24, "Santa María del Río", "Santa María del", "Santa María del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1868, 24, "Santo Domingo", "Santo Domingo", "Santo Domingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1869, 24, "San Vicente Tancuayalab", "San Vicente Tan", "San Vicente Tancuayalab");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1870, 24, "Soledad de Graciano Sánchez", "Soledad de Grac", "Soledad de Graciano Sánchez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1871, 24, "Tamasopo", "Tamasopo", "Tamasopo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1872, 24, "Tamazunchale", "Tamazunchale", "Tamazunchale");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1873, 24, "Tampacán", "Tampacán", "Tampacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1874, 24, "Tampamolón Corona", "Tampamolón Coro", "Tampamolón Corona");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1875, 24, "Armadillo de los Infante", "Armadillo de lo", "Armadillo de los Infante");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1876, 24, "Tamuín", "Tamuín", "Tamuín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1877, 24, "Tanlajás", "Tanlajás", "Tanlajás");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1878, 24, "Tanquián de Escobedo", "Tanquián de Esc", "Tanquián de Escobedo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1879, 24, "Tierra Nueva", "Tierra Nueva", "Tierra Nueva");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1880, 24, "Vanegas", "Vanegas", "Vanegas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1881, 24, "Venado", "Venado", "Venado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1882, 24, "Villa de Arriaga", "Villa de Arriag", "Villa de Arriaga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1883, 24, "Villa de Guadalupe", "Villa de Guadal", "Villa de Guadalupe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1884, 24, "Villa de la Paz", "Villa de la Paz", "Villa de la Paz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1885, 24, "Villa de Ramos", "Villa de Ramos", "Villa de Ramos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1886, 24, "Cárdenas", "Cárdenas", "Cárdenas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1887, 24, "Villa de Reyes", "Villa de Reyes", "Villa de Reyes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1888, 24, "Villa Hidalgo", "Villa Hidalgo", "Villa Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1889, 24, "Villa Juárez", "Villa Juárez", "Villa Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1890, 24, "Axtla de Terrazas", "Axtla de Terraz", "Axtla de Terrazas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1891, 24, "Xilitla", "Xilitla", "Xilitla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1892, 24, "Zaragoza", "Zaragoza", "Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1893, 24, "Villa de Arista", "Villa de Arista", "Villa de Arista");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1894, 24, "Matlapa", "Matlapa", "Matlapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1895, 24, "El Naranjo", "El Naranjo", "El Naranjo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1896, 24, "Catorce", "Catorce", "Catorce");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1897, 24, "Cedral", "Cedral", "Cedral");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1898, 24, "Cerritos", "Cerritos", "Cerritos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1899, 24, "Cerro de San Pedro", "Cerro de San Pe", "Cerro de San Pedro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1900, 25, "Ahome", "Ahome", "Ahome");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1901, 25, "El Fuerte", "El Fuerte", "El Fuerte");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1902, 25, "Guasave", "Guasave", "Guasave");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1903, 25, "Mazatlán", "Mazatlán", "Mazatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1904, 25, "Mocorito", "Mocorito", "Mocorito");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1905, 25, "Rosario", "Rosario", "Rosario");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1906, 25, "Salvador Alvarado", "Salvador Alvara", "Salvador Alvarado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1907, 25, "San Ignacio", "San Ignacio", "San Ignacio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1908, 25, "Sinaloa", "Sinaloa", "Sinaloa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1909, 25, "Navolato", "Navolato", "Navolato");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1910, 25, "Angostura", "Angostura", "Angostura");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1911, 25, "Badiraguato", "Badiraguato", "Badiraguato");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1912, 25, "Concordia", "Concordia", "Concordia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1913, 25, "Cosalá", "Cosalá", "Cosalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1914, 25, "Culiacán", "Culiacán", "Culiacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1915, 25, "Choix", "Choix", "Choix");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1916, 25, "Elota", "Elota", "Elota");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1917, 25, "Escuinapa", "Escuinapa", "Escuinapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1918, 26, "Aconchi", "Aconchi", "Aconchi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1919, 26, "Bacerac", "Bacerac", "Bacerac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1920, 26, "Bacoachi", "Bacoachi", "Bacoachi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1921, 26, "Bácum", "Bácum", "Bácum");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1922, 26, "Banámichi", "Banámichi", "Banámichi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1923, 26, "Baviácora", "Baviácora", "Baviácora");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1924, 26, "Bavispe", "Bavispe", "Bavispe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1925, 26, "Benjamín Hill", "Benjamín Hill", "Benjamín Hill");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1926, 26, "Caborca", "Caborca", "Caborca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1927, 26, "Cajeme", "Cajeme", "Cajeme");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1928, 26, "Cananea", "Cananea", "Cananea");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1929, 26, "Agua Prieta", "Agua Prieta", "Agua Prieta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1930, 26, "Carbó", "Carbó", "Carbó");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1931, 26, "La Colorada", "La Colorada", "La Colorada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1932, 26, "Cucurpe", "Cucurpe", "Cucurpe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1933, 26, "Cumpas", "Cumpas", "Cumpas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1934, 26, "Divisaderos", "Divisaderos", "Divisaderos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1935, 26, "Empalme", "Empalme", "Empalme");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1936, 26, "Etchojoa", "Etchojoa", "Etchojoa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1937, 26, "Fronteras", "Fronteras", "Fronteras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1938, 26, "Granados", "Granados", "Granados");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1939, 26, "Guaymas", "Guaymas", "Guaymas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1940, 26, "Ranchería", "Ranchería", "Ranchería");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1941, 26, "Alamos", "Alamos", "Alamos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1942, 26, "Hermosillo", "Hermosillo", "Hermosillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1943, 26, "Huachinera", "Huachinera", "Huachinera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1944, 26, "Huásabas", "Huásabas", "Huásabas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1945, 26, "Huatabampo", "Huatabampo", "Huatabampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1946, 26, "Huépac", "Huépac", "Huépac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1947, 26, "Imuris", "Imuris", "Imuris");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1948, 26, "Magdalena", "Magdalena", "Magdalena");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1949, 26, "Mazatán", "Mazatán", "Mazatán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1950, 26, "Moctezuma", "Moctezuma", "Moctezuma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1951, 26, "Naco", "Naco", "Naco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1952, 26, "Altar", "Altar", "Altar");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1953, 26, "Nácori Chico", "Nácori Chico", "Nácori Chico");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1954, 26, "Nacozari de García", "Nacozari de Gar", "Nacozari de García");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1955, 26, "Navojoa", "Navojoa", "Navojoa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1956, 26, "Nogales", "Nogales", "Nogales");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1957, 26, "Onavas", "Onavas", "Onavas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1958, 26, "Opodepe", "Opodepe", "Opodepe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1959, 26, "Oquitoa", "Oquitoa", "Oquitoa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1960, 26, "Pitiquito", "Pitiquito", "Pitiquito");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1961, 26, "Puerto Peñasco", "Puerto Peñasco", "Puerto Peñasco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1962, 26, "Quiriego", "Quiriego", "Quiriego");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1963, 26, "Arivechi", "Arivechi", "Arivechi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1964, 26, "Rayón", "Rayón", "Rayón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1965, 26, "Rosario", "Rosario", "Rosario");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1966, 26, "Sahuaripa", "Sahuaripa", "Sahuaripa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1967, 26, "San Felipe de Jesús", "San Felipe de J", "San Felipe de Jesús");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1968, 26, "San Javier", "San Javier", "San Javier");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1969, 26, "San Luis Río Colorado", "San Luis Río Co", "San Luis Río Colorado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1970, 26, "San Miguel de Horcasitas", "San Miguel de H", "San Miguel de Horcasitas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1971, 26, "San Pedro de la Cueva", "San Pedro de la", "San Pedro de la Cueva");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1972, 26, "Santa Ana", "Santa Ana", "Santa Ana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1973, 26, "Santa Cruz", "Santa Cruz", "Santa Cruz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1974, 26, "Arizpe", "Arizpe", "Arizpe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1975, 26, "Sáric", "Sáric", "Sáric");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1976, 26, "Soyopa", "Soyopa", "Soyopa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1977, 26, "Suaqui Grande", "Suaqui Grande", "Suaqui Grande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1978, 26, "Tepache", "Tepache", "Tepache");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1979, 26, "Trincheras", "Trincheras", "Trincheras");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1980, 26, "Tubutama", "Tubutama", "Tubutama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1981, 26, "Ures", "Ures", "Ures");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1982, 26, "Villa Hidalgo", "Villa Hidalgo", "Villa Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1983, 26, "Villa Pesqueira", "Villa Pesqueira", "Villa Pesqueira");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1984, 26, "Yécora", "Yécora", "Yécora");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1985, 26, "Atil", "Atil", "Atil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1986, 26, "General Plutarco Elías Calles", "General Plutarc", "General Plutarco Elías Calles");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1987, 26, "Benito Juárez", "Benito Juárez", "Benito Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1988, 26, "San Ignacio Río Muerto", "San Ignacio Río", "San Ignacio Río Muerto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1989, 26, "Bacadéhuachi", "Bacadéhuachi", "Bacadéhuachi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1990, 26, " IV", " IV", " IV");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1991, 26, "III", "III", "III");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1992, 26, "Bacanora", "Bacanora", "Bacanora");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1993, 27, "Balancán", "Balancán", "Balancán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1994, 27, "Jalpa de Méndez", "Jalpa de Méndez", "Jalpa de Méndez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1995, 27, "Jonuta", "Jonuta", "Jonuta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1996, 27, "Macuspana", "Macuspana", "Macuspana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1997, 27, "Nacajuca", "Nacajuca", "Nacajuca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1998, 27, "Paraíso", "Paraíso", "Paraíso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (1999, 27, "Tacotalpa", "Tacotalpa", "Tacotalpa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2000, 27, "Teapa", "Teapa", "Teapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2001, 27, "Tenosique", "Tenosique", "Tenosique");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2002, 27, "Cárdenas", "Cárdenas", "Cárdenas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2003, 27, "Ranchería", "Ranchería", "Ranchería");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2004, 27, "Centla", "Centla", "Centla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2005, 27, "Centro", "Centro", "Centro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2006, 27, "Comalcalco", "Comalcalco", "Comalcalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2007, 27, "Cunduacán", "Cunduacán", "Cunduacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2008, 27, "Emiliano Zapata", "Emiliano Zapata", "Emiliano Zapata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2009, 27, "Huimanguillo", "Huimanguillo", "Huimanguillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2010, 27, "Jalapa", "Jalapa", "Jalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2011, 28, "Abasolo", "Abasolo", "Abasolo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2012, 28, "Cruillas", "Cruillas", "Cruillas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2013, 28, "Gómez Farías", "Gómez Farías", "Gómez Farías");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2014, 28, "González", "González", "González");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2015, 28, "Güémez", "Güémez", "Güémez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2016, 28, "Guerrero", "Guerrero", "Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2017, 28, "Gustavo Díaz Ordaz", "Gustavo Díaz Or", "Gustavo Díaz Ordaz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2018, 28, "Hidalgo", "Hidalgo", "Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2019, 28, "Jaumave", "Jaumave", "Jaumave");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2020, 28, "Jiménez", "Jiménez", "Jiménez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2021, 28, "Llera", "Llera", "Llera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2022, 28, "Aldama", "Aldama", "Aldama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2023, 28, "Mainero", "Mainero", "Mainero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2024, 28, "El Mante", "El Mante", "El Mante");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2025, 28, "Fraccionamiento", "Fraccionamiento", "Fraccionamiento");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2026, 28, "Matamoros", "Matamoros", "Matamoros");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2027, 28, "Méndez", "Méndez", "Méndez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2028, 28, "Mier", "Mier", "Mier");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2029, 28, "Miguel Alemán", "Miguel Alemán", "Miguel Alemán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2030, 28, "Miquihuana", "Miquihuana", "Miquihuana");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2031, 28, "Nuevo Laredo", "Nuevo Laredo", "Nuevo Laredo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2032, 28, "3", "3", "3");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2033, 28, "Nuevo Morelos", "Nuevo Morelos", "Nuevo Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2034, 28, "Ocampo", "Ocampo", "Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2035, 28, "Altamira", "Altamira", "Altamira");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2036, 28, "Padilla", "Padilla", "Padilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2037, 28, "Palmillas", "Palmillas", "Palmillas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2038, 28, "Reynosa", "Reynosa", "Reynosa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2039, 28, "Río Bravo", "Río Bravo", "Río Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2040, 28, "San Carlos", "San Carlos", "San Carlos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2041, 28, "San Fernando", "San Fernando", "San Fernando");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2042, 28, "San Nicolás", "San Nicolás", "San Nicolás");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2043, 28, "Soto la Marina", "Soto la Marina", "Soto la Marina");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2044, 28, "Tampico", "Tampico", "Tampico");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2045, 28, "Tula", "Tula", "Tula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2046, 28, "Antiguo Morelos", "Antiguo Morelos", "Antiguo Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2047, 28, "Valle Hermoso", "Valle Hermoso", "Valle Hermoso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2048, 28, "Victoria", "Victoria", "Victoria");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2049, 28, "Villagrán", "Villagrán", "Villagrán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2050, 28, "Xicoténcatl", "Xicoténcatl", "Xicoténcatl");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2051, 28, "Burgos", "Burgos", "Burgos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2052, 28, "Bustamante", "Bustamante", "Bustamante");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2053, 28, "Camargo", "Camargo", "Camargo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2054, 28, "Casas", "Casas", "Casas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2055, 28, "Ciudad Madero", "Ciudad Madero", "Ciudad Madero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2056, 28, "Colonia", "Colonia", "Colonia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2057, 29, "Amaxac de Guerrero", "Amaxac de Guerr", "Amaxac de Guerrero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2058, 29, "Chiautempan", "Chiautempan", "Chiautempan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2059, 29, "Muñoz de Domingo Arenas", "Muñoz de Doming", "Muñoz de Domingo Arenas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2060, 29, "Españita", "Españita", "Españita");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2061, 29, "Huamantla", "Huamantla", "Huamantla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2062, 29, "Hueyotlipan", "Hueyotlipan", "Hueyotlipan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2063, 29, "Ixtacuixtla de Mariano Matamoros", "Ixtacuixtla de ", "Ixtacuixtla de Mariano Matamoros");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2064, 29, "Ixtenco", "Ixtenco", "Ixtenco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2065, 29, "Mazatecochco de José María Morelos", "Mazatecochco de", "Mazatecochco de José María Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2066, 29, "Contla de Juan Cuamatzi", "Contla de Juan ", "Contla de Juan Cuamatzi");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2067, 29, "Tepetitla de Lardizábal", "Tepetitla de La", "Tepetitla de Lardizábal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2068, 29, "Apetatitlán de Antonio Carvajal", "Apetatitlán de ", "Apetatitlán de Antonio Carvajal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2069, 29, "Sanctórum de Lázaro Cárdenas", "Sanctórum de Lá", "Sanctórum de Lázaro Cárdenas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2070, 29, "Nanacamilpa de Mariano Arista", "Nanacamilpa de ", "Nanacamilpa de Mariano Arista");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2071, 29, "Acuamanala de Miguel Hidalgo", "Acuamanala de M", "Acuamanala de Miguel Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2072, 29, "Natívitas", "Natívitas", "Natívitas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2073, 29, "Panotla", "Panotla", "Panotla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2074, 29, "San Pablo del Monte", "San Pablo del M", "San Pablo del Monte");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2075, 29, "Santa Cruz Tlaxcala", "Santa Cruz Tlax", "Santa Cruz Tlaxcala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2076, 29, "Tenancingo", "Tenancingo", "Tenancingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2077, 29, "Teolocholco", "Teolocholco", "Teolocholco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2078, 29, "Tepeyanco", "Tepeyanco", "Tepeyanco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2079, 29, "Atlangatepec", "Atlangatepec", "Atlangatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2080, 29, "Terrenate", "Terrenate", "Terrenate");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2081, 29, "Tetla de la Solidaridad", "Tetla de la Sol", "Tetla de la Solidaridad");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2082, 29, "Tetlatlahuca", "Tetlatlahuca", "Tetlatlahuca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2083, 29, "Tlaxcala", "Tlaxcala", "Tlaxcala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2084, 29, "Tlaxco", "Tlaxco", "Tlaxco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2085, 29, "Tocatlán", "Tocatlán", "Tocatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2086, 29, "Totolac", "Totolac", "Totolac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2087, 29, "Ziltlaltépec de Trinidad Sánchez Santos", "Ziltlaltépec de", "Ziltlaltépec de Trinidad Sánchez Santos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2088, 29, "Tzompantepec", "Tzompantepec", "Tzompantepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2089, 29, "Xaloztoc", "Xaloztoc", "Xaloztoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2090, 29, "Atltzayanca", "Atltzayanca", "Atltzayanca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2091, 29, "Xaltocan", "Xaltocan", "Xaltocan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2092, 29, "Papalotla de Xicohténcatl", "Papalotla de Xi", "Papalotla de Xicohténcatl");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2093, 29, "Xicohtzinco", "Xicohtzinco", "Xicohtzinco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2094, 29, "Yauhquemehcan", "Yauhquemehcan", "Yauhquemehcan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2095, 29, "Zacatelco", "Zacatelco", "Zacatelco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2096, 29, "Benito Juárez", "Benito Juárez", "Benito Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2097, 29, "Emiliano Zapata", "Emiliano Zapata", "Emiliano Zapata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2098, 29, "Lázaro Cárdenas", "Lázaro Cárdenas", "Lázaro Cárdenas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2099, 29, "La Magdalena Tlaltelulco", "La Magdalena Tl", "La Magdalena Tlaltelulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2100, 29, "San Damián Texóloc", "San Damián Texó", "San Damián Texóloc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2101, 29, "Apizaco", "Apizaco", "Apizaco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2102, 29, "San Francisco Tetlanohcan", "San Francisco T", "San Francisco Tetlanohcan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2103, 29, "San Jerónimo Zacualpan", "San Jerónimo Za", "San Jerónimo Zacualpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2104, 29, "San José Teacalco", "San José Teacal", "San José Teacalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2105, 29, "San Juan Huactzinco", "San Juan Huactz", "San Juan Huactzinco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2106, 29, "San Lorenzo Axocomanitla", "San Lorenzo Axo", "San Lorenzo Axocomanitla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2107, 29, "San Lucas Tecopilco", "San Lucas Tecop", "San Lucas Tecopilco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2108, 29, "Santa Ana Nopalucan", "Santa Ana Nopal", "Santa Ana Nopalucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2109, 29, "Santa Apolonia Teacalco", "Santa Apolonia ", "Santa Apolonia Teacalco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2110, 29, "Santa Catarina Ayometla", "Santa Catarina ", "Santa Catarina Ayometla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2111, 29, "Santa Cruz Quilehtla", "Santa Cruz Quil", "Santa Cruz Quilehtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2112, 29, "Calpulalpan", "Calpulalpan", "Calpulalpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2113, 29, "Santa Isabel Xiloxoxtla", "Santa Isabel Xi", "Santa Isabel Xiloxoxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2114, 29, "El Carmen Tequexquitla", "El Carmen Teque", "El Carmen Tequexquitla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2115, 29, "Cuapiaxtla", "Cuapiaxtla", "Cuapiaxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2116, 29, "Cuaxomulco", "Cuaxomulco", "Cuaxomulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2117, 30, "Acajete", "Acajete", "Acajete");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2118, 30, "Altotonga", "Altotonga", "Altotonga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2119, 30, "Manlio Fabio Altamirano", "Manlio Fabio Al", "Manlio Fabio Altamirano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2120, 30, "Mariano Escobedo", "Mariano Escobed", "Mariano Escobedo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2121, 30, "Martínez de la Torre", "Martínez de la ", "Martínez de la Torre");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2122, 30, "Mecatlán", "Mecatlán", "Mecatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2123, 30, "Mecayapan", "Mecayapan", "Mecayapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2124, 30, "Medellín", "Medellín", "Medellín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2125, 30, "Miahuatlán", "Miahuatlán", "Miahuatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2126, 30, "Las Minas", "Las Minas", "Las Minas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2127, 30, "Minatitlán", "Minatitlán", "Minatitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2128, 30, "Misantla", "Misantla", "Misantla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2129, 30, "Alvarado", "Alvarado", "Alvarado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2130, 30, "Mixtla de Altamirano", "Mixtla de Altam", "Mixtla de Altamirano");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2131, 30, "Moloacán", "Moloacán", "Moloacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2132, 30, "Naolinco", "Naolinco", "Naolinco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2133, 30, "Naranjal", "Naranjal", "Naranjal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2134, 30, "Nautla", "Nautla", "Nautla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2135, 30, "Nogales", "Nogales", "Nogales");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2136, 30, "Oluta", "Oluta", "Oluta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2137, 30, "Omealca", "Omealca", "Omealca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2138, 30, "Orizaba", "Orizaba", "Orizaba");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2139, 30, "Otatitlán", "Otatitlán", "Otatitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2140, 30, "Amatitlán", "Amatitlán", "Amatitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2141, 30, "Oteapan", "Oteapan", "Oteapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2142, 30, "Ozuluama de Mascareñas", "Ozuluama de Mas", "Ozuluama de Mascareñas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2143, 30, "Pajapan", "Pajapan", "Pajapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2144, 30, "Pánuco", "Pánuco", "Pánuco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2145, 30, "Papantla", "Papantla", "Papantla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2146, 30, "Paso del Macho", "Paso del Macho", "Paso del Macho");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2147, 30, "Paso de Ovejas", "Paso de Ovejas", "Paso de Ovejas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2148, 30, "La Perla", "La Perla", "La Perla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2149, 30, "Perote", "Perote", "Perote");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2150, 30, "Platón Sánchez", "Platón Sánchez", "Platón Sánchez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2151, 30, "Naranjos Amatlán", "Naranjos Amatlá", "Naranjos Amatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2152, 30, "Playa Vicente", "Playa Vicente", "Playa Vicente");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2153, 30, "Poza Rica de Hidalgo", "Poza Rica de Hi", "Poza Rica de Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2154, 30, "Las Vigas de Ramírez", "Las Vigas de Ra", "Las Vigas de Ramírez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2155, 30, "Pueblo Viejo", "Pueblo Viejo", "Pueblo Viejo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2156, 30, "Puente Nacional", "Puente Nacional", "Puente Nacional");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2157, 30, "Rafael Delgado", "Rafael Delgado", "Rafael Delgado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2158, 30, "Rafael Lucio", "Rafael Lucio", "Rafael Lucio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2159, 30, "Los Reyes", "Los Reyes", "Los Reyes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2160, 30, "Río Blanco", "Río Blanco", "Río Blanco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2161, 30, "Saltabarranca", "Saltabarranca", "Saltabarranca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2162, 30, "Amatlán de los Reyes", "Amatlán de los ", "Amatlán de los Reyes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2163, 30, "San Andrés Tenejapan", "San Andrés Tene", "San Andrés Tenejapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2164, 30, "San Andrés Tuxtla", "San Andrés Tuxt", "San Andrés Tuxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2165, 30, "San Juan Evangelista", "San Juan Evange", "San Juan Evangelista");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2166, 30, "Santiago Tuxtla", "Santiago Tuxtla", "Santiago Tuxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2167, 30, "Sayula de Alemán", "Sayula de Alemá", "Sayula de Alemán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2168, 30, "Soconusco", "Soconusco", "Soconusco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2169, 30, "Sochiapa", "Sochiapa", "Sochiapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2170, 30, "Soledad Atzompa", "Soledad Atzompa", "Soledad Atzompa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2171, 30, "Soledad de Doblado", "Soledad de Dobl", "Soledad de Doblado");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2172, 30, "Soteapan", "Soteapan", "Soteapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2173, 30, "Angel R. Cabada", "Angel R. Cabada", "Angel R. Cabada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2174, 30, "Tamalín", "Tamalín", "Tamalín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2175, 30, "Tamiahua", "Tamiahua", "Tamiahua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2176, 30, "Tampico Alto", "Tampico Alto", "Tampico Alto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2177, 30, "Tancoco", "Tancoco", "Tancoco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2178, 30, "Tantima", "Tantima", "Tantima");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2179, 30, "Tantoyuca", "Tantoyuca", "Tantoyuca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2180, 30, "Tatatila", "Tatatila", "Tatatila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2181, 30, "Castillo de Teayo", "Castillo de Tea", "Castillo de Teayo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2182, 30, "Tecolutla", "Tecolutla", "Tecolutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2183, 30, "Tehuipango", "Tehuipango", "Tehuipango");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2184, 30, "La Antigua", "La Antigua", "La Antigua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2185, 30, "Álamo Temapache", "Álamo Temapache", "Álamo Temapache");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2186, 30, "Tempoal", "Tempoal", "Tempoal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2187, 30, "Tenampa", "Tenampa", "Tenampa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2188, 30, "Tenochtitlán", "Tenochtitlán", "Tenochtitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2189, 30, "Teocelo", "Teocelo", "Teocelo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2190, 30, "Tepatlaxco", "Tepatlaxco", "Tepatlaxco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2191, 30, "Tepetlán", "Tepetlán", "Tepetlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2192, 30, "Tepetzintla", "Tepetzintla", "Tepetzintla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2193, 30, "Tequila", "Tequila", "Tequila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2194, 30, "José Azueta", "José Azueta", "José Azueta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2195, 30, "Apazapan", "Apazapan", "Apazapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2196, 30, "Texcatepec", "Texcatepec", "Texcatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2197, 30, "Texhuacán", "Texhuacán", "Texhuacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2198, 30, "Texistepec", "Texistepec", "Texistepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2199, 30, "Tezonapa", "Tezonapa", "Tezonapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2200, 30, "Tierra Blanca", "Tierra Blanca", "Tierra Blanca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2201, 30, "Tihuatlán", "Tihuatlán", "Tihuatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2202, 30, "Tlacojalpan", "Tlacojalpan", "Tlacojalpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2203, 30, "Tlacolulan", "Tlacolulan", "Tlacolulan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2204, 30, "Tlacotalpan", "Tlacotalpan", "Tlacotalpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2205, 30, "Tlacotepec de Mejía", "Tlacotepec de M", "Tlacotepec de Mejía");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2206, 30, "Aquila", "Aquila", "Aquila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2207, 30, "Tlachichilco", "Tlachichilco", "Tlachichilco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2208, 30, "Tlalixcoyan", "Tlalixcoyan", "Tlalixcoyan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2209, 30, "Tlalnelhuayocan", "Tlalnelhuayocan", "Tlalnelhuayocan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2210, 30, "Tlapacoyan", "Tlapacoyan", "Tlapacoyan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2211, 30, "Tlaquilpa", "Tlaquilpa", "Tlaquilpa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2212, 30, "Tlilapan", "Tlilapan", "Tlilapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2213, 30, "Tomatlán", "Tomatlán", "Tomatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2214, 30, "Tonayán", "Tonayán", "Tonayán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2215, 30, "Totutla", "Totutla", "Totutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2216, 30, "Tuxpan", "Tuxpan", "Tuxpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2217, 30, "Astacinga", "Astacinga", "Astacinga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2218, 30, "Tuxtilla", "Tuxtilla", "Tuxtilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2219, 30, "Ursulo Galván", "Ursulo Galván", "Ursulo Galván");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2220, 30, "Vega de Alatorre", "Vega de Alatorr", "Vega de Alatorre");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2221, 30, "Veracruz", "Veracruz", "Veracruz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2222, 30, "Villa Aldama", "Villa Aldama", "Villa Aldama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2223, 30, "Xoxocotla", "Xoxocotla", "Xoxocotla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2224, 30, "Yanga", "Yanga", "Yanga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2225, 30, "Yecuatla", "Yecuatla", "Yecuatla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2226, 30, "Zacualpan", "Zacualpan", "Zacualpan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2227, 30, "Zaragoza", "Zaragoza", "Zaragoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2228, 30, "Acatlán", "Acatlán", "Acatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2229, 30, "Atlahuilco", "Atlahuilco", "Atlahuilco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2230, 30, "Zentla", "Zentla", "Zentla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2231, 30, "Zongolica", "Zongolica", "Zongolica");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2232, 30, "Zontecomatlán de López y Fuentes", "Zontecomatlán d", "Zontecomatlán de López y Fuentes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2233, 30, "Zozocolco de Hidalgo", "Zozocolco de Hi", "Zozocolco de Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2234, 30, "Agua Dulce", "Agua Dulce", "Agua Dulce");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2235, 30, "El Higo", "El Higo", "El Higo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2236, 30, "Nanchital de Lázaro Cárdenas del Río", "Nanchital de Lá", "Nanchital de Lázaro Cárdenas del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2237, 30, "Tres Valles", "Tres Valles", "Tres Valles");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2238, 30, "Carlos A. Carrillo", "Carlos A. Carri", "Carlos A. Carrillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2239, 30, "Tatahuicapan de Juárez", "Tatahuicapan de", "Tatahuicapan de Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2240, 30, "Atoyac", "Atoyac", "Atoyac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2241, 30, "Uxpanapa", "Uxpanapa", "Uxpanapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2242, 30, "San Rafael", "San Rafael", "San Rafael");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2243, 30, "Santiago Sochiapan", "Santiago Sochia", "Santiago Sochiapan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2244, 30, "Atzacan", "Atzacan", "Atzacan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2245, 30, "Atzalan", "Atzalan", "Atzalan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2246, 30, "Granja", "Granja", "Granja");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2247, 30, "Tlaltetela", "Tlaltetela", "Tlaltetela");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2248, 30, "Ayahualulco", "Ayahualulco", "Ayahualulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2249, 30, "Banderilla", "Banderilla", "Banderilla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2250, 30, "Benito Juárez", "Benito Juárez", "Benito Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2251, 30, "Boca del Río", "Boca del Río", "Boca del Río");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2252, 30, "Calcahualco", "Calcahualco", "Calcahualco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2253, 30, "Acayucan", "Acayucan", "Acayucan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2254, 30, "Camerino Z. Mendoza", "Camerino Z. Men", "Camerino Z. Mendoza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2255, 30, "Carrillo Puerto", "Carrillo Puerto", "Carrillo Puerto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2256, 30, "Catemaco", "Catemaco", "Catemaco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2257, 30, "Cazones de Herrera", "Cazones de Herr", "Cazones de Herrera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2258, 30, "Cerro Azul", "Cerro Azul", "Cerro Azul");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2259, 30, "Citlaltépetl", "Citlaltépetl", "Citlaltépetl");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2260, 30, "Coacoatzintla", "Coacoatzintla", "Coacoatzintla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2261, 30, "Coahuitlán", "Coahuitlán", "Coahuitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2262, 30, "Coatepec", "Coatepec", "Coatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2263, 30, "Coatzacoalcos", "Coatzacoalcos", "Coatzacoalcos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2264, 30, "Actopan", "Actopan", "Actopan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2265, 30, "Coatzintla", "Coatzintla", "Coatzintla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2266, 30, "Coetzala", "Coetzala", "Coetzala");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2267, 30, "Colipa", "Colipa", "Colipa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2268, 30, "Comapa", "Comapa", "Comapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2269, 30, "Córdoba", "Córdoba", "Córdoba");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2270, 30, "Cosamaloapan de Carpio", "Cosamaloapan de", "Cosamaloapan de Carpio");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2271, 30, "Cosautlán de Carvajal", "Cosautlán de Ca", "Cosautlán de Carvajal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2272, 30, "Coscomatepec", "Coscomatepec", "Coscomatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2273, 30, "Cosoleacaque", "Cosoleacaque", "Cosoleacaque");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2274, 30, "Cotaxtla", "Cotaxtla", "Cotaxtla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2275, 30, "Acula", "Acula", "Acula");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2276, 30, "Coxquihui", "Coxquihui", "Coxquihui");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2277, 30, "Coyutla", "Coyutla", "Coyutla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2278, 30, "Cuichapa", "Cuichapa", "Cuichapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2279, 30, "Cuitláhuac", "Cuitláhuac", "Cuitláhuac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2280, 30, "Chacaltianguis", "Chacaltianguis", "Chacaltianguis");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2281, 30, "Chalma", "Chalma", "Chalma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2282, 30, "Chiconamel", "Chiconamel", "Chiconamel");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2283, 30, "Chiconquiaco", "Chiconquiaco", "Chiconquiaco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2284, 30, "Chicontepec", "Chicontepec", "Chicontepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2285, 30, "Chinameca", "Chinameca", "Chinameca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2286, 30, "Acultzingo", "Acultzingo", "Acultzingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2287, 30, "Chinampa de Gorostiza", "Chinampa de Gor", "Chinampa de Gorostiza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2288, 30, "Las Choapas", "Las Choapas", "Las Choapas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2289, 30, "Chocamán", "Chocamán", "Chocamán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2290, 30, "Chontla", "Chontla", "Chontla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2291, 30, "Chumatlán", "Chumatlán", "Chumatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2292, 30, "Emiliano Zapata", "Emiliano Zapata", "Emiliano Zapata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2293, 30, "Espinal", "Espinal", "Espinal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2294, 30, "Filomeno Mata", "Filomeno Mata", "Filomeno Mata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2295, 30, "Fortín", "Fortín", "Fortín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2296, 30, "Gutiérrez Zamora", "Gutiérrez Zamor", "Gutiérrez Zamora");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2297, 30, "Camarón de Tejeda", "Camarón de Teje", "Camarón de Tejeda");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2298, 30, "Hidalgotitlán", "Hidalgotitlán", "Hidalgotitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2299, 30, "Huatusco", "Huatusco", "Huatusco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2300, 30, "Huayacocotla", "Huayacocotla", "Huayacocotla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2301, 30, "Hueyapan de Ocampo", "Hueyapan de Oca", "Hueyapan de Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2302, 30, "Huiloapan de Cuauhtémoc", "Huiloapan de Cu", "Huiloapan de Cuauhtémoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2303, 30, "Ignacio de la Llave", "Ignacio de la L", "Ignacio de la Llave");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2304, 30, "Ilamatlán", "Ilamatlán", "Ilamatlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2305, 30, "Isla", "Isla", "Isla");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2306, 30, "Ixcatepec", "Ixcatepec", "Ixcatepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2307, 30, "Ixhuacán de los Reyes", "Ixhuacán de los", "Ixhuacán de los Reyes");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2308, 30, "Alpatláhuac", "Alpatláhuac", "Alpatláhuac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2309, 30, "Ixhuatlán del Café", "Ixhuatlán del C", "Ixhuatlán del Café");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2310, 30, "Ixhuatlancillo", "Ixhuatlancillo", "Ixhuatlancillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2311, 30, "Ixhuatlán del Sureste", "Ixhuatlán del S", "Ixhuatlán del Sureste");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2312, 30, "Ixhuatlán de Madero", "Ixhuatlán de Ma", "Ixhuatlán de Madero");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2313, 30, "Ixmatlahuacan", "Ixmatlahuacan", "Ixmatlahuacan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2314, 30, "Ixtaczoquitlán", "Ixtaczoquitlán", "Ixtaczoquitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2315, 30, "Jalacingo", "Jalacingo", "Jalacingo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2316, 30, "Xalapa", "Xalapa", "Xalapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2317, 30, "Jalcomulco", "Jalcomulco", "Jalcomulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2318, 30, "Jáltipan", "Jáltipan", "Jáltipan");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2319, 30, "Alto Lucero de Gutiérrez Barrios", "Alto Lucero de ", "Alto Lucero de Gutiérrez Barrios");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2320, 30, "Jamapa", "Jamapa", "Jamapa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2321, 30, "Jesús Carranza", "Jesús Carranza", "Jesús Carranza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2322, 30, "Xico", "Xico", "Xico");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2323, 30, "Jilotepec", "Jilotepec", "Jilotepec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2324, 30, "Juan Rodríguez Clara", "Juan Rodríguez ", "Juan Rodríguez Clara");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2325, 30, "Juchique de Ferrer", "Juchique de Fer", "Juchique de Ferrer");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2326, 30, "Landero y Coss", "Landero y Coss", "Landero y Coss");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2327, 30, "Lerdo de Tejada", "Lerdo de Tejada", "Lerdo de Tejada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2328, 30, "Magdalena", "Magdalena", "Magdalena");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2329, 30, "Maltrata", "Maltrata", "Maltrata");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2330, 31, "Abalá", "Abalá", "Abalá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2331, 31, "Cantamayec", "Cantamayec", "Cantamayec");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2332, 31, "Ucú", "Ucú", "Ucú");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2333, 31, "Umán", "Umán", "Umán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2334, 31, "Valladolid", "Valladolid", "Valladolid");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2335, 31, "Xocchel", "Xocchel", "Xocchel");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2336, 31, "Yaxcabá", "Yaxcabá", "Yaxcabá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2337, 31, "Yaxkukul", "Yaxkukul", "Yaxkukul");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2338, 31, "Yobaín", "Yobaín", "Yobaín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2339, 31, "Celestún", "Celestún", "Celestún");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2340, 31, "Cenotillo", "Cenotillo", "Cenotillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2341, 31, "Conkal", "Conkal", "Conkal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2342, 31, "Cuncunul", "Cuncunul", "Cuncunul");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2343, 31, "Cuzamá", "Cuzamá", "Cuzamá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2344, 31, "Chacsinkín", "Chacsinkín", "Chacsinkín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2345, 31, "Chankom", "Chankom", "Chankom");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2346, 31, "Chapab", "Chapab", "Chapab");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2347, 31, "Chemax", "Chemax", "Chemax");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2348, 31, "Acanceh", "Acanceh", "Acanceh");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2349, 31, "Chicxulub Pueblo", "Chicxulub Puebl", "Chicxulub Pueblo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2350, 31, "Chichimilá", "Chichimilá", "Chichimilá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2351, 31, "Chikindzonot", "Chikindzonot", "Chikindzonot");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2352, 31, "Chocholá", "Chocholá", "Chocholá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2353, 31, "Chumayel", "Chumayel", "Chumayel");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2354, 31, "Dzán", "Dzán", "Dzán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2355, 31, "Dzemul", "Dzemul", "Dzemul");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2356, 31, "Dzidzantún", "Dzidzantún", "Dzidzantún");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2357, 31, "Dzilam de Bravo", "Dzilam de Bravo", "Dzilam de Bravo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2358, 31, "Dzilam González", "Dzilam González", "Dzilam González");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2359, 31, "Akil", "Akil", "Akil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2360, 31, "Dzitás", "Dzitás", "Dzitás");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2361, 31, "Dzoncauich", "Dzoncauich", "Dzoncauich");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2362, 31, "Espita", "Espita", "Espita");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2363, 31, "Halachó", "Halachó", "Halachó");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2364, 31, "Hocabá", "Hocabá", "Hocabá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2365, 31, "Hoctún", "Hoctún", "Hoctún");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2366, 31, "Homún", "Homún", "Homún");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2367, 31, "Huhí", "Huhí", "Huhí");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2368, 31, "Hunucmá", "Hunucmá", "Hunucmá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2369, 31, "Ixil", "Ixil", "Ixil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2370, 31, "Baca", "Baca", "Baca");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2371, 31, "Izamal", "Izamal", "Izamal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2372, 31, "Kanasín", "Kanasín", "Kanasín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2373, 31, "Kantunil", "Kantunil", "Kantunil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2374, 31, "Kaua", "Kaua", "Kaua");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2375, 31, "Kinchil", "Kinchil", "Kinchil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2376, 31, "Kopomá", "Kopomá", "Kopomá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2377, 31, "Mama", "Mama", "Mama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2378, 31, "Maní", "Maní", "Maní");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2379, 31, "Maxcanú", "Maxcanú", "Maxcanú");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2380, 31, "Mayapán", "Mayapán", "Mayapán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2381, 31, "Bokobá", "Bokobá", "Bokobá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2382, 31, "Mérida", "Mérida", "Mérida");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2383, 31, "Mocochá", "Mocochá", "Mocochá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2384, 31, "Motul", "Motul", "Motul");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2385, 31, "Muna", "Muna", "Muna");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2386, 31, "Muxupip", "Muxupip", "Muxupip");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2387, 31, "Opichén", "Opichén", "Opichén");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2388, 31, "Oxkutzcab", "Oxkutzcab", "Oxkutzcab");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2389, 31, "Panabá", "Panabá", "Panabá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2390, 31, "Peto", "Peto", "Peto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2391, 31, "Progreso", "Progreso", "Progreso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2392, 31, "Buctzotz", "Buctzotz", "Buctzotz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2393, 31, "Quintana Roo", "Quintana Roo", "Quintana Roo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2394, 31, "Río Lagartos", "Río Lagartos", "Río Lagartos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2395, 31, "Sacalum", "Sacalum", "Sacalum");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2396, 31, "Samahil", "Samahil", "Samahil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2397, 31, "Sanahcat", "Sanahcat", "Sanahcat");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2398, 31, "San Felipe", "San Felipe", "San Felipe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2399, 31, "Santa Elena", "Santa Elena", "Santa Elena");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2400, 31, "Seyé", "Seyé", "Seyé");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2401, 31, "Sinanché", "Sinanché", "Sinanché");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2402, 31, "Sotuta", "Sotuta", "Sotuta");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2403, 31, "Cacalchén", "Cacalchén", "Cacalchén");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2404, 31, "Sucilá", "Sucilá", "Sucilá");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2405, 31, "Sudzal", "Sudzal", "Sudzal");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2406, 31, "Suma", "Suma", "Suma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2407, 31, "Tahdziú", "Tahdziú", "Tahdziú");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2408, 31, "Tahmek", "Tahmek", "Tahmek");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2409, 31, "Teabo", "Teabo", "Teabo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2410, 31, "Tecoh", "Tecoh", "Tecoh");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2411, 31, "Tekal de Venegas", "Tekal de Venega", "Tekal de Venegas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2412, 31, "Tekantó", "Tekantó", "Tekantó");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2413, 31, "Tekax", "Tekax", "Tekax");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2414, 31, "Calotmul", "Calotmul", "Calotmul");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2415, 31, "Tekit", "Tekit", "Tekit");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2416, 31, "Tekom", "Tekom", "Tekom");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2417, 31, "Telchac Pueblo", "Telchac Pueblo", "Telchac Pueblo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2418, 31, "Telchac Puerto", "Telchac Puerto", "Telchac Puerto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2419, 31, "Temax", "Temax", "Temax");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2420, 31, "Temozón", "Temozón", "Temozón");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2421, 31, "Tepakán", "Tepakán", "Tepakán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2422, 31, "Tetiz", "Tetiz", "Tetiz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2423, 31, "Teya", "Teya", "Teya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2424, 31, "Ticul", "Ticul", "Ticul");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2425, 31, "Cansahcab", "Cansahcab", "Cansahcab");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2426, 31, "Timucuy", "Timucuy", "Timucuy");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2427, 31, "Tinum", "Tinum", "Tinum");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2428, 31, "Tixcacalcupul", "Tixcacalcupul", "Tixcacalcupul");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2429, 31, "Tixkokob", "Tixkokob", "Tixkokob");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2430, 31, "Tixmehuac", "Tixmehuac", "Tixmehuac");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2431, 31, "Tixpéhual", "Tixpéhual", "Tixpéhual");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2432, 31, "Tizimín", "Tizimín", "Tizimín");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2433, 31, "Tunkás", "Tunkás", "Tunkás");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2434, 31, "Tzucacab", "Tzucacab", "Tzucacab");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2435, 31, "Uayma", "Uayma", "Uayma");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2436, 32, "Apozol", "Apozol", "Apozol");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2437, 32, "Fresnillo", "Fresnillo", "Fresnillo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2438, 32, "Trinidad García de la Cadena", "Trinidad García", "Trinidad García de la Cadena");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2439, 32, "Genaro Codina", "Genaro Codina", "Genaro Codina");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2440, 32, "General Enrique Estrada", "General Enrique", "General Enrique Estrada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2441, 32, "General Francisco R. Murguía", "General Francis", "General Francisco R. Murguía");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2442, 32, "El Plateado de Joaquín Amaro", "El Plateado de ", "El Plateado de Joaquín Amaro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2443, 32, "General Pánfilo Natera", "General Pánfilo", "General Pánfilo Natera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2444, 32, "Guadalupe", "Guadalupe", "Guadalupe");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2445, 32, "Huanusco", "Huanusco", "Huanusco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2446, 32, "Jalpa", "Jalpa", "Jalpa");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2447, 32, "Apulco", "Apulco", "Apulco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2448, 32, "Jerez", "Jerez", "Jerez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2449, 32, "Jiménez del Teul", "Jiménez del Teu", "Jiménez del Teul");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2450, 32, "Juan Aldama", "Juan Aldama", "Juan Aldama");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2451, 32, "Juchipila", "Juchipila", "Juchipila");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2452, 32, "Loreto", "Loreto", "Loreto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2453, 32, "Luis Moya", "Luis Moya", "Luis Moya");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2454, 32, "Mazapil", "Mazapil", "Mazapil");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2455, 32, "Melchor Ocampo", "Melchor Ocampo", "Melchor Ocampo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2456, 32, "Mezquital del Oro", "Mezquital del O", "Mezquital del Oro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2457, 32, "Miguel Auza", "Miguel Auza", "Miguel Auza");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2458, 32, "Atolinga", "Atolinga", "Atolinga");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2459, 32, "Momax", "Momax", "Momax");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2460, 32, "Monte Escobedo", "Monte Escobedo", "Monte Escobedo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2461, 32, "Morelos", "Morelos", "Morelos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2462, 32, "Moyahua de Estrada", "Moyahua de Estr", "Moyahua de Estrada");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2463, 32, "Nochistlán de Mejía", "Nochistlán de M", "Nochistlán de Mejía");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2464, 32, "Noria de Ángeles", "Noria de Ángele", "Noria de Ángeles");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2465, 32, "Ojocaliente", "Ojocaliente", "Ojocaliente");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2466, 32, "Pánuco", "Pánuco", "Pánuco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2467, 32, "Pinos", "Pinos", "Pinos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2468, 32, "Río Grande", "Río Grande", "Río Grande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2469, 32, "Benito Juárez", "Benito Juárez", "Benito Juárez");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2470, 32, "Sain Alto", "Sain Alto", "Sain Alto");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2471, 32, "El Salvador", "El Salvador", "El Salvador");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2472, 32, "Sombrerete", "Sombrerete", "Sombrerete");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2473, 32, "Susticacán", "Susticacán", "Susticacán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2474, 32, "Tabasco", "Tabasco", "Tabasco");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2475, 32, "Tepechitlán", "Tepechitlán", "Tepechitlán");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2476, 32, "Tepetongo", "Tepetongo", "Tepetongo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2477, 32, "Teúl de González Ortega", "Teúl de Gonzále", "Teúl de González Ortega");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2478, 32, "Tlaltenango de Sánchez Román", "Tlaltenango de ", "Tlaltenango de Sánchez Román");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2479, 32, "Valparaíso", "Valparaíso", "Valparaíso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2480, 32, "Calera", "Calera", "Calera");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2481, 32, "Vetagrande", "Vetagrande", "Vetagrande");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2482, 32, "Villa de Cos", "Villa de Cos", "Villa de Cos");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2483, 32, "Villa García", "Villa García", "Villa García");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2484, 32, "Villa González Ortega", "Villa González ", "Villa González Ortega");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2485, 32, "Villa Hidalgo", "Villa Hidalgo", "Villa Hidalgo");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2486, 32, "Villanueva", "Villanueva", "Villanueva");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2487, 32, "Zacatecas", "Zacatecas", "Zacatecas");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2488, 32, "Trancoso", "Trancoso", "Trancoso");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2489, 32, "Santa María de la Paz", "Santa María de ", "Santa María de la Paz");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2490, 32, "Cañitas de Felipe Pescador", "Cañitas de Feli", "Cañitas de Felipe Pescador");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2491, 32, "Concepción del Oro", "Concepción del ", "Concepción del Oro");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2492, 32, "Cuauhtémoc", "Cuauhtémoc", "Cuauhtémoc");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2493, 32, "Chalchihuites", "Chalchihuites", "Chalchihuites");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2494, 32, "Colonia", "Colonia", "Colonia");
	
	INSERT INTO cat_municipality (id_municipality, id_state, name, abbreviation, description) VALUES (2495, 32, "3", "3", "3");