import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmployeManagement {


    static class Employee {
        int ids;
        String fname;
        String lname;
        String department;
        String position;
        LocalDateTime loginTime;
        LocalDateTime logoutTime;

        public Employee(int ids, String fname, String lname, String department, String position) {
            this.ids = ids;
            this.fname = fname;
            this.lname = lname;
            this.department = department;
            this.position = position;
        }

  
        public String toString() {
            return "ID: " + ids + "\nFirst Name: " + fname + "\nLast Name: " + lname +
                    "\nDepartment: " + department + "\nPosition: " + position +
                    "\nLogin Time: " + (loginTime != null ? loginTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "Not logged in") +
                    "\nLogout Time: " + (logoutTime != null ? logoutTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "Not logged out") + "\n";
        }
    }
    public static String Filepath = "C:\\Users\\edwar\\OneDrive\\codingInNo\\EmployeeList.txt";
    public static String FilePath = "C:\\Users\\edwar\\OneDrive\\codingInNo\\AttendanceEmployee.txt";
    public static final ArrayList<Employee> employees = new ArrayList<>();
    private static final String ADMIN_USER = "Elmer";
    private static final String ADMIN_PASS = "Bantoy";
    static Scanner sc = new Scanner(System.in);
    static LocalDate date = LocalDate.now();
    static LocalTime time = LocalTime.now();
    static DateTimeFormatter time_format = DateTimeFormatter.ofPattern("HH:mm:ss");
    static DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd/MM/yyyy" );
   

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==========EmployeeManagement============");
            System.out.println("1. Log in as Administrator");
            System.out.println("2. Log in as Employee");
	    System.out.println("3. Log out Employee");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    if (AdministratorAcc()) {
                        AdminPannel();
                    }
                    break;
                case 2:
                    LoginEmployee();
                    break;
		case 3:
		    LogOutEmloyee();
		    break;
                case 4:
		    System.out.println("Good byee");
                    return;
                default:
            }
        }
    }

    public static boolean AdministratorAcc() {
        sc.nextLine();
        System.out.print("Enter admin username: ");
        String user = sc.nextLine().trim();

        System.out.print("Enter admin password: ");
        String password = sc.nextLine().trim();

        if (user.equals(ADMIN_USER) && password.equals(ADMIN_PASS)) {
            System.out.println("\nAdmin login successful. Welcome " + user + "!");
            return true;
        } else {
            System.out.println("\nFailed login as admin. Admin '" + user + "' not found or password incorrect.");
            return false;
        }
    }

  public static void LoginEmployee(){
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter First name: ");
        String first_name = sc.nextLine();
        System.out.print("Enter Last name: ");
        String last_name = sc.nextLine();
          boolean isfound = false;
          Employee LoggedIn = null;
          for (int i = 0; i < employees.size(); i++){
        if (employees.get(i).ids == id && employees.get(i).fname.equals(first_name) && employees.get(i).lname.equals(last_name)){
          isfound = true;
          LoggedIn = employees.get(i); 
          LoggedIn.loginTime = LocalDateTime.now();
            System.out.println("Successfully log in" + "\n" + date.format(date_format) + "\n" + time.format(time_format));
        }
      }
                

              if (LoggedIn != null){
                
                System.out.println("Log in record :" + date.format(date_format) + "\n " + time.format(time_format));
                System.out.println("Log in successfully");

              } else{
            System.out.println("Not existed");
          }
      
      }
   public static void LogOutEmloyee(){
	System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter First name: ");
        String first_name = sc.nextLine();
        System.out.print("Enter Last name: ");
        String last_name = sc.nextLine();
	 boolean isfound = false;
          Employee LoggedOut = null;
          for (int i = 0; i < employees.size(); i++){
        if (employees.get(i).ids == id && employees.get(i).fname.equals(first_name) && employees.get(i).lname.equals(last_name)){
          isfound = true;
          LoggedOut = employees.get(i); 
          LoggedOut.logoutTime = LocalDateTime.now();
            System.out.println("Successfully log out");
        }
      }
	
           
  
              if (LoggedOut != null){
                
                System.out.println("Log out record :" + date.format(date_format) + "\n " + time.format(time_format));
                System.out.println("Log out successfully");

              
          } else{
            System.out.println("Not existed");
          }
      }



    static Boolean AdminPannel() {
        while (true) {
            System.out.println("\n=======Admin Pannel==========");
            System.out.println("1. View Employee list");
            System.out.println("2. Add Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Export log in");
	    System.out.println("5. Export Attendance");
	    System.out.println("6. Back");
            System.out.println("7. Exit");
            System.out.print("select: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    EmployeeList();
                    break;
                case 2:
                    AddEmployee();
                    break;
                case 3:
                    RemoveEmployee();
                    break;
                case 4:
		    exportEmployeeList();
		case 5:
                    exportAttendanceLogs();
		case 6:
		    return false;
                case 7:
                    System.out.println("Goodbye Admin!");
                    
                default:
                    throw new AssertionError();
            }
        }
    }

    public static void AddEmployee() {
	System.out.println("\n ===========Use ID higher than 1=============");
        System.out.print("Enter your ID: ");
        int ids = sc.nextInt();
	
	
	Boolean checkId = false;
	for ( Employee emp : employees){
	  if (emp.ids == ids ){
		checkId = true;
		break;
	} 		
}
	if (checkId){
	System.out.println("Invalid Id or Id is already used");
	return;
}
        sc.nextLine();
        System.out.print("Enter your Firstname: ");
        String fname = sc.nextLine().trim();
        System.out.print("Enter your Lastname: ");
        String lname = sc.nextLine().trim();
        System.out.print("Enter your Department: ");
        String department = sc.nextLine().trim();
        System.out.print("Enter your position: ");
        String position = sc.nextLine().trim();

        employees.add(new Employee(ids, fname, lname, department, position));
    }

    public static void EmployeeList() {
        if (employees.isEmpty()) {
            System.out.println("No employee");
            return;
        }
        for (Employee emp : employees) {
            System.out.println("\n========================");
            System.out.println(emp);
        }
    }

    public static Boolean RemoveEmployee() {
        while (true) {
            System.out.print("\nEnter Employee ID to delete" + "\n or type 1 to go back: ");
            int id = sc.nextInt();

            if (id == 1) {
                System.out.println("Returning to Admin Panel...");
                return AdminPannel();
            }

            boolean isFound = false;

            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).ids == id) {
                    System.out.println("Employee " + employees.get(i).fname + " " + employees.get(i).lname + " has been removed.");
                    employees.remove(i);
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                System.out.println("Employee with ID " + id + " not found.");
            }
        }
    }
	public static void exportEmployeeList() {
    try (FileWriter writer = new FileWriter(Filepath, true)) {
        writer.write("ID\tFirst Name\tLast Name\tDepartment\tPosition\n");
        for (Employee emp : employees) {
            writer.write(emp.ids + "\t" + emp.fname + "\t" + "\t" + emp.lname + "\t" + "\t" + emp.department + "\t" + "\t" + emp.position + "\n");
        }
        System.out.println("Employee list exported to employees.txt");
    } catch (IOException e) {
        System.out.println("Error exporting employee list.");
    }
}
public static void exportAttendanceLogs() {
    try (FileWriter writer = new FileWriter(FilePath, true)) {
	writer.write("====================Attendance=======================" );
        writer.write("\nID\tDate    \tTime In\t        Time Out\n");
        for (Employee emp : employees) {
            if (emp.loginTime != null && emp.logoutTime != null) {
                String date = emp.loginTime.format(date_format);
                String timeIn = emp.loginTime.format(time_format);
                String timeOut = emp.logoutTime.format(time_format);
                writer.write(emp.ids + "\t" + date + "\t"  + timeIn + "\t"  + timeOut + "\n");
            }
        }
        System.out.println("Attendance logs exported to attendance_logs.txt");
    } catch (IOException e) {
        System.out.println("Error exporting attendance logs.");
    }
}
}

















