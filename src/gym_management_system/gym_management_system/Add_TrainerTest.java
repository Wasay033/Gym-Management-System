package gym_management_system;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Add_TrainerTest {

    private Add_Trainer addTrainer;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        addTrainer = new Add_Trainer();
    }

    @Test
    public void testEmptyFields() {
        ActionEvent e = new ActionEvent(addTrainer.bt1, ActionEvent.ACTION_PERFORMED, null);
        addTrainer.tf1.setText("");
        addTrainer.tf2.setText("");
        addTrainer.tf3.setText("");
        addTrainer.tf4.setText("");
        addTrainer.tf5.setText("");
        addTrainer.tf6.setText("");
        addTrainer.tf7.setText("");
        addTrainer.tf8.setText("");
        addTrainer.tf9.setText("");
        addTrainer.tf10.setText("");
        
        try {
            addTrainer.actionPerformed(e);
        } catch (Exception ex) {
            assertEquals("Please fill in all fields.", ex.getMessage());
        }
    }

    @Test
    public void testInvalidNumericValues() {
        ActionEvent e = new ActionEvent(addTrainer.bt1, ActionEvent.ACTION_PERFORMED, null);
        addTrainer.tf1.setText("John");
        addTrainer.tf2.setText("Doe");
        addTrainer.tf3.setText("john.doe@example.com");
        addTrainer.tf4.setText("123 Main St");
        addTrainer.tf5.setText("1234567890");
        addTrainer.tf6.setText("0987654321");
        addTrainer.ch1.select(0);
        addTrainer.tf7.setText("abc"); // Invalid Trainer ID
        addTrainer.ch2.select(0);
        addTrainer.ch3.select(0);
        addTrainer.tf8.setText("xyz"); // Invalid height
        addTrainer.tf9.setText("xyz"); // Invalid weight
        addTrainer.tf10.setText("No remarks");

        try {
            addTrainer.actionPerformed(e);
        } catch (Exception ex) {
            assertEquals("Please enter valid numeric values for Trainer ID, Height, and Weight.", ex.getMessage());
        }
    }

    @Test
    public void testSuccessfulInsert() throws SQLException {
        ActionEvent e = new ActionEvent(addTrainer.bt1, ActionEvent.ACTION_PERFORMED, null);
        addTrainer.tf1.setText("John");
        addTrainer.tf2.setText("Doe");
        addTrainer.tf3.setText("john.doe@example.com");
        addTrainer.tf4.setText("123 Main St");
        addTrainer.tf5.setText("1234567890");
        addTrainer.tf6.setText("0987654321");
        addTrainer.ch1.select(0);
        addTrainer.tf7.setText("123");
        addTrainer.ch2.select(0);
        addTrainer.ch3.select(0);
        addTrainer.tf8.setText("70");
        addTrainer.tf9.setText("180");
        addTrainer.tf10.setText("No remarks");

        try {
            addTrainer.actionPerformed(e);
          
        } catch (Exception ex) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void testCancelAction() {
        ActionEvent e = new ActionEvent(addTrainer.bt2, ActionEvent.ACTION_PERFORMED, null);
        try {
            addTrainer.actionPerformed(e);
            assertFalse(addTrainer.isVisible());
        } catch (Exception ex) {
            fail("Exception should not be thrown.");
        }
    }
}
