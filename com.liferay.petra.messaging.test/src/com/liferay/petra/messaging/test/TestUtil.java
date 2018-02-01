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

package com.liferay.petra.messaging.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import com.liferay.petra.messaging.api.MessageBuilderFactory;
import com.liferay.petra.messaging.api.MessageBus;

/**
 * @author Raymond Augé
 */
@Ignore
public class TestUtil {

	@After
	public void after() {
		messageBusTracker.close();
		messageBuilderFactoryTracker.close();
	}

	@Before
	public void before() {
		messageBusTracker = new ServiceTracker<>(
			bundleContext, MessageBus.class, null);

		messageBusTracker.open();

		messageBus = getMessageBus();

		Assert.assertNotNull(messageBus);

		messageBuilderFactoryTracker = new ServiceTracker<>(
			bundleContext, MessageBuilderFactory.class, null);

		messageBuilderFactoryTracker.open();

		messageBuilderFactory = getMessageBuilderFactory();

		Assert.assertNotNull(messageBuilderFactory);
	}

	public InputStream getInputStream(String bundlePath) {
		try {
			URL url = bundle.getEntry(bundlePath);

			return url.openStream();
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	public MessageBuilderFactory getMessageBuilderFactory() {
		try {
			MessageBuilderFactory messageBuilderFactory =
				messageBuilderFactoryTracker.waitForService(timeout);

			Assert.assertNotNull(messageBuilderFactory);

			return messageBuilderFactory;
		}
		catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
	}

	public MessageBus getMessageBus() {
		try {
			MessageBus messageBus = messageBusTracker.waitForService(timeout);

			Assert.assertNotNull(messageBus);

			return messageBus;
		}
		catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
	}

	public Bundle install(String bundlePath) {
		try {
			return bundleContext.installBundle(
				bundlePath, getInputStream(bundlePath));
		}
		catch (BundleException be) {
			throw new RuntimeException(be);
		}
	}

	protected <T> ServiceRegistration<T> registerService(
		Class<T> clazz, T instance, Dictionary<String, Object> properties) {

		return bundleContext.registerService(clazz, instance, properties);
	}

	protected <T> ServiceRegistration<T> registerService(
		Class<T> clazz, T instance, Object... parts) {

		Assert.assertTrue((parts.length % 2) == 0);

		Dictionary<String, Object> properties = new Hashtable<>();

		for (int i = 0; i < parts.length; i += 2) {
			properties.put(String.valueOf(parts[i]), parts[i + 1]);
		}

		return registerService(clazz, instance, properties);
	}

	protected Bundle bundle = FrameworkUtil.getBundle(TestUtil.class);
	protected BundleContext bundleContext = bundle.getBundleContext();
	protected MessageBuilderFactory messageBuilderFactory;
	protected ServiceTracker<MessageBuilderFactory, MessageBuilderFactory>
		messageBuilderFactoryTracker;
	protected MessageBus messageBus;
	protected ServiceTracker<MessageBus, MessageBus> messageBusTracker;
	protected long timeout = 1000;

}
