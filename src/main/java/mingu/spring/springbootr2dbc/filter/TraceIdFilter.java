package mingu.spring.springbootr2dbc.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.HttpHandlerDecoratorFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.UUID;

@Slf4j
@Order(PriorityOrdered.HIGHEST_PRECEDENCE)
@Component
public class TraceIdFilter implements WebFilter, HttpHandlerDecoratorFactory {

    private static final String MDC_TRACE_ID = "traceId";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange);
    }

    @Override
    public HttpHandler apply(HttpHandler httpHandler) {
        return ((request, response) -> {
            final String traceId = UUID.randomUUID().toString();
            MDC.put(MDC_TRACE_ID, traceId); // 여기에서 ThreadLocal로 traceId를 넣는다.
            return httpHandler.handle(request, response)
                    .contextWrite(context -> Context.of(MDC_TRACE_ID, traceId)); // 앞으로 호출될 Reactor에서 가질 Context에 traceId를 넣는다.

        });
    }
}
