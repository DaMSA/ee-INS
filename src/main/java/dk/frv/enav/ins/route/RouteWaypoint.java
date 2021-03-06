/*
 * Copyright 2011 Danish Maritime Authority. All rights reserved.
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
 * THIS SOFTWARE IS PROVIDED BY Danish Maritime Authority ``AS IS'' 
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
 * either expressed or implied, of Danish Maritime Authority.
 * 
 */
package dk.frv.enav.ins.route;

import java.io.Serializable;

import dk.frv.ais.geo.GeoLocation;
import dk.frv.enav.ins.common.Heading;

/**
 * A route waypoint
 */
public class RouteWaypoint implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Optional name for waypoint
	 */
	protected String name;	
	/**
	 * Position
	 */
	protected GeoLocation pos;	
	/**
	 * Optional turn radius
	 */
	protected Double turnRad;
	/**
	 * Optional rate of turn
	 */
	protected Double rot;
	/**
	 * Leg going from waypoint
	 */
	protected RouteLeg outLeg;
	/**
	 * Leg going to this waypoint
	 */
	protected RouteLeg inLeg;
	
	public RouteWaypoint(RouteWaypoint rw){
		this.name = rw.getName();
		this.pos = rw.getPos();
		this.turnRad = rw.getTurnRad();
		this.rot = rw.getRot();
		this.outLeg = rw.getOutLeg();
		this.inLeg = rw.getInLeg();
	}
	
	public RouteWaypoint() {
		
	}
	
	/**
	 * Performs a deep copy of this RouteWaypoint. The copy constructor above is used to perform shallow copy
	 * on route creation and editing, when a back reference to route leg is needed.
	 * @return Copy of routeWaypoint.
	 */
	public RouteWaypoint copy() {
		RouteWaypoint copy = new RouteWaypoint();
		copy.setName(this.getName());
		copy.setPos(this.getPos());
		copy.setTurnRad(this.getTurnRad());
		return copy;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GeoLocation getPos() {
		return pos;
	}

	public void setPos(GeoLocation pos) {
		this.pos = pos;
	}

	public Double getTurnRad() {
		return turnRad;
	}

	public RouteLeg getOutLeg() {
		return outLeg;
	}

	public void setOutLeg(RouteLeg leg) {
		this.outLeg = leg;
	}
	
	public RouteLeg getInLeg() {
		return inLeg;
	}
	
	public void setInLeg(RouteLeg inLeg) {
		this.inLeg = inLeg;
	}
	
	public Double getRot() {
		return rot;
	}
	
	public void setRot(Double rot) {
		if (inLeg == null || outLeg == null) {
			rot = null;
			return;
		}
		this.rot = rot;
		// Calculate radius from fixed speed and rot
		// Speed in nm / minute
		double speed = outLeg.getSpeed() / 60;
		// TODO This is probably not entirely correct
		this.turnRad = speed / rot; 		
	}
	
	public void setTurnRad(Double turnRad) {
		if (inLeg == null /*|| outLeg == null*/) {
			turnRad = null;
			return;
		}
		// TODO: Parser complains if last waypoint doesn't have turnrad, is this correct behavior?
		if (outLeg == null){
			this.turnRad = inLeg.getStartWp().getTurnRad();
			this.rot = inLeg.getStartWp().getRot();
			return;
		}
		this.turnRad = turnRad;
		// Calculate rot from fixed speed and rot
		// Speed in nm / minute
		double speed = outLeg.getSpeed() / 60;
		// TODO This is probably not entirely correct
		this.rot = speed / turnRad;		
	}
	
	public void setSpeed(double speed) {
		if (outLeg == null) {
			return;
		}
		outLeg.setSpeed(speed);
		if (turnRad == null) {
			return;	
		}
		// Calculate rot from fixed speed and rot
		// Speed in nm / minute
		speed /= 60;
		// TODO This is probably not entirely correct
		this.rot = speed / turnRad;
	}
		
	/**
	 * Calc range to next waypoint
	 * @return
	 */
	public Double calcRng() {
		return (outLeg == null ? null : outLeg.calcRng());
	}
	
	public Double calcBrg() {
		return (outLeg == null ? null : outLeg.calcBrg()); 
	}
	
	public Heading getHeading() {
		return (outLeg == null ? null : outLeg.getHeading());
	}
	
	public Double calcRot() {
		if (turnRad == null || outLeg == null || inLeg == null) {
			return null;
		}
		// Set speed will tricker calculation
		setSpeed(outLeg.getSpeed());
		return rot;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RouteWaypoint [leg=");
		builder.append(outLeg);
		builder.append(", name=");
		builder.append(name);
		builder.append(", pos=");
		builder.append(pos);
		builder.append(", turnRad=");
		builder.append(turnRad);
		builder.append("]");
		return builder.toString();
	}
	
}
