/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2017  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 *
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See http://featureide.cs.ovgu.de/ for further information.
 */
package de.ovgu.featureide.fm.core.base.impl;

import de.ovgu.featureide.fm.core.IExtensionLoader;
import de.ovgu.featureide.fm.core.base.IFactory;
import de.ovgu.featureide.fm.core.configuration.Configuration;

/**
 * Manages all factories for configuration objects.
 *
 * @author Sebastian Krieter
 */
public class ConfigurationFactoryManager extends FactoryManager<Configuration> {

	@Override
	protected String getDefaultID() {
		return DefaultConfigurationFactory.ID;
	}

	@Override
	protected Class<?>[] getDefaultClasses() {
		return new Class<?>[] { DefaultConfigurationFactory.class };
	}

	private static ConfigurationFactoryManager instance = new ConfigurationFactoryManager();

	public static ConfigurationFactoryManager getInstance() {
		instance.setLoader(null, null);
		return instance;
	}

	public static void initialize(IExtensionLoader<IFactory<Configuration>> extensionLoader, IFactoryWorkspaceLoader factorySpaceLoader) {
		instance.setLoader(extensionLoader, factorySpaceLoader);
	}

	@Override
	public IFactory<? extends Configuration> getFactory(Configuration object) {
		return DefaultConfigurationFactory.getInstance();
	}

}