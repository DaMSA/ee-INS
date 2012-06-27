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
package dk.frv.enav.ins.gui.sensors;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * GPS panel in sensor panel
 */
public class GPSPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel gpsTitleLabel = new JLabel("GPS");
	private JLabel latTitleLabel = new JLabel("LAT");
	private JLabel latLabel = new JLabel("N/A");
	private JLabel lonTitleLabel = new JLabel("LON");
	private JLabel lonLabel = new JLabel("N/A");
	private JLabel sogTitleLabel = new JLabel("SOG");
	private JLabel sogLabel = new JLabel("N/A");
	private JLabel cogLabel = new JLabel("N/A");
	private JLabel cogTitleLabel = new JLabel("COG");
	private JLabel hdgLabel = new JLabel("N/A");
	private JLabel hdgTitleLabel = new JLabel("HDG");
	
	public GPSPanel() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 25, 153, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 16, 16, 16, 16, 16, 10 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);
		gpsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gpsTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		GridBagConstraints gbc_gpsTitleLabel = new GridBagConstraints();
		gbc_gpsTitleLabel.anchor = GridBagConstraints.NORTH;
		gbc_gpsTitleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_gpsTitleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_gpsTitleLabel.gridwidth = 2;
		gbc_gpsTitleLabel.gridx = 0;
		gbc_gpsTitleLabel.gridy = 0;
		add(gpsTitleLabel, gbc_gpsTitleLabel);

		latTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		latTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_latTitleLabel = new GridBagConstraints();
		gbc_latTitleLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_latTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_latTitleLabel.gridx = 0;
		gbc_latTitleLabel.gridy = 1;
		add(latTitleLabel, gbc_latTitleLabel);

		latLabel.setHorizontalAlignment(SwingConstants.CENTER);
		latLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_latLabel = new GridBagConstraints();
		gbc_latLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_latLabel.insets = new Insets(0, 0, 5, 0);
		gbc_latLabel.gridx = 1;
		gbc_latLabel.gridy = 1;
		add(latLabel, gbc_latLabel);

		lonTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lonTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lonTitleLabel = new GridBagConstraints();
		gbc_lonTitleLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lonTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lonTitleLabel.gridx = 0;
		gbc_lonTitleLabel.gridy = 2;
		add(lonTitleLabel, gbc_lonTitleLabel);

		lonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lonLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lonLabel = new GridBagConstraints();
		gbc_lonLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lonLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lonLabel.gridx = 1;
		gbc_lonLabel.gridy = 2;
		add(lonLabel, gbc_lonLabel);

		sogTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		sogTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_sogTitleLabel = new GridBagConstraints();
		gbc_sogTitleLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_sogTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sogTitleLabel.gridx = 0;
		gbc_sogTitleLabel.gridy = 3;
		add(sogTitleLabel, gbc_sogTitleLabel);

		sogLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		sogLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_sogLabel = new GridBagConstraints();
		gbc_sogLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_sogLabel.insets = new Insets(0, 0, 5, 0);
		gbc_sogLabel.gridx = 1;
		gbc_sogLabel.gridy = 3;
		add(sogLabel, gbc_sogLabel);

		cogTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cogTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_cogTitleLabel = new GridBagConstraints();
		gbc_cogTitleLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_cogTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cogTitleLabel.gridx = 0;
		gbc_cogTitleLabel.gridy = 4;
		add(cogTitleLabel, gbc_cogTitleLabel);

		cogLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cogLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_cogLabel = new GridBagConstraints();
		gbc_cogLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_cogLabel.insets = new Insets(0, 0, 5, 0);
		gbc_cogLabel.gridx = 1;
		gbc_cogLabel.gridy = 4;
		add(cogLabel, gbc_cogLabel);

		hdgTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		hdgTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_hdgTitleLabel = new GridBagConstraints();
		gbc_hdgTitleLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_hdgTitleLabel.insets = new Insets(0, 0, 0, 5);
		gbc_hdgTitleLabel.gridx = 0;
		gbc_hdgTitleLabel.gridy = 5;
		add(hdgTitleLabel, gbc_hdgTitleLabel);

		hdgLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		hdgLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_hdgLabel = new GridBagConstraints();
		gbc_hdgLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_hdgLabel.gridx = 1;
		gbc_hdgLabel.gridy = 5;
		add(hdgLabel, gbc_hdgLabel);
	}
	
	public JLabel getLatLabel() {
		return latLabel;
	}
	
	public JLabel getLonLabel() {
		return lonLabel;
	}
	
	public JLabel getSogLabel() {
		return sogLabel;
	}
	
	public JLabel getCogLabel() {
		return cogLabel;
	}
	
	public JLabel getHdgLabel() {
		return hdgLabel;
	}
}
