package org.eclipse.microprofile.jwt.tomee.arquillian;

import java.util.logging.Logger;

import org.jboss.arquillian.container.test.spi.client.deployment.ApplicationArchiveProcessor;
import org.jboss.arquillian.test.spi.TestClass;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class TomEEWarArchiveProcessor implements ApplicationArchiveProcessor {
    private static Logger log = Logger.getLogger(TomEEWarArchiveProcessor.class.getName());

    @Override
    public void process(Archive<?> appArchive, TestClass testClass) {
        if (!(appArchive instanceof WebArchive)) {
            return;
        }
        log.info("Preparing archive: "+appArchive);
        WebArchive war = WebArchive.class.cast(appArchive);
        war.addAsWebInfResource("jwt-roles.properties", "classes/jwt-roles.properties")
            .addAsManifestResource(war.get("/WEB-INF/classes/publicKey.pem").getAsset(), "/MP-JWT-SIGNER")
                .addAsResource("login.config")
                .addAsResource("jwt-roles.properties", "roles.properties")
                .addAsResource("users.properties")
                .addAsManifestResource("tck-context.xml", "context.xml")
            .setWebXML("WEB-INF/web.xml")
            ;
        log.info("Augmented war: \n"+war.toString(true));
    }
}
