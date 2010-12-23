package russell_cmis440_lab3;


/**
* Program Name: CMIS440 Lab 3 JavaDB Address Book Program
* @author Brandon R Russell
* @Course CMIS440
* Date: Dec 15,2010
* IDE: NetBeans 6.9.1
* OS: Windows 7
* Java: JDK 1.6.0_22
* Files: AddressBookDisplay.java, PersonQueries.java, Person.java,
*        BooksDisplay.java, BooksQuery.java, Book.java
*
* Program Requirements:
* Minimum Assignment Requirements:
* 
* 1)Install and configure the Java DB per section 28.10 of the text. 
* 2)If you use the text book examples as your starting point, you will 
*   demonstrate you understand the code by a) commenting the code to meet 
*   the course standards and b) ensuring the course naming conventions 
*   are followed. 
* 3)Implement the additions stated in Exercise 28.6 of the text. 
* 4)Implement the additions stated in Exercise 28.7 of the text. 
* 5)Add a JTextArea to the main UI form for the display of the internal
*   'debugging' statements. 
* 6)Create a class called TextAreaLogger that takes a JTextArea in its 
*   contructor and provides one or more log methods. Possible log methods 
*   might be: 
*
*   log(String msg) - log the given message to the provided JTextArea 
*   log(String msg, int value) - log the given message and value to the 
*   provided JTextArea in a format: message = value 
* 7)Add a getLastError method to the PersonQueries class. The backing variable 
*   should be cleared (set to null) at the beginning of each method in the 
*   class and will contain any error messages that occurred in the method, i.e.,
*   replace the current sqlException.printStackTrace() to use this last error 
*   variable. This implies that after every method call on a PersonQueries 
*   instance you should check to see if the returned value of getLastError()
*   is null, meaning the operation was successful, or not null meaning the 
*   operation failed. All failures should be written to the 'debugging'
*   JTextArea. 
* 8)Remove all System.exit calls in the PersonQueries class and use the last
*   error variable approach outlined above. 
* 9)Add strategically placed logging statements in your UI code only. 
*
* Assignment Optional Implementations:
*
* 1)Add support for the Books database in a similar manner as the Address
*   database by creating a Book class, BooksQuery class and BoosDisplay form
*   class.
*
* Things you what me to know before I grade your work: I used the NetBeans
* Graphic designer to create the GUI portion of this program.
*/



import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/** This is the main window of operation for the entire program. The purpose of
*  this class is to create a GUI Interface to the AddressBook JavaDB Database
*  for the user to interact with.
*|----------------------------------------------------------------------------|
*|                                CRC: AddressBookDisplay                     |
*|----------------------------------------------------------------------------|
*|Creates GUI Interface                                                       |
*|Initialize Person object to hold current person to edit       Person        |
*|Initialize PersonQueries to handle AddressBook data entries   PersonQueries |
*|----------------------------------------------------------------------------|
*
* @TheCs Cohesion - All methods in this class work together on similar task.
* Completeness - Completely creates/runs Person and PersonQueries object to
*                handle AddressBook entries.
* Convenience - There are sufficient methods and variables to complete the
*                required task.
* Clarity - The methods and variables are distinguishable and work in a
*           uniform manner to provide clarity to other programmers.
* Consistency - All names,parameters ,return values , and behaviors follow
*               the same basic rules.
*/
        
public class AddressBookDisplay extends javax.swing.JFrame {

   private Person currentEntry;
   private PersonQueries personQueries;
   private List< Person > results;
   private int numberOfEntries = 0;
   private int currentEntryIndex;
   private TextAreaLogger myTextAreaLogger = null;

