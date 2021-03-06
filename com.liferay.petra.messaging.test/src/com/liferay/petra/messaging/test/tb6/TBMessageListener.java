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

package com.liferay.petra.messaging.test.tb6;

import com.liferay.petra.messaging.api.Message;
import com.liferay.petra.messaging.api.MessageListener;
import com.liferay.petra.messaging.api.MessageListenerException;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Raymond Augé
 */
@Component(
	property = "destination.name=" + TBSynchronousDestination.DESTINATION_NAME,
	scope = ServiceScope.SINGLETON,
	service = {Callable.class, MessageListener.class}
)
public class TBMessageListener implements Callable<Message>, MessageListener {

	@Override
	public Message call() throws Exception {
		return _message.get();
	}

	@Override
	public void receive(Message message) throws MessageListenerException {
		_message.set(message);
	}

	private final AtomicReference<Message> _message = new AtomicReference<>();

}