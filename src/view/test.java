
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.*;

public class test extends JPanel {

    private static final String[] COLUMN_NAMES = {"List ID", "Expiration Date", "Status", "Date Created"};
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = 1L;

    private static class StatusPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private JRadioButton theSingleOption;
        private JRadioButton theMarriedOption;
        private JRadioButton theDivorcedOption;

        StatusPanel() {
            super(new GridLayout(3, 1));
            setOpaque(true);
            ButtonGroup buttonGroup = new ButtonGroup();
            theSingleOption = new JRadioButton("Single");
            theSingleOption.setOpaque(false);
            add(theSingleOption);
            buttonGroup.add(theSingleOption);
            theMarriedOption = new JRadioButton("Married");
            theMarriedOption.setOpaque(false);
            add(theMarriedOption);
            buttonGroup.add(theMarriedOption);
            theDivorcedOption = new JRadioButton("Divorced");
            theDivorcedOption.setOpaque(false);
            add(theDivorcedOption);
            buttonGroup.add(theDivorcedOption);
        }

        public Status getStatus() {
            if (theMarriedOption.isSelected()) {
                return Status.MARRIED;
            } else if (theDivorcedOption.isSelected()) {
                return Status.DIVORCED;
            } else {
                return Status.SINGLE;
            }
        }

