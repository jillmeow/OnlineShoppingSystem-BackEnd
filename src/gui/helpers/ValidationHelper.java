/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.helpers;

import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 *
 * @author jillianmirandilla
 */
public class ValidationHelper {
    public void addTypeFormatter(JFormattedTextField textField,
            String format, Class type) {
// create a format object using our desired format
        DecimalFormat df = new DecimalFormat(format);
// create a formatter object that will apply the format
        NumberFormatter formatter = new NumberFormatter(df);
// dene the type that the formatter will return
        formatter.setValueClass(type);
// don't allow the user to enter values that don't match our format
        formatter.setAllowsInvalid(false);
// create a factory for the formatter
        DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter);
// install the factory in the text eld
        textField.setFormatterFactory(factory);
    }
    
    public boolean isObjectValid(Object domain) {
// create Oval validator
        Validator validator = new Validator();
// validate the object
        List<ConstraintViolation> violations = validator.validate(domain);
// were there any violations?
        if (violations.isEmpty()) {
            return true;
        } else {
            StringBuilder message = new StringBuilder();
// loop through the violations extracting the message for each
            for (ConstraintViolation violation : violations) {
                message.append(violation.getMessage()).append("\n");
            }
// show a message box to the user with all the violation messages
            JOptionPane.showMessageDialog(null, message.toString(),
                    "Input Problem", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    } 
}
