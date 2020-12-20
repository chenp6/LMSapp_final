package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import LMSapp.LMSapp;

public class MyClassPanel extends JPanel {

	/**
	 我的課程(學生)
	 */
	public MyClassPanel() {
		setLayout(null);
		setBounds(0,30, 916, 800);
		JLabel title = new JLabel("我的課程");
		title.setFont(new Font("微軟正黑體", Font.PLAIN, 60));
		title.setBounds(334, 10, 240, 106);
		add(title);
		JComboBox semesterComboInMyClass = new JComboBox(new Object[] { "請選擇", "107-2", "108-1", "108-2", "109-1" });
		JLabel semesterLabelInMyClass = new JLabel("學期");
		DefaultTableModel tableMInStudentScore;
		tableMInStudentScore = new DefaultTableModel(null, new String[] { "學期", "課程代碼", "課程名稱", "學分", "必/選修", "成績" });
		JTable courseTableInMyClass = new JTable(tableMInStudentScore);
		JScrollPane coursePaneInMyClass = new JScrollPane(courseTableInMyClass);
		TableColumn columnInMyClass = null;
		for (int i = 0; i < 5; i++) {
			columnInMyClass = courseTableInMyClass.getColumnModel().getColumn(i);
			if (i == 2) {
				columnInMyClass.setPreferredWidth(300); // third column is bigger
			} else {
				columnInMyClass.setPreferredWidth(20);
			}
		}
		JButton printTranscriptBtn = new JButton("列印總成績單");
		printTranscriptBtn.setBounds(319, 98, 271, 31);
		add(printTranscriptBtn);
		semesterComboInMyClass.setBounds(368, 139, 120, 40);
		semesterLabelInMyClass.setBounds(498, 144, 100, 30);
		coursePaneInMyClass.setBounds(0, 200, 900, 700);
		add(semesterComboInMyClass);
		add(semesterLabelInMyClass);
		add(coursePaneInMyClass);
		
		semesterComboInMyClass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedSemester = (String) semesterComboInMyClass.getSelectedItem();
				FileReader fr;
				try {
					fr = new FileReader("data/課程資料.txt");
					BufferedReader br = new BufferedReader(fr);
					cleanTable(tableMInStudentScore);
					try {
						while (br.ready()) {
							String[] courseInfo = br.readLine().split(" ");
							if (courseInfo[0].equals(selectedSemester)) {
								List studentList = (List) Arrays.asList(courseInfo[7].split(","));
								int studentIndex = studentList.indexOf(LMSapp.userAccount.account);
								String[] studentScore = courseInfo[8].split(",");
								if (studentIndex != -1) {
									Object[] temp = { courseInfo[0], courseInfo[1], courseInfo[2], courseInfo[3],
											courseInfo[5], studentScore[studentIndex] };
									tableMInStudentScore.addRow(temp);
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					add(coursePaneInMyClass);
					fr.close();
				} catch (FileNotFoundException e1) {
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});
		printTranscriptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = "";
				JFileChooser fileChoice = new JFileChooser();
				fileChoice.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int approve = fileChoice.showSaveDialog(null);
				if (approve == JFileChooser.APPROVE_OPTION)
					path = fileChoice.getSelectedFile().getPath();
				Document document = new Document();
				try {
					BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,
							BaseFont.NOT_EMBEDDED);
					com.itextpdf.text.Font chinessFont = new com.itextpdf.text.Font(baseFont);
					PdfWriter.getInstance(document,
							new FileOutputStream(path + LMSapp.userAccount.account +  LMSapp.userAccount.name + "的成績單.pdf"));
					document.open();
					document.add(new Paragraph( LMSapp.userAccount.account +  LMSapp.userAccount.name + "的成績單\n\n", chinessFont));
					PdfPTable scoreTable = new PdfPTable(6);
					scoreTable.setWidths(new float[] { 10f, 15f, 50f, 10f, 13f, 10f });
					scoreTable.addCell(new PdfPCell(new Paragraph("學期", chinessFont)));
					scoreTable.addCell(new PdfPCell(new Paragraph("課程代碼", chinessFont)));
					scoreTable.addCell(new PdfPCell(new Paragraph("課程名稱", chinessFont)));
					scoreTable.addCell(new PdfPCell(new Paragraph("學分", chinessFont)));
					scoreTable.addCell(new PdfPCell(new Paragraph("必/選修", chinessFont)));
					scoreTable.addCell(new PdfPCell(new Paragraph("成績", chinessFont)));
					FileReader fr;
					try {
						fr = new FileReader("data/課程資料.txt");
					} catch (FileNotFoundException e1) {
						return;
					}
					BufferedReader br = new BufferedReader(fr);
					try {
						ArrayList<ArrayList<String>> studentCourseInfo = new ArrayList<ArrayList<String>>();
						for (int i = 0; i < 4; i++)
							studentCourseInfo.add(new ArrayList());
						while (br.ready()) {
							String[] courseInfo = br.readLine().split(" ");
							List studentList = (List) Arrays.asList(courseInfo[7].split(","));
							int studentIndex = studentList.indexOf( LMSapp.userAccount.account);
							String[] studentScore = courseInfo[8].split(",");
							if (studentIndex != -1) {
								scoreTable.addCell(new PdfPCell(new Paragraph(courseInfo[0])));
								scoreTable.addCell(new PdfPCell(new Paragraph(courseInfo[1])));
								scoreTable.addCell(new PdfPCell(new Paragraph(courseInfo[2], chinessFont)));
								scoreTable.addCell(new PdfPCell(new Paragraph(courseInfo[3])));
								scoreTable.addCell(new PdfPCell(new Paragraph(courseInfo[5], chinessFont)));
								scoreTable.addCell(new PdfPCell(new Paragraph(studentScore[studentIndex])));
							}
						}
						fr.close();
						document.add(scoreTable);
						document.close();
					} catch (IOException e3) {
					}

				} catch (FileNotFoundException | DocumentException e) {
				} catch (IOException e) {
				}
			}
		});
	}
	static void cleanTable(DefaultTableModel tableM) {
		while (tableM.getRowCount() > 0)
			tableM.removeRow(0);
	}
}
