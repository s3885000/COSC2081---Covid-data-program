package Interface;


import Actions.ShowResults;
import DataHandle.AllRecord;
import Models.Group;
import Models.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.jar.JarEntry;

public class Menu {

    Scanner scan = new Scanner(System.in);
    ShowResults show = new ShowResults();

    public void startMenu(boolean firstTime){
        int choice = 1;

        if(!firstTime) {
            do {
                if (choice <= 0 || choice > 2)
                    System.out.println("\nPlease Enter from Options 1 or 2\n");

                System.out.print("Enter What you want :- \n1- Continue \n2- Exit : ");
                choice = scan.nextInt();
            } while (choice <= 0 || choice > 2);
        }

        if(choice==2)
            System.exit(0);

        do {
            if (choice <= 0 || choice > 2)
                System.out.println("\nPlease Enter from Options 1 or 2\n");

            System.out.print("Please select your location : \n1- Country \n2- Continent\n");
            choice = scan.nextInt();
        } while (choice <= 0 || choice > 2);

        scan.nextLine();
        String Location;
        boolean isHere = true;
        do {
            if(!isHere)
                System.out.println("\nCountry/Continent Not present in Data! Enter Again!\n");

            isHere=false;
            System.out.print("Enter Country/Continent Name:\n");
            Location = scan.nextLine();
            if(choice==1) {
                for (Record record : AllRecord.Allrecords) {
                    if (record.getCountryName().equalsIgnoreCase(Location)) {
                        isHere = true;
                        break;
                    }
                }
            }
            else{
                for (Record record : AllRecord.Allrecords) {
                    if (record.getContinentName().equalsIgnoreCase(Location)) {
                        isHere = true;
                        break;
                    }
                }
            }
        }while (!isHere);

        int metric=1;
        do {
            if(metric<=0||metric>3)
                System.out.println("\nPlease Enter from Options 1 to 3\n");

            System.out.print("Please choose a Grouping Metric : \n1- No Grouping \n2- Grouping by Number of Days \n3- Grouping by Number of Groups\n");
            metric = scan.nextInt();
        }while (metric<=0||metric>3);

        switch (metric){
            case 1 -> {
                if(choice==1)
                    noRangeMenu(1,Location);
                else
                    noRangeMenu(2,Location);
            }
            case 2 ->{
                if(choice==1)
                    groupingByDaysMenu(1,Location);
                else
                    groupingByDaysMenu(2,Location);
            }
            case 3 ->{
                if(choice==1)
                    groupingByNumMenu(1,Location);
                else
                    groupingByNumMenu(2,Location);
            }
        }

    }

    public void noRangeMenu(int LocationType,String Location){
        int day=1;
        do {
            if(day<=0 || day>31)
                System.out.println("\nEnter Day from 1 to 31 Please!");

            System.out.print("\nEnter Day : ");
            day = scan.nextInt();
        }while(day<=0||day>31);

        int month=1;
        do{
            if(month<=0 || month>12)
                System.out.println("\nEnter Months from 1 to 12 Please!");

            System.out.print("\nEnter Month : ");
            month = scan.nextInt();
        }while (month<0 || month>12);

        int year=2021;
        do{
            if(year!=2021 && year!=2020)
                System.out.println("\nEnter Years 2020 or 2021 Please!");

            System.out.print("\nEnter Year (2020 or 2021) : ");
            year = scan.nextInt();
        }while (year!=2021 && year!=2020);

        String date = month+"/"+day+"/"+year;

        boolean isFound = false;

        for(Record record : AllRecord.Allrecords){
            if(LocationType==1) {
                if (record.getDate().equals(date) && record.getCountryName().equalsIgnoreCase(Location)) {
                    show.showTable(LocationType,record);
                    isFound = true;
                    break;
                }
            }else{
                if (record.getDate().equals(date) && record.getContinentName().equalsIgnoreCase(Location)) {
                    show.showTable(LocationType,record);
                    isFound = true;
                    break;
                }
            }
        }

        if(!isFound){
            System.out.println("\nDate not Present in Data!\n");
            noRangeMenu(LocationType,Location);
        }
    }

