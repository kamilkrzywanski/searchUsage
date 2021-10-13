package my.packagee;

import java.util.HashSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.internal.corext.callhierarchy.CallHierarchy;
import org.eclipse.jdt.internal.corext.callhierarchy.MethodWrapper;

public class CallHierarchyGenerator {
    public HashSet<IMethod> getCallersOf(IMethod m) {

        CallHierarchy callHierarchy = CallHierarchy.getDefault();


        MethodWrapper methodWrappers = callHierarchy.getCallerRoot(m);

        HashSet<IMethod> callers = new HashSet<IMethod>();

        MethodWrapper[] mw2 = methodWrappers.getCalls(new NullProgressMonitor());
        HashSet<IMethod> temp = getIMethods(mw2);
        callers.addAll(temp);
        return callers;
    }

    HashSet<IMethod> getIMethods(MethodWrapper[] methodWrappers) {
        HashSet<IMethod> c = new HashSet<IMethod>();
        for (MethodWrapper m : methodWrappers) {
            IMethod im = getIMethodFromMethodWrapper(m);
            if (im != null) {
                c.add(im);
            }
        }
        return c;
    }

    IMethod getIMethodFromMethodWrapper(MethodWrapper m) {
        try {
            IMember im = m.getMember();
            if (im.getElementType() == IJavaElement.METHOD) {
                return (IMethod) m.getMember();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    IMethod findMethod(IType type, String methodName) throws JavaModelException
    {
        //IType type = project.findType(typeName);

        IMethod[] methods = type.getMethods();
        IMethod theMethod = null;

        for (int i = 0; i < methods.length; i++)
        {
            IMethod imethod = methods[i];
            if (imethod.getElementName().equals(methodName)) {
                theMethod = imethod;
            }
        }

        if (theMethod == null)
        {
            System.out.println("Error, method" + methodName + " not found");
            return null;
        }

        Main.getAll("addasdsa");

        return theMethod;
    }

    public static void getAll(){

        Main.getAll("addasdsa");
        Main.getAll("aasdddasdsa");
    }
}