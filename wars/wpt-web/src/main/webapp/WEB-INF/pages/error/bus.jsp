bus Exception ${exception.message}  ${exception.exceptionMessage.code}

<%
	Object exception = request.getAttribute("exception");

	if(exception instanceof com.gsoft.framework.core.exception.BusException){
		com.gsoft.framework.core.exception.BusException  busException = (com.gsoft.framework.core.exception.BusException)exception;
	
		System.out.println(busException.getExceptionMessage().getCode());
		
		request.getHeader("");
		
	}
%>
