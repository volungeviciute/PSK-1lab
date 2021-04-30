package lt.vu.mif.PSK_1lab.Interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@InterceptorMethod
@Interceptor
public class MethodInterceptor implements Serializable {
    @AroundInvoke
    public Object LogMethodName(InvocationContext invocationContext) throws Exception{
        System.out.println("INTERCEPTOR OUTPUT: calling " +
                            invocationContext.getMethod().getDeclaringClass().getName() + "." +
                            invocationContext.getMethod().getName());

        return invocationContext.proceed();
    }
}
