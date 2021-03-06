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
package dk.frv.enav.ins.gui.nogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import dk.frv.ais.geo.GeoLocation;
import dk.frv.enav.ins.ais.AisHandler;
import dk.frv.enav.ins.common.text.Formatter;
import dk.frv.enav.ins.gui.ChartPanel;
import dk.frv.enav.ins.gui.MainFrame;
import dk.frv.enav.ins.nogo.NogoHandler;

/**
 * The nogo dialog
 */
public class NogoDialog extends JDialog implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	private JButton requestNogo;
	private JButton cancelButton;
	private JButton btnSelectArea;
	JSpinner spinnerDraught;
	JSpinner spinnerTimeStart;
	JSpinner spinnerTimeEnd;

	JLabel nwPtlbl;
	JLabel nePtlbl;
	JLabel swPtlbl;
	JLabel sePtlbl;

	ChartPanel chartPanel;
	NogoHandler nogoHandler;
	MainFrame mainFrame;

	GeoLocation northWestPoint = null;
	GeoLocation southEastPoint = null;

	@SuppressWarnings("deprecation")
	public NogoDialog(JFrame parent, NogoHandler nogoHandler, AisHandler aisHandler) {
		super(parent, "Request Nogo", true);

		mainFrame = (MainFrame) parent;

		this.chartPanel = mainFrame.getChartPanel();
		this.nogoHandler = nogoHandler;

		setSize(384, 412);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		setResizable(false);

		JPanel contentPanel = new JPanel();

		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Area Selection", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(15, 30, 327, 115);

		JLabel lblNogoRequest = new JLabel("Nogo Request:");
		lblNogoRequest.setBounds(15, 5, 81, 14);
		lblNogoRequest.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPanel.setLayout(null);
		panel.setLayout(null);
		contentPanel.add(panel);

		btnSelectArea = new JButton("Select Area");
		btnSelectArea.setBounds(204, 81, 113, 23);
		panel.add(btnSelectArea);
		btnSelectArea.addActionListener(this);

		nwPtlbl = new JLabel("Select the area you want to request NoGo for");
		nwPtlbl.setBounds(10, 26, 300, 14);
		panel.add(nwPtlbl);

		nePtlbl = new JLabel("");
		nePtlbl.setBounds(176, 26, 141, 14);
		panel.add(nePtlbl);

		swPtlbl = new JLabel("");
		swPtlbl.setBounds(10, 51, 134, 14);
		panel.add(swPtlbl);

		sePtlbl = new JLabel("");
		sePtlbl.setBounds(176, 51, 127, 14);
		panel.add(sePtlbl);
		contentPanel.add(lblNogoRequest);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Time Selected", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(15, 156, 327, 94);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNogoBetween = new JLabel("NoGo valid between:");
		lblNogoBetween.setBounds(10, 24, 137, 14);
		panel_1.add(lblNogoBetween);

		Date date = new Date();
		date.setMinutes(0);
		date.setSeconds(0);

		Date date48hour = date;

		Calendar c = Calendar.getInstance();
		c.setTime(date48hour);
		c.add(Calendar.DATE, 2); // number of days to add
		date48hour = c.getTime();

		spinnerTimeStart = new JSpinner();
		spinnerTimeEnd = new JSpinner();

		spinnerTimeStart.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				// System.out.println("timeStart is :" + ((Date)
				// spinnerTimeStart.getValue()));
				// System.out.println("timeEnd is :" + ((Date)
				// spinnerTimeEnd.getValue()));

				if (((Date) spinnerTimeEnd.getValue()).getTime() < ((Date) spinnerTimeStart.getValue()).getTime()) {

					// Update timespinnerend with timespinnerstart
					spinnerTimeEnd.setValue(spinnerTimeStart.getValue());

					//
					// System.out.println("Update timeEnd");
				}

			}
		});

		spinnerTimeStart.setModel(new SpinnerDateModel(date, null, date48hour, Calendar.HOUR));
		spinnerTimeStart.setBounds(10, 41, 98, 20);

		spinnerTimeEnd.setModel(new SpinnerDateModel(date, null, date48hour, Calendar.HOUR));
		spinnerTimeEnd.setBounds(10, 64, 98, 20);

		// spinnerTimeStart.setEditor(new JSpinner.DateEditor(spinnerTimeStart,
		// "HH-mm - dd:MM"));

		panel_1.add(spinnerTimeStart);
		panel_1.add(spinnerTimeEnd);

		spinnerTimeStart.setEditor(new JSpinner.DateEditor(spinnerTimeStart, "HH:mm-dd-MM-yy"));
		spinnerTimeEnd.setEditor(new JSpinner.DateEditor(spinnerTimeEnd, "HH:mm-dd-MM-yy"));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Depth", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(15, 259, 327, 59);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Current depth in meters: ");
		lblNewLabel.setBounds(12, 26, 139, 16);
		panel_2.add(lblNewLabel);

		spinnerDraught = new JSpinner();
		spinnerDraught.setModel(new SpinnerNumberModel(new Integer(5), new Integer(0), null, new Integer(1)));
		spinnerDraught.setBounds(151, 24, 38, 20);

		JFormattedTextField txt = ((JSpinner.NumberEditor) spinnerDraught.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);

		panel_2.add(spinnerDraught);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				requestNogo = new JButton("Request NoGo");
				requestNogo.addActionListener(this);

				buttonPane.add(requestNogo);
				getRootPane().setDefaultButton(requestNogo);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}

		if (aisHandler != null && aisHandler.getOwnShip().getStaticData() != null) {
			Integer draught = ((int) aisHandler.getOwnShip().getStaticData().getDraught() / 10) + 2;
			spinnerDraught.setValue(draught);
		}

	}

	public void setSelectedArea(Point2D[] points) {

		// Find out what is the max of the selection made
		if (points[0].getY() > points[1].getY()) {
			// points 0 is the top left
			northWestPoint = new GeoLocation(points[0].getY(), points[0].getX());
			southEastPoint = new GeoLocation(points[1].getY(), points[1].getX());
		} else {
			northWestPoint = new GeoLocation(points[1].getY(), points[1].getX());
			southEastPoint = new GeoLocation(points[0].getY(), points[0].getX());
		}

		nwPtlbl.setText(Formatter.latToPrintable(northWestPoint.getLatitude())
				+ Formatter.lonToPrintable(northWestPoint.getLongitude()));

		nePtlbl.setText(Formatter.latToPrintable(southEastPoint.getLatitude())
				+ Formatter.lonToPrintable(northWestPoint.getLongitude()));

		swPtlbl.setText(Formatter.latToPrintable(northWestPoint.getLatitude())
				+ Formatter.lonToPrintable(southEastPoint.getLongitude()));

		sePtlbl.setText(Formatter.latToPrintable(southEastPoint.getLatitude())
				+ Formatter.lonToPrintable(southEastPoint.getLongitude()));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == requestNogo) {
			// Send off the request
			if (northWestPoint != null & southEastPoint != null) {

				double westEastDistance = northWestPoint.getRhumbLineDistance(new GeoLocation(northWestPoint
						.getLatitude(), southEastPoint.getLongitude()));
				double northSouthDistance = northWestPoint.getRhumbLineDistance(new GeoLocation(southEastPoint
						.getLatitude(), northWestPoint.getLongitude()));

				// 1300000000
				// 1300000				
				if ( (westEastDistance * northSouthDistance)/1000 < 1300000) {

					this.setVisible(false);
					nogoHandler.setNorthWestPoint(northWestPoint);
					nogoHandler.setSouthEastPoint(southEastPoint);
					double draught = ((Integer) spinnerDraught.getValue()).doubleValue();
					nogoHandler.setDraught(draught);
					nogoHandler.setValidFrom((Date) spinnerTimeStart.getValue());
					nogoHandler.setValidTo((Date) spinnerTimeEnd.getValue());

					if (mainFrame != null) {
						mainFrame.getTopPanel().activateNogoButton();
					}
					(new Thread(this)).start();
				} else {
					nwPtlbl.setText("The area you have selected is too big");
					nePtlbl.setText("");
					swPtlbl.setText("");
					sePtlbl.setText("");
				}
			} else {
				nwPtlbl.setText("You must select an area");
			}

		}
		if (e.getSource() == cancelButton) {
			// Cancel the request
			this.dispose();
		}
		if (e.getSource() == btnSelectArea) {
			// Make a selection on the chartmap
			this.setVisible(false);
			chartPanel.setNogoDialog(this);
			chartPanel.setNogoMode(true);
		}
	}

	@Override
	public void run() {
		nogoHandler.updateNogo();
		this.dispose();
	}

}
