package com.spring.cloud;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${custom.oauth2.server.registration.id:authServer2}")
	private String registrationId;
	
	@Value("${server.port:6666}")
	private String port;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired JwtTokenStore tokenStore;
	 
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		System.out.println(port + registrationId);
		
		clients.inMemory().withClient("1").secret("{noop}secret")
		.scopes("read", "write", "upload")
		.authorizedGrantTypes("password", "client_credentials", "authorization_code")
		.redirectUris("http://localhost:7878/client/login/oauth2/code/customAuthServer")
		.autoApprove(false)
		
		.and()
		
		.withClient("2").secret("{noop}secret2")
		.scopes("test")
		.authorizedGrantTypes("password", "authorization_code")
		.redirectUris("http://localhost:7878/client/login/oauth2/code/customAuthServer")
		.autoApprove(false);
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore).prefix("/test").accessTokenConverter(accessTokenConverter());
		
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
		security
		//.allowFormAuthenticationForClients()
		.checkTokenAccess("isAuthenticated()")
		.tokenKeyAccess("permitAll() || isAnonymous()")
		;
		//security.checkTokenAccess("permitAll()");
		
	}
	
	  @Bean 
	  public JwtTokenStore tokenStore() { 
		  return new JwtTokenStore(accessTokenConverter()); 
		  }
	  
	  private JwtAccessTokenConverter accessTokenConverter() {
	  
	  final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	  
	  final KeyStoreKeyFactory factory = new KeyStoreKeyFactory( new
	  ClassPathResource("oauth2.jks"), "password".toCharArray());
	  
	  KeyPair keyPair = factory.getKeyPair("oauth2");
	  
	  System.out.println(keyPair.getPrivate());
	  System.out.println(keyPair.getPublic());
	  
	  converter.setKeyPair(keyPair);
	  
	  return converter;
	  
	  }
	 
	
	
	
	
	
	
}
