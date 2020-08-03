package org.cloud.omnia.server;

import org.cloud.omnia.server.config.OmniaClassloaderConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OmniaClassloaderConfig.class)
public @interface EnableOmniaServer {
}
