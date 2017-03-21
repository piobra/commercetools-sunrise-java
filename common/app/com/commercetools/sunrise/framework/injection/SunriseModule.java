package com.commercetools.sunrise.framework.injection;

import com.commercetools.sunrise.ctp.SphereClientConfigProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.sphere.sdk.client.SphereClientConfig;
import io.sphere.sdk.utils.MoneyImpl;
import play.mvc.Http;

import javax.inject.Singleton;
import javax.money.Monetary;
import javax.money.format.MonetaryFormats;

public class SunriseModule extends AbstractModule {

    @Override
    protected void configure() {
        applyJavaMoneyHack();
        bindScope(RequestScoped.class, new RequestScope());
        bind(SphereClientConfig.class).toProvider(SphereClientConfigProvider.class).in(Singleton.class);
    }

    @Provides
    public Http.Context httpContext() {
        final Http.Context context = Http.Context.current.get();
        if (context != null) {
            return context;
        } else {
            return new Http.Context(new Http.RequestBuilder());
        }
    }

    private void applyJavaMoneyHack() {
        //fixes https://github.com/commercetools/commercetools-sunrise-java/issues/404
        //exception play.api.http.HttpErrorHandlerExceptions$$anon$1: Execution exception[[CompletionException: java.lang.IllegalArgumentException: java.util.concurrent.CompletionException: io.sphere.sdk.json.JsonException: detailMessage: com.fasterxml.jackson.databind.JsonMappingException: Operator failed: javax.money.DefaultMonetaryRoundingsSingletonSpi$DefaultCurrencyRounding@1655879e (through reference chain: io.sphere.sdk.payments.PaymentDraftImpl["amountPlanned"])
        Monetary.getDefaultRounding();
        Monetary.getDefaultRounding().apply(MoneyImpl.ofCents(123, "EUR"));
        Monetary.getDefaultAmountType();
        MonetaryFormats.getDefaultFormatProviderChain();
        Monetary.getDefaultCurrencyProviderChain();
    }
}
