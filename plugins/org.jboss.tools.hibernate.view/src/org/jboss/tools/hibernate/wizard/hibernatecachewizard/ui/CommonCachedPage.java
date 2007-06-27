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
package org.jboss.tools.hibernate.wizard.hibernatecachewizard.ui;
import java.util.HashSet;
import org.eclipse.jface.action.Action;

public class CommonCachedPage extends WizardPage 
{
	public CommonCachedPage(String pagetitle, final String descrinit, final String descrcomp)
	{
	public void createControl(Composite parent) 
	protected void setLayout(Composite comp)
	protected void createPageElements(Composite parent)
	protected void createInitialTreeDescriptor(Composite parent)
	protected GridData calculateTreeViewerSize(int dimension)
	protected void createInitilaTreeViewer(Composite parent)
	protected void createComposedTreeDescriptor(Composite parent){}
	protected void createComposedTreeViewer(Composite parent)
	protected void processAddToCacheButtonPressed(){}
	protected void processAddAllToCacheButtonPressed(){}
	protected void processRemoveFromCachePressed(){}
	protected void processRemoveAllFromCachePressed(){}
	protected void createPopupMenu()
		item1.setSelection(true);
	/**
	protected void itemAddRemoveCache(String name, String param, boolean strategy){}
	/**
	/**
	private void clearContexMenuSelection()
	protected class HibernateCacheWizardSelectionListener implements ISelectionChangedListener
	protected class HibernateCacheWizardPopUpMenuReadWrite extends Action
	protected class HibernateCacheWizardPopUpMenuTransactional extends Action
	protected class HibernateCacheWizardPopUpMenuNonstrictReadWrite extends Action
	protected class HibernateCacheWizardPopUpMenuReadOnly extends Action

	protected class HibernateCacheWizardMouseListener implements MouseListener
		private void processButtonPressed(Button btnevent)
	protected class ListDataModelContentProvider implements IStructuredContentProvider
		public Object[] getElements(Object inputElement) 
	protected class ListSorter extends ViewerSorter
	protected class ListDataModelLabelProvider extends LabelProvider
	protected class TreeSorter extends ViewerSorter