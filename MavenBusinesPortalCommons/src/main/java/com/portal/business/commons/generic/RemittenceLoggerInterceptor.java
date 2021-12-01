package com.portal.business.commons.generic;

import com.portal.business.commons.utils.MessageFormatHelper;
import com.portal.business.commons.utils.WsConstants;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;



public class RemittenceLoggerInterceptor {

    private MessageFormatHelper sysMessage = new MessageFormatHelper(WsConstants.MESSAGE_FILE_NAME);

    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
        /*Object[] oParams = ctx.getParameters();
        System.out.println("**********Thread id: " + Thread.currentThread().getId());
        System.out.println("**********Thread name: " + Thread.currentThread().getName());
        try {
            Logger logger = Logger.getLogger(ctx.getTarget().getClass());
            String clazz = ctx.getMethod().getDeclaringClass().getName();
            String methodName = ctx.getMethod().getName();
            String params = "";
            params += java.util.Arrays.toString(oParams);
            logger.info(sysMessage.format(EjbConstants.MSG_INIT_INVOCATION_METHOD, methodName, clazz, params));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return ctx.proceed();
    }
}
