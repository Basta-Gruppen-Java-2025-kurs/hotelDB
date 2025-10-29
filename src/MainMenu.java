import Control.BookingControl;
import Control.CustomerControl;
import Control.RoomControl;
import Helpers.IMenu;
import Helpers.MenuBuilder;

public class MainMenu implements IMenu {
    @Override
    public void menu() {
        MenuBuilder mainMenu = new MenuBuilder("Welcome to \033[1mHotel Database\033[22m application.", "Exit");
        mainMenu.addItem("Customer menu", new CustomerControl()::menu)
                .addItem("Room menu", new RoomControl()::menu)
                .addItem("Booking menu", new BookingControl()::menu);
        mainMenu.runMenu();
        System.out.println("Goodbye.");
    }

}
