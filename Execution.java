import java.util.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Expression {
   public boolean phoneNumber(String phone) {
       String pattern = "^[0-9+;,*#]+$";
       return Pattern.matches(pattern, phone);
   }

   public boolean email(String mail) {
       String pattern = "^[\\w\\d_][\\w\\d_.]*@[\\w\\d_.]+$";
       return Pattern.matches(pattern, mail);
   }
}
public class AddContact {
    Person person = new Person();
    Expression ob1 = new Expression();

    public void addFirstName(String first) {
        person.setFirstName(first);
    }

    public void addLastName(String last) {
        person.setLastName(last);
    }

    public boolean addNumber(String number) {
        if (ob1.phoneNumber(number)) {
            person.setContactNumbers(number);
            return true;
        }
        return false;
    }

    public boolean addEmail(String mail) {
        if (ob1.email(mail)) {
            person.setEmailAddress(mail);
            return true;
        }
        return false;
    }

    public Person addContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You have chosen to add a new contact:");
        System.out.println("Please enter the name of the Person");
        System.out.print("First Name: ");
        addFirstName(sc.nextLine());
        System.out.print("Last Name: ");
        addLastName(sc.nextLine());
        boolean a = true;
        while (a) {
            System.out.print("Contact Number: ");
            String phoneNumber = sc.nextLine();
            a = addNumber(phoneNumber);
            if (!a) {
                System.out.println("invalid no please re-enter");
                a = true;
                continue;
            }
            System.out.println("Would you like to add another contact number? (y/n): ");

            char ch = (sc.nextLine()).charAt(0);

            a = (ch == 'y') || (ch == 'Y');

        }

        System.out.println("Would you like to add email address? (y/n): y");

        char ch = (sc.nextLine()).charAt(0);
        if (ch == 'y' || ch == 'Y') {
            System.out.print("Email Address: ");
            String mail = sc.nextLine();
            boolean a1 = addEmail(mail);
            while (!a1) {
                System.out.println("invalid email please re-enter");
                System.out.print("Email Address: ");
                a1 = addEmail(sc.nextLine());
            }
        }
        return person;


    }
}



public class DeleteContact {
    public int printContact(ArrayList<Person> ob) {
        Scanner sc = new Scanner(System.in);
        int i = 1;
        System.out.println("Here are all your contacts: ");
        for (Person oj : ob) {
            System.out.println(i++ + ". " + oj.getFirstName() + " " + oj.getLastName());
        }
        System.out.println("Press the number against the contact to delete it: ");
        int fno;
        fno = sc.nextInt() - 1;
        return fno;

    }
}


public class Person implements Comparable<Person>{
    private String firstName;
    private String lastName;
    private String emailAddress;

    public int compareTo(Person other) {
        return firstName.compareTo(other.firstName);
    }

    private ArrayList<String> contactNumbers = new ArrayList<>();

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<String> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(String number) {
        contactNumbers.add(number);
    }
}



public class SearchContact {
    public void searchContact(ArrayList<Person> ob) {
        System.out.println("You could search for a contact from their first names:");
        Scanner sc = new Scanner(System.in);
        String searchName = sc.nextLine();
        int count = 0;
        ArrayList<Integer> ar = new ArrayList<>();
        int i = 0;
        for (Person oj : ob) {
            if (oj.getFirstName().equals(searchName)) {
                ar.add(i);
                count++;
            }
            i++;
        }
        if (count == 0)
            System.out.println("No match found!");
        else
            System.out.println(count + " match found!");
        ar.forEach(index -> {
            ViewContactList object = new ViewContactList();
            object.viewDetails(ob.get(index));
        });

    }
}


public class ViewContactList {
    public void viewDetails(Person oj) {
        System.out.println("-------- * -------- * -------- * --------");
        System.out.println("First Name: " + oj.getFirstName());
        System.out.println("Last Name: " + oj.getLastName());
        String s = "" + oj.getContactNumbers();
        System.out.println("Contact Number(s): " + s.substring(1, s.length() - 1));
        System.out.println("Email address: " + oj.getEmailAddress());
        System.out.println("-------- * -------- * -------- * --------");


    }

    public void viewContactList(ArrayList<Person> ob) {
        System.out.println("---Here are all your contacts---");
        for (Person person : ob) {
            viewDetails(person);
        }
    }
}





package execution;

import java.util.ArrayList;
import java.util.Scanner;
import definition.*;

class Execution {
    public static void main(String[] args) {
        ArrayList<Person> ob = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to VIKAS's Contact List App");
        boolean a = true;
        while (a) {
            try {
                System.out.println("Press 1 to add a new contact");
                System.out.println("Press 2 to view all contacts");
                System.out.println("Press 3 to search for a contact");
                System.out.println("Press 4 to delete a contact");
                System.out.println("Press 5 to exit program");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        
                        AddContact ob1 = new AddContact();
                        ob.add(ob1.addContact());

                        break;
                    case 2:
                        ViewContactList obj = new ViewContactList();
                        obj.viewContactList(ob);

                        break;
                    case 3:
                        SearchContact obj1 = new SearchContact();
                        obj1.searchContact(ob);

                        break;
                    case 4:
                        DeleteContact obj2 = new DeleteContact();
                        int index = obj2.printContact(ob);
                        System.out.println(ob.get(index).getFirstName() + " " + ob.get(index).getLastName() + "'s contact removed!");
                        ob.remove(ob.get(index));

                        break;
                    case 5:
                            a = false;
                        break;
                    default:
                        System.out.println("invalid choice please re-enter");
                        break;

                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}