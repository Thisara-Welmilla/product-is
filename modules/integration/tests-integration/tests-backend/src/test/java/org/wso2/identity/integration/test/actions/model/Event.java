/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.identity.integration.test.actions.model;

/**
 * This class models the common properties of the Event sent in the request payload to the API endpoint of a particular action.
 */
public class Event {

    protected Tenant tenant;
    protected Organization organization;
    protected User user;
    protected UserStore userStore;
    protected Application application;

    public Tenant getTenant() {

        return tenant;
    }

    public Organization getOrganization() {

        return organization;
    }

    public User getUser() {

        return user;
    }

    public UserStore getUserStore() {

        return userStore;
    }

    public Application getApplication() {

        return application;
    }
}
