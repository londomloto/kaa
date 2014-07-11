/*
 * Copyright 2014 CyberVision, Inc.
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

package org.kaaproject.kaa.client.update.commands;

import org.kaaproject.kaa.client.TransportExceptionHandler;
import org.kaaproject.kaa.client.transport.OperationsTransport;
import org.kaaproject.kaa.client.transport.TransportException;
import org.kaaproject.kaa.client.update.UpdateManager;
import org.kaaproject.kaa.common.endpoint.gen.ProfileUpdateRequest;
import org.kaaproject.kaa.common.endpoint.gen.SyncResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command that is responsible for the profile update.
 *
 * @author Yaroslav Zeygerman
 *
 */
public class ProfileUpdateCommand extends AbstractCommand {
    
    /** The Constant logger. */
    private static final Logger LOG = LoggerFactory
            .getLogger(ProfileUpdateCommand.class);

    final ProfileUpdateRequest request;
    final UpdateManager manager;
    final OperationsTransport transport;

    public ProfileUpdateCommand(ProfileUpdateRequest request,
            UpdateManager manager, OperationsTransport transport,
            TransportExceptionHandler handler) {
        super(handler);
        this.request = request;
        this.manager = manager;
        this.transport = transport;
    }

    @Override
    protected void doExecute() throws TransportException {
        LOG.info("ProfileUpdateRequest started: {}", request);
        SyncResponse response = transport.sendUpdateCommand(request);
        manager.onSyncResponse(response);
    }

}