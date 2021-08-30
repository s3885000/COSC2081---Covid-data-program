package Actions;

import DataHandle.AllRecord;
import Interface.Menu;
import Models.Group;
import Models.Record;

import java.util.Scanner;

public class ShowResults {

    Scanner scan = new Scanner(System.in);

    public void showTable(int Locationtype, Record record){
        Menu menu = new Menu();
        record.setNewPeopleVaccinated();

        System.out.printf("\n\t\t%-30.30s  %-30.30s  %-30.30s  %-30.30s%n\n", "Date" ,"New Cases","New Deaths", "New People Vaccinated");
        System.out.printf("\n\t\t%-30.30s  %-30.30s  %-30.30s  %-30.30s%n\n", record.getDate(),record.getNewCases() , record.getNewDeaths() , record.getNewPeopleVaccinated());

        int uptoCases=0,uptoDeaths=0;

        for(Record record1 : AllRecord.Allrecords){
            if(record.getCountryName().equals(record1.getCountryName())){
                uptoCases+=record1.getNewCases();
                uptoDeaths+=record1.getNewDeaths();
                if(record1.getDate().equals(record.getDate()))
                    break;
            }
        }

        System.out.printf("\n\t\t%-30.30s  %-30.30s  %-30.30s  %-30.30s%n\n", "Upto Data" ,uptoCases,uptoDeaths, record.getPeopleVaccinated());

        int choice=1;
        do{
            if(choice!=1 && choice!=2){
                System.out.println("\nEnter Choice from 1 or 2!\n");
            }
            System.out.print("Enter \n1- Check Again \n2- Go Back : ");
            choice = scan.nextInt();
        }while (choice!=1 && choice!=2);

        if(choice==1)
            if(Locationtype==1)
                menu.noRangeMenu(Locationtype,record.getCountryName());
            else
                menu.noRangeMenu(Locationtype,record.getContinentName());
        else
            menu.startMenu(false);
    }

