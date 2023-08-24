package panels;

import screens.ControlScreen;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import java.awt.event.ActionListener;

public class PrintPanel extends JPanel {
    public PrintTable printTable;
    public PrintService selectedPrinter;

    public PrintPanel() {
        printTable = new PrintTable();
        JScrollPane scrollPane = new JScrollPane(printTable);
        this.add(scrollPane);

        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(e -> {
            int selectedRow = printTable.getSelectedRow();
            if (selectedRow != -1) {
                selectedPrinter = printTable.printers.get(selectedRow);
                JOptionPane.showMessageDialog(null, selectedPrinter + " 가 선택되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "프린터를 선택해주세요.");
            }
        });
        this.add(confirmButton);
    }

    public static class PrintTable extends JTable {
        private DefaultTableModel tableModel;
        private String[] headers = {"사용 가능한 프린터기"};
        private Vector<PrintService> printers;


        public PrintTable() {
            Vector<String> header = createHeader(headers);
            tableModel = new DefaultTableModel(headers, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            this.setModel(tableModel);
            PrintManager printManager = new PrintManager();
            printers = new Vector<>();
            printManager.refreshPrinter(printers);

            for (PrintService printer : printers) {
                Vector<String> rowData = new Vector<>();
                rowData.add(printer.toString());
                tableModel.addRow(rowData);
            }


        }


        public Vector<String> createHeader(String[] inputList) {
            Vector<String> outputList = new Vector<String>();
            Collections.addAll(outputList, inputList);
            return outputList;
        }

    }


    public static class PrintManager {
        public PrintManager() {


        }

        public Vector<PrintService> refreshPrinter(Vector<PrintService> printers) {
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            printers.clear();
            printers.addAll(Arrays.asList(printServices));
            return printers;
        }

    }
}
