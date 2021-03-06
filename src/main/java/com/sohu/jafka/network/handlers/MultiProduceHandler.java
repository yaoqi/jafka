/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sohu.jafka.network.handlers;

import com.sohu.jafka.api.MultiProducerRequest;
import com.sohu.jafka.api.ProducerRequest;
import com.sohu.jafka.api.RequestKeys;
import com.sohu.jafka.log.LogManager;
import com.sohu.jafka.network.Receive;
import com.sohu.jafka.network.Send;

/**
 * handler for multi produce request
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 1.0
 */
public class MultiProduceHandler extends ProducerHandler {

    public MultiProduceHandler(LogManager logManager) {
        super(logManager);
    }

    public Send handler(RequestKeys requestType, Receive receive) {
        MultiProducerRequest request = MultiProducerRequest.readFrom(receive.buffer());
        if (logger.isDebugEnabled()) {
            logger.debug("Multiproducer request " + request);
        }
        for (ProducerRequest produce : request.produces) {
            handleProducerRequest(produce, "MultiProducerRequest");
        }
        return null;
    }
}
