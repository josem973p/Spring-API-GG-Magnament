package com.ociGG.goldengate.Config;

import com.ociGG.goldengate.Entities.BaseEntitieData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource("classpath:credentials.properties")
public class CredentialsConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer getPropertiesConfig(){
        return new PropertySourcesPlaceholderConfigurer();
    }

   public static List<BaseEntitieData> credentials = new ArrayList<BaseEntitieData>();
 int conta =0;

    public void Credentials(String url, String user, String password){
        if (conta==0) {
            BaseEntitieData entity = new BaseEntitieData(url, user, password);
            credentials.add(entity);
            conta ++;
            System.out.println(credentials.get(0).getUrl());
            System.out.println(credentials.get(0).getUser());
            System.out.println(credentials.get(0).getPassword());
        }else{
            credentials.get(0).setUrl(url);
            credentials.get(0).setUser(user);
            credentials.get(0).setPassword(password);
            System.out.println(credentials.get(0).getUrl());
            System.out.println(credentials.get(0).getUser());
            System.out.println(credentials.get(0).getPassword());
        }
    }


    public static void prueba(){}


    public static List<BaseEntitieData> getCredentials() {
        return credentials;
    }



    public static  void setCredentials(List<BaseEntitieData> credentials) {
        credentials = credentials;
    }
}
