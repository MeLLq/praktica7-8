import java.util.*;

public class Employee {
    public double fxsalary;
    public String position, name, surname;

    Employee() {
    }

    Employee(String position) {
        this.position = position;
    }

    void setFxsalary(double fxsalary) {
        this.fxsalary = fxsalary;
    }

    double getFxsalary() {
        return fxsalary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

interface EmployeePosition {
    String getJobTitle();

    double calcSalary();
}

class Manager implements EmployeePosition {

    @Override
    public String getJobTitle() {
        return "Manager";
    }

    @Override
    public double calcSalary() {
        int temp = 114000 + (int) (Math.random() * 140000);
        ;
        company.CompanyIncome += 0.95 * temp;
        return 0.05 * temp + 50000;
    }
}

class TopManager implements EmployeePosition {


    @Override
    public String getJobTitle() {
        return "Top Manager";
    }

    @Override
    public double calcSalary() {
        if (company.CompanyIncome > 10000000) {
            return 1.5 * 100000 + 100000;
        } else {
            return 100000;
        }
    }
}

class Operator implements EmployeePosition {

    @Override
    public String getJobTitle() {
        return "Operator";
    }

    @Override
    public double calcSalary() {
        return 30000;
    }
}

class company {

    void hire(String position, Employee employee) {
        switch (position) {
            case "Manager":
                employee.setFxsalary(new Manager().calcSalary());
                employee.setPosition(new Manager().getJobTitle());
                break;
            case "Top Manager":
                employee.setFxsalary(new TopManager().calcSalary());
                employee.setPosition(new TopManager().getJobTitle());
                break;
            case "Operator":
                employee.setFxsalary(new Operator().calcSalary());
                employee.setPosition(new Operator().getJobTitle());
                break;
        }
    }

    void hireAll(String position, int sum, List<Employee> employees) {
        for (int i = 0; i < sum; i++) {
            switch (position) {
                case "Manager":
                    employees.add(i, new Employee(new Manager().getJobTitle()));
                    employees.get(i).setFxsalary(new Manager().calcSalary());

                    break;
                case "Top Manager":
                    employees.add(i, new Employee(new TopManager().getJobTitle()));
                    employees.get(i).setFxsalary(new TopManager().calcSalary());
                    break;
                case "Operator":
                    employees.add(i, new Employee(new Operator().getJobTitle()));
                    employees.get(i).setFxsalary(new Operator().calcSalary());
                    break;
            }
        }
        System.out.println(sum + " employees hired.");
    }

    void fire(String name, List<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            if (name == employees.get(i).getName()) {
                System.out.println(employees.get(i).getName() + " fired");
                employees.remove(i);
            }
        }
    }


    private void sortSalary(List<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            for (int j = i; j < employees.size(); j++) {
                if (employees.get(i).getFxsalary() > employees.get(j).getFxsalary()) {
                    Collections.swap(employees, i, j);
                }
            }
        }
    }

    List<Employee> getLowestSalaryStaff(List<Employee> employees, int count) {
        sortSalary(employees);
        if (employees.size() > count) {
            for (int i = count; i < employees.size(); i++) {
                employees.remove(i);
            }
        }
        return employees;
    }

    List<Employee> getTopSalaryStaff(List<Employee> employees, int count) {
        sortSalary(employees);
        if (employees.size() > count) {
            for (int i = employees.size() - count; i > 0; i--) {
                employees.remove(i);
            }
        }
        return employees;
    }

    public static double CompanyIncome;

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<Employee>();
        Employee a = new Employee();
        company b = new company();

        b.hireAll("Manager", 80, employees);
        b.hireAll("Operator", 180, employees);
        b.hireAll("Top Manager", 10, employees);
        System.out.println(employees.size());
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i).getPosition() + " " + employees.get(i).getFxsalary());
            System.out.println();
        }
        employees.clear();
    }
}