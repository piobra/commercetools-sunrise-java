package com.commercetools.sunrise.shoppingcart;

import com.commercetools.sunrise.common.pages.SunrisePageData;
import com.commercetools.sunrise.hooks.SunrisePageDataHook;
import com.commercetools.sunrise.framework.ControllerComponent;
import play.mvc.Http;

import javax.inject.Inject;

public class MiniCartControllerComponent implements ControllerComponent, SunrisePageDataHook {
    @Inject
    private Http.Context context;

    @Override
    public void acceptSunrisePageData(final SunrisePageData sunrisePageData) {
        sunrisePageData.getHeader()
                .setMiniCart(CartSessionUtils.getMiniCart(context.session()));
    }
}