    public void showTable(int Locationtype,int resultType, int groupMethod) {

        if (resultType == 3) {
            System.out.println("\nAll Groups :- ");
            int i = 0;
            for (; i < AllRecord.Allgroups.size(); i++) {
                System.out.println(i + 1 + "- " + AllRecord.Allgroups.get(i).getRecords().get(0).getDate() + " to " + AllRecord.Allgroups.get(i).getRecords().get(AllRecord.Allgroups.get(i).getRecords().size() - 1).getDate());
            }
            int index = 1;
            do {
                if (index < 1 || index > i)
                    System.out.println("\nEnter in between the Range!\n");
                System.out.print("\nEnter Index of The Group You want summary of : ");
                index = scan.nextInt();
            } while (index < 1 || index > i);
            Group group = AllRecord.Allgroups.get(index - 1);

            System.out.printf("\n\t\t%-30.30s %-30.30s %-30.30s  %-30.30s  %-30.30s%n\n","Country" ,"Date", "New Cases", "New Deaths", "People Vaccinated");

            for (Record record : group.getRecords()) {
                System.out.printf("\n\t\t%-30.30s %-30.30s %-30.30s  %-30.30s  %-30.30s%n\n", record.getCountryName() ,record.getDate(), record.getNewCases(), record.getNewDeaths(), record.getNewPeopleVaccinated());
            }
            group.setResults(Locationtype);
            System.out.printf("\n\t\t%-30.30s %-30.30s %-30.30s  %-30.30s  %-30.30s%n\n", "Total Results : ", "----" , group.getTotalCases(), group.getTotalDeaths(), group.getTotalVacc());
            System.out.printf("\n\t\t%-30.30s %-30.30s %-30.30s  %-30.30s  %-30.30s%n\n", "Upto Results : ", "----" ,group.getUptoCases(), group.getUptoDeaths(), group.getUptoVacc());

        } else {
            if (resultType == 1)
                System.out.printf("\n\t\t%-30.30s  %-30.30s %-30.30s  %-30.30s  %-30.30s%n\n", "Group Number", "Range", "Total New Cases", "Total New Deaths", "Total People Vaccinated");
            else
                System.out.printf("\n\t\t%-30.30s  %-30.30s %-30.30s  %-30.30s  %-30.30s%n\n", "Group Number", "Range", "Upto Cases", "Upto Deaths", "Upto People Vaccinated");

            int i = 1;
            for (Group group : AllRecord.Allgroups) {
                group.setResults(Locationtype);
                if (resultType == 1)
                    System.out.printf("\n\t\t%-30.30s %-30.30s %-30.30s  %-30.30s  %-30.30s%n\n", i, AllRecord.Allgroups.get(i - 1).getRecords().get(0).getDate() + " to " + AllRecord.Allgroups.get(i - 1).getRecords().get(AllRecord.Allgroups.get(i - 1).getRecords().size() - 1).getDate(), group.getTotalCases(), group.getTotalDeaths(), group.getTotalVacc());
                else
                    System.out.printf("\n\t\t%-30.30s %-30.30s %-30.30s  %-30.30s  %-30.30s%n\n", i, AllRecord.Allgroups.get(i - 1).getRecords().get(0).getDate() + " to " + AllRecord.Allgroups.get(i - 1).getRecords().get(AllRecord.Allgroups.get(i - 1).getRecords().size() - 1).getDate(), group.getUptoCases(), group.getUptoDeaths(), group.getUptoVacc());
                i++;
            }
        }
            Menu menu = new Menu();

            int choice = 1;
            do {
                if (choice != 1 && choice != 2) {
                    System.out.println("\nEnter Choice from 1 or 2!\n");
                }
                System.out.print("Enter \n1- Check Again \n2- Go Back : ");
                choice = scan.nextInt();
            } while (choice != 1 && choice != 2);

            if (choice == 1 && groupMethod == 1)
                if(Locationtype==1)
                    menu.groupingByDaysMenu(Locationtype,AllRecord.Allgroups.get(0).getRecords().get(0).getCountryName());
                else
                    menu.groupingByDaysMenu(Locationtype,AllRecord.Allgroups.get(0).getRecords().get(0).getContinentName());
            else if (choice == 1 && groupMethod == 2)
                if(Locationtype==1)
                    menu.groupingByNumMenu(Locationtype,AllRecord.Allgroups.get(0).getRecords().get(0).getCountryName());
                else
                    menu.groupingByNumMenu(Locationtype,AllRecord.Allgroups.get(0).getRecords().get(0).getContinentName());
            else
                menu.startMenu(false);

    }

