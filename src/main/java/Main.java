import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.internal.core.JavaProject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args){

        init();

    }

    public static void init(){

        CallHierarchyGenerator callGen = new CallHierarchyGenerator();

        IMethod m = callGen.findMethod(type, "world");
        Set<IMethod> methods = new HashSet<IMethod>();
        methods = callGen.getCallersOf(m);
        for (Iterator<IMethod> i = methods.iterator(); i.hasNext();)
        {
            System.out.println(i.next().toString());
        }

    }

    public void getAll(IJavaProject project) {
        CallHierarchyGenerator callGen = new CallHierarchyGenerator();

        try {
            IJavaProject project1 = this.getJavaProject("elo");
        } catch (CoreException e) {
            e.printStackTrace();
        }

        try {
            IType type = project.findType("elo");
            IMethod m = callGen.findMethod(type, "world");
            Set<IMethod> methods = new HashSet<IMethod>();
            methods = callGen.getCallersOf(m);

            for (
                    Iterator<IMethod> i = methods.iterator(); i.hasNext(); ) {
                System.out.println(i.next().toString());
            }
        } catch (JavaModelException e) {
            e.printStackTrace();
        }




    }


    private IJavaProject getJavaProject(String projectName) throws CoreException
    {
        IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
        IProject project= root.getProject(projectName);
        if (!project.exists()) {
            project.create(null);
        } else {
            project.refreshLocal(IResource.DEPTH_INFINITE, null);
        }

        if (!project.isOpen()) {
            project.open(null);
        }

        IJavaProject jproject= JavaCore.create(project);

        return jproject;
    }


}
