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
 *   8-0
 */

package org.apache.qpid.framing.amqp_8_0;

import org.apache.qpid.codec.MarkableDataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.qpid.framing.*;
import org.apache.qpid.AMQException;

public class StreamConsumeBodyImpl extends AMQMethodBody_8_0 implements StreamConsumeBody
{
    private static final AMQMethodBodyInstanceFactory FACTORY_INSTANCE = new AMQMethodBodyInstanceFactory()
    {
        public AMQMethodBody newInstance(MarkableDataInput in, long size) throws AMQFrameDecodingException, IOException
        {
            return new StreamConsumeBodyImpl(in);
        }
    };

    public static AMQMethodBodyInstanceFactory getFactory()
    {
        return FACTORY_INSTANCE;
    }

    public static final int CLASS_ID =  80;
    public static final int METHOD_ID = 20;

    // Fields declared in specification
    private final int _ticket; // [ticket]
    private final AMQShortString _queue; // [queue]
    private final AMQShortString _consumerTag; // [consumerTag]
    private final byte _bitfield0; // [noLocal, exclusive, nowait]

    // Constructor
    public StreamConsumeBodyImpl(MarkableDataInput buffer) throws AMQFrameDecodingException, IOException
    {
        _ticket = readUnsignedShort( buffer );
        _queue = readAMQShortString( buffer );
        _consumerTag = readAMQShortString( buffer );
        _bitfield0 = readBitfield( buffer );
    }

    public StreamConsumeBodyImpl(
                                int ticket,
                                AMQShortString queue,
                                AMQShortString consumerTag,
                                boolean noLocal,
                                boolean exclusive,
                                boolean nowait
                            )
    {
        _ticket = ticket;
        _queue = queue;
        _consumerTag = consumerTag;
        byte bitfield0 = (byte)0;
        if( noLocal )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 0));
        }

        if( exclusive )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 1));
        }

        if( nowait )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 2));
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

    public final int getTicket()
    {
        return _ticket;
    }
    public final AMQShortString getQueue()
    {
        return _queue;
    }
    public final AMQShortString getConsumerTag()
    {
        return _consumerTag;
    }
    public final boolean getNoLocal()
    {
        return (((int)(_bitfield0)) & ( 1 << 0)) != 0;
    }
    public final boolean getExclusive()
    {
        return (((int)(_bitfield0)) & ( 1 << 1)) != 0;
    }
    public final boolean getNowait()
    {
        return (((int)(_bitfield0)) & ( 1 << 2)) != 0;
    }

    protected int getBodySize()
    {
        int size = 3;
        size += getSizeOf( _queue );
        size += getSizeOf( _consumerTag );
        return size;
    }

    public void writeMethodPayload(DataOutput buffer) throws IOException
    {
        writeUnsignedShort( buffer, _ticket );
        writeAMQShortString( buffer, _queue );
        writeAMQShortString( buffer, _consumerTag );
        writeBitfield( buffer, _bitfield0 );
    }

    public boolean execute(MethodDispatcher dispatcher, int channelId) throws AMQException
	{
    return ((MethodDispatcher_8_0)dispatcher).dispatchStreamConsume(this, channelId);
	}

    public String toString()
    {
        StringBuilder buf = new StringBuilder("[StreamConsumeBodyImpl: ");
        buf.append( "ticket=" );
        buf.append(  getTicket() );
        buf.append( ", " );
        buf.append( "queue=" );
        buf.append(  getQueue() );
        buf.append( ", " );
        buf.append( "consumerTag=" );
        buf.append(  getConsumerTag() );
        buf.append( ", " );
        buf.append( "noLocal=" );
        buf.append(  getNoLocal() );
        buf.append( ", " );
        buf.append( "exclusive=" );
        buf.append(  getExclusive() );
        buf.append( ", " );
        buf.append( "nowait=" );
        buf.append(  getNowait() );
        buf.append("]");
        return buf.toString();
    }

}
