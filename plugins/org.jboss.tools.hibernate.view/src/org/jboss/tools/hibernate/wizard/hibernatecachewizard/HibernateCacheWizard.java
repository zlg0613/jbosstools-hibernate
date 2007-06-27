/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.hibernate.wizard.hibernatecachewizard;
public class HibernateCacheWizard extends ReadOnlyWizard implements INewWizard {
	/**
	private void createCachedClassesList(IPersistentClassMapping item)
	/**
	private void createCachedCollectionsList(IPersistentClassMapping item)
	private boolean isPersistentCollectionCacheable(IPersistentValueMapping val)
	public boolean performFinish()
	private void createCachedRegionsList() {
	private void addCacheableRegion(int sign)
	/**
	/**
	public void setRegionName(final String itemname, final String regionname)
	public void addPages() 
		page2 = new CachedCollectionsPage(ICacheable.bundle.getString("HibernateCacheWizard.CACHING_COLLECTION"),cacheableCollections.keySet(), nativeCachedCollections.keySet());