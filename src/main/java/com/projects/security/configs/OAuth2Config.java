package com.projects.security.configs;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	   @Value("${security.jwt.client-id}")
	   private String clientId;

	   @Value("${security.jwt.client-secret}")
	   private String clientSecret;

	   @Value("${security.jwt.grant-type}")
	   private String grantType;

	   @Value("${security.jwt.scope-read}")
	   private String scopeRead;

	   @Value("${security.jwt.scope-write}")
	   private String scopeWrite = "write";

	   @Value("${security.jwt.resource-ids}")
	   private String resourceIds;

	   @Autowired
	   private TokenStore tokenStore;

	   @Autowired
	   private JwtAccessTokenConverter accessTokenConverter;

	   @Autowired
	   private AuthenticationManager authenticationManager;

	   @Override
	   public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
	      /*configurer
	              .inMemory()
	              .withClient(clientId)
	              .secret(clientSecret)
	              .authorizedGrantTypes(grantType)
	              .scopes(scopeRead, scopeWrite)
	              .resourceIds(resourceIds);*/
		   configurer.jdbc(oauthdataSource());
	   }

	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
	      enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
	      endpoints.tokenStore(tokenStore)
	              .accessTokenConverter(accessTokenConverter)
	              .tokenEnhancer(enhancerChain)
	              .authenticationManager(authenticationManager);
	   }
	   
	    @Bean(name = "oauthdataSource")
	    @ConfigurationProperties(prefix="spring.datasource")
	    public DataSource oauthdataSource() {
	        return DataSourceBuilder.create().build();
	    }
	   
	   /*
	    * To be implemented for assymetric key pair
	    * keytool -genkeypair -alias mykeypair 
                    -keyalg RSA 
                    -keypass jkspass 
                    -keystore mykeypair.jks 
                    -storepass jksmypass


	    * 
	    * @Bean
		public JwtAccessTokenConverter accessTokenConverter() {
		    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		    KeyStoreKeyFactory keyStoreKeyFactory = 
		      new KeyStoreKeyFactory(new ClassPathResource("mykeypair.jks"), "jkspass".toCharArray());
		    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mykeypair"));
		    return converter;
		}
	    */


}
