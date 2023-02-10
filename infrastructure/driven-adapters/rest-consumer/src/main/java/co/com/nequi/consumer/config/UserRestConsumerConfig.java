package co.com.nequi.consumer.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.AutoTimer;
import org.springframework.boot.actuate.metrics.web.reactive.client.DefaultWebClientExchangeTagsProvider;
import org.springframework.boot.actuate.metrics.web.reactive.client.MetricsWebClientFilterFunction;
import org.springframework.boot.actuate.metrics.web.reactive.client.WebClientExchangeTagsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Configuration
public class UserRestConsumerConfig {

    @Value("${adapter.restconsumer.url}")
    private String url;
    @Value("${adapter.restconsumer.timeout}")
    private int timeout;

    @Bean
    public WebClient getWebClient(
            MetricsWebClientFilterFunction metricsFilter) {
        return WebClient.builder().baseUrl(url).filter(metricsFilter)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .clientConnector(getClientHttpConnector()).build();
    }

    @Bean
    public MetricsWebClientFilterFunction webClientMetricsFilter(
            MeterRegistry registry) {
        WebClientExchangeTagsProvider tagsProvider = new DefaultWebClientExchangeTagsProvider();
        return new MetricsWebClientFilterFunction(registry, tagsProvider,
                "http-outgoing", AutoTimer.ENABLED);
    }

    private ClientHttpConnector getClientHttpConnector() {
        return new ReactorClientHttpConnector(HttpClient.create()
                //.secure(sslContextSpec -> sslContextSpec.sslContext(sslContext))
                .compress(true).keepAlive(true)
                .option(CONNECT_TIMEOUT_MILLIS, timeout)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(
                            new ReadTimeoutHandler(timeout, MILLISECONDS));
                    connection.addHandlerLast(
                            new WriteTimeoutHandler(timeout, MILLISECONDS));
                }));
    }

}
