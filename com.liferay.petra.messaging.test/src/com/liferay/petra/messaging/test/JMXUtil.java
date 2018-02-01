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

import javax.management.MBeanServer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Raymond Augé
 */
@Ignore
public class JMXUtil extends TestUtil {

	@After
	public void after() {
		mBeanServerTracker.close();

		super.after();
	}

	@Before
	public void before() {
		super.before();

		mBeanServerTracker = new ServiceTracker<>(
			bundleContext, MBeanServer.class, null);

		mBeanServerTracker.open();

		mBeanServer = getMBeanServer();
	}

	protected MBeanServer getMBeanServer() {
		try {
			MBeanServer mBeanServer = mBeanServerTracker.waitForService(
				timeout);

			Assert.assertNotNull(mBeanServer);

			return mBeanServer;
		}
		catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
	}

	protected MBeanServer mBeanServer;
	protected ServiceTracker<MBeanServer, MBeanServer> mBeanServerTracker;

}