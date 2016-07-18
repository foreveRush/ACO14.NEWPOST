package newpost.view;

import newpost.controller.IClientController;
import newpost.controller.IEmployeeManagement;
import newpost.controller.IManagerController;
import newpost.controller.IMoneyController;
import newpost.model.*;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private IClientController clientController;
    private IManagerController managerController;
    private IEmployeeManagement employeeManagement;
    private IMoneyController moneyController;

    public void start(IClientController controller) {
        clientController = controller;

        chooseUser();
         }


    private void chooseUser() {
        System.out.printf("For clients choose: 1\nRor manager choose: 2 \nFor director choose 3 ");
        int user = scanner.nextInt();
        switch (user) {
            case 1:
                clientEnter();
                clientMenuRun();
                break;
            case 2:
                while (true) {
                    System.out.println("Log in:");
                    String managerLog = scanner.next();
                    // Log in validation
                    managerMenuRun();
                }
            case 3:
                System.out.println("Log in:");
                String directorLog = scanner.next();
                // Log in validation
                directorMenuRun();
        }
    }

    private void directorMenuRun() {
        while (true) {
            showMainMenuDirector();
            int choice = scanner.nextInt();
            if (choice == 1) {
                showAddStaffMenu();
            } else if (choice == 2) {
                showRemoveStaffMenu();
            } else if (choice == 3) {
                showFindStaffByNameMenu();
            } else if (choice == 4) {
                showStaffInfo();      // in process
            } else if (choice == 5) {
                showPaySalaryMenu();
            } else if (choice == 6) {
                showPayTaxMenu();
            } else if (choice == 7) {
                showMakePaymentMenu();
            } else if (choice == 8) {
                showFindTransactionByIdMenu();  // test failed
            } else if (choice == 0){
                break;
            }
        }
    }

    private void managerMenuRun() {
        while (true) {
            showMainMenuManager();

            int choice = scanner.nextInt();

            if (choice == 1) {
                showAddTicketMenu();
            } else if (choice == 2) {
                showInfoMenu();
            } else if (choice == 3) {
                showCancelTicketMenu();
            } else if (choice == 4) {
                showAllLogs();
            } else if (choice == 5) {
                showTicketByClientPhoneMenu();
            } else if (choice == 6) {
                showGetClientMenu();
            } else if (choice == 7) {
                showAddClientMenu();
            } else if (choice == 0) {
                break;
            }
        }

    }

    private void clientMenuRun() {
        while (true) {
            showMenuClient();

            int clientChoice = scanner.nextInt();

            if (clientChoice == 1) {
                showAddTicketMenu();
            } else if (clientChoice == 2) {
                showInfoMenu();
            } else if (clientChoice == 3) {
                showCancelTicketMenu();
            } else if (clientChoice == 4) {
                showTakeProductMenu();
            } else if (clientChoice == 0) {
                break;
            }
        }
    }
    private void showFindTransactionByIdMenu() {
        System.out.println("Input transaction Id");
        String id = scanner.next();
        Transaction transaction = moneyController.findTransactionByID(id);
        System.out.println(transaction.toString());

    }

    private void showMakePaymentMenu() {
        System.out.println("Input ART-Post bank account");
        int ourAccount = scanner.nextInt();
        System.out.println("Input recipient bank account");
        int recipientAccount = scanner.nextInt();
        System.out.println("Input transfer amount");
        int transferAmount = scanner.nextInt();
        System.out.println("Input payment purpose");
        String paymentPurpose = scanner.nextLine();

        moneyController.makePayment(ourAccount, recipientAccount,transferAmount,paymentPurpose);
        System.out.println("Payment made");
    }

    private void showPayTaxMenu() {
        System.out.println("Input ART-Post bank account");
        int ourAccount = scanner.nextInt();
        System.out.println("Input recipient bank account");
        int recipientAccount = scanner.nextInt();
        System.out.println("Input transfer amount");
        int transferAmount = scanner.nextInt();
        System.out.println("Input payment purpose");
        String paymentPurpose = scanner.nextLine();
        System.out.println("Input income");
        int income = scanner.nextInt();

        moneyController.payTax(new Transaction(ourAccount,recipientAccount,transferAmount,paymentPurpose),income);
        System.out.println("Tax paid");
    }

    private void showPaySalaryMenu() {
        System.out.println("Input employees name");
        String name = scanner.next();
        System.out.println("Input employees surname");
        String surname = scanner.next();
        System.out.println("Input salary amount");
        int salary = scanner.nextInt();
        System.out.println("Input ART-Post bank account");
        int ourAccount = scanner.nextInt();
        System.out.println("Input recipient bank account");
        int recipientAccount = scanner.nextInt();
        System.out.println("Input transfer amount");
        int transferAmount = scanner.nextInt();
        System.out.println("Input payment purpose");
        String paymentPurpose = scanner.nextLine();

        moneyController.paySalary(name,surname,salary,
                new Transaction(ourAccount,recipientAccount,transferAmount,paymentPurpose));
    }

    // TODO: 18.07.2016
    private void showStaffInfo() {
        employeeManagement.showStaffInfo();
    }

    private void showFindStaffByNameMenu() {
        System.out.println("Input employees name");
        String name = scanner.next();
        System.out.println("Input employees surname");
        String surname = scanner.next();
        String fullName = name + surname;

        Employee employee = employeeManagement.findStaffByName(fullName);
        System.out.println(employee.toString());
    }

    private void showRemoveStaffMenu() {
        System.out.println("Input employees name");
        String name = scanner.next();
        System.out.println("Input employees surname");
        String surname = scanner.next();
        String fullName = name + surname;

        employeeManagement.removeStaff(fullName);
        System.out.println("Employee removed");
    }
    private void showAddStaffMenu() {
        System.out.println("Input job title");
        String jobTitle = scanner.next();
        System.out.println("Input employees name");
        String name = scanner.next();
        System.out.println("Input employees surname");
        String surname = scanner.next();
        String fullName = name + surname;
        System.out.println("Input Employees telephone");
        String phone = scanner.next();
        System.out.println("Input salary amount");
        int salary = scanner.nextInt();

        Employee employee = employeeManagement.addStaff(jobTitle,fullName,phone,salary);
        int password = employee.getPassword();
        String login = employee.getLogin();
        System.out.printf("Employee added. Employees password %d, login %s",password,login );
    }

    private void showTakeProductMenu() {
        System.out.println("Input ticket ID");
        String ticketId;
        ticketId = scanner.next();
        Product product = clientController.takeProduct(Integer.parseInt(ticketId));

        System.out.println(product.toString());
    }

    private void showGetClientMenu() {
        System.out.println("Input clients phone");
        String phone;
        phone = scanner.nextLine();
        Client client = managerController.getClient(phone);
        System.out.println(client.toString());
    }

    private void showTicketByClientPhoneMenu() {
        System.out.println("Input clients phone");
        String phone;
        phone = scanner.nextLine();
        PostTicket postTicket = managerController.showTicketByClientPhone(phone);
        System.out.println(postTicket.toString());
    }

    private void showAddClientMenu() {
        System.out.println("Input clients name");
        String name = scanner.next();
        System.out.println("Input clients Family name");
        String surname = scanner.next();
        String fullName = name + surname;
        System.out.println("Input passport number");
        String passportNum = scanner.next();

        Passport passport = new Passport(fullName, passportNum);

        System.out.println("Input clients phone in format +380938976554");
        String phone = scanner.nextLine();

        Client client = managerController.addClient(passport, phone);
        System.out.println("Client  added");
    }

    private void clientEnter() {
        while (true) {
//            System.out.println("Input: 1.I am already have account in Art Post ");
//            System.out.println("Input: 2. I am a new user "); //for receivers
//            int userAnswer = scanner.nextInt();
//            if (userAnswer != 1 && userAnswer != 2) System.out.println("Incorrect input");
//            if (userAnswer == 1) {
            System.out.println("Enter your login");
            String userLog = scanner.next();
            System.out.println("Enter your password");
            String userPass = scanner.next();
            break;
            //validation
            // if wrong System.out.println("Wrong login or password")

        }
    }

    private void showAllLogs() {
        System.out.println("Show all logs: ");
        LogContainer.showAllLogs();
    }

    private void showInfoMenu() {

        System.out.println("Show info: input ticket Id");
        String ticketId;
        ticketId = scanner.next();

        PostTicket postTicket = clientController.showTicketById(String.valueOf(ticketId));
        System.out.println(postTicket.toString());
    }

    private void showCancelTicketMenu() {

        System.out.println("Cancel: input product Id to cancel");
        String productId;
        productId = scanner.next();

        clientController.cancelTicket(Integer.parseInt(productId));
        System.out.println("Your order is canceled");

    }

    private void showAddTicketMenu() {
        System.out.println("Create  a client:");
        String clientPhone = phoneInput();
        String clientFullName = fullNameInput();
        String clientPassportNumber = passportInput();

        Client client = new Client(clientPhone, new Passport(clientFullName, clientPassportNumber));

        System.out.println("Address destination creation: input city ");
        String addressToCity;
        addressToCity = scanner.next();
        System.out.println("Address destination creation: input street ");
        String addressToStreet;
        addressToStreet = scanner.next();
        System.out.println("Address destination creation: input house number ");
        String addressToHouseNum;
        addressToHouseNum = scanner.next();

        Address addrTo = new Address(addressToCity, addressToStreet, addressToHouseNum);

        System.out.println("Product creation: input product name(from 2 to 20 symbols) ");
        String productName;
        productName = scanner.next();
        System.out.println("Product creation: input product length ");
        String productLength;
        productLength = scanner.next();
        System.out.println("Product creation: input product width ");
        String productWidth;
        productWidth = scanner.next();
        System.out.println("Product creation: input product height ");
        String productHeight;
        productHeight = scanner.next();
        System.out.println("Product creation: input product weight ");
        String productWeight;
        productWeight = scanner.next();
        System.out.println("Product creation: input product price ");
        String productPrice;
        productPrice = scanner.next();

        Product product = new Product(productName,
                new Size(Integer.parseInt(productLength),
                        Integer.parseInt(productWidth),
                        Integer.parseInt(productHeight),
                        Integer.parseInt(productWeight)),
                Integer.parseInt(productPrice),
                client);

        PostTicket postTicket = clientController.makeOrder(client, addrTo, product);

        System.out.println("post ticket id is " + postTicket.getId());
    }

    private String fullNameInput() {
        String fullName;
        while (true) {
            System.out.println("Input  first name and family name ");
            fullName = scanner.nextLine();
            if (fullName.isEmpty()) {
                System.out.println("incorrect data");
            } else {
                break;
            }
        }
        return fullName;
    }

    private String passportInput() {
        String passportNumber;
        while (true) {
            System.out.println("Input  passport number in format DF908754((without spaces)) ");
            passportNumber = scanner.next();
            if ((passportNumber.isEmpty() || passportNumber.length() > 8)) {
                System.out.println("incorrect data");
            } else {
                break;
            }
        }
        return passportNumber;
    }

    private String phoneInput() {
        String phone;
        while (true) {
            System.out.println("Input  phone in format: +380935075645 (without spaces)");
            phone = scanner.next();
            if ((phone.length() > 13) || (!(phone.contains("+380")))) {
                System.out.println("incorrect data");
            } else {
                break;
            }
        }
        return phone;
    }

    private void showMainMenuManager() {
        System.out.println("1. Add Ticket");
        System.out.println("2. Show info");
        System.out.println("3. Cancel Ticket");
        System.out.println("4. Show All Logs");
        System.out.println("5.Show Ticket by Clients Number");
        System.out.println("6. Get Client");
        System.out.println("7. Add Client");
        System.out.println("0. Exit");
    }

    private void showMenuClient() {
        System.out.println("1.Add Ticket");
        System.out.println("2.Show info ");
        System.out.println("3.Cancel order");
        System.out.println("4. Take product");
        System.out.println("0. Exit");
    }

    private void showMainMenuDirector() {
        System.out.println("1. Add an Employee");
        System.out.println("2.Fire an Employee");
        System.out.println("3. Find Employee by Name");
        System.out.println("4. Show Staff Info");
        System.out.println("5.Pay Salary");
        System.out.println("6. Pay tax");
        System.out.println("7. Make Payment");
        System.out.println("8.Find Transaction by Id");
        System.out.println("0. Exit");

    }
}
