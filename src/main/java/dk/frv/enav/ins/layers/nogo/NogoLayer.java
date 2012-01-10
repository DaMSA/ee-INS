/*
 * Copyright 2011 Danish Maritime Safety Administration. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY Danish Maritime Safety Administration ``AS IS'' 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of Danish Maritime Safety Administration.
 * 
 */
package dk.frv.enav.ins.layers.nogo;

import java.util.Date;
import java.util.List;

import com.bbn.openmap.layer.OMGraphicHandlerLayer;
import com.bbn.openmap.omGraphics.OMGraphicList;

import dk.frv.enav.common.xml.nogo.types.NogoPolygon;
import dk.frv.enav.ins.nogo.NogoHandler;

public class NogoLayer extends OMGraphicHandlerLayer {
	private static final long serialVersionUID = 1L;

	private NogoHandler nogoHandler = null;

	private OMGraphicList graphics = new OMGraphicList();

	public NogoLayer() {

	}

	public void doUpdate(boolean completed) {
		Date validFrom = nogoHandler.getValidFrom();
		Date validTo = nogoHandler.getValidTo();
		double draught = nogoHandler.getDraught();
		graphics.clear();
		if (completed) {
			// Get polygons
			List<NogoPolygon> polygons = nogoHandler.getPolygons();

			// We have selected an area outside of the available data - send
			// appropiate message
			if (polygons.size() == 0) {
				NogoGraphic nogoGraphic = new NogoGraphic(null, null, null, draught,
						"No data available for requested area");
				graphics.add(nogoGraphic);
			} else {
				// Data available, go through each polygon and draw them
				for (NogoPolygon polygon : polygons) {
					NogoGraphic nogoGraphic = new NogoGraphic(polygon, validFrom, validTo, draught, "");
					graphics.add(nogoGraphic);
				}
			}

		} else {
			// We have just sent a nogo request - display a message telling the
			// user to standby
			NogoGraphic nogoGraphic = new NogoGraphic(null, validFrom, validTo, draught,
					"NoGo area requested - standby");
			graphics.add(nogoGraphic);
		}

		doPrepare();
	}

	@Override
	public synchronized OMGraphicList prepare() {
		graphics.project(getProjection());
		return graphics;
	}

	@Override
	public void findAndInit(Object obj) {
		if (obj instanceof NogoHandler) {
			nogoHandler = (NogoHandler) obj;
		}
	}

}
