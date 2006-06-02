package org.hibernate.eclipse.jdt.apt.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.apt.core.env.EclipseAnnotationProcessorEnvironment;
import org.hibernate.console.ConsoleConfiguration;
import org.hibernate.eclipse.nature.HibernateNature;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;

public class HQLAnnotationProcessorFactory implements AnnotationProcessorFactory 
{

	public HQLAnnotationProcessorFactory() {
		
	}
	
	public Collection supportedOptions() {
		return Collections.EMPTY_LIST;
	}

	public Collection supportedAnnotationTypes() {
		return annotations;
	}

	public AnnotationProcessor getProcessorFor(
			Set atds,
			AnnotationProcessorEnvironment env) 
	{		
		if(env instanceof EclipseAnnotationProcessorEnvironment) {
			EclipseAnnotationProcessorEnvironment eenv = (EclipseAnnotationProcessorEnvironment) env;
			if(canProcess( eenv )) {
				return new HQLAnnotationProcessor(eenv);
			} else {
				return null;
			}
		} else {
			return null;
		}		
	}

	private boolean canProcess(EclipseAnnotationProcessorEnvironment eenv) {
		HibernateNature hibernateNature = HibernateNature.getHibernateNature( eenv.getJavaProject() );
		if(hibernateNature==null) {
			eenv.getMessager().printWarning( "Cannot process HQL queries. Hibernate not enabled for project [" + eenv.getJavaProject().getProject().getName() + "]" );
			return false;
		}
		
		ConsoleConfiguration cc = HQLAnnotationProcessor.getConsoleConfiguration( hibernateNature );
		if(cc==null) {
			eenv.getMessager().printWarning( "Cannot process HQL queries. No console configuration found for project [" + eenv.getJavaProject().getProject().getName() + "]" );
			return false;
		}
		
		if(!cc.isSessionFactoryCreated()) {
			eenv.getMessager().printWarning( "Cannot process HQL queries. No session factory created for console configuration [" + cc.getName() + "]" );
			return false;
		}
		
		return true;
	}
	
	private static List annotations = new ArrayList();
	
	static {
		annotations.add( "javax.persistence.NamedQuery" );
		annotations.add( "javax.persistence.NamedQueries" );
		annotations.add( "org.hibernate.annotations.NamedQuery" );
		annotations.add( "org.hibernate.annotations.NamedQueries" );
	}
}
