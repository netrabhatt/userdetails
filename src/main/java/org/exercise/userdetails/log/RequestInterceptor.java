package org.exercise.userdetails.log;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);
	private final AtomicLong requestSequence = new AtomicLong(1L);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		
		long requestId = requestSequence.getAndIncrement();
		request.setAttribute("request-id", requestId);
		
		String uri = request.getRequestURI();
		log.info( "Request#" + requestId + " : Requestor = " + request.getRemoteAddr() + ", uri = " + uri );
		
		if(uri.startsWith("/api/userdetails/") && !uri.matches("/api/userdetails/\\d+")) {
			String message = "User Details id should be a numeric value.";
			log.info("Request#" + requestId + " : Bad Request (" + message + ")" );
			throw new IllegalArgumentException(message);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long endTime = System.currentTimeMillis();
        long startTime=Long.parseLong(request.getAttribute("startTime")+"");
        
        log.info("Time taken to process request#" + request.getAttribute("request-id") + " = " + (endTime-startTime)+" ms");
	}
}