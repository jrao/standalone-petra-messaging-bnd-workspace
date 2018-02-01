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

package com.liferay.petra.messaging.test.tb14;

import org.osgi.service.component.annotations.Component;

import com.liferay.petra.messaging.api.DestinationConfiguration;
import com.liferay.petra.messaging.api.DestinationType;

/**
 * @author Raymond Augé
 */
@Component(service = DestinationConfiguration.class)
public class TBParallelDestination extends DestinationConfiguration {
	
	public static final String DESTINATION_NAME = "parallel/test";

	public TBParallelDestination() {
		super(DestinationType.PARALLEL, "parallel/test");
	}

}