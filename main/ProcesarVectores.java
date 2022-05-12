/*
 * Created on 17/01/2005
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package main;

import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import tools.Clasificacion;
import tools.ConverAngulos;
import tools.FormatText;
import conversiones.GeocentricasDirecto;
import conversiones.GeocentricasInversa;

/**
 * @author   david
 */
public class ProcesarVectores extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3617572721551554360L;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JPanel jPanel2 = null;

	private JButton jbCalcular = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JTextField campo1 = null;

	private JTextField campo2 = null;

	private JTextField campo3 = null;

	private JLabel jLabel3 = null;

	private JTextField campo4 = null;

	private JLabel jLabel4 = null;

	private JTextField campo5 = null;

	private JLabel jLabel5 = null;

	private JTextField campo6 = null;

	private JLabel jLabel7 = null;

	private JLabel jLabel8 = null;

	private JLabel jLabel9 = null;

	private JTextField campo8 = null;

	private JTextField campo9 = null;

	private JTextField campo10 = null;

	//varibles de calculo
	private double x = 0 ;
	//varibles de calculo
	private double y = 0 ;
	//varibles de calculo
	private double z = 0 ;

	private double latitudPunto = 0 ;
	private double longitudPunto = 0 ;
	private double altura = 0 ;

	private double Vx = 0 ;
	private double Vy = 0 ;
	private double Vz = 0 ;

	private Date fechaEp1 = null ;
	private Date fechaEp2 = null /*, anio = null*/ ;

	//private double anioActual = 0;

	private DateField datefieldEP1;
	//private double anioActual = 0;

	private DateField datefieldEP2;

	private JPanel jpanel = null;

	//private JPanel jPanel3 = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel10 = null;

	/**
	 * This is the default constructor
	 */
	public ProcesarVectores() {
		super();
		datefieldEP1 = CalendarFactory.createDateField();
		long time = new GregorianCalendar(1995, 04, 31).getTimeInMillis();
		datefieldEP1.setValue(new Date(time));
		datefieldEP2 = CalendarFactory.createDateField();
		datefieldEP1.setSize(65, 20);
		datefieldEP2.setSize(65, 20);
		initialize();
		this.jpanel = this;

	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		this.setLayout(null);
		this.setSize(537, 439);
		gridBagConstraints5.gridx = 0;
		gridBagConstraints5.gridy = 0;
		gridBagConstraints5.ipadx = 220;
		gridBagConstraints5.ipady = 120;
		gridBagConstraints5.insets = new java.awt.Insets(17, 18, 9, 7);
		gridBagConstraints6.gridx = 1;
		gridBagConstraints6.gridy = 0;
		gridBagConstraints6.gridwidth = 2;
		gridBagConstraints6.ipadx = 183;
		gridBagConstraints6.ipady = 120;
		gridBagConstraints6.insets = new java.awt.Insets(17, 10, 9, 17);
		gridBagConstraints7.gridx = 0;
		gridBagConstraints7.gridy = 1;
		gridBagConstraints7.gridwidth = 2;
		gridBagConstraints7.ipadx = 249;
		gridBagConstraints7.ipady = 106;
		gridBagConstraints7.insets = new java.awt.Insets(10, 19, 13, 12);
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 2;
		gridBagConstraints8.gridwidth = 2;
		gridBagConstraints8.ipadx = 7;
		gridBagConstraints8.ipady = -5;
		gridBagConstraints8.insets = new java.awt.Insets(13, 187, 14, 3);
		this.add(getJPanel(), null);
		this.add(getJPanel1(), null);
		this.add(getJPanel2(), null);
		this.add(getJbCalcular(), null);

	}

	/**
	 * This method initializes jPanel
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel"
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel6 = new JLabel();
			jPanel = new JPanel();
			jLabel = new JLabel();
			jLabel1 = new JLabel();
			jLabel2 = new JLabel();
			jPanel.setLayout(null);
			jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
					javax.swing.BorderFactory.createLineBorder(
							java.awt.Color.black, 1),
					"Coordenadas Geogr\u00e1ficas \u00c9poca I",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null,
					null));
			jPanel.setBounds(13, 19, 250, 182);
			jLabel.setText("Latitud");
			jLabel.setBounds(12, 37, 95, 20);
			jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel1.setBounds(12, 72, 95, 20);
			jLabel1.setText("Longitud");
			jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel2.setBounds(13, 107, 95, 20);
			jLabel2.setText("Altura Elipsoidal");
			jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel6.setBounds(13, 142, 95, 20);
			jLabel6.setText("\u00c9poca I");
			jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			datefieldEP1.setBounds(121, 142, 120, 20);
			jPanel.add(datefieldEP1, null);
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel2, null);
			jPanel.add(getCampo1(), null);
			jPanel.add(getCampo2(), null);
			jPanel.add(getCampo3(), null);
			jPanel.add(jLabel6, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel1"
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jLabel3 = new JLabel();
			jLabel4 = new JLabel();
			jLabel5 = new JLabel();
			jPanel1.setLayout(null);
			jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
					javax.swing.BorderFactory.createLineBorder(
							java.awt.Color.black, 1),
					"Velocidades IGAC [m/a\u00f1o]",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null,
					null));
			jPanel1.setBounds(156, 246, 224, 123);
			jLabel3.setBounds(15, 23, 41, 16);
			jLabel3.setText("Vx");
			jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel4.setBounds(15, 55, 41, 16);
			jLabel4.setText("Vy");
			jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel5.setBounds(15, 87, 41, 16);
			jLabel5.setText("Vz");
			jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel1.add(jLabel3, null);
			jPanel1.add(getCampo4(), null);
			jPanel1.add(jLabel4, null);
			jPanel1.add(getCampo5(), null);
			jPanel1.add(jLabel5, null);
			jPanel1.add(getCampo6(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel2"
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel10 = new JLabel();
			jPanel2 = new JPanel();
			jLabel7 = new JLabel();
			jLabel8 = new JLabel();
			jLabel9 = new JLabel();
			jPanel2.setLayout(null);
			jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(
					javax.swing.BorderFactory.createLineBorder(
							java.awt.Color.black, 1),
					"Coordenadas Geogr\u00e1ficas \u00c9poca II",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null,
					null));
			jPanel2.setBounds(272, 19, 250, 182);
			jLabel7.setBounds(12, 37, 95, 20);
			jLabel7.setText("Latitud");
			jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel8.setBounds(12, 72, 95, 20);
			jLabel8.setText("Longitud");
			jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel9.setBounds(13, 107, 95, 20);
			jLabel9.setText("Altura Elipsoidal");
			jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel10.setBounds(13, 142, 95, 20);
			jLabel10.setText("\u00c9poca II");
			jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			datefieldEP2.setBounds(121, 142, 120, 20);
			jPanel2.add(datefieldEP2, null);
			jPanel2.add(jLabel7, null);
			jPanel2.add(jLabel8, null);
			jPanel2.add(jLabel9, null);
			jPanel2.add(getCampo8(), null);
			jPanel2.add(getCampo9(), null);
			jPanel2.add(getCampo10(), null);
			jPanel2.add(jLabel10, null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jbCalcular
	 * @return   javax.swing.JButton
	 * @uml.property   name="jbCalcular"
	 */
	private JButton getJbCalcular() {
		if (jbCalcular == null) {
			jbCalcular = new JButton();
			jbCalcular.setText("Calcular");
			jbCalcular.setBounds(225, 399, 91, 20);
			jbCalcular.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//					valida datos de entrada
					if (!validarDatos()) {
						JOptionPane.showMessageDialog(jpanel,
								"Hay campos vacios", "Error...",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						//captura de datos de entrada
						boolean capt = capturaDatos();
						if (capt == false) {
							return;
						}
						//valida angulos
						if (!validarAngLat(latitudPunto)) {
							JOptionPane.showMessageDialog(jpanel,
									"Ha seleccionado una latitud por fuera del rango\n"
											+ " -5\u00ba a 14\u00ba", "Error...",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (!validarAngLong(longitudPunto)) {
							JOptionPane.showMessageDialog(jpanel,
									"Ha seleccionado una longitud por fuera del rango\n"
											+ " -66\u00ba a -82\u00ba", "Error...",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						//anioActual = 0;

						//conversion de coordenadas Geogr�ficas a Geocentricas
						GeocentricasDirecto geoDirect = new GeocentricasDirecto(
								Clasificacion.TIPO_DATUM.DATUM_MAGNA,
								latitudPunto, longitudPunto, altura);
						x = geoDirect.getX();
						y = geoDirect.getY();
						z = geoDirect.getZ();

						SimpleDateFormat formatoDiasAnio = new SimpleDateFormat(
								"DDD");
						SimpleDateFormat formatoAnio = new SimpleDateFormat(
								"yyyy");

						//determinaci�n de la fecha EP1
						fechaEp1 = (Date) datefieldEP1.getValue();
						fechaEp2 = (Date) datefieldEP2.getValue();

						//determina los dias del anio para cada epoca
						double diasAnioEp1 = Double.valueOf(
								formatoDiasAnio.format(fechaEp1)).doubleValue();
						diasAnioEp1 = diasAnioEp1 / 365d;
						double diasAnioEp2 = Double.valueOf(
								formatoDiasAnio.format(fechaEp2)).doubleValue();
						diasAnioEp2 = diasAnioEp2 / 365d;

						//determinacion de las fechas para cada epoca
						double fechaAnioEP2 = Integer.parseInt(formatoAnio
								.format(fechaEp2));

						double fechaAnioEP1 = Integer.parseInt(formatoAnio
								.format(fechaEp1));

						//formateo de texto
						FormatText ftext = new FormatText();
						diasAnioEp1 = Double.valueOf(
								ftext.formatAnio(diasAnioEp1)).doubleValue();
						diasAnioEp2 = Double.valueOf(
								ftext.formatAnio(diasAnioEp2)).doubleValue();

						fechaAnioEP1 = fechaAnioEP1 + diasAnioEp1;
						fechaAnioEP2 = fechaAnioEP2 + diasAnioEp2;

						double diferenciaAnio;
						boolean mensaje = false;
						if (fechaAnioEP1 < fechaAnioEP2) {
							diferenciaAnio = Math.abs(fechaAnioEP2
									- fechaAnioEP1);
							mensaje = true;
						} else {
							diferenciaAnio = Math.abs(fechaAnioEP1
									- fechaAnioEP2);
							Vx *= -1d;
							Vy *= -1d;
							Vz *= -1d;
						}

						//corregir geocentricas
						Vx = Vx * diferenciaAnio;
						Vy = Vy * diferenciaAnio;
						Vz = Vz * diferenciaAnio;
						x = x + Vx;
						y = y + Vy;
						z = z + Vz;

						//conversion de coordenadas Geocentricas a Geograficas
						GeocentricasInversa geoInvert = new GeocentricasInversa(
								Clasificacion.TIPO_DATUM.DATUM_MAGNA, x, y, z);
						ConverAngulos convAng = new ConverAngulos();
						latitudPunto = convAng.radToGms(geoInvert.getLatitud());
						longitudPunto = convAng.radToGms(geoInvert
								.getLongitud());
						altura = geoInvert.getAlturaElipsoidal();

						//escribe campos
						campo8.setText(ftext
								.formatAnguloVelocidades(latitudPunto));
						campo9.setText(ftext
								.formatAnguloVelocidades(longitudPunto));
						campo10.setText(ftext.formatTextCoord(altura, false));

						if (mensaje) {
							JOptionPane
									.showMessageDialog(
											jpanel,
											"Recuerde que sus coordenadas deben ser\n"
													+ "reducidas nuevamente a la \u00e9poca de referencia\n"
													+ "1995.4",
											"Informaci\u00f3n...",
											JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
		}
		return jbCalcular;
	}

	/**
	 * This method initializes campo1
	 * @return   javax.swing.JTextField
	 * @uml.property   name="campo1"
	 */
	private JTextField getCampo1() {
		if (campo1 == null) {
			campo1 = new JTextField();
			campo1.setBounds(121, 37, 120, 20);
			campo1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			campo1.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					JTextField textField = (JTextField) e.getSource();
					String content = textField.getText();
					if (content.length() != 0) {
						try {
							Float.parseFloat(content);
						} catch (NumberFormatException nfe) {
							getToolkit().beep();
							textField.requestFocus();
						}
					}
				}
			});
			campo1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					campo2.requestFocus();
					campo2.setText("");
				}
			});

		}
		return campo1;
	}

	/**
	 * This method initializes campo2
	 * @return   javax.swing.JTextField
	 * @uml.property   name="campo2"
	 */
	private JTextField getCampo2() {
		if (campo2 == null) {
			campo2 = new JTextField();
			campo2.setBounds(121, 72, 120, 20);
			campo2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			campo2.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					JTextField textField = (JTextField) e.getSource();
					String content = textField.getText();
					if (content.length() != 0) {
						try {
							Float.parseFloat(content);
						} catch (NumberFormatException nfe) {
							getToolkit().beep();
							textField.requestFocus();
						}
					}
				}
			});
			campo2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					campo3.requestFocus();
					campo3.setText("");
				}
			});
		}
		return campo2;
	}

	/**
	 * This method initializes campo3
	 * @return   javax.swing.JTextField
	 * @uml.property   name="campo3"
	 */
	private JTextField getCampo3() {
		if (campo3 == null) {
			campo3 = new JTextField();
			campo3.setBounds(121, 107, 120, 20);
			campo3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			campo3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					campo4.requestFocus();
					campo4.setText("");
				}
			});
			campo3.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					JTextField textField = (JTextField) e.getSource();
					String content = textField.getText();
					if (content.length() != 0) {
						try {
							Float.parseFloat(content);
						} catch (NumberFormatException nfe) {
							getToolkit().beep();
							textField.requestFocus();
						}
					}
				}
			});
		}
		return campo3;
	}

	/**
	 * This method initializes campo4
	 * @return   javax.swing.JTextField
	 * @uml.property   name="campo4"
	 */
	private JTextField getCampo4() {
		if (campo4 == null) {
			campo4 = new JTextField();
			campo4.setBounds(65, 20, 144, 20);
			campo4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			campo4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					campo5.requestFocus();
					campo5.setText("");
				}
			});
			campo4.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					JTextField textField = (JTextField) e.getSource();
					String content = textField.getText();
					if (content.length() != 0) {
						try {
							Float.parseFloat(content);
						} catch (NumberFormatException nfe) {
							getToolkit().beep();
							textField.requestFocus();
						}
					}
				}
			});
		}
		return campo4;
	}

	/**
	 * This method initializes campo5
	 * @return   javax.swing.JTextField
	 * @uml.property   name="campo5"
	 */
	private JTextField getCampo5() {
		if (campo5 == null) {
			campo5 = new JTextField();
			campo5.setBounds(65, 53, 144, 20);
			campo5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			campo5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					campo6.requestFocus();
					campo6.setText("");
				}
			});
			campo5.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					JTextField textField = (JTextField) e.getSource();
					String content = textField.getText();
					if (content.length() != 0) {
						try {
							Float.parseFloat(content);
						} catch (NumberFormatException nfe) {
							getToolkit().beep();
							textField.requestFocus();
						}
					}
				}
			});
		}
		return campo5;
	}

	/**
	 * This method initializes campo6
	 * @return   javax.swing.JTextField
	 * @uml.property   name="campo6"
	 */
	private JTextField getCampo6() {
		if (campo6 == null) {
			campo6 = new JTextField();
			campo6.setBounds(65, 86, 144, 20);
			campo6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			campo6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jbCalcular.requestFocus();
				}
			});
			campo6.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					JTextField textField = (JTextField) e.getSource();
					String content = textField.getText();
					if (content.length() != 0) {
						try {
							Float.parseFloat(content);
						} catch (NumberFormatException nfe) {
							getToolkit().beep();
							textField.requestFocus();
						}
					}
				}
			});
		}
		return campo6;
	}

	/**
	 * This method initializes campo8
	 * @return   javax.swing.JTextField
	 * @uml.property   name="campo8"
	 */
	private JTextField getCampo8() {
		if (campo8 == null) {
			campo8 = new JTextField();
			campo8.setBounds(121, 37, 120, 20);
			campo8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			campo8.setEditable(false);
			campo8.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
		}
		return campo8;
	}

	/**
	 * This method initializes campo9
	 * @return   javax.swing.JTextField
	 * @uml.property   name="campo9"
	 */
	private JTextField getCampo9() {
		if (campo9 == null) {
			campo9 = new JTextField();
			campo9.setBounds(121, 72, 120, 20);
			campo9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			campo9.setEditable(false);
			campo9.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
		}
		return campo9;
	}

	/**
	 * This method initializes campo10
	 * @return   javax.swing.JTextField
	 * @uml.property   name="campo10"
	 */
	private JTextField getCampo10() {
		if (campo10 == null) {
			campo10 = new JTextField();
			campo10.setBounds(121, 107, 120, 20);
			campo10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			campo10.setEditable(false);
			campo10.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
		}
		return campo10;
	}

	/**
	 * M&eacute;todo que valida los campos y sus contenidos
	 *  
	 */
	private boolean validarDatos() {
		boolean valido = false;
		if (!campo1.getText().equalsIgnoreCase(""))
			valido = true;
		else
			return false;
		if (!campo2.getText().equalsIgnoreCase(""))
			valido = true;
		else
			return false;
		if (!campo3.getText().equalsIgnoreCase(""))
			valido = true;
		else
			return false;
		if (!campo4.getText().equalsIgnoreCase(""))
			valido = true;
		else
			return false;
		if (!campo5.getText().equalsIgnoreCase(""))
			valido = true;
		else
			return false;
		if (!campo6.getText().equalsIgnoreCase(""))
			valido = true;
		else
			return false;

		return valido;
	}

	/**
	 * M&eacute;todo que inicializa las variables de c&aacute;lculo
	 *  
	 */
	private boolean capturaDatos() {
		ConverAngulos convAng = new ConverAngulos();
		latitudPunto = convAng.gmsToRad(Double.valueOf(campo1.getText())
				.doubleValue());
		if (latitudPunto == 0) {
			JOptionPane.showMessageDialog(this, "La latitud es incorrecta\n"
					+ " verifique el formato GG.MMSSSSS", "Error...",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		longitudPunto = convAng.gmsToRad(Double.valueOf(campo2.getText())
				.doubleValue());
		if (longitudPunto == 0) {
			JOptionPane.showMessageDialog(this, "La longitud es incorrecta\n"
					+ " verifique el formato -GG.MMSSSS", "Error...",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		longitudPunto = (longitudPunto < 0 ? longitudPunto : longitudPunto * -1);
		altura = Double.valueOf(campo3.getText()).doubleValue();
		Vx = Double.valueOf(campo4.getText()).doubleValue();
		Vy = Double.valueOf(campo5.getText()).doubleValue();
		Vz = Double.valueOf(campo6.getText()).doubleValue();
		//anio = (Date) datefieldEP1.getValue();
		return true;
	}

	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("Correcci\u00f3n de Velocidades");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		ProcesarVectores newContentPane = new ProcesarVectores();
		newContentPane.setOpaque(true); //content panes must be opaque
		frame.setContentPane(newContentPane);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * valida el &aacute;ngulo de entrada
	 * 
	 * @param angulo en radianes
	 * @return si el angulo es valido true, si no false
	 */
	private boolean validarAngLong(double angulo) {
		boolean valido = false;
		if ((angulo >= -1.4311699866353502) && (angulo <= -1.1519173063162575)) {
			valido = true;
		} else {
			valido = false;
		}
		return valido;
	}

	private boolean validarAngLat(double angulo) {
		boolean valido = false;
		if ((angulo >= -0.08726646259971647) && (angulo <= 0.24434609527920614)) {
			valido = true;
		} else {
			valido = false;
		}
		return valido;
	}

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
} //  @jve:decl-index=0:visual-constraint="10,10"
