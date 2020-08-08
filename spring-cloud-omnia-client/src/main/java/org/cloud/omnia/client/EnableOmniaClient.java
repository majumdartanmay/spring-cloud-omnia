package org.cloud.omnia.client;

import org.cloud.omnia.client.config.OmniaClientClassloaderConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({OmniaClientClassloaderConfig.class})
public @interface EnableOmniaClient {
}
