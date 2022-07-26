import java.util.*;
public class Main {
    public static Scanner scanner= new Scanner(System.in);
    public static void main(final String[] args) throws Exception {
        int number = scanner.nextInt();
        List<Customer> personList =new ArrayList<>();
        for(int i=0;i<number;i++){
            Customer employee = new Customer();
            String next0 = scanner.next();
            employee.setName(next0);
            int next1 = scanner.nextInt();
            employee.setGrade(next1);
            int next2 = scanner.nextInt();
            employee.setJobAge(next2);
            Double next3 = scanner.nextDouble();
            employee.setMoney(next3);
            personList.add(employee);
        }
        dataSort(personList);
        for(int p=0;p<personList.size();p++){
            System.out.println(personList.get(p).toString());
        }
    }
    public static void dataSort(List<Customer> personList ){
        Collections.sort(personList, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if(o1.getGrade()<o2.getGrade()){
                    return 1;
                }else if(o1.getGrade()>o2.getGrade()){
                    return -1;
                }else if(o1.getGrade() == o2.getGrade()){
                    if(o1.getMoney()<o2.getMoney()){
                        return -1;
                    }else if (o1.getMoney()>o2.getMoney()){
                        return 1;
                    }else if (o1.getMoney().equals(o2.getMoney())){
                        if (o1.getJobAge()<o2.getJobAge()){
                            return 1;
                        }else if(o1.getJobAge()>o2.getJobAge()){
                            return -1;
                        }else {
                            return 0;
                        }
                    }else {return 0;}
                }else {
                    return 0;
                }
            }
        });
    }
}
class Customer {
    public String name;
    public int grade;
    public int jobAge;
    public Double money;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public int getJobAge() {
        return jobAge;
    }
    public void setJobAge(int jobAge) {
        this.jobAge = jobAge;
    }
    public Double getMoney() {
        return money;
    }
    public void setMoney(Double money) {
        this.money = money;
    }
    @Override
    public String toString() {
        return  name+" "+grade+" "+jobAge+" "+String.format("%.2f", money);
    }
}