    /**Constructor to initialize gui components and load database data.
    * @TheCs Cohesion - Constructor to initialize gui components and load
    *                   database data.
    * Completeness - Completely constructs gui components / loads database data.
    * Convenience - Simply constructs gui components / loads database data.
    * Clarity - It is simple to understand that this constructs gui components
    *           / loads database data.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    public AddressBookDisplay() {
        super( "Address Book" );
        initComponents();//Create GUI components
        /**
         * myTextAreaLogger will be used throughout to print any errors to the
         * TextArea component.
         * personQueries is the main interface to the database
         * checkForErrors will see if the lastError variable is not null and
         * will print any errors if not.
         * loadDatabase will query the database for all entries to display in
         * the gui components.
         */
        myTextAreaLogger = new TextAreaLogger(debugTextArea);
        personQueries = new PersonQueries();
        checkForErrors();
        loadDatabase();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newUpdateDeletePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAddressID = new javax.swing.JTextField();
        lblFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        lblLastName = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPhoneNum = new javax.swing.JLabel();
        txtPhoneNum = new javax.swing.JTextField();
        btnNewEntry = new javax.swing.JButton();
        btnUpdateEntry = new javax.swing.JButton();
        btnDeleteEntry = new javax.swing.JButton();
        browsePanel = new javax.swing.JPanel();
        btnPrevious = new javax.swing.JButton();
        txtCurrentRecord = new javax.swing.JTextField();
        lblOf = new javax.swing.JLabel();
        txtTotalRecordCount = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        myJTableScrollPane = new javax.swing.JScrollPane();
        myJTable = new javax.swing.JTable();
        lblNoData = new javax.swing.JLabel();
        findPanel = new javax.swing.JPanel();
        txtFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        errorPanel = new javax.swing.JPanel();
        myTextAreaScrollPane = new javax.swing.JScrollPane();
        debugTextArea = new javax.swing.JTextArea();
        btnClear = new javax.swing.JButton();
        myMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openBooksMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        instructionsMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CMIS440 Lab 3 JavaDB Address Book Program");
        setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.Desktop.background"));

        newUpdateDeletePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("New/Update/Delete Record"));

        jLabel1.setText("Address ID:");

        txtAddressID.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.disabledBackground"));
        txtAddressID.setToolTipText("Unique key for entry");
        txtAddressID.setEnabled(false);

        lblFirstName.setText("First Name:");

        txtFirstName.setToolTipText("First name of person");

        lblLastName.setText("Last Name:");

        txtLastName.setToolTipText("Last name of person");

        lblEmail.setText("Email:");

        txtEmail.setToolTipText("Email Address of person");

        lblPhoneNum.setText("Phone Number:");

        txtPhoneNum.setToolTipText("Phone Number of person");

        btnNewEntry.setText("Insert New Entry");
        btnNewEntry.setToolTipText("Make a new entry into address book database");
        btnNewEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewEntryActionPerformed(evt);
            }
        });

        btnUpdateEntry.setText("Update Entry");
        btnUpdateEntry.setToolTipText("Update currently selected record");
        btnUpdateEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateEntryActionPerformed(evt);
            }
        });

        btnDeleteEntry.setText("Delete Entry");
        btnDeleteEntry.setToolTipText("Delete currently selected record");
        btnDeleteEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEntryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newUpdateDeletePanelLayout = new javax.swing.GroupLayout(newUpdateDeletePanel);
        newUpdateDeletePanel.setLayout(newUpdateDeletePanelLayout);
        newUpdateDeletePanelLayout.setHorizontalGroup(
            newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newUpdateDeletePanelLayout.createSequentialGroup()
                .addGroup(newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newUpdateDeletePanelLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(newUpdateDeletePanelLayout.createSequentialGroup()
                                .addGroup(newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPhoneNum)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPhoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtAddressID, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(newUpdateDeletePanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnNewEntry)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateEntry)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteEntry)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        newUpdateDeletePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnDeleteEntry, btnNewEntry, btnUpdateEntry});

        newUpdateDeletePanelLayout.setVerticalGroup(
            newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newUpdateDeletePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txtAddressID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFirstName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblLastName)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblPhoneNum)
                    .addComponent(txtPhoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(newUpdateDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewEntry)
                    .addComponent(btnUpdateEntry)
                    .addComponent(btnDeleteEntry))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        browsePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Browse"));

        btnPrevious.setText("Previous");
        btnPrevious.setToolTipText("Show previous record in editable area");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        txtCurrentRecord.setToolTipText("Current record selected, or enter record to select");
        txtCurrentRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCurrentRecordActionPerformed(evt);
            }
        });

        lblOf.setText("of");

        txtTotalRecordCount.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.disabledBackground"));
        txtTotalRecordCount.setToolTipText("Total amount of records in database");
        txtTotalRecordCount.setEnabled(false);

        btnNext.setText("Next");
        btnNext.setToolTipText("Show next record in editable area");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        myJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        myJTable.setToolTipText("Show records in current database");
        myJTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        myJTable.setFocusable(false);
        myJTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        myJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myJTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                myJTableMouseReleased(evt);
            }
        });
        myJTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                myJTablePropertyChange(evt);
            }
        });
        /**
        * The code below creates a ListSelectionModel based on the JTable and then
        * attaches a listener and refers it to the valueChanged method. This is used
        * so that the user can change the entry being edited by selecting it from the
        * table.
        */
        ListSelectionModel rowSM = myJTable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                myJTableValueChanged(evt);
            }
        });
        myJTableScrollPane.setViewportView(myJTable);

        lblNoData.setForeground(javax.swing.UIManager.getDefaults().getColor("nb.errorForeground"));
        lblNoData.setText("*No Data Present");

        javax.swing.GroupLayout browsePanelLayout = new javax.swing.GroupLayout(browsePanel);
        browsePanel.setLayout(browsePanelLayout);
        browsePanelLayout.setHorizontalGroup(
            browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, browsePanelLayout.createSequentialGroup()
                .addGroup(browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, browsePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(myJTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE))
                    .addGroup(browsePanelLayout.createSequentialGroup()
                        .addContainerGap(602, Short.MAX_VALUE)
                        .addComponent(lblNoData))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, browsePanelLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(btnPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCurrentRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalRecordCount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext)))
                .addContainerGap())
        );

        browsePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNext, btnPrevious});

        browsePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCurrentRecord, txtTotalRecordCount});

        browsePanelLayout.setVerticalGroup(
            browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(browsePanelLayout.createSequentialGroup()
                .addGroup(browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtCurrentRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalRecordCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevious)
                    .addComponent(lblOf)
                    .addComponent(btnNext))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(myJTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNoData))
        );

        browsePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCurrentRecord, txtTotalRecordCount});

        findPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Find by Last Name"));

        txtFind.setToolTipText("Enter last name of person to find");

        btnFind.setText("Find");
        btnFind.setToolTipText("Find all records that match the last name entered");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout findPanelLayout = new javax.swing.GroupLayout(findPanel);
        findPanel.setLayout(findPanelLayout);
        findPanelLayout.setHorizontalGroup(
            findPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFind, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        findPanelLayout.setVerticalGroup(
            findPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(findPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        errorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Log"));

        debugTextArea.setColumns(20);
        debugTextArea.setRows(5);
        debugTextArea.setToolTipText("Display messages/errors/exceptions");
        debugTextArea.setFocusable(false);
        myTextAreaScrollPane.setViewportView(debugTextArea);

        javax.swing.GroupLayout errorPanelLayout = new javax.swing.GroupLayout(errorPanel);
        errorPanel.setLayout(errorPanelLayout);
        errorPanelLayout.setHorizontalGroup(
            errorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myTextAreaScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );
        errorPanelLayout.setVerticalGroup(
            errorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myTextAreaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
        );

        btnClear.setText("Clear");
        btnClear.setToolTipText("Clear fields and reload database");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        openBooksMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        openBooksMenuItem.setText("Switch to Books Database");
        openBooksMenuItem.setToolTipText("Close Address Book Database and open Books Database");
        openBooksMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBooksMenuItemActionPerformed1(evt);
            }
        });
        fileMenu.add(openBooksMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        myMenuBar.add(fileMenu);

        helpMenu.setText("Help");

        instructionsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        instructionsMenuItem.setText("Instructions");
        instructionsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instructionsMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(instructionsMenuItem);

        myMenuBar.add(helpMenu);

        setJMenuBar(myMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newUpdateDeletePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(browsePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newUpdateDeletePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(browsePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(findPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(502, Short.MAX_VALUE)
                .addComponent(btnClear)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**Selects Previous entry from List of Person objects
    * @TheCs Cohesion - Selects Previous entry from List of Person objects
    * Completeness - Completely selects Previous entry from List of Person
    *                objects.
    * Convenience - Simply selects Previous entry from List of Person objects.
    * Clarity - It is simple to understand that this selects Previous entry
    *           from List of Person objects.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        /**
         * Basically this deducts from the current entry index and if it is less
         * than 0 it makes it equal to the last record. This number is then
         * added to the current record text field and 
         * updateCurrentSelected record will use this number to determine 
         * which person object in the person object list to display for editing.
         */
        currentEntryIndex--;
        if ( currentEntryIndex < 0 ){
            currentEntryIndex = numberOfEntries - 1;
        }
        txtCurrentRecord.setText( "" + ( currentEntryIndex + 1 ) );
        updateCurrentSelectedRecord();
    }//GEN-LAST:event_btnPreviousActionPerformed

    /**Allows the user to enter a number of record to select
    * @TheCs Cohesion - Allows the user to enter a number of record to select
    * Completeness - Completely allows the user to enter a number of record to
    *                select.
    * Convenience - Simply allows the user to enter a number of record to
    *               select.
    * Clarity - It is simple to understand that this  allows the user to enter
    *           a number of record to select.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void txtCurrentRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCurrentRecordActionPerformed
        /**
         * updateCurrentSelectedRecord will use the number entered into the
         * current record textfield to determine which person object in the
         * person object list to display for editing.
         */
        updateCurrentSelectedRecord();
    }//GEN-LAST:event_txtCurrentRecordActionPerformed

    /**Selects Next entry from List of Person objects
    * @TheCs Cohesion - Selects Next entry from List of Person objects
    * Completeness - Completely selects Next entry from List of Person
    *                objects.
    * Convenience - Simply selects Next entry from List of Person objects.
    * Clarity - It is simple to understand that this selects Next entry
    *           from List of Person objects.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        /**
        * Basically this adds 1 to the current entry index and if it is greater
        * than the total number of entries it makes it equal to the first record
        * . This number is then added to the current record text field and 
        * updateCurrentSelected record will use this number to determine 
        * which person object in the person object list to display for editing.
        */
        currentEntryIndex++;

        if ( currentEntryIndex >= numberOfEntries ){
          currentEntryIndex = 0;
        }
        txtCurrentRecord.setText( "" + ( currentEntryIndex + 1 ) );
        updateCurrentSelectedRecord();
    }//GEN-LAST:event_btnNextActionPerformed

    /** Find records based on last name entered
    * @TheCs Cohesion - Find records based on last name entered
    * Completeness - Completely finds records based on last name entered.
    * Convenience - Simply finds records based on last name entered.
    * Clarity - It is simple to understand that this finds records based on
    *           last name entered.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        /**
         * Run the .getPeopleByLastName method of personQueries to return a
         * list of all persons with the specified last name. It then calls
         * checkForErrors to determine if any errors were thrown during this
         * method call by checking the lastError variable through the
         * getLastError method. If so it will send to the TextAreaLogger to
         * display in the TextArea on the GUI.
         * Next it will get the number of entries and if not equal to 0 it will
         * display the first one in the editable area. Also, it will reset the
         * model of the JTable to ensure it reflects the new data being shown
         * and also calls enableControls to make sure all the controls are
         * enabled. Else it will just load the entire database if there are 0
         * entries found.
         */
        results = personQueries.getPeopleByLastName( txtFind.getText() );
        checkForErrors();
        numberOfEntries = results.size();

        if ( numberOfEntries != 0 ){
            currentEntryIndex = 0;//Show first entry in editable area
            currentEntry = results.get(currentEntryIndex);
            txtAddressID.setText("" + currentEntry.getAddressID());
            txtFirstName.setText(currentEntry.getFirstName());
            txtLastName.setText(currentEntry.getLastName());
            txtEmail.setText(currentEntry.getEmail());
            txtPhoneNum.setText(currentEntry.getPhoneNumber());
            txtTotalRecordCount.setText("" + numberOfEntries);
            txtCurrentRecord.setText("" + (currentEntryIndex + 1));
            myJTable.setModel(personQueries);//Update JTable
            enableControls();
        }
        else{
            loadDatabase();
        }
    }//GEN-LAST:event_btnFindActionPerformed

    /** Inputs a new entry into the database
    * @TheCs Cohesion - Inputs a new entry into the database
    * Completeness - Completely inputs a new entry into the database.
    * Convenience - Simply inputs a new entry into the database.
    * Clarity - It is simple to understand that this inputs a new entry into the
    *           database.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void btnNewEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewEntryActionPerformed

        /**
         * This calls the addPerson method of the personQueries object to add
         * a new person to the database. It's assigned to a int to determine
         * if it was successful in adding the person or not. checkForErrors is
         * called after to see if any errors were thrown during the process.
         * After the person is added the database is reloaded to reflect the new
         * entry.
         */
        int result = personQueries.addPerson( txtFirstName.getText(),
                txtLastName.getText(), txtEmail.getText(),
                txtPhoneNum.getText() );
        checkForErrors();
        
        myTextAreaLogger.log(result > 0 ? "Insert Successful"
                : "Insert Not Successful");
        loadDatabase();
    }//GEN-LAST:event_btnNewEntryActionPerformed

    private void myJTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_myJTablePropertyChange

    }//GEN-LAST:event_myJTablePropertyChange

    /** Deletes a entry into the database
    * @TheCs Cohesion - Deletes a entry into the database
    * Completeness - Completely deletes entry into the database.
    * Convenience - Simply deletes entry into the database.
    * Clarity - It is simple to understand that this deletes a entry into the
    *           database.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void btnDeleteEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEntryActionPerformed
        /**
         * If the AddressID field is blank then the method is stopped since no
         * entry is selected for deletion.
         * This calls the deletePerson method of the personQueries object to
         * delete a person from the database. It's assigned to a int to
         * determine if it was successful in deleting the person or not.
         * checkForErrors is called after to see if any errors were thrown
         * during the process.
         * After the person is deleted the database is reloaded to reflect the
         * deleted entry is gone.
         */
        if (txtAddressID.getText().equals("")){
            myTextAreaLogger.log("No record is selected for deletion");
            return;
        }
        int result = personQueries.deletePerson( txtAddressID.getText());
        checkForErrors();
        
        myTextAreaLogger.log(result > 0 ? "Delete Successful"
                : "Delete Not Successful");
        loadDatabase();
    }//GEN-LAST:event_btnDeleteEntryActionPerformed

    /** Updates a entry into the database
    * @TheCs Cohesion - Updates a entry into the database
    * Completeness - Completely updates a entry into the database.
    * Convenience - Simply updates a entry into the database.
    * Clarity - It is simple to understand that this updates a entry into the
    *           database.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void btnUpdateEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateEntryActionPerformed
        /**
         * If the AddressID field is blank then the method is stopped since no
         * entry is selected for updating.
         * This calls the updatePerson method of the personQueries object to
         * update a person from the database. It's assigned to a int to
         * determine if it was successful in updating the person or not.
         * checkForErrors is called after to see if any errors were thrown
         * during the process.
         * After the person is updated the database is reloaded to reflect the
         * changes.
         */
        if (txtAddressID.getText().equals("")){
            myTextAreaLogger.log("No record is selected for an update");
            return;
        }
            int result = personQueries.updatePerson( txtAddressID.getText(),
                    txtFirstName.getText(),txtLastName.getText(),
                    txtEmail.getText(),txtPhoneNum.getText() );
            checkForErrors();
            
            myTextAreaLogger.log(result > 0 ? "Update Successful"
                    : "Update Not Successful");
            loadDatabase();
    }//GEN-LAST:event_btnUpdateEntryActionPerformed

    /** Properly exits the program.
    * @TheCs Cohesion - Properly exits the program.
    * Completeness - Completely exits the program.
    * Convenience - Simply exits the program.
    * Clarity - It is simple to understand that this exits the program.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /** Shows instructions on using program.
    * @TheCs Cohesion - Shows instructions on using program.
    * Completeness - Completely shows instructions on using program.
    * Convenience - Simply shows instructions on using program.
    * Clarity - It is simple to understand that this shows instructions on using
    *           program.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void instructionsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instructionsMenuItemActionPerformed
        String instructionMessage = ""
                + "Add a Person\n"
                + "1) Input First and Last Name, Email, and Phone Number.\n "
                + "2) Click 'Insert New Entry'.\n"
                + "Update a Person\n"
                + "1) Select the person you want to update by using the"
                + " Previous/Next buttons or by selecting them from the table"
                + ".\n"
                + "2) Update their information and click 'Update Entry\n"
                + "Delete a Person\n"
                + "1) Select the person you want to delete by using the Previous"
                + "/Next buttons or by selecting them from the table.\n"
                + "2) Click 'Delete Entry'\n"
                + "Find a Person\n"
                + "1) Enter the persons last name into the text field in the"
                + " find area.\n"
                + "2) Click 'Find'.";

        JOptionPane.showMessageDialog(null, instructionMessage,
            "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_instructionsMenuItemActionPerformed


    private void openBooksMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBooksMenuItemActionPerformed

    }//GEN-LAST:event_openBooksMenuItemActionPerformed

    /** Clear find results, shows entire address book
    * @TheCs Cohesion - Clear find results, shows entire address book.
    * Completeness - Completely clears find results, shows entire address book.
    * Convenience - Simply clears find results, shows entire address book.
    * Clarity - It is simple to understand that this clears find results,
    *           shows entire address book.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtFind.setText("");
        loadDatabase();
    }//GEN-LAST:event_btnClearActionPerformed

    private void myJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myJTableMouseClicked

    }//GEN-LAST:event_myJTableMouseClicked

    private void myJTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myJTableMouseReleased

    }//GEN-LAST:event_myJTableMouseReleased

    /** Switches over to Books database
    * @TheCs Cohesion - Switches over to Books database
    * Completeness - Completely switches over to Books database.
    * Convenience - Simply switches over to Books database.
    * Clarity - It is simple to understand that this switches over to Books
    *           database.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void openBooksMenuItemActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBooksMenuItemActionPerformed1
        try{
            //Sets Look and Feel of GUI to Nimbus.
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

        /**
         * After the UIManager is updated, then make a new Runnable on the
         * EventQueue.invoke later to run the program and make it visible.
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BooksDisplay().setVisible(true);
            }
        });
        
        this.dispose();//Dispose of this GUI since its no longer needed

        }catch (UnsupportedLookAndFeelException exception) {
            myTextAreaLogger.log(exception.getMessage());
        }catch (ClassNotFoundException exception) {
            myTextAreaLogger.log(exception.getMessage());
        }catch (InstantiationException exception) {
            myTextAreaLogger.log(exception.getMessage());
        }catch (IllegalAccessException exception) {
            myTextAreaLogger.log(exception.getMessage());
        }catch (Exception exception) {
            myTextAreaLogger.log(exception.getMessage());
        }
    }//GEN-LAST:event_openBooksMenuItemActionPerformed1


    /**Sets editable area to entry based on number found in txtCurrentRecord
    * @TheCs Cohesion - Sets editable area to entry based on number found in
    *                   txtCurrentRecord.
    * Completeness - Completely sets editable area to entry based on number
    *                found in txtCurrentRecord.
    * Convenience - Simply sets editable area to entry based on number found in
    *               txtCurrentRecord.
    * Clarity - It is simple to understand that this sets editable area to entry
    *           based on number found in txtCurrentRecord.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    * @exception NumberFormatException for Integer.parseInt
    * @exception Exception general exception capture
    */
    private void updateCurrentSelectedRecord(){
        try{
            /**
             * First it gets the number from the txtCurrentRecord and -1 since
             * the person list is zero based.
             */
            currentEntryIndex =
                    ( Integer.parseInt( txtCurrentRecord.getText() ) - 1 );

            /**
             * Only attempt to get the person if the number of entries is not
             * equal to 0 and the index entered is less than the total number
             * of entries. It assigns the currentEntry, Person object, to the
             * person object in the results list that is at the selected index
             * and then retrieves all the information and displays in the
             * editable area. Also, the record counters are updated and the
             * JTable is updated to be selected to the entry in question.
             */
            if ( numberOfEntries != 0 && currentEntryIndex < numberOfEntries ){
                currentEntry = results.get( currentEntryIndex );
                txtAddressID.setText("" + currentEntry.getAddressID() );
                txtFirstName.setText( currentEntry.getFirstName() );
                txtLastName.setText( currentEntry.getLastName() );
                txtEmail.setText( currentEntry.getEmail() );
                txtPhoneNum.setText( currentEntry.getPhoneNumber() );
                txtTotalRecordCount.setText( "" + numberOfEntries );
                txtCurrentRecord.setText( "" + ( currentEntryIndex + 1 ) );
                myJTable.changeSelection(currentEntryIndex, 0, false, false);
            }
        }catch (NumberFormatException exception){
            myTextAreaLogger.log(exception.toString());
        }catch (Exception exception){
            myTextAreaLogger.log(exception.toString());
        }
    }

    /** Takes selected row from JTable to display in editable area
    * @TheCs Cohesion - Takes selected row from JTable to display in editable
    *                   area.
    * Completeness - Completely takes selected row from JTable to display in
    *                editable area.
    * Convenience - Simply takes selected row from JTable to display in editable
    *               area.
    * Clarity - It is simple to understand that this takes selected row from
    *           JTable to display in editable area.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void myJTableValueChanged(ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()){
            return; // if you don't want to handle intermediate selections
        }
        /**
         * This basically make a selection list model to the table change event
         * and retrieves the first selected index, or -1 if selection is empty
         * and updates the txtCurrentRecord w/ this index.
         * updateCurrentSelectedRecord will use this number to determine
         * which person object in the person object list to display for editing.
         */
        ListSelectionModel rowSM = (ListSelectionModel)evt.getSource();
        int selectedIndex = rowSM.getMinSelectionIndex();
        currentEntryIndex = selectedIndex;
        if (currentEntryIndex > -1){
            txtCurrentRecord.setText( "" + ( currentEntryIndex + 1 ) );
            updateCurrentSelectedRecord();
        }

    }

    /** Loads in data from database into results.
    * @TheCs Cohesion - Loads in data from database into results.
    * Completeness - Completely loads in data from database into results.
    * Convenience - Simply loads in data from database into results.
    * Clarity - It is simple to understand that this Loads in data from database
    *           into results.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    * @exception Exception general exception capture
    */
    private void loadDatabase(){
        try
        {
            /**
             * Calls the .getAllPeople to retrieve all records in the database.
             * checkForErrors will determine if any errors were thrown during
             * this process and if so will send them to the TextAreaLogger to
             * display in the TextArea on the GUI. Also, all of the fields in
             * the editable area are cleared and the JTable's model is reset to
             * reflect the new data being pulled in. Next, if the number of
             * entries pulled in is greater than zero the controls will be
             * enabled, otherwise they will be disabled until data is input.
             */
            results = personQueries.getAllPeople();
            checkForErrors();
            numberOfEntries = results.size();
            txtAddressID.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtEmail.setText("");
            txtPhoneNum.setText("");
            txtTotalRecordCount.setText( "" + numberOfEntries );
            txtCurrentRecord.setText("");
            myJTable.setModel(personQueries);
            if ( numberOfEntries > 0 ){
                enableControls();
            }else{
                disableControls();
            }

        }
        catch ( Exception exception ){
            myTextAreaLogger.log(exception.toString());
        }
    }

    /** Enables the Controls
    * @TheCs Cohesion - Enables the Controls
    * Completeness - Completely enables the Controls.
    * Convenience - Simply enables the Controls.
    * Clarity - It is simple to understand that this enables the Controls.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void enableControls(){
         btnNext.setEnabled( true );
         btnPrevious.setEnabled( true );
         txtCurrentRecord.setEnabled(true);
         lblNoData.setVisible(false);
         btnDeleteEntry.setEnabled(true);
         btnUpdateEntry.setEnabled(true);
    }

    /** Disables the Controls
    * @TheCs Cohesion - Disables the Controls.
    * Completeness - Completely disables the Controls.
    * Convenience - Simply disables the Controls.
    * Clarity - It is simple to understand that this disables the Controls.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void disableControls(){
         btnNext.setEnabled( false );
         btnPrevious.setEnabled( false );
         txtCurrentRecord.setEnabled(false);
         lblNoData.setVisible(true);
         btnDeleteEntry.setEnabled(false);
         btnUpdateEntry.setEnabled(false);
    }

    /** Checks for errors from the personQueries object
    * @TheCs Cohesion - Checks for errors from the personQueries object
    * Completeness - Completely checks for errors from the personQueries object.
    * Convenience - Simply checks for errors from the personQueries object.
    * Clarity - It is simple to understand that this checks for errors from the
    *           personQueries object.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    private void checkForErrors(){
        /**
         * Every time personQueries method is called this is called right after
         * it. It calls the getLastError method to determine of the lastError
         * variable is null or not. If it is not null then an error occured and
         * so this will send the error message to the TextAreaLogger to be
         * displayed on the TextArea on the GUI.
         */
        if (personQueries.getLastError() != null){
            myTextAreaLogger.log(personQueries.getLastError());
        }
    }


    /** Main method that starts the program by making the GUI visible.
    * @TheCs Cohesion - Starts the program by making the GUI visible.
    * Completeness - Completely makes the GUI visible.
    * Convenience - Simply makes the GUI visible.
    * Clarity - It is simple to understand that this makes the GUI visible.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    *
    * @param args the command line arguments
    * @exception UnsupportedLookAndFeelException for UIManager method
    * @exception ClassNotFoundException for UIManager method
    * @exception InstantiationException for UIManager method
    * @exception IllegalAccessException for UIManager method
    * @exception Exception general exception capture
    */
    public static void main(String args[]) {
        try{
            //Sets Look and Feel of GUI to Nimbus.
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

        }catch (UnsupportedLookAndFeelException exception) {
            JOptionPane.showMessageDialog(null,exception.getMessage(),
                    "UnsupportedLookAndFeelException Exception Thrown on"
                    + " UIManager",
                    JOptionPane.ERROR_MESSAGE);
        }catch (ClassNotFoundException exception) {
            JOptionPane.showMessageDialog(null,exception.getMessage(),
                    "ClassNotFoundException Exception Thrown on UIManager",
                    JOptionPane.ERROR_MESSAGE);
        }catch (InstantiationException exception) {
            JOptionPane.showMessageDialog(null,exception.getMessage(),
                    "InstantiationException Exception Thrown on UIManager",
                    JOptionPane.ERROR_MESSAGE);
        }catch (IllegalAccessException exception) {
            JOptionPane.showMessageDialog(null,exception.getMessage(),
                    "IllegalAccessException Exception Thrown on UIManager",
                    JOptionPane.ERROR_MESSAGE);
        }catch (Exception exception) {
            JOptionPane.showMessageDialog(null,exception.getMessage(),
                    "Unknown Exception Thrown on UIManager",
                    JOptionPane.ERROR_MESSAGE);
        }

        /**
         * After the UIManager is updated, then make a new Runnable on the
         * SwingUtilities.invoke later to run the program and make it visible.
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddressBookDisplay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel browsePanel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeleteEntry;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnNewEntry;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnUpdateEntry;
    private javax.swing.JTextArea debugTextArea;
    private javax.swing.JPanel errorPanel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel findPanel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem instructionsMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblNoData;
    private javax.swing.JLabel lblOf;
    private javax.swing.JLabel lblPhoneNum;
    private javax.swing.JTable myJTable;
    private javax.swing.JScrollPane myJTableScrollPane;
    private javax.swing.JMenuBar myMenuBar;
    private javax.swing.JScrollPane myTextAreaScrollPane;
    private javax.swing.JPanel newUpdateDeletePanel;
    private javax.swing.JMenuItem openBooksMenuItem;
    private javax.swing.JTextField txtAddressID;
    private javax.swing.JTextField txtCurrentRecord;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPhoneNum;
    private javax.swing.JTextField txtTotalRecordCount;
    // End of variables declaration//GEN-END:variables

}