        public void setStatus(Status status) {
            if (status == Status.MARRIED) {
                theMarriedOption.setSelected(true);
            } else if (status == Status.DIVORCED) {
                theDivorcedOption.setSelected(true);
            } else {
                theSingleOption.setSelected(true);
            }
        }
    }

    private static class Status {

        static final Status SINGLE = new Status("Single");
        static final Status MARRIED = new Status("Married");
        static final Status DIVORCED = new Status("Divorced");
        private final String myName; // for debug only

        private Status(String name) {
            myName = name;
        }

        @Override
        public String toString() {
            return myName;
        }
    }

    private static class TableEntry {

        private static int instanceNumber;
        private Long theId;
        private Date theExpirationDate;
        private Status theStatus;
        private Date theCreationDate;

        TableEntry() {
            instanceNumber++;
            theId = new Long(instanceNumber);
            theExpirationDate = new Date();
            theStatus = Status.SINGLE;
            theCreationDate = new Date();
        }

        TableEntry(Long anId, Date anExpirationDate, Status aStatus, Date aCreationDate) {
            theId = anId;
            theExpirationDate = anExpirationDate;
            theStatus = aStatus;
            theCreationDate = aCreationDate;
        }

        public Long getId() {
            return theId;
        }

        public Date getExpirationDate() {
            return theExpirationDate;
        }

        public Status getStatus() {
            return theStatus;
        }

        public Date getCreationDate() {
            return theCreationDate;
        }

        public void setId(Long anId) {
            theId = anId;
        }

        public void setExpirationDate(Date anExpirationDate) {
            theExpirationDate = anExpirationDate;
        }

        public void setStatus(Status aStatus) {
            theStatus = aStatus;
        }

        public void setCreationDate(Date aCreationDate) {
            theCreationDate = aCreationDate;
        }
    }

    private static class MyTableModel extends AbstractTableModel {

        private static final long serialVersionUID = 1L;
        private Vector<Object> theEntries;

        MyTableModel() {
            theEntries = new Vector<Object>();
        }

        @SuppressWarnings("unchecked")
        public void add(TableEntry anEntry) {
            int index = theEntries.size();
            theEntries.add(anEntry);
            fireTableRowsInserted(index, index);
        }

        public void remove(int aRowIndex) {
            if (aRowIndex < 0 || aRowIndex >= theEntries.size()) {
                return;
            }
            theEntries.removeElementAt(aRowIndex);
            fireTableRowsDeleted(aRowIndex, aRowIndex);

        }

        public int getRowCount() {
            return theEntries.size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return Long.class;
                case 1:
                    return Date.class;
                case 2:
                    return Status.class;
                case 3:
                    return Date.class;
            }
            return Object.class;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            TableEntry entry = (TableEntry) theEntries.elementAt(rowIndex);
            switch (columnIndex) {
                case 0:
                    try {
                        entry.setId(new Long(Long.parseLong(aValue.toString())));
                    }
                    catch (NumberFormatException nfe) {
                        return;
                    }
                    break;
                case 1:
                    entry.setExpirationDate((Date) aValue);
                    break;
                case 2:
                    entry.setStatus((Status) aValue);
                    break;
                case 3:
                    entry.setCreationDate((Date) aValue);
                    break;
                default:
                    return;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            TableEntry entry = (TableEntry) theEntries.elementAt(rowIndex);
            switch (columnIndex) {
                case 0:
                    return entry.getId();
                case 1:
                    return entry.getExpirationDate();
                case 2:
                    return entry.getStatus();
                case 3:
                    return entry.getCreationDate();
            }
            return null;
        }
    }

    private static class DateRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!(value instanceof Date)) {
                return this;
            }
            setText(DATE_FORMAT.format((Date) value));
            return this;
        }
    }

    private static class DateEditor extends AbstractCellEditor implements TableCellEditor {

        private static final long serialVersionUID = 1L;
        private JSpinner theSpinner;
        private Object value;

        DateEditor() {
            theSpinner = new JSpinner(new SpinnerDateModel());
            theSpinner.setOpaque(true);
            theSpinner.setEditor(new JSpinner.DateEditor(theSpinner, "dd/MM/yyyy"));
        }

        @Override
        public Object getCellEditorValue() {
            return theSpinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            theSpinner.setValue(value);
            if (isSelected) {
                theSpinner.setBackground(table.getSelectionBackground());
            } else {
                theSpinner.setBackground(table.getBackground());
            }
            return theSpinner;
        }
    }

    private static class StatusEditor extends AbstractCellEditor implements TableCellEditor {

        private static final long serialVersionUID = 1L;
        private StatusPanel theStatusPanel;

        StatusEditor() {
            theStatusPanel = new StatusPanel();
        }

        @Override
        public Object getCellEditorValue() {
            return theStatusPanel.getStatus();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            theStatusPanel.setStatus((Status) value);
            if (isSelected) {
                theStatusPanel.setBackground(table.getSelectionBackground());
            } else {
                theStatusPanel.setBackground(table.getBackground());
            }
            return theStatusPanel;
        }
    }

    private static class StatusRenderer extends StatusPanel implements TableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setStatus((Status) value);
            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getBackground());
            }
            return this;
        }
    }
    private MyTableModel theTableModel;
    private JTable theTable;

    public test() {
        super(new BorderLayout(0, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        theTableModel = new MyTableModel();
        theTable = new JTable(theTableModel);
        theTable.setDefaultEditor(Date.class, new DateEditor());
        theTable.setDefaultRenderer(Date.class, new DateRenderer());
        theTable.setDefaultEditor(Status.class, new StatusEditor());
        theTable.setDefaultRenderer(Status.class, new StatusRenderer());
// comment out the two preceding lines and uncomment the following one if you want a more standard editor
// theTable.setDefaultEditor(Status.class, new DefaultCellEditor(new JComboBox(new Status[]{Status.SINGLE, Status.MARRIED, Status.DIVORCED})));
        add(new JScrollPane(theTable), BorderLayout.CENTER);
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(new AbstractAction("Add new") {

            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                theTableModel.add(new TableEntry());
                packTable();
            }
        });
        toolBar.add(new AbstractAction("Remove") {

            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                theTableModel.remove(theTable.getSelectedRow());
            }
        });
        add(toolBar, BorderLayout.NORTH);
    }

    private void packTable() {
        TableColumnModel columnModel = theTable.getColumnModel();
        int columnCount = theTable.getColumnCount();
        int rowCount = theTable.getRowCount();
        int[][] preferredHeights = new int[columnCount][rowCount];
        TableCellRenderer renderer;
        Component comp;
        for (int col = 0; col < columnCount; col++) {
            renderer = columnModel.getColumn(col).getCellRenderer();
            if (renderer == null) {
                renderer = theTable.getDefaultRenderer(theTableModel.getColumnClass(col));
            }
            for (int row = 0; row < rowCount; row++) {
                comp = renderer.getTableCellRendererComponent(theTable, theTableModel.getValueAt(row, col), false, false, row, col);
                preferredHeights[col][row] = (int) comp.getPreferredSize().getHeight();
            }
        }
        for (int row = 0; row < rowCount; row++) {
            int pref = 0;
            for (int col = 0; col < columnCount; col++) {
                pref = Math.max(pref, preferredHeights[col][row]);
            }
            theTable.setRowHeight(row, pref);
        }
    }

    public static void main(String[] args) {
        try {
            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (info.getName().equals("Nimbus")) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        final JFrame frame = new JFrame("TestRadioButtonRenderer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new test());
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                frame.setSize(400, 300);
                frame.setVisible(true);
            }
        });
    }
}
