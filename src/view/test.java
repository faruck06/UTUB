/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Calendar;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Faruck
 */
public class test {

    public static void main(String[] args) {
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);

        JSpinner spinner = new JSpinner(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "hh:mm a");
        de.getTextField().setEditable(false);
        spinner.setEditor(de);

        System.out.println("Spinner:      " + de.getFormat().format(spinner.getValue()));
    }
}
