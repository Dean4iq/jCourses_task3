package ua.testing;

import ua.testing.controller.Controller;
import ua.testing.model.RecordBook;
import ua.testing.view.View;

/**
 * Start point for the program
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Controller controller = new Controller(new View(), new RecordBook());
        controller.processUser();
    }
}
