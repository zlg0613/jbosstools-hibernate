package org.hibernate.eclipse.jdt.ui.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.hibernate.cfg.Configuration;
import org.hibernate.console.ConsoleConfiguration;
import org.hibernate.eclipse.console.HibernateConsolePlugin;
import org.hibernate.eclipse.hqleditor.EclipseHQLCompletionRequestor;
import org.hibernate.eclipse.hqleditor.HQLCompletionProcessor;
import org.hibernate.eclipse.nature.HibernateNature;
import org.hibernate.tool.ide.completion.HQLCodeAssist;
import org.hibernate.tool.ide.completion.IHQLCodeAssist;

public class HQLJavaCompletionProposalComputer implements IJavaCompletionProposalComputer {

	HQLCompletionProcessor hqlProcessor;
	private String errorMessage;
	
	public HQLJavaCompletionProposalComputer() {
		super();
		hqlProcessor = new HQLCompletionProcessor(null);
	}

	ConsoleConfiguration getConfiguration(IJavaProject javaProject) {
		if(javaProject != null) {
			HibernateNature nature = HibernateNature.getHibernateNature( javaProject );
			if(nature!=null) {
				return nature.getDefaultConsoleConfiguration();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	public List computeCompletionProposals(ContentAssistInvocationContext context, IProgressMonitor monitor) {
		List proposals = new ArrayList();
		errorMessage = null;
		
		JavaContentAssistInvocationContext ctx = (JavaContentAssistInvocationContext)context;
		
		try {
				 ConsoleConfiguration consoleConfiguration = getConfiguration( ctx.getProject() );
				 if(consoleConfiguration!=null) {					 
					 Configuration configuration = consoleConfiguration!=null?consoleConfiguration.getConfiguration():null;

					 IHQLCodeAssist hqlEval = new HQLCodeAssist(configuration);
					 
					 String query = "";
					 int stringStart = getStringStart( ctx.getDocument(), ctx.getInvocationOffset() );
					 int stringEnd = getStringEnd( ctx.getDocument(), ctx.getInvocationOffset() );
					 query = ctx.getDocument().get(stringStart, stringEnd-stringStart );
					 EclipseHQLCompletionRequestor eclipseHQLCompletionCollector = new EclipseHQLCompletionRequestor(stringStart);
					 hqlEval.codeComplete(query, ctx.getInvocationOffset()-stringStart, eclipseHQLCompletionCollector);
					 errorMessage = eclipseHQLCompletionCollector.getLastErrorMessage();

					 proposals = eclipseHQLCompletionCollector.getCompletionProposals();
				 }
		} catch(RuntimeException re) {
			HibernateConsolePlugin.getDefault().logErrorMessage( "Error while performing HQL completion in java", re );
		}
		catch (BadLocationException e) {
			HibernateConsolePlugin.getDefault().logErrorMessage( "Error while performing HQL completion in java", e );
		}
		
		return proposals;
	}

	public int getStringStart(IDocument document, int location) throws BadLocationException {

		if (document == null) {
			return -1;
		}

		int end = location;
		int start = end;
		while (--start >= 0) {
			if ('"'==document.getChar(start)) {
				break;
			}
		}
		start++;
	
		return start;
	}

	public int getStringEnd(IDocument document, int location) throws BadLocationException {

		if (document == null) {
			return -1;
		}

		int end = document.getLength();
		int start = location;
		while (start < end) {
			char c = document.getChar(start);
			if ('"'==c) {
				break;
			}
			start++;
		}
		return start;
	}

	public List computeContextInformation(ContentAssistInvocationContext context, IProgressMonitor monitor) {
		return Collections.EMPTY_LIST;
	}

	public String getErrorMessage() {	
		return errorMessage;
	}

	public void sessionEnded() {
		
	}

	public void sessionStarted() {
		
	}

}