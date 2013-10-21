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
 *   0-91
 */

package org.apache.qpid.framing.amqp_0_91;

import org.apache.qpid.codec.MarkableDataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.qpid.framing.*;
import org.apache.qpid.AMQException;

public class ChannelCloseBodyImpl extends AMQMethodBody_0_91 implements ChannelCloseBody
{
    private static final AMQMethodBodyInstanceFactory FACTORY_INSTANCE = new AMQMethodBodyInstanceFactory()
    {
        public AMQMethodBody newInstance(MarkableDataInput in, long size) throws AMQFrameDecodingException, IOException
        {
            return new ChannelCloseBodyImpl(in);
        }
    };

    public static AMQMethodBodyInstanceFactory getFactory()
    {
        return FACTORY_INSTANCE;
    }

    public static final int CLASS_ID =  20;
    public static final int METHOD_ID = 40;

    // Fields declared in specification
    private final int _replyCode; // [replyCode]
    private final AMQShortString _replyText; // [replyText]
    private final int _classId; // [classId]
    private final int _methodId; // [methodId]

    // Constructor
    public ChannelCloseBodyImpl(MarkableDataInput buffer) throws AMQFrameDecodingException, IOException
    {
        _replyCode = readUnsignedShort( buffer );
        _replyText = readAMQShortString( buffer );
        _classId = readUnsignedShort( buffer );
        _methodId = readUnsignedShort( buffer );
    }

    public ChannelCloseBodyImpl(
                                int replyCode,
                                AMQShortString replyText,
                                int classId,
                                int methodId
                            )
    {
        _replyCode = replyCode;
        _replyText = replyText;
        _classId = classId;
        _methodId = methodId;
    }

    public int getClazz()
    {
        return CLASS_ID;
    }

    public int getMethod()
    {
        return METHOD_ID;
    }

    public final int getReplyCode()
    {
        return _replyCode;
    }
    public final AMQShortString getReplyText()
    {
        return _replyText;
    }
    public final int getClassId()
    {
        return _classId;
    }
    public final int getMethodId()
    {
        return _methodId;
    }

    protected int getBodySize()
    {
        int size = 6;
        size += getSizeOf( _replyText );
        return size;
    }

    public void writeMethodPayload(DataOutput buffer) throws IOException
    {
        writeUnsignedShort( buffer, _replyCode );
        writeAMQShortString( buffer, _replyText );
        writeUnsignedShort( buffer, _classId );
        writeUnsignedShort( buffer, _methodId );
    }

    public boolean execute(MethodDispatcher dispatcher, int channelId) throws AMQException
	{
    return ((MethodDispatcher_0_91)dispatcher).dispatchChannelClose(this, channelId);
	}

    public String toString()
    {
        StringBuilder buf = new StringBuilder("[ChannelCloseBodyImpl: ");
        buf.append( "replyCode=" );
        buf.append(  getReplyCode() );
        buf.append( ", " );
        buf.append( "replyText=" );
        buf.append(  getReplyText() );
        buf.append( ", " );
        buf.append( "classId=" );
        buf.append(  getClassId() );
        buf.append( ", " );
        buf.append( "methodId=" );
        buf.append(  getMethodId() );
        buf.append("]");
        return buf.toString();
    }

}
