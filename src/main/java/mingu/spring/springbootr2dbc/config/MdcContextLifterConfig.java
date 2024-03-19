package mingu.spring.springbootr2dbc.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Operators;
import reactor.util.context.Context;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
public class MdcContextLifterConfig {

    public static final String MDC_CONTEXT_REACTOR_KEY = MdcContextLifterConfig.class.getName();

    @PostConstruct
    public void contextOperatorHook() {
        // Publisher Mono/Flux 가 생성될 때마다 해당 Operator 실행
        Hooks.onEachOperator(
                MDC_CONTEXT_REACTOR_KEY,
                Operators.lift(((scannable, coreSubscriber) -> new MdcContextLifter<>(coreSubscriber)))
        );
    }

    @PreDestroy
    public void cleanupHook() {
        Hooks.resetOnEachOperator(MDC_CONTEXT_REACTOR_KEY);
    }

    @Slf4j
    @RequiredArgsConstructor
    public static class MdcContextLifter<T> implements CoreSubscriber<T> {

        private final CoreSubscriber<T> coreSubscriber;

        @Override
        public void onSubscribe(Subscription subscription) {
            coreSubscriber.onSubscribe(subscription);
        }

        @Override
        public void onNext(T t) {
            copyToMdc(coreSubscriber.currentContext()); // onNext가 발생할 때마다 MDC를 복사해준다.
            coreSubscriber.onNext(t);
        }

        @Override
        public void onError(Throwable throwable) {
            copyToMdc(coreSubscriber.currentContext());
            coreSubscriber.onError(throwable);
        }

        @Override
        public void onComplete() {
            coreSubscriber.onComplete();
        }

        @Override
        public Context currentContext() {
            return coreSubscriber.currentContext();
        }

        private void copyToMdc(Context context) {
            if (Objects.isNull(context) || context.isEmpty()) {
                MDC.clear();
                return;
            }
            Map<String, String> contextMap = context.stream()
                    .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
            MDC.setContextMap(contextMap);
        }
    }

}
