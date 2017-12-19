package com.github.justudin.mlp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;

import org.jfree.ui.RefineryUtilities;

import java.awt.TextField;

public class FrontGUI {

	private JFrame frmBackPropagationAlgorithm;
	String acc;
	private JTextField txtTrainingData;
	private JTextField txtTargetOutput;
	private JTextField txtTestingData;
	private JTextField txtTargetTestingData;
	// Format decimal
	DecimalFormat decformat = new DecimalFormat("####0.0000000000");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontGUI window = new FrontGUI();
					window.frmBackPropagationAlgorithm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrontGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBackPropagationAlgorithm = new JFrame();
		frmBackPropagationAlgorithm.setTitle("Back Propagation Algorithm");
		frmBackPropagationAlgorithm.setBounds(100, 100, 631, 479);
		frmBackPropagationAlgorithm
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBackPropagationAlgorithm.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(175, 316, 379, 85);
		frmBackPropagationAlgorithm.getContentPane().add(scrollPane);

		final JTextArea textAreaResult = new JTextArea();
		scrollPane.setViewportView(textAreaResult);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(175, 117, 379, 96);
		frmBackPropagationAlgorithm.getContentPane().add(scrollPane_1);
		final JTextArea textAreaRes = new JTextArea();
		scrollPane_1.setViewportView(textAreaRes);

		JButton btnResult = new JButton("Training and Testing the network");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textAreaResult.setText("");
				textAreaRes.setText("");
				// start

				//float[][] train = new float[249][19];
				float[][] train = new float[20][18];
				InputStream in = null;
				try {
					// String ins = txtIn.getText().toString().trim();
					String ins = txtTrainingData.getText().toString().trim();
					in = new FileInputStream(new File(ins));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(new JFrame(),
							"Please insert right name of input data", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in));

