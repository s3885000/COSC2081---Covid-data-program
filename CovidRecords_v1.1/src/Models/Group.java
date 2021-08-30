package Models;

import DataHandle.AllRecord;

import java.util.ArrayList;

public class Group {

    private ArrayList<Record> records = new ArrayList<>(); // A group basically contains list of records(list of data of different dates)
    int uptoCases=0,uptoDeaths=0,uptoVacc;
    int totalCases=0, totalDeaths=0,totalVacc;

    public void setResults(int locationType){

        uptoCases=0;uptoDeaths=0;uptoVacc=0;
        totalCases=0; totalDeaths=0;totalVacc=0;

        for (Record record1 : AllRecord.Allrecords) {
            if(locationType==1) {
                if (this.getRecords().get(0).getCountryName().equals(record1.getCountryName())) {
                    uptoCases += record1.getNewCases();
                    uptoDeaths += record1.getNewDeaths();
                    if (record1.getPeopleVaccinated() > 0)
                        uptoVacc = record1.getPeopleVaccinated();
                    if (record1.getDate().equals(this.getRecords().get(this.getRecords().size() - 1).getDate()))
                        break;
                }
            }else {
                if (this.getRecords().get(0).getContinentName().equals(record1.getContinentName())) {
                    uptoCases += record1.getNewCases();
                    uptoDeaths += record1.getNewDeaths();
                    if (record1.getPeopleVaccinated() > 0)
                        uptoVacc = record1.getPeopleVaccinated();
                    if (record1.getDate().equals(this.getRecords().get(this.getRecords().size() - 1).getDate()) && record1.getCountryName().equals(this.getRecords().get(getRecords().size()-1).getCountryName()))
                        break;
                }
            }
        }
        boolean isdone=false;
        for (Record record : getRecords()) {
            for(int i=0 ; i < AllRecord.Allrecords.size() ; i++){
                if(record.getCountryName().equals("Afghanistan") && record.getDate().equals("2/24/2020")){
                    totalVacc = uptoVacc-getRecords().get(0).getPeopleVaccinated();
                }else if(record==(AllRecord.Allrecords.get(i)) && record.getCountryName().equals(AllRecord.Allrecords.get(i-1).getCountryName())){
                    if(i==0)
                        totalVacc=uptoVacc-AllRecord.Allrecords.get(i).getPeopleVaccinated();
                    else
                        totalVacc=uptoVacc-AllRecord.Allrecords.get(i-1).getPeopleVaccinated();
                    isdone=true;
                    break;
                }else if (record==(AllRecord.Allrecords.get(i))){
                    totalVacc = uptoVacc-getRecords().get(0).getPeopleVaccinated();
                    isdone=true;
                    break;
                }
                if(isdone)
                    break;
            }
            totalCases += record.getNewCases();
            totalDeaths += record.getNewDeaths();
        }
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public int getUptoVacc() {
        return uptoVacc;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getTotalVacc() {
        return totalVacc;
    }

    public int getUptoCases() {
        return uptoCases;
    }

    public int getUptoDeaths() {
        return uptoDeaths;
    }
}
