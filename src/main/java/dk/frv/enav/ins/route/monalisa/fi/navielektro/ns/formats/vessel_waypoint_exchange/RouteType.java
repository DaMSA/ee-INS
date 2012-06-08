//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.08 at 04:37:43 PM CEST 
//


package dk.frv.enav.ins.route.monalisa.fi.navielektro.ns.formats.vessel_waypoint_exchange;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A route definition
 * 
 * <p>Java class for routeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="routeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="waypoints" type="{http://www.navielektro.fi/ns/formats/vessel-waypoint-exchange}waypointsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "routeType", propOrder = {
    "waypoints"
})
public class RouteType {

    @XmlElement(required = true)
    protected WaypointsType waypoints;

    /**
     * Gets the value of the waypoints property.
     * 
     * @return
     *     possible object is
     *     {@link WaypointsType }
     *     
     */
    public WaypointsType getWaypoints() {
        return waypoints;
    }

    /**
     * Sets the value of the waypoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link WaypointsType }
     *     
     */
    public void setWaypoints(WaypointsType value) {
        this.waypoints = value;
    }

}