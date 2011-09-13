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
package dk.frv.enav.ins.ais;

import dk.frv.ais.geo.GeoLocation;
import dk.frv.ais.message.AisMessage18;
import dk.frv.ais.message.AisPositionMessage;

public class VesselPositionData {
	
	private GeoLocation pos;
	private int navStatus = -1;
	private float rot;
	private float sog;
	private int posAcc;
	private float cog;
	private float trueHeading;
	
	public VesselPositionData(AisPositionMessage aisPositionMessage) {
		pos = aisPositionMessage.getPos().getGeoLocation();
		navStatus = aisPositionMessage.getNavStatus();
		rot = aisPositionMessage.getRot();
		sog = aisPositionMessage.getSog() / (float)10.0;
		posAcc = aisPositionMessage.getPosAcc();
		cog = aisPositionMessage.getCog() / (float)10.0;
		trueHeading = aisPositionMessage.getTrueHeading();
		
		validate();
	}
	
	public VesselPositionData(AisMessage18 aisPositionMessage18) {
		pos = aisPositionMessage18.getPos().getGeoLocation();
		cog = aisPositionMessage18.getCog() / (float)10.0;
		posAcc = aisPositionMessage18.getPosAcc();
		sog = aisPositionMessage18.getSog() / (float)10.0;
		trueHeading = aisPositionMessage18.getTrueHeading();
		
		validate();
	}
	
	private void validate() {
		// Handle unavailable speed and cog
		if (sog > 100) {
			sog = 0;
		}
		if (cog >= 360) {
			cog = 0;
		}
		
		// Handle bad pos
		if (pos.getLatitude() > 90 || pos.getLongitude() > 180) {
			pos = null;
		}
	}
	
	public VesselPositionData(VesselPositionData vesselPositionData) {
		if (vesselPositionData.pos != null) {
			pos = new GeoLocation(pos);
		}
	    navStatus = vesselPositionData.navStatus;
	    rot = vesselPositionData.rot;
	    sog = vesselPositionData.sog;
	    posAcc = vesselPositionData.posAcc;
	    cog = vesselPositionData.cog;
	    trueHeading = vesselPositionData.trueHeading;
	}
	
	public GeoLocation getPos() {
		return pos;
	}

	public void setPos(GeoLocation pos) {
		this.pos = pos;
	}
	
	public boolean hasPos() {
		return (pos != null);
	}

	public int getNavStatus() {
		return navStatus;
	}

	public void setNavStatus(int navStatus) {
		this.navStatus = navStatus;
	}

	public float getRot() {
		return rot;
	}

	public void setRot(float rot) {
		this.rot = rot;
	}

	public float getSog() {
		return sog;
	}

	public void setSog(float sog) {
		this.sog = sog;
	}

	public int getPosAcc() {
		return posAcc;
	}

	public void setPosAcc(int posAcc) {
		this.posAcc = posAcc;
	}

	public float getCog() {
		return cog;
	}

	public void setCog(float cog) {
		this.cog = cog;
	}

	
	/**
	 * Returns the heading in degrees. Heading 511 means the target is not available.
	 * @return Heading in degrees
	 */
	public float getTrueHeading() {
		return trueHeading;
	}

	public void setTrueHeading(float trueHeading) {
		this.trueHeading = trueHeading;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VesselPositionData [cog=");
		builder.append(cog);
		builder.append(", navStatus=");
		builder.append(navStatus);
		builder.append(", pos=");
		builder.append(pos);
		builder.append(", posAcc=");
		builder.append(posAcc);
		builder.append(", rot=");
		builder.append(rot);
		builder.append(", sog=");
		builder.append(sog);
		builder.append(", trueHeading=");
		builder.append(trueHeading);
		builder.append("]");
		return builder.toString();
	}
	
}
