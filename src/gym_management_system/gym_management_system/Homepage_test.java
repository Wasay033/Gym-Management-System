package gym_management_system;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
public class Homepage_test {

    private Home_Page homePage;

    @Before
    public void setUp() {
        homePage = new Home_Page();
    }

    @Test
    public void testAddTrainerMenuItem() {
        JMenuItem addTrainerItem = findMenuItem(homePage, "Add Trainer");
        assertNotNull(addTrainerItem);
        assertEquals(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK), addTrainerItem.getAccelerator());
    }

    @Test
    public void testAddCustomerMenuItem() {
        JMenuItem addCustomerItem = findMenuItem(homePage, "Add Customer");
        assertNotNull(addCustomerItem);
        assertEquals(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK), addCustomerItem.getAccelerator());
    }

    @Test
    public void testTrainerInformationMenuItem() {
        JMenuItem trainerInfoItem = findMenuItem(homePage, "Trainer Information");
        assertNotNull(trainerInfoItem);
        assertEquals(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK), trainerInfoItem.getAccelerator());
    }

    @Test
    public void testCustomerInformationMenuItem() {
        JMenuItem customerInfoItem = findMenuItem(homePage, "Customer Information");
        assertNotNull(customerInfoItem);
        assertEquals(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK), customerInfoItem.getAccelerator());
    }

    @Test
    public void testUpdateTrainerMenuItem() {
        JMenuItem updateTrainerItem = findMenuItem(homePage, "Update Trainer");
        assertNotNull(updateTrainerItem);
        assertEquals(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK), updateTrainerItem.getAccelerator());
    }

    @Test
    public void testUpdateCustomerMenuItem() {
        JMenuItem updateCustomerItem = findMenuItem(homePage, "Update Customer");
        assertNotNull(updateCustomerItem);
        assertEquals(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK), updateCustomerItem.getAccelerator());
    }

    @Test
    public void testTrainerFeesMenuItem() {
        JMenuItem trainerFeesItem = findMenuItem(homePage, "Trainer Fees");
        assertNotNull(trainerFeesItem);
        assertEquals(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK), trainerFeesItem.getAccelerator());
    }

    @Test
    public void testCustomerFeesMenuItem() {
        JMenuItem customerFeesItem = findMenuItem(homePage, "Customer Fees");
        assertNotNull(customerFeesItem);
        assertEquals(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK), customerFeesItem.getAccelerator());
    }

    @Test
    public void testExitMenuItem() {
        JMenuItem exitItem = findMenuItem(homePage, "Exit");
        assertNotNull(exitItem);
        assertEquals(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK), exitItem.getAccelerator());
    }

    private JMenuItem findMenuItem(JFrame frame, String text) {
        JMenuBar menuBar = frame.getJMenuBar();
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null && text.equals(item.getText())) {
                    return item;
                }
            }
        }
        return null;
    }
}
