package org.eclipse.microprofile.jwt.tomee.arquillian;

import org.jboss.arquillian.container.test.spi.client.deployment.ApplicationArchiveProcessor;
import org.jboss.arquillian.core.spi.LoadableExtension;

public class TomEEMPJwtTckExtension implements LoadableExtension {
    @Override
    public void register(ExtensionBuilder extensionBuilder) {
        System.err.println("Registered TomEEWarArchiveProcessor");
        extensionBuilder.service(ApplicationArchiveProcessor.class, TomEEWarArchiveProcessor.class);
    }

}

