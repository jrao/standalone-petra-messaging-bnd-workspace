/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.petra.messaging.api;

/**
 * Indicates that an inbound or outbound message processor encountered an error
 * while trying to process a message.
 *
 * @author Raymond Augé
 */
public class MessageProcessorException extends RuntimeException {

	public MessageProcessorException() {
	}

	public MessageProcessorException(String msg) {
		super(msg);
	}

	public MessageProcessorException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MessageProcessorException(Throwable cause) {
		super(cause);
	}

}