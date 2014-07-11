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

package org.kaaproject.kaa.server.operations.service.akka.messages.io.request;

import org.kaaproject.kaa.common.endpoint.gen.LongSyncRequest;
import org.kaaproject.kaa.common.hash.EndpointObjectHash;
import org.kaaproject.kaa.server.operations.service.akka.messages.core.endpoint.EndpointAwareMessage;
import org.kaaproject.kaa.server.operations.service.akka.messages.core.session.LongSyncRequestMessage;
import org.kaaproject.kaa.server.operations.service.akka.messages.io.NettyCommandAwareMessage;

import akka.actor.ActorRef;


/**
 * The Class NettyEndpointLongSyncMessage.
 */
public class NettyEndpointLongSyncMessage extends NettyDecodedRequestMessage {

    /** The request. */
    private final LongSyncRequest request;

    /**
     * Instantiates a new netty endpoint long sync message.
     *
     * @param request the request
     * @param nettyMessage the netty message
     */
    public NettyEndpointLongSyncMessage(LongSyncRequest request, NettyCommandAwareMessage nettyMessage) {
        super(nettyMessage.getHandlerUuid(), nettyMessage.getChannelContext(), nettyMessage.getCommand());
        this.request = request;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "NettyEndpointLongSyncMessage [request=" + request + "]";
    }

    /* (non-Javadoc)
     * @see org.kaaproject.kaa.server.operations.service.akka.messages.io.request.NettyDecodedRequestMessage#toEndpointMessage(akka.actor.ActorRef)
     */
    @Override
    public EndpointAwareMessage toEndpointMessage(ActorRef originator) {
        EndpointObjectHash endpointKey = EndpointObjectHash.fromBytes(request.getSyncRequest().getEndpointPublicKeyHash().array());
        return new LongSyncRequestMessage(request.getSyncRequest().getApplicationToken(), endpointKey, request, originator);
    }
}