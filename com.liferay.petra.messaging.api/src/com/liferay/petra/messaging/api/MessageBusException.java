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
 * Indicates that the message bus encountered an error while trying to send a
 * message.
 *
 * @author Brian Wing Shun Chan
 */
public class MessageBusException extends RuntimeException {

	public MessageBusException() {
	}

	public MessageBusException(String msg) {
		super(msg);
	}

	public MessageBusException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MessageBusException(Throwable cause) {
		super(cause);
	}

}