    public void showGraph(int Locationtype ,int groupMethod) {

        String[][]  Graph = new String[24][80];

        for(int i=0 ; i < 24 ; i++)
            for(int j=0 ; j < 80 ; j++)
                Graph[i][j]=" ";

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 79; j >= 0; j--) {
                if (i == 0)
                    Graph[i][j] = "_";
                else if(j==0)
                    Graph[i][j] = "|";
            }
        }

        int max=0;int min=100000000,maxTD=0,minTD=1000000000,maxTV=0,minTV=1000000000;  // maxTD = max total Deaths  // maxTV = max total vaccinated
        for(Group group : AllRecord.Allgroups){
            group.setResults(Locationtype);
            if(group.getTotalCases() > max)
                max=group.getTotalCases();
            if(group.getTotalCases() < min)
                min=group.getTotalCases();

            if(group.getTotalDeaths() > maxTD)
                maxTD=group.getTotalDeaths();
            if(group.getTotalDeaths() < minTD)
                minTD=group.getTotalDeaths();

            if(group.getTotalVacc() > maxTV)
                maxTV=group.getTotalVacc();
            if(group.getTotalVacc() < minTV)
                minTV=group.getTotalVacc();
        }

        int gap = (int) Math.round((double) (max)/23)+1 ;
        int gapTD = (int) Math.round((double) (maxTD)/23)+1;
        int gapTV = (int) Math.round((double) (maxTV)/23)+1;

        System.out.println("\nX-Axis : 1 to 80  |  1 dash(-) = 1 point \n");
        System.out.println("Y-Axis : "+ min +" to " + max + "  |  1 pipe(|) = " + gap + " point/s \n");

        for(int k=0 ; k < AllRecord.Allgroups.size() ; k++) {
            AllRecord.Allgroups.get(k).setResults(Locationtype);
            for (int i = min,j=0; i < max; i+=gap,j++) {
                int temp=0,diff=0,min1=1000000;
                for(int a=min ; a <= max ; a+=gap){
                    diff=AllRecord.Allgroups.get(k).getTotalCases()-a;
                    if(diff<0)
                        diff*=-1;
                    if(diff<min1){
                        min1=diff;
                        temp=a;
                    }
                }
                if (i == temp ){
                    if(k<79)
                        Graph[j][k+1]="*";
                }
            }
        }

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 0; j < 80; j++)
                System.out.print(Graph[i][j]);
            System.out.println();
        }

        System.out.println("\nGraph of Total New Cases in Each Group\n");


        System.out.println("\nX-Axis : 1 to 80  |  1 dash(-) = 1 point \n");
        System.out.println("Y-Axis : "+ minTD +" to " + maxTD + "  |  1 pipe(|) = " + gapTD + " point/s \n");

        for(int i=0 ; i < 24 ; i++)
            for(int j=0 ; j < 80 ; j++)
                Graph[i][j]=" ";

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 79; j >= 0; j--) {
                if (i == 0)
                    Graph[i][j] = "_";
                else if(j==0)
                    Graph[i][j] = "|";
            }
        }

        for(int k=0 ; k < AllRecord.Allgroups.size() ; k++) {
            AllRecord.Allgroups.get(k).setResults(Locationtype);
            for (int i = minTD,j=0; i < maxTD; i+=gapTD,j++) {
                int temp=0,diff=0,min1=1000000;
                for(int a=minTD ; a <= maxTD ; a+=gapTD){
                    diff=AllRecord.Allgroups.get(k).getTotalDeaths()-a;
                    if(diff<0)
                        diff*=-1;
                    if(diff<min1){
                        min1=diff;
                        temp=a;
                    }
                }
                if (i == temp ){
                    if(k<79)
                        Graph[j][k+1]="*";
                }
            }
        }

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 0; j < 80; j++)
                System.out.print(Graph[i][j]);
            System.out.println();
        }

        System.out.println("\nGraph of Total New Deaths in Each Group\n");

        System.out.println("\nX-Axis : 1 to 80  |  1 dash(-) = 1 point \n");
        System.out.println("Y-Axis : "+ minTV +" to " + maxTV + "  |  1 pipe(|) = " + gapTV + " point/s \n");

        for(int i=0 ; i < 24 ; i++)
            for(int j=0 ; j < 80 ; j++)
                Graph[i][j]=" ";

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 79; j >= 0; j--) {
                if (i == 0)
                    Graph[i][j] = "_";
                else if(j==0)
                    Graph[i][j] = "|";
            }
        }

        for(int k=0 ; k < AllRecord.Allgroups.size() ; k++) {
            AllRecord.Allgroups.get(k).setResults(Locationtype);
            for (int i = minTV,j=0; i < maxTV; i+=gapTV,j++) {
                int temp=0,diff=0,min1=10000000;
                for(int a=minTV ; a <= maxTV ; a+=gapTV){
                    diff=AllRecord.Allgroups.get(k).getTotalVacc()-a;
                    if(diff<0)
                        diff*=-1;
                    if(diff<min1){
                        min1=diff;
                        temp=a;
                    }
                }
                if (i == temp ){
                    if(k<79)
                        Graph[j][k+1]="*";
                }
            }
        }

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 0; j < 80; j++)
                System.out.print(Graph[i][j]);
            System.out.println();
        }

        System.out.println("\nGraph of Total New Vaccinated People in Each Group\n");

        Menu menu = new Menu();

        int choice=1;
        do{
            if(choice!=1 && choice!=2){
                System.out.println("\nEnter Choice from 1 or 2!\n");
            }
            System.out.print("Enter \n1- Check Again \n2- Go Back : ");
            choice = scan.nextInt();
        }while (choice!=1 && choice!=2);

        if(choice==1 && groupMethod==1) {
            if (Locationtype == 1)
                menu.groupingByDaysMenu(Locationtype, AllRecord.Allgroups.get(0).getRecords().get(0).getCountryName());
            else
                menu.groupingByDaysMenu(Locationtype, AllRecord.Allgroups.get(0).getRecords().get(0).getContinentName());
        } else if(choice ==1 && groupMethod==2) {
            if(Locationtype==1)
                menu.groupingByNumMenu(Locationtype,AllRecord.Allgroups.get(0).getRecords().get(0).getCountryName());
            else
                menu.groupingByNumMenu(Locationtype,AllRecord.Allgroups.get(0).getRecords().get(0).getContinentName());
        }
        else
            menu.startMenu(false);

    }


    public void showUptoGraph(int Locationtype,int groupMethod) {

        String[][]  Graph = new String[24][80];

        for(int i=0 ; i < 24 ; i++)
            for(int j=0 ; j < 80 ; j++)
                Graph[i][j]=" ";

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 79; j >= 0; j--) {
                if (i == 0)
                    Graph[i][j] = "_";
                else if(j==0)
                    Graph[i][j] = "|";
            }
        }

        int max=0;int min=100000000,maxTD=0,minTD=1000000000,maxTV=0,minTV=1000000000;  // max = max upto cases // maxTD = max upto Deaths  // maxTV = max upto vaccinated
        for(Group group : AllRecord.Allgroups){
            group.setResults(Locationtype);
            if(group.getUptoCases() > max)
                max=group.getUptoCases();
            if(group.getUptoCases() < min)
                min=group.getUptoCases();

            if(group.getUptoDeaths() > maxTD)
                maxTD=group.getUptoDeaths();
            if(group.getUptoDeaths() < minTD)
                minTD=group.getUptoDeaths();

            if(group.getUptoVacc() > maxTV)
                maxTV=group.getUptoVacc();
            if(group.getUptoVacc() < minTV)
                minTV=group.getUptoCases();
        }

        int gap = (int) Math.round((double) (max)/23)+1 ;
        int gapTD = (int) Math.round((double) (maxTD)/23)+1;
        int gapTV = (int) Math.round((double) (maxTV)/23)+1;

        System.out.println("\nX-Axis : 1 to 80  |  1 dash(-) = 1 point \n");
        System.out.println("Y-Axis : "+ min +" to " + max + "  |  1 pipe(|) = " + gap + " point/s \n");

        for(int k=0 ; k < AllRecord.Allgroups.size() ; k++) {
            AllRecord.Allgroups.get(k).setResults(Locationtype);
            for (int i = min,j=0; i < max; i+=gap,j++) {
                int temp=0,diff=0,min1=1000000;
                for(int a=min ; a <= max ; a+=gap){
                    diff=AllRecord.Allgroups.get(k).getUptoCases()-a;
                    if(diff<0)
                        diff*=-1;
                    if(diff<min1){
                        min1=diff;
                        temp=a;
                    }
                }
                if (i == temp ){
                    if(k<79)
                        Graph[j][k+1]="*";
                }
            }
        }

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 0; j < 80; j++)
                System.out.print(Graph[i][j]);
            System.out.println();
        }

        System.out.println("\nGraph of Upto New Cases in Each Group\n");


        System.out.println("\nX-Axis : 1 to 80  |  1 dash(-) = 1 point \n");
        System.out.println("Y-Axis : "+ minTD +" to " + maxTD + "  |  1 pipe(|) = " + gapTD + " point/s \n");

        for(int i=0 ; i < 24 ; i++)
            for(int j=0 ; j < 80 ; j++)
                Graph[i][j]=" ";

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 79; j >= 0; j--) {
                if (i == 0)
                    Graph[i][j] = "_";
                else if(j==0)
                    Graph[i][j] = "|";
            }
        }

        for(int k=0 ; k < AllRecord.Allgroups.size() ; k++) {
            AllRecord.Allgroups.get(k).setResults(Locationtype);
            for (int i = minTD,j=0; i < maxTD; i+=gapTD,j++) {
                int temp=0,diff=0,min1=1000000;
                for(int a=minTD ; a <= maxTD ; a+=gapTD){
                    diff=AllRecord.Allgroups.get(k).getUptoDeaths()-a;
                    if(diff<0)
                        diff*=-1;
                    if(diff<min1){
                        min1=diff;
                        temp=a;
                    }
                }
                if (i == temp ){
                    if(k<79)
                        Graph[j][k+1]="*";
                }
            }
        }

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 0; j < 80; j++)
                System.out.print(Graph[i][j]);
            System.out.println();
        }

        System.out.println("\nGraph of Upto New Deaths in Each Group\n");

        System.out.println("\nX-Axis : 1 to 80  |  1 dash(-) = 1 point \n");
        System.out.println("Y-Axis : "+ minTV +" to " + maxTV + "  |  1 pipe(|) = " + gapTV + " point/s \n");

        for(int i=0 ; i < 24 ; i++)
            for(int j=0 ; j < 80 ; j++)
                Graph[i][j]=" ";

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 79; j >= 0; j--) {
                if (i == 0)
                    Graph[i][j] = "_";
                else if(j==0)
                    Graph[i][j] = "|";
            }
        }

        for(int k=0 ; k < AllRecord.Allgroups.size() ; k++) {
            AllRecord.Allgroups.get(k).setResults(Locationtype);
            for (int i = minTV,j=0; i < maxTV; i+=gapTV,j++) {
                int temp=0,diff=0,min1=10000000;
                for(int a=minTV ; a <= maxTV ; a+=gapTV){
                    diff=AllRecord.Allgroups.get(k).getUptoVacc()-a;
                    if(diff<0)
                        diff*=-1;
                    if(diff<min1){
                        min1=diff;
                        temp=a;
                    }
                }
                if (i == temp ){
                    if(k<79)
                        Graph[j][k+1]="*";
                }
            }
        }

        for(int i=23 ; i >= 0 ; i--) {
            for (int j = 0; j < 80; j++)
                System.out.print(Graph[i][j]);
            System.out.println();
        }

        System.out.println("\nGraph of Upto New Vaccinated People in Each Group\n");

        Menu menu = new Menu();

        int choice=1;
        do{
            if(choice!=1 && choice!=2){
                System.out.println("\nEnter Choice from 1 or 2!\n");
            }
            System.out.print("Enter \n1- Check Again \n2- Go Back : ");
            choice = scan.nextInt();
        }while (choice!=1 && choice!=2);

        if(choice==1 && groupMethod==1) {
            if(Locationtype==1)
                menu.groupingByDaysMenu(Locationtype,AllRecord.Allgroups.get(0).getRecords().get(0).getCountryName());
            else
                menu.groupingByDaysMenu(Locationtype,AllRecord.Allgroups.get(0).getRecords().get(0).getContinentName());
        }
        else if(choice ==1 && groupMethod==2) {
            if(Locationtype==1)
                menu.groupingByNumMenu(Locationtype,AllRecord.Allgroups.get(0).getRecords().get(0).getCountryName());
            else
                menu.groupingByNumMenu(Locationtype,AllRecord.Allgroups.get(0).getRecords().get(0).getContinentName());
        }
        else
            menu.startMenu(false);

    }

}
