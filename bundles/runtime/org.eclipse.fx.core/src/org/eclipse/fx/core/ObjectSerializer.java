/*******************************************************************************
 * Copyright (c) 2015 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package org.eclipse.fx.core;

/**
 * Service interface used to serialize and deserialize objects
 */
public interface ObjectSerializer {
	/**
	 * @return the id of the serializer
	 */
	public String getId();

	/**
	 * Serialize an object
	 * 
	 * @param object
	 *            the object to serialize
	 * @return the object serialized as string
	 */
	public String serialize(Object object);

	/**
	 * Deserialize an object from a string
	 * 
	 * @param clazz
	 *            the type
	 * 
	 * @param value
	 *            the serialized object
	 * @return the deserialized object instance
	 */
	public <O> O deserialize(Class<O> clazz, String value);
}