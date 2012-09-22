//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.08 at 04:37:43 PM CEST 
//


package dk.frv.enav.ins.route.monalisa.se.sspa.optiroute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import dk.frv.enav.ins.route.monalisa.fi.navielektro.ns.formats.vessel_waypoint_exchange.RouteType;


/**
 * The calculated optimal route, with ETA for each waypoint and fuel consumption for requested and final routes
 * 
 * <p>Java class for routeresponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="routeresponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FuelRequested" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="FuelFinal" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Route" type="{http://www.navielektro.fi/ns/formats/vessel-waypoint-exchange}routeType"/>
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
@XmlType(name = "RouteResponseType", propOrder = {
    "fuelRequested",
    "fuelFinal",
    "route"
})
public class RouteresponseType {

    @XmlElement(name = "FuelRequested")
    protected float fuelRequested;
    @XmlElement(name = "FuelFinal")
    protected float fuelFinal;
    @XmlElement(name = "Route", required = true)
    protected RouteType route;

    /**
     * Gets the value of the fuelRequested property.
     * 
     */
    public float getFuelRequested() {
        return fuelRequested;
    }

    /**
     * Sets the value of the fuelRequested property.
     * 
     */
    public void setFuelRequested(float value) {
        this.fuelRequested = value;
    }

    /**
     * Gets the value of the fuelFinal property.
     * 
     */
    public float getFuelFinal() {
        return fuelFinal;
    }

    /**
     * Sets the value of the fuelFinal property.
     * 
     */
    public void setFuelFinal(float value) {
        this.fuelFinal = value;
    }

    /**
     * Gets the value of the route property.
     * 
     * @return
     *     possible object is
     *     {@link RouteType }
     *     
     */
    public RouteType getRoute() {
        return route;
    }

    /**
     * Sets the value of the route property.
     * 
     * @param value
     *     allowed object is
     *     {@link RouteType }
     *     
     */
    public void setRoute(RouteType value) {
        this.route = value;
    }

}