				String line;
				int i = 0;
				try {
					while ((line = reader.readLine()) != null) {
						// System.out.println(line);

						String temp[];
						String delimiter = ",";
						temp = line.split(delimiter);
						int z = 0;
						for (int j = 0; j < temp.length; j++) {
							// System.out.print("["+i+"]["+z+"] is "+temp[j]+" ");
							// System.out.println(temp[j]);

							train[i][z] = Float.parseFloat(temp[j]);
							z++;
						}
						// System.out.println();
						i++;
					}
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(new JFrame(),
							"Error reading file", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				try {
					reader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(new JFrame(),
							"Error reading file", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				//float[][] res = new float[249][1];
				//float out[] = new float[249];
				float[][] res = new float[20][1];
				float out[] = new float[20];
				InputStream in1 = null;
				try {
					// String soo = txtOut.getText().toString().trim();
					String soo = txtTargetOutput.getText().toString().trim();
					in1 = new FileInputStream(new File(soo));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(new JFrame(),
							"Please insert correct output class data", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				BufferedReader reader1 = new BufferedReader(
						new InputStreamReader(in1));

				String line1;
				int i1 = 0;
				try {
					while ((line1 = reader1.readLine()) != null) {
						// System.out.println(line1);

						res[i1][0] = Float.parseFloat(line1);
						out[i1] = Float.parseFloat(line1);
						i1++;
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane
							.showMessageDialog(new JFrame(), "error reading",
									"Error", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane
							.showMessageDialog(new JFrame(), "error reading",
									"Error", JOptionPane.ERROR_MESSAGE);
				}
				try {
					reader1.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane
							.showMessageDialog(new JFrame(), "Error reading",
									"Error", JOptionPane.ERROR_MESSAGE);
				}

				// float[][] train = new float[][]{new
				// float[]{25,1,49,4,1,0,0,1,0,0}, new float[]{3,1,0,0,1,0,0},
				// new float[]{1,1,0,0,0,0,0}, new float[]{4,2,0,0,0,0,0}};
				// float[][] testing = new float[][]{new
				// float[]{50,24,22,1,3,0,0,0,0,0}, new
				// float[]{35,10,81,3,2,104,0,0,0,1}};
				// float[][] res = new float[][]{new float[]{0}, new float[]{0},
				// new float[]{0}, new float[]{1}};

				//MLP mlp = new MLP(19, new int[] { 18, 1 });
				MLP mlp = new MLP(18, new int[] { 18, 1 });
				mlp.getLayer(1).setIsSigmoid(false);
				Random r = new Random();
				int en = 500;
				//float out2[] = new float[249];
				float out2[] = new float[20];
				for (int e = 0; e < en; e++) {

					for (int i111 = 0; i111 < res.length; i111++) {
						int idx = r.nextInt(res.length);
						mlp.train(train[idx], res[idx], 0.3f, 0.6f);
					}

					if ((e + 1) % 1 == 0) {
						System.out.println();
						for (int i111 = 0; i111 < res.length; i111++) {
							float[] t = train[i111];
							System.out.printf("%d epoch\n", e + 1);
							textAreaRes.append("epoch " + e + "\n");
							System.out
									.printf("%.1f, %.1f, %.1f,%.1f, %.1f, %.1f,%.1f --> %.3f\n",
											t[0], t[1], t[2], t[3], t[4], t[5],
											t[6], mlp.run(t)[0]);

							textAreaRes.append("output result " + mlp.run(t)[0]
									+ "\n");
							out2[i111] = mlp.run(t)[0];
						}
					}
				}

				// Plot Errors
				PlotErrors chart;
				try {
					chart = new PlotErrors("Sum Square Errors",
							"# of Errors vs Sum Square Errors", mlp.getErrorsData());
					chart.pack();
					RefineryUtilities.centerFrameOnScreen(chart);
					chart.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				textAreaResult.append("output \t prediction \t decision \n");
				textAreaResult.append("__________ \t ___________ \n");
				System.out.println("output \t prediction");
				System.out.println("__________ \t ___________");
				int tot = 0;
				for (int h = 0; h < out2.length; h++) {
					String dec = "wrong";
					if ((out[h] < 0.5) & (out2[h] < 0.5)) {
						dec = "right";
						tot++;
					} else if ((out[h] >= 0.5) & (out2[h] >= 0.5)) {
						dec = "right";
						tot++;
					} else if ((out[h] >= 0.5) & (out2[h] < 0.5)) {
						dec = "wrong";
					} else if ((out[h] < 0.5) & (out2[h] >= 0.5)) {
						dec = "wrong";
					}
					System.out
							.println(out[h] + " \t " + decformat.format(out2[h]) + " \t " + dec);
					double rd = Math.round(out2[h] * 100.0) / 100.0;
					textAreaResult.append(out[h] + " \t " + decformat.format(out2[h]) + " \t "
							+ dec + "\n");
				}

				float bawah = out.length;
				float avg = tot / bawah;
				System.out.println("Accuracy " + avg);
				acc = "Accuracy is" + avg;
				textAreaResult.append("Accuracy " + avg + "\n");
				// end

			}
		});

		btnResult.setBounds(175, 83, 281, 23);
		frmBackPropagationAlgorithm.getContentPane().add(btnResult);

		/*
		 * JButton btnGraph = new JButton("Graph");
		 * btnGraph.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { Graph t = new Graph(acc,""); }
		 * }); btnGraph.setBounds(125, 370, 185, 23);
		 * frame.getContentPane().add(btnGraph);
		 */

		JLabel lblTrainingData = new JLabel("Training data:");
		lblTrainingData.setBounds(22, 24, 83, 14);
		frmBackPropagationAlgorithm.getContentPane().add(lblTrainingData);

		txtTrainingData = new JTextField();
		txtTrainingData.setBounds(175, 20, 281, 23);
		frmBackPropagationAlgorithm.getContentPane().add(txtTrainingData);
		txtTrainingData.setColumns(10);

		JButton btnChooseFileTraining = new JButton("Choose File");
		btnChooseFileTraining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(fileChooser
						.getFileSystemView().getDefaultDirectory().toString()));
				int result = fileChooser.showOpenDialog(fileChooser);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					txtTrainingData.setText(selectedFile.getAbsolutePath());
					System.out.println("Selected file: "
							+ selectedFile.getAbsolutePath());
				}
			}
		});

		btnChooseFileTraining.setBounds(466, 20, 111, 23);
		frmBackPropagationAlgorithm.getContentPane().add(btnChooseFileTraining);

		JLabel lblTargetOutputData = new JLabel("Target output data:");
		lblTargetOutputData.setBounds(22, 53, 111, 14);
		frmBackPropagationAlgorithm.getContentPane().add(lblTargetOutputData);

		txtTargetOutput = new JTextField();
		txtTargetOutput.setColumns(10);
		txtTargetOutput.setBounds(175, 49, 281, 23);
		frmBackPropagationAlgorithm.getContentPane().add(txtTargetOutput);

		JButton btnTargetOutput = new JButton("Choose File");
		btnTargetOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(fileChooser
						.getFileSystemView().getDefaultDirectory().toString()));
				int result = fileChooser.showOpenDialog(fileChooser);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					txtTargetOutput.setText(selectedFile.getAbsolutePath());
					System.out.println("Selected file: "
							+ selectedFile.getAbsolutePath());
				}
			}
		});
		btnTargetOutput.setBounds(466, 49, 111, 23);
		frmBackPropagationAlgorithm.getContentPane().add(btnTargetOutput);

		JLabel lblResult = new JLabel("Training log:");
		lblResult.setBounds(22, 124, 103, 14);
		frmBackPropagationAlgorithm.getContentPane().add(lblResult);

		JLabel lblResults = new JLabel("Testing Results:");
		lblResults.setBounds(22, 316, 127, 14);
		frmBackPropagationAlgorithm.getContentPane().add(lblResults);

	}
}
