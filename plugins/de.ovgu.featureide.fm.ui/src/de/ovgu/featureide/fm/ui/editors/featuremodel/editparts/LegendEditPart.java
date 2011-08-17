/* FeatureIDE - An IDE to support feature-oriented software development
 * Copyright (C) 2005-2010  FeatureIDE Team, University of Magdeburg
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses/.
 *
 * See http://www.fosd.de/featureide/ for further information.
 */
package de.ovgu.featureide.fm.ui.editors.featuremodel.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.ui.editors.featuremodel.Legend;
import de.ovgu.featureide.fm.ui.editors.featuremodel.figures.LegendFigure;

/**
 * EditPart for feature model legend
 * 
 * @author Fabian Benduhn
 */
public class LegendEditPart extends AbstractGraphicalEditPart {

	public LegendEditPart(Legend legend) {
		super();
		setModel(legend);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		FeatureModel fm = ((Legend)this.getModel()).getModel();
		boolean hasMandatory = fm.hasMandatoryFeatures();
		boolean hasOptional = fm.hasOptionalFeatures();
		boolean hasAnd = fm.hasAndGroup();
		boolean hasAlternative = fm.hasAlternativeGroup();
		boolean hasOr = fm.hasOrGroup();
		boolean hasAbstract = fm.hasAbstract();
		boolean hasConcrete = fm.hasConcrete();
		boolean hasHidden = fm.hasHidden();
		boolean hasDead = fm.hasDead() || fm.hasFalse();  //same color
		
		LegendFigure figure = new LegendFigure(((Legend) getModel()).getPos(),hasMandatory,
				hasOptional,hasOr,hasAlternative,hasAnd,hasAbstract, hasConcrete, hasHidden,hasDead);

		return figure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {

		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new NonResizableEditPolicy());

	}

}
