package screens;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import panels.PrintPanel;
import panels.PrintPanel.PrintTable;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class ControlScreen extends JPanel {
    FileTable fileTable;
    PrintPanel printPanel;
    String folderPath;
    //    private PrintService selectedPrint;
//    private Vector<PrintService> availablePrinters;
    public ControlScreen() {
        setLayout(new BorderLayout());

        fileTable = new FileTable();
        JScrollPane scrollPane = new JScrollPane(fileTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JButton folderButton = new JButton("폴더 경로 수정");
        folderButton.addActionListener(e -> selectFolderPath());

        JButton printButton = new JButton("출력");
        printButton.addActionListener(e -> printFileWithPDFBox());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(folderButton);
        buttonsPanel.add(printButton);

        this.add(buttonsPanel, BorderLayout.NORTH);

        printPanel = new PrintPanel();
        this.add(printPanel, BorderLayout.SOUTH);

    }
    private void selectFolderPath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            folderPath = fileChooser.getSelectedFile().getPath();
            loadPDFFilesFromFolder(folderPath);
        }
    }
    private void loadPDFFilesFromFolder(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        DefaultTableModel model = (DefaultTableModel) fileTable.getModel();
        model.setRowCount(0); // Clear previous rows

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                model.addRow(new Object[]{i+1, listOfFiles[i].getName(), "??", "??", "??"});  // 컬러/흑백, 출력시간, 상태 정보는 모르므로 ?? 로 표시했습니다.
            }
        }
    }
    private void printFile() {
        if (folderPath == null || fileTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "파일 또는 프린터를 선택해주세요.");
            return;
        }

        File selectedFile = new File(folderPath + "/" + fileTable.getValueAt(fileTable.getSelectedRow(), 1).toString());
        PrintService printer = printPanel.selectedPrinter;

        if (printer != null && selectedFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(selectedFile);
                Doc doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
                DocPrintJob printJob = printer.createPrintJob();
                PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
                printJob.print(doc, attributes);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "파일 출력 중 오류가 발생했습니다.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "올바른 프린터 또는 파일을 선택해주세요.");
        }
    }
    private void printFileWithPDFBox() {
        if (folderPath == null || fileTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "파일 또는 프린터를 선택해주세요.");
            return;
        }

        File selectedFile = new File(folderPath + "/" + fileTable.getValueAt(fileTable.getSelectedRow(), 1).toString());
        PrintService printer = printPanel.selectedPrinter;

        if (printer != null && selectedFile.exists()) {
            try {
                PDDocument document = Loader.loadPDF(selectedFile);
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintService(printer);
                job.setPageable(new PDFPageable(document));
                job.print();
                document.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "파일 출력 중 오류가 발생했습니다.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "올바른 프린터 또는 파일을 선택해주세요.");
        }
    }

    public class FileTable extends JTable {
        private DefaultTableModel tableModel;
        private String[] headers = {"번호", "파일명", "컬러/흑백", "출력시간", "상태"};

        public FileTable() {
            Vector<String> header = createHeader(headers);


            tableModel = new DefaultTableModel(headers, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells not editable
                }
            };
            this.setModel(tableModel);


        }

        public Vector<String> createHeader(String[] inputList) { // create Table Header
            Vector<String> outputList = new Vector<String>();
            Collections.addAll(outputList, inputList);
            return outputList;
        }
    }


}
