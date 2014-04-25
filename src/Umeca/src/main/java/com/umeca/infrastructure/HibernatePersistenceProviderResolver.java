package com.umeca.infrastructure;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolver;
import javax.persistence.spi.PersistenceProviderResolverHolder;

import org.hibernate.ejb.HibernatePersistence;

public class HibernatePersistenceProviderResolver implements PersistenceProviderResolver {
	private static final Logger LOGGER = Logger.getLogger(HibernatePersistenceProviderResolver.class.getName());
	
	private volatile PersistenceProvider persistenceProvider = new HibernatePersistence();
	
	public List<PersistenceProvider> getPersistenceProviders() {
		return Collections.singletonList(persistenceProvider);
	}

	public void clearCachedProviders() {
		persistenceProvider = new HibernatePersistence();
	}
	
	public static void register() {
		LOGGER.info("Registering HibernatePersistenceProviderResolver");
		PersistenceProviderResolverHolder.setPersistenceProviderResolver(new HibernatePersistenceProviderResolver());
	}
}