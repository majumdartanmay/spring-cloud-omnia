package org.cloud.omnia.server;

import org.cloud.omnia.server.config.OmniaServerClassloaderConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OmniaServerClassloaderConfig.class)
public @interface EnableOmniaServer {
}
