package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements  TimeEntryRepository{

    private long idcounter = 1L;
    private Map<Long, TimeEntry> timeEntryData =  new HashMap<Long, TimeEntry>();

    public TimeEntry create(TimeEntry timeEntry) {

        TimeEntry entry = new TimeEntry();

        entry.setId(idcounter);
        entry.setDate(timeEntry.getDate());
        entry.setHours(timeEntry.getHours());
        entry.setProjectId(timeEntry.getProjectId());
        entry.setUserId(timeEntry.getUserId());
        timeEntryData.put(idcounter, entry);

        idcounter++;
        return entry;
    }

    public TimeEntry find(long id) {
        if(timeEntryData!= null && timeEntryData.containsKey(id)){
            return timeEntryData.get(id);
        } else {
            return null;
        }
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(timeEntryData!= null && timeEntryData.containsKey(id)){
            timeEntry.setId(id);
            timeEntryData.put(id, timeEntry);
            return timeEntryData.get(id);
        } else {
            return null;
        }
    }

    public List<TimeEntry> list() {
        List<TimeEntry> timeEntryList = new ArrayList<>();
        for(Map.Entry<Long, TimeEntry> entry : timeEntryData.entrySet()){
            timeEntryList.add(entry.getValue());
        }
        return timeEntryList;
    }

    public void delete(long id) {

        timeEntryData.remove(id);
    }
}
