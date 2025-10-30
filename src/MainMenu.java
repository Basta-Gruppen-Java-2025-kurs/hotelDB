import Control.BookingControl;
import Control.CustomerControl;
import Control.RoomControl;
import Helpers.IMenu;
import Helpers.MenuBuilder;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import service.CustomerService;

public class MainMenu implements IMenu {
    @Override
    public void menu() {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        CustomerService service = new CustomerService(customerDAO);
        CustomerControl customerControl = new CustomerControl(service);

        MenuBuilder mainMenu = new MenuBuilder("Welcome to \033[1mHotel Database\033[22m application.", "Exit");
        mainMenu.addItem("Customer menu", customerControl::menu)
                .addItem("Room menu", new RoomControl()::menu)
                .addItem("Booking menu", new BookingControl()::menu);
        if (!CreateTestData.testDataExists()) {
            mainMenu.addItem("Create test data", CreateTestData::createDataIfNotExists);
        }
        mainMenu.runMenu();
        System.out.println("Goodbye.");
    }

}
