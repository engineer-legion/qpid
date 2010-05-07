/*
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
 *
 */
package org.apache.qpid.server.virtualhost.plugin.SlowConsumer;

import org.apache.log4j.Logger;
import org.apache.qpid.AMQException;
import org.apache.qpid.protocol.AMQConstant;
import org.apache.qpid.server.binding.Binding;
import org.apache.qpid.server.exchange.TopicExchange;
import org.apache.qpid.server.protocol.AMQSessionModel;
import org.apache.qpid.server.queue.AMQQueue;

public class TopicDeletePolicy implements SlowConsumerPolicyPlugin
{
    Logger _logger = Logger.getLogger(TopicDeletePolicy.class);
    private SlowConsumerDetectionPolicyConfiguration _configuration;

    public static class DeletePolicyFactory implements SlowConsumerPolicyPluginFactory
    {

        public SlowConsumerPolicyPlugin newInstance(SlowConsumerDetectionPolicyConfiguration configuration)
        {
            return new TopicDeletePolicy(configuration);
        }

        public String getPluginName()
        {
            return "topicdelete";
        }
    }

    public TopicDeletePolicy(SlowConsumerDetectionPolicyConfiguration config)
    {
        _configuration = config;
    }

    public void performPolicy(AMQQueue q)
    {
        AMQSessionModel owner = q.getExclusiveOwningSession();

        // Only process exclusive queues
        if (owner == null)
        {
            return;
        }

        //Only process Topics
        if(!validateQueueIsATopic(q))
        {
            return;
        }

        try
        {
            owner.getConnectionModel().
                    closeSession(owner, AMQConstant.RESOURCE_ERROR,
                                 "Consuming to slow.");

            String option = _configuration.getOption("delete-persistent");

            boolean deletePersistent = option != null && Boolean.parseBoolean(option);

            if (!q.isAutoDelete() && deletePersistent)
            {
                q.delete();
            }

        }
        catch (AMQException e)
        {
            _logger.warn("Unable to close consumer:" + owner + ", on queue:" + q.getName());
        }

    }

    /**
     * Check the queue bindings to validate the queue is bound to the
     * topic exchange.
     *
     * @param q the Queue
     * @return true iff Q is bound to a TopicExchange
     */
    private boolean validateQueueIsATopic(AMQQueue q)
    {
        for (Binding binding : q.getBindings())
        {
            if (binding.getExchange() instanceof TopicExchange)
            {
                return true;
            }
        }

        return false;
    }
}
