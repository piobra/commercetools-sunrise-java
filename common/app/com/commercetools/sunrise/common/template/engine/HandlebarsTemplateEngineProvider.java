package com.commercetools.sunrise.common.template.engine;

import com.commercetools.sunrise.common.SunriseConfigurationException;
import com.commercetools.sunrise.common.template.engine.handlebars.HandlebarsContextFactory;
import com.commercetools.sunrise.common.template.engine.handlebars.HandlebarsFactory;
import com.commercetools.sunrise.common.template.engine.handlebars.HandlebarsTemplateEngine;
import com.google.inject.Provider;
import play.Configuration;

import javax.inject.Inject;
import java.util.Optional;

public final class HandlebarsTemplateEngineProvider implements Provider<TemplateEngine> {

    private static final String CONFIG_HANDLEBARS = "handlebars";
    @Inject
    private Configuration configuration;
    @Inject
    private HandlebarsFactory handlebarsFactory;
    @Inject
    private HandlebarsContextFactory handlebarsContextFactory;

    @Override
    public TemplateEngine get() {
        return Optional.ofNullable(configuration.getConfig(CONFIG_HANDLEBARS))
                .map(config -> HandlebarsTemplateEngine.of(handlebarsFactory.create(config), handlebarsContextFactory))
                .orElseThrow(() -> new SunriseConfigurationException("Could not initialize HandlebarsTemplateEngine due to missing configuration", CONFIG_HANDLEBARS));
    }
}
