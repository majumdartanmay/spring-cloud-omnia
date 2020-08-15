package org.cloud.omnia.server;

import org.cloud.omnia.server.config.OmniaServerClassloaderConfig;
import org.springframework.context.annotation.Import;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

/**
 * Class to load omnia class loaders.
 *
 * @author Tanmay Majumdar
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OmniaServerClassloaderConfig.class)
public @interface EnableOmniaServer {
}
