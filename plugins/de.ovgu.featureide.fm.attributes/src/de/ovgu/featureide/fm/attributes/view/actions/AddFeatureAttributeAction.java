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
package de.ovgu.featureide.fm.attributes.view.actions;

import org.eclipse.jface.action.Action;

import de.ovgu.featureide.fm.attributes.base.IFeatureAttribute;
import de.ovgu.featureide.fm.attributes.base.impl.BooleanFeatureAttribute;
import de.ovgu.featureide.fm.attributes.base.impl.DoubleFeatureAttribute;
import de.ovgu.featureide.fm.attributes.base.impl.ExtendedFeature;
import de.ovgu.featureide.fm.attributes.base.impl.ExtendedFeatureModel;
import de.ovgu.featureide.fm.attributes.base.impl.FeatureAttribute;
import de.ovgu.featureide.fm.attributes.base.impl.LongFeatureAttribute;
import de.ovgu.featureide.fm.attributes.base.impl.StringFeatureAttribute;
import de.ovgu.featureide.fm.core.base.event.FeatureIDEEvent;
import de.ovgu.featureide.fm.core.base.event.FeatureIDEEvent.EventType;

/**
 * Action used to create an attribute. Depending on the {@link #attributeType} the action creates an attribute of the given type.
 *
 * @author Joshua Sprey
 * @author Chico Sundermann
 */
public class AddFeatureAttributeAction extends Action {

	private final ExtendedFeatureModel featureModel;
	private final ExtendedFeature feature;
	private final String attributeType;

	public AddFeatureAttributeAction(ExtendedFeatureModel featureModel, ExtendedFeature feature, String attributeType, String actionName) {
		super(actionName);
		this.featureModel = featureModel;
		this.feature = feature;
		this.attributeType = attributeType;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		String name = getUniqueAttributeName();
		switch (attributeType) {
		case FeatureAttribute.BOOLEAN:
			final IFeatureAttribute attributeBoolean = new BooleanFeatureAttribute(feature, name, "", null, false, false);
			feature.addAttribute(attributeBoolean);
			featureModel.fireEvent(new FeatureIDEEvent(attributeBoolean, EventType.FEATURE_ATTRIBUTE_CHANGED, true, feature));
			break;
		case FeatureAttribute.DOUBLE:
			final IFeatureAttribute attributeDouble = new DoubleFeatureAttribute(feature, name, "", null, false, false);
			feature.addAttribute(attributeDouble);
			featureModel.fireEvent(new FeatureIDEEvent(attributeDouble, EventType.FEATURE_ATTRIBUTE_CHANGED, true, feature));
			break;
		case FeatureAttribute.LONG:
			final IFeatureAttribute attributeLong = new LongFeatureAttribute(feature, name, "", null, false, false);
			feature.addAttribute(attributeLong);
			featureModel.fireEvent(new FeatureIDEEvent(attributeLong, EventType.FEATURE_ATTRIBUTE_CHANGED, true, feature));
			break;
		case FeatureAttribute.STRING:
			final IFeatureAttribute attributeString = new StringFeatureAttribute(feature, name, "", null, false, false);
			feature.addAttribute(attributeString);
			featureModel.fireEvent(new FeatureIDEEvent(attributeString, EventType.FEATURE_ATTRIBUTE_CHANGED, true, feature));
			break;
		default:
			break;
		}
	}

	private String getUniqueAttributeName() {
		int amountOfAttributes = 0;
		while (true) {
			boolean isUnique = true;
			String attributeName = "Attribute" + amountOfAttributes++;
			for (IFeatureAttribute att : feature.getAttributes()) {
				if (att.getName().equals(attributeName)) {
					isUnique = false;
					break;
				}
			}
			if (isUnique) {
				return attributeName;
			}
		}

	}
}
