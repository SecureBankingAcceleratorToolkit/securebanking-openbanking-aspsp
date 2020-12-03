/**
 * Copyright 2020 ForgeRock AS.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.forgerock.securebanking.openbanking.aspsp.discovery;

import com.forgerock.securebanking.openbanking.aspsp.common.OBApiReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Stores controller methods that have been disabled in the configuration (i.e. endpoints that are not supported by
 * the Discovery endpoint). This blacklist can be used to accurately reject requests to controller methods, without
 * needing to use regex matches (which may match the same URL, but the wrong HTTP method).
 */
@Slf4j
@Component
public class ControllerEndpointBlacklistHandler {

    private final RequestMappingHandlerMapping requestHandlerMapping;
    /**
     * Set of all controller methods that are not in discovery
     */
    private final Set<String> blackList = new HashSet<>();

    public ControllerEndpointBlacklistHandler(RequestMappingHandlerMapping requestHandlerMapping) {
        this.requestHandlerMapping = requestHandlerMapping;
    }

    /**
     * Adds the API endpoint to the list of ones to block.
     *
     * @param obEndpointReference the {@link OBApiReference} from the list of available endpoints.
     * @param obEndpointUrl the {@link URL} of the endpoint from the list of available endpoints.
     */
    public void blacklistEndpoint(OBApiReference obEndpointReference, URL obEndpointUrl) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
            RequestMappingInfo mapping = item.getKey();
            HandlerMethod method = item.getValue();

            if (isOBApiEndpoint(mapping, obEndpointReference, obEndpointUrl)) {
                addToBlacklist(method.getBeanType(), method.getMethod());
            }
        }
    }

    public boolean isBlacklisted(Class clazz, Method method) {
        if (clazz == null || method == null) {
            return false;
        }
        String methodName = formatString(clazz, method);
        return blackList.contains(methodName);
    }

    private boolean isOBApiEndpoint(RequestMappingInfo mapping, OBApiReference obApiEndpoint, URL obApiUrl) {
        Set<String> patterns = mapping.getPatternsCondition().getPatterns();
        Set<RequestMethod> methods = mapping.getMethodsCondition().getMethods();
        if (patterns.isEmpty() || methods.isEmpty()) {
            return false;
        }

        String urlPattern = patterns.iterator().next();
        String httpMethod = methods.iterator().next().name();
        return obApiUrl.getPath().equals(urlPattern) && httpMethod.equals(obApiEndpoint.getHttpMethod().name());
    }

    private void addToBlacklist(Class clazz, Method method) {
        if (clazz != null && method != null) {
            String methodName = formatString(clazz, method);
            blackList.add(methodName);
            log.info("Disabled Controller method: {}", methodName);
        }
    }

    private static String formatString(Class clazz, Method method) {
        return String.format("%s.%s", clazz.getName(), method.getName());
    }
}
