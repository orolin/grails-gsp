/*
 * Copyright 2004-2005 Graeme Rocher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.spring.context.support;

import grails.config.DeprecatedGrailsConfig;
import groovy.util.ConfigObject;

import java.io.IOException;
import java.util.Properties;

import grails.core.GrailsApplication;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Uses Grails' ConfigObject for place holder values.
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public class GrailsPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

    private GrailsApplication grailsApplication;

    public GrailsPlaceholderConfigurer(GrailsApplication grailsApplication) {
        this.grailsApplication = grailsApplication;
        DeprecatedGrailsConfig config = new DeprecatedGrailsConfig(grailsApplication);
        setPlaceholderPrefix(config.get(DeprecatedGrailsConfig.SPRING_PLACEHOLDER_PREFIX, "${"));
        setIgnoreUnresolvablePlaceholders(true);
    }

    @Override
    protected void loadProperties(Properties props) throws IOException {
        ConfigObject config = grailsApplication.getConfig();
        if (config != null) {
            props.putAll(config.toProperties());
        }
    }
}
