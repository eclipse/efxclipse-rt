package org.eclipse.fx.code.editor.fx.services.internal;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.fx.code.editor.Input;
import org.eclipse.fx.code.editor.fx.services.CompletionProposalPresenter;
import org.eclipse.fx.code.editor.services.CompletionProposal;
import org.eclipse.fx.code.editor.services.HoverInformationProvider;
import org.eclipse.fx.code.editor.services.ProposalComputer;
import org.eclipse.fx.code.editor.services.ProposalComputer.ProposalContext;
import org.eclipse.fx.text.ui.ITextHover;
import org.eclipse.fx.text.ui.ITextViewer;
import org.eclipse.fx.text.ui.contentassist.ContentAssistant;
import org.eclipse.fx.text.ui.contentassist.ICompletionProposal;
import org.eclipse.fx.text.ui.contentassist.IContentAssistant;
import org.eclipse.fx.text.ui.presentation.IPresentationReconciler;
import org.eclipse.fx.text.ui.presentation.PresentationReconciler;
import org.eclipse.fx.text.ui.source.AnnotationPresenter;
import org.eclipse.fx.text.ui.source.ISourceViewer;
import org.eclipse.fx.text.ui.source.SourceViewerConfiguration;
import org.eclipse.fx.ui.controls.styledtext.TextSelection;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.IAnnotationModel;

import javafx.scene.Node;

@SuppressWarnings("restriction")
public class DefaultSourceViewerConfiguration extends SourceViewerConfiguration {
	private final Input<?> input;
	private final PresentationReconciler reconciler;
	private final ProposalComputer proposalComputer;
	private final IAnnotationModel annotationModel;
	private final List<AnnotationPresenter> annotationPresenters;
	private final HoverInformationProvider hoverInformationProvider;
	private final CompletionProposalPresenter proposalPresenter;

	@Inject
	public DefaultSourceViewerConfiguration(
			Input<?> input,
			PresentationReconciler reconciler,
			@Optional ProposalComputer proposalComputer,
			@Optional IAnnotationModel annotationModel,
			@Optional AnnotationPresenter presenter,
			@Optional HoverInformationProvider hoverInformationProvider,
			@Optional CompletionProposalPresenter proposalPresenter
			) {
		this.input = input;
		this.hoverInformationProvider = hoverInformationProvider;
		this.reconciler = reconciler;
		this.proposalComputer = proposalComputer;
		this.annotationModel = annotationModel;
		this.proposalPresenter = proposalPresenter == null ? DefaultProposal::new : proposalPresenter;
		if( presenter != null ) {
			this.annotationPresenters = Collections.singletonList(presenter);
		} else {
			this.annotationPresenters = Collections.emptyList();
		}
	}

	@Override
	public String getStyleclassName() {
		return "source-viewer";
	}

	public final IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		return reconciler;
	}

	@Override
	public IContentAssistant getContentAssist() {
		if( proposalComputer != null ) {
			return new ContentAssistant(this::computeProposals);
		}
		return super.getContentAssist();
	}

	List<ICompletionProposal> computeProposals(Integer offset) {
		try {
			return proposalComputer.compute(new ProposalContext(input, offset)).get()
						.stream()
						.map(proposalPresenter::createProposal)
						.collect(Collectors.toList());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public IAnnotationModel getAnnotationModel() {
		return annotationModel;
	}

	@Override
	public List<AnnotationPresenter> getAnnotationPresenters() {
		return annotationPresenters;
	}

	@Override
	public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
		if( hoverInformationProvider != null ) {
			return new ITextHover() {

				@Override
				public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
					return hoverInformationProvider.getHoverRegion(contentType, offset);
				}

				@Override
				public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
					CharSequence information = hoverInformationProvider.getHoverInformation(contentType, hoverRegion);
					return information == null ? null : information.toString();
				}
			};
		}
		return super.getTextHover(sourceViewer, contentType);
	}

	static class DefaultProposal implements ICompletionProposal {
		private final CompletionProposal proposal;

		public DefaultProposal(CompletionProposal proposal) {
			this.proposal = proposal;
		}

		@Override
		public CharSequence getLabel() {
			return this.proposal.getLabel();
		}

		@Override
		public Node getGraphic() {
			return null;
		}

		@Override
		public void apply(IDocument document) {
			this.proposal.apply(document);
		}

		@Override
		public TextSelection getSelection(IDocument document) {
			org.eclipse.fx.code.editor.services.CompletionProposal.TextSelection selection = proposal.getSelection(document);
			return selection == null ? TextSelection.EMPTY : new TextSelection(selection.offset, selection.length);
		}
	}
}
