import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.FilterableList;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
public final class RestTemplate{

    private final int connectTimeOut = 5000;
    private final int readTimeOut = 5000;

    private boolean bAsync = false;
    private String url;
    private HttpMethod method = HttpMethod.POST;
    private RestTemplate restTemplate;

    //org.apache.http.client.HttpClient
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

    public RestTemplate(String url){
        this(url,HttpMethod.POST, false);
    }

    public RestTemplate(String url,HttpMethod method){
        this(url,method, false);
    }

    public RestTemplate(String url,boolean bAsync){
        this(url,HttpMethod.POST, bAsync);
    }


    public RestTemplate(String url, HttpMethod method, boolean bAsync){
        this.url = url;
        this.method = method;
        this.bAsync = bAsync;

        requestFactory.setConnectTimeout(this.connectTimeOut);
        requestFactory.setReadTimeout(this.connectTimeOut);

        this.restTemplate = new RestTemplate(requestFactory);
    }

    public ResponseEntity exchage(@Nullable Object data, @Nullable HttpHeaders headers) throws Exception{
        if (null==this.url) throw new Exception("Url is Undefined ");

        if (null==headers) headers = HttpHeaders.EMPTY;

        ResponseEntity result = restTemplate.exchange(this.url, this.method
                                                        , new HttpEntity<>(data,headers), Object.class);

        return result;
    }

}
