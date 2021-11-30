package com.portal.business.commons.generic;

import com.portal.business.commons.utils.MessageFormatHelper;
import com.portal.business.commons.utils.WsConstants;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;


public class RemittenceContextInterceptor
{
	private MessageFormatHelper sysMessage = new MessageFormatHelper(WsConstants.MESSAGE_FILE_NAME);
	
	@AroundInvoke
	public Object configData(InvocationContext ctx) throws Exception
	{
		return ctx.proceed();
	}
	
	
	
	
	

}
