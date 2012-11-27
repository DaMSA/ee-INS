//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.25 at 06:07:46 PM PDT 
//


package dk.frv.enav.ins.route.monalisa.se.sspa.optiroute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import dk.frv.enav.ins.route.monalisa.fi.navielektro.ns.formats.vessel_waypoint_exchange.RouteType;


/**
 * Information required by the route optimization in order to calculate the optimal route
 * 
 * <p>Java class for routerequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="routerequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Route" type="{http://www.navielektro.fi/ns/formats/vessel-waypoint-exchange}routeType"/>
 *         &lt;element name="WeatherPoints" type="{http://www.sspa.se/optiroute}WeatherPointsType"/>
 *         &lt;element name="DepthPoints" type="{http://www.sspa.se/optiroute}DepthPointsType"/>
 *         &lt;element name="CurrentShipData" type="{http://www.sspa.se/optiroute}CurrentShipDataType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@XmlRootElement(name = "RouteRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RouteRequest", propOrder = {
    "route",
    "weatherPoints",
    "depthPoints",
    "currentShipData"
})
public class RouteRequest {

    @XmlElement(name = "Route", required = true)
    protected RouteType route;
    @XmlElement(name = "WeatherPoints", required = true)
    protected WeatherPointsType weatherPoints;
    @XmlElement(name = "DepthPoints", required = true)
    protected DepthPointsType depthPoints;
    @XmlElement(name = "CurrentShipData", required = true)
    protected CurrentShipDataType currentShipData;

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

    /**
     * Gets the value of the weatherPoints property.
     * 
     * @return
     *     possible object is
     *     {@link WeatherPointsType }
     *     
     */
    public WeatherPointsType getWeatherPoints() {
        return weatherPoints;
    }

    /**
     * Sets the value of the weatherPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeatherPointsType }
     *     
     */
    public void setWeatherPoints(WeatherPointsType value) {
        this.weatherPoints = value;
    }

    /**
     * Gets the value of the depthPoints property.
     * 
     * @return
     *     possible object is
     *     {@link DepthPointsType }
     *     
     */
    public DepthPointsType getDepthPoints() {
        return depthPoints;
    }

    /**
     * Sets the value of the depthPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link DepthPointsType }
     *     
     */
    public void setDepthPoints(DepthPointsType value) {
        this.depthPoints = value;
    }

    /**
     * Gets the value of the currentShipData property.
     * 
     * @return
     *     possible object is
     *     {@link CurrentShipDataType }
     *     
     */
    public CurrentShipDataType getCurrentShipData() {
        return currentShipData;
    }

    /**
     * Sets the value of the currentShipData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrentShipDataType }
     *     
     */
    public void setCurrentShipData(CurrentShipDataType value) {
        this.currentShipData = value;
    }

}
