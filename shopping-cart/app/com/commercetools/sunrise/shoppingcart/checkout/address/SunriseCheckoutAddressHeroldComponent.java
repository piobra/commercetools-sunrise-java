package com.commercetools.sunrise.shoppingcart.checkout.address;

import com.commercetools.sunrise.common.pages.HeroldComponentBase;
import com.commercetools.sunrise.common.pages.PageMeta;
import com.commercetools.sunrise.common.reverserouter.CheckoutReverseRouter;

import javax.inject.Inject;

final class SunriseCheckoutAddressHeroldComponent extends HeroldComponentBase {
    @Inject
    private CheckoutReverseRouter reverseRouter;

    protected void updateMeta(final PageMeta meta) {
        meta.addHalLink(reverseRouter.checkoutAddressesPageCall(languageTag()), "checkout", "editShippingAddress", "editBillingAddress");
        meta.addHalLink(reverseRouter.checkoutAddressesProcessFormCall(languageTag()), "checkoutAddressSubmit");
    }
}
