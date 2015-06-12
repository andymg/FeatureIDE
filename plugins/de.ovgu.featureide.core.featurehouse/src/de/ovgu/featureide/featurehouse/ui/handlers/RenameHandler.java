package de.ovgu.featureide.featurehouse.ui.handlers;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.javaeditor.JavaEditor;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.ResourceUtil;
import org.eclipse.ui.texteditor.ITextEditor;

import de.ovgu.featureide.core.CorePlugin;
import de.ovgu.featureide.core.IFeatureProject;
import de.ovgu.featureide.featurehouse.refactoring.RenameMethodRefactoring;
import de.ovgu.featureide.featurehouse.refactoring.RenameRefactoringWizard;
import de.ovgu.featureide.fm.ui.handlers.base.ASelectionHandler;
import de.ovgu.featureide.fm.ui.handlers.base.SelectionWrapper;


public class RenameHandler extends ASelectionHandler {

	@Override
	protected void singleAction(Object element) 
	{
//		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//		ITextEditor editor = (ITextEditor) page.getActiveEditor();
//		IJavaElement elem = JavaUI.getEditorInputJavaElement(editor.getEditorInput());
//		if (elem instanceof ICompilationUnit) {
//		    ITextSelection sel = (ITextSelection) editor.getSelectionProvider().getSelection();
//		    IJavaElement selected;
//			try {
//				selected = ((ICompilationUnit) elem).getElementAt(sel.getOffset());
//				if (selected != null && selected.getElementType() == IJavaElement.METHOD) {
//			    	element = (IMethod) selected;
//			    }
//			} catch (JavaModelException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		if (!(element instanceof IMethod)) return;
		IMethod method = (IMethod) element;
		
		IFeatureProject featureProject = getFeatureProject();
		if (featureProject == null) return;

		RenameMethodRefactoring refactoring = new RenameMethodRefactoring(method, featureProject);

		RenameRefactoringWizard refactoringWizard = new RenameRefactoringWizard(refactoring);
		RefactoringWizardOpenOperation op = new RefactoringWizardOpenOperation(refactoringWizard);
		try {
			op.run(getShell(), "Rename-Refactoring");
		}
		catch (InterruptedException e) {
		}
	}

	private IFeatureProject getFeatureProject()
	{
		
//		final IResource res = (IResource) SelectionWrapper.checkClass(element, IResource.class);
//		final IFeatureProject featureProject = CorePlugin.getFeatureProject(res);
		
		
		IEditorInput fileEditorInput = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput();
		final IFile file = ResourceUtil.getFile(fileEditorInput);
		if (file == null) return null;
		
		return CorePlugin.getFeatureProject(file);
	}
	
	protected Shell getShell() 
	{
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window == null) return null;
		return window.getShell();
	}   

//	@Override
//	public Object execute(ExecutionEvent event) throws ExecutionException {
//		final ISelection selection = HandlerUtil.getCurrentSelection(event);
//		if (selection instanceof ITextSelection) {
//		    ITextSelection selection1 = (ITextSelection) selection;
//		    System.out.println(selection1.getOffset());
//		    selection1.
//		}
//		return null;
//	}
	

}
