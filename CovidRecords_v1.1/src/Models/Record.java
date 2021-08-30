package Models;

import DataHandle.AllRecord;

public class Record {

    // A record is a row of data in csv File.

    private String countryName;
    private String continentName;
    private String date;
    private int newCases;
    private int newDeaths;
    private long population;
    private int peopleVaccinated;
    private int newPeopleVaccinated;

    public Record(String countryName,String continentName,String date,int newCases, int newDeaths, long population, int peopleVaccinated){
        setCountryName(countryName);
        setContinentName(continentName);
        setDate(date);
        setNewCases(newCases);
        setNewDeaths(newDeaths);
        setPopulation(population);
        setPeopleVaccinated(peopleVaccinated);
    }

    public int getNewPeopleVaccinated() {
        return newPeopleVaccinated;
    }

    public void setNewPeopleVaccinated() {
        for(int i = 0 ; i < AllRecord.Allrecords.size() ; i++){
            if(i == 0) {
                this.newPeopleVaccinated = this.peopleVaccinated;
                break;
            }
            else if(AllRecord.Allrecords.get(i).getCountryName().equals(this.countryName)
                    && AllRecord.Allrecords.get(i).getDate().equals(this.date)
                    && AllRecord.Allrecords.get(i-1).getCountryName().equals(this.countryName)){
                this.newPeopleVaccinated=this.peopleVaccinated-AllRecord.Allrecords.get(i-1).getPeopleVaccinated();
                break;
            }else if(AllRecord.Allrecords.get(i).getCountryName().equals(this.countryName)
                    && AllRecord.Allrecords.get(i).getDate().equals(this.date)) {
                this.newPeopleVaccinated = this.peopleVaccinated;
                break;
            }
        }
        if(this.newPeopleVaccinated< 0)
            this.newPeopleVaccinated = 0;
    }

    public String getContinentName() {
        return continentName;
    }

    public int getNewCases() {
        return newCases;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDate() {
        return date;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getPeopleVaccinated() {
        return peopleVaccinated;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public void setPeopleVaccinated(int peopleVaccinated) {
        this.peopleVaccinated = peopleVaccinated;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}
