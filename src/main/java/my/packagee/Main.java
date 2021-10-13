package my.packagee;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.*;
import org.reflections8.Reflections;
import org.reflections8.scanners.*;
import org.reflections8.util.ClasspathHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.*;
import java.util.stream.Collectors;



public class Main {

    public static void main(String[] args){

      //  init();
        Reflections reflections = new Reflections(
                ClasspathHelper.forPackage("my.package"), new SubTypesScanner(),
                new TypeAnnotationsScanner(),
                new FieldAnnotationsScanner(),
                new MemberUsageScanner());


        try {
            Set<Member> usages = reflections.getMethodUsage(Main.class.getMethod("getAll", String.class));
            //System.out.println(usages.stream().map( m -> m.()));
//            System.out.println(usages.stream()
//                    .filter(Objects::nonNull)
//                    .map( java.lang.reflect.Field::set)
//                    .filter(Objects::nonNull)
//                    .collect(Collectors.toList()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

//    public static void init(){
//
//        CallHierarchyGenerator callGen = new CallHierarchyGenerator();
//
//        IMethod m = callGen.findMethod(type, "world");
//        Set<IMethod> methods = new HashSet<IMethod>();
//        methods = callGen.getCallersOf(m);
//        for (Iterator<IMethod> i = methods.iterator(); i.hasNext();)
//        {
//            System.out.println(i.next().toString());
//        }
//
//    }


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

        // @code
        getAll("adsds");


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

    public static void getAll(String x){

    }

}
