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

public class TestIntegerBodyImpl extends AMQMethodBody_8_0 implements TestIntegerBody
{
    private static final AMQMethodBodyInstanceFactory FACTORY_INSTANCE = new AMQMethodBodyInstanceFactory()
    {
        public AMQMethodBody newInstance(MarkableDataInput in, long size) throws AMQFrameDecodingException, IOException
        {
            return new TestIntegerBodyImpl(in);
        }
    };

    public static AMQMethodBodyInstanceFactory getFactory()
    {
        return FACTORY_INSTANCE;
    }

    public static final int CLASS_ID =  120;
    public static final int METHOD_ID = 10;

    // Fields declared in specification
    private final short _integer1; // [integer1]
    private final int _integer2; // [integer2]
    private final long _integer3; // [integer3]
    private final long _integer4; // [integer4]
    private final short _operation; // [operation]

    // Constructor
    public TestIntegerBodyImpl(MarkableDataInput buffer) throws AMQFrameDecodingException, IOException
    {
        _integer1 = readUnsignedByte( buffer );
        _integer2 = readUnsignedShort( buffer );
        _integer3 = readUnsignedInteger( buffer );
        _integer4 = readLong( buffer );
        _operation = readUnsignedByte( buffer );
    }

    public TestIntegerBodyImpl(
                                short integer1,
                                int integer2,
                                long integer3,
                                long integer4,
                                short operation
                            )
    {
        _integer1 = integer1;
        _integer2 = integer2;
        _integer3 = integer3;
        _integer4 = integer4;
        _operation = operation;
    }

    public int getClazz()
    {
        return CLASS_ID;
    }

    public int getMethod()
    {
        return METHOD_ID;
    }

    public final short getInteger1()
    {
        return _integer1;
    }
    public final int getInteger2()
    {
        return _integer2;
    }
    public final long getInteger3()
    {
        return _integer3;
    }
    public final long getInteger4()
    {
        return _integer4;
    }
    public final short getOperation()
    {
        return _operation;
    }

    protected int getBodySize()
    {
        int size = 16;
        return size;
    }

    public void writeMethodPayload(DataOutput buffer) throws IOException
    {
        writeUnsignedByte( buffer, _integer1 );
        writeUnsignedShort( buffer, _integer2 );
        writeUnsignedInteger( buffer, _integer3 );
        writeLong( buffer, _integer4 );
        writeUnsignedByte( buffer, _operation );
    }

    public boolean execute(MethodDispatcher dispatcher, int channelId) throws AMQException
	{
    return ((MethodDispatcher_8_0)dispatcher).dispatchTestInteger(this, channelId);
	}

    public String toString()
    {
        StringBuilder buf = new StringBuilder("[TestIntegerBodyImpl: ");
        buf.append( "integer1=" );
        buf.append(  getInteger1() );
        buf.append( ", " );
        buf.append( "integer2=" );
        buf.append(  getInteger2() );
        buf.append( ", " );
        buf.append( "integer3=" );
        buf.append(  getInteger3() );
        buf.append( ", " );
        buf.append( "integer4=" );
        buf.append(  getInteger4() );
        buf.append( ", " );
        buf.append( "operation=" );
        buf.append(  getOperation() );
        buf.append("]");
        return buf.toString();
    }

}
