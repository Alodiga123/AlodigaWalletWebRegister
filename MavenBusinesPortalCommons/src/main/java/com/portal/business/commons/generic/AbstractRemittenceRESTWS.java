package com.portal.business.commons.generic;

import org.springframework.web.client.RestTemplate;



public class AbstractRemittenceRESTWS extends AbstractBusinessPortalWs {

	private RestTemplate restTemplate;

	public AbstractRemittenceRESTWS() {
		restTemplate = new RestTemplate();
	}



}
