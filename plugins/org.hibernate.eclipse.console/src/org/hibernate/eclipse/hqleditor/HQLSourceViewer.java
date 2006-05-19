package org.hibernate.eclipse.hqleditor;

import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.hibernate.eclipse.util.xpl.PlatformStatusLineUtil;

/**
 * Source viewer for editing HQL source text. 
 * 
 */
public class HQLSourceViewer extends ProjectionViewer {

    /**
     * Creates an instance of this class with the given parameters.
     * 
     * @param parent the SWT parent control
     * @param ruler the vertical ruler (annotation area)
     * @param overviewRuler the overview ruler
     * @param showsAnnotationOverview <code>true</code> if the overview ruler should be shown
     * @param styles the SWT style bits
     */
    public HQLSourceViewer( Composite parent, IVerticalRuler ruler,
            IOverviewRuler overviewRuler, boolean showsAnnotationOverview,
            int styles ) {
        super( parent, ruler, overviewRuler, showsAnnotationOverview, styles );
    }

    public void doOperation(int operation) {

    	if (getTextWidget() == null || (!redraws() && operation != FORMAT))
			return;

		switch (operation) {
		case CONTENTASSIST_PROPOSALS:
			String err = fContentAssistant.showPossibleCompletions();
			if (err != null) {
				// don't wanna beep if there is no error
				PlatformStatusLineUtil.displayErrorMessage(err);
			}
			PlatformStatusLineUtil.addOneTimeClearListener();
			return;
		default:
			super.doOperation( operation );
		}
    }
}
