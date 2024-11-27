package telran.employees.client;

import org.json.JSONObject;
import org.json.JSONString;
import telran.net.exceptions.ServerCloseConnectionException;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

import java.io.IOException;

public class ClientOperations
{
    private final Client client;
    private final InputOutput io;

    public ClientOperations(Client client, InputOutput io)
    {
        this.client = client;
        this.io = io;
    }

    public void createMenu()
    {
        Menu menu = new Menu("Company Application", getItems());
        menu.perform(io);
    }

    public Item[] getItems()
    {
        Item[] hireSubmenuItems = {
                Item.of("Hire Employee", this::hire_employee),
                Item.of("Hire Wage Employee", this::hire_wage_employee),
                Item.of("Hire Sales Person", this::hire_sales_person),
                Item.of("Hire Manager", this::hire_manager),
                Item.of("Return to Main menu...", _ -> {}, true)
        };

        Item[] displaySubmenuItems = {
                Item.of("Display Employee data", this::display_employee_by_id),
                Item.of("Department Salary Budget", this::department_salary_budget),
                Item.of("List of Departments", this::departments_list),
                Item.of("Display Managers with Most Factor", this::display_managers_most_factors),
                Item.of("Return to Main menu...", _ -> {}, true)
        };

        Item[] items = {
                createMenuItemWithSubmenu("Hire...", hireSubmenuItems),
                Item.of("Fire Employee", this::fire_employee_by_id),
                createMenuItemWithSubmenu("Display...", displaySubmenuItems),
                Item.ofExit()
        };

        return items;
    }

    private void fire_employee_by_id(InputOutput io)
    {
        String employee_id = io.readString("Enter Employee ID: ");
        processing("fireEmployee", employee_id);
    }

    private  void display_managers_most_factors(InputOutput io)
    {
        processing("getManagersMostFactors", "");
    }

    private void departments_list(InputOutput io)
    {
        processing("getDepartmentsList", "");
    }

    private void department_salary_budget(InputOutput io)
    {
        processing("getDepartmentSalaryBudget", "");
    }

    private void display_employee_by_id(InputOutput io)
    {
        String employee_id = io.readString("Enter Employee ID: ");
        processing("getEmployeeById", employee_id);
    }

    private void hire_manager(InputOutput io)
    {
        String employee_data = io.readObject("Enter the Employee data in the format: \n" +
                        "[id]#[Salary]#[Department]#[factor]",
                "Wrong format for Employee data", str -> {
                    String[] tokens = str.split("#");
                    String result = null;
                    if (tokens.length == 4) {
                        try {
                            JSONObject employee_json = new JSONObject();
                            employee_json.put("className", "telran.employees.Manager");
                            employee_json.put("id", Integer.parseInt(tokens[0]));
                            employee_json.put("basicSalary", Double.parseDouble(tokens[1]));
                            employee_json.put("department", tokens[2]);
                            employee_json.put("factor", Double.parseDouble(tokens[3]));

                            result = employee_json.toString();
                        } catch (NumberFormatException e) {
                            System.out.println("Number format exception: " + e.getMessage()); // Отладочное сообщение
                        } catch (Exception e) {
                            System.out.println("Unexpected exception: " + e.getMessage()); // Отладочное сообщение
                            e.printStackTrace();
                        }                    }
                    return result;
                });

        processing("hireManager", employee_data);
    }

    private void hire_sales_person(InputOutput io) {
        String employee_data = io.readObject("Enter the Employee data in the format: \n" +
                        "[id]#[Salary]#[Department]#[wage]#[hours]#[percents]#[sales]",
                "Wrong format for Employee data", str -> {
                    String[] tokens = str.split("#");
                    String result = null;
                    if (tokens.length == 7) {
                        try {
                            JSONObject employee_json = new JSONObject();
                            employee_json.put("className", "telran.employees.SalesPerson");
                            employee_json.put("id", Integer.parseInt(tokens[0]));
                            employee_json.put("basicSalary", Double.parseDouble(tokens[1]));
                            employee_json.put("department", tokens[2]);
                            employee_json.put("wage", Integer.parseInt(tokens[3]));
                            employee_json.put("hours", Integer.parseInt(tokens[4]));
                            employee_json.put("percent", Float.parseFloat(tokens[5]));
                            employee_json.put("sales", Long.parseLong(tokens[6]));

                            result = employee_json.toString();
                        } catch (NumberFormatException e) {
                            System.out.println("Number format exception: " + e.getMessage()); // Отладочное сообщение
                        } catch (Exception e) {
                            System.out.println("Unexpected exception: " + e.getMessage()); // Отладочное сообщение
                            e.printStackTrace();
                        }                    }
                    return result;
                });

        processing("hireSalesPerson", employee_data);
    }

    private void hire_wage_employee(InputOutput io)
    {
        String employee_data = io.readObject("Enter the Employee data in the format: \n" +
                        "[id]#[Salary]#[Department]#[wage]#[hours]",
                "Wrong format for Employee data", str -> {
                    String[] tokens = str.split("#");
                    String result = null;
                    if (tokens.length == 5) {
                        try {
                            JSONObject employee_json = new JSONObject();
                            employee_json.put("className", "telran.employees.WageEmployee");
                            employee_json.put("id", Integer.parseInt(tokens[0]));
                            employee_json.put("basicSalary", Double.parseDouble(tokens[1]));
                            employee_json.put("department", tokens[2]);
                            employee_json.put("wage", Integer.parseInt(tokens[3]));
                            employee_json.put("hours", Integer.parseInt(tokens[4]));

                            result = employee_json.toString();
                        } catch (NumberFormatException e) {
                            System.out.println("Number format exception: " + e.getMessage()); // Отладочное сообщение
                        } catch (Exception e) {
                            System.out.println("Unexpected exception: " + e.getMessage()); // Отладочное сообщение
                            e.printStackTrace();
                        }                    }
                    return result;
                });

        processing("hireWageEmployee", employee_data);
    }

    private void hire_employee(InputOutput io) {
        String employee_data = io.readObject("Enter the Employee data in the format: \n" +
                        "[id]#[Salary]#[Department]",
                "Wrong format for Employee data", str -> {
                    String[] tokens = str.split("#");
                    String result = null;
                    if (tokens.length == 3) {
                        try {
                            Integer.parseInt(tokens[0]); // id
                            Double.parseDouble(tokens[1]); // Salary

                            JSONObject employee_json = new JSONObject();
                            employee_json.put("className", "telran.employees.Employee");
                            employee_json.put("id", Integer.parseInt(tokens[0]));
                            employee_json.put("basicSalary", Double.parseDouble(tokens[1]));
                            employee_json.put("department", tokens[2]);

                            result = employee_json.toString();
                        } catch (NumberFormatException e) {
                            System.out.println("Number format exception: " + e.getMessage()); // Отладочное сообщение
                        } catch (Exception e) {
                            System.out.println("Unexpected exception: " + e.getMessage()); // Отладочное сообщение
                            e.printStackTrace();
                        }                    }
                    return result;
                });

        processing("hireEmployee", employee_data);
    }

    private Item createMenuItemWithSubmenu(String title, Item[] submenuItems) {
        return Item.of(title, io -> {
            Menu submenu = new Menu(title, submenuItems);
            submenu.perform(io);
        });
    }

    private void processing(String request_type, String request_data)
    {
        try {
            String response = client.getClient().processSendAndReceive(request_type, request_data);
            io.writeLine(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new ServerCloseConnectionException(Settings.host, client.getCurrentPort());
        }
    }

}
