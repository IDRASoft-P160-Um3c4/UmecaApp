package com.umeca.importer;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.umeca.model.catalog.Vialidad;
import com.umeca.repository.catalog.VialidadRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/jpaContext.xml" })
public class InsertarCatalogos {

	@Autowired
    VialidadRepository repositoryVialidad;

    @PersistenceContext
    private EntityManager em;
    
    @Before
    public void init() {
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(em);

        repositoryVialidad = jpaRepositoryFactory.getRepository(VialidadRepository.class);
        assertNotNull(repositoryVialidad);

    }
    
	@Test
	@Transactional
	public void guardarVialidades(){		
		
		try {
			FileInputStream fstream = new FileInputStream("C:\\dev\\projects\\enrique\\catalogs\\TipoVialidad.txt");
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		        BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    //Read File Line By Line
		    int iCount = 0;
		    
		    
		    while ((strLine = br.readLine()) != null)   {

		    	iCount ++;
		    	System.out.println (strLine);
		      
		    	if(iCount <= 1)
		    		continue;
		    	  
		    	String[] data = strLine.split("\\|");
		    	
		    	if(data.length != 2)
		    		continue;
		      
		    	Vialidad vialidad = new Vialidad();

		    	vialidad.setId(Long.parseLong(data[0]));
		    	vialidad.setNombre(data[1]);
		    	
		    	repositoryVialidad.saveAndFlush(vialidad);
		    	
		    	iCount++;
		    }
		    
		    //Close the input stream
		    in.close();			
		} catch (Exception e) {
			System.out.println (e.getMessage());
		}
		
	}
}
