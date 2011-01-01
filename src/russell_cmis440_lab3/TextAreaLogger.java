package russell_cmis440_lab3;


/**
* Program Name: CMIS440 Lab 3 JavaDB Address Book Program
* @author Brandon R Russell
* @Course CMIS440
* Date: Dec 15,2010
*/

/** Used by GUI to update TextArea with error messages
*|----------------------------------------------------------------------------|
*|                           CRC: TextAreaLogger                              |
*|----------------------------------------------------------------------------|
*|Updates TextArea with passed error messages             AddressBookDisplay  |
*|Updates TextArea with passed error messages                   BooksDisplay  |
*|----------------------------------------------------------------------------|
*
* @TheCs Cohesion - All methods in this class work together on similar task.
* Completeness - Completely creates word counter threads to process file input
* Convenience - There are sufficient methods and variables to complete the
*                required task.
* Clarity - The methods and variables are distinguishable and work in a
*           uniform manner to provide clarity to other programmers.
* Consistency - All names,parameters ,return values , and behaviors follow
*               the same basic rules.
*/


import javax.swing.JTextArea;


public class TextAreaLogger {
    
    private JTextArea myDebugJTextArea = null;

   /** Constructor. Assigns TextArea reference
   * @TheCs Cohesion -  Constructor. Assigns TextArea reference.
   * Completeness - Completely constructs/assigns TextArea reference.
   * Convenience - Simply constructs/assigns TextArea reference.
   * Clarity - It is simple to understand that this constructs/assigns TextArea
   *           reference.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   *
   * @param aJTextArea reference to TextArea on GUI
   */
    public TextAreaLogger(JTextArea aJTextArea){
        myDebugJTextArea = aJTextArea;
    }

   /** Adds error message to TextArea
   * @TheCs Cohesion -  Adds error message to TextArea.
   * Completeness - Completely adds error message to TextArea.
   * Convenience - Simply adds error message to TextArea.
   * Clarity - It is simple to understand that this adds error message to
   *           TextArea.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   *
   * @param msg error message to display on TextArea
   */
    public void log(String msg){
        myDebugJTextArea.setText(myDebugJTextArea.getText() +  msg + "\n");
    }

   /** Adds error message/value to TextArea
   * @TheCs Cohesion -  Adds error message/value to TextArea.
   * Completeness - Completely adds error message/value to TextArea.
   * Convenience - Simply adds error message/value to TextArea.
   * Clarity - It is simple to understand that this adds error message/value to
   *           TextArea.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   *
   * @param msg error message to display on TextArea
   * @param value error message value to display on TextArea
   */
    public void log(String msg, int value){
        myDebugJTextArea.setText(myDebugJTextArea.getText() 
                + msg + " = " + value + "\n");
    }


}
