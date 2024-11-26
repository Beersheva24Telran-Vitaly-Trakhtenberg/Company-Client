package telran.employees.client;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class ClientOperations 
{
    public static void createMenu()
    {
        Menu menu = new Menu("Company Application", getItems());
    }

    public static Item[] getItems()
    {
        Item[] hireSubmenuItems = {
                Item.of("Hire Employee", ClientOperations::hire_employee),
                Item.of("Hire Wage Employee", ClientOperations::hire_wage_employee),
                Item.of("Hire Sales Person", ClientOperations::hire_sales_person),
                Item.of("Hire Manager", ClientOperations::hire_manager),
                Item.of("Return to Main menu...", _ -> {}, true)
        };

        Item[] displaySubmenuItems = {
                Item.of("Display Employee data", ClientOperations::display_employee_by_id),
                Item.of("Department Salary Budget", ClientOperations::department_salary_budget),
                Item.of("List of Departments", ClientOperations::departments_list),
                Item.of("Display Managers with Most Factor", ClientOperations::display_managers_most_factors),
                Item.of("Return to Main menu...", _ -> {}, true)
        };

        Item[] items = {
                createMenuItemWithSubmenu("Hire...", hireSubmenuItems),
                Item.of("Fire Employee", ClientOperations::fire_employee_by_id),
                createMenuItemWithSubmenu("Display...", displaySubmenuItems),
                Item.ofExit()
        };

        return items;
    }

    private static void fire_employee_by_id(InputOutput inputOutput) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method ClientOperations.fire_employee_by_id() not implemented yet");
    }

    private static void display_managers_most_factors(InputOutput inputOutput) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method ClientOperations.display_managers_most_factors() not implemented yet");
    }

    private static void departments_list(InputOutput inputOutput) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method ClientOperations.departments_list() not implemented yet");
    }

    private static void department_salary_budget(InputOutput inputOutput) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method ClientOperations.department_salary_budget() not implemented yet");
    }

    private static void display_employee_by_id(InputOutput inputOutput) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method ClientOperations.display_employee_by_id() not implemented yet");
    }

    private static void hire_manager(InputOutput inputOutput) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method ClientOperations.hire_manager() not implemented yet");
    }

    private static void hire_sales_person(InputOutput inputOutput) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method ClientOperations.hire_sales_person() not implemented yet");
    }

    private static void hire_wage_employee(InputOutput inputOutput) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method ClientOperations.hire_wage_employee() not implemented yet");
    }

    private static void hire_employee(InputOutput inputOutput) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method ClientOperations.hire_employee() not implemented yet");
    }

    private static Item createMenuItemWithSubmenu(String title, Item[] submenuItems) {
        return Item.of(title, io -> {
            Menu submenu = new Menu(title, submenuItems);
            submenu.perform(io);
        });
    }

}
