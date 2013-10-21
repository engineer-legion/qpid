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

/*
 * This file is auto-generated by Qpid Gentools v.0.1 - do not modify.
 * Supported AMQP version:
 *   0-9
 */

package org.apache.qpid.framing.amqp_0_9;

import org.apache.qpid.codec.MarkableDataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.qpid.framing.*;
import org.apache.qpid.AMQException;

public class BasicAckBodyImpl extends AMQMethodBody_0_9 implements BasicAckBody
{
    private static final AMQMethodBodyInstanceFactory FACTORY_INSTANCE = new AMQMethodBodyInstanceFactory()
    {
        public AMQMethodBody newInstance(MarkableDataInput in, long size) throws AMQFrameDecodingException, IOException
        {
            return new BasicAckBodyImpl(in);
        }
    };

    public static AMQMethodBodyInstanceFactory getFactory()
    {
        return FACTORY_INSTANCE;
    }

    public static final int CLASS_ID =  60;
    public static final int METHOD_ID = 80;

    // Fields declared in specification
    private final long _deliveryTag; // [deliveryTag]
    private final byte _bitfield0; // [multiple]

    // Constructor
    public BasicAckBodyImpl(MarkableDataInput buffer) throws AMQFrameDecodingException, IOException
    {
        _deliveryTag = readLong( buffer );
        _bitfield0 = readBitfield( buffer );
    }

    public BasicAckBodyImpl(
                                long deliveryTag,
                                boolean multiple
                            )
    {
        _deliveryTag = deliveryTag;
        byte bitfield0 = (byte)0;
        if( multiple )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 0));
        }
        _bitfield0 = bitfield0;
    }

    public int getClazz()
    {
        return CLASS_ID;
    }

    public int getMethod()
    {
        return METHOD_ID;
    }

    public final long getDeliveryTag()
    {
        return _deliveryTag;
    }
    public final boolean getMultiple()
    {
        return (((int)(_bitfield0)) & ( 1 << 0)) != 0;
    }

    protected int getBodySize()
    {
        int size = 9;
        return size;
    }

    public void writeMethodPayload(DataOutput buffer) throws IOException
    {
        writeLong( buffer, _deliveryTag );
        writeBitfield( buffer, _bitfield0 );
    }

    public boolean execute(MethodDispatcher dispatcher, int channelId) throws AMQException
	{
    return ((MethodDispatcher_0_9)dispatcher).dispatchBasicAck(this, channelId);
	}

    public String toString()
    {
        StringBuilder buf = new StringBuilder("[BasicAckBodyImpl: ");
        buf.append( "deliveryTag=" );
        buf.append(  getDeliveryTag() );
        buf.append( ", " );
        buf.append( "multiple=" );
        buf.append(  getMultiple() );
        buf.append("]");
        return buf.toString();
    }

}