    public void groupingByDaysMenu(int Locationtype, String Location) {
        AllRecord.Allgroups.clear();

        int numofGroups=5;
        do {
            if(numofGroups%5!=0)
                System.out.println("\nNumber should be multiple of 5!\n");
            System.out.print("Enter Number of Days you want in one Group (should be multiple of 5) : ");
            numofGroups=scan.nextInt();
        }while (numofGroups%5!=0 );

        int numOfDays=0;
        ArrayList<Record> testRecords = new ArrayList<>();
        for(Record record : AllRecord.Allrecords){
            if(Locationtype==1) {
                if (record.getCountryName().equalsIgnoreCase(Location)) {
                    numOfDays++;
                    testRecords.add(record);
                }
            }else{
                if (record.getContinentName().equalsIgnoreCase(Location)) {
                    numOfDays++;
                    testRecords.add(record);
                }
            }
        }

        if(numOfDays%numofGroups!=0){
            System.out.println("\nNumber of Days can't be equally divide in groups So select another Country!\n");
            startMenu(true);
        }

        for (int i = 0; i < numOfDays / numofGroups; i++)
            AllRecord.Allgroups.add(i, new Group());

        double limit = numofGroups;
        for (int i = 0; i < numOfDays; ) {
            for (int j = 0; j < AllRecord.Allgroups.size(); ) {

                if (i == limit) {
                    j++;
                    limit += numofGroups;
                }

                if (i < numOfDays && i >= 0)
                    AllRecord.Allgroups.get(j).getRecords().add(testRecords.get(i));

                i++;
            }
        }

        int choice=1;
        do {
            if(choice!=1&&choice!=2)
                System.out.println("\nChose Options from 1 or 2!\n");
            System.out.print("\n1- Show Data in Graph\n2- Show Tabular Data : ");
            choice = scan.nextInt();
        }while (choice!=1 && choice!=2);

        if(choice==1){
            int choice1=1;
            do {
                if(choice1!=1&&choice1!=2)
                    System.out.println("\nChose Options from 1 or 2!\n");
                System.out.print("\n1- Total Results\n2- Upto Results : ");
                choice1 = scan.nextInt();
            }while (choice1!=1 && choice1!=2);

            if(choice1==1)
                show.showGraph(Locationtype,1);
            else
                show.showUptoGraph(Locationtype,1);

        }else {
            int choice1=1;
            do {
                if(choice1!=1&&choice1!=2&&choice1!=3)
                    System.out.println("\nChose Options from 1 or 2!\n");
                System.out.print("\n1- Total Results\n2- Upto Results\n3- Individual Results : ");
                choice1 = scan.nextInt();
            }while (choice1!=1 && choice1!=2 && choice1!=3);

            show.showTable(Locationtype,choice1, 1);
        }

    }

    public void groupingByNumMenu(int Locationtype , String Location) {
        AllRecord.Allgroups.clear();

        System.out.print("Enter Number of Groups you want : ");
        int numofGroups = scan.nextInt();

        int numOfDays = 0;
        ArrayList<Record> testRecords = new ArrayList<>();
        for (Record record : AllRecord.Allrecords) {
            if(Locationtype==1) {
                if (record.getCountryName().equalsIgnoreCase(Location)) {
                    numOfDays++;
                    testRecords.add(record);
                }
            }else {
                if (record.getContinentName().equalsIgnoreCase(Location)) {
                    numOfDays++;
                    testRecords.add(record);
                }
            }
        }

        for (int i = 0; i < numofGroups; i++)
            AllRecord.Allgroups.add(i, new Group());

        double limit = (double) numOfDays / numofGroups;
        for (int i = 0; i < numOfDays; ) {
            for (int j = 0; j < numofGroups; ) {

                if (i == Math.round(limit)) {
                    if (j != AllRecord.Allgroups.size() - 1)
                        j++;
                    limit += Math.round((double) numOfDays / numofGroups);
                }

                if (i < numOfDays && i >= 0)
                    AllRecord.Allgroups.get(j).getRecords().add(testRecords.get(i));

                i++;
                if (i == numOfDays)
                    break;
            }

        }

        int choice=1;
        do {
            if(choice!=1&&choice!=2)
                System.out.println("\nChose Options from 1 or 2!\n");
            System.out.print("\n1- Show Data in Graph\n2- Show Tabular Data : ");
            choice = scan.nextInt();
        }while (choice!=1 && choice!=2);

        if(choice==1){
            int choice1=1;
            do {
                if(choice1!=1&&choice1!=2)
                    System.out.println("\nChose Options from 1 or 2!\n");
                System.out.print("\n1- Total Results\n2- Upto Results : ");
                choice1 = scan.nextInt();
            }while (choice1!=1 && choice1!=2);

            if(choice1==1)
                show.showGraph(Locationtype,2);
            else
                show.showUptoGraph(Locationtype,2);
        } else {

            int choice1=1;
            do {
                if(choice1!=1&&choice1!=2&&choice1!=3)
                    System.out.println("\nChose Options from 1 or 2!\n");
                System.out.print("\n1- Total Results\n2- Upto Results\n3- Individual Results : ");
                choice1 = scan.nextInt();
            }while (choice1!=1 && choice1!=2 && choice1!=3);

            show.showTable(Locationtype,choice1, 2);

        }
    }

}
